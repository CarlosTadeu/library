import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICopyBook } from 'app/shared/model/copy-book.model';

@Component({
    selector: 'jhi-copy-book-detail',
    templateUrl: './copy-book-detail.component.html'
})
export class CopyBookDetailComponent implements OnInit {
    copyBook: ICopyBook | null = null;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit(): void {
        this.activatedRoute.data.subscribe(({ copyBook }) => (this.copyBook = copyBook));
    }

    previousState(): void {
        window.history.back();
    }
}
