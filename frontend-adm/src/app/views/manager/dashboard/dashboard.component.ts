import { BsModalService } from 'ngx-bootstrap/modal';
import { Component, Input } from '@angular/core';
import { OrderService } from '../../../service/order.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html'
})
export class DashboardComponent {

  rows = [];

  constructor(private orderService: OrderService) {

  }

  public orderTotal: number;
  public incomeTotal: number;

  public lineChartData: Array<any> = [
    { data: [20, 2, 20, 23, 34, 23, 10, 20, 23, 34, 23, 10], label: 'Số lượng đơn hàng', yAxisID: "y-axis-so-luong" },
    { data: [2000000, 2000000, 2300000, 1200000, 3400000, 2300000, 100000, 20, 23, 34, 23, 10], label: 'Doanh thu đơn hàng (VND)', fill: false, yAxisID: "y-axis-doanh-thu" },
  ];
  public lineChartLabels: Array<any> = ['January', 'February', 'March', 'April', 'May', 'June', 'July'];
  public lineChartOptions: any = {
    responsive: true,
    scales: {
      yAxes: [{
        id: "y-axis-so-luong"
      }, {
        id: "y-axis-doanh-thu"
      }]
    }
  };
  public lineChartColors: Array<any> = [

  ];
  public lineChartLegend: boolean = true;
  public lineChartType: string = 'line';

  // events
  public chartClicked(e: any): void {
    console.log(e);
  }

  public chartHovered(e: any): void {
    console.log(e);
  }
  activeToggle0: String = "btn btn-outline-secondary";
  activeToggle1: String = "btn btn-outline-secondary";
  activeToggle2: String = "btn btn-outline-secondary";
  activeToggle3: String = "btn btn-outline-secondary";
  activeToggle4: String = "btn btn-outline-secondary";

  ngOnInit(): void {
    this.getStaticsLine(1);
  }

  public getStaticsLine(type: number) {
    if (type == 0) {
      this.activeToggle0 = "btn btn-outline-secondary active";
    } else {
      this.activeToggle0 = "btn btn-outline-secondary";
    }
    if (type == 1) {
      this.activeToggle1 = "btn btn-outline-secondary active";
    } else {
      this.activeToggle1 = "btn btn-outline-secondary";
    }
    if (type == 2) {
      this.activeToggle2 = "btn btn-outline-secondary active";
    } else {
      this.activeToggle2 = "btn btn-outline-secondary";
    }

    this.orderService.getStaticLine(type).subscribe(data => {
      this.lineChartLabels = data.labels; 
      this.lineChartData[0].data = data.datasets[0];
      this.lineChartData[1].data = data.datasets[1];
      this.orderTotal = data.orderTotal;
      this.incomeTotal = data.incomeTotal;
    });
  }

}
