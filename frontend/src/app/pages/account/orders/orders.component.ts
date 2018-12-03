import { Component, OnInit } from '@angular/core';
import { AppService } from '../../../app.service';
@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.scss']
})
export class OrdersComponent implements OnInit {

  constructor(public appService: AppService) {

  }

  public orders = null;
  public userInfo = JSON.parse(localStorage.getItem('userInfo'));
  ngOnInit() {
    console.log('start call list order');
    this.appService.getOrdersByUser(this.userInfo.userId).subscribe(data=>{
      this.orders = data;
    })
  }

}
