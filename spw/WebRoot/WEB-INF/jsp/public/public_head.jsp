<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("ctx", request.getContextPath());%>
<% request.setAttribute("buttonAuthorization", request.getSession().getAttribute("buttonAuthorization"));%>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1">
<link rel="shortcut icon" href="${ctx}/static/img/public/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="${ctx}/static/css/public/iconfont.css">
<link rel="stylesheet" href="${ctx}/static/css/public/public.css" />