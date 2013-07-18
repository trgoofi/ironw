<%@page pageEncoding="UTF-8" %>
<%@include file="common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>商品</title>
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
                <li class="active"><a href="${ctx}/ware/query">商品</a></li>
                <li><a href="${ctx}/order/query">购买记录</a></li>
            </ul>
        </div>
    </div>
</div>
<!-- Nav End-->
<!-- Main Start -->
<div class="container">
    <div class="input-append text-center query-box">
        <form action="${ctx}/ware/query" method="get">
            <input type="hidden" id="typeahead-url" value="${ctx}/ware/query/json">
            <input type="text" name="keyword" value="${param.keyword}" accesskey="s" id="typeahead" class="span3">
            <button class="btn" type="submit">搜索</button>
        </form>
    </div>
    <div class="ware">
        <table class="table table-bordered table-hover table-striped">
            <thead>
                <tr>
                    <th class="span2">编号</th>
                    <th class="span3">名称</th>
                    <th class="span1">单位</th>
                    <th class="span1">数量</th>
                    <th class="span1">进货价</th>
                    <th class="span1">最平价</th>
                    <th class="span1">工程价</th>
                    <th class="span1">零售价</th>
                    <th class="span1"><a id="create" class="btn btn-small btn-primary">新建</a></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${page.content}" var="ware">
                    <tr>
                        <td>${ware.code}</td>
                        <td>${ware.name}</td>
                        <td>${ware.units}</td>
                        <td><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${ware.inventory}" /></td>
                        <td><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${ware.price.cost}" /></td>
                        <td><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${ware.price.bottom}" /></td>
                        <td><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${ware.price.wholesale}" /></td>
                        <td><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${ware.price.retail}" /></td>
                        <td><a href="${ctx}/ware/${ware.id}/json" class="btn btn-info btn-small update">更新</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <p:pagination current="${page.page}" pages="${page.totalPages}" page="p">
        <div class="pagination pagination-centered">
            <ul>
                <p:first>
                    <p:clickable><li><a href="${ctx}/ware/query?page=${p}&size=${page.size}&keyword=${param.keyword}">&laquo;</a></li></p:clickable>
                    <p:unclickable><li class="disabled"><a>&laquo;</a></li></p:unclickable>
                </p:first>
                <p:previous>
                    <p:clickable><li><a href="${ctx}/ware/query?page=${p}&size=${page.size}&keyword=${param.keyword}">&lsaquo;</a></li></p:clickable>
                    <p:unclickable><li class="disabled"><a>&lsaquo;</a></li></p:unclickable>
                </p:previous>
                <p:number>
                    <p:clickable><li><a href="${ctx}/ware/query?page=${p}&size=${page.size}&keyword=${param.keyword}">${p}</a></li></p:clickable>
                    <p:unclickable><li class="active"><a>${p}</a></li></p:unclickable>
                </p:number>
                <p:ellipsis><li class="disabled"><a>...</a></li></p:ellipsis>
                <p:next>
                    <p:clickable><li><a href="${ctx}/ware/query?page=${p}&size=${page.size}&keyword=${param.keyword}">&rsaquo;</a></li></p:clickable>
                    <p:unclickable><li class="disabled"><a>&rsaquo;</a></li></p:unclickable>
                </p:next>
                <p:last>
                    <p:clickable><li><a href="${ctx}/ware/query?page=${p}&size=${page.size}&keyword=${param.keyword}">&raquo;</a></li></p:clickable>
                    <p:unclickable><li class="disabled"><a>&raquo;</a></li></p:unclickable>
                </p:last>
            </ul>
        </div>
    </p:pagination>
    <div id="modal-container"></div>
</div>
<!-- Main End -->

<script id="update-tpl" type="text/template">
    <div id="ware-modal" class="modal hide fade" tabindex="-1" role="dialog" aria-hidden="true">
        <form action="${ctx}/stock" id="stock" method="post" class="form-horizontal">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4>{{:name}}</h4>
            </div>
            <div class="modal-body">
                <div class="control-group">
                    <label class="control-label">数量</label>
                    <div class="controls">
                        <input type="text" name="ware.inventory">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">进货价</label>
                    <div class="controls">
                        <input type="text" name="ware.price.cost" value="{{:price.cost}}">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">最平价</label>
                    <div class="controls">
                        <input type="text" name="ware.price.bottom" value="{{:price.bottom}}">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">工程价</label>
                    <div class="controls">
                        <input type="text" name="ware.price.wholesale" value="{{:price.wholesale}}">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">零售价</label>
                    <div class="controls">
                        <input type="text" name="ware.price.retail" value="{{:price.retail}}">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
                <button type="submit" class="btn btn-primary">更新</button>
            </div>
            <input type="hidden" name="ware.id" value="{{:id}}"/>
            <input type="hidden" name="ware.code" value="{{:code}}"/>
            <input type="hidden" name="ware.name" value="{{:name}}"/>
            <input type="hidden" name="ware.units" value="{{:units}}"/>
        </form>
    </div>
</script>
<script id="success-tpl" type="text/template">
    <div id="success" class="modal hide fade alert-success" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="text-center">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h4>操作成功</h4>
        </div>
    </div>
</script>
<script id="error-tpl" type="text/template">
    <div id="error" class="modal hide fade alert-error" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="text-center">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h4>操作失败</h4>
        </div>
    </div>
</script>
<script id="query-tpl" type="text/template">
    <div class="text-left">
        <strong>{{:code}}</strong>
        <strong>{{:name}}</strong>
    </div>
</script>
<script id="create-tpl" type="text/template">
    <div id="ware-modal" class="modal hide fade" tabindex="-1" role="dialog" aria-hidden="true">
        <form action="${ctx}/stock" id="stock" method="post" class="form-horizontal">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4>新建商品</h4>
            </div>
            <div class="modal-body">
                <div class="control-group">
                    <label class="control-label">编号</label>
                    <div class="controls">
                        <input type="text" name="ware.code">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">名称</label>
                    <div class="controls">
                        <input type="text" name="ware.name">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">单位</label>
                    <div class="controls">
                        <input type="text" name="ware.units">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">数量</label>
                    <div class="controls">
                        <input type="text" name="ware.inventory">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">进货价</label>
                    <div class="controls">
                        <input type="text" name="ware.price.cost">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">最平价</label>
                    <div class="controls">
                        <input type="text" name="ware.price.bottom">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">工程价</label>
                    <div class="controls">
                        <input type="text" name="ware.price.wholesale">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">零售价</label>
                    <div class="controls">
                        <input type="text" name="ware.price.retail">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
                <button type="submit" class="btn btn-primary">新建</button>
            </div>
        </form>
    </div>
</script>

<script src="${ctx}/assets/js/jquery.js"></script>
<script src="${ctx}/assets/js/bootstrap.js"></script>
<script src="${ctx}/assets/js/jsrender.js"></script>
<script src="${ctx}/assets/js/ware.js"></script>
</body>
</html>
