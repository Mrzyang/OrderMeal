<template>
  <div class="app-container">
    <ul class="mui-table-view mui-table-view-chevron">
      <li class="mui-table-view-cell mui-media" v-for="item in orders">
        <router-link class="mui-navigate-right" :to="'/orderDetail/'+item.orderId">
          <div class="mui-row">
          <div class="mui-col-xs-3" style="padding: 0 2% 0 0">
            <img  class="head-img" :src="website+item.goods.productPath">
          </div>
          <div class="mui-media-body mui-col-xs-9" style="margin: 0;padding: 0">
            {{item.productName.split(',')[0]}}+{{item.productName.split(',')[1]}}
            <span style="float: right">￥ <span class="price">{{item.goodsPrice}}</span></span>
            <p class='mui-ellipsis'>
              {{item.productName.split(',')[2]? item.productName.split(',')[2]:''}}
              {{item.productName.split(',')[3]?'+'+item.productName.split(',')[3]:''}}
              {{item.productName.split(',')[4]?'+'+item.productName.split(',')[4]:''}}
            </p>
            <p class="orderTime">
              {{item.orderDate}}  <span class="orderStatus">{{transformStatus(item.orderStatus)}}</span>
            </p>
          </div>
          </div>
        </router-link>
      </li>
    </ul>
  </div>
</template>

<script>
  import {Toast} from 'mint-ui'
  export default {
    name: "Order",
    data(){
      return {
        orders: [],
        website: 'http://localhost:8080/'
      }
    },
    created(){
      //从localStorage中获取schoolId
      var storageData = localStorage.getItem("jwt");
      if(!storageData) {
        Toast("请先登录!")
        this.$router.push('/login')
      }else {
        //获取Orders的ajax请求
        this.axios.get("/orders?type=getAll").then(result => {
          this.orders = result.data;
          console.log(this.orders)
        })
      }
    },
    methods:{
      transformStatus(status){
        switch (status){
          case 0: return '订单取消';break;
          case 1: return '下单';break;
          case 2: return '已支付';break;
          case 3: return '订单完成';break;
          default: return '订单错误';break;
        }
      }
    }
  }
</script>

<style scoped>
  .app-container{

  }
  .mui-media-body{
    font-size: 14px;
  }
  .mui-ellipsis{
    font-size: 12px;
  }
  .price{
  color: red;
  font-size: 18px;
  font-weight: bold;
}
  a:after,a:link,a:active,a:visited{
    text-decoration:none;
  }
  .orderTime{
    color: #c8cbcf;
    font-size: 12px;
  }
  .head-img{
    width: 60px;
    height: 58px;
  }
  .orderStatus{
    display: inline-block;
    float: right;
    color: cornflowerblue;
  }
</style>
