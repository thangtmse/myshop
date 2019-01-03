// Angular
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { OrderRoutingModule } from './order.routing.module';
import { OrderComponent } from './order.component';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { ModalModule } from 'ngx-bootstrap';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ToastrModule } from 'ngx-toastr';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { CheckOutComponent } from './checkout.component';
import { NgSelectModule } from '@ng-select/ng-select';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    NgxDatatableModule,
    OrderRoutingModule,
    NgSelectModule,
    ModalModule.forRoot(),
    ToastrModule.forRoot()
  ],
  declarations: [
    CheckOutComponent,
    OrderComponent
  ]
})
export class OrderModule { }
