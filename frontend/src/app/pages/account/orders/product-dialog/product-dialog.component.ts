import { Component, ViewEncapsulation, OnInit, Inject} from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { SwiperConfigInterface } from 'ngx-swiper-wrapper';
import { Data, AppService } from '../../../../app.service';

@Component({
  selector: 'app-product-dialog',
  templateUrl: './product-dialog.component.html',
  styleUrls: ['./product-dialog.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class ProductDialogComponent implements OnInit {
  public config: SwiperConfigInterface = {};
  public orderdetails :any = [];
  public orderInfo={
    user:{
      username:{},
      fullName:{}
    },
    addAt:{},
    phone:{},
    address:{}
  };
  constructor(public appService:AppService, 
              public dialogRef: MatDialogRef<ProductDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public orderId: any) { }

  ngOnInit() { 
    this.appService.getOrderDetailsByOrderDungNA(this.orderId).subscribe(data=>{
      this.orderInfo = data;
    })
    this.appService.getOrderDetailsByOrder(this.orderId).subscribe(orderDetails => {
      this.orderdetails = orderDetails;
      
    });
  }

  ngAfterViewInit(){
    this.config = {
      slidesPerView: 1,
      spaceBetween: 0,         
      keyboard: true,
      navigation: true,
      pagination: false,
      grabCursor: true,        
      loop: false,
      preloadImages: false,
      lazy: true, 
      effect: "fade",
      fadeEffect: {
        crossFade: true
      }
    }
  }

  public close(): void {
    this.dialogRef.close();

  }



}