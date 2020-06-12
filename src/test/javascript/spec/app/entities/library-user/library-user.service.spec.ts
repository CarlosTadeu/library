import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { LibraryUserService } from 'app/entities/library-user/library-user.service';
import { ILibraryUser, LibraryUser } from 'app/shared/model/library-user.model';

describe('Service Tests', () => {
    describe('LibraryUser Service', () => {
        let injector: TestBed;
        let service: LibraryUserService;
        let httpMock: HttpTestingController;
        let elemDefault: ILibraryUser;
        let expectedResult: ILibraryUser | ILibraryUser[] | boolean | null;
        let currentDate: moment.Moment;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            expectedResult = null;
            injector = getTestBed();
            service = injector.get(LibraryUserService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new LibraryUser(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', currentDate);
        });

        describe('Service methods', () => {
            it('should find an element', () => {
                const returnedFromService = Object.assign(
                    {
                        suspensionDate: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );

                service.find(123).subscribe(resp => (expectedResult = resp.body));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(returnedFromService);
                expect(expectedResult).toMatchObject(elemDefault);
            });

            it('should create a LibraryUser', () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        suspensionDate: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        suspensionDate: currentDate
                    },
                    returnedFromService
                );

                service.create(new LibraryUser()).subscribe(resp => (expectedResult = resp.body));

                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(returnedFromService);
                expect(expectedResult).toMatchObject(expected);
            });

            it('should update a LibraryUser', () => {
                const returnedFromService = Object.assign(
                    {
                        cpf: 'BBBBBB',
                        rg: 'BBBBBB',
                        name: 'BBBBBB',
                        username: 'BBBBBB',
                        address: 'BBBBBB',
                        email: 'BBBBBB',
                        phoneNumber: 'BBBBBB',
                        suspensionDate: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        suspensionDate: currentDate
                    },
                    returnedFromService
                );

                service.update(expected).subscribe(resp => (expectedResult = resp.body));

                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(returnedFromService);
                expect(expectedResult).toMatchObject(expected);
            });

            it('should return a list of LibraryUser', () => {
                const returnedFromService = Object.assign(
                    {
                        cpf: 'BBBBBB',
                        rg: 'BBBBBB',
                        name: 'BBBBBB',
                        username: 'BBBBBB',
                        address: 'BBBBBB',
                        email: 'BBBBBB',
                        phoneNumber: 'BBBBBB',
                        suspensionDate: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        suspensionDate: currentDate
                    },
                    returnedFromService
                );

                service.query().subscribe(resp => (expectedResult = resp.body));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush([returnedFromService]);
                httpMock.verify();
                expect(expectedResult).toContainEqual(expected);
            });

            it('should delete a LibraryUser', () => {
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
