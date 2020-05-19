import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IStudentType } from 'app/shared/model/student-type.model';

type EntityResponseType = HttpResponse<IStudentType>;
type EntityArrayResponseType = HttpResponse<IStudentType[]>;

@Injectable({ providedIn: 'root' })
export class StudentTypeService {
  public resourceUrl = SERVER_API_URL + 'api/student-types';

  constructor(protected http: HttpClient) {}

  create(studentType: IStudentType): Observable<EntityResponseType> {
    return this.http.post<IStudentType>(this.resourceUrl, studentType, { observe: 'response' });
  }

  update(studentType: IStudentType): Observable<EntityResponseType> {
    return this.http.put<IStudentType>(this.resourceUrl, studentType, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IStudentType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IStudentType[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
