> 去年这个时候是我人生中比较迷茫的时候，自己准备找工作，父母要求我考研，虽然我知道自己不属于那种能静下心来、忍受寂寞长时间复习备考的人，为了不让父母失望，我还是毅然考研，虽然结果并不怎么样。错过了秋招，春招是我最后一次咸鱼翻身的机会了，即使我的技术并不怎么样，但是人生中第一份工作是尤其重要的。现在补发下去年这时候应一位老师的要求做的一个<b>中学订餐系统</b>。
##  技术涉及

 -  移动端： Vue.js---2.5.2 
     - 开发工具： webstorm
    -  UI组件: 饿了么UI---element
    -  CSS样式: mui
    - ajax跨域请求采用axios插件
 - 后端
   - 开发工具： IDEA
   - PC端CMS采用jsp+servlet+mysql实现。(其实我是想采用spring+springmvc+Mybatis，这是团队采取的方案)
   - 主要解决前后端分离、跨域请求、无Session登录等问题，登录采用jwt验证，token放在 localstorage中，当然也可以放在cookie中， 不过前者允许存储的字节数更大，而已更易于js操作。每次涉及数据操作的请求，token都放在http-request-header中，后端拦截器判断请求的可行性。

## 项目展示
<img src="https://img-blog.csdnimg.cn/20190304160131904.png" width="50%">
<img src="https://img-blog.csdnimg.cn/20190304160156208.png" width="50%">
<img src="https://img-blog.csdnimg.cn/20190304160221331.png" width="50%">
<img src="https://img-blog.csdnimg.cn/20190304160246106.png
" width="50%">
<img src="https://img-blog.csdnimg.cn/20190304160317285.png" width="50%">
<img src="https://img-blog.csdnimg.cn/20190304160324271.png
" width="50%">
<img src="https://img-blog.csdnimg.cn/20190304160335418.png" width="50%">


![在这里插入图片描述](https://img-blog.csdnimg.cn/20190304163421423.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzMyMzg4OTc3,size_16,color_FFFFFF,t_70)

## 备注
1. 前端菜鸟花了半个月来学习Vue.js做移动端页面，效果low，不喜勿喷，，请多多指教
2. 后期联系老师准备交项目的时候，老师没有给出答复，敷衍了我们，项目算是泡汤了，部分功能没有完成，也没有继续开发下去，紧接着是匆忙复习考研，所以也耽搁下了。即便如此，通过这个小项目，学到了不少东西，还是挺值得的。
3. 项目地址：https://github.com/Mrzyang/OrderMeal
4. 预览效果： 移动前端： http://zyang.top   后台CMS:  http://zyang.top:8080/admin/product
5. **项目备注**： 周一到周五显示本周菜单，周末显示下周菜单，发现选菜页面没有东西的同学注意啦！！！
