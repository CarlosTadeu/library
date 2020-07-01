export interface IFilter {
    option?: string;
    value?: number;
}

export class Filter implements IFilter {
    constructor(public option?: string, public value?: number) {}
}
