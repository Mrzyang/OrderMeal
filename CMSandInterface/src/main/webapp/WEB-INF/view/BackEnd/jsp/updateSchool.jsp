<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>学校信息管理</title>
    <link rel="stylesheet" type="text/css" href="/BackEnd-style/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="/BackEnd-style/css/main.css"/>
    <script type="text/javascript" src="/BackEnd-style/js/libs/modernizr.min.js"></script>
    <script src="/BackEnd-style/js/jquery.js"></script>
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
                <form method="post" id="myform" name="myform">
                    <input type="hidden" name="type" value="update">
                    <input type="hidden" value="${school.schoolId}" name="schoolId"/>
                    <table class="insert-tab" width="100%">
                        <tbody>
                        <tr>
                            <th><i class="require-red">*</i>学校名：</th>
                            <td>
                                <input class="common-text required" id="schoolName" name="schoolName" size="50" value="${school.schoolName}" type="text">
                                <span style="color: red;">${nameMistake}</span>
                            </td>
                        </tr>
                        <tr>
                            <th width="120"><i class="require-red">*</i>各年级班级数：</th>
                            <td>
                                一年级<input type="text" value="${school.schoolClasstotal1}" name="schoolClasstotal1" style="width: 20px"/>&nbsp;&nbsp;
                                二年级<input type="text" value="${school.schoolClasstotal2}" name="schoolClasstotal2" style="width: 20px"/>&nbsp;&nbsp;
                                三年级<input type="text" value="${school.schoolClasstotal3}" name="schoolClasstotal3" style="width: 20px"/>
                            </td>
                        </tr>
                        <tr>
                            <th width="120"><i class="require-red">*</i>联系方式：</th>
                            <td><input type="text" value="${school.schoolPhone}" name="schoolPhone"/>
                                <span style="color: red;">${phoneMistake}</span>
                            </td>
                        </tr>
                        <tr>
                            <th width="120"><i class="require-red">*</i>学校地址：</th>
                            <td><input type="text" value="${school.schoolAddress}" name="schoolAddress"/>
                                <span style="color: red;">${addressMistake}</span>
                            </td>
                        </tr>
                        <tr>
                            <th></th>
                            <td>
                                <button type="button" class="btn btn-primary btn6 mr10" id="btn-submit">提交</button>
                                <input class="btn btn6" onclick="history.go(-1)" value="返回" type="button">
                            </td>
                        </tr>
                        </tbody></table>
                </form>
                <!--END 新增商品的表单-->
            </div>
        </div>

    </div>
    <!--/main-->
</div>
</body>
</html>



<script>
    $(document).ready(function () {
        $("#btn-submit").click(function () {
            if (!$('#schoolName').val()) {
                alert("请输入学校名！");
                return;
            }
            else {
                $.ajax({
                    type: "post",
                    url: "/admin/school",
                    data: $('#myform').serialize(),
                    dataType: "json",
                    success: function (msg) {
                        if (msg.status == 1) {
                            // layer.msg(msg.data);
                            window.location.href="school";
                        } else {
                            //layer.msg(msg.data);
                        }
                    }
                });
            }
        });
    })
</script>