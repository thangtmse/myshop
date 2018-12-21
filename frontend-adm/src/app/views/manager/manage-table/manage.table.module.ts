// Angular
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { ManageTableRoutingModule } from './manage.table.routing.module';
import { TableListComponent } from './table.list.component';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { ModalModule, BsDatepickerModule } from 'ngx-bootstrap';
import { EditTableComponent } from './table.edit.component';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';
import { FormsModule, ReactiveFormsModule  }   from '@angular/forms';
import { ToastrModule } from 'ngx-toastr';
import { NgSelectModule } from '@ng-select/ng-select';

@NgModule({
  imports: [
    CommonModule,
    NgxDatatableModule,
    ManageTableRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    NgSelectModule,
    ModalModule.forRoot(),
    ToastrModule.forRoot(),
    BsDatepickerModule.forRoot()
  ],
  declarations: [
    TableListComponent,
    EditTableComponent
  ],
})
export class ManageTableModule { }
