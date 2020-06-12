import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILoan } from 'app/shared/model/loan.model';
import { LoanService } from './loan.service';

@Component({
    templateUrl: './loan-delete-dialog.component.html'
})
export class LoanDeleteDialogComponent {
    loan?: ILoan;

    constructor(protected loanService: LoanService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    cancel(): void {
        this.activeModal.dismiss();
    }

    confirmDelete(id: number): void {
        this.loanService.delete(id).subscribe(() => {
            this.eventManager.broadcast('loanListModification');
            this.activeModal.close();
        });
    }
}
