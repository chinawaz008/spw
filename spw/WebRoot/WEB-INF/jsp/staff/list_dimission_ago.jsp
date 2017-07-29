<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@include file="../public/public_head_list.jsp"%>
    <title>人员管理</title>
</head>
<body>
<div class="theme_lay">
	<div class="theme">
		<label class="main_theme">离职人员管理</label> <label class="sub_theme">（查询）</label>
	</div>
	<div class="btns">
	</div>
</div>
<div class="search_lay">
	<div class="controller_div">
		<i class="iconfont icon-sousuo-sousuo"> |</i> 查询筛选 <i
			class="iconfont right_icon icon-shangla" id="right_icon"></i>
	</div>
	<form id="search_form" method="post">
		<div class="search_div" id="search_div">
			<select name="personType" id= "personType">
				<option value="0" selected="selected">业务人员</option>
				<option value="1">后援</option>
			</select>
			<input placeholder="请输入人员姓名" name="name" type="text"></input>
			<input type="text" name="idCard" placeholder="请输入身份证号">
			<input type="text" name="workNum" placeholder="请输入岗位号">
			<input type="text" name="orgName" placeholder="请输入组织名称">
			<br />
			<select  id="branchCompanyId" name="branchCompanyId" >
                     <option value="">请选择省公司</option>
                     <c:forEach items="${comList}" var="itme" varStatus="st">
                         <option value="${itme.provinceId}">${itme.compName}</option>
                     </c:forEach>
			</select>
			
			<br />
			<select class="s_province" id="provinceId" name="provinceId">
				<option value="">请选择省</option>
					<c:forEach items="${plist}" var="itme" varStatus="st">
						<option value="${itme.provinceNo}">${itme.provinceName}</option>
				</c:forEach>
			</select>
			<select class="s_region" name="regionId">
				<option value="">请选择市</option>
			</select>
			<select class="s_county" name="countyId">
				<option value="">请选择区县</option>
			</select>
			<br />
			<%-- <%@include file="../public/public_select.jsp" %> --%>
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
                    <th class="person2">条线</th>
                    <th class="person2">省级机构</th>
                    <th class="person2">督训区</th>
                    <th class="person2">分部</th>
                    <th class="person1 hide">公司类型</th>
                    <th class="person1 hide">公司名称</th>
                    <th>姓名</th>
                    <th>职级</th>
                    <th>手机号码</th>
                    <th>岗位号</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody id="flexible_tbody">
            	<tr class="templet hide" name="insuranceTr">
            		<td class="person2">@{localName}@</td>
					<td class="person2">@{branchCompanyName}@</td>
					<td class="person2">@{countyFranchiseesName}@</td>
					<td class="person2">@{storeName}@</td>
					<td class="person1 hide">@{branchCompanyId}@</td>
					<td class="person1 hide">@{branchCompanyName }@</td>
					<td>@{name }@</td>
					<td>@{positionName }@</td>
					<td>@{phoneNum }@</td>
					<td>@{workNum }@</td>
					<td>
					<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
					<c:if test="${item == 77}">
						^{'@{personType }@'=='1'^^
							<a href="${ctx}/staff/look_support?staffId=@{id}@"><button type="button" class="btn_orange">查看</button></a>^^
							<a href="${ctx}/staff/look?staffId=@{id}@"><button type="button" class="btn_orange">查看</button></a>
						}^
					</c:if> 
					</c:forEach>
					</td>
                </tr>
            </tbody>
        </table>
    </div>
    <ul class="pagination" id="pagers"></ul>
    <i class="clear"></i>
</div> 
<%@include file="../public/public_script3.jsp" %>
<script src="${ctx}/static/js/public/public_list3.js"></script> 
<script src="${ctx}/static/js/staff/list_dimission_ago.js"></script>
</body>
</html>