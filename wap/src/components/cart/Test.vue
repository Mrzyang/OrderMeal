<template>
  <transition>
    <div class="app-container">

      {{msg}} <br/>
      <button @click="change">点我</button>

    </div>
  </transition>
</template>

<script>
    export default {
        name: "Test",
        data(){
          return {
            msg: 'hello world'
          }
        },
      methods:{
          change(){
            this.msg+="你好世界！"
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
</style>
