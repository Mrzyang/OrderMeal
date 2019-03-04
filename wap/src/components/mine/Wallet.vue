<template>
    <div class="mui-content">
      <div class="mui-card">
        <ul class="mui-table-view">
          <li class="mui-table-view-cell">余额 <span class="spanright">{{customerAccount}}</span></li>
          <li class="mui-table-view-cell">充值方式 <span class="mui-icon mui-icon-weixin spanright"></span></li>
        </ul>
        <form class="mui-input-group">
          <div class="mui-input-row">
            <label>充值金额</label>
            <input type="text" placeholder="请输入整数">
          </div>
          <button type="button" class="mui-btn mui-btn-block mui-btn-success">充值</button>
        </form>
      </div>
      </div>
</template>

<script>
    export default {
        name: "Wallet",
      data(){
        return{
          customerAccount: null
        }
      },
      created(){
        this.axios.get("/user?type=getCustomerById").then(result=>{
          console.log(result)
          if(result){
            var customer=result.data            //这里返回的json Vue会自动转化为js对象
            this.customerAccount=customer.customerAccount;
          }
        })
      }
    }
</script>

<style scoped>
.spanright{
  display: inline-block;
  float: right;
}
</style>
