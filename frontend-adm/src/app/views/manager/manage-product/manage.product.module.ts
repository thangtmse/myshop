// Angular
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { ManageProductRoutingModule } from './manage.product.routing.module';
import { ManageProductComponent } from './manage.product.component';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { ModalModule } from 'ngx-bootstrap';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ToastrModule } from 'ngx-toastr';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { ProductEditorComponent } from './product.editor.component';
import { NgSelectModule } from '@ng-select/ng-select';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    NgxDatatableModule,
    ManageProductRoutingModule,
    NgSelectModule,
    ModalModule.forRoot(),
    ToastrModule.forRoot()
  ],
  declarations: [
    ManageProductComponent,
    ProductEditorComponent
  ]
})
export class ManageProductModule { }
