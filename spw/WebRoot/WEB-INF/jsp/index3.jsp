<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@include file="public/public_head3.jsp" %>
    <link rel="stylesheet" href="${ctx}/static/css/public/index3.css" />
    <title>民盛首页</title>
</head>
<body>
    <%@include file="public/public_top.jsp" %>
    <div class="index_box">
        <div class="desk_calendar">
            <p class="date" id="date"></p>
            <p class="week" id="week"></p>
        </div>

        <div class="hr_box">
            <p class="hr_num">6000<span>人</span></p>
            <p>当前在册人力</p>
        </div>

        <div class="rate_box">
            <p class="rate_num">80<span>%</span></p>
            <p>当前滚动继续率</p>
        </div>

        <div class="sales_box">
            <p class="sales_num">8000<span>万</span></p>
            <p>当前总收益</p>
        </div>

        <!-- 审批板块 -->
        <div class="apply_box">
            <!-- <div class="box_title">
                <i class="iconfont icon-shenpi"></i>
                <span>审批</span>
            </div>
            <div class="new_apply">
                <select id="apply_type">
                    <option value="/document/recieve/getSignList">待审批</option>
                    <option value="/document/recieve/getInfoList">我提交的</option>
                </select>
                <a title="新建审批" href="${ctx}/document/send/add_document"><i class="iconfont icon-tianjia"></i></a>
            </div>
            
            <div class="apply_board">
                <table class="apply_table" id="apply_table"></table>
            </div>
            <ul class="page_bar" id="apply_pagers"></ul> -->
        </div>

        <!-- 日历板块 -->
        <div class="calendar_box">
            <!-- <div class="box_title">
                <i class="iconfont icon-rili"></i>
                <span>日历</span>
            </div>
            
            <div id="calen dar_div"></div>-->
        </div>

        <!-- 公告板块 -->
        <div class="notice_box">
            <!-- <div class="box_title">
                <i class="iconfont icon-gonggao"></i>
                <span>公告</span>
            </div>
            <div class="notice_publisher">
                <select name="orgType" id="orgType">
                  <option value="">全部</option>
                  <c:forEach items="${departList }" var="item">
                    <option value="${item.id }" <c:if test='${item.id == orgType }'>selected = "selected"</c:if>>${item.name }</option>
                  </c:forEach>
                 </select>
            </div>
            <div class="notice_board">
                <table class="notice_table" id="notice_table"></table>
            </div>
            <ul class="page_bar" id="notice_pagers"></ul> -->
        </div>

        <div class="echarts_box">
            <!-- <div class="box_title">
                <i class="iconfont icon-renminbi1"></i>
                <span>本年目标总情况</span>
            </div>
            <div class="box_body">
                <div id="main_chart" class="main_chart"></div>
            </div> -->
        </div>
    </div>
<%@include file="public/public_script3.jsp" %>
<script>
window.onload = function() {
    var date = new Date();
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    var d = date.getDate();
    var w = date.getDay();
    var ch = ['天', '一', '二', '三', '四', '五', '六'];
    var str1 = y + '年' + m + '月' + d + '日'; 
    var str2 = '星期' + ch[w];
    document.getElementById('date').innerHTML = str1;
    document.getElementById('week').innerHTML = str2;
}
</script>

</body>
</html>
