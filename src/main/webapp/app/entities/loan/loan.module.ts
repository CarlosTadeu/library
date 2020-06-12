import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LibrarySharedModule } from 'app/shared/shared.module';
import { LoanComponent } from './loan.component';
import { LoanDetailComponent } from './loan-detail.component';
import { LoanUpdateComponent } from './loan-update.component';
import { LoanReturnComponent } from './loan-return.component';
import { LoanRenewComponent } from './loan-renew.component';
import { LoanUserComponent } from './loan-user.component';
import { LoanDeleteDialogComponent } from './loan-delete-dialog.component';
import { loanRoute } from './loan.route';

@NgModule({
    imports: [LibrarySharedModule, RouterModule.forChild(loanRoute)],
    declarations: [
        LoanComponent,
        LoanDetailComponent,
        LoanUpdateComponent,
        LoanReturnComponent,
        LoanRenewComponent,
        LoanUserComponent,
        LoanDeleteDialogComponent
    ],
    entryComponents: [LoanDeleteDialogComponent]
})
export class LibraryLoanModule {}
