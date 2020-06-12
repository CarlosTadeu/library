import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICopyBook } from 'app/shared/model/copy-book.model';

type EntityResponseType = HttpResponse<ICopyBook>;
type EntityArrayResponseType = HttpResponse<ICopyBook[]>;

@Injectable({ providedIn: 'root' })
export class CopyBookService {
    public resourceUrl = SERVER_API_URL + 'api/copy-books';

    constructor(protected http: HttpClient) {}

    create(copyBook: ICopyBook): Observable<EntityResponseType> {
        return this.http.post<ICopyBook>(this.resourceUrl, copyBook, { observe: 'response' });
    }

    update(copyBook: ICopyBook): Observable<EntityResponseType> {
        return this.http.put<ICopyBook>(this.resourceUrl, copyBook, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICopyBook>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICopyBook[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<{}>> {
        return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
