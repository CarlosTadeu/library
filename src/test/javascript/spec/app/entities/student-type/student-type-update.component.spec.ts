import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { LibraryTestModule } from '../../../test.module';
import { StudentTypeUpdateComponent } from 'app/entities/student-type/student-type-update.component';
import { StudentTypeService } from 'app/entities/student-type/student-type.service';
import { StudentType } from 'app/shared/model/student-type.model';

describe('Component Tests', () => {
  describe('StudentType Management Update Component', () => {
    let comp: StudentTypeUpdateComponent;
    let fixture: ComponentFixture<StudentTypeUpdateComponent>;
    let service: StudentTypeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LibraryTestModule],
        declarations: [StudentTypeUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(StudentTypeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(StudentTypeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(StudentTypeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new StudentType(123);
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
        const entity = new StudentType();
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
