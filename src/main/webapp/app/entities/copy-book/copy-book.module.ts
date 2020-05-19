import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LibrarySharedModule } from 'app/shared/shared.module';
import { CopyBookComponent } from './copy-book.component';
import { CopyBookDetailComponent } from './copy-book-detail.component';
import { CopyBookUpdateComponent } from './copy-book-update.component';
import { CopyBookDeleteDialogComponent } from './copy-book-delete-dialog.component';
import { copyBookRoute } from './copy-book.route';

@NgModule({
  imports: [LibrarySharedModule, RouterModule.forChild(copyBookRoute)],
  declarations: [CopyBookComponent, CopyBookDetailComponent, CopyBookUpdateComponent, CopyBookDeleteDialogComponent],
  entryComponents: [CopyBookDeleteDialogComponent]
})
export class LibraryCopyBookModule {}
