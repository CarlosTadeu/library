import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IStudentType } from 'app/shared/model/student-type.model';

@Component({
  selector: 'jhi-student-type-detail',
  templateUrl: './student-type-detail.component.html'
})
export class StudentTypeDetailComponent implements OnInit {
  studentType: IStudentType | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ studentType }) => (this.studentType = studentType));
  }

  previousState(): void {
    window.history.back();
  }
}
