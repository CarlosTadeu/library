import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LibrarySharedModule } from 'app/shared/shared.module';
import { LibraryUserComponent } from './library-user.component';
import { LibraryUserDetailComponent } from './library-user-detail.component';
import { LibraryUserUpdateComponent } from './library-user-update.component';
import { LibraryUserDeleteDialogComponent } from './library-user-delete-dialog.component';
import { libraryUserRoute } from './library-user.route';

@NgModule({
  imports: [LibrarySharedModule, RouterModule.forChild(libraryUserRoute)],
  declarations: [LibraryUserComponent, LibraryUserDetailComponent, LibraryUserUpdateComponent, LibraryUserDeleteDialogComponent],
  entryComponents: [LibraryUserDeleteDialogComponent]
})
export class LibraryLibraryUserModule {}
