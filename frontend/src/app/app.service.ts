import { Injectable, EventEmitter } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { MatSnackBar } from '@angular/material';
import { Category, Product, User } from './app.models';
import { map, catchError } from 'rxjs/operators';
import { environment } from '../environments/environment';
import { InnerSubscriber } from 'rxjs/internal/InnerSubscriber';
import { prepareProfile } from 'selenium-webdriver/firefox';

export class Data {
    constructor(public categories: Category[],
        public user: User[],
        public compareList: Product[],
        public wishList: Product[],
        public cartList: Product[],
        public totalPrice: number) { }
}

@Injectable()
export class AppService {
    public static search = new EventEmitter<any>();
    public Data = new Data(
        [], // categories
        [], // user
        [], // compareList
        [],  // wishList
        [],  // cartList
        null //totalPrice
    )
    public url = "assets/data/";
    public API_URL = environment.API_URL;
    constructor(public http: HttpClient, public snackBar: MatSnackBar,
        private httpClient: HttpClient) { }

    public getOrdersByUser(userId: number): Observable<any> {
        return this.http.get<any[]>(this.API_URL + '/order/user/' + userId).pipe(map((response: any) => {

            let data = response.map(ele => {
                return {
                    number: ele.orderId,
                    date: new Date(ele.addAt),
                    status: ele.status,
                    total: ele.totalPrice.toLocaleString('vi', {style : 'currency', currency : 'VND'}) + '  cho ' + ele.amount + ' sản phẩm',
                    invoice: true
                }
            })
            return data;
        }));
    }

    public getCategories(): Observable<Category[]> {
        return this.http.get<any[]>(this.API_URL + '/category').pipe(map((cate: any) => {
            let parentIds = cate.content.filter(cate => cate.categoryParentId != null).map(cate => cate.categoryParentId)
            let data = cate.content.map(ele => {
                return {
                    id: ele.categoryId,
                    name: ele.categoryName,
                    hasSubCategory: parentIds.includes(ele.categoryId),
                    parentId: ele.categoryParentId || 0
                }
            })
            return data;
        }));
    }



    public login(data: any): Observable<any> {
        return this.http.post<any[]>(this.API_URL + '/user/authenticate', data).pipe(map((data: any) => {
            console.log('end');
            if (data.token) {
                localStorage.setItem('authToken', data.token);
                localStorage.setItem('userInfo', JSON.stringify(data.userInfo));
                this.Data.user = data.userInfo;
                console.log(data);
                return data;
            }
            return null;
        })).pipe(
            catchError((error: HttpErrorResponse) => {
                return new Observable((observer: InnerSubscriber<any, any>) => {
                    observer.next(null)
                });
            })
        );
    }

    public register(data: any): Observable<any> {
        return this.http.post<any[]>(this.API_URL + '/user', data).pipe(map((data: any) => {
            console.log('end');

            return data;
        })).pipe(
            catchError((error: HttpErrorResponse) => {
                return new Observable((observer: InnerSubscriber<any, any>) => {
                    observer.next(null)
                });
            })
        );
    }
    public createReport(data: any): Observable<any> {
        return this.http.post<any[]>(this.API_URL + '/report', data).pipe(map((data: any) => {
            console.log('end');

            return data;
        })).pipe(
            catchError((error: HttpErrorResponse) => {
                return new Observable((observer: InnerSubscriber<any, any>) => {
                    observer.next(null)
                });
            })
        );
    }
    public changePassword(data: any): Observable<any> {
        return this.http.post<any[]>(this.API_URL + '/user/change-password', data).pipe(map((data: any) => {

            return data;
        })).pipe(
            catchError((error: HttpErrorResponse) => {
                return new Observable((observer: InnerSubscriber<any, any>) => {
                    observer.next(null)
                });
            })
        );
    }


