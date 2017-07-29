<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("ctx", request.getContextPath());%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8" />
</head>
<body>
<p>提交失败！（${message}）</p>
<button onclick="parent.closeBox();parent.freshList(); ">关闭窗口</button>
</body>
</html>

