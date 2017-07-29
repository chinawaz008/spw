<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@include file="../public/public_head_list.jsp"%>
    <title>权限管理</title>
    <link rel="stylesheet" href="${ctx}/static/css/public/zTreeStyle.css" />
    <link rel="stylesheet" href="${ctx}/static/css/staff/authority_manage.css" />
</head>
<body>

    <div class="theme_lay">
        <div class="theme">
            <label class="main_theme">权限管理</label>
            <label class="sub_theme">（管理所有人员权限）</label>
        </div>
        <div class="btns">
        	<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
				<c:if test="${item == 171}">
					<button type="button" class="btn_green" id="save_auth_btn" ><i class="iconfont icon-zhengque"></i> 保存</button>
				</c:if> 
			</c:forEach>
        </div>
    </div>

    <div class="tab_lay">
        <div class="tab_bodies">
            <div class="tab_body">
                <div class="part1">
                    <ul id="treePosition" class="ztree"></ul>
                </div>
                <div class="part2">
                    <ul id="treeAuth" class="ztree">
                        <li style="color: #999">请先在左侧选择岗位</li>
                    </ul>
                </div>
            </div>

        </div>
    </div>
<%@include file="../public/public_script3.jsp" %>
<script src="${ctx}/static/js/plugins/msPositionTree/jquery.ztree.core.min.js"></script>
<script src="${ctx}/static/js/plugins/msPositionTree/jquery.ztree.excheck.min.js"></script>
<script src="${ctx}/static/js/staff/authority_redevelope_manage.js"></script>
</body>
</html>