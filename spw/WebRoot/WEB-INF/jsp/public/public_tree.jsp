<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("ctx", request.getContextPath());%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
   <%@include file="./public_head3.jsp" %>
    <link rel="stylesheet" href="${ctx}/static/css/public/public_tree.css?v_0.0.6">
</head>

<body class="tree">
    <div class="select_person">
        <div class="zTreeDemoBackground left">
            <input id="search" class="search" type="text" placeholder="搜索">
            <ul id="treePosition" class="ztree"></ul>
            <ul id="treeSearch" class="ztree"></ul>
        </div>
        <div class="right">
            <ul id="treeShow" class="ztree"></ul>
        </div>
        <div class="btn">
            <div class="ms_btn cancel">取消</div>
            <div class="ms_btn sure">确定</div>
        </div>
    </div>

    <%@include file="./public_script3.jsp" %>
    <script src="${ctx}/static/js/plugins/msPositionTree/jquery.ztree.core.min.js"></script>
    <script src="${ctx}/static/js/plugins/msPositionTree/jquery.ztree.excheck.min.js"></script>
    <script src="${ctx}/static/js/public/public_tree.js?v-0.2.33"></script>
</body>
 
</html>