import { Component, OnInit } from '@angular/core';
import { AppService } from '../../../app.service';
import { MatDialog } from '@angular/material';
import { SwiperConfigInterface } from 'ngx-swiper-wrapper';
import { ProductDialogComponent } from './product-dialog/product-dialog.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.scss']
})
export class OrdersComponent implements OnInit {
  constructor(public appService: AppService,public dialog: MatDialog,private router: Router) {

  }
  public showSpinner = false;
  public orders = null;
  public userInfo = JSON.parse(localStorage.getItem('userInfo'));
  ngOnInit() {
    console.log('start call list order');
    this.appService.getOrdersByUser(this.userInfo.userId).subscribe(data=>{
      this.orders = data;
    })
  }

  public openOrderDetail(order){  
    console.log(order); 
    let dialogRef = this.dialog.open(ProductDialogComponent, {
        data: order.number,
        panelClass: 'product-dialog'
    });
    dialogRef.afterClosed().subscribe(product => {
      // if(product){
      //   this.router.navigate(['/products', product.id, product.name]); 
      // }
    });
  }

}
