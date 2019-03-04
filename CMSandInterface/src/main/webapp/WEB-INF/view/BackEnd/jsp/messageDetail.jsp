<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>新闻公告管理</title>
    <link rel="stylesheet" type="text/css" href="/BackEnd-style/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="/BackEnd-style/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="/BackEnd-style/css/pagination.css"/>
    <script type="text/javascript" src="/BackEnd-style/js/libs/modernizr.min.js"></script>
    <script src="/BackEnd-style/js/jquery.js"></script>
    <script src="/BackEnd-style/js/layer.js"></script>
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
            <div class="crumb-list"><i class="icon-font"></i><a href="/admin/product">首页</a><span class="crumb-step">&gt;</span><span class="crumb-step"><a href="/admin/message?type=show&page=1">用户消息反馈</a></span>&gt;</span><span class="crumb-name">详情</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-title">
                <c:if test="${message.status==0}">
                <div class="result-list">
                    <button class="btn btn-success btn2" onclick="deal(${message.id},this)"><i class="icon-font"></i>标记为已处理</button>
                </div>
                </c:if>
            </div>

        <div class="result-content">
            <table class="result-tab" width="100%">
                <tr>
                    <td>用户编号</td>
                    <td>用户姓名</td>
                    <td>用户电话</td>
                    <td>反馈时间</td>
                    <td>处理状态</td>
                </tr>
                <tr>
                    <td>${message.customer.customerNum}</td>
                    <td>${message.customer.customerName}</td>
                    <td>${message.customer.customerPhone}</td>
                    <td>${message.messageDate}</td>
                    <td>${message.status==0? '未处理': '已处理'}</td>
                </tr>
                <tr>
                    <td colspan="5">
                        ${message.message}
                    </td>
                </tr>
            </table>
        </div>
        </div>
    </div>
    <!--/main-->
</div>
<script>
    function deal(id,obj) {
        layer.confirm('确认标记为已处理吗？', function (index) {
            //此处请求后台程序，下方是成功后的前台处理……
            $.get("/admin/message", { id: id,type: 'deal'},function (data) {
                if(data.status==1){
                    layer.msg('已处理!', { icon: 1, time: 1000 });
                    setTimeout(function () {
                        window.location.reload();
                    },1000)
                }else {
                    layer.msg('操作失败!', { icon: 2, time: 1000 });
                }
            },'json');
        })
    }
</script>
</body>
</html>


