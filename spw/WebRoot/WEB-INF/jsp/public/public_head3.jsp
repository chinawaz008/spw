<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("ctx", request.getContextPath());%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<link rel="shortcut icon" href="${ctx}/static/img/public/favicon.ico" type="image/x-icon" />
<%@include file="./public_icon.jsp" %>  
<link rel="stylesheet" href="${ctx}/static/css/public/public3.css" />