<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@include file="../public/public_head_list.jsp" %>
<script type="text/javascript" src="${ctx }/static/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<div class="theme_lay">
  <div class="theme">
      <label class="main_theme">佣金结算日期管理</label>
      <label class="sub_theme">（管理所有佣金结算日期）</label>
  </div>
      <div class="btns">
      	<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
			<c:if test="${item == 167}">
				<button type="button" class="btn_blue open_box" data-src="${ctx}/staff/add_salary_time" data-type="新增"><i class="iconfont icon-jiahao"></i> 新增</button>
			</c:if> 
		</c:forEach>
    </div>
</div>
<div class="search_lay">
	<div class="controller_div">
	        <i class="iconfont icon-sousuo-sousuo"> |</i> 查询筛选
	        <i class="iconfont right_icon icon-shangla" id="right_icon"></i>               
	 </div>
    <form method="post" id="search_form" name="myform">
	   <div class="search_div">
	        <input type="text" name="currentTime" id="currentTime"  onfocus="WdatePicker({isShowWeek:true,readOnly:true,dateFmt:'yyyy-MM'})" placeholder="当月日期">
	        <button type="button" class="btn_green search_btn" ><i class="iconfont icon-sousuo-sousuo"></i> 搜索</button> 
	    </div>
    </form>
</div>
<div class="table_lay">
    <div class="table_window" data-style="overflow-x: scroll;">
        <table data-style="width:1300px">
            <thead>
                <tr>
		           <th>当前年月</th>
		           <th>预收开始日期</th>
		           <th>预收结束日期</th>
		           <th>承保开始日期</th>
		           <th>承保结束日期</th>
		           <th>回执开始日期</th>
		           <th>回执结束日期</th>
		           <th>回访开始日期</th>
		           <th>回访结束日期</th>
		           <th>操作</th>
                </tr>
            </thead>
            <tbody id="flexible_tbody">
                <tr class="templet hide">
                    <td>@{nowDate}@</td>
                    <td>@{ysTimeBegin}@</td>
                    <td>@{ysTimeEnd}@</td>
                    <td>@{acceptTimeBegin}@</td>
                    <td>@{acceptTimeEnd}@</td>
                    <td>@{hzTimeBegin}@</td>
                    <td>@{hzTimeEnd}@</td>
                    <td>@{hfTimeBegin}@</td>
                    <td>@{hfTimeEnd}@</td>
                    <td>
                    	<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
							<c:if test="${item == 168}">
								<button data-src="${ctx}/staff/update_salary_time?id=@{id}@"  data-type="编辑" class="btn_green open_box " type="button" >编辑</button>
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
<script src="${ctx}/static/js/staff/list_salary_time.js"></script>
</body>
</html>