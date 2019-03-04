<template>
    <div class="mui-content">
      <div class="mui-card">
        <ul class="mui-table-view">
          <li class="mui-table-view-cell">姓名<span class="spanright">{{customerName}}</span></li>
          <li class="mui-table-view-cell">学工号<span class="spanright">{{customerNum}}</span></li>
          <li class="mui-table-view-cell">身份<span class="spanright">{{customerType==0?'学生':'老师'}}</span></li>
          <li class="mui-table-view-cell">班级<span class="spanright">{{grade}}{{customerClass}}班</span></li>
          <li class="mui-table-view-cell">学校<span class="spanright">{{schoolName}}</span></li>
          <li class="mui-table-view-cell">手机号<span class="spanright">{{customerPhone}}</span></li>
          <li class="mui-table-view-cell"></li>
        </ul>
        <router-link type="button" class="mui-btn mui-btn-block mui-btn-warning" to="ModifyPwd">修改信息</router-link>
      </div>
    </div>
</template>

<script>
    export default {
        name: "Info",
        data(){
          return {
            schoolName: null,
            customerNum: null,
            customerGrade: null,
            customerClass: null,
            customerName: null,
            customerAccount: null,
            customerType: null,
            customerPhone: null
          }
        },
       created(){
          //由于所有的信息都已经保存在localStorage中了，不妨我们直接从中，取涉信息修改后我们再重载一次页面就好了(刷新)
         this.axios.get("/user?type=getCustomerById").then(result=>{
           if(result){
             var customer=result.data            //这里返回的json Vue会自动转化为js对象
             this.schoolName=customer.schoolName;
             this.customerNum=customer.customerNum;
             this.customerGrade=customer.customerGrade;
             this.customerClass=customer.customerClass;
             this.customerName=customer.customerName;
             this.customerAccount=customer.customerAccount;
             this.customerType=customer.customerType;
             this.customerPhone=customer.customerPhone;
           }
         })
      },
      computed:{
        grade:function () {
          switch (this.customerGrade){
            case '1': return '一年级';break;
            case '2': return '二年级';break;
            case '3': return '三年级';break;
            default:return '错误';
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
</style>
