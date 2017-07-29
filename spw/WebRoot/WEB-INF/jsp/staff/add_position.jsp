<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("ctx", request.getContextPath());%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${ctx}/static/css/public/add3.css" />
</head>
<body data-message="${message}">
            <div class="body">
            <form id="add_duxun_form" method="post" >
                <div class="theme_lay">
                    <div class="theme">
                        <label class="main_theme">后援人员调岗——新增</label>
                        <label class="sub_theme">（在搜索框中输入工号或姓名，确定调岗人员后，填写相关信息）</label>
                    </div>
                    <div class="btns">
                   		<button type="button" id="change_position_btn" class="btn_green"><i class="iconfont icon-zhengque"></i> 提交</button>
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
                                        </tr>
                                    </tbody>

                                </table>

                                <table>
                                    <tbody>
                                        
                                        <tr>
                                            <td class="item_name" style="width:150px">公司：</td>
                                            <td>
                                                <select class="select_m" id="company_type" name="companyType">
                                                	<option value="1" selected="selected">总公司</option>
                                                	<option value="2">分公司</option>
    						                    </select>
                                                <select class="select_m hide" data-c="条线" id="line_type" name="lineID"></select>
                                                <select class="select_m hide" data-c="分公司名称" id="sub_company" name="subComID"></select>
                                            </td>
                                        </tr>
                                        <tr class="hide" id="position_ready">
                                            <td class="item_name">已选择的岗位：</td>
                                            <td id="position_ready_td"></td>
                                        </tr>
                                        
                                        <tr class="select_position_tr">
                                            <td class="item_name">部门：</td>
                                            <td id="section_tr"></td>
                                        </tr>
                                        <tr class="select_position_tr">
                                            <td class="item_name">岗位：</td>
                                            <td>
                                                <select id="position_select" class="select_m" class="required">
                                                    <option value="">请选择...</option>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                        <td class="item_name">
                                                <button type="button" class="btn_orange hide" id="add_gangwei">岗位 + </button>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                        </div>

                    </div>
                </div>
           </form>
            </div>
<%@include file="../public/public_script3.jsp" %>
<script src="${ctx}/static/js/public/public_add.js"></script>
<script src="${ctx}/static/js/staff/add_position.js"></script>
<script src="${ctx}/static/js/plugins/msdatepicker/msdatepicker.js"></script>

</body>
</html>