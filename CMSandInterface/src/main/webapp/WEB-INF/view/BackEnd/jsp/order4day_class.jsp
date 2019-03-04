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
            <form name="/admin/order4day_class" id="myform" method="post">
                <input type="hidden" name="type" value="day_classList">
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
                &nbsp;	&nbsp;<i class="require-red">*</i>班级：<input type="text" value="${customerClass}" name="customerClass" style="width: 30px;">
                &nbsp;	&nbsp;<i class="require-red">*</i>订餐周次：<input type="text" value="${orderDelivery}" name="orderDelivery">
                <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td><br><br>
            </form>
            <c:forEach items="${mistakeList}" var="item">
                <p style="color: red;">*${item}</p>
            </c:forEach>

            <c:if test="${!empty list1 || !empty list2 || !empty list3 || !empty list4}">
                <div class="result-content">
                    <table class="result-tab" width="40%" align="center">
                        <tr>
                            <th style="text-align: center" colspan="2">15元 中餐</th>
                        </tr>
                        <c:forEach var="item" items="${list1}">
                            <tr>
                                <td>${item}</td>
                                <td>15元</td>
                            </tr>
                        </c:forEach>
                    </table><br>

                    <table class="result-tab" width="40%" align="center">
                        <tr>
                            <th style="text-align: center" colspan="2">18元 中餐</th>
                        </tr>
                        <c:forEach var="item" items="${list2}">
                            <tr>
                                <td>${item}</td>
                                <td>18元</td>
                            </tr>
                        </c:forEach>
                    </table><br>

                    <table class="result-tab" width="40%" align="center">
                        <tr>
                            <th style="text-align: center" colspan="2">15元 晚餐</th>
                        </tr>
                        <c:forEach var="item" items="${list3}">
                            <tr>
                                <td>${item}</td>
                                <td>15元</td>
                            </tr>
                        </c:forEach>
                    </table><br>

                    <table class="result-tab" width="40%" align="center">
                        <tr>
                            <th style="text-align: center" colspan="2">18元 晚餐</th>
                        </tr>
                        <c:forEach var="item" items="${list4}">
                            <tr>
                                <td>${item}</td>
                                <td>18元</td>
                            </tr>
                        </c:forEach>
                    </table><br>

                </div>
            </c:if>
        </div>
    </div>
    <!--/main-->
</div>
</body>
</html>


