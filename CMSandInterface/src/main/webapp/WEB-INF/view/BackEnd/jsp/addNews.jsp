<%--
  Created by IntelliJ IDEA.
  User: Mr.Zhang
  Date: 2018/5/29
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>发布公告</title>
    <link rel="stylesheet" type="text/css" href="/BackEnd-style/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="/BackEnd-style/css/main.css"/>
    <script type="text/javascript" src="/BackEnd-style/js/libs/modernizr.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="/BackEnd-style/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="/BackEnd-style/ueditor/ueditor.all.js"> </script>
    <script type="text/javascript" src="/BackEnd-style/js/jquery.js"></script>
    <script>
        function bbb() {
            alert(22);
        }
        function aaa() {
            alert(111);
        }
        function release() {
            var title = $("#newsTitle").val();
            var content = $("#newsContent").val();
            if(title==""||title==null||content==""||content==null){
                alert("请输入标题和内容！")
                return false;
            }
            if(confirm("是否确定发布？")){
                $.ajax({
                    type:"POST",
                    dataType:"json",
                    url:"/admin/news",
                    data:$('#newsForm').serialize(),
                    success:function (result) {
                        if(result){
                            alert("恭喜您发布成功！");
                            window.location.href=("/admin/news");
                        }else {
                            alert("对不起发布失败，请重试！")
                        }
                    },
                    error:function () {
                        alert("出现异常：请确认操作是否正确！")
                    }
                });
            }else{
                return false;
            }

        }
    </script>
    <script>
        function aaa() {
            alert(111);
        }
        function release() {
            if(confirm("是否确定发布？")){
                $.ajax({
                    type:"POST",
                    dataType:"json",
                    url:"/admin/news",
                    data:$('#newsForm').serialize(),
                    success:function (result) {
                        if(result){
                            alert("恭喜您发布成功！");
                            window.location.href=("/admin/news");
                        }else {
                            alert("对不起发布失败，请重试！")
                        }
                    },
                    error:function () {
                        alert("出现异常：请确认操作是否正确！")
                    }
                })
            }else{
                return false;
            }

        }
    </script>
</head>
<body>
<c:import url="public/head.jsp"></c:import>
<div class="container clearfix">
    <c:import url="public/sideMenu.jsp"></c:import>
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="/jscss/admin/design/">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="/admin/news">新闻公告</a><span class="crumb-step">&gt;</span><span>发布公告</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <p>${releaseMessage}</p>
                <!--START 新增商品的表单-->
                <form action="##" method="post" id="newsForm" name="newsForm" onsubmit="return false;">
                    <input type="hidden" name="type" value="release">
                    <table class="insert-tab" width="100%">
                        <tbody>
                        <tr>
                            <th><i class="require-red">*</i>标题：</th>
                            <td>
                                <input class="common-text required" id="newsTitle" name="newsTitle" size="50" value="${newsTitle}" type="text" placeholder="请输入公告标题！">
                                <span style="color: red;">${titleMistake}</span>
                            </td>
                        </tr>
                        <tr>
                            <th width="120"><i class="require-red">*</i>

                                正文：</th>
                            <td>
                                <textarea class="common-text required" id="newsContent" name="newsContent" cols="30" rows="10"  style="height: 400px;width: 90%;" placeholder="请输入公告内容!">${newsContent}</textarea>
                                <br>
                                <span style="color: red;">${contentMistake}</span>
                            </td>
                        </tr>
                        <tr>
                            <th></th>
                            <td>
                                <button class="btn btn-primary btn6 mr10" onclick="release()">发布</button>
                                <input class="btn btn6" onclick="history.go(-1)" value="返回" type="button">
                            </td>
                        </tr>
                        </tbody></table>
                </form>
                <!--END 新增商品的表单-->
            </div>
        </div>

    </div>
    <!--/main-->
</div>
</body>
</html>
