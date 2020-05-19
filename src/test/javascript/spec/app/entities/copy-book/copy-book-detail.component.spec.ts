import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { LibraryTestModule } from '../../../test.module';
import { CopyBookDetailComponent } from 'app/entities/copy-book/copy-book-detail.component';
import { CopyBook } from 'app/shared/model/copy-book.model';

describe('Component Tests', () => {
  describe('CopyBook Management Detail Component', () => {
    let comp: CopyBookDetailComponent;
    let fixture: ComponentFixture<CopyBookDetailComponent>;
    const route = ({ data: of({ copyBook: new CopyBook(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LibraryTestModule],
        declarations: [CopyBookDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CopyBookDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CopyBookDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load copyBook on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.copyBook).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
