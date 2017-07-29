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
                        <label class="main_theme">人员晋升管理</label>
                        <label class="sub_theme">（管理所有人员晋升）</label>
                    </div>
                    <div class="btns">
						<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
							<c:if test="${item == 69}">
								<button type="button" class="btn_blue open_box" data-src="${ctx}/staff/add_promotion" data-type="新增"><i class="iconfont icon-jiahao"></i> 新增</button>
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
                          			  组织名称
                             <input type="text" name="orgName" placeholder="请输入组织名称">
                             		 岗位号
                             <input type="text" name="workNum" placeholder="请输入岗位号">
                  				           身份证号
                  			 <input type="text" name="idCard" placeholder="请输入身份证号">
                            <button type="button" class="btn_green search_btn" ><i class="iconfont icon-sousuo-sousuo"></i> 搜索</button>
                        </div>
                    </form>

                </div>
                <div class="table_lay">
                    <div class="table_window" data-style="overflow-x: scroll;">
                        <table data-style="width:1300px">
                            <thead>
                                <tr>
                                    <th>省级机构</th>
                                    <th>督训区</th>
                                    <th>分部</th>
                                    <th>姓名</th>
                                    <th>当前职级</th>
                                    <th>予晋升职级</th>
                                    <th>手机号码</th>
                                    <th>状态</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody id="flexible_tbody">
                                <tr class="templet hide">
                                    <td>@{branchCompanyName}@</td>
                                    <td>@{countyFranchiseesName}@</td>
                                    <td>@{storeName}@</td>
                                    <td>@{name}@</td>
                                    <td>@{position}@</td>
                                    <td>@{toJob}@</td>
                                    <td>@{phoneNum}@</td>
                                    <td>@{status}@</td>
                                    <td>
                                    
										<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
											<c:if test="${item == 71}">
			                                    <a href="select_achieve/@{id}@">
			                                        <button type="button" class="btn_blue" >详情</button>
						                        </a>
											</c:if> 
										</c:forEach>
										<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
											<c:if test="${item == 70}">
						                        ^{ '@{status}@'=='申请中'^^
				                                       <button type="button" class="btn_green open_box" data-src="${ctx}/staff/update_promotion/@{id}@" data-type="编辑业务人员" >编辑</button>
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
<script src="${ctx}/static/js/staff/list_promotion.js"></script>
</body>
</html>