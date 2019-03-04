<%--
  Created by IntelliJ IDEA.
  User: Mr.Zhang
  Date: 2018/5/31
  Time: 17:18
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

    <script type="text/javascript" src="/BackEnd-style/js/jquery.js"></script>
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
            <div class="crumb-list"><i class="icon-font"></i><a href="/jscss/admin/design/">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="/jscss/admin/design/">新闻公告</a><span class="crumb-step">&gt;</span><span>发布公告</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <p>${releaseMessage}</p>
                <!--START 新增商品的表单-->
                <form action="/admin/news" method="post" id="myform" name="myform">
                    <input type="hidden" name="type" value="update">
                    <input type="hidden" name="newsId" value="${news.newsId}">
                    <input type="hidden" name="pageNum" value="${pageNum}">
                    <table class="insert-tab" width="100%">
                        <tbody>
                        <tr>
                            <th width="120">
                                <i class="require-red">*</i>标题：</th>
                            <td>
                                <input class="common-text required" id="newsTitle" name="newsTitle" size="50" value="${news.newsTitle}" type="text" placeholder="请输入公告标题！">
                            </td>
                        </tr>

                        <tr>
                            <th>
                                <i class="require-red">*</i>正文：
                            </th>
                            <td>
                                <textarea  class="common-textarea" id="newsContent" name="newsContent"  style="height: 65%;width: 100%;" >${news.newsContent}</textarea>
                            </td>
                        </tr>
                        <tr>
                            <th></th>
                            <td>
                                <button class="btn btn-primary btn6 mr10">发布</button>
                                <input class="btn btn6" onclick="history.go(-1)" value="返回" type="button">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>

    </div>
</div>
</body>
</html>
<script type="text/javascript">
    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('newsContent');
</script>
