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
	<div class="theme_lay">
		<div class="theme">
			<label class="main_theme">业务人员审核查询</label> <label class="sub_theme"></label>
		</div>
		<div class="btns">
			<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
				<c:if test="${item == 54}">
					<button type="button" onclick="exportOrder();" class="btn_green"><i class="iconfont icon-sousuo"></i> 导出</button> 
				</c:if> 
			</c:forEach>
		</div>
	</div>
	<div class="search_lay">
		<div class="controller_div">
			<i class="iconfont icon-sousuo-sousuo"> |</i> 查询筛选 <i
				class="iconfont right_icon icon-shangla" id="right_icon"></i>
		</div>
		<form method="post" id="search_form" name="myform">
			<div class="search_div" id="search_div">
				人员姓名 <input type="text" name="name" id="name" placeholder="请输入人员姓名" />
				手机号 <input type="text" name="phoneNum" placeholder="请输入手机号">
				身份证号 <input type="text" name="idCard" placeholder="请输入身份证号">
				打印纸质收据 &nbsp;<input type="checkbox"  name="isShow" value="1"/>
				<br /> 审核状态 <select   name="status">
					<option value="">--请选择--</option>
					<option value="0">未审核</option>
					<option value="1">已通过</option>
					<option value="2">未通过</option>
					<option value="3">已撤回</option>
					<option value="4">已入司</option>
				</select>
				支付状态 <select   name="orgType">
					<option value="">--请选择--</option>
					<option value="0">未支付</option>
					<option value="1">已支付</option>
					<option value="2">处理中</option>
					<option value="3">已退款</option>
				</select>
				
				 申请时间 <input type="text" class="input_m ms_datepicker"
					id="beginTime" name="beginTime" value=""
					onfocus="WdatePicker({isShowWeek:true,readOnly:true,maxDate:'#F{$dp.$D(\'endTime\')}'})"
					placeholder="申请开始日期" /> -- <input type="text"
					class="input_m ms_datepicker" id="endTime" name="endTime" value=""
					onfocus="WdatePicker({isShowWeek:true,readOnly:true,minDate:'#F{$dp.$D(\'beginTime\')}'})"
					placeholder="申请结束日期" />
				审核时间 <input type="text" class="input_m ms_datepicker"
					id="beginDate" name="beginDate" value=""
					onfocus="WdatePicker({isShowWeek:true,readOnly:true,maxDate:'#F{$dp.$D(\'endDate\')}'})"
					placeholder="审核开始日期" /> -- <input type="text"
					class="input_m ms_datepicker" id="endDate" name="endDate" value=""
					onfocus="WdatePicker({isShowWeek:true,readOnly:true,minDate:'#F{$dp.$D(\'beginDate\')}'})"
					placeholder="审核结束日期" />
					<br /><%@include file="../public/public_select_font.jsp" %>
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
						<th>渠道</th>
						<th>分公司</th>
						<th>督训区</th>
						<th>分部</th>
						<th>姓名</th>
						<th>职级</th>
						<th>状态</th>
						<th>支付状态</th>
						<th>申请日期</th>
						<th>推荐人</th>
						<th>审核人</th>
						<th>审核日期</th>
						<th>支付方式</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="flexible_tbody">
					<tr class="templet hide" name="staffSupportTr">
						<td 
							<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
								<c:if test="${item == 53}">
										data-src="${ctx}/staff/detail/@{id}@" data-type="查看" class="open_box"
								</c:if> 
							</c:forEach>
						>@{lineType}@</td>
						<td>@{branCompanyName}@</td>
						<td>@{countyFranchiseesName}@</td>
						<td>@{storeName}@</td>
						<td>@{name}@</td>
						<td>@{position}@</td>
						<td>@{status}@</td>
						<td>^{'@{costStatus}@' =='0' ^^ 未支付 }^ 
						   ^{'@{costStatus}@'  =='1' ^^ 已支付 }^
						   ^{'@{costStatus}@'  =='2' ^^ 处理中 }^
						   ^{'@{costStatus}@'  =='3' ^^ 已退款 }^
						</td>
						<td>@{applyTime}@</td>
						<td>@{referId}@</td>
						<td>@{checkStaffId}@</td>
						<td>@{updateTime}@</td>
						<td>@{payType}@</td>
						<td>
							<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
								<c:if test="${item == 49}">
									^{'@{status}@' =='未审核' ^^
									 <button data-src="${ctx}/staff/check/@{id}@" data-type="审核" type="button"  class="btn_green open_box" >审核</button>
									 }^
								</c:if>
							</c:forEach>
							
							<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
								<c:if test="${item == 50}">
									^{'@{costStatus}@' =='0' && '@{status}@' =='申请通过'^^
										<button type="button" onclick="pay(@{id}@);" class="btn_orange">支付</button>
									 }^  
								</c:if> 
							</c:forEach>
							
							<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
								<c:if test="${item == 51}">
									^{'@{costStatus}@' =='2' && '@{status}@' =='申请通过'^^
										<button type="button" onclick="fresh(@{id}@);" class="btn_blue">刷新</button>
									 }^
								</c:if> 
							</c:forEach>
							<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
								<c:if test="${item == 52}">
									<a href="javascript:;">
											<button type="button" onclick="removes(@{id}@)" class="btn_red">删除</button>
									</a>
								</c:if> 
							</c:forEach>
							<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
								<c:if test="${item == 55}">
									^{'@{status}@' =='已入司'^^
									<button type="button" data-src="${ctx}/staff/printContract/@{id}@" data-type="打印合同" class="btn_green open_box" >打印合同</button>
									}^
								</c:if> 
							</c:forEach>
							 ^{'@{costStatus}@' =='1' && '@{status}@' !='已入司' && '@{tradeInfo}@'!=''^^
								<button type="button" onclick="returnMoney(@{id}@)"   class="btn_orange" >退款</button>
							}^ 
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<ul class="pagination" id="pagers"></ul>
		<i class="clear"></i>
	</div>

	<%@include file="../public/public_script3.jsp"%>
	<script src="${ctx}/static/js/public/public_list3.js"></script>
	<script src="${ctx}/static/js/staff/list_staff_apply.js"></script>
</body>
</html>