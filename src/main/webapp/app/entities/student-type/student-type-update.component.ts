import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IStudentType, StudentType } from 'app/shared/model/student-type.model';
import { StudentTypeService } from './student-type.service';

@Component({
    selector: 'jhi-student-type-update',
    templateUrl: './student-type-update.component.html'
})
export class StudentTypeUpdateComponent implements OnInit {
    isSaving = false;

    editForm = this.fb.group({
        id: [],
        studentType: [],
        numberOfDaysLoan: [],
        numberOfDaysRenewal: [],
        maxBooksOnLoan: [],
        maxRenewalNumber: []
    });

    constructor(protected studentTypeService: StudentTypeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

    ngOnInit(): void {
        this.activatedRoute.data.subscribe(({ studentType }) => {
            this.updateForm(studentType);
        });
    }

    updateForm(studentType: IStudentType): void {
        this.editForm.patchValue({
            id: studentType.id,
            studentType: studentType.studentType,
            numberOfDaysLoan: studentType.numberOfDaysLoan,
            numberOfDaysRenewal: studentType.numberOfDaysRenewal,
            maxBooksOnLoan: studentType.maxBooksOnLoan,
            maxRenewalNumber: studentType.maxRenewalNumber
        });
    }

    previousState(): void {
        window.history.back();
    }

    save(): void {
        this.isSaving = true;
        const studentType = this.createFromForm();
        if (studentType.id !== undefined) {
            this.subscribeToSaveResponse(this.studentTypeService.update(studentType));
        } else {
            this.subscribeToSaveResponse(this.studentTypeService.create(studentType));
        }
    }

    private createFromForm(): IStudentType {
        return {
            ...new StudentType(),
            id: this.editForm.get(['id'])!.value,
            studentType: this.editForm.get(['studentType'])!.value,
            numberOfDaysLoan: this.editForm.get(['numberOfDaysLoan'])!.value,
            numberOfDaysRenewal: this.editForm.get(['numberOfDaysRenewal'])!.value,
            maxBooksOnLoan: this.editForm.get(['maxBooksOnLoan'])!.value,
            maxRenewalNumber: this.editForm.get(['maxRenewalNumber'])!.value
        };
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IStudentType>>): void {
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
}
