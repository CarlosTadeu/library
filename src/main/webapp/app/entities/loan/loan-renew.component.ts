import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILoan } from 'app/shared/model/loan.model';
import { LoanService } from 'app/entities/loan/loan.service';
import { Observable } from 'rxjs';
import { HttpResponse } from '@angular/common/http';

@Component({
    selector: 'jhi-loan-renew',
    templateUrl: './loan-renew.component.html'
})
export class LoanRenewComponent implements OnInit {
    isSaving = false;
    loan: ILoan | null = null;

    constructor(protected activatedRoute: ActivatedRoute, protected loanService: LoanService) {}

    ngOnInit(): void {
        this.activatedRoute.data.subscribe(({ loan }) => (this.loan = loan));
    }

    previousState(): void {
        window.history.back();
    }

    renew(): void {
        this.isSaving = true;
        if (this.loan) {
            this.subscribeToSaveResponse(this.loanService.renew(this.loan));
        }
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
}
