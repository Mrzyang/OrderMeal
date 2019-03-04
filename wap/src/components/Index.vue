<template>
  <div>
    <!--轮播图-->
 <mt-swipe :auto="4000">
   <mt-swipe-item v-for="item in lunbotuList" :key="item.url">
     <img :src="item.img" alt=""/>
   </mt-swipe-item>
 </mt-swipe>

    <!--平台公告-->
    <div class="annoBox">
      <img src="../assets/images/laba.gif" alt=""/> 平台公告!
    </div>
    <ul class="mui-table-view">
      <li v-for="item in newsList" :key="item.newsId" class="mui-table-view-cell">
        <router-link  :to="'/notice/'+item.newsId">{{item.newsTitle}}</router-link>
      </li>
    </ul>
  </div>
</template>

<script>
  import {Toast} from 'mint-ui'
export default {
  name: 'Index',
  data () {
    return {
      lunbotuList: [
        {url: 'https://s1.st.meishij.net/rs/50/123/6030800/n6030800_152708155351112.jpg',img: 'https://s1.st.meishij.net/rs/50/123/6030800/n6030800_152708155351112.jpg'},
        {url: 'https://s1.st.meishij.net/rs/105/225/1306355/n1306355_150185137138378.jpg',img: 'https://s1.st.meishij.net/rs/105/225/1306355/n1306355_150185137138378.jpg'},
        {url: 'https://s1.st.meishij.net/rs/106/232/3995606/n3995606_152654133043680.jpg',img: 'https://s1.st.meishij.net/rs/106/232/3995606/n3995606_152654133043680.jpg'}
      ], //保存轮播图的数组
      newsList:null
    }
  },
  created(){
    this.getLunbotu()
  },
  methods:{
    getLunbotu(){
      //获取News的ajax请求
      this.axios.get("/news?type=getAll").then(result=>{
        this.newsList=result.data;
       })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style  scoped>
.mint-swipe {
  height: 200px;
}
.mint-swipe-item{
  background-color: pink;
}
.mint-swipe-item img{
  width: 100%;
  height: 100%;
}
  .mui-table-view-cell{
    font-size: 14px;
  }
 .annoBox{
   padding: 5px 17px;
   font: 11px red kaiti;
   color: red;
 }

a:after,a:link,a:active,a:visited{
  text-decoration:none;
}
</style>
