import { ToastrModule } from 'ngx-toastr';
// Angular
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { ManageSupplierRoutingModule } from './manage.supplier.routing.module';
import { SupplierListComponent } from './supplier.list.component';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { ModalModule } from 'ngx-bootstrap';
import { SupplierEditorComponent} from './supplier.editor.component';
import { FormsModule, ReactiveFormsModule  } from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    NgxDatatableModule,
    ManageSupplierRoutingModule,
    ModalModule.forRoot(),
    FormsModule,
    ReactiveFormsModule,
    ToastrModule.forRoot()
  ],
  declarations: [
    SupplierListComponent,
    SupplierEditorComponent
  ]
})
export class ManageSupplierModule { }
