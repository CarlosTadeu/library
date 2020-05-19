import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICopyBook, CopyBook } from 'app/shared/model/copy-book.model';
import { CopyBookService } from './copy-book.service';
import { CopyBookComponent } from './copy-book.component';
import { CopyBookDetailComponent } from './copy-book-detail.component';
import { CopyBookUpdateComponent } from './copy-book-update.component';

@Injectable({ providedIn: 'root' })
export class CopyBookResolve implements Resolve<ICopyBook> {
  constructor(private service: CopyBookService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICopyBook> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((copyBook: HttpResponse<CopyBook>) => {
          if (copyBook.body) {
            return of(copyBook.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CopyBook());
  }
}

export const copyBookRoute: Routes = [
  {
    path: '',
    component: CopyBookComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'CopyBooks'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CopyBookDetailComponent,
    resolve: {
      copyBook: CopyBookResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'CopyBooks'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CopyBookUpdateComponent,
    resolve: {
      copyBook: CopyBookResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'CopyBooks'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CopyBookUpdateComponent,
    resolve: {
      copyBook: CopyBookResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'CopyBooks'
    },
    canActivate: [UserRouteAccessService]
  }
];
