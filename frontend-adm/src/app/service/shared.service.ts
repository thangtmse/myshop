import { Injectable, Output, EventEmitter } from '@angular/core';
@Injectable()
export class SharedService {
    constructor() { }
    public static userLogin = new EventEmitter<Object>();
    public static newProductPreCook = new EventEmitter<Object>();
    public static newProductReady = new EventEmitter<Object>();
    public static productStatusChange = new EventEmitter<Object>();
}