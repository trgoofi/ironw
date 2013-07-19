<%@page pageEncoding="UTF-8" %>
<%@include file="common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>${siteName}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${ctx}/assets/css/bootstrap.css" rel="stylesheet">
    <link href="${ctx}/assets/css/style.css" rel="stylesheet">
</head>

<body>
<!-- Nav Start-->
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="brand" href="${ctx}/">${siteName}</a>
            <ul class="nav">
                <li class="active"><a href="${ctx}/">首页</a></li>
                <li><a href="${ctx}/ware/query">商品</a></li>
                <li><a href="${ctx}/order/query">购买记录</a></li>
            </ul>
        </div>
    </div>
</div>
<!-- Nav End-->
<!-- Main Start -->
<div class="container">
    <div class="text-center query-box">
        <input type="hidden" id="typeahead-url" value="${ctx}/ware/query/json">
        <input type="text" accesskey="s" id="typeahead" class="span4" autocomplete="off">
    </div>
    <div class="cart">
        <form action="${ctx}/order/create" method="post">
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
                    <th class="span1">操作</th>
                </tr>
            </thead>
            <tbody>
            </tbody>
            <tfoot>
                <tr>
                    <td colspan="8" style="text-align: right;">
                        <span>总金额：</span>
                        <strong id="total" class="text-info"></strong>
                        <a href="${ctx}/" class="btn btn-small margin-cancel">取消</a>
                        <button type="submit" class="btn btn-primary btn-small">结算</button>
                    </td>
                </tr>
            </tfoot>
        </table>
        </form>
    </div>
</div>
<!-- Main End -->

<script id="item-tpl" type="text/template">
    <tr>
        <td>{{:code}}</td>
        <td>{{:name}}</td>
        <td>{{:units}}</td>
        <td>
            <input type="hidden" name="items[{{:index}}].id" value="{{:id}}"/>
            <input type="text" name="items[{{:index}}].quantity" value="1" data-toggle="tooltip" title="{{:inventory}}" id="{{:id}}-quantity" class="span1 change-price" />
        </td>
        <td>
            <div class="input-prepend">
                <span class="add-on" data-toggle="tooltip" title="{{:price.cost}} | {{:price.bottom}} | {{:price.wholesale}}">￥</span>
                <input type="text" name="items[{{:index}}].price" value="{{:price.retail}}" id="{{:id}}-price" class="span1 change-price" />
            </div>
        </td>
        <td>{{:price.retail}}</td>
        <td id="{{:id}}-subtotal">{{:price.retail}}</td>
        <td><a class="btn btn-small btn-danger" data-id="{{:id}}">删除</a></td>
    </tr>
</script>
<script id="query-tpl" type="text/template">
    <div class="text-left">
        <strong>{{:code}}</strong>
        <strong>{{:name}}</strong>
    </div>
</script>

<script src="${ctx}/assets/js/jquery.js"></script>
<script src="${ctx}/assets/js/bootstrap.js"></script>
<script src="${ctx}/assets/js/jsrender.js"></script>
<script src="${ctx}/assets/js/cart.js"></script>
</body>
</html>
