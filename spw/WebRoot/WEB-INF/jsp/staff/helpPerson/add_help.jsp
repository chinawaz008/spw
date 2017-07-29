<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("ctx", request.getContextPath());%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${ctx}/static/css/public/add3.css" />
    <script type="text/javascript"
	src="${ctx }/static/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
    <div class="body">
     	<form id="add_help_form" method="post" >
        <div class="theme_lay">
            <div class="theme">
                <label class="main_theme"><c:if test="${!empty flag}">修改</c:if><c:if test="${empty flag}">新增</c:if>外省支持人员</label>
                <label class="sub_theme">（请录入外省支持人员的信息）</label>
            </div>
            <div class="btns">
                <button type="submit" id="btn_submit" class="btn_green"><i class="iconfont icon-zhengque"></i> 提交</button>
            </div>
        </div>
        <div class="tab_lay">
            <ul class="tab_controller">
                <li class="active"><i class="iconfont icon-jibenxinxi"></i> 基本信息</li>
            </ul>
            <input type="hidden" name="id" value="${entity.id }" />
            <div class="tab_bodies">
                <div class="tab_body">
                    <table>
                        <tbody id="Tbl">
                            <tr>
                           		<td class="item_name">帮扶人员：</td>
                                <td>
                              	  <select name="worknum" id="worknum" data-v=${entity.worknum } class="select_m required" data-c="帮扶人员">
	                                    <c:forEach items="${plist}" var="cur">
	                                    	<option value="${cur.workNum}" >${cur.name}</option>
	                                    </c:forEach>
                                    </select>
                                </td>
                            	<td class="item_name">帮扶条线：</td>
                                <td>
                                    <select name="lineType" id="line_type" data-v=${entity.lineType } class="select_m required" data-c="帮扶条线" onchange="getProvince()">
	                                    <c:forEach items="${lineType}" var="cur">
	                                    	<option value="${cur.id}" >${cur.name}</option>
	                                    </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td class="item_name">帮扶分公司：</td>
                                <td>
                                    <select id="province" name="branchCompanyId"
                                      onchange="getDXQ()" data-v="${entity.branchCompanyId}" class="select_m required" data-c="帮扶分公司">
                                      <c:forEach items="${comList}" var="itme" varStatus="st">
                                      		<option value="${itme.id}">${itme.compName}</option>
                                      </c:forEach>
                                      </select>
                                </td>
                                <td class="item_name">帮扶督训区：</td>
                                <td>
                                    <select id="county"
                                      name="dxqId" data-v="${entity.dxqId}" class="select_m required" data-c="帮扶督训区">
                                      <option value="">--请选择督训区--</option>
                                      <c:forEach items="${dxqlist}" var="itme" varStatus="st">
                                      		<option value="${itme.id}">${itme.name}</option>
                                      </c:forEach>
                                      </select>
                                </td>
                            </tr>
                            <tr>
								<td class="item_name">开始日期：</td>
								<td><input type="Text" name="helpBeginTime" id="helpBeginTime" class="required" data-c="开始日期" value="${entity.helpBeginTime}" onfocus="WdatePicker({isShowWeek:true,maxDate:'#F{$dp.$D(\'helpEndTime\')}'})" >
								</td>
								<td class="item_name">结束日期：</td>
								<td><input type="Text" name="helpEndTime" id="helpEndTime" value="${entity.helpEndTime}" onfocus="WdatePicker({isShowWeek:true,minDate:'#F{$dp.$D(\'helpBeginTime\')}'})">
								</td>
							</tr>
                       </tbody>
                    </table>
                </div>
        </div>
    </div>
    </form>
<%@include file="../../public/public_script3.jsp" %>
<script src="${ctx}/static/js/public/public_add.js"></script>
<script src="${ctx}/static/js/staff/helpPerson/add.js"></script>
<script src="${ctx}/static/js/plugins/kuidate/MSCalendar.js"></script>
</div>
</body>
</html>