export interface ILoanCreate {
    userCpf?: string;
    copyBookId?: number;
}

export class LoanCreate implements ILoanCreate {
    constructor(public userCpf?: string, public copyBookId?: number) {}
}
