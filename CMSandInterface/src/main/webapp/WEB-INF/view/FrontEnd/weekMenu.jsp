<%--
  Created by IntelliJ IDEA.
  User: Mr.Zhang
  Date: 2018/5/14
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
    import="com.orderMeal.utils.getTime"
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/operate.js"></script>
    <script type="text/javascript">
        //alert("${pageContext.request.contextPath}/showMenu");
        $.ajax({
            type: "get",
            url: "/showMenu.do",
            dateType: "json",
            success:function (data) {
                alert(data);
                var menuJson = eval(data);
                alert(menuJson[0].goodsId);
                var table = $("#weekMenu");
                product[] = menuJson[0].split(",");
                table.append('<tr>\n' +
                    '                <td rowspan="4"><%=time.getWeekDay(1)%>'+'</td>\n' +
                    '                <td rowspan="4">周一</td>\n' +
                    '                <td rowspan=2><a href="foodDetail.html">午餐</a></td>\n' +
                    '                <td>15元</td>\n' +
                    '                <td rowspan=2>回锅牛肉</td>\n' +
                    '                <td rowspan=2>豆角肉丝</td>\n' +
                    '                <td rowspan=2>香拌土豆丝</td>\n' +
                    '                <td></td>\n' +
                    '                <td rowspan=2>橙汁</td>\n' +
                    '                <td><button class="btn btn-success">加入购物车</button></td>\n' +
                    '            </tr>\n' +
                    '            <tr>\n' +
                    '                <td>18元</td>\n' +
                    '                <td>牙签肉</td>\n' +
                    '                <td><button class="btn btn-success">加入购物车</button></td>\n' +
                    '            </tr>\n' +
                    '            <!--晚餐-->\n' +
                    '            <tr>\n' +
                    '                <td rowspan=2><a href="foodDetail.html">晚餐</a></td>\n' +
                    '                <td>15元</td>\n' +
                    '                <td rowspan=2>回锅牛肉</td>\n' +
                    '                <td rowspan=2>豆角肉丝</td>\n' +
                    '                <td rowspan=2>香拌土豆丝</td>\n' +
                    '                <td></td>\n' +
                    '                <td rowspan=2>橙汁</td>\n' +
                    '                <td><button class="btn btn-success">加入购物车</button></td>\n' +
                    '            </tr>\n' +
                    '            <tr>\n' +
                    '                <td>18元</td>\n' +
                    '                <td>牙签肉</td>\n' +
                    '                <td><button class="btn btn-success">加入购物车</button></td>\n' +
                    '            </tr>');
                table.append('<tr>\n' +
                    '                <td rowspan="4"><%=time.getWeekDay(2)%>'+'</td>\n' +
                    '                <td rowspan="4">周二</td>\n' +
                    '                <td rowspan=2><a href="foodDetail.html">午餐</a></td>\n' +
                    '                <td>15元</td>\n' +
                    '                <td rowspan=2>回锅牛肉</td>\n' +
                    '                <td rowspan=2>豆角肉丝</td>\n' +
                    '                <td rowspan=2>香拌土豆丝</td>\n' +
                    '                <td></td>\n' +
                    '                <td rowspan=2>橙汁</td>\n' +
                    '                <td><button class="btn btn-success">加入购物车</button></td>\n' +
                    '            </tr>\n' +
                    '            <tr>\n' +
                    '                <td>18元</td>\n' +
                    '                <td>牙签肉</td>\n' +
                    '                <td><button class="btn btn-success">加入购物车</button></td>\n' +
                    '            </tr>\n' +
                    '            <!--晚餐-->\n' +
                    '            <tr>\n' +
                    '                <td rowspan=2><a href="foodDetail.html">晚餐</a></td>\n' +
                    '                <td>15元</td>\n' +
                    '                <td rowspan=2>回锅牛肉</td>\n' +
                    '                <td rowspan=2>豆角肉丝</td>\n' +
                    '                <td rowspan=2>香拌土豆丝</td>\n' +
                    '                <td></td>\n' +
                    '                <td rowspan=2>橙汁</td>\n' +
                    '                <td><button class="btn btn-success">加入购物车</button></td>\n' +
                    '            </tr>\n' +
                    '            <tr>\n' +
                    '                <td>18元</td>\n' +
                    '                <td>牙签肉</td>\n' +
                    '                <td><button class="btn btn-success">加入购物车</button></td>\n' +
                    '            </tr>');
                table.append('<tr>\n' +
                    '                <td rowspan="4"><%=time.getWeekDay(3)%>'+'</td>\n' +
                    '                <td rowspan="4">周三</td>\n' +
                    '                <td rowspan=2><a href="foodDetail.html">午餐</a></td>\n' +
                    '                <td>15元</td>\n' +
                    '                <td rowspan=2>回锅牛肉</td>\n' +
                    '                <td rowspan=2>豆角肉丝</td>\n' +
                    '                <td rowspan=2>香拌土豆丝</td>\n' +
                    '                <td></td>\n' +
                    '                <td rowspan=2>橙汁</td>\n' +
                    '                <td><button class="btn btn-success">加入购物车</button></td>\n' +
                    '            </tr>\n' +
                    '            <tr>\n' +
                    '                <td>18元</td>\n' +
                    '                <td>牙签肉</td>\n' +
                    '                <td><button class="btn btn-success">加入购物车</button></td>\n' +
                    '            </tr>\n' +
                    '            <!--晚餐-->\n' +
                    '            <tr>\n' +
                    '                <td rowspan=2><a href="foodDetail.html">晚餐</a></td>\n' +
                    '                <td>15元</td>\n' +
                    '                <td rowspan=2>回锅牛肉</td>\n' +
                    '                <td rowspan=2>豆角肉丝</td>\n' +
                    '                <td rowspan=2>香拌土豆丝</td>\n' +
                    '                <td></td>\n' +
                    '                <td rowspan=2>橙汁</td>\n' +
                    '                <td><button class="btn btn-success">加入购物车</button></td>\n' +
                    '            </tr>\n' +
                    '            <tr>\n' +
                    '                <td>18元</td>\n' +
                    '                <td>牙签肉</td>\n' +
                    '                <td><button class="btn btn-success">加入购物车</button></td>\n' +
                    '            </tr>')
                table.append('<tr>\n' +
                    '                <td rowspan="4"><%=time.getWeekDay(4)%>'+'</td>\n' +
                    '                <td rowspan="4">周四</td>\n' +
                    '                <td rowspan=2><a href="foodDetail.html">午餐</a></td>\n' +
                    '                <td>15元</td>\n' +
                    '                <td rowspan=2>回锅牛肉</td>\n' +
                    '                <td rowspan=2>豆角肉丝</td>\n' +
                    '                <td rowspan=2>香拌土豆丝</td>\n' +
                    '                <td></td>\n' +
                    '                <td rowspan=2>橙汁</td>\n' +
                    '                <td><button class="btn btn-success">加入购物车</button></td>\n' +
                    '            </tr>\n' +
                    '            <tr>\n' +
                    '                <td>18元</td>\n' +
                    '                <td>牙签肉</td>\n' +
                    '                <td><button class="btn btn-success">加入购物车</button></td>\n' +
                    '            </tr>\n' +
                    '            <!--晚餐-->\n' +
                    '            <tr>\n' +
                    '                <td rowspan=2><a href="foodDetail.html">晚餐</a></td>\n' +
                    '                <td>15元</td>\n' +
                    '                <td rowspan=2>回锅牛肉</td>\n' +
                    '                <td rowspan=2>豆角肉丝</td>\n' +
                    '                <td rowspan=2>香拌土豆丝</td>\n' +
                    '                <td></td>\n' +
                    '                <td rowspan=2>橙汁</td>\n' +
                    '                <td><button class="btn btn-success">加入购物车</button></td>\n' +
                    '            </tr>\n' +
                    '            <tr>\n' +
                    '                <td>18元</td>\n' +
                    '                <td>牙签肉</td>\n' +
                    '                <td><button class="btn btn-success">加入购物车</button></td>\n' +
                    '            </tr>')
                table.append('<tr>\n' +
                    '                <td rowspan="4"><%=time.getWeekDay(5)%>'+'</td>\n' +
                    '                <td rowspan="4">周五</td>\n' +
                    '                <td rowspan=2><a href="foodDetail.html">午餐</a></td>\n' +
                    '                <td>15元</td>\n' +
                    '                <td rowspan=2>回锅牛肉</td>\n' +
                    '                <td rowspan=2>豆角肉丝</td>\n' +
                    '                <td rowspan=2>香拌土豆丝</td>\n' +
                    '                <td></td>\n' +
                    '                <td rowspan=2>橙汁</td>\n' +
                    '                <td><button class="btn btn-success">加入购物车</button></td>\n' +
                    '            </tr>\n' +
                    '            <tr>\n' +
                    '                <td>18元</td>\n' +
                    '                <td>牙签肉</td>\n' +
                    '                <td><button class="btn btn-success">加入购物车</button></td>\n' +
                    '            </tr>\n' +
                    '            <!--晚餐-->\n' +
                    '            <tr>\n' +
                    '                <td rowspan=2><a href="foodDetail.html">晚餐</a></td>\n' +
                    '                <td>15元</td>\n' +
                    '                <td rowspan=2>回锅牛肉</td>\n' +
                    '                <td rowspan=2>豆角肉丝</td>\n' +
                    '                <td rowspan=2>香拌土豆丝</td>\n' +
                    '                <td></td>\n' +
                    '                <td rowspan=2>橙汁</td>\n' +
                    '                <td><button class="btn btn-success">加入购物车</button></td>\n' +
                    '            </tr>\n' +
                    '            <tr>\n' +
                    '                <td>18元</td>\n' +
                    '                <td>牙签肉</td>\n' +
                    '                <td><button class="btn btn-success">加入购物车</button></td>\n' +
                    '            </tr>')
                table.append('<tr>\n' +
                    '                <td rowspan="4"><%=time.getWeekDay(6)%>'+'</td>\n' +
                    '                <td rowspan="4">周六</td>\n' +
                    '                <td rowspan=2><a href="foodDetail.html">午餐</a></td>\n' +
                    '                <td>15元</td>\n' +
                    '                <td rowspan=2>回锅牛肉</td>\n' +
                    '                <td rowspan=2>豆角肉丝</td>\n' +
                    '                <td rowspan=2>香拌土豆丝</td>\n' +
                    '                <td></td>\n' +
                    '                <td rowspan=2>橙汁</td>\n' +
                    '                <td><button class="btn btn-success">加入购物车</button></td>\n' +
                    '            </tr>\n' +
                    '            <tr>\n' +
                    '                <td>18元</td>\n' +
                    '                <td>牙签肉</td>\n' +
                    '                <td><button class="btn btn-success">加入购物车</button></td>\n' +
                    '            </tr>\n' +
                    '            <!--晚餐-->\n' +
                    '            <tr>\n' +
                    '                <td rowspan=2><a href="foodDetail.html">晚餐</a></td>\n' +
                    '                <td>15元</td>\n' +
                    '                <td rowspan=2>回锅牛肉</td>\n' +
                    '                <td rowspan=2>豆角肉丝</td>\n' +
                    '                <td rowspan=2>香拌土豆丝</td>\n' +
                    '                <td></td>\n' +
                    '                <td rowspan=2>橙汁</td>\n' +
                    '                <td><button class="btn btn-success">加入购物车</button></td>\n' +
                    '            </tr>\n' +
                    '            <tr>\n' +
                    '                <td>18元</td>\n' +
                    '                <td>牙签肉</td>\n' +
                    '                <td><button class="btn btn-success">加入购物车</button></td>\n' +
                    '            </tr>')

            }

        });
    </script>
    <title>今日菜单</title>
