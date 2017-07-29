<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@include file="public/public_head.jsp" %>
    <link rel="stylesheet" href="${ctx}/static/css/public/index.css" />
    <link rel="stylesheet" href="${ctx}/static/css/public/index2.css" />
    <title>民盛首页</title>
</head>
<body>
    <div class="box">
        <%@include file="public/public_side.jsp" %>
        <div class="main">
            <div class="title">民盛后台管理系统</div>
            <div class="crumbs">
                <ul>
                    <li><i class="iconfont icon-zhuye"></i> 首页</li>
                </ul>
            </div>
            <div class="body">
                <div class="dashboard">
                    <div class="dashboard_lay">
                        <!-- 业务数据板块 -->
                        <div class="dashboard_box left data_box">
                            <div class="bg_img"><img src="${ctx}/static/img/index/2.png"></div>
                            <ul class="data_list">
                                <li>
                                    <p class="data">${human }</p>
                                    <p class="item">当前在册人力</p>
                                </li>
                                <li>
                                    <p class="data">${gundong }<c:if test="${gundong!=null }">%</c:if></p>
                                    <p class="item">当月滚动继续率</p>
                                </li>
                                <li>
                                    <p class="data">${achievement }</p>
                                    <p class="item">年度承保</p>
                                </li>
                            </ul>
                        </div>

                        <!-- 日历板块 -->
                        <div class="dashboard_box right calendar_box">
                            <div class="box_title">
                                <i class="iconfont icon-rili"></i>
                                <span>日历</span>
                            </div>
                            
                            <div id="calendar_div"></div>
                        </div>
                    </div>

                    <div class="dashboard_lay">
                        
                    <!-- 审批板块 -->
                        <div class="dashboard_box left apply_box">
                            <div class="box_title">
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
                            <ul class="page_bar" id="apply_pagers"></ul>
                        </div>

                    <!-- 公告板块 -->
                        <div class="dashboard_box right notice_box">
                            <div class="box_title">
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
                            <ul class="page_bar" id="notice_pagers"></ul>
                        </div>
                    </div> 

                    <div class="dashboard_lay">
                        
                        <div class="dashboard_box_full">
                            <div class="box_title">
                                <i class="iconfont icon-renminbi1"></i>
                                <span>本年目标总情况</span>
                            </div>
                            <div class="box_body">
                                <div id="main_chart" class="main_chart"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<%@include file="public/public_script.jsp" %>
<script src="${ctx}/static/js/public/react.min.js"></script>
<script src="${ctx}/static/js/public/react-dom.min.js"></script>
<script src="${ctx}/static/js/public/browser.min.js"></script>
<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
<script type="text/babel" src="${ctx}/static/js/index/react_notice_board.js"></script>
<script type="text/babel" src="${ctx}/static/js/index/react_apply_board.js"></script>
<script type="text/babel" src="${ctx}/static/js/index/react_canlendar.js"></script>
<script src="${ctx}/static/js/index/index.js"></script>
</body>
</html>
