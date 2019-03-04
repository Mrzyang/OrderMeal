<template>
  <div class="app-container">
    <div class="mui-card">
      <ul class="mui-table-view">
        <li class="mui-table-view-cell advicetitle">您的建议将是我们前进的动力!</li>
        <li class="mui-table-view-cell"></li>
      </ul>
    <form class="mui-input-group">
      <textarea id="textarea" rows="10" placeholder="请留下您宝贵的建议。" v-model="advice"></textarea>
      <br/>
      <button type="button" class="mui-btn mui-btn-block mui-btn-primary" @click="submitAdvice">提交</button>
    </form>
    </div>
  </div>
</template>

<script>
  import {Toast} from 'mint-ui'
    export default {
      name: "Advice",
      data(){
          return{
            advice: null
          }
      },
      methods:{
        submitAdvice(){
          this.axios.get("/advice?message="+this.advice).then(result=>{
            if(result.data.status==1){
              Toast("投诉成功！")
              this.$router.push('/mine')
            }else {
              Toast("投诉失败！")
            }
          })
        }
      }
    }
</script>

<style scoped>
.advicetitle{
  /*padding-left: 41px;*/
  font-size: 17px;
  font-weight: bold;
  text-align: center;
}
</style>
