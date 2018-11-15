import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SupplierListComponent } from './supplier.list.component';
import { SupplierEditorComponent } from './supplier.editor.component';

const routes: Routes = [
  {
    path: 'list',
    component: SupplierListComponent,
    data: {
      title: 'Danh sách nhà cung cấp'
    }
  },
  {
    path: 'edit/:id',
    component: SupplierEditorComponent,
    data: {
      title: 'Sửa nhà cung cấp'
    }
  },
  {
    path: 'create',
    component: SupplierEditorComponent,
    data: {
      title: 'Tạo nhà cung cấp'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ManageSupplierRoutingModule { }
