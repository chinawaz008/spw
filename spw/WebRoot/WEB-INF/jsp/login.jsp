<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("ctx", request.getContextPath());%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8" />
    <title>民盛登录</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <link rel="shortcut icon" href="${ctx}/static/img/public/favicon.ico" type="image/x-icon" />
    <link href="${ctx}/static/css/login/login.css" rel="stylesheet" />
</head>

<body>
    <div class="login_box">
        <img src="${ctx}/static/img/login/1.png">
        <input type="text" class="username" id="worknum" placeholder="请输入岗位号" name="username" autofocus="autofocus"/>
        <input type="password" class="password" id="password" placeholder="请输入密码" name="password"/>
        <button type="button" id="btn" class="submit" data-ctx="${ctx}">登 录</button>
    </div>
    <script src="${ctx}/static/js/public/jquery-2.1.4.min.js"></script>
    <script src="${ctx}/static/js/login/login.js"></script>
</body>
</html>