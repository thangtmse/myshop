import { Component, OnInit, ViewChild, HostListener } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog } from '@angular/material';
import { ProductDialogComponent } from '../../shared/products-carousel/product-dialog/product-dialog.component';
import { AppService } from '../../app.service';
import { Product, Category } from "../../app.models";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit {
  @ViewChild('sidenav') sidenav: any;
  public sidenavOpen: boolean = true;
  private sub: any;
  public viewType: string = 'grid';
  public viewCol: number = 25;
  public counts = [12, 24, 36];
  public count: any;
  public totalItems: any;
  public sortings = ['Mặc định', 'Giá tăng dần', 'Giá giảm dần'];
  public sort: any;
  public products: Array<Product> = [];
  public categories: Category[];
  public category: any = {};
  public search = "";
  public brands = [];
  public priceFrom: number = 1;
  public priceTo: number = 500000000;
  public colors = ["#5C6BC0", "#66BB6A", "#EF5350", "#BA68C8", "#FF4081", "#9575CD", "#90CAF9", "#B2DFDB", "#DCE775", "#FFD740", "#00E676", "#FBC02D", "#FF7043", "#F5F5F5", "#000000"];
  public sizes = ["S", "M", "L", "XL", "2XL", "32", "36", "38", "46", "52", "13.3\"", "15.4\"", "17\"", "21\"", "23.4\""];
  public page: any;
  public max:number;
  public min:number;

  constructor(private activatedRoute: ActivatedRoute, public appService: AppService, public dialog: MatDialog, private router: Router) { }

  ngOnInit() {
    this.count = this.counts[0];
    this.sort = this.sortings[0];
    this.page = 1;
    this.sub = this.activatedRoute.params.subscribe(params => {
      this.category = {
        name: params['name'],
        id: params['id'] || null
      };
      this.search = params['search'] || '';
      AppService.search.emit({ txt: this.search, id: params['id'] })
      this.priceFrom = 0;
      this.priceTo = 500000000;
      this.getAllProducts();
    });
    if (window.innerWidth < 960) {
      this.sidenavOpen = false;
    };
    if (window.innerWidth < 1280) {
      this.viewCol = 33.3;
    };
    this.priceFrom = 0;
    this.priceTo = 500000000;
    this.getCategories();
    this.getBrands();
    // this.getAllProducts();
  }

  public getAllProducts() {
    let sortText = null;
    if(this.sort == this.sortings[1]){
      sortText="priceOut,asc"
    }
    if(this.sort == this.sortings[2]){
      sortText="priceOut,desc"
    }
    
    this.appService.getProducts(this.search.replace(/ +(?= )/g,''), this.category.id ? this.category.id : null, this.priceFrom, this.priceTo, this.page - 1, this.count, sortText).subscribe((data: any) => {
      this.products = data.content;
      this.totalItems = data.totalElements;
    })

    this.appService.getProducts(this.search.replace(/ +(?= )/g,''), this.category.id ? this.category.id : null, 1, 5000000000, this.page - 1, this.count, sortText).subscribe((data: any) => {
      this.priceFrom = data.minPrice-100;
      this.priceTo = data.maxPrice+100;
      this.max = data.maxPrice+100;
      this.min = data.minPrice-100;
    })
    
  }

  public getAllProductsBySlider(){
    let sortText = null;
    if(this.sort == this.sortings[1]){
      sortText="priceOut,asc"
    }
    if(this.sort == this.sortings[2]){
      sortText="priceOut,desc"
    }
    
    this.appService.getProducts(this.search.replace(/ +(?= )/g,''), this.category.id ? this.category.id : null, this.priceFrom, this.priceTo, this.page - 1, this.count, sortText).subscribe((data: any) => {
      this.products = data.content;
      this.totalItems = data.totalElements;
    })
  }
  public getCategories() {
    if (this.appService.Data.categories.length == 0) {
      this.appService.getCategories().subscribe(data => {
        this.categories = data;
        this.appService.Data.categories = data;
      });
    }
    else {
      this.categories = this.appService.Data.categories;
    }
  }

  public getBrands() {
    this.brands = this.appService.getBrands();
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  @HostListener('window:resize')
  public onWindowResize(): void {
    (window.innerWidth < 960) ? this.sidenavOpen = false : this.sidenavOpen = true;
    (window.innerWidth < 1280) ? this.viewCol = 33.3 : this.viewCol = 25;
  }

  public changeCount(count) {
    this.count = count;
    this.getAllProducts();
  }

  public changeSorting(sort) {
    this.sort = sort;
    this.getAllProducts();
  }

  public changeViewType(viewType, viewCol) {
    this.viewType = viewType;
    this.viewCol = viewCol;
  }

  public openProductDialog(product) {
    let dialogRef = this.dialog.open(ProductDialogComponent, {
      data: product,
      panelClass: 'product-dialog'
    });
    dialogRef.afterClosed().subscribe(product => {
      if (product) {
        this.router.navigate(['/products', product.id, product.name]);
      }
    });
  }

  public onPageChanged(event) {
    this.page = event;
    this.getAllProducts();
    window.scrollTo(0, 0);
  }

  public onChangeCategory(event) {
    if (event.id) {
      this.router.navigate(['/products/cate', event.id, event.name]);
    }
  }

}
