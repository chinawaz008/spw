<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("ctx", request.getContextPath());%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8" />
    <title>民盛500页面</title>
    <link rel="stylesheet" href="${ctx}/static/css/public/404500.css" />
</head>
<body>
    <a href="${ctx}/">
        <img src="${ctx}/static/img/public/500.jpg" alt="500,服务器出错">
    </a>
</body>
</html>