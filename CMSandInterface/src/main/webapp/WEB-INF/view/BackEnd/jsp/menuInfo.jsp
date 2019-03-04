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
                <p style="color:#ffab41"></p>
                <!--START 新增商品的表单-->
                <form action="/admin/menu" method="post" id="myform" name="myform">
                    <input type="hidden" name="type" value="add">
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
                                    <p style="font-weight: bold">${map1[i].goodsName} &nbsp&nbsp&nbsp&nbsp${map1[i].goodsPrice}</p>
                                    <p>${fn:length(map1[i].productNames)>18?fn:substring(map1[i].productNames,0,18):map1[i].productNames}${fn:length(map1[i].productNames)>18?'...':''}</p>
                                    <span>套餐二:</span>
                                    <p style="font-weight: bold">${map2[i].goodsName} &nbsp&nbsp&nbsp&nbsp${map2[i].goodsPrice}</p>
                                    <p>${fn:length(map2[i].productNames)>18?fn:substring(map2[i].productNames,0,18):map2[i].productNames}${fn:length(map2[i].productNames)>18?'...':''}</p>
                                </td>
                            </c:forEach>
                        </tr>
                        <tr>
                            <td>晚餐</td>
                            <c:forEach begin="1" end="7" var="i">
                                <td>
                                    <span>套餐一:</span>
                                    <p style="font-weight: bold">${map3[i].goodsName} &nbsp&nbsp&nbsp&nbsp${map3[i].goodsPrice}</p>
                                    <p>${fn:length(map3[i].productNames)>18?fn:substring(map3[i].productNames,0,18):map3[i].productNames}${fn:length(map3[i].productNames)>18?'...':''}</p>
                                    <span>套餐二:</span>
                                    <p style="font-weight: bold">${map4[i].goodsName} &nbsp&nbsp&nbsp&nbsp${map4[i].goodsPrice}</p>
                                    <p>${fn:length(map4[i].productNames)>18?fn:substring(map4[i].productNames,0,18):map4[i].productNames}${fn:length(map4[i].productNames)>18?'...':''}</p>
                                </td>
                            </c:forEach>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                周次:${menuDate}
                            </td>
                            <td>
                                学校:${schoolName}
                            </td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td>
                                <button class="btn btn-primary btn6 mr10" id="btn-submit">修改</button>
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

