<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("ctx", request.getContextPath());%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8" />
    <title>民盛登录</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
</head>

<body>
    	<form action="${ctx}/alipay/pay" method="get">
    		<input type="text" class="out_trade_no" placeholder="商户订单号" name="out_trade_no"/><br/>
	        <input type="text" class="username" placeholder="订单标题" name="subject"/><br/>
	        <input type="text" class="username" placeholder="订单金额" name="total_amount"/><br/>
	        <button type="submit" id="btn" class="submit" data-ctx="${ctx}">登 录</button>
        </form>
</body>
</html>