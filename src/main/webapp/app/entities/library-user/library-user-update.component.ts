import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ILibraryUser, LibraryUser } from 'app/shared/model/library-user.model';
import { LibraryUserService } from './library-user.service';
import { IStudentType } from 'app/shared/model/student-type.model';
import { StudentTypeService } from 'app/entities/student-type/student-type.service';

@Component({
  selector: 'jhi-library-user-update',
  templateUrl: './library-user-update.component.html'
})
export class LibraryUserUpdateComponent implements OnInit {
  isSaving = false;
  studenttypes: IStudentType[] = [];
  suspensionDateDp: any;

  editForm = this.fb.group({
    id: [],
    cpf: [],
    rg: [],
    name: [],
    username: [],
    address: [],
    email: [],
    phoneNumber: [],
    suspensionDate: [],
    studentTypeId: []
  });

  constructor(
    protected libraryUserService: LibraryUserService,
    protected studentTypeService: StudentTypeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ libraryUser }) => {
      this.updateForm(libraryUser);

      this.studentTypeService.query().subscribe((res: HttpResponse<IStudentType[]>) => (this.studenttypes = res.body || []));
    });
  }

  updateForm(libraryUser: ILibraryUser): void {
    this.editForm.patchValue({
      id: libraryUser.id,
      cpf: libraryUser.cpf,
      rg: libraryUser.rg,
      name: libraryUser.name,
      username: libraryUser.username,
      address: libraryUser.address,
      email: libraryUser.email,
      phoneNumber: libraryUser.phoneNumber,
      suspensionDate: libraryUser.suspensionDate,
      studentTypeId: libraryUser.studentTypeId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const libraryUser = this.createFromForm();
    if (libraryUser.id !== undefined) {
      this.subscribeToSaveResponse(this.libraryUserService.update(libraryUser));
    } else {
      this.subscribeToSaveResponse(this.libraryUserService.create(libraryUser));
    }
  }

  private createFromForm(): ILibraryUser {
    return {
      ...new LibraryUser(),
      id: this.editForm.get(['id'])!.value,
      cpf: this.editForm.get(['cpf'])!.value,
      rg: this.editForm.get(['rg'])!.value,
      name: this.editForm.get(['name'])!.value,
      username: this.editForm.get(['username'])!.value,
      address: this.editForm.get(['address'])!.value,
      email: this.editForm.get(['email'])!.value,
      phoneNumber: this.editForm.get(['phoneNumber'])!.value,
      suspensionDate: this.editForm.get(['suspensionDate'])!.value,
      studentTypeId: this.editForm.get(['studentTypeId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILibraryUser>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IStudentType): any {
    return item.id;
  }
}
