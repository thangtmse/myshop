import { Component, OnInit, OnChanges, ViewChild, TemplateRef } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderService } from '../../service/order.service';
import { SimpleChanges } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  templateUrl: './checkout.component.html'
}) export class CheckOutComponent implements OnInit {

  orderId: number = Number.NaN;
  orderInfo: any;
  returnMoneyGlobal: number;
  total: number = 0;

  orderDetail: any[] = [];

  constructor(private route: ActivatedRoute,
    private orderService: OrderService,
    private router: Router,
    private toastr: ToastrService) { }

  ngOnInit(): void {
    this.orderId = Number.parseInt(this.route.snapshot.paramMap.get('id'));
    this.orderService.getOrder(this.orderId).subscribe(data => {
      this.orderDetail = [];
      this.total = 0;
      for (let detail of data) {
        this.total += detail.totalprice;
        this.orderDetail.push(detail);
      }
    });
    this.orderService.getOrderInfo(this.orderId).subscribe(data => {
      this.orderInfo = data;
      this.orderInfo.customerName = data.user.username + ' - ' + data.user.fullName;
      this.orderInfo.dateNow = new Date(data.addAt).toLocaleString();

    })
  }


  changeStatus(status: any) {
    this.orderService.changeStatus(this.orderId, status).subscribe(
      data => {
        this.toastr.success('Cập nhật thông tin đơn hàng thành công');
        this.ngOnInit();
      },
      error => {
        this.toastr.error(error.error.message);
      }
    )
  }
}
