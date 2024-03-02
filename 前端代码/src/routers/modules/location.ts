/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/layout/index.vue'

const formRouter = [
  {
    path: '/location',
    component: Layout,
    redirect: '/location/listParking',
    name: 'location',
    alwaysShow: true,
    meta: {
      title: '地点管理',
      icon: 'Location',
    },
    children: [
      {
        path: '/location/listParking',
        component: () => import('@/views/location/listParking/index.vue'),
        name: 'listParking',
        meta: { title: '停车场信息', keepAlive: true, icon: 'MenuIcon' },
      },
      {
        path: '/location/listCharging',
        component: () => import('@/views/location/listCharge/index.vue'),
        name: 'listCharge',
        meta: { title: '充电点管理', keepAlive: true, icon: 'MenuIcon' },
      },
      {
        path: '/location/listChargingPile',
        component: () => import('@/views/location/listChargingPile/index.vue'),
        name: 'listChargingPile',
        meta: { title: '充电桩管理', keepAlive: true, icon: 'MenuIcon' },
      },
    ],
  },
]

export default formRouter
