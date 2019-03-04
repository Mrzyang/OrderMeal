import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/Index'
import Order from '@/components/Order'
import Cart from '@/components/Cart'
import Mine from '@/components/Mine'
import Login from '@/components/mine/Login'
import Notice from '@/components/Home/Notice'
import Wallet from '@/components/Mine/Wallet'
import Info from '@/components/Mine/Info'
import ModifyPwd from '@/components/Mine/ModifyPwd'
import Advice from '@/components/Mine/Advice'
import About from '@/components/Mine/About'
import Test from '@/components/Cart/Test'
import OrderDetail from '@/components/order/OrderDetail'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Index',
      component: Index,
      meta: {
        keepAlive: true // 需要缓存
      }
    },
    {
      path: '/order',
      name: 'Order',
      component: Order,
      meta: {
        keepAlive: false // 需要缓存,下单后记得刷新下当前页面
      }
    },
    {
      path: '/cart',
      name: 'Cart',
      component: Cart,
      meta: {
        keepAlive: false // 不需要缓存
      }
    },
    {
      path: '/mine',
      name: 'Mine',
      component: Mine,
      meta: {
        keepAlive: true // 需要缓存
      }
    },
    {
      path: '/login',
      name: 'Login',
      component: Login,
      meta: {
        keepAlive: true // 需要缓存
      }
    },
    {
      path: '/notice/:newsId',
      name: 'Notice',
      component: Notice,
      meta: {
        keepAlive: false // 不需要缓存,每个notice显示的内容不同
      }
    },
    {
      path: '/wallet',
      name: 'Wallet',
      component: Wallet,
      meta: {
        keepAlive: false // 不需要缓存
      }
    },
    {
      path: '/info',
      name: 'Info',
      component: Info,
      meta: {
        keepAlive: true // 需要缓存
      }
    },
    {
      path: '/modifypwd',
      name: 'ModifyPwd',
      component: ModifyPwd,
      meta: {
        keepAlive: true // 需要缓存，修改完信息后记得刷新下当前页面
      }
    },
    {
      path: '/advice',
      name: 'Advice',
      component: Advice,
      meta: {
        keepAlive: true // 需要缓存
      }
    },
    {
      path: '/about',
      name: 'About',
      component: About,
      meta: {
        keepAlive: true // 需要缓存
      }
    },
    {
      path: '/test',
      name: 'Test',
      component: Test,
      meta: {
        keepAlive: false // 不需要缓存
      }
    },
    {
      path: '/orderDetail/:orderId',
      name: 'OrderDetail',
      component: OrderDetail,
      meta: {
        keepAlive: false // 不需要缓存
      }
    }
  ],
  linkActiveClass: 'mui-active'
})
