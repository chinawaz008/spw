<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("ctx", request.getContextPath());%>
<% request.setAttribute("buttonAuthorization", request.getSession().getAttribute("buttonAuthorization"));%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1">
<%@include file="./public_icon.jsp" %>  
<link rel="stylesheet" href="${ctx}/static/css/public/subframe.css" />