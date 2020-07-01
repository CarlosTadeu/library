export interface ISearch {
    option?: string;
    value?: string;
}

export class Search implements ISearch {
    constructor(public option?: string, public value?: string) {}
}
