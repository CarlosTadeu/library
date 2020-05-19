import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ILibraryUser } from 'app/shared/model/library-user.model';

type EntityResponseType = HttpResponse<ILibraryUser>;
type EntityArrayResponseType = HttpResponse<ILibraryUser[]>;

@Injectable({ providedIn: 'root' })
export class LibraryUserService {
  public resourceUrl = SERVER_API_URL + 'api/library-users';

  constructor(protected http: HttpClient) {}

  create(libraryUser: ILibraryUser): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(libraryUser);
    return this.http
      .post<ILibraryUser>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(libraryUser: ILibraryUser): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(libraryUser);
    return this.http
      .put<ILibraryUser>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ILibraryUser>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ILibraryUser[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(libraryUser: ILibraryUser): ILibraryUser {
    const copy: ILibraryUser = Object.assign({}, libraryUser, {
      suspensionDate:
        libraryUser.suspensionDate && libraryUser.suspensionDate.isValid() ? libraryUser.suspensionDate.format(DATE_FORMAT) : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.suspensionDate = res.body.suspensionDate ? moment(res.body.suspensionDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((libraryUser: ILibraryUser) => {
        libraryUser.suspensionDate = libraryUser.suspensionDate ? moment(libraryUser.suspensionDate) : undefined;
      });
    }
    return res;
  }
}