    public getAccountInfo(): Observable<any> {
        let token = sessionStorage.getItem('authToken') || localStorage.getItem('authToken');
        let myheaders = new HttpHeaders().set('Content-Type', 'application/json')
            .set('authorization', 'Bearer ' + token).set('Access-Control-Allow-Methods', 'GET,PUT,POST,DELETE,PATCH,OPTIONS')
            .set('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept, Authorization');
        return this.httpClient.get(this.API_URL + "/user/profile", { headers: myheaders }).pipe(map((user: any) => {
            console.log(user);
            let data = {

            };
            return data;
        }));
    }



    public checkout(cart): Observable<any> {
        return this.http.post<any[]>(this.API_URL + '/order', cart).pipe(map((data: any) => {
            console.log('order thành công');
            console.log(data);
            return data;
        })).pipe(
            catchError((error: HttpErrorResponse) => {
                return new Observable((observer: InnerSubscriber<any, any>) => {
                    observer.next(null)
                });
            })
        );
    }

    public getProducts(name: any, cateId: any, min: any, max: any, page: any, size: any, sort: any = false): Observable<Product[]> {
        let url = this.API_URL + '/product?name=' + name;
        if (cateId) {
            url += "&categoryid=" + cateId
        }
        if (min && max) {
            url += "&min=" + min;
            url += "&max=" + max;
        }
        url += "&page=" + page + "&size=" + size;
        if (sort) {
            url += "&sort=" + sort;
        }
        return this.http.get<Product[]>(url).pipe(map((data: any) => {

            let maxPrice = 0;
            let minPrice = 50000000;

            data.content = data.content.map(pro => {
                if (pro.priceOut < minPrice) {
                    minPrice = pro.priceOut;
                }
                if (pro.priceOut > maxPrice) {
                    maxPrice = pro.priceOut;
                }
                let hasPromotion = (pro.discount != null);
                return {
                    id: pro.productId,
                    name: pro.productName,
                    images: pro.images.map(image => {
                        return {
                            small: this.API_URL + "/image/" + image.imageId,
                            medium: this.API_URL + "/image/" + image.imageId,
                            big: this.API_URL + "/image/" + image.imageId
                        }
                    }),
                    oldPrice: hasPromotion ? pro.priceOut : null,
                    newPrice: pro.priceOut * ((100 - pro.discount) / 100),
                    discount: pro.discount,
                    description: pro.description,
                    quantity: pro.quantity
                }
            })
            data.minPrice = (minPrice == 50000000) ? 0 : minPrice;
            data.maxPrice = maxPrice;
            return data;
        }));
    }

    public getSlide(name: any, cateId: any, min: any, max: any, page: any, size: any, sort: any = false): Observable<Product[]> {
        let url = this.API_URL + '/product/slide?name=' + name;
        if (cateId) {
            url += "&categoryid=" + cateId
        }
        if (min && max) {
            url += "&min=" + min;
            url += "&max=" + max;
        }
        url += "&page=" + page + "&size=" + size;
        if (sort) {
            url += "&sort=" + sort;
        }
        return this.http.get<Product[]>(url).pipe(map((data: any) => {

            let maxPrice = 0;
            let minPrice = 50000000;

            data.content = data.content.map(pro => {
                if (pro.priceOut < minPrice) {
                    minPrice = pro.priceOut;
                }
                if (pro.priceOut > maxPrice) {
                    maxPrice = pro.priceOut;
                }
                let hasPromotion = (pro.discount != null);
                return {
                    id: pro.productId,
                    name: pro.productName,
                    images: pro.images.map(image => {
                        return {
                            small: this.API_URL + "/image/" + image.imageId,
                            medium: this.API_URL + "/image/" + image.imageId,
                            big: this.API_URL + "/image/" + image.imageId
                        }
                    }),
                    oldPrice: hasPromotion ? pro.priceOut : null,
                    newPrice: pro.priceOut * ((100 - pro.discount) / 100),
                    discount: pro.discount,
                    description: pro.description
                }
            })
            data.minPrice = (minPrice == 50000000) ? 0 : minPrice;
            data.maxPrice = maxPrice;
            console.log(maxPrice);
            console.log(minPrice);
            return data;
        }));
    }

