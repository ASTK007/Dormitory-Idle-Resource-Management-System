import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    meta: { title: '后台登录', icon: 'dashboard' },
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    redirect: '/home',
    hidden: true
  },


  //前台
  {
    path: '/home',
    component: () => import('@/views/home/index'),
    meta: { title: '首页' },
    hidden: true
  },
  {
    path: '/goods',
    component: () => import('@/views/goods/index'),
    meta: { title: '商品详情' },
    hidden: true
  },
  {
    path: '/car',
    component: () => import('@/views/car/index'),
    meta: { title: '购物车' },
    hidden: true
  },
  {
    path: '/my',
    component: () => import('@/views/my/index'),
    meta: { title: '个人中心' },
    hidden: true
  },

  //后台
  {
    path: '/admin',
    component: Layout,
    redirect: '/admin/home',
    children: [
      {
        path: 'home',
        component: () => import('@/views/admin/home/index'),
        meta: { title: '后台首页' },
      },
      {
        path: 'user',
        component: () => import('@/views/admin/user/index'),
        meta: { title: '用户管理' }
      },
      {
        path: 'comment',
        component: () => import('@/views/admin/comment/index'),
        meta: { title: '评论管理' }
      },
      {
        path: 'goods',
        component: () => import('@/views/admin/goods/index'),
        meta: { title: '商品管理' }
      },
      {
        path: 'orders',
        component: () => import('@/views/admin/orders/index'),
        meta: { title: '订单管理' }
      },
    ]
  },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
