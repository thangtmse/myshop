import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { environment } from '../../environments/environment';
import { map, catchError } from "rxjs/operators";
import { InnerSubscriber } from 'rxjs/internal/InnerSubscriber';

@Injectable({
    providedIn: 'root'
})
export class OrderService {
    getStaticLine(type: number): any {
        let query = environment.url + "api/order/staticsLine?"
        query = query + "type=" + type;
        return this.httpClient.get(query)
            .pipe(
                catchError((error: HttpErrorResponse) => {
                    return new Observable((observer: InnerSubscriber<any, any>) => {
                        observer.error(error)
                    });
                })
            );
    }

    constructor(private httpClient: HttpClient) {
    }

    getOrders(params: any): Observable<any> {
        return this.httpClient.get(environment.url + 'api/order', { params: params })
            .pipe(
                map((res: any) => {
                    res.content.map(data => {
                        data.createDate = new Date(data.addAt);
                        return data;
                    });
                    return res;
                }),
                catchError((error: HttpErrorResponse) => {
                    return new Observable((observer: InnerSubscriber<any, any>) => {
                        observer.error(error)
                    });
                })
            );
    }

    getOrdersByPhone(phoneNumber: string, params: any): Observable<any> {
        return this.httpClient.get(environment.url + 'api/order?search=' + phoneNumber + '&status=-1', { params: params })
            .pipe(
                catchError((error: HttpErrorResponse) => {
                    return new Observable((observer: InnerSubscriber<any, any>) => {
                        observer.error(error)
                    });
                })
            );
    }

    getListOrderParent(id: any): Observable<any> {
        return this.httpClient.get(environment.url + 'api/order/except/' + id)
            .pipe(
                catchError((error: HttpErrorResponse) => {
                    return new Observable((observer: InnerSubscriber<any, any>) => {
                        observer.error(error)
                    });
                })
            );
    }

    getOrderInfo(id: any): Observable<any> {
        return this.httpClient.get(environment.url + 'api/order/' + id)
            .pipe(
                catchError((error: HttpErrorResponse) => {
                    return new Observable((observer: InnerSubscriber<any, any>) => {
                        observer.error(error)
                    });
                })
            );
    }

    getOrder(id: any): Observable<any> {
        return this.httpClient.get(environment.url + 'api/order/' + id + "/detail")
            .pipe(
                map((data: any) => {
                    data.createDate = new Date(data.addAt);
                    return data;
                }),
                catchError((error: HttpErrorResponse) => {
                    return new Observable((observer: InnerSubscriber<any, any>) => {
                        observer.error(error)
                    });
                })
            );
    }

    getOrderDetail(id: any): Observable<any> {
        return this.httpClient.get(environment.url + 'api/orderdetail/' + id)
            .pipe(
                catchError((error: HttpErrorResponse) => {
                    return new Observable((observer: InnerSubscriber<any, any>) => {
                        observer.error(error)
                    });
                })
            );
    }

    createOrders(params: any): Observable<any> {
        return this.httpClient.post(environment.url + 'api/order', params)
            .pipe(
                catchError((error: HttpErrorResponse) => {
                    return new Observable((observer: InnerSubscriber<any, any>) => {
                        observer.error(error)
                    });
                })
            );
    }

    updateOrders(id: any, params: any): Observable<any> {
        return this.httpClient.put(environment.url + 'api/order/' + id, params)
            .pipe(
                catchError((error: HttpErrorResponse) => {
                    return new Observable((observer: InnerSubscriber<any, any>) => {
                        observer.error(error);
                    });
                })
            );
    }

    updateOrderByOrderDetail(id: any, params: any): Observable<any> {
        return this.httpClient.put(environment.url + 'api/order/update/' + id, params)
            .pipe(
                catchError((error: HttpErrorResponse) => {
                    return new Observable((observer: InnerSubscriber<any, any>) => {
                        observer.error(error);
                    });
                })
            );
    }

    deleteOrders(id: any): Observable<any> {
        return this.httpClient.delete(environment.url + 'api/order/' + id)
            .pipe(
                catchError((error: HttpErrorResponse) => {
                    return new Observable((observer: InnerSubscriber<any, any>) => {
                        observer.error(error);
                    });
                })
            );
    }

    checkout(id: any): Observable<any> {
        return this.httpClient.get(environment.url + 'api/order/' + id + '/checkout')
            .pipe(
                catchError((error: HttpErrorResponse) => {
                    return new Observable((observer: InnerSubscriber<any, any>) => {
                        observer.error(error);
                    });
                })
            );
    }

    changeStatus(id: number, status: string): Observable<any> {
        let query = environment.url + 'api/order/' + id + '/status/?status=' + status;
        return this.httpClient.post(query, "")
            .pipe(
                catchError((error: HttpErrorResponse) => {
                    return new Observable((observer: InnerSubscriber<any, any>) => {
                        observer.error(error);
                    });
                })
            );
    }

    changeDetailStatus(id: number, status: number, orderID: number, ): Observable<any> {
        let query = environment.url + 'api/order/detail/' + id + '?status=' + status + '&order_id=' + orderID;
        return this.httpClient.post(query, "")
            .pipe(
                catchError((error: HttpErrorResponse) => {
                    return new Observable((observer: InnerSubscriber<any, any>) => {
                        observer.error(error);
                    });
                })
            );
    }

    getRecommend(products: String, size: number): Observable<any> {
        products = products.substr(3, products.length);
        let query = environment.url + 'api/recommend/?product=' + products + '&size=' + size;
        return this.httpClient.get(query)
            .pipe(
                catchError((error: HttpErrorResponse) => {
                    return new Observable((observer: InnerSubscriber<any, any>) => {
                        observer.error(error);
                    });
                })
            );
    }
}
