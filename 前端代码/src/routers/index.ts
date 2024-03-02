import {
  createRouter,
  createWebHistory,
  RouteRecordRaw,
  createWebHashHistory,
  Router,
} from 'vue-router'
import Layout from '@/layout/index.vue'
// 扩展继承属性
interface extendRoute {
  hidden?: boolean
}
//
/* import tableRouter from './modules/table'
import dataScreenRouter from './modules/dataScreen'
import excelRouter from './modules/excel'
import nestedRouter from './modules/nested'
import systemRouter from './modules/system'
import echartsRouter from './modules/echarts'
import chatRouter from './modules/chat'
import othersRouter from './modules/other'
import externalLink from './modules/externalLink'
import formRouter from './modules/form'
import functionPageRouter from './modules/functionPage' */
import dataScreenRouter from './modules/dataScreen'
import userRouter from './modules/User'
import orderRouter from './modules/order'
import selfRouter from './modules/self'
import locationRouter from './modules/location'
import charging from './modules/charging'

// 异步组件
export const asyncRoutes = [
  /* ...dataScreenRouter,
  ...echartsRouter,
  ...tableRouter,
  ...formRouter,
  ...othersRouter,
  ...functionPageRouter,
  ...chatRouter,
  ...nestedRouter,
  ...excelRouter,
  ...externalLink,
  ...systemRouter, */
  //...charging,
  //...selfRouter,
  ...dataScreenRouter,
  ...userRouter,
  ...orderRouter,
  ...locationRouter
]

/**
 * path ==> 路由路径
 * name ==> 路由名称
 * component ==> 路由组件
 * redirect ==> 路由重定向
 * alwaysShow ==> 如果设置为true，将始终显示根菜单，无论其子路由长度如何
 * hidden ==> 如果“hidden:true”不会显示在侧边栏中（默认值为false）
 * keepAlive ==> 设为true 缓存
 * meta ==> 路由元信息
 * meta.title ==> 路由标题
 * meta.icon ==> 菜单icon
 * meta.affix ==> 如果设置为true将会出现在 标签栏中
 * meta.breadcrumb ==> 如果设置为false，该项将隐藏在breadcrumb中（默认值为true）
 * meta.activeMenu ==> 详情页的时候可以设置菜单高亮 ,高亮菜单的path
 */

export const constantRoutes: Array<RouteRecordRaw & extendRoute> = [
  {
    path: '/404',
    name: '404',
    component: () => import('@/views/errorPages/404.vue'),
    hidden: true,
  },
  {
    path: '/403',
    name: '403',
    component: () => import('@/views/errorPages/403.vue'),
    hidden: true,
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/login/index.vue'),
    hidden: true,
    //redirect: '/login',
    meta: { title: '登录' },
  },
  {
    path: '/',
    name: 'layout',
    component: Layout,
    redirect: '/home',
    meta: { title: '首页', icon: 'House' },
    children: [
      {
        path: '/home',
        component: () => import('@/views/home/index.vue'),
        name: 'home',
        meta: { title: '首页', icon: 'House', affix: true, role: ['other'] },
      },
    ],
  },
  {
    path: '/charging',
    component: Layout,
    redirect: '/charging/page',
    name: 'charging',
    //alwaysShow: true,
    meta: {
      title: '地点',
      icon: 'LocationInformation',
    },
    children: [
        {
          path: '/charging/page',
          component: () => import('@/views/charging/page/index.vue'),
          name: 'chargingPage',
          meta: { title: '地点', icon: 'LocationInformation' ,affix: true, role: ['other'] },
        }
      ],
  },
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

/**
 * notFoundRouter(找不到路由)
 */
export const notFoundRouter = {
  path: '/:pathMatch(.*)',
  name: 'notFound',
  redirect: '/404',
}

const router = createRouter({
  // history: createWebHistory(process.env.BASE_URL), // history
  history: createWebHashHistory(), // hash
  routes: constantRoutes,
})

export default router
