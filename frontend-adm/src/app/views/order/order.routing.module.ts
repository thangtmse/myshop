import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CheckOutComponent } from './checkout.component';
import { OrderComponent } from './order.component';

const routes: Routes = [
  {
    path: '',
    component: OrderComponent,
    data: {
      title: 'Order'
    }
  },
  {
    path: 'checkout/:id',
    component: CheckOutComponent,
    data: {
      title: 'Checkout'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OrderRoutingModule { }
