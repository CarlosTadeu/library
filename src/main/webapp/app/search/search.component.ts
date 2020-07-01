import { Component, OnInit } from '@angular/core';
import { BookService } from 'app/entities/book/book.service';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { IBook } from 'app/shared/model/book.model';
import { CopyBookService } from 'app/entities/copy-book/copy-book.service';
import { ISearch, Search } from 'app/shared/model/search.model';

@Component({
    selector: 'jhi-search',
    templateUrl: './search.component.html'
})
export class SearchComponent implements OnInit {
    books?: IBook[];
    option = '';
    value = '';
    totalItems = 0;

    constructor(protected bookService: BookService, protected copyBookService: CopyBookService) {}

    ngOnInit(): void {}

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

    trackId(index: number, item: IBook): number {
        return item.id!;
    }

    private onSuccess(books: IBook[] | null, headers: HttpHeaders): void {
        this.totalItems = Number(headers.get('X-Total-Count'));
        this.books = books || [];
    }
}
