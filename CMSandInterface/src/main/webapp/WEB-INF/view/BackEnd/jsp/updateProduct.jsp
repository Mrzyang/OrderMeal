<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>商品管理</title>
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
                <input type="hidden" value="${product.productId}" name="productId"/>
                <input type="hidden" value="${product.productSign}" name="productSign"/>
                <table class="insert-tab" width="100%">
                    <tbody>
                    <tr>
                        <th><i class="require-red">*</i>名称：</th>
                        <td>
                            <input class="common-text required" id="productName" name="productName" size="50" value="${product.productName}" type="text">
                        </td>
                    </tr>
                    <tr>
                        <th width="120"><i class="require-red">*</i>类型：</th>
                        <td>
                            <select name="productType" id="type" class="required" >
                                <option value="1" ${product.productType=="1"?'selected':''}>主荤</option>
                                <option value="2" ${product.productType=="2"?'selected':''}>辅荤</option>
                                <option value="3" ${product.productType=="3"?'selected':''}>素菜</option>
                                <option value="4" ${product.productType=="4"?'selected':''}>水果饮品</option>
                            </select>
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
    <!--/main-->
</div>
</body>
</html>



<script>
    $(document).ready(function () {
        $("#btn-submit").click(function () {
            if (!$('#productName').val()) {
                alert("请输入菜品名！");
                return;
            }
            else {
                $.ajax({
                    type: "post",
                    url: "/admin/product",
                    data: $('#myform').serialize(),
                    dataType: "json",
                    success: function (msg) {
                        if (msg.status == 1) {
                            // layer.msg(msg.data);
                            window.location.href="product";
                        } else {
                            //layer.msg(msg.data);
                        }
                    }
                });
            }
        });
    })
</script>