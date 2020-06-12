import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICopyBook, CopyBook } from 'app/shared/model/copy-book.model';
import { CopyBookService } from './copy-book.service';
import { IBook } from 'app/shared/model/book.model';
import { BookService } from 'app/entities/book/book.service';

@Component({
    selector: 'jhi-copy-book-update',
    templateUrl: './copy-book-update.component.html'
})
export class CopyBookUpdateComponent implements OnInit {
    isSaving = false;
    books: IBook[] = [];

    editForm = this.fb.group({
        id: [],
        available: [],
        bookId: []
    });

    constructor(
        protected copyBookService: CopyBookService,
        protected bookService: BookService,
        protected activatedRoute: ActivatedRoute,
        private fb: FormBuilder
    ) {}

    ngOnInit(): void {
        this.activatedRoute.data.subscribe(({ copyBook }) => {
            this.updateForm(copyBook);

            this.bookService.query().subscribe((res: HttpResponse<IBook[]>) => (this.books = res.body || []));
        });
    }

    updateForm(copyBook: ICopyBook): void {
        this.editForm.patchValue({
            id: copyBook.id,
            available: copyBook.available,
            bookId: copyBook.bookId
        });
    }

    previousState(): void {
        window.history.back();
    }

    save(): void {
        this.isSaving = true;
        const copyBook = this.createFromForm();
        if (copyBook.id !== undefined) {
            this.subscribeToSaveResponse(this.copyBookService.update(copyBook));
        } else {
            this.subscribeToSaveResponse(this.copyBookService.create(copyBook));
        }
    }

    private createFromForm(): ICopyBook {
        return {
            ...new CopyBook(),
            id: this.editForm.get(['id'])!.value,
            available: this.editForm.get(['available'])!.value,
            bookId: this.editForm.get(['bookId'])!.value
        };
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ICopyBook>>): void {
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

    trackById(index: number, item: IBook): any {
        return item.id;
    }
}
