<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加套餐</title>
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
                <p style="color:#ffab41">${addMistake}</p>
                <!--START 新增商品的表单-->
                <form action="/admin/school" method="post" id="myform" name="myform">
                    <input type="hidden" name="type" value="add">
                    <table class="insert-tab" width="100%">
                        <tbody>
                        <tr>
                            <th><i class="require-red">*</i>学校名：</th>
                            <td>
                                <input class="common-text required" id="title" name="schoolName" size="50" value="${schoolName}" type="text">
                            </td>
                        </tr>
                        <tr>
                            <th width="120"><i class="require-red">*</i>各年级班级数：</th>
                            <td>
                                一年级<input type="text" value="${schoolClasstotal1}" name="schoolClasstotal1" style="width: 20px"/>&nbsp;&nbsp;
                                二年级<input type="text" value="${schoolClasstotal2}" name="schoolClasstotal2" style="width: 20px"/>&nbsp;&nbsp;
                                三年级<input type="text" value="${schoolClasstotal3}" name="schoolClasstotal3" style="width: 20px"/>
                            </td>
                        </tr>
                        <tr>
                            <th width="120"><i class="require-red">*</i>联系方式：</th>
                            <td><input type="text" value="${schoolPhone}" name="schoolPhone"/></td>
                        </tr>
                        <tr>
                            <th width="120"><i class="require-red">*</i>学校地址：</th>
                            <td><input type="text" value="${schoolAddress}" name="schoolAddress"/></td>
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
                <!--END 新增商品的表单-->
                <c:forEach items="${mistakeList}" var="item">
                    <p style="color: red;">*${item}</p>
                </c:forEach>
            </div>
        </div>

    </div>
</div>
</body>
</html>

