<template>
  <div class="mui-content">
    <div><img src="../assets/images/logo.jpg" alt="" class="logo"/></div>
    <div class="mui-row">
    <div class="mui-col-xs-3" style="border-right: 1px solid #c8c7cc">
      <div id="segmentedControls" class="mui-segmented-control mui-segmented-control-inverted mui-segmented-control-vertical">
        <a class="mui-control-item weekday-active" @click.prevent="weekday($event,1)">周一</a>
        <a class="mui-control-item" @click.prevent="weekday($event,2)">周二</a>
        <a class="mui-control-item" @click.prevent="weekday($event,3)">周三</a>
        <a class="mui-control-item" @click.prevent="weekday($event,4)">周四</a>
        <a class="mui-control-item" @click.prevent="weekday($event,5)">周五</a>
        <a class="mui-control-item" @click.prevent="weekday($event,6)">周六</a>
        <a class="mui-control-item" @click.prevent="weekday($event,7)">周日</a>
      </div>
    </div>
    <div id="segmentedControlContents" class="mui-col-xs-9">
      <!--START循环遍历-->
      <!--:key="item.id"-->

      <div class="itembox mui-row" v-for="(item,index) in todayMenu">
        <div class="mui-col-xs-3">
          <img :src="website+item.productPath" alt=""/>
        </div>
        <div class="mui-col-xs-9">
          <div class="itemmain">{{item.productNames.split(',')[0]}}+{{item.productNames.split(',')[1]}}</div>
          <div class="itemsub">
            {{item.productNames.split(',')[2]? item.productNames.split(',')[2]:''}}
            {{item.productNames.split(',')[3]?'+'+item.productNames.split(',')[3]:''}}
            {{item.productNames.split(',')[4]?'+'+item.productNames.split(',')[4]:''}}
          </div>
          <div class="priceBox">
            ￥<span class="price">{{item.goodsPrice}}</span>
            <div class="oper-icon">
              <span  v-bind:class="{'mui-icon': true, 'mui-icon-minus': selected[dayOfWeek][index],'mui-icon-plus': !selected[dayOfWeek][index] }" @click="addOrMinus(dayOfWeek,index)"></span>
              <!--<span  v-bind:class="{'mui-icon': true, 'mui-icon-minus': testSelected,'mui-icon-plus': !testSelected }" @click="addOrMinus($event,dayOfWeek,index)"></span>-->
            </div>
            <span class="mui-badge mui-badge-warning mealTime" v-if="index<2">午餐</span>
            <span class="mui-badge mui-badge-success mealTime" v-if="index>=2">晚餐</span>
          </div>
        </div>
      </div>

      <!--END 循环遍历-->
    </div>
  </div>

    <nav class="mui-bar mui-bar-tab cart-box" id="cart-box" v-show="navShow">
      <div class="cart-container">
        <div class="cell1">
          <div class="cart-icon-box">
            <span data-v-04c2046b="" class="mui-icon mui-icon-extra mui-icon-extra-cart">
              <span data-v-04c2046b="" class="mui-badge" id="goodsNum">{{numSelected}}</span>
            </span>
          </div>
        </div>
        <div class="cell12">
          ￥<span id="totalMoney">{{totalMoney}}</span>
        </div>
        <div class="cell2" @click="emptyCart(dayOfWeek)">
          <span class="mui-icon mui-icon-trash"></span>
          清空餐车
        </div>
        <div class="cell3" @click="purchase">
          <span class="mui-icon mui-icon-checkmarkempty"></span>
          确认下单
        </div>
      </div>
    </nav>

  </div>
</template>