    public getProductById(id): Observable<any> {
        return this.http.get<any>(this.API_URL + "/product/" + id).pipe(map((pro: any) => {
            let hasPromotion = (pro.discount != null);
            let data = {
                id: pro.productId,
                name: pro.productName,
                images: pro.images.map(image => {
                    return {
                        small: this.API_URL + "/image/" + image.imageId,
                        medium: this.API_URL + "/image/" + image.imageId,
                        big: this.API_URL + "/image/" + image.imageId
                    }
                }),
                oldPrice: hasPromotion ? pro.priceOut : null,
                newPrice: pro.priceOut * ((100 - pro.discount) / 100),
                discount: pro.discount,
                description: pro.description,
                availibilityCount: pro.quantity
            };
            return data;
        }));
    }

    public getOrderReview(userId: number): Observable<any> {
        return this.http.get(this.API_URL + "/order/" + userId + "/review").pipe(map((reviewDto: any) => {

            return reviewDto;
        }));
    }

    public getRankOfPrice(): Observable<any> {
        return this.http.get(this.API_URL + "/product/getRank").pipe(map((rank: any) => {

            return rank;
        }));
    }
    public getOrderDetailsByOrderDungNA(OrderId: number): Observable<any> {
        return this.http.get(this.API_URL + "/order/" + OrderId ).pipe(map((orderDetails: any) => {
            orderDetails.addAt = new Date(orderDetails.addAt)
            return orderDetails;
        }));
    }
    public getOrderDetailsByOrder(OrderId: number): Observable<any> {
        return this.http.get(this.API_URL + "/order/" + OrderId + "/detail").pipe(map((orderDetails: any) => {

            return orderDetails;
        }));
    }

    public getBanners(): Observable<any[]> {
        return this.http.get<any[]>(this.API_URL + '/category').pipe(map((cate: any) => {
            let data = cate.content.filter(ele => {
                return ele.categoryParentId == null;
            }).map(ele => {
                return {
                    id: ele.categoryId,
                    title: ele.categoryName,
                    subtitle: ele.description,
                    image: this.API_URL + "/category/" + ele.categoryId + "/image"
                }
            })
            return data;
        }));
    }

    public addToCompare(product: Product) {
        let message, status;
        if (this.Data.compareList.filter(item => item.id == product.id)[0]) {
            message = 'The product ' + product.name + ' already added to comparison list.';
            status = 'error';
        }
        else {
            this.Data.compareList.push(product);
            message = 'The product ' + product.name + ' has been added to comparison list.';
            status = 'success';
        }
        this.snackBar.open(message, '×', { panelClass: [status], verticalPosition: 'top', duration: 3000 });
    }

    public addToWishList(product: Product) {
        let message, status;
        if (this.Data.wishList.filter(item => item.id == product.id)[0]) {
            message = 'The product ' + product.name + ' already added to wish list.';
            status = 'error';
        }
        else {
            this.Data.wishList.push(product);
            message = 'The product ' + product.name + ' has been added to wish list.';
            status = 'success';
        }
        this.snackBar.open(message, '×', { panelClass: [status], verticalPosition: 'top', duration: 3000 });
    }

    public addToCart(product: Product) {
        let message, status;
        if (this.Data.cartList.filter(item => item.id == product.id)[0]) {
            message = 'Sản phẩm ' + product.name + ' đã được thêm vào giỏ hàng.Vui lòng xóa sản phẩm trước khi thêm số lượng mong muốn.';
            status = 'error';
        }
        else {
            this.Data.totalPrice = null;
            this.Data.cartList.push(product);
            this.Data.cartList.forEach(product => {
                this.Data.totalPrice = this.Data.totalPrice + (product.newPrice * product.quantity);
            })
            message = 'Sản phẩm ' + product.name + ' đã được thêm vào giỏ hàng';
            status = 'success';
        }
        this.snackBar.open(message, '×', { panelClass: [status], verticalPosition: 'top', duration: 3000 });
    }

