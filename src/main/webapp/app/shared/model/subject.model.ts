import { IBook } from 'app/shared/model/book.model';

export interface ISubject {
    id?: number;
    subject?: string;
    books?: IBook[];
}

export class Subject implements ISubject {
    constructor(public id?: number, public subject?: string, public books?: IBook[]) {}
}
