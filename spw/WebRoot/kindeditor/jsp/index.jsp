<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.spw.elife.common.TestProperties" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
    <img alt="" src="<%=basePath%>/image/20150908/20150908175244_345.jpg">
    <%
    TestProperties tx = new TestProperties();
    String kaddress=tx.kindeditorAddress();
    String kshow=tx.kindeditorShow();
    System.out.println(kaddress+"=========================");
    
    %>
  </body>
</html>
