<%--
  Created by IntelliJ IDEA.
  User: Mr.Zhang
  Date: 2018/5/29
  Time: 9:55
  To change this template use File | Settings | File Templates.
--%>
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
            <div class="crumb-list"><i class="icon-font"></i><a href="/admin/product">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">公告管理</span></div>
        </div>
        <div class="result-wrap">
            <form name="myform" id="myform" method="post">
                <div class="result-title">
                    <div class="result-list">
                        <a href="news?&type=releaseUI"><i class="icon-font"></i>发布公告</a>
                        <a id="batchDel" href="#" onclick="deleteNewses()"><i class="icon-font"></i>批量删除</a>
                        <span>${mistakeMessage}</span>
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th class="tc" width="5%"><input class="allChoose" id="allChoose" name="" type="checkbox"></th>
                            <th width="5%" style="text-align: center"><p style="white-space: nowrap;">序号</p></th>
                            <th width="15%" style="text-align: center">标题</th>
                            <th width="25%" style="text-align: center">发布时间</th>
                            <th width="25%" style="text-align: center">操作</th>
                        </tr>
                        <!--START 循环遍历-->
                        <% int i=0;%>
                        <c:forEach var="item" items="${list}">
                            <tr>
                                <td class="tc"><input name="newsId" value="${item.newsId}" type="checkbox" class="singleChoose"></td>
                                <td><p style="white-space: nowrap;"><% i++;%><%= i%></p></td>
                                <td><p style="text-overflow: ellipsis;white-space: nowrap;overflow: hidden;width: 160px">${item.newsTitle}</p></td>
                                <td><p style="white-space: nowrap;">${item.releaseTime}</p></td>
                                <td>
                                    <p style="white-space: nowrap;">
                                        <button type="button" onclick="window.location.href=('news?&type=details&newsId=${item.newsId}')" class="btn btn-success btn2">详情</button>
                                        <button type="button" onclick="window.location.href=('news?&type=updateUI&newsId=${item.newsId}&pageNum=${pageNum}')" class="btn btn-primary btn2">修改</button>
                                        <button type="button" onclick="singleDelete(${item.newsId},${pageNum})" class="btn btn-danger btn2">删除</button>
                                    </p>
                                </td>
                            </tr>
                        </c:forEach>

                        <!--END 循环遍历-->
                    </table>
                    <!--分页按钮-->
                    <div class="list-page">
                        <ul class="pagination">
                            <c:if test="${pageNum > 1}">
                                <li><span><a href="news?pageNum=${pageNum-1}">&laquo;</a></span></li>
                            </c:if>
                            <c:if test=""></c:if>
                            <li><span style="white-space: nowrap;">当前第<a href="#">${pageNum}</a>页, 共${totalPage}页</span></li>
                            <c:if test="${pageNum < totalPage}">
                                <li><span><a href="news?pageNum=${pageNum+1}">&raquo;</a></span></li>
                            </c:if>
                        </ul>
                    </div>
                </div>
            </form>
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
    function deleteNewses(){
        var check = document.getElementsByName("newsId");
        var len= check.length;
        var idAll="";
        for(var i=0;i<len;i++){
            if(check[i].checked){
                idAll+=check[i].value+",";
            }
        }
        if(idAll==""){
            alert("您还没有选择任何选项！")
            return ;
        }
        con=confirm("您确认要删除吗？");
        if(con==true){
            $.post('news',{newsId:idAll,type:'deleteMore',pageNum:${pageNum} },function(msg) {
                if(msg.result){
                    alert("恭喜你，删除成功！");
                    // layer.msg(msg.data);
                    window.location.href="news?&pageNum="+msg.pageNum;
                }else {
                    layer.msg(msg);
                }
            },'json')
        }
    }
    function  singleDelete(id,pageNum) {
        con=confirm("您确认要删除吗？");
        if(con){
            $.post('news',{newsId:id,type:'delete',pageNum:pageNum},function(msg) {
                if(msg.result){
                    alert("恭喜你，删除成功！");
                    // layer.msg(msg.data);
                    window.location.href="news?&pageNum="+msg.pageNum;
                }else {
                    layer.msg(msg);
                }

            },'json')
        }

    }
</script>
</body>
</html>


