import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ILoan } from 'app/shared/model/loan.model';
import { LoanService } from './loan.service';
import { CpfValidator } from 'app/shared/util/cpf-validator';
import { ILoanCreate, LoanCreate } from 'app/shared/model/loan-create.model';

@Component({
    selector: 'jhi-loan-update',
    templateUrl: './loan-update.component.html'
})
export class LoanUpdateComponent implements OnInit {
    loan: ILoan | null = null;
    isSaving = false;

    editForm = this.fb.group({
        userCpf: ['', [Validators.required, Validators.minLength(11), Validators.maxLength(11), CpfValidator.isValidCpf()]],
        copyBookId: ['', [Validators.required]]
    });

    constructor(protected loanService: LoanService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

    ngOnInit(): void {}

    previousState(): void {
        window.history.back();
    }

    save(): void {
        this.isSaving = true;
        const loan = this.createFromForm();
        this.subscribeToSaveResponse(this.loanService.create(loan));
    }

    private createFromForm(): ILoanCreate {
        return {
            ...new LoanCreate(),
            userCpf: this.editForm.get(['userCpf'])!.value,
            copyBookId: this.editForm.get(['copyBookId'])!.value
        };
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ILoan>>): void {
        result.subscribe(
            (res: HttpResponse<ILoan>) => {
                this.loan = res.body || null;
                this.isSaving = false;
            },
            () => this.onSaveError()
        );
    }

    protected onSaveSuccess(): void {
        this.isSaving = false;
        // this.previousState();
    }

    protected onSaveError(): void {
        this.isSaving = false;
    }
}
