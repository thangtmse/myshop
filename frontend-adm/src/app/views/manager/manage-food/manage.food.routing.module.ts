import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ManageFoodComponent } from './manage.food.component';
import { FoodEditorComponent } from './food.editor.component';

const routes: Routes = [
  {
    path: 'list',
    component: ManageFoodComponent,
    data: {
      title: 'Danh sách sản phẩm'
    }
  },
  {
    path: 'edit/:id',
    component: FoodEditorComponent,
    data: {
      title: 'Cập nhật sản phẩm'
    }
  },
  {
    path: 'create',
    component: FoodEditorComponent,
    data: {
      title: 'Tạo sản phẩm'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ManageFoodRoutingModule { }
