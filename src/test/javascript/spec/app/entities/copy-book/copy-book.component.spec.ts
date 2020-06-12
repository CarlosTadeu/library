import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { LibraryTestModule } from '../../../test.module';
import { CopyBookComponent } from 'app/entities/copy-book/copy-book.component';
import { CopyBookService } from 'app/entities/copy-book/copy-book.service';
import { CopyBook } from 'app/shared/model/copy-book.model';

describe('Component Tests', () => {
    describe('CopyBook Management Component', () => {
        let comp: CopyBookComponent;
        let fixture: ComponentFixture<CopyBookComponent>;
        let service: CopyBookService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [LibraryTestModule],
                declarations: [CopyBookComponent]
            })
                .overrideTemplate(CopyBookComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CopyBookComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CopyBookService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new CopyBook(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.copyBooks && comp.copyBooks[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
