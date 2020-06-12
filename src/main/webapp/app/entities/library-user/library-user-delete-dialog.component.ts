import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILibraryUser } from 'app/shared/model/library-user.model';
import { LibraryUserService } from './library-user.service';

@Component({
    templateUrl: './library-user-delete-dialog.component.html'
})
export class LibraryUserDeleteDialogComponent {
    libraryUser?: ILibraryUser;

    constructor(
        protected libraryUserService: LibraryUserService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    cancel(): void {
        this.activeModal.dismiss();
    }

    confirmDelete(id: number): void {
        this.libraryUserService.delete(id).subscribe(() => {
            this.eventManager.broadcast('libraryUserListModification');
            this.activeModal.close();
        });
    }
}
