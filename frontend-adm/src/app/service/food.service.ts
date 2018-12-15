import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { environment } from '../../environments/environment';
import { map, catchError } from "rxjs/operators";
import { InnerSubscriber } from 'rxjs/internal/InnerSubscriber';

@Injectable({
  providedIn: 'root'
})
export class FoodService {

  constructor(private httpClient: HttpClient) {
    }
    
    
     

    getAllCategory(): Observable<any> {
        return this.httpClient.get(environment.url + 'api/category/all')
            .pipe(
                catchError((error:HttpErrorResponse) => {
                    return new Observable((observer:InnerSubscriber<any, any> )=>{
                        observer.error(error)
                    });
                })
            );
    }
    getAllSupplier(): Observable<any> {
        return this.httpClient.get(environment.url + "api/supplier/all")
            .pipe(
                catchError((error:HttpErrorResponse) => {
                    return new Observable((observer:InnerSubscriber<any, any> )=>{
                        observer.error(error)
                    });
                })
            );
    }

    findFood(params: any): Observable<any> {
        return this.httpClient.get(environment.url + 'api/product', { params: params })
            .pipe(
                catchError((error:HttpErrorResponse) => {
                    return new Observable((observer:InnerSubscriber<any, any> )=>{
                        observer.error(error)
                    });
                })
            );
    }

    getFoodbyPage(page:number, categoryId:number, size:number): Observable<any> {
        let query = environment.url + "api/foods?"  
        query = query + "&page=" + page;
        query = query + "&category="+categoryId;
        query = query + "&size=" + size;
        return this.httpClient.get(query)
            .pipe(
                catchError((error:HttpErrorResponse) => {
                    return new Observable((observer:InnerSubscriber<any, any> )=>{
                        observer.error(error)
                    });
                })
            );
    }

    getOneFood(productId: number): Observable<any> {    
        return this.httpClient.get(environment.url + 'api/product/' + productId)
            .pipe(
                catchError((error:HttpErrorResponse) => {
                    return new Observable((observer:InnerSubscriber<any, any> )=>{
                        observer.error(error)
                    });
                })
            );
    }

    createNewFood(food: any): Observable<any> {
    	return this.httpClient.post(environment.url + 'api/product/acept', food)
    		.pipe(
                catchError((error:HttpErrorResponse) => {
                    return new Observable((observer:InnerSubscriber<any, any> )=>{
                        observer.error(error)
                    });
                })
            );	
    }

    updateFood(id: any, params: any): Observable<any> {
    	return this.httpClient.put(environment.url + 'api/product/'+id,params )
    		.pipe(
                catchError((error:HttpErrorResponse) => {
                    return new Observable((observer:InnerSubscriber<any, any> )=>{
                        observer.error(error)
                    });
                })
            );	
    }

    deleteFood(productId: any): Observable<any> {   
        return this.httpClient.delete(environment.url + 'api/product/' + productId)
            .pipe(
                catchError((error:HttpErrorResponse) => {
                    return new Observable((observer:InnerSubscriber<any, any> )=>{
                        observer.error(error)
                    });
                })
            );
    }
}
