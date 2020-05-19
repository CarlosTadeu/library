import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IStudentType } from 'app/shared/model/student-type.model';
import { StudentTypeService } from './student-type.service';

@Component({
  templateUrl: './student-type-delete-dialog.component.html'
})
export class StudentTypeDeleteDialogComponent {
  studentType?: IStudentType;

  constructor(
    protected studentTypeService: StudentTypeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.studentTypeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('studentTypeListModification');
      this.activeModal.close();
    });
  }
}
