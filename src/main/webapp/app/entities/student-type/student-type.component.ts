import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IStudentType } from 'app/shared/model/student-type.model';
import { StudentTypeService } from './student-type.service';
import { StudentTypeDeleteDialogComponent } from './student-type-delete-dialog.component';

@Component({
    selector: 'jhi-student-type',
    templateUrl: './student-type.component.html'
})
export class StudentTypeComponent implements OnInit, OnDestroy {
    studentTypes?: IStudentType[];
    eventSubscriber?: Subscription;

    constructor(
        protected studentTypeService: StudentTypeService,
        protected eventManager: JhiEventManager,
        protected modalService: NgbModal
    ) {}

    loadAll(): void {
        this.studentTypeService.query().subscribe((res: HttpResponse<IStudentType[]>) => (this.studentTypes = res.body || []));
    }

    ngOnInit(): void {
        this.loadAll();
        this.registerChangeInStudentTypes();
    }

    ngOnDestroy(): void {
        if (this.eventSubscriber) {
            this.eventManager.destroy(this.eventSubscriber);
        }
    }

    trackId(index: number, item: IStudentType): number {
        // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
        return item.id!;
    }

    registerChangeInStudentTypes(): void {
        this.eventSubscriber = this.eventManager.subscribe('studentTypeListModification', () => this.loadAll());
    }

    delete(studentType: IStudentType): void {
        const modalRef = this.modalService.open(StudentTypeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
        modalRef.componentInstance.studentType = studentType;
    }
}
