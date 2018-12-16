import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

// Import Containers
import { DefaultLayoutComponent, CustomerLayoutComponent } from './containers';

import { P404Component } from './views/error/404.component';
import { P500Component } from './views/error/500.component';
import { LoginComponent } from './views/login/login.component';
import { RegisterComponent } from './views/register/register.component';
import { CustomerProfileComponent } from './views/customer-profile/customer.profile.component';
import { LogoutComponent } from './views/logout/logout.component';
import { ManageFoodComponent } from './views/manager/manage-food/manage.food.component';
import { AboutusComponent } from './views/aboutus/aboutus.component';
import { OrderStatusComponent } from './views/chef/order.status.component';
import { AuthenticationService } from './service/authentication.service';
import { P403Component } from './views/error/403.component';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full',
  },
  {
    path: 'manage',
    component: DefaultLayoutComponent,
    canActivate: [AuthenticationService],
    canActivateChild: [AuthenticationService],
    data: {
      title: 'Home'
    },
    children: [
      {
        path: 'dashboard',
        loadChildren: './views/manager/dashboard/dashboard.module#DashboardModule',
        data: {
        roles: ['ADMIN']
        }
      },
      {
        path: 'user',
        loadChildren: './views/manager/manage-user/manage.user.module#ManageUserModule',
        data: {
          roles: ['ADMIN']
        }
      },
      {
        path: 'category',
        loadChildren: './views/manager/manage-category/manage.category.module#ManageCategoryModule',
        data: {
          roles: ['ADMIN']
        }
      },
      {
        path: 'supplier',
        loadChildren: './views/manager/manage-supplier/manage.supplier.module#ManageSupplierModule',
        data: {
          roles: ['ADMIN']
        }
      },
      {
        path: 'food',
        loadChildren: './views/manager/manage-food/manage.food.module#ManageFoodModule',
        data: {
          roles: ['ADMIN']
        }
      },
      {
        path: 'table',
        loadChildren: './views/manager/manage-table/manage.table.module#ManageTableModule',
        data: {
          roles: ['ADMIN']
        }
      },
      {
        path: 'view-available-table',
        loadChildren: './views/waiter/view-available-table/manage.available_table.module#ManageAvailableTableModule',
        data: {
          roles: ['WAITER','CUSTOMER']
        }
      },
      {
        path: 'manage-table-booking',
        loadChildren: './views/waiter/manage-table-booking/manage.table_booking.module#ManageTableBookingModule',
        data: {
          roles: ['ADMIN','CUSTOMER']
        }
      },
      {
        path: 'order',
        loadChildren: './views/waiter/manage-order/manage.order.module#ManageOrderModule',
        data: {
          roles: ['ADMIN','CUSTOMER']
        }
      },
      {
        path: 'cashier',
        loadChildren: './views/cashier/cashier.module#CashierModule',
        data: {
          roles: ['EMPLOYEE','ADMIN']
        }
      },
      {
        path: 'chef',
        component: OrderStatusComponent,
        data: {
          title: 'Danh sách trạng thái sản phẩm',
          roles: ['WAITER','CHEF']
        }
      }
    ]
  },
  {
    path: '403',
    component: P403Component,
    data: {
      title: 'Page 403'
    }
  },
  {
    path: '404',
    component: P404Component,
    data: {
      title: 'Page 404'
    }
  },
  {
    path: '500',
    component: P500Component,
    data: {
      title: 'Page 500'
    }
  },
  {
    path: 'login',
    component: LoginComponent,
    data: {
      title: 'Đăng nhập'
    }
  },
  {
    path: 'logout',
    component: LogoutComponent,
    data: {
      title: 'Đăng xuất'
    }
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
