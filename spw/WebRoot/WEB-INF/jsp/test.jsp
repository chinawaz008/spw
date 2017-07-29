<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="<%=path%>/static/js/public/jquery-2.1.4.min.js"></script>
	<script type="text/javascript">
	$(function(){
		$("#submit").click(function(){
		 var url = $("#url").val();
		 var attr1 = $("#attr1").val();
		 var attr2 = $("#attr2").val();
		 var attr3 = $("#attr3").val();
		 var attr4 = $("#attr4").val();
		 var val1 = $("#val1").val();
		 var val2 = $("#val2").val();
		 var val3 = $("#val3").val();
		 var val4 = $("#val4").val();
		 url = url +"?";
		 if(attr1!="")url += attr1 +"="+ val1;
		 if(attr2!="")url +="&"+attr2 +"="+ val2;
		 if(attr3!="")url +="&"+ attr3 +"="+ val3;
		 if(attr4!="")url +="&"+attr4 +"="+ val4;
	     $("#form").attr("action",url);
		 $("#form").submit();
		})
	})
	</script>
  </head>
  <body>
    	<form action="" id="form" method="post">
    		<ul>
    		<li>访问地址:<input type="text" id="url" style="width: 500px;"></li><br />
    			<li>参数:<input type="text" id="attr1"> &nbsp;&nbsp;&nbsp;&nbsp; 值<input type="text" id="val1"></li>
    			<li>参数:<input type="text" id="attr2"> &nbsp;&nbsp;&nbsp;&nbsp; 值<input type="text" id="val2"></li>
    			<li>参数:<input type="text" id="attr3"> &nbsp;&nbsp;&nbsp;&nbsp; 值<input type="text" id="val3"></li>
    			<li>参数:<input type="text" id="attr4"> &nbsp;&nbsp;&nbsp;&nbsp; 值<input type="text" id="val4"></li>
    		</ul>
    		<input type ="submit" value="提交" id="submit" style="width: 80px;height: 40px;"> 
    	</form>
  </body>
</html>
