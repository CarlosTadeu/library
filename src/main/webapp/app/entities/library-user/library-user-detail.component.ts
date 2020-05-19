import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILibraryUser } from 'app/shared/model/library-user.model';

@Component({
  selector: 'jhi-library-user-detail',
  templateUrl: './library-user-detail.component.html'
})
export class LibraryUserDetailComponent implements OnInit {
  libraryUser: ILibraryUser | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ libraryUser }) => (this.libraryUser = libraryUser));
  }

  previousState(): void {
    window.history.back();
  }
}
