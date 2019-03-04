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
    <script src="/BackEnd-style/js/jquery.js"></script>
    <script src="/BackEnd-style/js/jquery.tabletojson.js"></script>
    <script src="/BackEnd-style/js/opreate.js"></script>
    <script>
<%--        function exportExcel(fileName,tableId,type){
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
            <form name="/admin/order4week_school" id="myform" method="post">
                <input type="hidden" name="type" value="week_schoolList">
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
                &nbsp;	&nbsp;<i class="require-red">*</i>订餐周次：<input type="text" value="${orderDelivery}" name="orderDelivery">
                <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
            </form>
            <button class="btn btn-success btn2" id="btn" style="width: 200px" onclick="exportExcel('ceshi','schoolOrder','3')">导出为excel表格</button>

            <c:forEach items="${mistakeList}" var="item">
                <p style="color: red;">*${item}</p>
            </c:forEach>
            <c:if test="${!empty list}">
                <div class="result-content">
                    <table class="result-tab" width="100%" id="schoolOrder">
                        <thead>
                        <tr>
                            <th style="text-align: center" rowspan="">星期</th>
                            <th style="text-align: center">午餐1</th>
                            <th style="text-align: center">晚餐1</th>
                            <th style="text-align: center">午餐2</th>
                            <th style="text-align: center">晚餐2</th>
                            <th style="text-align: center" rowspan="2">总计</th>
                            <th style="text-align: center" rowspan="2">合计餐费/(元)</th>
                        </tr>

                        </thead>

                        <tr>
                            <th style="text-align: center" rowspan=""></th>
                            <th style="text-align: center" colspan="2">15元</th>
                            <th style="text-align: center" colspan="2">18元</th>
                            <th style="text-align: center" rowspan=""></th>
                            <th style="text-align: center" rowspan=""></th>
                        </tr>

                        <tr>
                            <td>星期一</td>
                            <td>${list[0][0]}</td>
                            <td>${list[0][1]}</td>
                            <td>${list[0][2]}</td>
                            <td>${list[0][3]}</td>
                            <td>${list[0][4]}</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>星期二</td>
                            <td>${list[1][0]}</td>
                            <td>${list[1][1]}</td>
                            <td>${list[1][2]}</td>
                            <td>${list[1][3]}</td>
                            <td>${list[1][4]}</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>星期三</td>
                            <td>${list[2][0]}</td>
                            <td>${list[2][1]}</td>
                            <td>${list[2][2]}</td>
                            <td>${list[2][3]}</td>
                            <td>${list[2][4]}</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>星期四</td>
                            <td>${list[3][0]}</td>
                            <td>${list[3][1]}</td>
                            <td>${list[3][2]}</td>
                            <td>${list[3][3]}</td>
                            <td>${list[3][4]}</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>星期五</td>
                            <td>${list[4][0]}</td>
                            <td>${list[4][1]}</td>
                            <td>${list[4][2]}</td>
                            <td>${list[4][3]}</td>
                            <td>${list[4][4]}</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>星期六</td>
                            <td>${list[5][0]}</td>
                            <td>${list[5][1]}</td>
                            <td>${list[5][2]}</td>
                            <td>${list[5][3]}</td>
                            <td>${list[5][4]}</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>星期日</td>
                            <td>${list[6][0]}</td>
                            <td>${list[6][1]}</td>
                            <td>${list[6][2]}</td>
                            <td>${list[6][3]}</td>
                            <td>${list[6][4]}</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>份数</td>
                            <td>${list[7][0]}</td>
                            <td>${list[7][1]}</td>
                            <td>${list[7][2]}</td>
                            <td>${list[7][3]}</td>
                            <td>${list[7][4]}</td>
                            <td>${total}</td>
                        </tr>


                        <!--END 循环遍历-->
                    </table>
                    <!--分页按钮-->
                    <div class="list-page">
                        <ul class="pagination">
                            <c:if test="${customerClass > 1}">
                                <li><span><a href="order4week_school?type=week_schoolList&customerClass=${customerClass-1}&schoolId=${schoolId}&customerGrade=${customerGrade}&customerClass=${customerClass}&orderDelivery=${orderDelivery}">&laquo;上个班级</a></span></li>
                            </c:if>
                            <li><span>当前班级:（<a href="#">${customerClass}</a>）班, 共${totalClass}个班级</span></li>
                            <c:if test="${customerClass < totalClass}">
                                <li><span><a href="order4week_school?type=week_schoolList&customerClass=${customerClass+1}&schoolId=${schoolId}&customerGrade=${customerGrade}&customerClass=${customerClass}&orderDelivery=${orderDelivery}">下个班级&raquo;</a></span></li>
                            </c:if>
                        </ul>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
    <!--/main-->
</div>
</body>
</html>


