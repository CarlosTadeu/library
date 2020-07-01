import { Component } from '@angular/core';
import { NgbCarouselConfig } from '@ng-bootstrap/ng-bootstrap';
@Component({
    selector: 'jhi-carousel',
    templateUrl: './carousel.component.html'
})
export class CarouselComponent {
    constructor(config: NgbCarouselConfig) {
        config.interval = 5000;
        config.pauseOnHover = false;
        config.showNavigationArrows = false;
        config.showNavigationIndicators = true;
    }
}
