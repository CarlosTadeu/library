import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { LibraryTestModule } from '../../../test.module';
import { LoanUpdateComponent } from 'app/entities/loan/loan-update.component';
import { LoanService } from 'app/entities/loan/loan.service';
import { Loan } from 'app/shared/model/loan.model';

describe('Component Tests', () => {
    describe('Loan Management Update Component', () => {
        let comp: LoanUpdateComponent;
        let fixture: ComponentFixture<LoanUpdateComponent>;
        let service: LoanService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [LibraryTestModule],
                declarations: [LoanUpdateComponent],
                providers: [FormBuilder]
            })
                .overrideTemplate(LoanUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(LoanUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LoanService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Loan(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.updateForm(entity);
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Loan();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.updateForm(entity);
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
