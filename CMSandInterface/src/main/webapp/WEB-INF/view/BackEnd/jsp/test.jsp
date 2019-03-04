<%--
  Created by IntelliJ IDEA.
  User: Mr.Zhang
  Date: 2018/6/8
  Time: 13:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>

    <% String path = request.getContextPath();%>
    <title>aa</title>
    <script src="../BackEnd-style/js/jquery.js"></script>
    <script src="../BackEnd-style/js/jquery.tabletojson.js"></script>


</head>
<body>
<table id="test">
    <thead>
    <tr><td id="seq">序号<%=path%></td><td>姓名</td><td>name</td></tr>
    </thead>
    <tr><td>1</td><td>张三</td><td>zhangsan</td></tr>
    <tr><td>2</td><td>李四</td><td>lisi</td></tr>
    <tr><td>3</td><td>王五</td><td>wangwu</td></tr>
</table>
<input id="aaa" value="123">
<button onclick="expo()">Export excel 2</button><br><br>
<script type="text/javascript">
    function expo() {
        alert(1111);
        var table = $("#test").tableToJSON();
        alert(222)
    }
</script>
</body>
</html>
