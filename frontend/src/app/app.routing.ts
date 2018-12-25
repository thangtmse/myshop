import { Routes, RouterModule, PreloadAllModules  } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';

import { PagesComponent } from './pages/pages.component';
import { NotFoundComponent } from './pages/not-found/not-found.component';

export const routes: Routes = [
    { 
        path: '', 
        component: PagesComponent, children: [
            { path: '', loadChildren: 'app/pages/home/home.module#HomeModule' },
            { path: 'account', loadChildren: 'app/pages/account/account.module#AccountModule', data: { breadcrumb: 'Cai dat tai khoan' } },
            { path: 'compare', loadChildren: 'app/pages/compare/compare.module#CompareModule', data: { breadcrumb: 'Compare' } },
            { path: 'wishlist', loadChildren: 'app/pages/wishlist/wishlist.module#WishlistModule', data: { breadcrumb: 'Wishlist' } },
            { path: 'cart', loadChildren: 'app/pages/cart/cart.module#CartModule', data: { breadcrumb: 'giỏ hàng' } },
            { path: 'checkout', loadChildren: 'app/pages/checkout/checkout.module#CheckoutModule', data: { breadcrumb: 'thanh toán' } },
            { path: 'contact', loadChildren: 'app/pages/contact/contact.module#ContactModule', data: { breadcrumb: 'Liên hệ' } },
            { path: 'sign-in', loadChildren: 'app/pages/sign-in/sign-in.module#SignInModule', data: { breadcrumb: 'Đăng nhập ' } },
            { path: 'brands', loadChildren: 'app/pages/brands/brands.module#BrandsModule', data: { breadcrumb: 'Brands' } },
            { path: 'products', loadChildren: 'app/pages/products/products.module#ProductsModule', data: { breadcrumb: 'Sản phẩm' } }
        ]
    },
    { path: '**', component: NotFoundComponent }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(routes, {
   preloadingStrategy: PreloadAllModules,  // <- comment this line for activate lazy load
   // useHash: true
});