<template>
  <div class="mui-content">
    <div class="mui-card">
      <form class="mui-input-group">
        <div class="mui-input-row">
          <label>电话号码</label>
          <input type="text" v-model="customerPhone">
        </div>
        <br/>
        <div class="mui-input-row">
          <label>新密码</label>
          <input type="password" placeholder="请输入新密码" v-model="newPassword">
        </div>

        <br/>

      </form>
      <button type="button" class="mui-btn mui-btn-block mui-btn-success" @click="modify">确认修改</button>
    </div>
  </div>
</template>

<script>
  import {Toast} from 'mint-ui'
    export default {
      name: "ModifyPwd",
      data(){
        return {
          customerPhone: null,
          newPassword: null
        }
      },
      created(){
        this.axios.get("/user?type=getCustomerById").then(result=>{
          if(result){
            var customer=result.data            //这里返回的json Vue会自动转化为js对象
            this.newPassword=customer.customerPassword;
            this.customerPhone=customer.customerPhone;
          }
        })
      },
      methods:{
        modify(){
          if(!this.newPassword){
            Toast("请输入新密码");
            return;
          }

          let param = new URLSearchParams()
          param.append('type', 'modifyInfo')
          param.append('customerPhone', this.customerPhone)
          param.append('newPassword', this.newPassword)

          this.axios.post("/user",param).then(response =>{
            console.log(response.data);
            var data=response.data;    //data={ status:'' , msg:''}
            //判断
            if(data.status==1){
              Toast(data.msg)
              window.location.reload()
              this.$router.push('/info')
            }else{
              Toast(data.msg)
            }
          })

        }
      }

    }
</script>

<style scoped>

</style>
