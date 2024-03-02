/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/layout/index.vue'

const formRouter = [
  {
    path: '/charging',
    component: Layout,
    redirect: '/charging/page',
    name: 'charging',
    //alwaysShow: true,
    meta: {
      title: '充电点',
      icon: 'User',
    },
    children: [
        {
          path: '/charging/page',
          component: () => import('@/views/charging/page/index.vue'),
          name: 'chargingPage',
          meta: { title: '个充电点', icon: 'User' ,affix: true, role: ['other'] },
        }
      ],
  },
]

export default formRouter
