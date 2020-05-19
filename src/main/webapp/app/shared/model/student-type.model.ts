export interface IStudentType {
  id?: number;
  studentType?: string;
  numberOfDaysLoan?: number;
  numberOfDaysRenewal?: number;
  maxBooksOnLoan?: number;
  maxRenewalNumber?: number;
}

export class StudentType implements IStudentType {
  constructor(
    public id?: number,
    public studentType?: string,
    public numberOfDaysLoan?: number,
    public numberOfDaysRenewal?: number,
    public maxBooksOnLoan?: number,
    public maxRenewalNumber?: number
  ) {}
}
