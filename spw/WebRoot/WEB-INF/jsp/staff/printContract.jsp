<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("ctx", request.getContextPath());%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
    <script type="text/javascript" src="http://sources.ikeepstudying.com/js/jquery-1.8.3.min.js"></script>  
	<script type="text/javascript" src="${ctx }/static/js/staff/jquery.media.js"></script>  
	<script type="text/javascript">  
	    $(function() {  
	        $('a.media').media({width:850, height:530});  
	    });  
	</script>
</head>
<body data-message="${message}">
       <div class="body">
       		<c:if test="${empty url }">
	       		无
       		</c:if>
       			<c:forEach items="${url }" var="item" varStatus="st">
		       		<br />
		       		<div  align="center" style="font-size: 25px">${item.name }</div>
		       		<br />
		            <a class="media" href="${item.money}"></a>
	       		</c:forEach>
        </div>
</body>
</html>