import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { environment } from '../../environments/environment';
import { map, catchError } from "rxjs/operators";
import { InnerSubscriber } from 'rxjs/internal/InnerSubscriber';

@Injectable({
    providedIn: 'root'
})
export class CategoryService {

    constructor(private httpClient: HttpClient) {
    }

    getCategorys(params: any): Observable<any> {
        return this.httpClient.get(environment.url + 'api/category', { params: params })
            .pipe(
                map((data: any) => {
                    data.content =  data.content.map(element => {
                        if(element.categoryParentId && element.categoryParentId>0){
                            this.getCategory(element.categoryParentId).subscribe(data2=>{
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

    getListCategoryParent(id: any): Observable<any> {
        return this.httpClient.get(environment.url + 'api/category/except/' + id)
            .pipe(
                catchError((error: HttpErrorResponse) => {
                    return new Observable((observer: InnerSubscriber<any, any>) => {
                        observer.next(null);
                    });
                })
            );
    }

    getCategory(id: any): Observable<any> {
        return this.httpClient.get(environment.url + 'api/category/' + id)
            .pipe(
                catchError((error: HttpErrorResponse) => {
                    return new Observable((observer: InnerSubscriber<any, any>) => {
                        observer.next(null);
                    });
                })
            );
    }

    createCategorys(params: any): Observable<any> {
        return this.httpClient.post(environment.url + 'api/category', params)
            .pipe(
                catchError((error: HttpErrorResponse) => {
                    return new Observable((observer: InnerSubscriber<any, any>) => {
                        observer.error(error);
                    });
                })
            );
    }

    updateCategorys(id: any, params: any): Observable<any> {
        return this.httpClient.put(environment.url + 'api/category/' + id, params)
            .pipe(
                catchError((error: HttpErrorResponse) => {
                    return new Observable((observer: InnerSubscriber<any, any>) => {
                        observer.error(error);
                    });
                })
            );
    }

    deleteCategorys(id: any): Observable<any> {
        return this.httpClient.delete(environment.url + 'api/category/' + id)
            .pipe(
                catchError((error: HttpErrorResponse) => {
                    return new Observable((observer: InnerSubscriber<any, any>) => {
                        observer.error(error);
                    });
                })
            );
    }
}
