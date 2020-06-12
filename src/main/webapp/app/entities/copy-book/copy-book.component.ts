import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICopyBook } from 'app/shared/model/copy-book.model';
import { CopyBookService } from './copy-book.service';
import { CopyBookDeleteDialogComponent } from './copy-book-delete-dialog.component';

@Component({
    selector: 'jhi-copy-book',
    templateUrl: './copy-book.component.html'
})
export class CopyBookComponent implements OnInit, OnDestroy {
    copyBooks?: ICopyBook[];
    eventSubscriber?: Subscription;

    constructor(protected copyBookService: CopyBookService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

    loadAll(): void {
        this.copyBookService.query().subscribe((res: HttpResponse<ICopyBook[]>) => (this.copyBooks = res.body || []));
    }

    ngOnInit(): void {
        this.loadAll();
        this.registerChangeInCopyBooks();
    }

    ngOnDestroy(): void {
        if (this.eventSubscriber) {
            this.eventManager.destroy(this.eventSubscriber);
        }
    }

    trackId(index: number, item: ICopyBook): number {
        // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
        return item.id!;
    }

    registerChangeInCopyBooks(): void {
        this.eventSubscriber = this.eventManager.subscribe('copyBookListModification', () => this.loadAll());
    }

    delete(copyBook: ICopyBook): void {
        const modalRef = this.modalService.open(CopyBookDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
        modalRef.componentInstance.copyBook = copyBook;
    }
}
