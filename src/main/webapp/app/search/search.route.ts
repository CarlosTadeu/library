import { Route } from '@angular/router';

import { SearchComponent } from './search.component';
import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';

export const searchRoute: Route = {
    path: '',
    component: SearchComponent,
    data: {
        authorities: [Authority.USER, Authority.LIBRARIAN, Authority.ADMIN],
        pageTitle: 'Search'
    },
    canActivate: [UserRouteAccessService]
};
