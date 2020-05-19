import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IStudentType, StudentType } from 'app/shared/model/student-type.model';
import { StudentTypeService } from './student-type.service';
import { StudentTypeComponent } from './student-type.component';
import { StudentTypeDetailComponent } from './student-type-detail.component';
import { StudentTypeUpdateComponent } from './student-type-update.component';

@Injectable({ providedIn: 'root' })
export class StudentTypeResolve implements Resolve<IStudentType> {
  constructor(private service: StudentTypeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IStudentType> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((studentType: HttpResponse<StudentType>) => {
          if (studentType.body) {
            return of(studentType.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new StudentType());
  }
}

export const studentTypeRoute: Routes = [
  {
    path: '',
    component: StudentTypeComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'StudentTypes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: StudentTypeDetailComponent,
    resolve: {
      studentType: StudentTypeResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'StudentTypes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: StudentTypeUpdateComponent,
    resolve: {
      studentType: StudentTypeResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'StudentTypes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: StudentTypeUpdateComponent,
    resolve: {
      studentType: StudentTypeResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'StudentTypes'
    },
    canActivate: [UserRouteAccessService]
  }
];
