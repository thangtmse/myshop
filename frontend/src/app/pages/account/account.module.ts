import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from '../../shared/shared.module';
import { AccountComponent } from './account.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { InformationComponent } from './information/information.component';
import { AddressesComponent } from './addresses/addresses.component';
import { OrdersComponent } from './orders/orders.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { 
  MatDialogModule,
 } from '@angular/material/dialog';
  
import { ProductDialogComponent } from './orders/product-dialog/product-dialog.component';

export const routes = [
  { 
      path: '', 
      component: AccountComponent, children: [
          { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
          { path: 'dashboard', component: DashboardComponent, data: {  breadcrumb: 'Chỉnh sửa tài khoản' } },
          { path: 'information', component: InformationComponent, data: {  breadcrumb: 'Thông tin' } },
          { path: 'addresses', component: AddressesComponent, data: {  breadcrumb: 'Địa chỉ' } },
          { path: 'orders', component: OrdersComponent, data: {  breadcrumb: 'Đơn hàng' } }
      ]
  }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    ReactiveFormsModule,
    SharedModule,
    FlexLayoutModule,

    MatDialogModule,
    
  ],
  declarations: [
    AccountComponent,
    DashboardComponent,
    InformationComponent,
    AddressesComponent,
    OrdersComponent,
    ProductDialogComponent
  ],entryComponents:[
    ProductDialogComponent
  ],
  exports: [
    RouterModule,
    FlexLayoutModule,
    
    MatDialogModule,
   
    ProductDialogComponent
  ]
})
export class AccountModule { }
