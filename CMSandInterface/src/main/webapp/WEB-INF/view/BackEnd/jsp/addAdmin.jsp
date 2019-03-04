<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理员注册</title>
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
            <div class="crumb-list"><i class="icon-font"></i><a href="/jscss/admin/design/">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="/jscss/admin/design/">商品管理</a><span class="crumb-step">&gt;</span><span>新增商品</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <!--START 新增商品的表单-->
                <form action="/admin/administrator" method="post" id="myform" name="myform">
                    <input type="hidden" name="type" value="add">
                    <table class="insert-tab" width="100%">
                        <tbody>
                        <tr>
                            <th><i class="require-red">*</i>员工号：</th>
                            <td>
                                <input class="common-text required" id="title" name="adminNum" size="50" value="${adminNum}" type="text">
                            </td>
                        </tr>
                        <tr>
                            <th width="120"><i class="require-red">*</i>密码：</th>
                            <td>
                                <input type="password" value="${adminPassword}" name="adminPassword" />
                            </td>
                        </tr>
                        <tr>
                            <th width="120"><i class="require-red">*</i>权限：</th>
                            <td>
                                <input type="radio" value="1" name="adminType" ${adminType==1?'checked':''}>一级</input>
                                <input type="radio" value="2" name="adminType" ${adminType==2?'checked':''}>二级</input>
                                <input type="radio" value="3" name="adminType" ${adminType==3?'checked':''}>三级</input>
                            </td>
                        </tr>
                        <tr>
                            <th></th>
                            <td>
                                <button class="btn btn-primary btn6 mr10" id="btn-submit">提交</button>
                                <input class="btn btn6" onclick="history.go(-1)" value="返回" type="button">
                            </td>
                        </tr>
                        </tbody></table>
                </form>
                <c:forEach var="item" items="${Mistake}">
                    <p style="color: red;">*${item}</p>
                </c:forEach>
                <!--END 新增商品的表单-->
            </div>
        </div>

    </div>
</div>
</body>
</html>

