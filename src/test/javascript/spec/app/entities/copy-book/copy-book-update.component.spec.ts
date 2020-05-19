import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { LibraryTestModule } from '../../../test.module';
import { CopyBookUpdateComponent } from 'app/entities/copy-book/copy-book-update.component';
import { CopyBookService } from 'app/entities/copy-book/copy-book.service';
import { CopyBook } from 'app/shared/model/copy-book.model';

describe('Component Tests', () => {
  describe('CopyBook Management Update Component', () => {
    let comp: CopyBookUpdateComponent;
    let fixture: ComponentFixture<CopyBookUpdateComponent>;
    let service: CopyBookService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LibraryTestModule],
        declarations: [CopyBookUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CopyBookUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CopyBookUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CopyBookService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CopyBook(123);
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
        const entity = new CopyBook();
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
