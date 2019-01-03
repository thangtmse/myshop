import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

// Import Containers
import { DefaultLayoutComponent } from './containers';

import { P404Component } from './views/error/404.component';
import { P500Component } from './views/error/500.component';
import { LoginComponent } from './views/login/login.component';
import { LogoutComponent } from './views/logout/logout.component';
import { AuthenticationService } from './service/authentication.service';
import { P403Component } from './views/error/403.component';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'manage',
    pathMatch: 'full',
  },
  {
    path: 'manage',
    component: DefaultLayoutComponent,
    canActivate: [AuthenticationService],
    canActivateChild: [AuthenticationService],
    data: {
      roles: ['ADMIN','EMPLOYEE'],
      title: 'Home'
    },
    children: [
      {
        path: '',
        redirectTo: 'orders',
        pathMatch: 'full',
      },
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
        path: 'product',
        loadChildren: './views/manager/manage-product/manage.product.module#ManageProductModule',
        data: {
          roles: ['ADMIN']
        }
      },
      {
        path: 'promotion',
        loadChildren: './views/manager/manage-promotion/manage.promotion.module#ManagePromotionModule',
        data: {
          roles: ['ADMIN']
        }
      },
      {
        path: 'orders',
        loadChildren: './views/order/order.module#OrderModule',
        data: {
          roles: ['EMPLOYEE','ADMIN']
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
