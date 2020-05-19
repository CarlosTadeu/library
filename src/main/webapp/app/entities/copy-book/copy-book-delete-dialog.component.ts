import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICopyBook } from 'app/shared/model/copy-book.model';
import { CopyBookService } from './copy-book.service';

@Component({
  templateUrl: './copy-book-delete-dialog.component.html'
})
export class CopyBookDeleteDialogComponent {
  copyBook?: ICopyBook;

  constructor(protected copyBookService: CopyBookService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.copyBookService.delete(id).subscribe(() => {
      this.eventManager.broadcast('copyBookListModification');
      this.activeModal.close();
    });
  }
}
