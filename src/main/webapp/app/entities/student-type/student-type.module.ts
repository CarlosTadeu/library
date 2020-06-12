import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LibrarySharedModule } from 'app/shared/shared.module';
import { StudentTypeComponent } from './student-type.component';
import { StudentTypeDetailComponent } from './student-type-detail.component';
import { StudentTypeUpdateComponent } from './student-type-update.component';
import { StudentTypeUserComponent } from './student-type-user.component';
import { StudentTypeDeleteDialogComponent } from './student-type-delete-dialog.component';
import { studentTypeRoute } from './student-type.route';

@NgModule({
    imports: [LibrarySharedModule, RouterModule.forChild(studentTypeRoute)],
    declarations: [
        StudentTypeComponent,
        StudentTypeDetailComponent,
        StudentTypeUpdateComponent,
        StudentTypeUserComponent,
        StudentTypeDeleteDialogComponent
    ],
    entryComponents: [StudentTypeDeleteDialogComponent]
})
export class LibraryStudentTypeModule {}
