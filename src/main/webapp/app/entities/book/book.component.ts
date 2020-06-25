import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IBook } from 'app/shared/model/book.model';
import { BookService } from './book.service';
import { BookDeleteDialogComponent } from './book-delete-dialog.component';
import { ISearch, Search } from 'app/shared/model/search.model';

@Component({
    selector: 'jhi-book',
    templateUrl: './book.component.html'
})
export class BookComponent implements OnInit, OnDestroy {
    books?: IBook[];
    eventSubscriber?: Subscription;
    option = '';
    value = '';
    totalItems = 0;

    constructor(protected bookService: BookService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

    loadAll(): void {
        this.bookService.query().subscribe((res: HttpResponse<IBook[]>) => (this.books = res.body || []));
    }

    ngOnInit(): void {
        this.loadAll();
        this.registerChangeInBooks();
    }

    ngOnDestroy(): void {
        if (this.eventSubscriber) {
            this.eventManager.destroy(this.eventSubscriber);
        }
    }

    trackId(index: number, item: IBook): number {
        // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
        return item.id!;
    }

    registerChangeInBooks(): void {
        this.eventSubscriber = this.eventManager.subscribe('bookListModification', () => this.loadAll());
    }

    delete(book: IBook): void {
        const modalRef = this.modalService.open(BookDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
        modalRef.componentInstance.book = book;
    }

    canLoad(): boolean {
        return this.option !== '' && this.value !== '';
    }

    private createSearch(): ISearch {
        return {
            ...new Search(),
            option: this.option,
            value: this.value
        };
    }

    search(): void {
        if (this.canLoad()) {
            const search = this.createSearch();
            this.bookService.search(search).subscribe((res: HttpResponse<IBook[]>) => this.onSuccess(res.body, res.headers));
        }
    }

    private onSuccess(books: IBook[] | null, headers: HttpHeaders): void {
        this.totalItems = Number(headers.get('X-Total-Count'));
        this.books = books || [];
    }
}
