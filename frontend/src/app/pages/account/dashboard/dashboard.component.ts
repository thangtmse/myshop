import { Component, OnInit } from '@angular/core';
import { AppService } from '../../../app.service';
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  constructor(public appService: AppService) { }
  public reviewDto:any ={
    done: 2,
    processing: 1,
    delivering: 1,
    cancel: 1
  };
  ngOnInit() {
    let userInfo =  JSON.parse(localStorage.getItem('userInfo'));
    this.appService.getOrderReview(userInfo.userId).subscribe(data=>{
      console.log(data)
      this.reviewDto = data;
    })
  }

}
