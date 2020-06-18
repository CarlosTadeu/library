import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { SearchComponent } from 'app/search/search.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@NgModule({
    imports: [
        RouterModule.forRoot([
            {
                path: 'search',
                component: SearchComponent,
                data: {
                    authorities: [Authority.USER]
                },
                canActivate: [UserRouteAccessService]
            }
        ]),
        FontAwesomeModule,
        ReactiveFormsModule,
        FormsModule,
        CommonModule
    ],
    declarations: [SearchComponent]
})
export class LibrarySearchModule {}
