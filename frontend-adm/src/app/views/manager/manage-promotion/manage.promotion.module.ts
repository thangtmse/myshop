// Angular
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { ManagePromotionRoutingModule } from './manage.promotion.routing.module';
import { PromotionListComponent } from './promotion.list.component';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { ModalModule, BsDatepickerModule } from 'ngx-bootstrap';
import { EditPromotionComponent } from './promotion.edit.component';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';
import { FormsModule, ReactiveFormsModule  }   from '@angular/forms';
import { ToastrModule } from 'ngx-toastr';
import { NgSelectModule } from '@ng-select/ng-select';

@NgModule({
  imports: [
    CommonModule,
    NgxDatatableModule,
    ManagePromotionRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    NgSelectModule,
    ModalModule.forRoot(),
    ToastrModule.forRoot(),
    BsDatepickerModule.forRoot()
  ],
  declarations: [
    PromotionListComponent,
    EditPromotionComponent
  ],
})
export class ManagePromotionModule { }
