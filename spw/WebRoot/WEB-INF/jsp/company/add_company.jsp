<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@include file="../public/public_head3.jsp"%>
<link rel="stylesheet" href="${ctx}/static/css/public/add3.css" />
<link rel="stylesheet" href="${ctx}/static/css/public/sub_add.css" />
<link rel="stylesheet" href="${ctx}/static/css/staff/add_data.css" />
<link rel="stylesheet" href="${ctx}/static/css/public/sub_add.css" />
</head>
<body>
	<div class="btns">
		<button type="button" id="add_submit_btn">提交</button>
	</div>
	<div class="blank_box">
		公司名称： <input type="text" style="width: 250px" name="name" placeholder="请输入 公司名称" class="long" value="${entity.name}"/>
		 管理员手机号码： <input type="text" style="width: 250px" name="managerPhone" placeholder="请输入管理员手机号码" class="long" value="${entity.managerPhone}"/>
		<input type="hidden" id="id" name="id" value="${entity.id}" />
	</div>
	<div class="blank_box">
		<div class="title">公司logo</div>
		<table class="achievement_authority" id="achievement_authority">
			<tbody>
				<tr>
					<td>
						<div class="choose_pic">
							<img class="ms_feedback" src="${ctx}/static/img/staff/1.png" id = "logo">
							<input type="hidden" name="logo" value="">
						</div>
						<p>
							图片比例 2:3 <br>
							<button type="button" class="ms_upload_file_btn btn_blue">浏览</button>
						</p>
					</td>
				</tr>
			</tbody>
		</table>
	</div>

	<%@include file="../public/public_script3.jsp"%>
<form class="hide" id="submit_form" method="post" action="${ctx}/upload/images" target="exec_target" enctype="multipart/form-data">
    <input type="file" id="replace_input" name="files">
</form>
<iframe id="exec_target" class="hide" name="exec_target"></iframe>
	<script src="${ctx}/static/js/public/public_add3.js"></script>
	<script src="${ctx}/static/js/company/add_company.js"></script>

</body>
</html>