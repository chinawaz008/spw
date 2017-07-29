<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	request.setAttribute("ctx", request.getContextPath());
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@include file="../public/public_head_list.jsp"%>
<script type="text/javascript" src="${ctx }/static/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
	<div class="body">
		<div class="theme_lay">
			<div class="theme">
				<label class="main_theme">公司管理管理</label> <label class="sub_theme">（管理所有公司）</label>
			</div>
			<div class="btns">
				<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
					<c:if test="${item == 198}">
						<button type="button" class="btn_blue open_box"
							data-src="${ctx}/company/add_company" data-type="新增公司">
							<i class="iconfont icon-jiahao"></i> 新增
						</button>
					</c:if>
				</c:forEach>
			</div>
		</div>
		<div class="search_lay">
			<div class="controller_div">
				<i class="iconfont icon-sousuo-sousuo"> |</i> 查询筛选 <i
					class="iconfont right_icon icon-shangla" id="right_icon"></i>
			</div>
			<form id="search_form">
				<div class="search_div" id="search_div">
					<input type="text" name="name" placeholder="请输入公司名称" /> <input
						type="text" class="input_m ms_datepicker" id="beginTime"
						name="beginDate" value=""
						onfocus="WdatePicker({isShowWeek:true,readOnly:true,maxDate:'#F{$dp.$D(\'endTime\')}'})"
						placeholder="创建开始日期" /> -- <input type="text"
						class="input_m ms_datepicker" id="endTime" name="endDate" value=""
						onfocus="WdatePicker({isShowWeek:true,readOnly:true,minDate:'#F{$dp.$D(\'beginTime\')}'})"
						placeholder="创建结束日期" />
					<button type="button" class="btn_green search_btn">
						<i class="iconfont icon-sousuo-sousuo"></i> 搜索
					</button>
				</div>
			</form>
		</div>
		<div class="table_lay">
			<div class="table_window" data-style="overflow-x: scroll;">
				<table data-style="width:1300px">
					<thead>
						<tr>
							<th>公司名称</th>
							<th>管理员手机号码</th>
							<th>创建时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="flexible_tbody">
						<tr class="templet hide" name="staffSupportTr">
							<td>@{name}@</td>
							<td>@{managerPhone}@</td>
							<td>@{createTime}@</td>
							<td><c:forEach var="item" items="${buttonAuthorization }"
									varStatus="st">
									<c:if test="${item == 199}">
										<button type="button" class="btn_green open_box"
											data-src="${ctx}/company/update?id=@{id}@"
											data-type="编辑人员权限">编辑</button>
									</c:if>
								</c:forEach></td>
						</tr>
					</tbody>
				</table>
			</div>
			<ul class="pagination" id="pagers"></ul>
			<i class="clear"></i>
		</div>
	</div>
	<%@include file="../public/public_script3.jsp"%>
	<script src="${ctx}/static/js/public/public_list3.js"></script>
	<script src="${ctx}/static/js/company/list.js"></script>
</body>
</html>