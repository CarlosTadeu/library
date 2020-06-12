import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ILoan } from 'app/shared/model/loan.model';
import { LoanService } from './loan.service';
import { ILibraryUser } from 'app/shared/model/library-user.model';
import { ICopyBook, CopyBook } from 'app/shared/model/copy-book.model';
import { CopyBookService } from 'app/entities/copy-book/copy-book.service';

type SelectableEntity = ILibraryUser | ICopyBook;

@Component({
    selector: 'jhi-loan-return',
    templateUrl: './loan-return.component.html'
})
export class LoanReturnComponent implements OnInit {
    isSaving = false;

    editForm = this.fb.group({
        id: [],
        available: true,
        bookId: []
    });

    constructor(
        protected loanService: LoanService,
        protected copyBookService: CopyBookService,
        protected activatedRoute: ActivatedRoute,
        private fb: FormBuilder
    ) {}

    ngOnInit(): void {
        this.activatedRoute.data.subscribe(({ copyBook }) => {
            this.updateForm(copyBook);
        });
    }

    updateForm(copyBook: ICopyBook): void {
        this.editForm.patchValue({
            id: copyBook.id,
            available: copyBook.available
        });
    }

    previousState(): void {
        window.history.back();
    }

    save(): void {
        this.isSaving = true;
        const copyBook = this.createFromForm();
        this.subscribeToSaveResponse(this.loanService.return(copyBook));
    }

    private createFromForm(): ICopyBook {
        return {
            ...new CopyBook(),
            id: this.editForm.get(['id'])!.value,
            available: this.editForm.get(['available'])!.value,
            bookId: this.editForm.get(['bookId'])!.value
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
