import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Resolve, Router, Routes } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Book, IBook } from 'app/shared/model/book.model';
import { BookService } from './book.service';
import { BookComponent } from './book.component';
import { BookDetailComponent } from './book-detail.component';
import { BookUpdateComponent } from './book-update.component';

@Injectable({ providedIn: 'root' })
export class BookResolve implements Resolve<IBook> {
    constructor(private service: BookService, private router: Router) {}

    resolve(route: ActivatedRouteSnapshot): Observable<IBook> | Observable<never> {
        const id = route.params['id'];
        if (id) {
            return this.service.find(id).pipe(
                flatMap((book: HttpResponse<Book>) => {
                    if (book.body) {
                        return of(book.body);
                    } else {
                        this.router.navigate(['404']);
                        return EMPTY;
                    }
                })
            );
        }
        return of(new Book());
    }
}

export const bookRoute: Routes = [
    {
        path: '',
        component: BookComponent,
        data: {
            authorities: [Authority.ADMIN, Authority.LIBRARIAN],
            pageTitle: 'Books'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: BookDetailComponent,
        resolve: {
            book: BookResolve
        },
        data: {
            authorities: [Authority.ADMIN, Authority.LIBRARIAN, Authority.USER],
            pageTitle: 'Books'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: BookUpdateComponent,
        resolve: {
            book: BookResolve
        },
        data: {
            authorities: [Authority.ADMIN, Authority.LIBRARIAN],
            pageTitle: 'Books'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: BookUpdateComponent,
        resolve: {
            book: BookResolve
        },
        data: {
            authorities: [Authority.ADMIN, Authority.LIBRARIAN],
            pageTitle: 'Books'
        },
        canActivate: [UserRouteAccessService]
    }
];
