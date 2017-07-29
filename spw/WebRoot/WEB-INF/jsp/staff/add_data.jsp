<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@include file="../public/public_head3.jsp" %>
    <link rel="stylesheet" href="${ctx}/static/css/public/add3.css" />
    <link rel="stylesheet" href="${ctx}/static/css/public/sub_add.css" />
    <link rel="stylesheet" href="${ctx}/static/css/staff/add_data.css" />
</head>
<body>
    <div class="btns">
        <button type="button" id="add_submit_btn">提交</button>
    </div>
    <div class="blank_box">
	    <c:if test="${empty modifyFlag}">
		        分配人员查询：
	        <input type="text" style="width: 250px" placeholder="请输入身份证/岗位号/姓名/手机号" id="staff" class="long" />
	        <button type="button"  class="btn" id="search">搜索</button>
	        <span class="message_ti hide">查询结果较多，若查询人员不在列表中，请完善信息！</span>
	        <table class="staff_table hide" id="staff_table">
	            <thead>
	                <tr>
	                    <th>选择</th>
	                    <th>姓名</th>
	                    <th>岗位号</th>
	                    <th>职位</th>
	                </tr>
	            </thead>
	            <tbody id="staff_tbody"></tbody>
	        </table>
	    </c:if>
	    <c:if test="${!empty modifyFlag}">
	    	<table class="staff_table" id="staff_table">
	            <thead>
	                <tr>
	               		<th></th>
	                    <th>姓名</th>
	                    <th>岗位号</th>
	                    <th>职位</th>
	                </tr>
	            </thead>
	            <tbody id="staff_tbody">
	           		<tr>
		            	<td><div class="selected" data-id="${staff.id}"></div></td>
		            	<td>${staff.name}</td>
		            	<td>${staff.workNum}</td>
		            	<td>${staff.positionName}</td>
	            	</tr>
	            </tbody>
	        </table>
	    </c:if>
    </div>
    <div class="blank_box">
        <div class="title">业务业管数据权限</div>
        <table class="achievement_authority" id="achievement_authority">
            <tbody>
                <tr>
                
                    <td><div class="un_selected <c:if test="${dataType == 1}">selected</c:if>"></div>总公司权限</td>
                    <td>
                        <div class="box <c:if test="${dataType == 2}">hide</c:if>">
                            <span class="can">可多选：</span>
                            <ul class="lines" id="lines">
                                <li  id="all"><div class="line un_checked"></div><span id="quanxuan">全选</span></li>
								<c:forEach var="item" items="${lineType}">
                                	<li><div class="line childline ${item.checkFlag}" data-id="${item.id}"></div>${item.name}</li>
								</c:forEach>
                            </ul>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td><div class="un_selected <c:if test="${dataType == 2}">selected</c:if>"></div>分公司权限</td>
                    <td>
                        <ul class="box fen" id="fen" <c:if test="${dataType == 2}">style="display: block;"</c:if>>
                        <c:forEach var="item" items="${lineData}">
                            <li data-id="${item.id}">
                                <div class="first">
									<span class="name">${item.name}</span>
                                    <select data-id="${item.checkId }">
                                        <option value="" >请选择</option>
                                        <c:forEach var="subItem" items="${item.subList}">
                                       		<option value="${subItem.id}" <c:if test="${item.checkId == subItem.id}">selected</c:if> <c:if test="${subItem.disabledFlag == false}">disabled</c:if>>${subItem.name}</option>
                                        </c:forEach>
                                    </select>
                                    <button type="button" class="blue select_duxunqu">请选择督训区</button>
                                    <button type="button" class="green add_authority">+</button>
                                    <span class="text">增加权限</span>
                                </div>
                                <c:if test="${!empty item.orgList}">
	                                <div class="duxunqu <c:if test="${empty item.orgList}">hide</c:if>">
	                                    <c:forEach var="subItem2" items="${item.orgList}">
		                                	<span data-id="${subItem2.id}">${subItem2.name}</span>
	                                    </c:forEach>
	                                </div>
                                </c:if>
                            </li>
						</c:forEach>
                        </ul>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="blank_box">
        <div class="title">部门数据权限</div>
        <button type="button" class="blue" id="select_department">选择部门</button>
        <div class="department_div" id="department_div" <c:if test="${dataType3 == 3}">style="display: block;"</c:if>>
             <c:forEach var="subItem" items="${selectDepartments}">
        		<span data-id="${subItem.id}">${subItem.name}</span>
             </c:forEach>
        </div>
    </div>
    <div class="inner_box" id="inner_box">
        <div class="box_title"><span class="title"><span id="line_name"></span> —— <span id="pro"></span></span><span class="close_btn close">X</span><span class="select_all">全选</span></div>
        <ul id="duxunqus"></ul>
        <div class="box_foot">
            <button id="confirm_duxunqu">确认</button>
            <button class="close">取消</button>
        </div>
    </div>

    <div class="inner_box" id="department_box">
        <div class="box_title"><span class="title">选择部门</span><span class="close_btn close">X</span><span class="select_all">全选</span></div>
        <ul id="departments">
            <c:forEach var="subItem" items="${departments}">
	           	<li data-id="${subItem.id}" title= "${subItem.name}">${subItem.name}</li>
            </c:forEach>
        </ul>
        <div class="box_foot">
            <button id="confirm_department">确认</button>
            <button class="close">取消</button>
        </div>
    </div>

<%@include file="../public/public_script3.jsp" %>
<script src="${ctx}/static/js/public/public_add3.js"></script>
<script src="${ctx}/static/js/staff/add_data.js"></script>

</body>
</html>