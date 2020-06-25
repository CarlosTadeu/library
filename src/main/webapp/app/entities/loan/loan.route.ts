import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Resolve, Router, Routes } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ILoan, Loan } from 'app/shared/model/loan.model';
import { LoanService } from './loan.service';
import { LoanComponent } from './loan.component';
import { LoanDetailComponent } from './loan-detail.component';
import { LoanUpdateComponent } from './loan-update.component';
import { LoanReturnComponent } from './loan-return.component';
import { LoanRenewComponent } from './loan-renew.component';
import { LoanUserComponent } from 'app/entities/loan/loan-user.component';

@Injectable({ providedIn: 'root' })
export class LoanResolve implements Resolve<ILoan> {
    constructor(private service: LoanService, private router: Router) {}

    resolve(route: ActivatedRouteSnapshot): Observable<ILoan> | Observable<never> {
        const id = route.params['id'];
        if (id) {
            return this.service.find(id).pipe(
                flatMap((loan: HttpResponse<Loan>) => {
                    if (loan.body) {
                        return of(loan.body);
                    } else {
                        this.router.navigate(['404']);
                        return EMPTY;
                    }
                })
            );
        }
        return of(new Loan());
    }
}

export const loanRoute: Routes = [
    {
        path: '',
        component: LoanComponent,
        data: {
            authorities: [Authority.ADMIN, Authority.LIBRARIAN, Authority.USER],
            pageTitle: 'Loans'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: LoanDetailComponent,
        resolve: {
            loan: LoanResolve
        },
        data: {
            authorities: [Authority.ADMIN, Authority.LIBRARIAN, Authority.USER],
            pageTitle: 'Loans'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/renew',
        component: LoanRenewComponent,
        resolve: {
            loan: LoanResolve
        },
        data: {
            authorities: [Authority.ADMIN, Authority.LIBRARIAN, Authority.USER],
            pageTitle: 'Loans'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: LoanUpdateComponent,
        resolve: {
            loan: LoanResolve
        },
        data: {
            authorities: [Authority.ADMIN, Authority.LIBRARIAN],
            pageTitle: 'Loans'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: LoanUpdateComponent,
        resolve: {
            loan: LoanResolve
        },
        data: {
            authorities: [Authority.ADMIN],
            pageTitle: 'Loans'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'return',
        component: LoanReturnComponent,
        data: {
            authorities: '',
            pageTitle: 'Loans'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'user',
        component: LoanUserComponent,
        data: {
            authorities: [Authority.USER],
            pageTitle: 'Loans'
        },
        canActivate: [UserRouteAccessService]
    }
];
