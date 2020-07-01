import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';

import { IStudentType } from 'app/shared/model/student-type.model';
import { StudentTypeService } from './student-type.service';

@Component({
    selector: 'jhi-student-type-user',
    templateUrl: './student-type-user.component.html'
})
export class StudentTypeUserComponent implements OnInit {
    studentTypes?: IStudentType[];

    constructor(protected studentTypeService: StudentTypeService) {}

    loadAll(): void {
        this.studentTypeService.query().subscribe((res: HttpResponse<IStudentType[]>) => (this.studentTypes = res.body || []));
    }

    ngOnInit(): void {
        this.loadAll();
    }

    returnStudentType(item: IStudentType): string {
        switch (item.studentType) {
            case 'GRADUATE_STUDENT': {
                return 'Graduate Student';
                break;
            }
            case 'TEACHER': {
                return 'Teacher';
                break;
            }
            case 'POSTGRADUATE_STUDENT': {
                return 'Postgraduate Student';
                break;
            }
            case 'DISTANCE_STUDENT': {
                return 'Distance Student';
                break;
            }
            default: {
                return 'default';
                break;
            }
        }
    }

    trackId(index: number, item: IStudentType): number {
        return item.id!;
    }
}
