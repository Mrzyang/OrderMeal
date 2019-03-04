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
    <script type="text/javascript" src="/BackEnd-style/js/libs/modernizr.min.js"></script>
</head>
<body>
<c:import url="public/head.jsp"></c:import>
<div class="container clearfix">
    <c:import url="public/sideMenu.jsp"></c:import>
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="/admin/goods">首页</a><span class="crumb-step">&gt;</span><span>关于本平台</span></div>
        </div>

        <div class="search-wrap">
            <div class="search-content">
                    <table class="search-tab">
                        <tr>
                            <td><a class="btn btn-primary btn2" href="about?type=update">点击修改</a></td>
                        </tr>
                    </table>
            </div>
        </div>

        <div class="result-wrap">
            <div class="result-content" style="overflow: auto;height: 90%">
                ${about.content}
            </div>
        </div>

    </div>
</div>
</body>
</html>
