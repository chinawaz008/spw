<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("ctx", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@include file="../public/public_head_list.jsp" %>  
    <script type="text/javascript" src="${ctx }/static/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
  <div class="theme_lay">
      <div class="theme">
          <label class="main_theme">前线工资管理列表</label>
          <label class="sub_theme">（管理前线人员工资）</label>
      </div>
  </div>
  <div class="search_lay">
  <div class="controller_div">
        <i class="iconfont icon-sousuo-sousuo"> |</i> 查询筛选
        <i class="iconfont right_icon icon-shangla" id="right_icon"></i>               
    </div>
      <form id="search_form">
          <div class="search_div" id="search_div">
             <input type="text" name="name" placeholder="请输入姓名/岗位号/身份证号/手机号" style="width: 285px;"> <br />
             <input type="text" class="input_m ms_datepicker" id="date" name="date" value="" onfocus="WdatePicker({dateFmt:'yyyy-MM'})" placeholder="请输入年月" />
             <input type="text" name="orgName" placeholder="请输入分部名称">
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
                      <th>条线</th>
                      <th>分公司</th>
                      <th>督训区</th>
                      <th>分部</th>
                      <th>岗位</th>
                      <th>姓名</th>
                      <th>年月</th>
                      <th>当月工资</th>
                      <th>操作</th>
                  </tr>
              </thead>
              <tbody id="flexible_tbody">
                  <tr class="templet hide">
                      <td>@{lineType}@</td>
                      <td>@{branchCompanyName}@</td>
                      <td>@{countyFranchiseesName}@</td>
                      <td>@{storeName}@</td>
                      <td>@{position}@</td>
                      <td>@{name}@</td>
                      <td>@{salaryDate}@</td>
                      <td>@{total}@</td>
                      <td>
						<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
							<c:if test="${item == 78}">
		                      <a href="detail_salary?worknum=@{worknum}@&date=@{salaryDate}@">
		                          <button type="button" class="btn_green" >详情</button>
		           			  </a>
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
<%@include file="../public/public_script3.jsp" %>
<script src="${ctx}/static/js/public/public_list3.js"></script>
<script src="${ctx}/static/js/staff/list_salary.js"></script>
</body>
</html>