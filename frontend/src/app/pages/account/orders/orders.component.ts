import { Component, OnInit } from '@angular/core';
import { AppService } from '../../../app.service';
@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.scss'],
  providers: [NgbModalConfig, NgbModal]
})
export class OrdersComponent implements OnInit {

  constructor(public appService: AppService,public config: NgbModalConfig, private modalService: NgbModal) {
    // customize default values of modals used by this component tree
    config.backdrop = 'static';
    config.keyboard = false;
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