<script>
  import {Toast} from 'mint-ui';
  import { MessageBox } from 'mint-ui';
    export default {
      name: "Cart",
      data() {
        return {
          msg: '订餐平台',
          weekMenu: null,    //一周的菜单   （数组)[28]
          dayMenu: null,     //每天的菜单   （数组）[8][4] 0不要   { 1 2 3 4 5 6 7}
          todayMenu: [],    //今天的菜单    （数组）[4]  中*2，晚*2       { 0 1 2 3 }
          dayOfWeek: 1,    //订星期几的菜
          selected: null,   //星期几的那一个套餐是否被选中  数组）[8][4] 0不要   { 1 2 3 4 5 6 7}
          status: null,       //是否可以订餐  0--不允许  1--允许  (规定周末才可以订餐)
          website: 'http://localhost:8080/',     //用于获取图片
          testSelected: false,
          navShow: false,
          numSelected: 0,
          totalMoney: 0
        }
      },
      methods: {
        weekday(e,day) {
          //获取本组件中定义的变量
          //console.log(this.msg)
          //vue的点击事件怎么获取当前点击的元素
          // console.log(e.target)
          var n = e.target.parentNode.firstChild;
          for (; n; n = n.nextSibling) {
            if (n.nodeType === 1 && n !== this) {
              n.classList.remove("weekday-active")
            }
            e.target.classList.add("weekday-active")
          }

          this.todayMenu=this.dayMenu[day];
          this.dayOfWeek=day;
          console.log("当前点的是星期"+this.dayOfWeek+"菜");
          console.log("今的菜单是:")
          console.log(this.todayMenu)
        },
        addOrMinus(dayOfWeek,index) {
          console.log("当前点击的坐标是:"+dayOfWeek+"  "+index);
          console.log("修改前的选中状态:"+this.selected[dayOfWeek][index]);
          if( this.selected[dayOfWeek][index]==false){
            if(index==0){
              if( this.selected[dayOfWeek][1]==true)
                this.selected[dayOfWeek][1]=false;
            }
            if(index==1){
              if( this.selected[dayOfWeek][0]==true)
                this.selected[dayOfWeek][0]=false;
            }
            if(index==2){
              if( this.selected[dayOfWeek][3]==true)
                this.selected[dayOfWeek][3]=false;
            }
            if(index==3){
              if( this.selected[dayOfWeek][2]==true)
                this.selected[dayOfWeek][2]=false;
            }
          }

          this.selected[dayOfWeek][index] =!this.selected[dayOfWeek][index];
          this.dayOfWeek=0;
          this.dayOfWeek=dayOfWeek;
          console.log("修改后的选中状态:"+this.selected[dayOfWeek][index]);
          this.goodsOfSelected()
        },
        purchase(){             //************这里记得要改过来*************
          if(this.status==0){  // status=1的时候才可以下单，(周末才可以下单),ps: 这里测试起见，临时允许工作日可以下单
            if(this.numSelected==0){   //当一个套餐都没有选中的时候不允许下单
              Toast('请至少选择一个套餐!')
            }else {
              MessageBox.prompt('请输入密码!').then(({ value, action }) => {
                var storageData = localStorage.getItem("jwt");
                storageData = JSON.parse(storageData);
                var  customerId=storageData.user.customerId;
                console.log("customerId="+customerId);
                var dayNoStr="";
                var mealTimeNoStr="";
                var goodsIdStr="";
                var customerPassword=value;

                for(var x=1;x<=7;x++){                 //7天 0 1234567  [0]不要
                  for(var y=0;y<4;y++){               //每天两餐啊 共4个套餐
                    if(this.selected[x][y]==true) {   //数组初始化为false
                      dayNoStr+=x+',';
                      mealTimeNoStr+=y+',';
                      goodsIdStr+=this.dayMenu[x][y].goodsId+',';
                    }
                  }
                }
                dayNoStr=dayNoStr.substring(0,dayNoStr.length-1);
                mealTimeNoStr=mealTimeNoStr.substring(0,mealTimeNoStr.length-1);
                goodsIdStr=goodsIdStr.substring(0,goodsIdStr.length-1);

                console.log(dayNoStr);
                console.log(mealTimeNoStr);
                console.log(goodsIdStr);

                let param = new URLSearchParams();
                param.append('customerId', customerId);     //customer的主键
                param.append('customerPassword', customerPassword);     //customer的主键
                param.append('totalMoney',this.totalMoney);
                console.log("总共费用是:"+this.totalMoney);
                param.append('goodsIdStr', goodsIdStr);    // goodsId 拼接成的字符串
                param.append('dayNoStr', dayNoStr);        // 每个字符自能取{1,2,3,4,5,6,7}   拼接的字符串
                param.append('mealTimeNoStr',mealTimeNoStr); // 每个 字符只能取{0,1,2,3}      拼接的字符串

                this.axios.post("/orderMeal",param).then(result=>{
                  var data=result.data;
                  if(data.status==1){
                    Toast('下单成功!');
                    this.$router.push('/order');
                  }else if(data.status==0){
                    Toast("账户余额不足!");
                  }else if(data.status==2){
                    Toast("密码输入有误！");
                  }else {
                    Toast('交易出错!')
                  }
                })
              });

            }
          }else{
            Toast("周末才可以下单!")
          }
        },
        emptyCart(dayOfWeek){
          for(var x=0;x<=7;x++){                 //7天 0 1234567  [0]不要
            //this.selected[x]=new Array();        //声明二维数组
            for(var y=0;y<4;y++){               //每天两餐啊 共4个套餐
              this.selected[x][y]=false;            //数组初始化为false
            }
          }
          this.numSelected=0;
          this.totalMoney=0;

          this.dayOfWeek=0;
          this.dayOfWeek=dayOfWeek
        },
        goodsOfSelected(){                     //这里统计订餐的份数
          var num=0;
          var total=0;
          for(var x=1;x<=7;x++){                 //7天 0 1234567  [0]不要
            for(var y=0;y<4;y++){               //每天两餐啊 共4个套餐
              if(this.selected[x][y]==true) {   //数组初始化为false
                num++;
                total+=this.dayMenu[x][y].goodsPrice;
              }
            }
          }
          this.numSelected=num;
          console.log("选中的数量="+this.numSelected);
          this.totalMoney=total;
          console.log("选中的套餐的总价是="+total);
        }


      },
         created() {
           //从localStorage中获取schoolId
           var storageData = localStorage.getItem("jwt");
           if(!storageData) {
             Toast("请先登录!")
             this.$router.push('/login')
           }else{
             storageData = JSON.parse(storageData);
             //console.log(storageData)
             var  schoolId=storageData.user.schoolId;
             console.log("学校Id："+schoolId);

             //根据学校Id获取Menu的ajax请求
             this.axios.get("/menu?schoolId="+schoolId).then(result=>{
               console.log(result.data);
               this.status=result.data.status;
               console.log("是否可以订餐"+result.data.status);
               this.weekMenu=result.data.menu;
               //console.log("周菜单为:");
               //console.log(this.weekMenu);
               console.log("周一上午第一档套餐为：");
               console.log(this.weekMenu[1]);           //这个下标从1-28

               //dayMenu[][]二维数组 初始化
               this.dayMenu = new Array();           //声明一维数组
               for(var x=0;x<=7;x++){                 //7天 0 1234567  [0]不要
                 this.dayMenu[x]=new Array();        //声明二维数组
                 for(var y=0;y<4;y++){               //每天两餐啊 共4个套餐
                   this.dayMenu[x][y]=0;                      //数组初始化为0
                 }
               }
               //  dayMenu[][]二维数组 赋值
               for(var i=1;i<=7;i++){
                 for(var j=0;j<4;j++){
                   //1  8  15  22
                   this.dayMenu[i][j]=this.weekMenu[i+j*7];  //i+j*7    [i][j]
                 }
               }
               //console.log("第一天的菜单:")
               //console.log(this.dayMenu[1])    获取正常

               //初次加载Cart这个组件，默认显示星期一的菜单
               this.todayMenu=this.dayMenu[1];
               console.log("今的菜单是:")
               console.log(this.todayMenu)


               //selected[][]二维数组 初始化
               this.selected = new Array();           //声明一维数组
               for(var x=0;x<=7;x++){                 //7天 0 1234567  [0]不要
                 this.selected[x]=new Array();        //声明二维数组
                 for(var y=0;y<4;y++){               //每天两餐啊 共4个套餐
                   this.selected[x][y]=false;            //数组初始化为false
                 }
               }
             });
           }
        },
      mounted(){
        setTimeout( () =>{
          this.navShow=true
        },500)
      },
      computed:{
      },
      watch:{
      }

    }
