import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ILoan } from 'app/shared/model/loan.model';
import { ICopyBook } from 'app/shared/model/copy-book.model';

type EntityResponseType = HttpResponse<ILoan>;
type EntityArrayResponseType = HttpResponse<ILoan[]>;

@Injectable({ providedIn: 'root' })
export class LoanService {
    public resourceUrl = SERVER_API_URL + 'api/loans';

    constructor(protected http: HttpClient) {}

    create(loan: ILoan): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(loan);
        return this.http
            .post<ILoan>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(loan: ILoan): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(loan);
        return this.http
            .put<ILoan>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    return(copyBook: ICopyBook): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(copyBook);
        return this.http
            .put<ICopyBook>(`${this.resourceUrl}/${'return'}`, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    renew(loan: ILoan): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(loan);
        return this.http
            .put<ILoan>(`${this.resourceUrl}/${'renew'}`, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ILoan>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    findByUser(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ILoan[]>(`${this.resourceUrl}/${'user'}`, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ILoan[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<{}>> {
        return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(loan: ILoan): ILoan {
        const copy: ILoan = Object.assign({}, loan, {
            loanDate: loan.loanDate && loan.loanDate.isValid() ? loan.loanDate.format(DATE_FORMAT) : undefined,
            dateReturned: loan.dateReturned && loan.dateReturned.isValid() ? loan.dateReturned.format(DATE_FORMAT) : undefined,
            dateToBeReturned:
                loan.dateToBeReturned && loan.dateToBeReturned.isValid() ? loan.dateToBeReturned.format(DATE_FORMAT) : undefined
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.loanDate = res.body.loanDate ? moment(res.body.loanDate) : undefined;
            res.body.dateReturned = res.body.dateReturned ? moment(res.body.dateReturned) : undefined;
            res.body.dateToBeReturned = res.body.dateToBeReturned ? moment(res.body.dateToBeReturned) : undefined;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((loan: ILoan) => {
                loan.loanDate = loan.loanDate ? moment(loan.loanDate) : undefined;
                loan.dateReturned = loan.dateReturned ? moment(loan.dateReturned) : undefined;
                loan.dateToBeReturned = loan.dateToBeReturned ? moment(loan.dateToBeReturned) : undefined;
            });
        }
        return res;
    }
}
