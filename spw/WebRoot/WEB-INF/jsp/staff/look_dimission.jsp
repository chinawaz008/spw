<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@include file="../public/public_head.jsp" %>
    <title>民盛个代业务人员离职</title>
    <link rel="stylesheet" href="${ctx}/static/css/public/add.css" />
    <link rel="stylesheet" href="${ctx}/static/css/staff/look_dimission.css" />
</head>
<body>
    <div class="box">
        <%@include file="../public/public_side.jsp" %>
        <div class="main">
            <%@include file="../public/public_title.jsp" %>
            <div class="crumbs">
                <ul>
                    <li><a href="${ctx}/"><i class="iconfont icon-zhuye"></i> 首页</a></li>
                    <li>人员管理</li>
                    <li>个代人员离职</li>
                </ul>
            </div>
            <div class="body">
                <div class="theme_lay">
                    <div class="theme">
                        <label class="main_theme">个代业务人员离职——进度</label>
                        
                    </div>
                    
                </div>
                <div class="tab_lay">
                    <ul class="tab_controller">
                        <li class="active"><i class="iconfont icon-gongzuojingliline"></i> 离职办理进度</li>
                    </ul>
                    <div class="tab_bodies">
                        
                        <div class="tab_body">
                            <div class="steps passed">
                                <div class="section" title="${entity[0].remark}">人事部</div>
                                <p>人管整理上个月月初至月底递交的离司材料，列出本月需要离司人员信息，和是否涉及增员方案，涉及的部分需要扣除增员方案奖励</p>
                            </div>
                            <img class="arrow" src="${ctx}/static/img/staff/2.jpg">
                            <div class="steps 
                            	 <c:if test="${entity[1].isAgree == 1}"> passed</c:if>
                           		 <c:if test="${entity[1].isAgree == 0}"> stop</c:if>
                           		 <c:if test="${entity[1].isAgree ==null}"> current</c:if>
                           		 ">
                                <div class="section" title="${entity[1].remark}">财务部</div>
                                <p>财务查询业务员款项是否已经入账[款项已入账（备注哪个账户），款项未入账</p>
                            </div>
                            <img class="arrow" src="${ctx}/static/img/staff/2.jpg">
                            <div class="steps 
                            	 <c:if test="${entity[2].isAgree == 1}"> passed</c:if>
                           		 <c:if test="${entity[2].isAgree == 0}"> stop</c:if>
                           		 <c:if test="${entity[2].isAgree ==null && entity[1].isAgree==1}"> current</c:if>
                           		 ">
                                <div class="section" title="${entity[2].remark}">运营新单</div>
                                <p>运营新单查询是否有业绩，(有业绩，无业绩)，哪一年的业绩</p>
                            </div>
                            <img class="arrow" src="${ctx}/static/img/staff/2.jpg">
                            <div class="steps 
                            	 <c:if test="${entity[3].isAgree == 1}"> passed</c:if>
                           		 <c:if test="${entity[3].isAgree == 0}"> stop</c:if>
                           		 <c:if test="${entity[3].isAgree ==null && entity[2].isAgree==1}"> current</c:if>
                           		 ">
                                <div class="section" title="${entity[3].remark}">续期</div>
                                <p>运营续期给出续期意见，业务员是否可以正常离司</p>
                            </div>
                            <img class="arrow" src="${ctx}/static/img/staff/2.jpg">
                            <div class="steps 
                            	 <c:if test="${entity[4].isAgree == 1}"> passed</c:if>
                           		 <c:if test="${entity[4].isAgree == 0}"> stop</c:if>
                           		 <c:if test="${entity[4].isAgree ==null && entity[3].isAgree==1}"> current</c:if>
                           		 ">
                                <div class="section" title="${entity[4].remark}">人事部</div>
                                <p>管综合各部门意见，给出人管意见是否可以正常办理离司（入司押金退款多少？）</p>
                            </div>
                            <img class="arrow" src="${ctx}/static/img/staff/2.jpg">
                            <div class="steps 
                            	 <c:if test="${entity[5].isAgree == 1}"> passed</c:if>
                           		 <c:if test="${entity[5].isAgree == 0}"> stop</c:if>
                           		 <c:if test="${entity[5].isAgree ==null && entity[4].isAgree==1}"> current</c:if>
                           		 ">
                                <div class="section" title="${entity[5].remark}">领导</div>
                                <p>每月5号，离司材料定稿递交领导，条线领导给出确认意见</p>
                            </div>
                            <img class="arrow" src="${ctx}/static/img/staff/2.jpg">
                            <div class="steps 
                                 <c:if test="${status == 2 }"> passed</c:if>
                           		 <c:if test="${status == 3 }"> stop</c:if>  
                           		 <c:if test="${entity[5].isAgree ==1}"> current</c:if>
                           		 ">
                                <div class="section">人事部</div>
                                <p>每月10号办理离司退还押金</p>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
<%@include file="../public/public_script.jsp" %>

</body>
</html>