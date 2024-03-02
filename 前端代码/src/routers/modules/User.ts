/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/layout/index.vue'

const formRouter = [
  {
    path: '/user',
    component: Layout,
    redirect: '/user/listUser',
    name: 'user',
    alwaysShow: true,
    meta: {
      title: '用户管理',
      icon: 'User',
    },
    children: [
      {
        path: '/user/listUser',
        component: () => import('@/views/user/listUser/index.vue'),
        name: 'listUser',
        meta: { title: '用户信息', keepAlive: true, icon: 'MenuIcon' },
      },
      {
        path: '/user/listCar',
        component: () => import('@/views/user/listCar/index.vue'),
        name: 'listCar',
        meta: { title: '车辆信息', keepAlive: true, icon: 'MenuIcon' },
      },
      {
        path: '/user/listDept',
        component: () => import('@/views/user/listDept/index.vue'),
        name: 'listDept',
        meta: { title: '角色管理', keepAlive: true, icon: 'MenuIcon' },
      },
    ],
  },
]

export default formRouter
