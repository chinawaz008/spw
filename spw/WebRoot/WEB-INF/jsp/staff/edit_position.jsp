<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@include file="../public/public_head.jsp" %>
    <title>民盛后援人员调岗</title>
    <link rel="stylesheet" href="${ctx}/static/css/public/add.css" />
    <link rel="stylesheet" href="${ctx}/static/css/staff/add_staff_support.css" />
</head>
<body>
    <div class="box">
        <%@include file="../public/public_side.jsp" %>
        <div class="main">
            <%@include file="../public/public_title.jsp" %>
            <div class="crumbs">
                <ul>
                    <li><a href="${ctx}/"><i class="iconfont icon-zhuye"></i> 首页</a></li>
                    <li>人员管理</li>
                    <li>后援人员调岗</li>
                </ul>
            </div>
            <div class="body">
            	<form id="organization_form">
                <div class="theme_lay">	
                    <div class="theme">
                        <label class="main_theme">后援人员调岗——编辑</label>
                        <label class="sub_theme">（在搜索框中输入工号或姓名，确定调岗人员后，填写相关信息）</label>
                    </div>
                    <div class="btns">
                        <button type="button" class="btn_blue goback"><i class="iconfont icon-quxiao"></i> 返回</button>
                        <button type="button" class="btn_orange reload"><i class="iconfont icon-shuaxin"></i> 恢复</button>
                        <button type="button" class="btn_green" id="edit_pro_btn" ><i class="iconfont icon-zhengque"></i> 保存</button>
                    </div>
                </div>
                <div class="tab_lay">
                    <ul class="tab_controller">
                        <li class="active"><i class="iconfont icon-gongzuojingliline"></i> 人员调岗</li>
                    </ul>
                    <div class="tab_bodies">
                        
                        <div class="tab_body">
                                <table>
                                    <tbody>
                                        <tr>
                                            <td class="item_name" style="width:150px">姓名：</td>
                                            <td>
                                                <input class="required" type="text" placeholder="输入姓名或工号" id="name_num"  disabled="disabled" value="${entity.worknum }">
                                                <input type="hidden" id="worknum" name="worknum" value="${entity.worknum}"></input>
                                                <input type="hidden" value="${entity.id}" name="id"></input>
                                            </td>
                                            <td>
                                                <button type="button" class="btn_blue" id="search_name_num" ><i class="iconfont icon-sousuo"></i> 搜索</button>
                                            </td>
                                            <td id="tips_td" class="tips_td" colspan="8"></td>
                                        </tr>
                                        <tr>
                                            <td class="item_name">当前职级：</td>
                                            <td><input class="required" type="text" id="current_position" value="${entity.currentJob}" readonly="readonly"></td>
                                        </tr>
                                    </tbody>

                                </table>

                                <table>
                                    <tbody>
                                        
                                        <tr>
                                            <td class="item_name" style="width:150px">公司：</td>
                                            <td>
                                                <select class="select_m" id="company_type" data-v="1" name="companyType">
                                                	<option value="1">总公司</option>
                                                	<option value="2">分公司</option>
    						                    </select>
                                               <%--  <select class="select_m <c:if test='${entity.lineType==1}' >hide</c:if>" data-c="条线" id="line_type" name="lineID" data-v="${staff.lineType}">
                                                	<c:forEach items="${lineType }" var="item">
                                                		<option value="${item.id }" <c:if test="${entity.lineType eq item.id}">selected="selected"</c:if>>${item.name}</option>
                                                	</c:forEach>
                                                </select>
                                                <select class="select_m <c:if test='${entity.lineType==1}' >hide</c:if>" data-c="分公司名称" id="sub_company" name="subComID" data-v="${staff.lineType}">
                                                	<c:forEach items="${comList }" var="item">
                                                		<option value="${item.id }"  <c:if test="${entity.branchCompanyId eq item.id}">selected="selected"</c:if>>${item.compName}</option>
                                                	</c:forEach>
                                                </select> --%>
                                            </td>
                                        </tr>
                                        <tr class="" id="position_ready">
                                            <td class="item_name">已选择的岗位：</td>
                                            <td id="position_ready_td">
                                            <c:forEach items="${bumenList}" var="item" varStatus="st">
	                                            	<div data-id="${item.position}" data-route="${item.orgType}" class="selected_position">
	                                            		${item.positionName}<i class="iconfont icon-cuowu"></i>
	                                            	</div>
                                            </c:forEach>
                                            </td>
                                        </tr>
                                        
                                        <tr class="select_position_tr hide">
                                            <td class="item_name">部门：</td>
                                            <td id="section_tr"></td>
                                        </tr>
                                        <tr class="select_position_tr hide">
                                            <td class="item_name">岗位：</td>
                                            <td>
                                                <select id="position_select" class="select_m" class="required">
                                                    <option value="">请选择...</option>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                        <td class="item_name">
                                                <button type="button" class="btn_orange" id="add_gangwei">岗位 + </button>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                        </div>
                    </div>
                </div>
                </form>
            </div>
        </div>
    </div>
<%@include file="../public/public_script.jsp" %>
<script src="${ctx}/static/js/public/public_add.js"></script>
<script src="${ctx}/static/js/staff/add_position.js"></script>
<script src="${ctx}/static/js/plugins/msdatepicker/msdatepicker.js"></script>

</body>
</html>