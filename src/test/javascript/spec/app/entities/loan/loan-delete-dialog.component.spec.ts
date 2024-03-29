import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { LibraryTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { LoanDeleteDialogComponent } from 'app/entities/loan/loan-delete-dialog.component';
import { LoanService } from 'app/entities/loan/loan.service';

describe('Component Tests', () => {
    describe('Loan Management Delete Component', () => {
        let comp: LoanDeleteDialogComponent;
        let fixture: ComponentFixture<LoanDeleteDialogComponent>;
        let service: LoanService;
        let mockEventManager: MockEventManager;
        let mockActiveModal: MockActiveModal;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [LibraryTestModule],
                declarations: [LoanDeleteDialogComponent]
            })
                .overrideTemplate(LoanDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(LoanDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LoanService);
            mockEventManager = TestBed.get(JhiEventManager);
            mockActiveModal = TestBed.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.closeSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));

            it('Should not call delete service on clear', () => {
                // GIVEN
                spyOn(service, 'delete');

                // WHEN
                comp.cancel();

                // THEN
                expect(service.delete).not.toHaveBeenCalled();
                expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
            });
        });
    });
});
