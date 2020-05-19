import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LibrarySharedModule } from 'app/shared/shared.module';
import { LoanComponent } from './loan.component';
import { LoanDetailComponent } from './loan-detail.component';
import { LoanUpdateComponent } from './loan-update.component';
import { LoanDeleteDialogComponent } from './loan-delete-dialog.component';
import { loanRoute } from './loan.route';

@NgModule({
  imports: [LibrarySharedModule, RouterModule.forChild(loanRoute)],
  declarations: [LoanComponent, LoanDetailComponent, LoanUpdateComponent, LoanDeleteDialogComponent],
  entryComponents: [LoanDeleteDialogComponent]
})
export class LibraryLoanModule {}
