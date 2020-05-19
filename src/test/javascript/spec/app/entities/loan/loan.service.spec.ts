import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { LoanService } from 'app/entities/loan/loan.service';
import { ILoan, Loan } from 'app/shared/model/loan.model';

describe('Service Tests', () => {
  describe('Loan Service', () => {
    let injector: TestBed;
    let service: LoanService;
    let httpMock: HttpTestingController;
    let elemDefault: ILoan;
    let expectedResult: ILoan | ILoan[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(LoanService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Loan(0, currentDate, currentDate, currentDate, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            loanDate: currentDate.format(DATE_FORMAT),
            dateReturned: currentDate.format(DATE_FORMAT),
            dateToBeReturned: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Loan', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            loanDate: currentDate.format(DATE_FORMAT),
            dateReturned: currentDate.format(DATE_FORMAT),
            dateToBeReturned: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            loanDate: currentDate,
            dateReturned: currentDate,
            dateToBeReturned: currentDate
          },
          returnedFromService
        );

        service.create(new Loan()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Loan', () => {
        const returnedFromService = Object.assign(
          {
            loanDate: currentDate.format(DATE_FORMAT),
            dateReturned: currentDate.format(DATE_FORMAT),
            dateToBeReturned: currentDate.format(DATE_FORMAT),
            numberOfRenewals: 1
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            loanDate: currentDate,
            dateReturned: currentDate,
            dateToBeReturned: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Loan', () => {
        const returnedFromService = Object.assign(
          {
            loanDate: currentDate.format(DATE_FORMAT),
            dateReturned: currentDate.format(DATE_FORMAT),
            dateToBeReturned: currentDate.format(DATE_FORMAT),
            numberOfRenewals: 1
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            loanDate: currentDate,
            dateReturned: currentDate,
            dateToBeReturned: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Loan', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
