import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { LibraryTestModule } from '../../../test.module';
import { StudentTypeComponent } from 'app/entities/student-type/student-type.component';
import { StudentTypeService } from 'app/entities/student-type/student-type.service';
import { StudentType } from 'app/shared/model/student-type.model';

describe('Component Tests', () => {
    describe('StudentType Management Component', () => {
        let comp: StudentTypeComponent;
        let fixture: ComponentFixture<StudentTypeComponent>;
        let service: StudentTypeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [LibraryTestModule],
                declarations: [StudentTypeComponent]
            })
                .overrideTemplate(StudentTypeComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(StudentTypeComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StudentTypeService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new StudentType(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.studentTypes && comp.studentTypes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
