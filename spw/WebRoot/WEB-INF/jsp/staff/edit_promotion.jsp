<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("ctx", request.getContextPath());%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${ctx}/static/css/public/add3.css" />
    <script type="text/javascript" src="${ctx }/static/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
    <div class="box">
            <div class="body">
                <div class="theme_lay">
                    <div class="btn_div">
                        <button type="button" class="btn_green" id="edit_pro_btn" ><i class="iconfont icon-zhengque"></i> 保存</button>
                    </div>
                </div>
                <div class="tab_lay">
                    <ul class="tab_controller">
                        <li class="active"><i class="iconfont icon-gongzuojingliline"></i> 晋升申请书</li>
                    </ul>
                    <div class="tab_bodies">
                        
                        <div class="tab_body">
                            <form id="promotion_form">
                                <table>
                                    <tbody>
                                        <tr>
                                            <td class="item_name">姓名：</td>
                                            <td>
                                                <input type="text" placeholder="输入姓名或工号" id="name_num" disabled="disabled" value="${entity.name }">
                                                <input type="hidden" id="worknum" name="worknum"></input>
                                                <input type="hidden" value="${entity.id}" name="id"></input>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="item_name">当前职级：</td>
                                            <td><input type="text" id="current_position" value="${entity.position }" disabled="disabled"></td>
                                            <td class="item_name">予晋升职级：</td>
                                            <td>
                                                <select name="toJob" data-v="${entity.toJob}">
                                                	<c:forEach var="item" items="${list }" varStatus="st">
                                                   			 <option value="${item }">分部经理</option>
                                                	</c:forEach>
                                                </select>
                                            </td>
                                            <td class="item_name">晋升开始时间：</td>
                                            <td><input type="text" class="input_m ms_datepicker" name="promotionBeginTime" value="${entity.promotionBeginTime }"
                                            onfocus="WdatePicker({isShowWeek:true,readOnly:true,minDate:'#F{$dp.$D(\'promotionEndTime\')}'})"
                                            id="promotionBeginTime" 
                                            ></td>
                                            <td class="item_name">计划达成时间：</td>
                                            <td><input type="text" class="input_m ms_datepicker" name="promotionEndTime" value="${entity.promotionEndTime }"
                                            onfocus="WdatePicker({isShowWeek:true,readOnly:true,minDate:'#F{$dp.$D(\'promotionBeginTime\')}'})"
                                             id="promotionEndTime" 
                                             placeholder="最多三个月"></td>
                                        </tr>
                                        <tr class="tips_tr">
                                            <td class="item_name">工作概况：</td>
                                            <td colspan="7">
                                                <textarea name="workSummary" placeholder="现任职务及现任岗位工作情况简介">${entity.workSummary }</textarea>
                                            </td>
                                        </tr>
                                        <tr class="tips_tr">
                                            <td class="item_name">申请晋升理由：</td>
                                            <td colspan="7">
                                                <textarea name="promotionReason" placeholder="1、自己的实力及主要业绩表现；2、晋升后的工作计划与发展目标">${entity.promotionReason }</textarea>
                                            </td>
                                        </tr>
                                        <tr class="tips_tr">
                                            <td class="item_name">对晋升职位的&nbsp;&nbsp;&nbsp;<br>信息与期望：</td>
                                            <td colspan="7">
                                               <textarea name="expectation">${entity.expectation }</textarea>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </form>
                        </div>

                    </div>
                </div>
            </div>
        </div>
<%@include file="../public/public_script3.jsp" %>
<script src="${ctx}/static/js/public/public_add.js"></script>
<script src="${ctx}/static/js/staff/add_promotion.js"></script>

</body>
</html>