<template>
  <div class="mui-page-content">
      <div>
        <ul class="mui-table-view mui-table-view-chevron">
          <li class="mui-table-view-cell mui-media">
            <router-link class="mui-navigate-right" to="/info">
              <img class="mui-media-object mui-pull-left head-img" id="head-img" src="../assets/images/photo.jpg">
              <div class="mui-media-body">
                测试用户
                <p class='mui-ellipsis'>账号: Hello</p>
              </div>
            </router-link>
          </li>
        </ul>
        <br/>
        <ul class="mui-table-view mui-table-view-chevron">
          <li class="mui-table-view-cell">
            <router-link to="wallet" class="mui-navigate-right" >我的钱包</router-link>
          </li>
        </ul>

        <br/>
        <ul class="mui-table-view mui-table-view-chevron">
          <li class="mui-table-view-cell">
            <a  class="mui-navigate-right">新消息通知</a>
          </li>
          <li class="mui-table-view-cell">
            <a  class="mui-navigate-right">通用</a>
          </li>
          <li class="mui-table-view-cell">
            <router-link  class="mui-navigate-right" to="advice">建议反馈</router-link>
          </li>
        </ul>
        <ul class="mui-table-view mui-table-view-chevron">
          <li class="mui-table-view-cell">
            <router-link  class="mui-navigate-right" to="about">关于点餐平台 <i class="mui-pull-right update">V1.0</i></router-link>
          </li>
        </ul>
        <br/><br/><br/>
        <button class="mui-btn mui-btn-danger mui-btn-block quit" @click="logout">退出登录</button>
      </div>
  </div>

</template>

<script>
  import {Toast} from 'mint-ui'
    export default {
        name: "Mine",
      created(){
        //从localStorage中获取schoolId
        var storageData = localStorage.getItem("jwt");
        if(!storageData) {
          Toast("请先登录!")
          this.$router.push('/login')
        }
      },
      methods:{
          logout(){
            //localStorage.setItem('jwt',null)
            localStorage.removeItem("jwt")
            Toast("退出成功！")
            location.reload()
            this.$router.push('/login')
          }
      }
    }
</script>

<style scoped>
.mui-page-content{
  background-color: #efeff4;
}
  .quit{
    margin: 0 auto;
    width: 95%;

  }

a:after,a:link,a:active,a:visited{
  text-decoration:none;
}
</style>
