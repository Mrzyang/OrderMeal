<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" import="com.orderMeal.utils.getTime" import="java.util.*" language="java" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>添加菜谱</title>
    <link rel="stylesheet" type="text/css" href="/BackEnd-style/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="/BackEnd-style/css/main.css"/>
    <script type="text/javascript" src="/BackEnd-style/js/libs/modernizr.min.js"></script>
    <script src="/BackEnd-style/js/jquery.js"></script>
    <script src="/BackEnd-style/js/jquery.tabletojson.js"></script>
    <script src="/BackEnd-style/js/opreate.js"></script>
   <%-- <script>
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
</head>
<body>
<c:import url="public/head.jsp"></c:import>
<div class="container clearfix">
    <c:import url="public/sideMenu.jsp"></c:import>
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="/jscss/admin/design/">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="/jscss/admin/design/">商品管理</a><span class="crumb-step">&gt;</span><span>新增商品</span></div>
        </div>
        <%! getTime time = new getTime();%>
        <div class="result-wrap">
            <div class="result-content">
                <p style="color:#ffab41"></p>
                <!--START 新增商品的表单-->
                <form action="/admin/menu" method="post" id="myform" name="myform" onsubmit="return false">
                    <input type="hidden" name="type" value="add">
                    操作：
                    <button class="btn btn-primary btn6 mr10" id="btn-submit" style="width: 200px">修改</button>
                    <button class="btn btn-success btn2" id="btn" style="width: 200px" onclick="exportExcel('ceshi','weekMenu','1')">导出为excel表格</button>
                    <br><br/>
                    <table class="result-tab" id="" width="100%" style="white-space: nowrap;text-align: center">
                        <tr style="text-align: center;color: red;">
                            <th colspan="10" style="text-align: center">
                                ${schoolName}的周菜单&nbsp;&nbsp;&nbsp;
                                时间: <%= time.getMondayDate((String) request.getAttribute("menuDate"))%>
                                ----
                                <%= time.getSundayDate((String) request.getAttribute("menuDate"))%>
                            </th>
                        </tr>
                    </table>
                    <table class="result-tab" id="weekMenu" width="100%" style="white-space: nowrap;text-align: center">
                        <thead>
                        <tr>
                            <th style="text-align: center;width: 20px;">日期</th>
                            <th style="text-align: center;width: 20px;">星期</th>
                            <th style="text-align: center;width: 20px;">时段</th>
                            <th style="text-align: center;width: 20px;">价格</th>
                            <th style="text-align: center;">主荤</th>
                            <th style="text-align: center;">配荤</th>
                            <th style="text-align: center;">素菜</th>
                            <th style="text-align: center;">配荤</th>
                            <th style="text-align: center;">水果/饮品</th>
                        </tr>
                        </thead>
                        <tbody>

                        <% int i=0; String weekDay="";%>
                        <c:forEach begin="1" end="7" var="i">
                            <%
                                i++;
                                switch (i){
                                    case 1:weekDay="星期一";break;
                                    case 2:weekDay="星期二";break;
                                    case 3:weekDay="星期三";break;
                                    case 4:weekDay="星期四";break;
                                    case 5:weekDay="星期五";break;
                                    case 6:weekDay="星期六";break;
                                    case 7:weekDay="星期日";break;
                                }
                            %>
                            <tr>
                                <td rowspan="4" style="text-align: center;width: 10%;">
                                    <%=time.getDay((String) request.getAttribute("menuDate"),i)%>
                                </td>
                                <td rowspan="4" style="text-align: center;width: 10%;">
                                    <%=weekDay%>
                                </td>
                                <td rowspan="2" style="width: 12%;">午餐</td>
                                <td style="width: 8%;">${map1[i].goodsPrice}</td>
                                <td style="width: 12%;">${fn:split(map1[i].productNames,"," )[0]}</td>
                                <td style="width: 12%;">${fn:split(map1[i].productNames,"," )[1]}</td>
                                <td style="width: 12%;">${fn:split(map1[i].productNames,"," )[2]}</td>
                                <td style="width: 12%;">${fn:split(map1[i].productNames,"," )[3]}</td>
                                <td style="width: 12%;">${fn:split(map1[i].productNames,"," )[4]}</td>
                            </tr>
                            <tr>
                                <td>${map2[i].goodsPrice}</td>
                                <td>${fn:split(map2[i].productNames,"," )[0]}</td>
                                <td>${fn:split(map2[i].productNames,"," )[1]}</td>
                                <td>${fn:split(map2[i].productNames,"," )[2]}</td>
                                <td>${fn:split(map2[i].productNames,"," )[3]}</td>
                                <td>${fn:split(map2[i].productNames,"," )[4]}</td>
                            </tr>
                            <tr>
                                <td rowspan="2">晚餐</td>
                                <td>${map3[i].goodsPrice}</td>
                                <td>${fn:split(map3[i].productNames,"," )[0]}</td>
                                <td>${fn:split(map3[i].productNames,"," )[1]}</td>
                                <td>${fn:split(map3[i].productNames,"," )[2]}</td>
                                <td>${fn:split(map3[i].productNames,"," )[3]}</td>
                                <td>${fn:split(map3[i].productNames,"," )[4]}</td>
                            </tr>
                                <td>${map4[i].goodsPrice}</td>
                                <td>${fn:split(map4[i].productNames,"," )[0]}</td>
                                <td>${fn:split(map4[i].productNames,"," )[1]}</td>
                                <td>${fn:split(map4[i].productNames,"," )[2]}</td>
                                <td>${fn:split(map4[i].productNames,"," )[3]}</td>
                                <td>${fn:split(map4[i].productNames,"," )[4]}</td>
                        </c:forEach>
                        </tbody></table>
                </form><!--END 新增商品的表单-->
            </div>
        </div>
    </div>
</div>
</body>
</html>
