<%@page pageEncoding="UTF-8" %>
<%@include file="common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>购买记录</title>
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
                <li><a href="${ctx}/">首页</a></li>
                <li><a href="${ctx}/ware/query">商品</a></li>
                <li class="active"><a href="${ctx}/order/query">购买记录</a></li>
            </ul>
        </div>
    </div>
</div>
<!-- Nav End-->
<!-- Main Start -->
<div class="container">
    <div>
        <table class="table table-bordered table-hover table-striped">
            <thead>
                <tr>
                    <th class="span1">订单号</th>
                    <th class="span1">客户</th>
                    <th class="span1">电话</th>
                    <th class="span3">地址</th>
                    <th class="span1">联系人</th>
                    <th class="span1">订单日期</th>
                    <th class="span1">金额</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${page.content}" var="order">
                    <tr>
                        <td>${order.number}</td>
                        <td>${order.client.name}</td>
                        <td>${order.client.phone}</td>
                        <td>${order.client.address}</td>
                        <td>${order.client.contacts}</td>
                        <td><fmt:formatDate value="${order.createAt}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                        <td style="text-align: right"><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${order.total}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <p:pagination current="${page.page}" pages="${page.totalPages}" page="p">
        <div class="pagination pagination-centered">
            <ul>
                <p:first>
                    <p:clickable><li><a href="${ctx}/order/query?page=${p}&size=${page.size}">&laquo;</a></li></p:clickable>
                    <p:unclickable><li class="disabled"><a>&laquo;</a></li></p:unclickable>
                </p:first>
                <p:previous>
                    <p:clickable><li><a href="${ctx}/order/query?page=${p}&size=${page.size}">&lsaquo;</a></li></p:clickable>
                    <p:unclickable><li class="disabled"><a>&lsaquo;</a></li></p:unclickable>
                </p:previous>
                <p:number>
                    <p:clickable><li><a href="${ctx}/order/query?page=${p}&size=${page.size}">${p}</a></li></p:clickable>
                    <p:unclickable><li class="active"><a>${p}</a></li></p:unclickable>
                </p:number>
                <p:ellipsis><li class="disabled"><a>...</a></li></p:ellipsis>
                <p:next>
                    <p:clickable><li><a href="${ctx}/order/query?page=${p}&size=${page.size}">&rsaquo;</a></li></p:clickable>
                    <p:unclickable><li class="disabled"><a>&rsaquo;</a></li></p:unclickable>
                </p:next>
                <p:last>
                    <p:clickable><li><a href="${ctx}/order/query?page=${p}&size=${page.size}">&raquo;</a></li></p:clickable>
                    <p:unclickable><li class="disabled"><a>&raquo;</a></li></p:unclickable>
                </p:last>
            </ul>
        </div>
    </p:pagination>
</div>
<!-- Main End -->
<script src="${ctx}/assets/js/jquery.js"></script>
<script src="${ctx}/assets/js/bootstrap.js"></script>
</body>
</html>
