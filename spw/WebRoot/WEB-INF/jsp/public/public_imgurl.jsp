<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("ctx", request.getContextPath());%>
<!DOCTYPE html>
<html>
<head>
    <title></title>
</head>
<body>
<div id="imgurl" data-url="${path}" data-name="${fileName}" data-result="${result}"></div>

</body>
</html>