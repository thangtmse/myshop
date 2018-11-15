import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CategoryListComponent } from './category.list.component';
import { CategoryEditorComponent } from './category.editor.component';

const routes: Routes = [
  {
    path: 'list',
    component: CategoryListComponent,
    data: {
      title: 'Danh sách danh mục sản phẩm'
    }
  },
  {
    path: 'edit/:id',
    component: CategoryEditorComponent,
    data: {
      title: 'Sửa danh mục sản phẩm'
    }
  },
  {
    path: 'create',
    component: CategoryEditorComponent,
    data: {
      title: 'Tạo danh mục sản phẩm'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ManageCategoryRoutingModule { }
