import { Moment } from 'moment';

export interface ILibraryUser {
    id?: number;
    cpf?: string;
    rg?: string;
    name?: string;
    username?: string;
    address?: string;
    email?: string;
    phoneNumber?: string;
    suspensionDate?: Moment;
    studentTypeId?: number;
}

export class LibraryUser implements ILibraryUser {
    constructor(
        public id?: number,
        public cpf?: string,
        public rg?: string,
        public name?: string,
        public username?: string,
        public address?: string,
        public email?: string,
        public phoneNumber?: string,
        public suspensionDate?: Moment,
        public studentTypeId?: number
    ) {}
}
