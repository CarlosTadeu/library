import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBook } from 'app/shared/model/book.model';
import { ISearch } from 'app/search/search.model';

type EntityResponseType = HttpResponse<IBook>;
type EntityArrayResponseType = HttpResponse<IBook[]>;

@Injectable({ providedIn: 'root' })
export class BookService {
    public resourceUrl = SERVER_API_URL + 'api/books';

    constructor(protected http: HttpClient) {}

    create(book: IBook): Observable<EntityResponseType> {
        return this.http.post<IBook>(this.resourceUrl, book, { observe: 'response' });
    }

    update(book: IBook): Observable<EntityResponseType> {
        return this.http.put<IBook>(this.resourceUrl, book, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IBook>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(search: ISearch): Observable<EntityArrayResponseType> {
        return this.http.put<IBook[]>(`${this.resourceUrl}/search/`, search, { observe: 'response' });
    }

    findAllByTitle(title: string): Observable<EntityArrayResponseType> {
        return this.http.get<IBook[]>(`${this.resourceUrl}/search/title/${title}`, { observe: 'response' });
    }

    findAllByAuthor(author: string): Observable<EntityArrayResponseType> {
        return this.http.get<IBook[]>(`${this.resourceUrl}/search/author/${author}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IBook[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<{}>> {
        return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
