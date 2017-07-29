<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("ctx", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${ctx}/static/css/public/iframe_detail.css" />
</head>
<body>
<ul class="tab_controllers" id="tab_controllers">
    <li class="selected">常规信息</li>
</ul>
<div class="info_cards">
    <table class="detail_table">
        <tbody>
            <tr>
                <td>街道名称</td>
                <td>${entity.townName}</td>
            </tr>
            <tr>
                <td>街道编码</td>
                <td>${entity.townNo }</td>
            </tr>
            <tr>
                <td>省</td>
                <td>${entity.provinceName }</td>
            </tr>
            <tr>
                <td>市</td>
                <td>${entity.regionName }</td>
            </tr>
            <tr>
                <td>区</td>
                <td>${entity.countyName}</td>
            </tr>
            <tr>
                <td>邮编</td>
                <td>${entity.zipCode}</td>
            </tr>
        </tbody>
    </table>
</div>
<script src="${ctx}/static/js/public/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
$(function(){

    //切换tab页
    $('#tab_controllers li').click(function(){
        var index = $(this).index();
        $(this).addClass('selected').siblings().removeClass('selected');
        $('.detail_table:eq(' + index +')').show().siblings().hide();
    })
})
</script>
</body>
</html>