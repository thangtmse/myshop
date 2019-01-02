export const navItems = [
  {
    name: 'Thống kê',
    url: '/manage/dashboard',
    icon: 'icon-speedometer',
    roles: ['ADMIN'],
  },
  {
    name: 'Tài khoản',
    url: '/manage/user',
    icon: 'icon-puzzle',
    roles: ['ADMIN'],
    children: [
      {
        name: 'Danh sách',
        url: '/manage/user/list',
        icon: 'icon-people'
      },
      {
        name: 'Thêm mới',
        url: '/manage/user/create',
        icon: 'icon-user-follow'
      }
    ]
  },
  {
    name: 'Quản lý giảm giá',
    url: '/manage/promotion',
    icon: 'icon-puzzle',
    roles: ['ADMIN'],
    children: [
      {
        name: 'Danh sách giảm giá',
        url: '/manage/promotion',
        icon: 'icon-people'
      },
      {
        name: 'Tạo sự kiện giảm giá',
        url: '/manage/promotion/create',
        icon: 'icon-table-follow'
      }
    ]
  },
  {
    name: 'Quản lý danh mục sản phẩm',
    url: '/manage/category',
    icon: 'icon-puzzle',
    roles: ['ADMIN'],
    children: [
      {
        name: 'Danh sách danh mục',
        url: '/manage/category/list',
        icon: 'icon-docs'
      },
      {
        name: 'Tạo danh mục',
        url: '/manage/category/create',
        icon: 'icon-doc'
      }
    ]
  },
  {
    name: 'Quản lý NCC',
    url: '/manage/supplier',
    icon: 'icon-puzzle',
    roles: ['ADMIN'],
    children: [
      {
        name: 'Danh sách NCC',
        url: '/manage/supplier/list',
        icon: 'icon-docs'
      },
      {
        name: 'Tạo NCC',
        url: '/manage/supplier/create',
        icon: 'icon-doc'
      }
    ]
  },
  {
    name: 'Quản lý sản phẩm',
    url: '/manage/product',
    icon: 'icon-puzzle',
    roles: ['ADMIN'],
    children: [
      {
        name: 'Danh sách sản phẩm',
        url: '/manage/product/list',
        icon: 'icon-docs'
      },
      {
        name: 'Tạo sản phẩm',
        url: '/manage/product/create',
        icon: 'icon-doc'
      },
      {
        name: 'Danh sách đã xóa',
        url:'/manage/product/listdeleted',
        icon: 'icon-docs'
      }
    ]
  },
  {
    name: 'Danh sách bàn trống',
    url: '/manage/view-available-table',
    icon: 'icon-cursor',
    roles: ['CUSTOMER','WAITER']
  },
  {
    name: 'Quản lý đặt bàn',
    url: '/manage/manage-table-booking',
    icon: 'cui-list',
    roles: ['CUSTOMER','WAITER']
  },
  {
    name: 'Quản lí hóa đơn',
    url: '/manage/orders',
    icon: 'icon-paypal',
    roles: ['ADMIN', 'EMPLOYEE']
  }
];
