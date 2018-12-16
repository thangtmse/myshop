import { Component, OnInit } from '@angular/core';
import { AppService } from '../../app.service';
import { Product } from "../../app.models";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  public slides = [
    { id: 1, title: 'The biggest sale', subtitle: 'Special for today', image: 'assets/images/carousel/banner1.jpg' },
    { title: 'Summer collection', subtitle: 'New Arrivals On Sale', image: 'assets/images/carousel/banner2.jpg' },
    { title: 'The biggest sale', subtitle: 'Special for today', image: 'assets/images/carousel/banner3.jpg' },
    { title: 'Summer collection', subtitle: 'New Arrivals On Sale', image: 'assets/images/carousel/banner4.jpg' },
    { title: 'The biggest sale', subtitle: 'Special for today', image: 'assets/images/carousel/banner5.jpg' }
  ];

  public brands = [];
  public banners = [];
  public featuredProducts: Array<Product>;
  public onSaleProducts: Array<Product>;
  public topRatedProducts: Array<Product>;
  public newArrivalsProducts: Array<Product>;


  constructor(public appService: AppService) { }

  ngOnInit() {
    this.getBanners();
    this.getProducts("featured");
    // this.initSlide();
    this.getBrands();
  }

  public onLinkClick(e) {
    this.getProducts(e.tab.textLabel.toLowerCase());
  }

  public initSlide() {
    this.appService.getProducts("", null, null, null, 0, 5).subscribe((data: any) => {
      let products: any = data.content;
      products.forEach(element => {
        console.log(element);
        let slide: any = {
          title: element.name,
          subtitle: element.description,
          image: element.images[0].big,
          id: element.id
        }
        console.log(slide);
        this.slides.push(slide);
      });
    })
  }

  public getProducts(type) {
    if (type == "featured" && !this.featuredProducts) {
      this.appService.getProducts("", null, null, null, 0, 20).subscribe((data: any) => {
        this.featuredProducts = data.content;
      })
    }
    // if(type == "on sale" && !this.onSaleProducts){
    //   this.appService.getProducts("on-sale").subscribe(data=>{
    //     this.onSaleProducts = data;      
    //   })
    // }
    // if(type == "top rated" && !this.topRatedProducts){
    //   this.appService.getProducts("top-rated").subscribe(data=>{
    //     this.topRatedProducts = data;      
    //   })
    // }
    // if(type == "new arrivals" && !this.newArrivalsProducts){
    //   this.appService.getProducts("new-arrivals").subscribe(data=>{
    //     this.newArrivalsProducts = data;      
    //   })
    // }

  }

  public getBanners() {
    this.appService.getBanners().subscribe(data => {
      this.banners = data;
    })
  }

  public getBrands() {
    this.brands = this.appService.getBrands();
  }

}
