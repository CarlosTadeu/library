import { Moment } from 'moment';

export interface ILoan {
    id?: number;
    loanDate?: Moment;
    dateReturned?: Moment;
    dateToBeReturned?: Moment;
    numberOfRenewals?: number;
    userId?: number;
    copyBookId?: number;
}

export class Loan implements ILoan {
    constructor(
        public id?: number,
        public loanDate?: Moment,
        public dateReturned?: Moment,
        public dateToBeReturned?: Moment,
        public numberOfRenewals?: number,
        public userId?: number,
        public copyBookId?: number
    ) {}
}
