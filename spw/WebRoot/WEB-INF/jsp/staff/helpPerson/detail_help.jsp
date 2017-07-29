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
                <td>姓名</td>
                <td>${entity.name }</td>
            </tr>
            <tr>
                <td>帮扶条线</td>
                <td>${entity.lineType }</td>
            </tr>
            <tr>
                <td>帮扶省级机构</td>
                <td>${entity.branchCompanyName }</td>
            </tr>
            <tr>
                <td>帮扶督训区</td>
                <td>${entity.dxqName }</td>
            </tr>
            <tr>
                <td>开始日期</td>
                <td>${entity.helpBeginTime }</td>
            </tr>
            <tr>
                <td>结束日期</td>
                <td>${entity.helpEndTime }</td>
            </tr>
            <tr>
                <td>帮扶前业绩</td>
                <td>${entity.beforeMoney }</td>
            </tr>
            <tr>
                <td>帮扶后业绩</td>
                <td>${entity.nowMoney }</td>
            </tr>
            <tr>
                <td>帮扶前人力</td>
                <td>${entity.beforePerson }</td>
            </tr>
            <tr>
                <td>帮扶后人力</td>
                <td>${entity.nowPerson }</td>
            </tr>
            <tr>
                <td>创建日期</td>
                <td>${entity.createTime }</td>
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