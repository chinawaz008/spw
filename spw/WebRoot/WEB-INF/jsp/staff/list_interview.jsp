<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("ctx", request.getContextPath());%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@include file="../public/public_head_list.jsp" %>  
    <script type="text/javascript" src="${ctx }/static/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
            <div class="body">
                <div class="theme_lay">
                    <div class="theme">
                        <label class="main_theme">面试申请管理</label>
                        <label class="sub_theme">（管理所有面试申请查询、管理）</label>
                    </div>
                    <div class="btns">
                    </div>
                </div>
                <div class="search_lay">
                <div class="controller_div">
			        <i class="iconfont icon-sousuo-sousuo"> |</i> 查询筛选
			        <i class="iconfont right_icon icon-shangla" id="right_icon"></i>               
			    </div>
                    <form id="search_form">
                        <div class="search_div" id="search_div">
                            <input type="text" name="name" placeholder="请输入面试者姓名"/>
                            <br />
							<input class="query_input ms_datepicker" id="beginDate" type="text"
								placeholder="申请日期"
								onfocus="WdatePicker({isShowWeek:true,readOnly:true,maxDate:'#F{$dp.$D(\'endDate\')}'})"
								name="beginDate"></input>
								 ~ <input type="text"
								class="query_input ms_datepicker" id="endDate"
								placeholder="申请日期"
								onfocus="WdatePicker({isShowWeek:true,readOnly:true,minDate:'#F{$dp.$D(\'beginDate\')}'})"
								name="endDate"></input>
                            <br />
                                 <select class="s_county" name="status">
                                   <option value="">请选择状态</option>
                                   <option value="0">未面试</option>
                                   <option value="1">不通过</option>
                                   <option value="2">通过</option>
                                 </select>
                            <br />
							<select id="line_type" name="lineType">
							<option value="">请选择渠道</option>
							<c:forEach items="${lineType}" var="itme" varStatus="st">
							<option value="${itme.id}">${itme.name}</option>
							</c:forEach>
							</select>
							<select id="branch_company_id" class="hide" name="branchCompanyId">
							  <option value="">请选择分公司</option>
							</select>
                            <button type="button" class="btn_green search_btn" ><i class="iconfont icon-sousuo-sousuo"></i> 搜索</button>
                        </div>
                    </form>

                </div>
                <div class="table_lay">
                    <div class="table_window" data-style="overflow-x: scroll;">
                        <table data-style="width:1300px">
                            <thead>
                                <tr>
                                    <th>部门</th>
                                    <th>省份</th>
                                    <th>姓名</th>
                                    <th>岗位</th>
                                    <th>申请日期</th>
                                    <th>联系方式</th>
                                    <th>面试状态</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody id="flexible_tbody">
                                <tr class="templet hide" name="staffSupportTr">
                                    <td data-src="${ctx}/interview/detail/@{id}@" data-type="详情" class="open_box">@{departmentName}@</td>
                                    <td>@{proviceName}@</td>
                                    <td>@{applyName}@</td>
                                    <td>@{positionName}@</td>
                                    <td>@{applyTime}@</td>
                                    <td>@{phone}@</td>
                                    <td>
                                   		^{'@{status}@' =='0' ^^ 未面试 }^ 
										^{'@{status}@'  =='1' ^^ 不通过 }^
										^{'@{status}@'  =='2' ^^ 通过 }^
                                    </td>
                                    <td>
				                    	<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
											<c:if test="${item == 209}">
											^{'@{status}@' =='0' ^^ 
												<button type="button" class="btn_green" onclick="deal(@{id}@,2)" >通过</button>
												<button type="button" class="btn_green" onclick="deal(@{id}@,1)">不通过</button>
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
            </div>
<%@include file="../public/public_script3.jsp" %>
<script src="${ctx}/static/js/public/public_list3.js"></script>
<script src="${ctx}/static/js/staff/list_interview.js"></script>
</body>
</html>