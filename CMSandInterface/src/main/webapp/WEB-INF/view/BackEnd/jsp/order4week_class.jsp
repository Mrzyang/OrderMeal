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
    <script src="/BackEnd-style/js/jquery.tabletojson.js"></script>
    <script src="/BackEnd-style/js/opreate.js"></script>
<%--    <script>
        function exportExcel(fileName,tableId,type){
            alert(111);
            if(confirm("是否确定导出报？")){
                alert(111);
                var table = $("#"+tableId).tableToJSON();
                console.log(table);
                var json = JSON.stringify(table);
                alert(json);
                var nodes = $("#"+tableId+" thead tr").children();
                var headers = "";
                $.each(nodes,function(i,item){
                    headers += item.innerHTML+",";
                });
                //调用post方法
                post('/export.do', {fileName :fileName,headers:headers,json:json,type:type});
            }
            else return false;
        }
        function post(url, params) {
            var temp = document.createElement("form");
            temp.action = url;
            temp.method = "post";
            temp.style.display = "none";
            for (var x in params) {
                var opt = document.createElement("input");
                opt.name = x;
                opt.value = params[x];
                temp.appendChild(opt);
            }
            document.body.appendChild(temp);
            temp.submit();
            return temp;
        }
    </script>--%>
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
            <form name="/admin/order4week_class" id="myform" method="post">
                <input type="hidden" name="type" value="week_classList">
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
                <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
            </form>
            <button class="btn btn-success btn2" id="btn" style="width: 200px" onclick="exportExcel('ceshi','classOrder','2')">导出为excel表格</button>
            <c:forEach items="${mistakeList}" var="item">
                <p style="color: red;">*${item}</p>
            </c:forEach>
            <c:if test="${!empty list}">
                <div class="result-content">
                    <%--<table class="result-tab" width="100%">
                        <tr>
                            <th style="text-align: center" rowspan="2">姓名</th>
                            <th style="text-align: center" colspan="2">周一</th>
                            <th style="text-align: center" colspan="2">周二</th>
                            <th style="text-align: center" colspan="2">周三</th>
                            <th style="text-align: center" colspan="2">周四</th>
                            <th style="text-align: center" colspan="2">周五</th>
                            <th style="text-align: center" colspan="2">周六</th>
                            <th style="text-align: center" colspan="2">周日</th>
                            <th style="text-align: center" rowspan="2">总计/元</th>
                        </tr>
                    </table>--%>
                    <table class="result-tab" width="100%" id="classOrder">
                        <thead>
                        <%--<tr>
                            <th style="text-align: center" rowspan="2">姓名</th>
                            <th style="text-align: center" colspan="">周一</th>

                            <th style="text-align: center" colspan="">周二</th>
                            <th></th>
                            <th style="text-align: center" colspan="">周三</th>
                            <th></th>
                            <th style="text-align: center" colspan="">周四</th>
                            <th></th>
                            <th style="text-align: center" colspan="">周五</th>
                            <th></th>
                            <th style="text-align: center" colspan="">周六</th>
                            <th></th>
                            <th style="text-align: center" colspan="">周日</th>
                            <th></th>
                            <th style="text-align: center" rowspan="2">总计/元</th>
                        </tr>--%>
                        <tr>
                            <th style="text-align: center">姓名</th>
                            <th style="text-align: center">午餐1</th>
                            <th style="text-align: center">晚餐1</th>
                            <th style="text-align: center">午餐2</th>
                            <th style="text-align: center">晚餐2</th>
                            <th style="text-align: center">午餐3</th>
                            <th style="text-align: center">晚餐3</th>
                            <th style="text-align: center">午餐4</th>
                            <th style="text-align: center">晚餐4</th>
                            <th style="text-align: center">午餐5</th>
                            <th style="text-align: center">晚餐5</th>
                            <th style="text-align: center">午餐6</th>
                            <th style="text-align: center">晚餐6</th>
                            <th style="text-align: center">午餐7</th>
                            <th style="text-align: center">晚餐7</th>
                            <th style="text-align: center">总计/元</th>
                        </tr>
                        </thead>
                        <tr>
                            <th style="text-align: center" rowspan="">姓名</th>
                            <th style="text-align: center" colspan="2">周一</th>
                            <th style="text-align: center" colspan="2">周二</th>
                            <th style="text-align: center" colspan="2">周三</th>
                            <th style="text-align: center" colspan="2">周四</th>
                            <th style="text-align: center" colspan="2">周五</th>
                            <th style="text-align: center" colspan="2">周六</th>
                            <th style="text-align: center" colspan="2">周日</th>
                            <th style="text-align: center" rowspan="">总计/元</th>
                        </tr>
                        <!--START 循环遍历-->
                        <c:forEach var="item" items="${list}">
                            <tr>
                                <td>${item.customerName}</td>
                                <td>${item.order4weekPrice[0]}</td>
                                <td>${item.order4weekPrice[1]}</td>
                                <td>${item.order4weekPrice[2]}</td>
                                <td>${item.order4weekPrice[3]}</td>
                                <td>${item.order4weekPrice[4]}</td>
                                <td>${item.order4weekPrice[5]}</td>
                                <td>${item.order4weekPrice[6]}</td>
                                <td>${item.order4weekPrice[7]}</td>
                                <td>${item.order4weekPrice[8]}</td>
                                <td>${item.order4weekPrice[9]}</td>
                                <td>${item.order4weekPrice[10]}</td>
                                <td>${item.order4weekPrice[11]}</td>
                                <td>${item.order4weekPrice[12]}</td>
                                <td>${item.order4weekPrice[13]}</td>
                                <td>${item.total}</td>
                            </tr>
                        </c:forEach>

                        <!--END 循环遍历-->
                    </table>
                </div>
            </c:if>
        </div>
    </div>
    <!--/main-->
</div>
</body>
</html>


