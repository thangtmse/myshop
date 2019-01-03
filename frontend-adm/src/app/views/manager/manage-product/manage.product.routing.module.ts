import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ManageProductComponent } from './manage.product.component';
import { ProductEditorComponent } from './product.editor.component';

const routes: Routes = [
  {
    path: 'list',
    component: ManageProductComponent,
    data: {
      isDelete: false,
      title: 'Danh sách sản phẩm'
    }
  },
  {
    path: 'listdeleted',
    component: ManageProductComponent,
    data: {
      isDelete: true,
      title: 'Danh sách sản phẩm'
    }
  },
  {
    path: 'edit/:id',
    component: ProductEditorComponent,
    data: {
      title: 'Cập nhật sản phẩm'
    }
  },
  {
    path: 'create',
    component: ProductEditorComponent,
    data: {
      title: 'Tạo sản phẩm'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ManageProductRoutingModule { }
