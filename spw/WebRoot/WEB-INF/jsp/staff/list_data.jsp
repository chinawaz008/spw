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
                        <label class="main_theme">数据权限管理</label>
                        <label class="sub_theme">（管理所有人员数据权限和查询）</label>
                    </div>
                    <div class="btns">
                    	<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
							<c:if test="${item == 198}">
		                        <button type="button" class="btn_blue open_box" data-src="${ctx}/authority/add_data" data-type="新增数据权限管理"><i class="iconfont icon-jiahao"></i> 新增</button>
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
                        		 <!-- 组织名称  <input type="text" name="orgName" placeholder="请输入组织名称"> -->
    						           岗位号  <input type="text" name="workNum" placeholder="请输入岗位号">
    						           身份证号  <input type="text" name="code" placeholder="请输入身份证号">
                            <br />
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
                                    <th>姓名</th>
                                    <th>岗位</th>
                                    <th>岗位号</th>
                                    <!-- <th>权限类型</th> -->
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody id="flexible_tbody">
                                <tr class="templet hide" name="staffSupportTr">
                                    <td>@{staffName}@</td>
                                    <td>@{positionName}@</td>
                                    <td>@{worknum}@</td>
                                    <!-- <td>@{workNum}@</td> -->
                                    <td>
				                    	<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
											<c:if test="${item == 199}">
												<button type="button" class="btn_green open_box" data-src="${ctx}/authority/update?staffId=@{staffId}@" data-type="编辑人员权限">编辑</button>
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
<script src="${ctx}/static/js/staff/list_data.js"></script>
<script src="${ctx}/static/js/plugins/msimportexcel/msimportexcel.js"></script>
</body>
</html>