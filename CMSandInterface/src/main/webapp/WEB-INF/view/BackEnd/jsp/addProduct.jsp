<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加菜品</title>
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
                <p>${addMistake}</p>
                <!--START 新增商品的表单-->
                <form action="/admin/product" method="post" id="myform" name="myform">
                    <input type="hidden" name="type" value="add">
                    <table class="insert-tab" width="100%">
                        <tbody>
                        <tr>
                            <th><i class="require-red">*</i>名称：</th>
                            <td>
                                <input class="common-text required" id="productName" name="productName" size="50" value="${productName}" type="text">
                                <span style="color: red;">${nameMistake}</span>
                            </td>
                        </tr>
                        <tr>
                            <th width="120"><i class="require-red">*</i>类型：</th>
                            <td>
                                <select name="productType" id="type" class="required" >
                                    <c:choose>
                                        <c:when test="${productType==4}">
                                            <option value="1">主荤</option>
                                            <option value="2">辅荤</option>
                                            <option value="3">素菜</option>
                                            <option value="4" selected='selected'>水果饮品</option>
                                        </c:when>
                                        <c:when test="${productType==2}">
                                            <option value="1">主荤</option>
                                            <option value="2" selected='selected'>辅荤</option>
                                            <option value="3">素菜</option>
                                            <option value="4">水果饮品</option>
                                        </c:when>
                                        <c:when test="${productType==3}">
                                            <option value="1">主荤</option>
                                            <option value="2">辅荤</option>
                                            <option value="3" selected='selected'>素菜</option>
                                            <option value="4">水果饮品</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="1" selected='selected'>主荤</option>
                                            <option value="2">辅荤</option>
                                            <option value="3">素菜</option>
                                            <option value="4">水果饮品</option>

                                        </c:otherwise>
                                    </c:choose>
                                </select>
                                <span style="color: red;">${typeMistake}</span>
                            </td>
                        </tr>
                        <tr>
                            <th></th>
                            <td>
                                <button class="btn btn-primary btn6 mr10">提交</button>
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


