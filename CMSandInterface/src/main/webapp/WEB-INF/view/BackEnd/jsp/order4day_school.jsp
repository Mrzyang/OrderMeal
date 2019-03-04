<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>订单管理</title>
    <link rel="stylesheet" type="text/css" href="/BackEnd-style/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="/BackEnd-style/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="/BackEnd-style/css/pagination.css"/>
    <script type="text/javascript" src="/BackEnd-style/js/libs/modernizr.min.js"></script>
    <script src="/BackEnd-style/js/jquery.js"></script>
    <style type="text/css">
        th,td{
            text-align: center;
        }
    </style>
</head>
<body>
<c:import url="public/head.jsp"></c:import>
<div class="container clearfix">
    <c:import url="public/sideMenu.jsp"></c:import>
    <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="/jscss/admin">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">周班级订单</span></div>
        </div>
        <div class="result-wrap">
            <form name="/admin/order4week_school" id="myform" method="post">
                <input type="hidden" name="type" value="day_schoolList">
                &nbsp;	&nbsp;<i class="require-red">*</i>学校：
                <select name="schoolId">
                    <c:forEach items="${schoolList}" var="item">
                        <option value="${item.schoolId}" ${schoolId==item.schoolId?'selected':''}>${item.schoolName}</option>
                    </c:forEach>
                </select>
                &nbsp;	&nbsp;<i class="require-red">*</i>年级：
                <select name="customerGrade">
                    <option value="1" ${customerGrade=='1'?'selected':''}>一年级</option>
                    <option value="2" ${customerGrade=='2'?'selected':''}>二年级</option>
                    <option value="3" ${customerGrade=='3'?'selected':''}>三年级</option>
                </select>
                &nbsp;	&nbsp;<i class="require-red">*</i>配送日期：<input type="text" value="${orderDelivery}" name="orderDelivery">
                <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
            </form>
            <c:forEach items="${mistakeList}" var="item">
                <p style="color: red;">*${item}</p>
            </c:forEach>
            <c:if test="${!empty list}">
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th style="text-align: center" rowspan="2">班级</th>
                            <th style="text-align: center" colspan="2">午餐</th>
                            <th style="text-align: center" colspan="2">晚餐</th>
                        </tr>
                        <tr>
                            <th style="text-align: center">15元</th>
                            <th style="text-align: center">18元</th>
                            <th style="text-align: center">15元</th>
                            <th style="text-align: center">18元</th>
                        </tr>
                        <c:forEach begin="0" end="${totalClass}" var="i">
                            <tr>
                                <c:choose>
                                    <c:when test="${list[i][0]=='-1'}">
                                        <td>总份数</td>
                                    </c:when>
                                    <c:when test="${customerGrade=='1'}">
                                        <td>高一(${list[i][0]})班</td>
                                    </c:when>
                                    <c:when test="${customerGrade=='2'}">
                                        <td>高二(${list[i][0]})班</td>
                                    </c:when>
                                    <c:when test="${customerGrade=='3'}">
                                        <td>高三(${list[i][0]})班</td>
                                    </c:when>
                                </c:choose>
                                <td>${list[i][1]}</td>
                                <td>${list[i][2]}</td>
                                <td>${list[i][3]}</td>
                                <td>${list[i][4]}</td>
                            </tr>
                        </c:forEach>
                        <!--END 循环遍历-->
                    </table>
                </div>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>


