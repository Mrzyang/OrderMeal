<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="topbar-wrap white">
    <div class="topbar-inner clearfix">
        <div class="topbar-logo-wrap clearfix">
            <h1 class="topbar-logo none"><a href="index.html" class="navbar-brand">后台管理</a></h1>
            <ul class="navbar-list clearfix">
                <li><a class="on" href="/admin/index">首页</a></li>
                <li><a href="#" target="_blank">订餐首页</a></li>
            </ul>
        </div>
        <div class="top-info-wrap">
            <ul class="top-info-list clearfix">
                <c:choose>
                    <c:when test="${!empty sessionScope.adminuser.adminNum}">
                        <li>欢迎你，${sessionScope.adminuser.adminNum}</li>
                        <li><a href="http://www.jscss.me">注销</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="/admin/administrator?type=loginUI">管理员登陆</a></li>
                    </c:otherwise>
                </c:choose>

            </ul>
        </div>
    </div>
</div>