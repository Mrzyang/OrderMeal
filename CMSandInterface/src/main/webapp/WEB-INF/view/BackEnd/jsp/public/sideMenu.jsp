<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="sidebar-wrap">
    <div class="sidebar-title">
        <h1>菜单</h1>
    </div>
    <div class="sidebar-content">
        <ul class="sidebar-list">
            <li>
                <a href="#"><i class="icon-font">&#xe003;</i>信息管理</a>
                <ul class="sub-menu">
                    <li><a href="/admin/product"><i class="icon-font">&#xe008;</i>菜品信息管理</a></li>
                    <li><a href="/admin/goods"><i class="icon-font">&#xe008;</i>套餐信息管理</a></li>
                    <li><a href="/admin/menu"><i class="icon-font">&#xe008;</i>菜单信息管理</a></li>
                    <li><a href="/admin/school"><i class="icon-font">&#xe008;</i>学校信息管理</a></li>
                    <c:if test="${!empty sessionScope.adminuser.adminNum}">
                        <li><a href="/admin/administrator"><i class="icon-font">&#xe008;</i>管理员信息管理</a></li>
                    </c:if>
                    <li><a href="/admin/order4week_class"><i class="icon-font">&#xe012;</i>周订餐班级汇总</a></li>
                    <li><a href="/admin/order4week_school"><i class="icon-font">&#xe012;</i>周订餐学校汇总</a></li>
                    <li><a href="/admin/order4day_class"><i class="icon-font">&#xe012;</i>订餐班级配送汇总</a></li>
                    <li><a href="/admin/order4day_school"><i class="icon-font">&#xe012;</i>订餐学校配送汇总</a></li>
                    <li><a href="orders.html"><i class="icon-font">&#xe005;</i>确认订餐单</a></li>
                    <li><a href="monthRefund.html"><i class="icon-font">&#xe006;</i>月总退餐表</a></li>
                    <li><a href="statistics.html"><i class="icon-font">&#xe052;</i>数据统计</a></li>
                </ul>
            </li>
            <li>
                <a href="#"><i class="icon-font">&#xe018;</i>其他操作</a>
                <ul class="sub-menu">
                    <li><a href="/admin/news"><i class="icon-font">&#xe017;</i>新闻公告管理</a> </li>
                    <li><a href="deliveryMen.html"><i class="icon-font">&#xe017;</i>送餐员管理</a></li>

                    <li><a href="/admin/about?type=show"><i class="icon-font">&#xe004;</i>关于本平台</a></li>                    <li><a href="/admin/rules"><i class="icon-font">&#xe017;</i>订餐规则设置</a> </li>
                    <li><a href="/admin/message?type=show&page=1"><i class="icon-font">&#xe004;</i>客户意见反馈</a></li>
                </ul>
            </li>
        </ul>
    </div>
</div>
<!--/sidebar-->
