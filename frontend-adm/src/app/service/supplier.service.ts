import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { environment } from '../../environments/environment';
import { map, catchError } from "rxjs/operators";
import { InnerSubscriber } from 'rxjs/internal/InnerSubscriber';

@Injectable({
    providedIn: 'root'
})
export class SupplierService {

    constructor(private httpClient: HttpClient) {
    }

    getSuppliers(params: any): Observable<any> {
        return this.httpClient.get(environment.url + 'api/supplier', { params: params })
            .pipe(
                map((data: any) => {
                    data.content =  data.content.map(element => {
                        if(element.supplierParentId && element.supplierParentId>0){
                            this.getSupplier(element.supplierParentId).subscribe(data2=>{
                                element.parent = data2;
                                console.log(element);
                            })
                        }
                        return element;
                    });
                    return data;
                })
            )
            .pipe(
                catchError((error: HttpErrorResponse) => {
                    return new Observable((observer: InnerSubscriber<any, any>) => {
                        observer.next(null);
                    });
                })
            );
    }

    getListSupplierParent(id: any): Observable<any> {
        return this.httpClient.get(environment.url + 'api/supplier/except/' + id)
            .pipe(
                catchError((error: HttpErrorResponse) => {
                    return new Observable((observer: InnerSubscriber<any, any>) => {
                        observer.next(null);
                    });
                })
            );
    }

    getSupplier(id: any): Observable<any> {
        return this.httpClient.get(environment.url + 'api/supplier/' + id)
            .pipe(
                catchError((error: HttpErrorResponse) => {
                    return new Observable((observer: InnerSubscriber<any, any>) => {
                        observer.next(null);
                    });
                })
            );
    }

    createSuppliers(params: any): Observable<any> {
        return this.httpClient.post(environment.url + 'api/supplier', params)
            .pipe(
                catchError((error: HttpErrorResponse) => {
                    return new Observable((observer: InnerSubscriber<any, any>) => {
                        observer.error(error);
                    });
                })
            );
    }

    updateSuppliers(id: any, params: any): Observable<any> {
        return this.httpClient.put(environment.url + 'api/supplier/' + id, params)
            .pipe(
                catchError((error: HttpErrorResponse) => {
                    return new Observable((observer: InnerSubscriber<any, any>) => {
                        observer.error(error);
                    });
                })
            );
    }

    deleteSuppliers(id: any): Observable<any> {
        return this.httpClient.delete(environment.url + 'api/supplier/' + id)
            .pipe(
                catchError((error: HttpErrorResponse) => {
                    return new Observable((observer: InnerSubscriber<any, any>) => {
                        observer.error(error);
                    });
                })
            );
    }
}
