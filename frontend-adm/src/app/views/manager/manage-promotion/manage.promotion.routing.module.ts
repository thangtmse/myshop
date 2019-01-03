import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PromotionListComponent } from './promotion.list.component';
import { EditPromotionComponent } from './promotion.edit.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'list',
    pathMatch: 'full',
  },
  {
    path: '',
    component: PromotionListComponent,
    data: {
      title: 'Danh sách giảm giá'
    }
  },
  {
    path: 'edit/:id',
    component: EditPromotionComponent,
    data: {
      title: 'Sửa thông tin giảm giá'
    }
  },
  {
    path: 'create',
    component: EditPromotionComponent,
    data: {
      title: 'Tạo sự kiện giảm giá'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ManagePromotionRoutingModule { }
