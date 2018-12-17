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
    showHuy: boolean = false;
    showDangXuly: boolean = false;
    showDanggiaohang: boolean=false;
    showHoanThanh: boolean = false;
  ngOnInit(): void {
    this.orderId = Number.parseInt(this.route.snapshot.paramMap.get('id'));
    
    this.orderService.getOrder(this.orderId).subscribe(data => {
      console.log(data);
     
      this.orderDetail = [];
      this.total = 0;
      for (let detail of data) {
        this.total += detail.totalprice;
        this.orderDetail.push(detail);
      }
    });
    this.orderService.getOrderInfo(this.orderId).subscribe(data => {
      if(data.status=="Hủy"){
        console.log(data.status);
        this.showDanggiaohang=true;
        this.showHoanThanh=true;
      }  if(data.status=="Đang xử lý"){
        this.showDanggiaohang=false;
        this.showHuy=true;
        this.showHoanThanh=true;
      } if(data.status=="Đang giao hàng"){
        this.showHoanThanh=false;
        this.showHuy=true;
        this.showDangXuly=true;
      } if(data.status=="Hoàn thành"){
        this.showDangXuly=true;
        this.showDanggiaohang=true;
        this.showHuy=true;
      }
      this.orderInfo = data;
      this.orderInfo.customerName = data.user.username + ' - ' + data.user.fullName;
      this.orderInfo.dateNow = new Date(data.addAt).toLocaleString();

    })
  }


  changeStatus(status: any) {
    if(status=="Hủy"){
       
      this.showDanggiaohang=true;
      this.showHoanThanh=true;
    }  if(status=="Đang xử lý"){
      this.showDanggiaohang=false;
      this.showHuy=true;
      this.showHoanThanh=true;
    } if(status=="Đang giao hàng"){
      this.showHoanThanh=false;
      this.showHuy=true;
      this.showDangXuly=true;
    } if(status=="Hoàn thành"){
      this.showDangXuly=true;
      this.showDanggiaohang=true;
      this.showHuy=true;
    }
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
