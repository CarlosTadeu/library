import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ILibraryUser, LibraryUser } from 'app/shared/model/library-user.model';
import { LibraryUserService } from './library-user.service';
import { LibraryUserComponent } from './library-user.component';
import { LibraryUserDetailComponent } from './library-user-detail.component';
import { LibraryUserUpdateComponent } from './library-user-update.component';

@Injectable({ providedIn: 'root' })
export class LibraryUserResolve implements Resolve<ILibraryUser> {
  constructor(private service: LibraryUserService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ILibraryUser> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((libraryUser: HttpResponse<LibraryUser>) => {
          if (libraryUser.body) {
            return of(libraryUser.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new LibraryUser());
  }
}

export const libraryUserRoute: Routes = [
  {
    path: '',
    component: LibraryUserComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.ADMIN, Authority.LIBRARIAN],
      defaultSort: 'id,asc',
      pageTitle: 'LibraryUsers'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: LibraryUserDetailComponent,
    resolve: {
      libraryUser: LibraryUserResolve
    },
    data: {
      authorities: [Authority.ADMIN, Authority.LIBRARIAN],
      pageTitle: 'LibraryUsers'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: LibraryUserUpdateComponent,
    resolve: {
      libraryUser: LibraryUserResolve
    },
    data: {
      authorities: [Authority.ADMIN, Authority.LIBRARIAN],
      pageTitle: 'LibraryUsers'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: LibraryUserUpdateComponent,
    resolve: {
      libraryUser: LibraryUserResolve
    },
    data: {
      authorities: [Authority.ADMIN, Authority.LIBRARIAN],
      pageTitle: 'LibraryUsers'
    },
    canActivate: [UserRouteAccessService]
  }
];
