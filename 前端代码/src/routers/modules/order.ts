/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/layout/index.vue'

const formRouter = [
  {
    path: '/order',
    component: Layout,
    redirect: '/order/listCharging',
    name: 'order',
    alwaysShow: true,
    meta: {
      title: '订单管理',
      icon: 'Notebook',
    },
    children: [
      {
        path: '/order/listCharging',
        component: () => import('@/views/order/listCharging/index.vue'),
        name: 'listCharging',
        meta: { title: '充电订单', keepAlive: true, icon: 'MenuIcon' },
      },
      {
        path: '/order/listTopup',
        component: () => import('@/views/order/listTopup/index.vue'),
        name: 'listTopup',
        meta: { title: '充值订单', keepAlive: true, icon: 'MenuIcon' },
      },
      {
        path: '/order/listBreak',
        component: () => import('@/views/order/listBreak/index.vue'),
        name: 'listBreak',
        meta: { title: '维修订单', keepAlive: true, icon: 'MenuIcon' },
      },
    ],
  },
]

export default formRouter
