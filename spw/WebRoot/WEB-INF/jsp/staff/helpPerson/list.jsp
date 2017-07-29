<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("ctx", request.getContextPath());%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
        <%@include file="../../public/public_head_list.jsp" %>
</head>
<body>
   <div class="theme_lay">
       <div class="theme">
           <label class="main_theme">外省支持人员管理</label>
           <label class="sub_theme"></label>
       </div>
       <div class="btns">
       
			<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
				<c:if test="${item == 79}">
				   <button type="button" class="btn_blue open_box" data-src="${ctx}/staff/add_help/0" data-type="新增帮扶人员"><i class="iconfont icon-jiahao"></i> 新增</button>
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
            <input type="text" name="name" id="name"  placeholder="请输入姓名"  />
            <%@include file="../../public/public_select_font.jsp" %>
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
                            	<th>帮扶条线</th>
                            	<th>帮扶省级机构</th>
                            	<th>帮扶督训区</th>
                                <th>开始日期</th>
                                <th>结束日期</th>
                                <th>帮扶前业绩</th>
                                <th>帮扶后业绩</th>
                                <th>帮扶前人力</th>
                                <th>帮扶后人力</th>
                                <th>创建日期</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody id="flexible_tbody">
                        	<tr class="templet hide">
                        		<td data-src="${ctx}/staff/detail_help/@{id}@" data-type="详情" class="open_box">@{name}@</td>
                        		<td>@{lineType}@</td>
                        		<td>@{branchCompanyName}@</td>
                        		<td>@{dxqName}@</td>
                                <td>@{helpBeginTime}@</td>
                                <td>@{helpEndTime}@</td>
                                <td>@{beforeMoney}@</td>
                                <td>@{nowMoney}@</td>
                                <td>@{beforePerson}@</td>
                                <td>@{nowPerson}@</td>
                                <td>@{createTime}@</td>
                                <td>
									<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
										<c:if test="${item == 80}">
						                    <button data-src="${ctx}/staff/add_help/@{id}@" data-type="编辑" type="button"  class="btn_green open_box" >编辑</button>
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
<%@include file="../../public/public_script3.jsp" %>
<script src="${ctx}/static/js/public/public_list3.js"></script>
<script src="${ctx}/static/js/staff/helpPerson/list.js"></script>
</body>
</html>