</head>
<body>

<!--导航栏-->
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#"><img src="images/logo.png" alt="" class="logo"/></a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        菜单信息
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="index.html">今日菜单</a></li>
                        <li class="divider"></li>
                        <li><a href="weekMenu.html">本周菜单</a></li>
                    </ul>
                </li>
                <li><a href="cart.html">购物车</a></li>
                <li><a href="#">我的订单</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        其他信息
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="order.html">公告栏</a></li>
                        <li class="divider"></li>
                        <li><a href="refundRule.html">订/退餐条例</a></li>
                        <li class="divider"></li>
                        <li><a href="#">意见反馈</a></li>
                    </ul>
                </li>
                <li><div style="width: 465px">&nbsp;</div></li>
                <li><a href="#"><span class="glyphicon glyphicon-user"></span> 注册</a></li>
                <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> 登录</a></li>
            </ul>
        </div>
    </div>
</nav>
<!--END 导航栏--->

<div class="container">
    <div id="todayMenu">
        <table class="table table-bordered" id = "weekMenu">
            <tr>
                <th colspan="10">本周菜单  时间: <%! getTime time = new getTime();%><%= time.getMonday()%>----<%=time.getSaturday()%></th>
            </tr>
            <tr>
                <th>日期</th>
                <th>星期</th>
                <th>时段</th>
                <th>价格</th>
                <th>主荤</th>
                <th>配荤</th>
                <th>素菜</th>
                <th>配荤</th>
                <th>水果/饮品</th>
                <th>选购</th>
            </tr>


            <!--start 循环遍历-->


            <!--午餐-->
            <%--<tr>
                <td rowspan="4">5月7日</td>
                <td rowspan="4">周一</td>
                <td rowspan=2><a href="foodDetail.html">午餐</a></td>
                <td>15元</td>
                <td rowspan=2>回锅牛肉</td>
                <td rowspan=2>豆角肉丝</td>
                <td rowspan=2>香拌土豆丝</td>
                <td></td>
                <td rowspan=2>橙汁</td>
                <td><button class="btn btn-success">加入购物车</button></td>
            </tr>
            <tr>
                <td>18元</td>
                <td>牙签肉</td>
                <td><button class="btn btn-success">加入购物车</button></td>
            </tr>
            <!--晚餐-->
            <tr>
                <td rowspan=2><a href="foodDetail.html">晚餐</a></td>
                <td>15元</td>
                <td rowspan=2>回锅牛肉</td>
                <td rowspan=2>豆角肉丝</td>
                <td rowspan=2>香拌土豆丝</td>
                <td></td>
                <td rowspan=2>橙汁</td>
                <td><button class="btn btn-success">加入购物车</button></td>
            </tr>
            <tr>
                <td>18元</td>
                <td>牙签肉</td>
                <td><button class="btn btn-success">加入购物车</button></td>
            </tr>
            <!------------------------------------------------------------------>
            <!--午餐-->
            <tr>
                <td rowspan="4">5月8日</td>
                <td rowspan="4">周二</td>
                <td rowspan=2>午餐</td>
                <td>15元</td>
                <td rowspan=2>回锅牛肉</td>
                <td rowspan=2>豆角肉丝</td>
                <td rowspan=2>香拌土豆丝</td>
                <td></td>
                <td rowspan=2>橙汁</td>
                <td></td>
            </tr>
            <tr>
                <td>18元</td>
                <td>牙签肉</td>
                <td></td>
            </tr>
            <!--晚餐-->
            <tr>
                <td rowspan=2>晚餐</td>
                <td>15元</td>
                <td rowspan=2>回锅牛肉</td>
                <td rowspan=2>豆角肉丝</td>
                <td rowspan=2>香拌土豆丝</td>
                <td></td>
                <td rowspan=2>橙汁</td>
                <td></td>
            </tr>
            <tr>
                <td>18元</td>
                <td>牙签肉</td>
                <td></td>
            </tr>

            <!------------------------------------------------------------------>
            <!--午餐-->
            <tr>
                <td rowspan="4">5月9日</td>
                <td rowspan="4">周二</td>
                <td rowspan=2>午餐</td>
                <td>15元</td>
                <td rowspan=2>回锅牛肉</td>
                <td rowspan=2>豆角肉丝</td>
                <td rowspan=2>香拌土豆丝</td>
                <td></td>
                <td rowspan=2>橙汁</td>
                <td></td>
            </tr>
            <tr>
                <td>18元</td>
                <td>牙签肉</td>
                <td></td>
            </tr>
            <!--晚餐-->
            <tr>
                <td rowspan=2>晚餐</td>
                <td>15元</td>
                <td rowspan=2>回锅牛肉</td>
                <td rowspan=2>豆角肉丝</td>
                <td rowspan=2>香拌土豆丝</td>
                <td></td>
                <td rowspan=2>橙汁</td>
                <td></td>
            </tr>
            <tr>
                <td>18元</td>
                <td>牙签肉</td>
                <td></td>
            </tr>
            <!------------------------------------------------------------------>
            <!--午餐-->
            <tr>
                <td rowspan="4">5月10日</td>
                <td rowspan="4">周四</td>
                <td rowspan=2>午餐</td>
                <td>15元</td>
                <td rowspan=2>回锅牛肉</td>
                <td rowspan=2>豆角肉丝</td>
                <td rowspan=2>香拌土豆丝</td>
                <td></td>
                <td rowspan=2>橙汁</td>
                <td></td>
            </tr>
            <tr>
                <td>18元</td>
                <td>牙签肉</td>
                <td></td>
            </tr>
            <!--晚餐-->
            <tr>
                <td rowspan=2>晚餐</td>
                <td>15元</td>
                <td rowspan=2>回锅牛肉</td>
                <td rowspan=2>豆角肉丝</td>
                <td rowspan=2>香拌土豆丝</td>
                <td></td>
                <td rowspan=2>橙汁</td>
                <td></td>
            </tr>
            <tr>
                <td>18元</td>
                <td>牙签肉</td>
                <td></td>
            </tr>


            <!--end 循环遍历-->--%>

        </table>
    </div>
</div>

<!--Start 底部-->
<div class="footer">
    <div class="panel-footer">
        <ul>
            <li>学校入驻</li>
            <li>配送加盟</li>
            <li>城市代理</li>
        </ul>
        <ul>
            <li>关于水高</li>
            <li>媒体报道</li>
            <li>平台制度</li>
        </ul>
        <ul>
            <li>常见问题</li>
            <li>用户反馈</li>
            <li>诚信举报</li>
        </ul>
        <div class="contactMe">
            <font size="5px"><b>1010-9777</b></font> <br/><br>
            周一至周日 9:00-23:00 <br/>
            客服不受理商务合作 <br/>
        </div>
        <div class="contactMe">
            <img src="images/QR-code.png" alt="" width="80px" style="position: relative;top: -20px;"/>
            <div style="font-size: 14px;display: inline-block">
                <b>关注公众号</b>
                <br><br>
                更多折扣，更多优惠
            </div>
        </div>
    </div>
    <div class="beian">
        ©2015 shuigao.com 京ICP证070791号 京公网安备11010502025545号
    </div>
</div>
<!--End 底部-->
</body>
</html>
