<template>
  <div class="mui-content">
    <div class="mui-card">
      <img :src="website+imgPath" alt=""/>
      <ul class="mui-table-view" v-if="order">
        <!--<li class="mui-table-view-cell mui-media">-->
            <!--&lt;!&ndash;<img class="mui-media-object mui-pull-left head-img" id="head-img" :src="website+order.goods.productPath">&ndash;&gt;-->
            <!--<div class="mui-media-body">-->
              <!--{{order.productName.split(',')[0]}}+{{order.productName.split(',')[1]}}-->
              <!--<p class='mui-ellipsis'>-->
                <!--{{order.productName.split(',')[2]? order.productName.split(',')[2]:''}}-->
                <!--{{order.productName.split(',')[3]?'+'+order.productName.split(',')[3]:''}}-->
                <!--{{order.productName.split(',')[4]?'+'+order.productName.split(',')[4]:''}}-->
              <!--</p>-->
            <!--</div>-->
        <!--</li>-->
        <li class="mui-table-view-cell">套餐<span class="spanright">{{order.productName}}</span></li>
        <li class="mui-table-view-cell">套餐价格<span class="spanright">{{order.goodsPrice}}元</span></li>
        <li class="mui-table-view-cell">下单时间<span class="spanright">{{order.orderDate}}</span></li>
        <li class="mui-table-view-cell">配送日期<span class="spanright">{{order.orderDelivery}}</span></li>
        <li class="mui-table-view-cell">就餐时间<span class="spanright">{{order.deliveryDate==1 ? '中午': '晚上'}}</span></li>
        <li class="mui-table-view-cell">订单状态<span class="spanright orderStatus">{{transformStatus(order.orderStatus)}}</span></li>
        <li class="mui-table-view-cell"></li>
      </ul>
      <button type="button" id="orderStatus" class="mui-btn mui-btn-block mui-btn-warning" @click="cancleOrder">取消订单</button>
    </div>
  </div>
</template>

<script>
  import { MessageBox } from 'mint-ui';
  import {Toast} from 'mint-ui';
    export default {
        name: "orderDetail",
      data(){
          return {
            orderId: this.$route.params.orderId,
            order: {},
            imgPath: '',
            orderStatus: null,
            website: 'http://localhost:8080/'
          }
      },
      created(){
        //获取某一个order的ajax请求
        this.axios.get("/orders?type=getOne&orderId="+this.orderId).then(result=>{
          this.order=result.data.orderInfo;
          console.log(this.order)
           this.orderStatus=this.order.orderStatus;
          this.imgPath=result.data.imgPath;
          console.log('图片地址是:'+this.imgPath)
          console.log("goods="+this.goods)
          console.log()
        })
      },
      methods:{
        cancleOrder(){
          MessageBox.confirm('确定执行此操作?').then(action => {
            this.axios.get("/orders?type=cancel&orderId="+this.orderId+"&goodsPrice="+this.order.goodsPrice).then(result=>{
              var data=result.data;
              console.log(data);
              if(data.status==1){
                Toast('取消成功');
                this.orderStatus=0;
              }else{

              }
            })
          });
        },
        transformStatus(status){
          switch (status){
            case 0: return '订单取消';break;
            case 1: return '下单';break;
            case 2: return '已支付';break;
            case 3: return '订单完成';break;
            default: return '订单错误';break;
          }
        }
      },
      watch:{
          orderStatus: function (newValue, oldValue) {    //0--取消  1--已支付
            console.log("订单状态="+this.order.orderStatus)
            if(newValue==0){
              document.getElementById('orderStatus').setAttribute('hidden', 'hidden')
            }else {
              document.getElementById('orderStatus').removeAttribute('hidden')
            }
          }
      }
    }
</script>

<style scoped>
.spanright{
  display: inline-block;
  float: right;
}
  .orderStatus{
    font-weight: bold;
    color: red;
  }
</style>
