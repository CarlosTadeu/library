import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LibrarySharedModule } from 'app/shared/shared.module';
import { AuthorComponent } from './author.component';
import { AuthorDetailComponent } from './author-detail.component';
import { AuthorUpdateComponent } from './author-update.component';
import { AuthorDeleteDialogComponent } from './author-delete-dialog.component';
import { authorRoute } from './author.route';

@NgModule({
    imports: [LibrarySharedModule, RouterModule.forChild(authorRoute)],
    declarations: [AuthorComponent, AuthorDetailComponent, AuthorUpdateComponent, AuthorDeleteDialogComponent],
    entryComponents: [AuthorDeleteDialogComponent]
})
export class LibraryAuthorModule {}
