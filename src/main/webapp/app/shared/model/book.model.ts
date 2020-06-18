import { ISubject } from 'app/shared/model/subject.model';
import { IAuthor } from 'app/shared/model/author.model';

export interface IBook {
    id?: number;
    isbn?: string;
    title?: string;
    publisher?: string;
    publicationYear?: number;
    totalCopies?: number;
    availableCopies?: number;
    // copyBooks?: ICopyBook[];
    subjects?: ISubject[];
    authors?: IAuthor[];
}

export class Book implements IBook {
    constructor(
        public id?: number,
        public isbn?: string,
        public title?: string,
        public publisher?: string,
        public publicationYear?: number,
        public totalCopies?: number,
        public availableCopies?: number,
        // public copyBooks?: ICopyBook[],
        public subjects?: ISubject[],
        public authors?: IAuthor[]
    ) {}
}
