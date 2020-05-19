import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { LibraryTestModule } from '../../../test.module';
import { StudentTypeDetailComponent } from 'app/entities/student-type/student-type-detail.component';
import { StudentType } from 'app/shared/model/student-type.model';

describe('Component Tests', () => {
  describe('StudentType Management Detail Component', () => {
    let comp: StudentTypeDetailComponent;
    let fixture: ComponentFixture<StudentTypeDetailComponent>;
    const route = ({ data: of({ studentType: new StudentType(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LibraryTestModule],
        declarations: [StudentTypeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(StudentTypeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(StudentTypeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load studentType on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.studentType).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
