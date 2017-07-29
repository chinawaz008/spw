<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@include file="../public/public_head.jsp" %>
    <title>民盛外省支持部人员管理</title>
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
                    <li>外省支持部人员</li>
                </ul>
            </div>
            <div class="body">
                <div class="theme_lay">
                    <div class="theme">
                        <label class="main_theme">外省支持部人员管理</label>
                        <label class="sub_theme">（管理所有外省支持部人员）</label>
                    </div>
                </div>
                <div class="search_lay">
                    <form id="search_form">
                        <div class="search_div">
                        		    人员岗位号
                            <input type="text" name="workNum" placeholder="请输入人员岗位号"/><button type="button" class="first_search search_btn">条件搜索</button>
                            <button type="button" class="btn_orange" id="more_condition_btn"><i class="iconfont icon-xiala"></i> <span>更多</span></button>
                        </div>
                        <div class="table_div" id="table_div">
                            <table style="width:75%">
                                <tbody>
                                		<tr>
	                               		 	<td>创建时间</td> 
	                                        <td>
	                                            <input class="input_m ms_datepicker" name="icpBeginTime" type="text"  placeholder="开始时间">-- 
	                                            <input class="input_m ms_datepicker" name="icpEndTime" type="text" placeholder="结束时间"> 
	                                        </td> 
                                        </tr>
                                   <%--  <tr>
                                        <td>省级机构</td>
                                        <td>
                                         <select  id="branchCompanyId" name="branchCompanyId">
                                           	<option value="">请选择省公司</option>
                                            <c:forEach items="${comList}" var="itme" varStatus="st">
                                            <option value="${itme.provinceId}">${itme.compName}</option>
                                           	</c:forEach>
    						           </select>
                                        </td>
                                    </tr> 
                                    <tr>
                                        <td>组织名称</td>
                                        <td><input type="text" name="orgName" placeholder="请输入组织名称"></td>
                                        <td>岗位号</td>
                                        <td><input type="text" name="workNum" placeholder="请输入岗位号"></td>
                                        <td>身份证号</td>
                                        <td><input type="text" name="idCard" placeholder="请输入身份证号"></td>
                                    </tr>--%>
                                </tbody>
                            </table>
                            <button type="button" class="btn_blue search_btn"><i class="iconfont icon-sousuo"></i> 搜索</button>
                        </div>
                    </form>

                </div>
                <div class="table_lay">
                    <div class="table_window" data-style="overflow-x: scroll;">
                        <table data-style="width:1300px">
                            <thead>
                                <tr>
                                    <th>岗位号</th>
                                    <th>当前省</th>
                                    <th>开始时间</th>
                                    <th>结束时间</th>
                                    <th>新增人数</th>
                                    <th>新增受益</th>
                                </tr>
                            </thead>
                            <tbody id="flexible_tbody">
                                <tr class="templet hide">
                                    <td>@{workNum}@</td>
                                    <td>@{provinceName}@</td>
                                    <td>@{beginDate}@</td>
                                    <td>@{endDate}@</td>
                                    <td>@{addPerson}@</td>
                                    <td>@{addMoney}@</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <ul class="pagination" id="pagers"></ul>
                    <i class="clear"></i>
                </div>
            </div>
        </div>
    </div>
<%@include file="../public/public_script.jsp" %>
<script src="${ctx}/static/js/public/public_list.js"></script>
<script src="${ctx}/static/js/staff/list_person_help.js"></script>
<script src="${ctx}/static/js/plugins/msdatepicker/msdatepicker.js"></script>
</body>
</html>