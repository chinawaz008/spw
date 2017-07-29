<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@include file="../public/public_head3.jsp" %>
    <title>民盛新增代扣对账</title>
    <link rel="stylesheet" href="${ctx}/static/css/public/add3.css" />
    <link rel="stylesheet" href="${ctx}/static/css/public/sub_add.css" />
    <link rel="stylesheet" href="${ctx}/static/css/lifeInsurance/form.css" />
    <style type="text/css">
    .tab_body table td .input_tip{
    	position: absolute;
		right: 0;
		bottom: 15px;
		color: #ba1e17;
    }
    </style>
</head>
<body>
    <div class="btns">
        <button type="button" id="add_submit_btn" data-i="${staff.id}">提交</button>
    </div>

    <div class="tab_bodies">                
        <div class="tab_body">
	  		<div class="salesman">
	  			<label >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;业务人员：</label>
		        <input type="text" id="search_salesman_input" class="search_salesman_input" placeholder="输入姓名查找">
		        <button class="btn_orange" type="button" id="search_salesman_btn">搜索</button>
		        <button class="btn_blue" type="button" id="reset" title="点击重新选择填写">重置</button>
		        <label class="search_salesman_tip" id="search_salesman_tip"></label>
		        <ul id="search_salesman_ul"></ul>
		    </div>
            <form id="organization_form" action="${ctx}/staff/add_debit" method="post">
                <table>
                    <tbody>
                        <tr>
                            <td class="item_name"><span class="red">*</span> 渠道</td>
                            <td>
                                <select id="line_type" class="select_m required" name="lineType">
                                    <option value="">请选择条线部门</option>
                                    <c:forEach items="${lineType}" var="itme" varStatus="st">
                                         <option value="${itme.id}">${itme.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td class="item_name"><span class="red">*</span> 省级机构</td>
                            <td>
                                <select id="branch_company_id" class="select_m required" name="branchCompanyId">
                                    <option value="">请选择所属省级机构</option>
                                </select>
                            </td>
                        </tr>
                        
                        <tr>
                            <td class="item_name"><span class="red">*</span> 督训区</td>
                            <td>
                                <select id="county_franchisees_id" class="select_m required" name="countyFranchiseesId">
                                    <option value="">请选择督训区</option>
                                </select>
                            </td>
                            
                            <td class="item_name"><span class="red">*</span> 职级</td>
                            <td>
                                <select id="position" class="select_m required" name="position">
                                    <option value="">请选择职级</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td class="item_name"><span class="red">*</span> 姓名</td>
                            <td><input type="text" class="required" name="debitName" id="debitName" data-c="姓名"></td>
                            <td class="item_name"><span class="red">*</span> 身份证号</td>
                            <td><input type="text" class="required" name="debitNo" id="debitNo" data-c="身份证号"></td>
                        </tr>
                        <tr>
                            <td class="item_name"><span class="red">*</span> 交款金额</td>
                            <td><input type="text" class="required formated" name="debitMoney" id="debitMoney" data-c="金额"></td>
                            <td class="item_name"><span class="red">*</span> 交款时间</td>
                            <td>
                            <input class="input_l ms_datepicker required" id="debit_time" data-c="交款时间" name="debitTime" type="text" onfocus="WdatePicker({isShowWeek:true,readOnly:true,dateFmt: 'yyyy-MM-dd HH:mm:ss'})">
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

<%@include file="../public/public_script3.jsp" %>
<script src="${ctx}/static/js/public/public_add.js"></script>
<script src="${ctx}/static/js/staff/add_debit.js"></script>
<script src="${ctx}/static/js/plugins/msdatepicker/msdatepicker.js"></script>
<script type="text/javascript" src="${ctx }/static/js/My97DatePicker/WdatePicker.js"></script>

</body>
</html>