import { Injectable } from '@angular/core';

export class Settings {
    constructor(public name: string,
                public theme: string) { }
}

@Injectable()
export class AppSettings {
    public settings = new Settings(
        'Trâm Anh',  // theme name
        'green'     // green, blue, red, pink, purple, grey
    )
}