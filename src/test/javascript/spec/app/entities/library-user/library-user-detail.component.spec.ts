import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { LibraryTestModule } from '../../../test.module';
import { LibraryUserDetailComponent } from 'app/entities/library-user/library-user-detail.component';
import { LibraryUser } from 'app/shared/model/library-user.model';

describe('Component Tests', () => {
  describe('LibraryUser Management Detail Component', () => {
    let comp: LibraryUserDetailComponent;
    let fixture: ComponentFixture<LibraryUserDetailComponent>;
    const route = ({ data: of({ libraryUser: new LibraryUser(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [LibraryTestModule],
        declarations: [LibraryUserDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(LibraryUserDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(LibraryUserDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load libraryUser on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.libraryUser).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