</script>

<style scoped>
  .mui-row.mui-fullscreen>[class*="mui-col-"] {
    height: 100%;
  }
  .mui-col-xs-3,
  .mui-control-content {
    overflow-y: auto;
    height: 100%;
  }
  .mui-segmented-control .mui-control-item {
    line-height: 50px;
    width: 100%;
  }
  .mui-segmented-control.mui-segmented-control-inverted .mui-control-item.mui-active {
    background-color: #fff;
  }

  #segmentedControlContents,#segmentedControls{
    background-color: white;
  }

  #segmentedControlContents{
    padding: 15px 0 0 10px;
  }
  .mui-content{
    padding: 0;
    background-color: white;
  }
  .itembox{
    margin: 0 0 16px;
    padding: 0 0 5px;
    border-bottom: 1px silver solid;
  }
  img{
    width: 60px;
    height: 60px;
    vertical-align: center;
  }
  .oper-icon{
    display: inline-block;
    float: right;
    padding-right: 10%;
    color: blue;
  }
  .itemmain{
    font-size: 13px;
    font-weight: bold;
  }
  .itemsub{
    font-size: 12px;
    font-family: 'Helvetica Neue', Helvetica, sans-serif;
  }
  .price {
    font-size: 18px;
    font-weight: bold;
    color: red;
  }

  .mealTime{
    /*display: inline-block;*/
    margin-left: 20px;
    /*color: mediumblue;*/
    /*font-size: 12px;*/
    /*font-weight: bold;*/
    /*font-family: fangsong;*/
  }
  .logo{
    width: 100%;
    height: 100%;
  }
  .weekday-active{
    color: red;
    background-color: #c8c7cc;
  }

  .cell1, .cell2, .cell3 {
    display: inline-block;
  }
  .cart-container{
    display: flex;
    padding: 0;
    margin: 0;
    height: 51px;
  }
  .cell1{
    flex: 2;
    background-color: #141d27;
    color: #cccccc;
  }

  .cell12{
    flex: 2;
    background-color: #141d27;
    text-align: center;
    padding-right: 0;
    color: white;
    font-size: 17px;
    line-height: 51px;
  }
  .cell2{
    flex: 3;
    background-color: #dd524d;
    text-align: center;
    padding-left: 0;
    margin: 0;
    color: white;
    font-size: 15px;
  }

  .cell3{
    flex: 3;
    background-color: #007aff;
    text-align: center;
    padding-right: 10px;
    color: white;
    font-size: 15px;
  }
  .cart-icon-box{
    border: black 5px solid;
    border-radius: 50%;
    height: 50px;
    width: 50px;
    text-align: center;
    background-color: #6c757d;
    z-index: 100;
    position: relative;
    top: -15px;
    left: 10%;
  }


</style>
