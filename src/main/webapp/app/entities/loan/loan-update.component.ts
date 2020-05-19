import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ILoan, Loan } from 'app/shared/model/loan.model';
import { LoanService } from './loan.service';
import { ILibraryUser } from 'app/shared/model/library-user.model';
import { LibraryUserService } from 'app/entities/library-user/library-user.service';
import { ICopyBook } from 'app/shared/model/copy-book.model';
import { CopyBookService } from 'app/entities/copy-book/copy-book.service';

type SelectableEntity = ILibraryUser | ICopyBook;

@Component({
  selector: 'jhi-loan-update',
  templateUrl: './loan-update.component.html'
})
export class LoanUpdateComponent implements OnInit {
  isSaving = false;
  users: ILibraryUser[] = [];
  copybooks: ICopyBook[] = [];
  loanDateDp: any;
  dateReturnedDp: any;
  dateToBeReturnedDp: any;

  editForm = this.fb.group({
    id: [],
    loanDate: [],
    dateReturned: [],
    dateToBeReturned: [],
    numberOfRenewals: [],
    userId: [],
    copyBookId: []
  });

  constructor(
    protected loanService: LoanService,
    protected libraryUserService: LibraryUserService,
    protected copyBookService: CopyBookService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ loan }) => {
      this.updateForm(loan);

      this.libraryUserService
        .query({ filter: 'loan-is-null' })
        .pipe(
          map((res: HttpResponse<ILibraryUser[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ILibraryUser[]) => {
          if (!loan.userId) {
            this.users = resBody;
          } else {
            this.libraryUserService
              .find(loan.userId)
              .pipe(
                map((subRes: HttpResponse<ILibraryUser>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ILibraryUser[]) => (this.users = concatRes));
          }
        });

      this.copyBookService
        .query({ filter: 'loan-is-null' })
        .pipe(
          map((res: HttpResponse<ICopyBook[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ICopyBook[]) => {
          if (!loan.copyBookId) {
            this.copybooks = resBody;
          } else {
            this.copyBookService
              .find(loan.copyBookId)
              .pipe(
                map((subRes: HttpResponse<ICopyBook>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ICopyBook[]) => (this.copybooks = concatRes));
          }
        });
    });
  }

  updateForm(loan: ILoan): void {
    this.editForm.patchValue({
      id: loan.id,
      loanDate: loan.loanDate,
      dateReturned: loan.dateReturned,
      dateToBeReturned: loan.dateToBeReturned,
      numberOfRenewals: loan.numberOfRenewals,
      userId: loan.userId,
      copyBookId: loan.copyBookId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const loan = this.createFromForm();
    if (loan.id !== undefined) {
      this.subscribeToSaveResponse(this.loanService.update(loan));
    } else {
      this.subscribeToSaveResponse(this.loanService.create(loan));
    }
  }

  private createFromForm(): ILoan {
    return {
      ...new Loan(),
      id: this.editForm.get(['id'])!.value,
      loanDate: this.editForm.get(['loanDate'])!.value,
      dateReturned: this.editForm.get(['dateReturned'])!.value,
      dateToBeReturned: this.editForm.get(['dateToBeReturned'])!.value,
      numberOfRenewals: this.editForm.get(['numberOfRenewals'])!.value,
      userId: this.editForm.get(['userId'])!.value,
      copyBookId: this.editForm.get(['copyBookId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILoan>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
