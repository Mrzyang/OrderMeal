<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
            <div class="crumb-list"><i class="icon-font"></i><a href="/admin/product">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">用户消息反馈</span></div>
        </div>
        <div class="result-wrap">
                <div class="result-title">
                    <div class="result-list">
                        <button class="btn btn-warning btn2" onclick="many_del()"><i class="icon-font"></i>批量删除</button>
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th class="tc" width="5%"><input class="allChoose" id="allChoose" name="" type="checkbox"></th>
                            <th width="5%" style="text-align: center"><p style="white-space: nowrap;">用户编号</p></th>
                            <th width="5%" style="text-align: center"><p style="white-space: nowrap;">用户姓名</p></th>
                            <th width="15%" style="text-align: center">内容</th>
                            <th width="25%" style="text-align: center">反馈时间</th>
                            <th width="10%" style="text-align: center">状态</th>
                            <th width="25%" style="text-align: center">操作</th>
                        </tr>

                        <c:forEach var="item" items="${messages}">
                        <tr>
                            <td class="tc"><input value="${item.id}" type="checkbox" class="singleChoose"></td>
                            <td><p style="white-space: nowrap;">${item.customer.customerNum}</p></td>
                            <td><p style="white-space: nowrap;">${item.customer.customerName}</p></td>
                            <td><p style="text-overflow: ellipsis;white-space: nowrap;overflow: hidden;width: 160px">${fn:substring(item.message,0,10)}   </p></td>
                            <td><p style="white-space: nowrap;">${item.messageDate}</p></td>
                            <td><p style="white-space: nowrap;">${item.status==0? '未处理': '已处理'}</p></td>
                            <td>
                                <p style="white-space: nowrap;">
                                    <a type="button"  class="btn btn-success btn2" href="/admin/message?type=detail&id=${item.id}">详情</a>
                                    <c:if test="${item.status==0}">
                                    <button type="button"  class="btn btn-primary btn2" onclick="deal(${item.id},this)">已处理</button>
                                    </c:if>
                                    <c:if test="${item.status==1}">
                                        <button type="button"  class="btn btn-primary btn2" onclick="deal(${item.id},this)" disabled>已处理</button>
                                    </c:if>
                                    <button type="button"  class="btn btn-danger btn2" onclick="delete_message(${item.id},this)">删除</button>
                                </p>
                            </td>
                        </tr>
                        </c:forEach>
                    </table>
                    <!--分页按钮-->
                    <div class="list-page">
                        <ul class="pagination">
                                <li><a href="/admin/message?type=show&page=${page-1>=1?page-1:1}">&laquo;</a></li>
                            <c:forEach begin="1" end="${totalPages}" varStatus="loop">
                                <c:set var="active" value="${loop.index==page?'active':''}"/>
                                <li class="${active}">
                                    <a href="/admin/message?type=show&page=${loop.index}">${loop.index}</a>
                                </li>
                            </c:forEach>
                                <li><a href="/admin/message?type=show&page=${page+1<=totalPages?page+1:totalPages}">&raquo;</a></li>
                        </ul>
                    </div>
                </div>
        </div>
    </div>
    <!--/main-->
</div>
<script>
    $('#allChoose').on('change', function () {
        if ($(this).is(':checked')) {
            $('.singleChoose').prop('checked', 'checked');
        } else {
            $('.singleChoose').prop('checked', '');
        }
    })
</script>

<script>
    function delete_message(id, obj) {
        layer.confirm('确认要删除吗？', function (index) {
            //此处请求后台程序，下方是成功后的前台处理……
            $.get("/admin/message", { id: id,type: 'delete'},function (data) {
                if(data.status==1){
                    $(obj).parents("tr").remove();
                    layer.msg('已删除!', { icon: 1, time: 1000 });
                }else{
                    layer.msg('删除失败!', { icon: 1, time: 1000 });
                }
            },'json');
        })
    }


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


    function many_del() {
        var idStr = '';
        $(".singleChoose").each(function () {
            if ($(this)[0].checked) {
                idStr += $(this).val() + ',';
            }
        });
        idStr=idStr.substring(0,idStr.length-1);
        if (!idStr) { alert('请至少勾选一个！'); return }
        layer.confirm('确认要删除吗？', function (index) {
            $.get("/admin/message", { idStr: idStr,type: 'batchDel' },function (data) {
                if(data.status==1){
                    layer.msg('已删除!', { icon: 1, time: 1000 });
                    setTimeout(function () {
                        window.location.reload();
                    },1000)
                }else{
                    layer.msg('删除失败!', { icon: 1, time: 1000 });
                }
            },'json')
        });
    }
</script>
</body>
</html>


