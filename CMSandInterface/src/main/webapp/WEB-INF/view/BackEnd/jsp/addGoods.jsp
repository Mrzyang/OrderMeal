<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加套餐</title>
    <link rel="stylesheet" type="text/css" href="/BackEnd-style/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="/BackEnd-style/css/main.css"/>
    <script type="text/javascript" src="/BackEnd-style/js/libs/modernizr.min.js"></script>
    <script src="/BackEnd-style/js/jquery.js"></script>
    <script src="/BackEnd-style/js/layer.js"></script>
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
                <form action="/admin/goods?type=add" method="post" id="myform" name="myform" enctype="multipart/form-data">
                    <table class="insert-tab" width="100%">
                        <tbody>
                        <tr>
                            <th><i class="require-red">*</i>名称：</th>
                            <td>
                                <input class="common-text required" id="title" name="goodsName" size="50" value="${goodsName}" type="text">
                            </td>
                        </tr>
                        <tr>
                            <th width="120"><i class="require-red">*</i>价格：</th>
                            <td><input type="text" value="${goodsPrice}" name="goodsPrice"/>
                            </td>
                        </tr>
                        <tr>
                            <th width="120"><i class="require-red">*</i>菜品：</th>
                            <td>
                                <span>主荤:</span>
                                <select name="product1" id="product1" class="required">
                                    <option value="-1">无</option>
                                    <c:forEach items="${productlist1}" var="item">
                                        <option value="${item}" ${product1==item?'selected':''}>${item}</option>
                                    </c:forEach>
                                </select>
                                <span>辅荤:</span>
                                <select name="product2" id="product2" class="required">
                                    <option value="-1">无</option>
                                    <c:forEach items="${productlist2}" var="item">
                                        <option value="${item}" ${product2==item?'selected':''}>${item}</option>
                                    </c:forEach>
                                </select>
                                <span>素菜:</span>
                                <select name="product3" id="product3" class="required">
                                    <option value="-1">无</option>
                                    <c:forEach items="${productlist3}" var="item">
                                        <option value="${item}" ${product3==item?'selected':''}>${item}</option>
                                    </c:forEach>
                                </select>
                                <span>水果饮品:</span>
                                <select name="product4" id="product5" class="required">
                                    <option value="-1">无</option>
                                    <c:forEach items="${productlist4}" var="item">
                                        <option value="${item}" ${product4==item?'selected':''}>${item}</option>
                                    </c:forEach>
                                </select>
                                <span>辅荤(选填):</span>
                                <select name="product5" id="product4" class="required">
                                    <option value="-1">无</option>
                                    <c:forEach items="${productlist2}" var="item">
                                        <option value="${item}" ${product5==item?'selected':''}>${item}</option>
                                    </c:forEach>
                                </select>
                                <span style="color: red">${productMistake}</span>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                <i class="require-red">*</i>缩略图：</th>
                            <td>
                                <input type=file name="img" id="img" onchange="javascript:setImagePreview();">
                                <img id="preview" width=-1 height=-1 style="diplay:none" />
                            </td>
                        </tr>
                        <tr>
                            <th></th>
                            <td>
                                <button class="btn btn-primary btn6 mr10" id="btn-submit">提交</button>
                                <input class="btn btn6" onclick="history.go(-1)" value="返回" type="button">
                            </td>
                        </tr>
                        </tbody></table>
                </form>
                <!--END 新增商品的表单-->
                <c:forEach var="item" items="${Mistake}">
                    <p style="color: red;">*${item}</p>
                </c:forEach>
            </div>
        </div>

    </div>
</div>
</body>
</html>

<%--<script>
    function doUpload() {
        var formData = new FormData($("#myform")[0]);
        console.log(formData.get("type"));
        if(!formData.get("goodsName")){
            alert("请输入套餐名！");
            return;
        }
        if(!formData.get("goodsPrice")){
            alert("请输入价格！");
            return;
        }
        // if(!formData.get("product1") || !formData.get("product2") || !formData.get("product3") || !formData.get("product5")){
        //     alert("请选择必选菜品！");
        //     return;
        // }
        $.ajax({
            url: '/admin/goods' ,  /*这是处理文件上传的servlet*/
            type: 'POST',
            data: formData,
            dataType: 'json',
            //async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (msg) {
                if (msg.status == 1) {
                    layer.msg(msg.data);
                } else {
                    alert("adwad2");
                    layer.msg(msg.data);
                }
            }
        });
    }
</script>--%>

<script type="text/javascript">
    function setImagePreview() {
        var docObj = document.getElementById("img");
        var imgObjPreview = document.getElementById("preview");
        if (docObj.files && docObj.files[0]) {
            //火狐下，直接设img属性
            imgObjPreview.style.display = 'block';
            imgObjPreview.style.width = '150px';
            imgObjPreview.style.display="inline-block";
            imgObjPreview.style.marginLeft="10px";
            //imgObjPreview.style.height = '120px';
            //imgObjPreview.src = docObj.files[0].getAsDataURL();
            //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式
            imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
        } else {
            //IE下，使用滤镜
            docObj.select();
            var imgSrc = document.selection.createRange().text;
            var localImagId = document.getElementById("localImag");
            //必须设置初始大小
            localImagId.style.width = "50px";
            //localImagId.style.height = "200px";
            //图片异常的捕捉，防止用户修改后缀来伪造图片
            try {
                localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
                localImagId.filters
                    .item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
            } catch (e) {
                alert("您上传的图片格式不正确，请重新选择!");
                return false;
            }
            imgObjPreview.style.display = 'none';
            document.selection.empty();
        }
        return true;
    }
</script>
