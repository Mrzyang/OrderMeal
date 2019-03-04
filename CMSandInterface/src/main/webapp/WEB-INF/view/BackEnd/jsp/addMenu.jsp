<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加菜谱</title>
    <link rel="stylesheet" type="text/css" href="/BackEnd-style/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="/BackEnd-style/css/main.css"/>
    <script type="text/javascript" src="/BackEnd-style/js/libs/modernizr.min.js"></script>
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
                <p style="color:#ffab41">${addMistake}</p>
                <!--START 新增商品的表单-->
                <form action="/admin/menu" method="post" id="myform" name="myform">
                    <input type="hidden" name="type" value="add">
                    <table class="insert-tab" width="100%">
                        <tbody>
                        <tr>
                            <th></th>
                            <th>星期一</th>
                            <th>星期二</th>
                            <th>星期三</th>
                            <th>星期四</th>
                            <th>星期五</th>
                            <th>星期六</th>
                            <th>星期日</th>
                        </tr>
                        <tr>
                            <td>午餐</td>
                            <td>
                                <span>套餐一:</span>
                                <select name="value1" class="required">
                                    <option value="-1">无</option>
                                    <c:forEach items="${goodsList}" var="item">
                                        <option value="${item.goodsId}-${item.goodsName}-${item.goodsPrice}-${item.productNames}">${fn:length(item.goodsName)>4?fn:substring(item.goodsName,0,4):item.goodsName}${fn:length(item.goodsName)>4?'...':''} ${item.goodsPrice}元</option>
                                    </c:forEach>
                                </select><br>
                                <span>套餐二:</span>
                                <select name="value8" class="required">
                                    <option value="-1">无</option>
                                    <c:forEach items="${goodsList}" var="item">
                                        <option value="${item.goodsId}-${item.goodsName}-${item.goodsPrice}-${item.productNames}">${fn:length(item.goodsName)>4?fn:substring(item.goodsName,0,4):item.goodsName}${fn:length(item.goodsName)>4?'...':''} ${item.goodsPrice}元</option>                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <span>套餐一:</span>
                                <select name="value2" class="required">
                                    <option value="-1">无</option>
                                    <c:forEach items="${goodsList}" var="item">
                                        <option value="${item.goodsId}-${item.goodsName}-${item.goodsPrice}-${item.productNames}">${fn:length(item.goodsName)>4?fn:substring(item.goodsName,0,4):item.goodsName}${fn:length(item.goodsName)>4?'...':''} ${item.goodsPrice}元</option>
                                    </c:forEach>
                                </select><br>
                                <span>套餐二:</span>
                                <select name="value9" class="required">
                                    <option value="-1">无</option>
                                    <c:forEach items="${goodsList}" var="item">
                                        <option value="${item.goodsId}-${item.goodsName}-${item.goodsPrice}-${item.productNames}">${fn:length(item.goodsName)>4?fn:substring(item.goodsName,0,4):item.goodsName}${fn:length(item.goodsName)>4?'...':''} ${item.goodsPrice}元</option>                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <span>套餐一:</span>
                                <select name="value3" class="required">
                                    <option value="-1">无</option>
                                    <c:forEach items="${goodsList}" var="item">
                                        <option value="${item.goodsId}-${item.goodsName}-${item.goodsPrice}-${item.productNames}">${fn:length(item.goodsName)>4?fn:substring(item.goodsName,0,4):item.goodsName}${fn:length(item.goodsName)>4?'...':''} ${item.goodsPrice}元</option>
                                    </c:forEach>
                                </select><br>
                                <span>套餐二:</span>
                                <select name="value10" class="required">
                                    <option value="-1">无</option>
                                    <c:forEach items="${goodsList}" var="item">
                                        <option value="${item.goodsId}-${item.goodsName}-${item.goodsPrice}-${item.productNames}">${fn:length(item.goodsName)>4?fn:substring(item.goodsName,0,4):item.goodsName}${fn:length(item.goodsName)>4?'...':''} ${item.goodsPrice}元</option>                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <span>套餐一:</span>
                                <select name="value4" class="required">
                                    <option value="-1">无</option>
                                    <c:forEach items="${goodsList}" var="item">
                                        <option value="${item.goodsId}-${item.goodsName}-${item.goodsPrice}-${item.productNames}">${fn:length(item.goodsName)>4?fn:substring(item.goodsName,0,4):item.goodsName}${fn:length(item.goodsName)>4?'...':''} ${item.goodsPrice}元</option>
                                    </c:forEach>
                                </select><br>
                                <span>套餐二:</span>
                                <select name="value11" class="required">
                                    <option value="-1">无</option>
                                    <c:forEach items="${goodsList}" var="item">
                                        <option value="${item.goodsId}-${item.goodsName}-${item.goodsPrice}-${item.productNames}">${fn:length(item.goodsName)>4?fn:substring(item.goodsName,0,4):item.goodsName}${fn:length(item.goodsName)>4?'...':''} ${item.goodsPrice}元</option>                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <span>套餐一:</span>
                                <select name="value5" class="required">
                                    <option value="-1">无</option>
                                    <c:forEach items="${goodsList}" var="item">
                                        <option value="${item.goodsId}-${item.goodsName}-${item.goodsPrice}-${item.productNames}">${fn:length(item.goodsName)>4?fn:substring(item.goodsName,0,4):item.goodsName}${fn:length(item.goodsName)>4?'...':''} ${item.goodsPrice}元</option>
                                    </c:forEach>
                                </select><br>
                                <span>套餐二:</span>
                                <select name="value12" class="required">
                                    <option value="-1">无</option>
                                    <c:forEach items="${goodsList}" var="item">
                                        <option value="${item.goodsId}-${item.goodsName}-${item.goodsPrice}-${item.productNames}">${fn:length(item.goodsName)>4?fn:substring(item.goodsName,0,4):item.goodsName}${fn:length(item.goodsName)>4?'...':''} ${item.goodsPrice}元</option>                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <span>套餐一:</span>
                                <select name="value6" class="required">
                                    <option value="-1">无</option>
                                    <c:forEach items="${goodsList}" var="item">
                                        <option value="${item.goodsId}-${item.goodsName}-${item.goodsPrice}-${item.productNames}">${fn:length(item.goodsName)>4?fn:substring(item.goodsName,0,4):item.goodsName}${fn:length(item.goodsName)>4?'...':''} ${item.goodsPrice}元</option>
                                    </c:forEach>
                                </select><br>
                                <span>套餐二:</span>
                                <select name="value13" class="required">
                                    <option value="-1">无</option>
                                    <c:forEach items="${goodsList}" var="item">
                                        <option value="${item.goodsId}-${item.goodsName}-${item.goodsPrice}-${item.productNames}">${fn:length(item.goodsName)>4?fn:substring(item.goodsName,0,4):item.goodsName}${fn:length(item.goodsName)>4?'...':''} ${item.goodsPrice}元</option>                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <span>套餐一:</span>
                                <select name="value7" class="required">
                                    <option value="-1">无</option>
                                    <c:forEach items="${goodsList}" var="item">
                                        <option value="${item.goodsId}-${item.goodsName}-${item.goodsPrice}-${item.productNames}">${fn:length(item.goodsName)>4?fn:substring(item.goodsName,0,4):item.goodsName}${fn:length(item.goodsName)>4?'...':''} ${item.goodsPrice}元</option>
                                    </c:forEach>
                                </select><br>
                                <span>套餐二:</span>
                                <select name="value14" class="required">
                                    <option value="-1">无</option>
                                    <c:forEach items="${goodsList}" var="item">
                                        <option value="${item.goodsId}-${item.goodsName}-${item.goodsPrice}-${item.productNames}">${fn:length(item.goodsName)>4?fn:substring(item.goodsName,0,4):item.goodsName}${fn:length(item.goodsName)>4?'...':''} ${item.goodsPrice}元</option>                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>晚餐</td>
                            <td>
                                <span>套餐一:</span>
                                <select name="value15" class="required">
                                    <option value="-1">无</option>
                                    <c:forEach items="${goodsList}" var="item">
                                        <option value="${item.goodsId}-${item.goodsName}-${item.goodsPrice}-${item.productNames}">${fn:length(item.goodsName)>4?fn:substring(item.goodsName,0,4):item.goodsName}${fn:length(item.goodsName)>4?'...':''} ${item.goodsPrice}元</option>
                                    </c:forEach>
                                </select><br>
                                <span>套餐二:</span>
                                <select name="value22" class="required">
                                    <option value="-1">无</option>
                                    <c:forEach items="${goodsList}" var="item">
                                        <option value="${item.goodsId}-${item.goodsName}-${item.goodsPrice}-${item.productNames}">${fn:length(item.goodsName)>4?fn:substring(item.goodsName,0,4):item.goodsName}${fn:length(item.goodsName)>4?'...':''} ${item.goodsPrice}元</option>                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <span>套餐一:</span>
                                <select name="value16" class="required">
                                    <option value="-1">无</option>
                                    <c:forEach items="${goodsList}" var="item">
                                        <option value="${item.goodsId}-${item.goodsName}-${item.goodsPrice}-${item.productNames}">${fn:length(item.goodsName)>4?fn:substring(item.goodsName,0,4):item.goodsName}${fn:length(item.goodsName)>4?'...':''} ${item.goodsPrice}元</option>
                                    </c:forEach>
                                </select><br>
                                <span>套餐二:</span>
                                <select name="value23" class="required">
                                    <option value="-1">无</option>
                                    <c:forEach items="${goodsList}" var="item">
                                        <option value="${item.goodsId}-${item.goodsName}-${item.goodsPrice}-${item.productNames}">${fn:length(item.goodsName)>4?fn:substring(item.goodsName,0,4):item.goodsName}${fn:length(item.goodsName)>4?'...':''} ${item.goodsPrice}元</option>                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <span>套餐一:</span>
                                <select name="value17" class="required">
                                    <option value="-1">无</option>
                                    <c:forEach items="${goodsList}" var="item">
                                        <option value="${item.goodsId}-${item.goodsName}-${item.goodsPrice}-${item.productNames}">${fn:length(item.goodsName)>4?fn:substring(item.goodsName,0,4):item.goodsName}${fn:length(item.goodsName)>4?'...':''} ${item.goodsPrice}元</option>
                                    </c:forEach>
                                </select><br>
                                <span>套餐二:</span>
                                <select name="value24" class="required">
                                    <option value="-1">无</option>
                                    <c:forEach items="${goodsList}" var="item">
                                        <option value="${item.goodsId}-${item.goodsName}-${item.goodsPrice}-${item.productNames}">${fn:length(item.goodsName)>4?fn:substring(item.goodsName,0,4):item.goodsName}${fn:length(item.goodsName)>4?'...':''} ${item.goodsPrice}元</option>                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <span>套餐一:</span>
                                <select name="value18" class="required">
                                    <option value="-1">无</option>
                                    <c:forEach items="${goodsList}" var="item">
                                        <option value="${item.goodsId}-${item.goodsName}-${item.goodsPrice}-${item.productNames}">${fn:length(item.goodsName)>4?fn:substring(item.goodsName,0,4):item.goodsName}${fn:length(item.goodsName)>4?'...':''} ${item.goodsPrice}元</option>
                                    </c:forEach>
                                </select><br>
                                <span>套餐二:</span>
                                <select name="value25" class="required">
                                    <option value="-1">无</option>
                                    <c:forEach items="${goodsList}" var="item">
                                        <option value="${item.goodsId}-${item.goodsName}-${item.goodsPrice}-${item.productNames}">${fn:length(item.goodsName)>4?fn:substring(item.goodsName,0,4):item.goodsName}${fn:length(item.goodsName)>4?'...':''} ${item.goodsPrice}元</option>                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <span>套餐一:</span>
                                <select name="value19" class="required">
                                    <option value="-1">无</option>
                                    <c:forEach items="${goodsList}" var="item">
                                        <option value="${item.goodsId}-${item.goodsName}-${item.goodsPrice}-${item.productNames}">${fn:length(item.goodsName)>4?fn:substring(item.goodsName,0,4):item.goodsName}${fn:length(item.goodsName)>4?'...':''} ${item.goodsPrice}元</option>
                                    </c:forEach>
                                </select><br>
                                <span>套餐二:</span>
                                <select name="value26" class="required">
                                    <option value="-1">无</option>
                                    <c:forEach items="${goodsList}" var="item">
                                        <option value="${item.goodsId}-${item.goodsName}-${item.goodsPrice}-${item.productNames}">${fn:length(item.goodsName)>4?fn:substring(item.goodsName,0,4):item.goodsName}${fn:length(item.goodsName)>4?'...':''} ${item.goodsPrice}元</option>                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <span>套餐一:</span>
                                <select name="value20" class="required">
                                    <option value="-1">无</option>
                                    <c:forEach items="${goodsList}" var="item">
                                        <option value="${item.goodsId}-${item.goodsName}-${item.goodsPrice}-${item.productNames}">${fn:length(item.goodsName)>4?fn:substring(item.goodsName,0,4):item.goodsName}${fn:length(item.goodsName)>4?'...':''} ${item.goodsPrice}元</option>
                                    </c:forEach>
                                </select><br>
                                <span>套餐二:</span>
                                <select name="value27" class="required">
                                    <option value="-1">无</option>
                                    <c:forEach items="${goodsList}" var="item">
                                        <option value="${item.goodsId}-${item.goodsName}-${item.goodsPrice}-${item.productNames}">${fn:length(item.goodsName)>4?fn:substring(item.goodsName,0,4):item.goodsName}${fn:length(item.goodsName)>4?'...':''} ${item.goodsPrice}元</option>                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <span>套餐一:</span>
                                <select name="value21" class="required">
                                    <option value="-1">无</option>
                                    <c:forEach items="${goodsList}" var="item">
                                        <option value="${item.goodsId}-${item.goodsName}-${item.goodsPrice}-${item.productNames}">${fn:length(item.goodsName)>4?fn:substring(item.goodsName,0,4):item.goodsName}${fn:length(item.goodsName)>4?'...':''} ${item.goodsPrice}元</option>
                                    </c:forEach>
                                </select><br>
                                <span>套餐二:</span>
                                <select name="value28" class="required">
                                    <option value="-1">无</option>
                                    <c:forEach items="${goodsList}" var="item">
                                        <option value="${item.goodsId}-${item.goodsName}-${item.goodsPrice}-${item.productNames}">${fn:length(item.goodsName)>4?fn:substring(item.goodsName,0,4):item.goodsName}${fn:length(item.goodsName)>4?'...':''} ${item.goodsPrice}元</option>                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                周次：<input name="menuDate" style="width: 120px" type="text">
                            </td>
                            <td>
                                学校
                                <select name="schoolValue" class="required">
                                    <option value="-1">无</option>
                                    <c:forEach items="${schoolList}" var="item">
                                        <option value="${item.schoolId},${item.schoolName}">${fn:length(item.schoolName)>9?fn:substring(item.schoolName,0,9):item.schoolName}${fn:length(item.schoolName)>9?'...':''}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td>
                                <button class="btn btn-primary btn6 mr10" id="btn-submit">提交</button>
                            </td>
                            <td>
                                <input class="btn btn6" onclick="history.go(-1)" value="返回" type="button">
                            </td>
                        </tr>
                        </tbody></table>
                </form>
                <!--END 新增商品的表单-->
            </div>
        </div>

    </div>
</div>
</body>
</html>

