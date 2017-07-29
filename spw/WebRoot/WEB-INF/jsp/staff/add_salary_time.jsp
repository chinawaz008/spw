<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("ctx", request.getContextPath());%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${ctx}/static/css/public/add3.css" />
	<script type="text/javascript" src="${ctx }/static/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<div class="body">
	<form id="add_salaryTime_form" method="post">
		<div class="theme_lay">
			<div class="theme">
				<label class="main_theme"><c:if test="${!empty flag}">修改</c:if>
				<c:if test="${empty flag}">新增</c:if>佣金结算日期</label> <label class="sub_theme">（请录入佣金结算日期信息）</label>
		</div>
		<div class="btns">
            <button type="submit" id="btn_submit" class="btn_green" data-v="${flag}"><i class="iconfont icon-zhengque"></i> 提交</button>
        </div>
	</div>
	<div class="tab_lay">
		<ul class="tab_controller">
			<li class="active"><i class="iconfont icon-jibenxinxi"></i>
				基本信息</li>
		</ul>
		<div class="tab_bodies">
			<div class="tab_body">
				<table>
					<tbody id="Tbl">
						<tr>
							<td class="item_name">当前年月：</td>
							<td><input type="Text" class="required" data-c="当前年月" name="nowDate" id="nowDate" value="${entity.nowDate}" onfocus="WdatePicker({isShowWeek:true,readOnly:true,dateFmt:'yyyy-MM'})" >
							</td>
						</tr>
						 <tr>
							<td class="item_name">预收开始日期：</td>
							<td><input type="Text" class="required" data-c="预收开始日期" name="ysTimeBegin" id="ysTimeBegin" value="${entity.ysTimeBegin}" onfocus="WdatePicker({isShowWeek:true,readOnly:true,maxDate:'#F{$dp.$D(\'ysTimeEnd\')}'})" >
							</td>
							<td class="item_name">预收结束日期：</td>
							<td><input type="Text" class="required" data-c="预收结束日期" name="ysTimeEnd" id="ysTimeEnd" value="${entity.ysTimeEnd}" onfocus="WdatePicker({isShowWeek:true,readOnly:true,minDate:'#F{$dp.$D(\'ysTimeBegin\')}'})">
							</td>
						</tr>
						<tr>
							<td class="item_name">承保开始日期：</td>
							<td><input type="Text" class="required" data-c="承保开始日期" name="acceptTimeBegin" id="acceptTimeBegin" value="${entity.acceptTimeBegin}" onfocus="WdatePicker({isShowWeek:true,readOnly:true,maxDate:'#F{$dp.$D(\'acceptTimeEnd\')}'})" >
							</td>
							<td class="item_name">承保结束日期：</td>
							<td><input type="Text" class="required" data-c="承保结束日期" name="acceptTimeEnd" id="acceptTimeEnd" value="${entity.acceptTimeEnd}" onfocus="WdatePicker({isShowWeek:true,readOnly:true,minDate:'#F{$dp.$D(\'acceptTimeBegin\')}'})">
							</td>
						</tr>
						<tr>
							<td class="item_name">回执开始日期：</td>
							<td><input type="Text" class="required" data-c="回执开始日期" name="hzTimeBegin" id="hzTimeBegin" value="${entity.hzTimeBegin}" onfocus="WdatePicker({isShowWeek:true,readOnly:true,maxDate:'#F{$dp.$D(\'hzTimeEnd\')}'})" >
							</td>
							<td class="item_name">回执结束日期：</td>
							<td><input type="Text" class="required" data-c="回执结束日期" name="hzTimeEnd" id="hzTimeEnd" value="${entity.hzTimeEnd}" onfocus="WdatePicker({isShowWeek:true,readOnly:true,minDate:'#F{$dp.$D(\'hzTimeBegin\')}'})">
							</td>
						</tr>
						<tr>
							<td class="item_name">回访开始日期：</td>
							<td><input type="Text" class="required" data-c="回访开始日期" name="hfTimeBegin" id="hfTimeBegin" value="${entity.hfTimeBegin}" onfocus="WdatePicker({isShowWeek:true,readOnly:true,maxDate:'#F{$dp.$D(\'hfTimeEnd\')}'})" >
							</td>
							<td class="item_name">回访结束日期：</td>
							<td><input type="Text" class="required" data-c="回访结束日期" name="hfTimeEnd" id="hfTimeEnd" value="${entity.hfTimeEnd}" onfocus="WdatePicker({isShowWeek:true,readOnly:true,minDate:'#F{$dp.$D(\'hfTimeBegin\')}'})">
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</form>
</div>
<%@include file="../public/public_script3.jsp"%>
<script src="${ctx}/static/js/public/public_add3.js"></script>
<script	src="${ctx}/static/js/staff/add_salary_time.js"></script>
</body>
</html>