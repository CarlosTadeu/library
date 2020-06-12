export interface ICopyBook {
    id?: number;
    available?: boolean;
    bookId?: number;
}

export class CopyBook implements ICopyBook {
    constructor(public id?: number, public available?: boolean, public bookId?: number) {
        this.available = this.available || false;
    }
}
