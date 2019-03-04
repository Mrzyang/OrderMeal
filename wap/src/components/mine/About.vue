<template>
  <div class="app-container">
    <div v-html="aboutContent"></div>
  </div>
</template>

<script>
    export default {
        name: "About",
      data(){
          return{
            aboutContent: null
          }
      },
      created(){
        this.axios.get("/about").then(result=>{
          console.log(result)
          if(result){
           //这里返回的json Vue会自动转化为js对象
            this.aboutContent=result.data;
          }
        })
      }
    }
</script>

<style scoped>
.app-container{
  background-color: white;
  padding: 5px 10px;
  font: 14px fangsong;
  color: #8f8f94;
  font-family: 'Helvetica Neue', Helvetica, sans-serif;
}
</style>
