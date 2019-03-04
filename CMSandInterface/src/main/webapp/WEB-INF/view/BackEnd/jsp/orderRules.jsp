<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加菜品</title>
    <link rel="stylesheet" type="text/css" href="/BackEnd-style/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="/BackEnd-style/css/main.css"/>
    <script type="text/javascript" src="/BackEnd-style/js/libs/modernizr.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="/BackEnd-style/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="/BackEnd-style/ueditor/ueditor.all.js"> </script>
    <script type="text/javascript" src="/BackEnd-style/js/jquery.js"></script>
    <script>
        function save() {
            if(confirm("是否确定修改？")){
                $.ajax({
                    type:"POST",
                    dataType:"json",
                    url:"/admin/rules",
                    data:$('#rulesForm').serialize(),
                    success:function (result) {
                        if(result){
                            alert("恭喜您修改成功！");
                            window.location.reload();
                        }else {
                            alert("对不起跟新失败，请重试！");
                        }
                    },
                    error:function () {
                        alert("出现异常：请检查操作是否符合规范");
                    }
                });
            }
            else {
                return false;
            }
        }
        function defaultRules() {
            if(confirm("是否确定恢复默认设置？")){
                var type = "default";
                $.ajax({
                   type:"POST",
                   dataType:"json",
                   url:"/admin/rules",
                   data:{"type":type},
                   success:function (result) {
                     if(result){
                         alert("已恢复默认设置!");
                         window.location.reload();
                     }
                     else alert("服务器或网络异常，操作失败，请重试！")
                   } ,
                    error:function () {
                       alert("出现异常！");
                    }
                });
            }else {
                return false;
            }
        }
    </script>
</head>
<body>
<c:import url="public/head.jsp"></c:import>
<div class="container clearfix">
    <c:import url="public/sideMenu.jsp"></c:import>
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="/jscss/admin/design/">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="/jscss/admin/design/">商品管理</a><span class="crumb-step">&gt;</span><span>新增商品</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <!--START 新增商品的表单-->
                <form action="##" method="post" id="rulesForm" name="rulesForm" onsubmit="return false">
                    <div class="result-title">
                        <div class="result-list">
                            <a href="##" onclick="defaultRules()"><i class="icon-font">&#xe017;</i>恢复默认设置</a>
                        </div>
                    </div>
                    <input type="hidden" name="type" value="save">
                    <table class="insert-tab" width="100%">
                        <thead>
                        <tr>
                           <td colspan="2" style="text-align: center;color: #080808">订餐规则设置</td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th><i class="require-red">*</i>订餐时间限制：</th>
                            <td>
                                用户应至少提前
                                <input id="orderTime" name="orderTime" type="number" style="text-align: center;color: red;width: 80px;" value="${rules.orderTime}" placeholder="填数字">
                                小时进行订餐
                            </td>
                        </tr>
                        <tr>
                            <th><i class="require-red">*</i>转餐时间：</th>
                            <td>
                                用户应至少提前
                                <input id="changeTime" name="changeTime" type="number"  style="text-align: center;color: red;width: 80px;" value="${rules.changeTime}" placeholder="填数字">
                                小时进行转餐
                            </td>
                        </tr>
                        <tr>
                            <th><i class="require-red">*</i>退餐时间：</th>
                            <td>
                                用户应至少提前
                                <input id="returnTime" name="returnTime" type="number" style="text-align: center;color: red;width: 80px;"  value="${rules.returnTime}"  placeholder="填数字">
                                小时进行退餐
                            </td>
                        </tr>
                        <tr>
                            <th><i class="require-red">*</i>订餐次数限制：</th>
                            <td>
                                每个用户每天可订餐次数为
                                <input id="orderTimes" name="orderTimes" type="number" style="text-align: center;color: red;width: 80px;"  value="${rules.orderTimes}" placeholder="填数字">
                                次
                            </td>
                        </tr>
                        <tr>
                            <th><i class="require-red">*</i>转餐次数限制：</th>
                            <td>
                                每个用户每天可转餐次数为
                                <input id="changeTimes" name="changeTimes" type="number" style="text-align: center;color: red;width: 80px;"  value="${rules.changeTimes}" placeholder="填数字">
                                次
                            </td>
                        </tr>
                        <tr>
                            <th><i class="require-red">*</i>退餐次数限制：</th>
                            <td>
                                每个用户每天可退餐次数为
                                <input id="returnTimes" name="returnTimes" type="number" style="text-align: center;color: red;width: 80px;"  value="${rules.returnTimes}" placeholder="填数字">
                                次
                            </td>
                        </tr>
                        <tr>
                            <th></th>
                            <td>
                                <button class="btn btn-primary btn6 mr10" onclick="save()">修改</button>
                                <input class="btn btn6" onclick="history.go(-1)" value="返回" type="button">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>
                <!--END 新增商品的表单-->
            </div>
        </div>

    </div>
    <!--/main-->
</div>
</body>
</html>