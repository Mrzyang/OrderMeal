<template>
    <div class="app-container">
      <div id="content" v-html="newsContent">

      </div>
      </div>
</template>

<script>
    export default {
      name: "Notice",
      data(){
          return {
            newsId: this.$route.params.newsId,
            newsContent: null
          }
      },
      created(){
        //获取News的ajax请求
        this.axios.get("/news?type=getNewsById&newsId="+this.newsId).then(result=>{
          this.newsContent=result.data.newsContent;
        })
      }
    }
</script>

<style scoped>
  .app-container{
    padding: 5px 10px;
  }
  h4{
    text-align: center;
  }
</style>
