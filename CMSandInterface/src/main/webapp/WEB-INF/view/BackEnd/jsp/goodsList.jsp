<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>套餐管理</title>
    <link rel="stylesheet" type="text/css" href="/BackEnd-style/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="/BackEnd-style/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="/BackEnd-style/css/pagination.css"/>
    <script type="text/javascript" src="/BackEnd-style/js/libs/modernizr.min.js"></script>
    <script src="/BackEnd-style/js/layer.js"></script>
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
            <div class="crumb-list"><i class="icon-font"></i><a href="/jscss/admin">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">商品管理</span></div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                <form action="/admin/goods" method="post">
                    <table class="search-tab">
                        <tr>
                            <th width="70">套餐名:</th>
                            <td><input class="common-text" placeholder="套餐名" name="keyword" value="${keyword}" id="" type="text"></td>
                            <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div class="result-wrap">
            <form name="myform" id="myform" method="post">
                <div class="result-title">
                    <div class="result-list">
                        <a href="goods?&type=addUI"><i class="icon-font"></i>添加套餐</a>
                        <a id="batchDel" href="#" onclick="delelotproduct()"><i class="icon-font"></i>批量删除</a>
                        <span>${mistakeMessage}</span>
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th class="tc" width="5%"><input class="allChoose" id="allChoose" name="" type="checkbox"></th>
                            <th style="text-align: center">套餐名称</th>
                            <th style="text-align: center">套餐价格</th>
                            <th style="text-align: center">套餐菜品</th>
                            <th style="text-align: center">操作</th>
                        </tr>
                        <!--START 循环遍历-->
                        <c:forEach var="item" items="${list}">
                            <tr>
                                <td class="tc"><input name="goodsId" value="${item.goodsId}" type="checkbox" class="singleChoose"></td>
                                <td>${item.goodsName}</td>
                                <td><input style="width: 45px" name="goodsPrice" type="text" value="${item.goodsPrice}" onchange="updatePrice(this,${item.goodsId},${pageNum})">元</td>
                                <td>${item.productNames}</td>
                                <td>
                                    <a  href="goods?goodsId=${item.goodsId}&goodsName=${item.goodsName}&goodsPrice=${item.goodsPrice}&products=${item.productNames}&type=updateUI&updateType=all" class="btn btn-primary btn2">修改</a>
                                    <button type="button" onclick="singleDelete(${item.goodsId},${pageNum})" class="btn btn-danger btn2">删除</button>
                                </td>
                            </tr>
                        </c:forEach>

                        <!--END 循环遍历-->
                    </table>
                    <!--分页按钮-->
                    <div class="list-page">
                        <ul class="pagination">
                            <c:if test="${pageNum > 1}">
                                <li><span><a href="goods?pageNum=${pageNum-1}&keyword=${keyword}">&laquo;</a></span></li>
                            </c:if>
                            <li><span>当前第<a href="#">${pageNum}</a>页, 共${totalPage}页</span></li>
                            <c:if test="${pageNum < totalPage}">
                                <li><span><a href="goods?pageNum=${pageNum+1}&keyword=${keyword}">&raquo;</a></span></li>
                            </c:if>
                        </ul>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <!--/main-->
</div>
</body>
</html>

<script>

    //更新价格
    function updatePrice(input,id,pageNum){
        var price = input.value;
        con=confirm("您确认要修改价格吗？");
        if(con==true){
            $.get('goods',{goodsId:id,type:'update',updateType:'price',goodsPrice:price,pageNum:pageNum },function(msg) {
                if(msg.status==1){
                    var pageNum = msg.pageNum;
                    // layer.msg(msg.data);
                    window.location.href="goods?pageNum="+pageNum;
                }else {
                    layer.msg(msg.data);
                }

            },'json')
        }
    }
    //checkbox的全选与取消全选
    $('#allChoose').on('change', function () {
        if ($(this).is(':checked')) {
            $('.singleChoose').prop('checked', 'checked');
        } else {
            $('.singleChoose').prop('checked', '');
        }
    })

    //将要删除的id拼接 并向后端发送请求
    function delelotproduct(){
        var check = document.getElementsByName("goodsId");
        var len= check.length;
        var idAll="";
        con=confirm("您确认要删除吗？");
        if(con==true){
            for(var i=0;i<len;i++){
                if(check[i].checked){
                    idAll+=check[i].value+",";
                }
            }
            $.get('goods',{goodsId:idAll,type:'delete' },function(msg) {
                if(msg.status==1){
                    window.location.href="goods";
                }else {
                    layer.msg(msg.data);
                }

            },'json')
        }
    }
    function  singleDelete(id,pageNum) {
        $.get('goods',{goodsId:id,type:'delete',pageNum:pageNum },function(msg) {
            if(msg.status==1){
                var pageNum = msg.pageNum;
                // layer.msg(msg.data);
                window.location.href="goods?pageNum="+pageNum;
            }else {
                layer.msg(msg.data);
            }

        },'json')
    }
    <%--productName=null&productType=null&productSign=1&pageNum=${pageNum}&type=update"--%>
    function  updateProductSingn(id,sign,pageNum) {
        $.get('product',{productId:id,type:'update',productName:null,productType:null,productSign:sign,pageNum:pageNum },function(msg) {
            if(msg.status==1){
                var pageNum = msg.pageNum;
                // layer.msg(msg.data);
                window.location.href="goods?pageNum="+pageNum;
            }else {
                layer.msg(msg.data);
            }

        },'json')
    }

</script>

