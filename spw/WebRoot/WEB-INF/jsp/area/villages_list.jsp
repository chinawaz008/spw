<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<%@include file="../public/public_head_list.jsp" %>
</head>
<body data-message="${message}">
<div class="theme_lay">
    <div class="theme">
        <label class="main_theme">居委会管理</label>
        <label class="sub_theme">（管理所有居委会）</label>
    </div> 
        <div class="btns">
			<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
				<c:if test="${item == 148}">
					<button type="button" class="btn_blue open_box" data-src="${ctx}/area/villages/0" data-type="新增"><i class="iconfont icon-jiahao"></i> 新增</button>
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
	    			居委会名称
	        <input type="text" name="villagesName" id="villagesName" placeholder="请输入居委会名称"/>
	        <input type="text" name="villagesNo" id="villagesNo"placeholder="请输入居委会编码">
	        <button type="button" class="btn_green search_btn" ><i class="iconfont icon-sousuo-sousuo"></i> 搜索</button> 
	    </div>
    </form>
</div>
<div class="table_lay">
    <div class="table_window" data-style="overflow-x: scroll;">
        <table data-style="width:1300px">
            <thead>
                <tr>
			        <th>居委会名称</th>
			        <th>居委会编码</th>
			        <!-- <th>街道编码</th>
			        <th>区级编码</th>
			        <th>市级编码</th>
			        <th>省级编码</th> -->
			        <th>省</th>
			        <th>市</th>
			        <th>区</th>
			        <th>街道</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody id="flexible_tbody">
                <tr class="templet hide">
                    <td
						<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
							<c:if test="${item == 150}">
								data-src="${ctx}/villages/detail/@{villageId}@" data-type="详情" class="open_box"
							</c:if> 
						</c:forEach>
                     >@{villageName}@</td>
                    <td>@{villageNo}@</td>
                    <td>@{provinceName}@</td>
                    <td>@{regionName}@</td>
                    <td>@{countyName}@</td>
                    <td>@{townName}@</td>
                    <td>
						<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
							<c:if test="${item == 149}">
                        		<button data-src="${ctx}/area/villages/@{villageId}@"  data-type="编辑" class="btn_green open_box " type="button" >编辑</button>
							</c:if> 
						</c:forEach>
						<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
							<c:if test="${item == 151}">
		                        <a onclick="javascript:return confirm('确认删除吗？');" href="${ctx}/area/@{villageId}@/villremove" ><button type="button" class="btn_red" data-date="" >删除</button></a>
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
<script src="${ctx}/static/js/area/villages.js"></script>
</body>
</html>