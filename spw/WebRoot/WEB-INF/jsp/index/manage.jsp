<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@include file="../public/public_head3.jsp" %>
    <link rel="stylesheet" href="${ctx}/static/css/public/work3.css" />
    <title>民盛工作</title>
</head>
<body>
<%@include file="../public/public_top.jsp" %>
<div class="work_box">
    <div class="side_box">
        <ul>
	      <c:forEach items="${principal.menus}" var="cur">
      		<c:if test="${cur.id==id}">
      			<c:forEach items="${cur.menus}" var="submenu">
		      		<li class="menu_li">
		      			<div class="menu_div">
	                	  	<i class="iconfont icon-${submenu.icon}"></i>
		  	            	<p class="title_name">${submenu.name}</p>
		                </div>
		                <ul class="sub_ul">
		                      <c:forEach items="${submenu.menus}" var="last">
			                    <li data-href="${ctx}${last.url}">${last.name}</li>
			                  </c:forEach>
		                </ul>
		      		</li>
	      		</c:forEach>
      		</c:if>
	      </c:forEach>
        </ul>
    </div>
    <div class="body">
        <iframe id="list_iframe" src=""></iframe>             
    </div>
</div>
<div class="box" id="box">
    <div class="box_title"><span class="title" id="title"></span><span class="close_btn">X</span></div>
    <iframe id="sub_iframe"></iframe>
</div>
<%@include file="../public/public_script3.jsp" %>
<script src="${ctx}/static/js/public/work3.js"></script>
</body>
</html>
