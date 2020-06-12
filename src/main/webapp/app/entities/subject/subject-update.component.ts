import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ISubject, Subject } from 'app/shared/model/subject.model';
import { SubjectService } from './subject.service';

@Component({
    selector: 'jhi-subject-update',
    templateUrl: './subject-update.component.html'
})
export class SubjectUpdateComponent implements OnInit {
    isSaving = false;

    editForm = this.fb.group({
        id: [],
        subject: []
    });

    constructor(protected subjectService: SubjectService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

    ngOnInit(): void {
        this.activatedRoute.data.subscribe(({ subject }) => {
            this.updateForm(subject);
        });
    }

    updateForm(subject: ISubject): void {
        this.editForm.patchValue({
            id: subject.id,
            subject: subject.subject
        });
    }

    previousState(): void {
        window.history.back();
    }

    save(): void {
        this.isSaving = true;
        const subject = this.createFromForm();
        if (subject.id !== undefined) {
            this.subscribeToSaveResponse(this.subjectService.update(subject));
        } else {
            this.subscribeToSaveResponse(this.subjectService.create(subject));
        }
    }

    private createFromForm(): ISubject {
        return {
            ...new Subject(),
            id: this.editForm.get(['id'])!.value,
            subject: this.editForm.get(['subject'])!.value
        };
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISubject>>): void {
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
