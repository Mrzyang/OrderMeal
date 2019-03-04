<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>订餐平台后台管理系统</title>
    <link href="/BackEnd-style/css/admin_login.css" rel="stylesheet" type="text/css" />
</head>
<body>

<div class="admin_login_wrap" style="position:relative;left: -50px">
    <div style="font: 24px kaiti;text-align: center;width: 510px;margin-bottom: 30px">在线订餐平台后台管理系统</div>
    <div class="adming_login_border" style="width: 510px">
        <div class="QRcode" style="float: left;margin:30px 30px 0 30px">
            <img src="images/QR-code.png" width="160px" alt="二维码"/>
            <p style="font: 12px kaiti;text-align: center">扫描二维码关注微信公众号</p>
        </div>
        <div class="admin_input" style="float: left;">
            <!-- START 登录表单   推荐使用ajax提交-->
            <form action="/admin/administrator" method="post">
                <input type="hidden" name="type" value="login">
                <ul class="admin_items">
                    <li>
                        <label for="user">用户名：</label>
                        <input type="text" name="adminNum" value="${adminNum}" id="user" size="35" class="admin_input_style" />
                    </li>
                    <li>
                        <label for="pwd">密码：</label>
                        <input type="password" name="adminPassword" value="${adminPassword}" id="pwd" size="35" class="admin_input_style" />
                    </li>
                    <li>
                        <button type="submit" class="btn btn-primary"  style="width: 262px">登录</button>
                    </li>
                </ul>
            </form>
            <!--END 登录表单-->
        </div>
    </div>
</div>
</body>
</html>

