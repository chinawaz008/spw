<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("ctx", request.getContextPath());%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@include file="../public/public_head_list.jsp" %>  
</head>
<body>
            <div class="body">
                <div class="theme_lay">
                    <div class="theme">
                        <label class="main_theme">后援人员管理</label>
                        <label class="sub_theme">（管理所有后援人员和查询）</label>
                    </div>
                    <div class="btns">
                    	
						<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
							<c:if test="${item == 63}">
		                        <button type="button" class="btn_blue open_box" data-src="${ctx}/staff/add_staff_support" data-type="新增后勤人员"><i class="iconfont icon-jiahao"></i> 新增</button>
							</c:if> 
						</c:forEach>
						<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
							<c:if test="${item == 67}">
		                        <button type="button" onclick="exportOrder();" class="btn_green"><i class="iconfont icon-sousuo"></i> 导出</button> 
							</c:if> 
						</c:forEach>
                    </div>
                </div>
                <div class="search_lay">
	                <div class="controller_div" id="search_div">
				        <i class="iconfont icon-sousuo-sousuo"> |</i> 查询筛选
				        <i class="iconfont right_icon icon-shangla" id="right_icon"></i>               
				    </div>
                    <form id="search_form" name="myform">
                       <div class="search_div">
                        		    人员姓名
                            <input type="text" name="name" placeholder="请输入人员姓名"/>
                                            岗位号  <input type="text" name="workNum" placeholder="请输入岗位号">
                                            身份证号  <input type="text" name="idCard" placeholder="请输入身份证号"><br />
                                            所在省份
             		   <select class="s_province" id="provinceId" name="provinceId">
                             	<option value="">请选择省</option>
                                 <c:forEach items="${plist}" var="itme" varStatus="st">
                             		 <option value="${itme.provinceNo}">${itme.provinceName}</option>
                             	</c:forEach>
         					</select>
    						            &nbsp;&nbsp;&nbsp;&nbsp;所在市
			           <select class="s_region" name="regionId">
                                         <option value="">请选择市</option>
                                     </select>
                                 		 &nbsp;&nbsp;&nbsp;&nbsp;    所在县区
                                     <select class="s_county" name="countyId">
                                                <option value="">请选择区县</option>
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
                                   <!--  <th>地域</th> -->
                                    <th>公司类型</th>
                                    <th>公司名称</th>
                                    <th>职级</th>
                                    <th>姓名</th>
                                    <th>手机号码</th>
                                    <th>岗位号</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody id="flexible_tbody">
                                <tr class="templet hide" name="staffSupportTr">
                                   <!--  <td>@{provinceName}@@{regionName}@@{countyName}@</td> -->
                                    <td>@{branchCompanyId}@</td>
                                    <td>@{branchCompanyName}@</td>
                                    <td>@{position}@</td>
                                    <td>@{name}@</td>
                                    <td>@{phoneNum}@</td>
                                    <td>@{workNum}@</td>
                                    <td>
										<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
											<c:if test="${item == 66}">
		                                        <button type="button" class="btn_orange open_box" data-src="${ctx}/staff/look_support?staffId=@{id}@" data-type="查看后勤人员">查看</button>
											</c:if>
										</c:forEach>
										<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
											<c:if test="${item == 64}">
		                                        <button type="button" class="btn_green open_box" data-src="${ctx}/staff/update_support?staffId=@{id}@" data-type="编辑后勤人员">编辑</button>
											</c:if> 
										</c:forEach>
										<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
											<c:if test="${item == 65}">
		                                        <button type="button" class="btn_red"  onclick="removes(@{id}@)" data-id="@{id}@">离职</button>
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
<script src="${ctx}/static/js/staff/list_staff_support.js"></script>
</body>
</html>