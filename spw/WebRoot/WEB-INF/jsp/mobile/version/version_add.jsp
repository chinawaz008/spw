<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@include file="../../public/public_head_list.jsp" %>
<link rel="stylesheet" href="${ctx}/static/css/public/add.css" />
</head>
<body data-message="${message}">
<div class="theme_lay">
	<div class="theme">
		<label class="main_theme">版本更新</label>
	</div>
	<div class="btns">
		<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
			<c:if test="${item == 172}">
				<button type="button"
				class="btn_blue" id="btnAdd">
				<i class="iconfont icon-arrow121112"></i> 上传
		</button>
			</c:if> 
		</c:forEach>
	</div>
</div>
<div class="tab_lay">
	<ul class="tab_controller">
		<li class="active"><i class="iconfont icon-wanshanziliao"></i>
			APK更新</li>
	</ul>
	<input type="hidden" id="typeNo" name="typeNo"
		value="${entity.typeId}" /> <input type="hidden" id="flag"
		value="${flag}">
	<div class="tab_bodies">
		<div class="tab_body">
			<form action="${ctx}/version/saveVersion" method="post" enctype="multipart/form-data" id="upform">
			<table style="width: 50%">
				<tbody id="Tbl">
							<input type="hidden" id="top_version" value="${version.version}">
							<tr>
								<td class="item_name">历史版本号：</td>
								<td>${version.version}</td>
							</tr>
							<tr>
								<td class="item_name">上次更新日期：</td>
								<td>${version.date}</td>
							</tr>
							<tr>
								<td class="item_name">版本号：</td>
								<td>
                                    <input type="text" placeholder="大于上次版本号" id="version" name="version" />
                                </td>
							</tr>
							<tr>
								<td class="item_name">APK文件：</td>
                                <td>
                                    <input type="file" name="apkFile" id="apkFile" >
                                </td>
							</tr>

						</tbody>
					</table>
					</form>  
				</div>
			</div>
</div>
<%@include file="../../public/public_script3.jsp"%>
<script src="${ctx}/static/js/mobile/version/version.js"></script>
<script src="${ctx}/static/js/public/public_add.js"></script>
</body>
</html>