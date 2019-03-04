<%--
  Created by IntelliJ IDEA.
  User: Mr.Zhang
  Date: 2018/5/31
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>公告详情</title>
    <link rel="stylesheet" type="text/css" href="/BackEnd-style/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="/BackEnd-style/css/main.css"/>
    <script type="text/javascript" src="/BackEnd-style/js/jquery.js"></script>
    <script type="text/javascript" src="/BackEnd-style/js/layer.js"></script>
    <script type="text/javascript" src="/BackEnd-style/js/libs/modernizr.min.js"></script>

    <script type="text/javascript" charset="utf-8" src="/BackEnd-style/Ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="/BackEnd-style/Ueditor/ueditor.all.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="/BackEnd-style/Ueditor/lang/zh-cn/zh-cn.js"></script>


</head>
<body>
<c:import url="public/head.jsp"></c:import>
<div class="container clearfix">
    <c:import url="public/sideMenu.jsp"></c:import>
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="/admin/goods">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="/admin/about?type=show">关于本平台</a><span class="crumb-step">&gt;</span><span>修改信息</span></div>
        </div>

        <div class="result-wrap">
            <form enctype="multipart/form-data" name="about-form" id="about-form">
            <!--style给定宽度可以影响编辑器的最终宽度-->
                <textarea name="content" class="common-textarea" id="content" style="height: 70%;width: 100%"></textarea>
                <div style="padding-left: 1%">
                     <button class="btn btn-primary btn6 mr10" type="button" id="content-submit" >提交</button>
                     <input class="btn btn6" onclick="history.go(-1)" value="返回" type="button">
                </div>
            </form>
        </div>
    </div>
    <div id="about-content" hidden>${about.content}</div>

</div>
</body>
</html>

<script type="text/javascript">
    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('content');
</script>

<script>
    var detail=$('#about-content').html();
    ue.ready(function () {
        //设置编辑器的内容
        ue.setContent(detail);
    });
</script>

<script>
$('#content-submit').click(function () {
    $.post('about', $('#about-form').serialize(),function (data) {
        console.log(data);
        if(data.status==1){
            layer.msg(data.msg);
            setTimeout(function () {
                window.location.href='about?type=show';
            },1000);
        }else {
            layer.msg(data.msg);
        }

    },'json')
})
</script>