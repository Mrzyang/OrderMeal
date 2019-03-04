<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>修改套餐</title>
    <link rel="stylesheet" type="text/css" href="/BackEnd-style/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="/BackEnd-style/css/main.css"/>
    <script type="text/javascript" src="/BackEnd-style/js/libs/modernizr.min.js"></script>
    <script src="/BackEnd-style/js/jquery.js"></script>
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
                <form method="post" id="myform" name="myform">
                    <input type="hidden" name="type" value="update">
                    <input type="hidden" name="updateType" value="all">
                    <input type="hidden" name="goodsId" value="${goods.goodsId}">
                    <table class="insert-tab" width="100%">
                        <tbody>
                        <tr>
                            <th><i class="require-red">*</i>名称：</th>
                            <td>
                                <input class="common-text required" id="goodsName" name="goodsName" size="50" value="${goods.goodsName}" type="text">
                                <span style="color: red;">${nameMistake}</span>
                            </td>
                        </tr>
                        <tr>
                            <th width="120"><i class="require-red">*</i>价格：</th>
                            <td><input type="text" value="${goods.goodsPrice}" id="goodsPrice" name="goodsPrice"/>
                                <span style="color: red;">${priceMistake}</span>
                            </td>
                        </tr>
                        <tr>
                            <th width="120"><i class="require-red">*</i>菜品：</th>
                            <td>
                                <span>主荤:</span>
                                <select name="product1" id="product1" class="required">
                                    <option value="-1">无</option>
                                    <c:forEach items="${productlist1}" var="item">
                                        <option value="${item}" ${product1==item||product2==item||product3==item||product4==item?'selected':''}>${item}</option>
                                    </c:forEach>
                                </select>
                                <span>素菜:</span>
                                <select name="product3" id="product3" class="required">
                                    <option value="-1">无</option>
                                    <c:forEach items="${productlist2}" var="item">
                                        <option value="${item}" ${product1==item||product2==item||product3==item||product4==item?'selected':''}>${item}</option>
                                    </c:forEach>
                                </select>
                                <span>水果饮品:</span>
                                <select name="product4" id="product4" class="required">
                                    <option value="-1">无</option>
                                    <c:forEach items="${productlist3}" var="item">
                                        <option value="${item}" ${product1==item||product2==item||product3==item||product4==item?'selected':''}>${item}</option>
                                    </c:forEach>
                                </select>
                                <span>辅荤:</span>
                                <select name="product2" id="product2" class="required">
                                    <option value="-1">无</option>
                                    <c:forEach items="${productlist4}" var="item">
                                        <option value="${item}" ${product1==item||product2==item||product3==item||product4==item?'selected':''}>${item}</option>
                                    </c:forEach>
                                </select>
                                <span style="color: red">${productMistake}</span>
                            </td>
                        </tr>
                        <tr>
                            <th></th>
                            <td>
                                <button type="button" class="btn btn-primary btn6 mr10" id="btn-submit">提交</button>
                                <input class="btn btn6" onclick="history.go(-1)" value="返回" type="button">
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



<script>
    $(document).ready(function () {
        $("#btn-submit").click(function () {
            if (!$('#goodsName').val()) {
                alert("请输入套餐名！");
                return;
            }
            if (!$('#goodsPrice').val()) {
                alert("请输入套餐价格！");
                return;
            }
            else {
                $.ajax({
                    type: "post",
                    url: "/admin/goods",
                    data: $('#myform').serialize(),
                    dataType: "json",
                    success: function (msg) {
                        if (msg.status == 1) {
                            // layer.msg(msg.data);
                            window.location.href="goods";
                        } else {
                            layer.msg(msg.data);
                        }
                    }
                });
            }
        });
    })
</script>
