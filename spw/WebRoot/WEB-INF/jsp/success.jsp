<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("ctx", request.getContextPath());%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8" />
  <script type="text/javascript">
    if (parent.closeBox) {
      parent.closeBox();
      parent.freshList();  
    }

  </script>

</head>
<body>
<p>提交成功！</p>
</body>
</html>

