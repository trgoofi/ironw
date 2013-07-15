<%@page pageEncoding="UTF-8" %>
<%@include file="common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>确认订单</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${ctx}/assets/css/bootstrap.css" rel="stylesheet">
    <link href="${ctx}/assets/css/style.css" rel="stylesheet">
</head>

<body>
<!-- Nav Start-->
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="brand" href="${ctx}/">ironw</a>
            <ul class="nav">
                <li class="active"><a href="${ctx}/">首页</a></li>
                <li><a href="${ctx}/ware/query">商品</a></li>
            </ul>
        </div>
    </div>
</div>
<!-- Nav End-->
<!-- Main Start -->
<div class="container">
    <div class="order">
        <form action="${ctx}/order/confirm" method="post">
        <table class="table table-bordered table-hover table-striped">
            <thead>
                <tr>
                    <th class="span2">编号</th>
                    <th class="span3">名称</th>
                    <th class="span1">单位</th>
                    <th class="span1">数量</th>
                    <th class="span1">单价</th>
                    <th class="span1">实价</th>
                    <th class="span1">金额</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${order.items}" var="item">
                    <tr>
                        <td>${item.ware.code}</td>
                        <td>${item.ware.name}</td>
                        <td>${item.ware.units}</td>
                        <td>${item.quantity}</td>
                        <td><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${item.price}" /></td>
                        <td><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${item.ware.price.retail}" /></td>
                        <td><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${item.total}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
            <tfoot>
                <tr>
                    <td colspan="4">
                        <input type="text" class="input-small" name="name" placeholder="客户"/>
                        <input type="text" class="input-small" name="phone" placeholder="电话" />
                        <input type="text" class="input-medium" name="address" placeholder="地址" />
                        <input type="text" class="input-small" name="contacts" placeholder="联系人" />
                    </td>
                    <td colspan="3" style="text-align: right;">

                        <span>总金额：</span>
                        <strong id="total" class="text-info"><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${order.total}" /></strong>
                        <a href="${ctx}/order/cancel" class="btn btn-small btn-margin">取消</a>
                        <a href="${ctx}/order/print" class="btn btn-primary btn-small btn-margin">打单</a>
                        <button type="submit" class="btn btn-primary btn-small btn-margin">结算</button>
                    </td>
                </tr>
            </tfoot>
        </table>
        </form>
    </div>
</div>
<!-- Main End -->
<script src="${ctx}/assets/js/jquery.js"></script>
<script src="${ctx}/assets/js/bootstrap.js"></script>
</body>
</html>
