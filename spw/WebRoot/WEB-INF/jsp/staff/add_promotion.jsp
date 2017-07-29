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
            <div class="body">
                <div class="theme_lay">
                    <div class="btn_div">
                        <button type="button" class="btn_green" id="add_pro_btn" ><i class="iconfont icon-zhengque"></i> 提交</button>
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
                                                <input class="required" type="text" placeholder="输入姓名或工号" id="name_num">
                                                <input type="hidden" id="worknum" name="worknum"></input>
                                            </td>
                                            <td>
                                                <button type="button" class="btn_blue" id="search_name_num" ><i class="iconfont icon-sousuo"></i> 搜索</button>
                                            </td>
                                            <td id="tips_td" class="tips_td" colspan="8"></td>
                                        </tr>
                                        <tr>
                                            <td class="item_name">当前职级：</td>
                                            <td><input class="required" type="text" id="current_position" value="" disabled="disabled"></td>
                                            <td class="item_name">予晋升职级：</td>
                                            <td>
                                                <select name="toJob" id="toJob">
                                                    <!-- <option value="0">请选择</option> -->
                                                    <!--  <option id="job" selected="selected">分部经理</option> -->
                                                    <!-- <option value="3">续期专员</option>
                                                    <option value="2">专训</option> -->
                                                </select>
                                            </td>
                                            <td class="item_name">晋升开始时间：</td>
                                            <td>
                                            <input type="text" class="input_m ms_datepicker required" onfocus="WdatePicker({isShowWeek:true,readOnly:true,minDate:'#F{$dp.$D(\'promotionEndTime\')}'})"
                                            id="promotionBeginTime"	name="promotionBeginTime"></input>
                                            
                                            </td>
                                            <td class="item_name">计划达成时间：</td>
                                            <td><input type="text" class="input_m ms_datepicker required" onfocus="WdatePicker({isShowWeek:true,readOnly:true,minDate:'#F{$dp.$D(\'promotionBeginTime\')}'})"
                                            name="promotionEndTime" id="promotionEndTime" placeholder="最多三个月"></td>
                                        </tr>
                                        <tr class="tips_tr">
                                            <td class="item_name">工作概况：</td>
                                            <td colspan="7">
                                                <textarea name="workSummary" placeholder="现任职务及现任岗位工作情况简介"></textarea>
                                            </td>
                                        </tr>
                                        <tr class="tips_tr">
                                            <td class="item_name">申请晋升理由：</td>
                                            <td colspan="7">
                                                <textarea name="promotionReason" placeholder="1、自己的实力及主要业绩表现；2、晋升后的工作计划与发展目标"></textarea>
                                            </td>
                                        </tr>
                                        <tr class="tips_tr">
                                            <td class="item_name">对晋升职位的&nbsp;&nbsp;&nbsp;<br>信息与期望：</td>
                                            <td colspan="7">
                                               <textarea name="expectation" placeholder="对新岗位的期望"></textarea>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </form>
                        </div>

                    </div>
                </div>
            </div>
<%@include file="../public/public_script3.jsp" %>
<script src="${ctx}/static/js/public/public_add.js"></script>
<script src="${ctx}/static/js/staff/add_promotion.js"></script>
</body>
</html>