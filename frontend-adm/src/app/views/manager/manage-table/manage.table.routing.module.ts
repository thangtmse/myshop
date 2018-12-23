import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TableListComponent } from './table.list.component';
import { EditTableComponent } from './table.edit.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'list',
    pathMatch: 'full',
  },
  {
    path: '',
    component: TableListComponent,
    data: {
      title: 'Danh sách giảm giá'
    }
  },
  {
    path: 'edit/:id',
    component: EditTableComponent,
    data: {
      title: 'Sửa thông tin giảm giá'
    }
  },
  {
    path: 'create',
    component: EditTableComponent,
    data: {
      title: 'Tạo sự kiện giảm giá'
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ManageTableRoutingModule { }
