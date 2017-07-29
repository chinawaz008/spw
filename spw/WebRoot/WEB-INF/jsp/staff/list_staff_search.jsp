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
                        <label class="main_theme">业务人员查询</label>
                        <label class="sub_theme">（管理所有业务人员和查询）</label>
                    </div>
                    <div class="btns">
						<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
							<c:if test="${item == 68}">
		                        <button type="button" onclick="exportOrder();" class="btn_green"><i class="iconfont icon-sousuo"></i> 导出</button> 
							</c:if> 
						</c:forEach>
                    </div>  
                </div>
                <div class="search_lay">
                <div class="controller_div">
			        <i class="iconfont icon-sousuo-sousuo"> |</i> 查询筛选
			        <i class="iconfont right_icon icon-shangla" id="right_icon"></i>               
			    </div>
                    <form id="search_form">
                        <div class="search_div" id="search_div">
                        		    人员姓名
                            <input type="text" name="name" placeholder="请输入人员姓名"/>
                          	 岗位号
                          	 <input type="text" name="workNum" placeholder="请输入岗位号">
                          	 身份证号
                          	 <input type="text" name="idCard" placeholder="请输入身份证号"> <br />
                            		所在省份
                            <select class="s_province" id="provinceId" name="provinceId">
                                           	<option value="">请选择省</option>
                                               <c:forEach items="${plist}" var="itme" varStatus="st">
                                           		 <option value="${itme.provinceNo}">${itme.provinceName}</option>
                                           	</c:forEach>
    						             </select>
    						              &nbsp;&nbsp;&nbsp;&nbsp;	所在市
    						              <select class="s_region" name="regionId">
                                              <option value="">请选择市</option>
                                          </select>
                                           &nbsp;&nbsp;&nbsp;&nbsp;	所在县区
                                          	<select class="s_county" name="countyId">
                                              <option value="">请选择区县</option>
                                          </select>
                                           &nbsp;&nbsp;&nbsp;&nbsp;      <input  type="text" class="input_m ms_datepicker" id="entryDate" name="entryDate" value="" onfocus="WdatePicker({isShowWeek:true,maxDate:'#F{$dp.$D(\'quitDate\')}'})" placeholder="入职日期开始" />
                              	--
                                 <input  type="text" class="input_m ms_datepicker" id="quitDate" name="quitDate" value="" onfocus="WdatePicker({isShowWeek:true,minDate:'#F{$dp.$D(\'entryDate\')}'})" placeholder="入职日期结束" />
    						          <%@include file="../public/public_select_font.jsp" %>
                            <button type="button" class="btn_green search_btn" ><i class="iconfont icon-sousuo-sousuo"></i> 搜索</button>
                        </div>
                    </form>

                </div>
                <div class="table_lay">
                    <div class="table_window" data-style="overflow-x: scroll;">
                        <table data-style="width:1300px">
                            <thead>
                                <tr>
                                    <th>区域</th>
                                    <th>督训区</th>
                                    <th>分部</th>
                                    <th>姓名</th>
                                    <th>职级</th>
                                    <th>性别</th>
                                    <th>学历</th>
                                    <th>岗位号</th>
                                </tr>
                            </thead>
                            <tbody id="flexible_tbody">
                                <tr class="templet hide" name="staffSupportTr">
                                    <td>@{regionName}@</td>
                                    <td>@{countyFranchiseesName}@</td>
                                    <td>@{storeName}@</td>
                                    <td>@{name}@</td>
                                    <td>@{position}@</td>
                                    <td>^{@{sex}@ == 0 ^^ 男^^}^
                                        ^{@{sex}@ == 1 ^^ 女^^}^
                                    </td>
                                    <td>
                                    ^{@{education}@ == 0 ^^高中^^}^
                                    ^{@{education}@ == 1 ^^专科^^}^
                                    ^{@{education}@ == 2 ^^本科^^}^
                                    ^{@{education}@ == 3 ^^硕士^^}^
                                    ^{@{education}@ == 4 ^^博士^^}^
                                    ^{@{education}@ == 5 ^^其他^^}^
                                    </td>
                                    <td>
                                    	@{workNum}@
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
<script src="${ctx}/static/js/staff/list_staff_search.js"></script>
<script src="${ctx}/static/js/plugins/msimportexcel/msimportexcel.js"></script>
</body>
</html>