import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Resolve, Router, Routes } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Author, IAuthor } from 'app/shared/model/author.model';
import { AuthorService } from './author.service';
import { AuthorComponent } from './author.component';
import { AuthorDetailComponent } from './author-detail.component';
import { AuthorUpdateComponent } from './author-update.component';

@Injectable({ providedIn: 'root' })
export class AuthorResolve implements Resolve<IAuthor> {
    constructor(private service: AuthorService, private router: Router) {}

    resolve(route: ActivatedRouteSnapshot): Observable<IAuthor> | Observable<never> {
        const id = route.params['id'];
        if (id) {
            return this.service.find(id).pipe(
                flatMap((author: HttpResponse<Author>) => {
                    if (author.body) {
                        return of(author.body);
                    } else {
                        this.router.navigate(['404']);
                        return EMPTY;
                    }
                })
            );
        }
        return of(new Author());
    }
}

export const authorRoute: Routes = [
    {
        path: '',
        component: AuthorComponent,
        data: {
            authorities: [Authority.ADMIN, Authority.LIBRARIAN],
            pageTitle: 'Authors'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: AuthorDetailComponent,
        resolve: {
            author: AuthorResolve
        },
        data: {
            authorities: [Authority.ADMIN, Authority.LIBRARIAN],
            pageTitle: 'Authors'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: AuthorUpdateComponent,
        resolve: {
            author: AuthorResolve
        },
        data: {
            authorities: [Authority.ADMIN, Authority.LIBRARIAN],
            pageTitle: 'Authors'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: AuthorUpdateComponent,
        resolve: {
            author: AuthorResolve
        },
        data: {
            authorities: [Authority.ADMIN, Authority.LIBRARIAN],
            pageTitle: 'Authors'
        },
        canActivate: [UserRouteAccessService]
    }
];
