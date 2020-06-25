import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ILoan } from 'app/shared/model/loan.model';
import { LoanService } from './loan.service';
import { LoanDeleteDialogComponent } from './loan-delete-dialog.component';
import { Filter, IFilter } from 'app/shared/model/filter.model';

@Component({
    selector: 'jhi-loan',
    templateUrl: './loan.component.html'
})
export class LoanComponent implements OnInit, OnDestroy {
    loans?: ILoan[];
    eventSubscriber?: Subscription;
    option = '';
    value: number | undefined;
    totalItems = 0;

    constructor(protected loanService: LoanService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

    loadAll(): void {
        this.loanService.query().subscribe((res: HttpResponse<ILoan[]>) => (this.loans = res.body || []));
    }

    ngOnInit(): void {
        this.loadAll();
        this.registerChangeInLoans();
    }

    ngOnDestroy(): void {
        if (this.eventSubscriber) {
            this.eventManager.destroy(this.eventSubscriber);
        }
    }

    trackId(index: number, item: ILoan): number {
        // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
        return item.id!;
    }

    canLoad(): boolean {
        return this.option !== '' && this.value !== undefined;
    }

    private createLoanFilter(): IFilter {
        return {
            ...new Filter(),
            option: this.option,
            value: this.value
        };
    }

    search(): void {
        if (this.canLoad()) {
            const search = this.createLoanFilter();
            this.loanService.filter(search).subscribe((res: HttpResponse<ILoan[]>) => this.onSuccess(res.body, res.headers));
        }
    }

    private onSuccess(loans: ILoan[] | null, headers: HttpHeaders): void {
        this.totalItems = Number(headers.get('X-Total-Count'));
        this.loans = loans || [];
    }

    registerChangeInLoans(): void {
        this.eventSubscriber = this.eventManager.subscribe('loanListModification', () => this.loadAll());
    }

    delete(loan: ILoan): void {
        const modalRef = this.modalService.open(LoanDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
        modalRef.componentInstance.loan = loan;
    }
}
