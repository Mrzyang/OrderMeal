<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加菜谱</title>
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
                <p style="color:#ffab41">${dataMistake1}${dataMistake2}${dataMistake3}</p>
                <!--START 新增商品的表单-->
                <form action="/admin/menu" method="post" id="myform" name="myform">
                    <input type="hidden" name="type" value="update">
                    <input type="hidden" name="menuId" value="${menuId}">
                    <table class="insert-tab" width="100%">
                        <tbody>
                        <tr>
                            <th></th>
                            <th>星期一</th>
                            <th>星期二</th>
                            <th>星期三</th>
                            <th>星期四</th>
                            <th>星期五</th>
                            <th>星期六</th>
                            <th>星期日</th>
                        </tr>
                        <tr>
                            <td>午餐</td>

                            <c:forEach begin="1" end="7" var="i">
                                <td>
                                    <span>套餐一:</span>
                                    <select name="value${i}" class="required">
                                        <option value="-1">无</option>
                                        <c:forEach items="${goodsList}" var="item" >
                                            <option value="${item.goodsId}-${item.goodsName}-${item.goodsPrice}-${item.productNames}" ${map1[i].goodsId==item.goodsId?'selected':''}>${fn:length(item.goodsName)>4?fn:substring(item.goodsName,0,4):item.goodsName}${fn:length(item.goodsName)>4?'...':''} ${item.goodsPrice}元</option>
                                        </c:forEach>
                                    </select><br>
                                    <span>套餐二:</span>
                                    <select name="value${i+7}" class="required">
                                        <option value="-1">无</option>
                                        <c:forEach items="${goodsList}" var="item">
                                            <option value="${item.goodsId}-${item.goodsName}-${item.goodsPrice}-${item.productNames}" ${map2[i].goodsId==item.goodsId?'selected':''}>${fn:length(item.goodsName)>4?fn:substring(item.goodsName,0,4):item.goodsName}${fn:length(item.goodsName)>4?'...':''} ${item.goodsPrice}元</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </c:forEach>
                        </tr>
                        <tr>
                            <td>晚餐</td>
                            <c:forEach begin="1" end="7" var="i">
                                <td>
                                    <span>套餐一:</span>
                                    <select name="value${i+14}" class="required">
                                        <option value="-1">无</option>
                                        <c:forEach items="${goodsList}" var="item" >
                                            <option value="${item.goodsId}-${item.goodsName}-${item.goodsPrice}-${item.productNames}" ${map3[i].goodsId==item.goodsId?'selected':''}>${fn:length(item.goodsName)>4?fn:substring(item.goodsName,0,4):item.goodsName}${fn:length(item.goodsName)>4?'...':''} ${item.goodsPrice}元</option>
                                        </c:forEach>
                                    </select><br>
                                    <span>套餐二:</span>
                                    <select name="value${i+21}" class="required">
                                        <option value="-1">无</option>
                                        <c:forEach items="${goodsList}" var="item">
                                            <option value="${item.goodsId}-${item.goodsName}-${item.goodsPrice}-${item.productNames}" ${map4[i].goodsId==item.goodsId?'selected':''}>${fn:length(item.goodsName)>4?fn:substring(item.goodsName,0,4):item.goodsName}${fn:length(item.goodsName)>4?'...':''} ${item.goodsPrice}元</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </c:forEach>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                周次:<input type="text" name="menuDate" value="${menuDate}">
                            </td>
                            <td>
                                学校:
                                <select name="schoolValue" class="required">
                                    <option value="-1">无</option>
                                    <c:forEach items="${schoolList}" var="item">
                                        <option value="${item.schoolId},${item.schoolName}" ${item.schoolName==schoolName?'selected':''}>${fn:length(item.schoolName)>9?fn:substring(item.schoolName,0,9):item.schoolName}${fn:length(item.schoolName)>9?'...':''}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td>
                                <button class="btn btn-primary btn6 mr10" id="btn-submit">提交</button>
                            </td>
                        </tr>
                        </tbody></table>
                </form>
                <!--END 新增商品的表单-->
            </div>
        </div>

    </div>
</div>
</body>
</html>

