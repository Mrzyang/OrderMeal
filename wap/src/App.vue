<template>
  <div class="app-container">
    <mt-header title="水高订餐平台">
      <mt-button  icon="back" slot="left" @click="back">返回</mt-button>
    </mt-header>
     <!--路由内容区-->
    <!--<transition>-->
      <!--<router-view/>-->
    <!--</transition>-->

    <transition>
    <keep-alive>
      <router-view v-if="$route.meta.keepAlive"></router-view>
    </keep-alive>
    </transition>

    <transition>
      <router-view v-if="!$route.meta.keepAlive"></router-view>
    </transition>

    <!--底部Tap bar-->
    <nav class="mui-bar mui-bar-tab" id="bar-tab">
      <router-link class="mui-tab-item" to="/">
        <span class="mui-icon mui-icon-home"></span>
        <span class="mui-tab-label">首页</span>
      </router-link>
      <router-link class="mui-tab-item" to="/cart">
        <span class="mui-icon mui-icon-extra mui-icon-extra-cart"><!--<span class="mui-badge">9</span>--></span>
        <span class="mui-tab-label">点餐</span>
      </router-link>
      <router-link class="mui-tab-item"  to="/order">
        <span class="mui-icon mui-icon-extra mui-icon-extra-dictionary" style="font-size: 21px;position:relative;top: 6px"></span>
        <span class="mui-tab-label">订单</span>
      </router-link>
      <router-link class="mui-tab-item"  :to="loginOrNot">
        <span class="mui-icon mui-icon-contact"></span>
        <span class="mui-tab-label">我的</span>
      </router-link>
    </nav>

    <nav class="mui-bar mui-bar-tab cart-box" id="cart-box" hidden>
      <div class="cart-container">
        <div class="cell1">
          <div class="cart-icon-box">
            <span data-v-04c2046b="" class="mui-icon mui-icon-extra mui-icon-extra-cart">
              <span data-v-04c2046b="" class="mui-badge" id="goodsNum">9</span>
            </span>
          </div>
        </div>
        <div class="cell12">
             ￥<span id="totalMoney">128</span>
        </div>
        <div class="cell2">
            <span class="mui-icon mui-icon-trash"></span>
            清空餐车
        </div>
        <div class="cell3">
          <span class="mui-icon mui-icon-checkmarkempty"></span>
           确认下单
        </div>
      </div>

    </nav>

  </div>
</template>

<script>
export default {
  name: 'App',
  data() {
    return {title: '订餐平台'}
  },
  created(){
    //由于本项目把token存储在localStorage中，只有当用户手动注销登录的时候才会清除jwt，但是jwt会有过期时间，不妨我们首次加载这个应用的时候就判断下
    //token是否有效，如果无效，则清除之
    console.log("App.vue被创建")
    this.axios.get("/user?type=getCustomerById").then(result=>{
      if(result.data.code=='403'){
        localStorage.removeItem("jwt")
      }
    })
  },
  methods: {
    back() {
      history.go(-1)
      }
    },
  computed: {
    // 计算属性的 这里的路由选择判断最后还是要改为vuex
    loginOrNot: function () {
      // `this` 指向 vm 实例
      var storageData = localStorage.getItem("jwt");
      console.log('storageData==='+storageData)
      if(storageData) {
        storageData = JSON.parse(storageData);
        let token = storageData.token,
            loginId = storageData.loginId;
        if(token) return '/mine'
        else return '/login'
      }else return '/login'
    }
  },
      watch: {
        '$route.path': function (newVal, oldVal) {
          // console.log(newVal+'----'+oldVal)
          if (newVal === '/cart') {
            //document.getElementById('cart-box').removeAttribute('hidden')
            document.getElementById('bar-tab').setAttribute('hidden', 'hidden')
          } else {
            //document.getElementById('cart-box').setAttribute('hidden', 'hidden')
            document.getElementById('bar-tab').removeAttribute('hidden')
          }
        }
      }
}
</script>

<style lang="scss" scoped>
  .app-container {
    padding-bottom: 50px;
    overflow: hidden;
    .mint-header {
      z-index: 99;
    }
  }

  // 控制 组件切换动画效果的类
  .v-enter {
    opacity: 0;
    transform: translateX(100%);
  }

  .v-leave-to {
    opacity: 0;
    transform: translateX(-100%);
    // 让即将离开的组件脱标，这样就能放置闪动的问题
    position: absolute;
  }

  .v-enter-active,
  .v-leave-active {
    transition: all 0.4s ease;
  }

  // 自定义 mui-tab-item-my 类名，解决 tabbar 和 mui.js 之间冲突的问题
  .mui-bar-tab .mui-tab-item-my.mui-active {
    color: #007aff;
  }

  .mui-bar-tab .mui-tab-item-my {
    display: table-cell;
    overflow: hidden;
    width: 1%;
    height: 50px;
    text-align: center;
    vertical-align: middle;
    white-space: nowrap;
    text-overflow: ellipsis;
    color: #929292;
  }

  .mui-bar-tab .mui-tab-item-my .mui-icon {
    top: 3px;
    width: 24px;
    height: 24px;
    padding-top: 0;
    padding-bottom: 0;
  }

  .mui-bar-tab .mui-tab-item-my .mui-icon ~ .mui-tab-label {
    font-size: 11px;
    display: block;
    overflow: hidden;
    text-overflow: ellipsis;
  }
  a:after,a:link,a:active,a:visited{
    text-decoration:none;
  }
</style>