    public getBrands() {
        return [
            { name: 'aloha', image: 'assets/images/brands/aloha.png' },
            { name: 'dream', image: 'assets/images/brands/dream.png' },
            { name: 'congrats', image: 'assets/images/brands/congrats.png' },
            { name: 'best', image: 'assets/images/brands/best.png' },
            { name: 'original', image: 'assets/images/brands/original.png' },
            { name: 'retro', image: 'assets/images/brands/retro.png' },
            { name: 'king', image: 'assets/images/brands/king.png' },
            { name: 'love', image: 'assets/images/brands/love.png' },
            { name: 'the', image: 'assets/images/brands/the.png' },
            { name: 'easter', image: 'assets/images/brands/easter.png' },
            { name: 'with', image: 'assets/images/brands/with.png' },
            { name: 'special', image: 'assets/images/brands/special.png' },
            { name: 'bravo', image: 'assets/images/brands/bravo.png' }
        ];
    }

    public getCountries() {
        return [
            { name: 'Afghanistan', code: 'AF' },
            { name: 'Aland Islands', code: 'AX' },
            { name: 'Albania', code: 'AL' },
            { name: 'Algeria', code: 'DZ' },
            { name: 'American Samoa', code: 'AS' },
            { name: 'AndorrA', code: 'AD' },
            { name: 'Angola', code: 'AO' },
            { name: 'Anguilla', code: 'AI' },
            { name: 'Antarctica', code: 'AQ' },
            { name: 'Antigua and Barbuda', code: 'AG' },
            { name: 'Argentina', code: 'AR' },
            { name: 'Armenia', code: 'AM' },
            { name: 'Aruba', code: 'AW' },
            { name: 'Australia', code: 'AU' },
            { name: 'Austria', code: 'AT' },
            { name: 'Azerbaijan', code: 'AZ' },
            { name: 'Bahamas', code: 'BS' },
            { name: 'Bahrain', code: 'BH' },
            { name: 'Bangladesh', code: 'BD' },
            { name: 'Barbados', code: 'BB' },
            { name: 'Belarus', code: 'BY' },
            { name: 'Belgium', code: 'BE' },
            { name: 'Belize', code: 'BZ' },
            { name: 'Benin', code: 'BJ' },
            { name: 'Bermuda', code: 'BM' },
            { name: 'Bhutan', code: 'BT' },
            { name: 'Bolivia', code: 'BO' },
            { name: 'Bosnia and Herzegovina', code: 'BA' },
            { name: 'Botswana', code: 'BW' },
            { name: 'Bouvet Island', code: 'BV' },
            { name: 'Brazil', code: 'BR' },
            { name: 'British Indian Ocean Territory', code: 'IO' },
            { name: 'Brunei Darussalam', code: 'BN' },
            { name: 'Bulgaria', code: 'BG' },
            { name: 'Burkina Faso', code: 'BF' },
            { name: 'Burundi', code: 'BI' },
            { name: 'Cambodia', code: 'KH' },
            { name: 'Cameroon', code: 'CM' },
            { name: 'Canada', code: 'CA' },
            { name: 'Cape Verde', code: 'CV' },
            { name: 'Cayman Islands', code: 'KY' },
            { name: 'Central African Republic', code: 'CF' },
            { name: 'Chad', code: 'TD' },
            { name: 'Chile', code: 'CL' },
            { name: 'China', code: 'CN' },
            { name: 'Christmas Island', code: 'CX' },
            { name: 'Cocos (Keeling) Islands', code: 'CC' },
            { name: 'Colombia', code: 'CO' },
            { name: 'Comoros', code: 'KM' },
            { name: 'Congo', code: 'CG' },
            { name: 'Congo, The Democratic Republic of the', code: 'CD' },
            { name: 'Cook Islands', code: 'CK' },
            { name: 'Costa Rica', code: 'CR' },
            { name: 'Cote D\'Ivoire', code: 'CI' },
            { name: 'Croatia', code: 'HR' },
            { name: 'Cuba', code: 'CU' },
            { name: 'Cyprus', code: 'CY' },
            { name: 'Czech Republic', code: 'CZ' },
            { name: 'Denmark', code: 'DK' },
            { name: 'Djibouti', code: 'DJ' },
            { name: 'Dominica', code: 'DM' },
            { name: 'Dominican Republic', code: 'DO' },
            { name: 'Ecuador', code: 'EC' },
            { name: 'Egypt', code: 'EG' },
            { name: 'El Salvador', code: 'SV' },
            { name: 'Equatorial Guinea', code: 'GQ' },
            { name: 'Eritrea', code: 'ER' },
            { name: 'Estonia', code: 'EE' },
            { name: 'Ethiopia', code: 'ET' },
            { name: 'Falkland Islands (Malvinas)', code: 'FK' },
            { name: 'Faroe Islands', code: 'FO' },
            { name: 'Fiji', code: 'FJ' },
            { name: 'Finland', code: 'FI' },
            { name: 'France', code: 'FR' },
            { name: 'French Guiana', code: 'GF' },
            { name: 'French Polynesia', code: 'PF' },
            { name: 'French Southern Territories', code: 'TF' },
            { name: 'Gabon', code: 'GA' },
            { name: 'Gambia', code: 'GM' },
            { name: 'Georgia', code: 'GE' },
            { name: 'Germany', code: 'DE' },
            { name: 'Ghana', code: 'GH' },
            { name: 'Gibraltar', code: 'GI' },
            { name: 'Greece', code: 'GR' },
            { name: 'Greenland', code: 'GL' },
            { name: 'Grenada', code: 'GD' },
            { name: 'Guadeloupe', code: 'GP' },
            { name: 'Guam', code: 'GU' },
            { name: 'Guatemala', code: 'GT' },
            { name: 'Guernsey', code: 'GG' },
            { name: 'Guinea', code: 'GN' },
            { name: 'Guinea-Bissau', code: 'GW' },
            { name: 'Guyana', code: 'GY' },
            { name: 'Haiti', code: 'HT' },
            { name: 'Heard Island and Mcdonald Islands', code: 'HM' },
            { name: 'Holy See (Vatican City State)', code: 'VA' },
            { name: 'Honduras', code: 'HN' },
            { name: 'Hong Kong', code: 'HK' },
            { name: 'Hungary', code: 'HU' },
            { name: 'Iceland', code: 'IS' },
            { name: 'India', code: 'IN' },
            { name: 'Indonesia', code: 'ID' },
            { name: 'Iran, Islamic Republic Of', code: 'IR' },
            { name: 'Iraq', code: 'IQ' },
            { name: 'Ireland', code: 'IE' },
            { name: 'Isle of Man', code: 'IM' },
            { name: 'Israel', code: 'IL' },
            { name: 'Italy', code: 'IT' },
            { name: 'Jamaica', code: 'JM' },
            { name: 'Japan', code: 'JP' },
            { name: 'Jersey', code: 'JE' },
            { name: 'Jordan', code: 'JO' },
            { name: 'Kazakhstan', code: 'KZ' },
            { name: 'Kenya', code: 'KE' },
            { name: 'Kiribati', code: 'KI' },
            { name: 'Korea, Democratic People\'S Republic of', code: 'KP' },
            { name: 'Korea, Republic of', code: 'KR' },
            { name: 'Kuwait', code: 'KW' },
            { name: 'Kyrgyzstan', code: 'KG' },
            { name: 'Lao People\'S Democratic Republic', code: 'LA' },
            { name: 'Latvia', code: 'LV' },
            { name: 'Lebanon', code: 'LB' },
            { name: 'Lesotho', code: 'LS' },
            { name: 'Liberia', code: 'LR' },
            { name: 'Libyan Arab Jamahiriya', code: 'LY' },
            { name: 'Liechtenstein', code: 'LI' },
            { name: 'Lithuania', code: 'LT' },
            { name: 'Luxembourg', code: 'LU' },
            { name: 'Macao', code: 'MO' },
            { name: 'Macedonia, The Former Yugoslav Republic of', code: 'MK' },
            { name: 'Madagascar', code: 'MG' },
            { name: 'Malawi', code: 'MW' },
            { name: 'Malaysia', code: 'MY' },
            { name: 'Maldives', code: 'MV' },
            { name: 'Mali', code: 'ML' },
            { name: 'Malta', code: 'MT' },
            { name: 'Marshall Islands', code: 'MH' },
            { name: 'Martinique', code: 'MQ' },
            { name: 'Mauritania', code: 'MR' },
            { name: 'Mauritius', code: 'MU' },
            { name: 'Mayotte', code: 'YT' },
            { name: 'Mexico', code: 'MX' },
            { name: 'Micronesia, Federated States of', code: 'FM' },
            { name: 'Moldova, Republic of', code: 'MD' },
            { name: 'Monaco', code: 'MC' },
            { name: 'Mongolia', code: 'MN' },
            { name: 'Montserrat', code: 'MS' },
            { name: 'Morocco', code: 'MA' },
            { name: 'Mozambique', code: 'MZ' },
            { name: 'Myanmar', code: 'MM' },
            { name: 'Namibia', code: 'NA' },
            { name: 'Nauru', code: 'NR' },
            { name: 'Nepal', code: 'NP' },
            { name: 'Netherlands', code: 'NL' },
            { name: 'Netherlands Antilles', code: 'AN' },
            { name: 'New Caledonia', code: 'NC' },
            { name: 'New Zealand', code: 'NZ' },
            { name: 'Nicaragua', code: 'NI' },
            { name: 'Niger', code: 'NE' },
            { name: 'Nigeria', code: 'NG' },
            { name: 'Niue', code: 'NU' },
            { name: 'Norfolk Island', code: 'NF' },
            { name: 'Northern Mariana Islands', code: 'MP' },
            { name: 'Norway', code: 'NO' },
            { name: 'Oman', code: 'OM' },
            { name: 'Pakistan', code: 'PK' },
            { name: 'Palau', code: 'PW' },
            { name: 'Palestinian Territory, Occupied', code: 'PS' },
            { name: 'Panama', code: 'PA' },
            { name: 'Papua New Guinea', code: 'PG' },
            { name: 'Paraguay', code: 'PY' },
            { name: 'Peru', code: 'PE' },
            { name: 'Philippines', code: 'PH' },
            { name: 'Pitcairn', code: 'PN' },
            { name: 'Poland', code: 'PL' },
            { name: 'Portugal', code: 'PT' },
            { name: 'Puerto Rico', code: 'PR' },
            { name: 'Qatar', code: 'QA' },
            { name: 'Reunion', code: 'RE' },
            { name: 'Romania', code: 'RO' },
            { name: 'Russian Federation', code: 'RU' },
            { name: 'RWANDA', code: 'RW' },
            { name: 'Saint Helena', code: 'SH' },
            { name: 'Saint Kitts and Nevis', code: 'KN' },
            { name: 'Saint Lucia', code: 'LC' },
            { name: 'Saint Pierre and Miquelon', code: 'PM' },
            { name: 'Saint Vincent and the Grenadines', code: 'VC' },
            { name: 'Samoa', code: 'WS' },
            { name: 'San Marino', code: 'SM' },
            { name: 'Sao Tome and Principe', code: 'ST' },
            { name: 'Saudi Arabia', code: 'SA' },
            { name: 'Senegal', code: 'SN' },
            { name: 'Serbia and Montenegro', code: 'CS' },
            { name: 'Seychelles', code: 'SC' },
            { name: 'Sierra Leone', code: 'SL' },
            { name: 'Singapore', code: 'SG' },
            { name: 'Slovakia', code: 'SK' },
            { name: 'Slovenia', code: 'SI' },
            { name: 'Solomon Islands', code: 'SB' },
            { name: 'Somalia', code: 'SO' },
            { name: 'South Africa', code: 'ZA' },
            { name: 'South Georgia and the South Sandwich Islands', code: 'GS' },
            { name: 'Spain', code: 'ES' },
            { name: 'Sri Lanka', code: 'LK' },
            { name: 'Sudan', code: 'SD' },
            { name: 'Suriname', code: 'SR' },
            { name: 'Svalbard and Jan Mayen', code: 'SJ' },
            { name: 'Swaziland', code: 'SZ' },
            { name: 'Sweden', code: 'SE' },
            { name: 'Switzerland', code: 'CH' },
            { name: 'Syrian Arab Republic', code: 'SY' },
            { name: 'Taiwan, Province of China', code: 'TW' },
            { name: 'Tajikistan', code: 'TJ' },
            { name: 'Tanzania, United Republic of', code: 'TZ' },
            { name: 'Thailand', code: 'TH' },
            { name: 'Timor-Leste', code: 'TL' },
            { name: 'Togo', code: 'TG' },
            { name: 'Tokelau', code: 'TK' },
            { name: 'Tonga', code: 'TO' },
            { name: 'Trinidad and Tobago', code: 'TT' },
            { name: 'Tunisia', code: 'TN' },
            { name: 'Turkey', code: 'TR' },
            { name: 'Turkmenistan', code: 'TM' },
            { name: 'Turks and Caicos Islands', code: 'TC' },
            { name: 'Tuvalu', code: 'TV' },
            { name: 'Uganda', code: 'UG' },
            { name: 'Ukraine', code: 'UA' },
            { name: 'United Arab Emirates', code: 'AE' },
            { name: 'United Kingdom', code: 'GB' },
            { name: 'United States', code: 'US' },
            { name: 'United States Minor Outlying Islands', code: 'UM' },
            { name: 'Uruguay', code: 'UY' },
            { name: 'Uzbekistan', code: 'UZ' },
            { name: 'Vanuatu', code: 'VU' },
            { name: 'Venezuela', code: 'VE' },
            { name: 'Viet Nam', code: 'VN' },
            { name: 'Virgin Islands, British', code: 'VG' },
            { name: 'Virgin Islands, U.S.', code: 'VI' },
            { name: 'Wallis and Futuna', code: 'WF' },
            { name: 'Western Sahara', code: 'EH' },
            { name: 'Yemen', code: 'YE' },
            { name: 'Zambia', code: 'ZM' },
            { name: 'Zimbabwe', code: 'ZW' }
        ]
    }

    public getMonths() {
        return [
            { value: '01', name: 'January' },
            { value: '02', name: 'February' },
            { value: '03', name: 'March' },
            { value: '04', name: 'April' },
            { value: '05', name: 'May' },
            { value: '06', name: 'June' },
            { value: '07', name: 'July' },
            { value: '08', name: 'August' },
            { value: '09', name: 'September' },
            { value: '10', name: 'October' },
            { value: '11', name: 'November' },
            { value: '12', name: 'December' }
        ]
    }

    public getYears() {
        return ["2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"]
    }

    public getDeliveryMethods() {
        return [
            { value: 'free', name: 'Giao hàng miễn phí trong 2 đến 3 ngày', desc: '' },
            // { value: 'standard', name: 'Giao hàng nhanh:30 000 đ(khu vực Hà Nội trong vòng 1 ngày )', desc: ' /  75.000 đ(dành cho các tỉnh ngoài Hà Nội giao hàng trong 3 đến 4 ngày làm việc' },
            //{ value: 'express', name: 'Chuyển phát nhanh', desc: '$29.99 / Giao hàng trong 1 ngày làm việc' }
        ]
    }

} 