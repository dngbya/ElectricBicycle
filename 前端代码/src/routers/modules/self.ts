/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/layout/index.vue'

const formRouter = [
  {
    path: '/self',
    component: Layout,
    redirect: '/self/total',
    name: 'self',
    //alwaysShow: true,
    meta: {
      title: '个人中心',
      icon: 'User',
    },
    children: [
        {
          path: '/self/total',
          component: () => import('@/views/self/total/index.vue'),
          name: 'selftotal',
          meta: { title: '个人中心', icon: 'User' ,affix: true, role: ['other'] },
        }
      ],
  },
]

export default formRouter
