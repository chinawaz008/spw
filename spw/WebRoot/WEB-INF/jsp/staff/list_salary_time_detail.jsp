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
                <td>当前年月</td>
                <td>${entity.nowDate}</td>
            </tr>
            <tr>
                <td>预收开始日期</td>
                <td>${entity.ysTimeBegin }</td>
            </tr>
            <tr>
                <td>预收结束日期</td>
                <td>${entity.ysTimeEnd }</td>
            </tr>
            <tr>
                <td>承保开始日期</td>
                <td>${entity.acceptTimeBegin }</td>
            </tr>
            <tr>
                <td>承保结束日期</td>
                <td>${entity.acceptTimeEnd}</td>
            </tr>
            <tr>
                <td>回执开始日期</td>
                <td>${entity.hzTimeBegin}</td>
            </tr>
            <tr>
                <td>回执结束日期</td>
                <td>${entity.hzTimeEnd}</td>
            </tr>
            <tr>
                <td>回访开始日期</td>
                <td>${entity.hfTimeBegin}</td>
            </tr>
            <tr>
                <td>回访结束日期</td>
                <td>${entity.hfTimeEnd}</td>
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