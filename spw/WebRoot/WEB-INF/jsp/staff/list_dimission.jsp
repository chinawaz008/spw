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
                        <label class="main_theme">个代人员离职管理</label>
                        <label class="sub_theme">（管理所有人员离职）</label>
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
                        		    打印纸质收据 &nbsp;<input type="checkbox"  name="isShow" value="2"/>
                             <button type="button" class="btn_green search_btn" ><i class="iconfont icon-sousuo-sousuo"></i> 搜索</button>
                        </div>
                    </form>
                </div>
                <div class="table_lay">
                    <div class="table_window" data-style="overflow-x: scroll;">
                        <table data-style="width:1300px">
                            <thead>
                                <tr>
                                    <!-- <th>省级机构</th>
                                    <th>督训区</th>
                                    <th>分部</th> -->
                                    <th>岗位号</th>
                                    <th>姓名</th>
                                    <th>当前职级</th>
                                    <th>手机号码</th>
                                    <th>申请日期</th>
                                    <th>状态</th>
                                    <th>打印纸质收据</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody id="flexible_tbody">
                                <tr class="templet hide">
                                   <!--  <td>@{branchCompanyName}@</td>
                                    <td>@{countyFranchiseesName}@</td>
                                    <td>@{storeName}@</td> -->
                                    <td>@{worknum}@</td>
                                    <td>@{name}@</td>
                                    <td>@{position}@</td>
                                    <td>@{phone}@</td>
                                    <td>@{applyTime}@</td>
                                    <td>@{status}@</td>
                                    <td>@{isPrint}@</td>
                                    <td>
			                         ^{ '@{dimissReason}@' == 'go' && '@{department}@' == 5 ^^
										<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
											<c:if test="${item == 74}">
			                                    <a href="update_dimission/@{id}@"><button type="button" class="btn_green" >办理</button></a>  
											</c:if> 
										</c:forEach>
										<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
											<c:if test="${item == 75}">
			                                    <a href="javascript:;"><button type="button" onclick="dimiss(@{id}@)"  class="btn_red" >离职</button></a>
											</c:if> 
										</c:forEach>
                                	 }^
                                	 
									<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
										<c:if test="${item == 74}">
					                         ^{ '@{dimissReason}@' != 'go' && '@{dimissReason}@' != 'admin' ^^
		                                     <a href="update_dimission/@{id}@"><button type="button" class="btn_green" >办理</button></a>  
		                                	 }^
										</c:if> 
									</c:forEach>
                                	 ^{ '@{dimissReason}@' == 'admin' && '@{department}@' != 7  ^^
                                	 
										<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
											<c:if test="${item == 74}">
                                     				<a href="update_dimission/@{id}@"><button type="button" class="btn_green" >办理</button></a>  
											</c:if> 
										</c:forEach>
										<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
											<c:if test="${item == 75}">
						                         <a href="javascript:;"><button type="button" onclick="dimiss(@{id}@)"  class="btn_red" >离职</button></a>
											</c:if> 
										</c:forEach>
                                	 }^
                                	 
									<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
										<c:if test="${item == 75}">
		                                	 ^{ '@{dimissReason}@' == 'admin' && '@{department}@' == 7 ^^
					                         <a href="javascript:;"><button type="button" onclick="dimiss(@{id}@)"  class="btn_red" >离职</button></a>
		                                	 }^
		                                	 ^{ '@{dimissReason}@' == 'go' && '@{department}@' != 5 ^^
			                                    <a href="javascript:;"><button type="button" onclick="dimiss(@{id}@)"  class="btn_red" >离职</button></a>
		                                	 }^
										</c:if> 
									</c:forEach>
									<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
										<c:if test="${item == 76}">
		                                	<a href="look_dimission/@{id}@"><button type="button"  class="btn_blue" >进度 </button></a>
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
<script src="${ctx}/static/js/staff/list_dimission.js"></script>
</body>
</html>