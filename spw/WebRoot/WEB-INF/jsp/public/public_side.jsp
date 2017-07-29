<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="side" id="side" data-ctx="${ctx}">
    <div class="member">
        <div class="portrait">
            <img src="${ctx}/static/img/public/1.png">
        </div>
        <div class="name">欢迎您！<span ><a href="${ctx}/staff/edit_info" style="cursor: pointer;">${principal.userInfo.username}</a></span></div>
        <div class="logout" id="logout"><i class="iconfont icon-tuichu tuichu"></i><span>退出</span></div>
    </div>
    <div class="menu">
        <ul class="nav_list" id="nav_list">
            <li>
            <a href = "${ctx}/">
                <div class="nav_item">
                    <i class="iconfont icon-zhuye"></i>
                    <label>首页</label>
                </div>
            </a>
            </li>
            <c:forEach items="${principal.menus}" var="cur">
             <li>
            	<div class="nav_item">
            		<c:if test="${cur.name=='组织管理'}">
                  	  <i class="iconfont icon-organizationalstructure"></i>
            		</c:if>
            		<c:if test="${cur.name=='人员管理'}">
                  	  <i class="iconfont icon-yonghuguanli"></i>
            		</c:if>
            		<c:if test="${cur.name=='业务管理'}">
                  	  <i class="iconfont icon-baoxian"></i>
            		</c:if>
            		<c:if test="${cur.name=='基础数据'}">
                  	  <i class="iconfont icon-xitongguanli"></i>
            		</c:if>
            		<c:if test="${cur.name=='团队管理'}">
                  	  <i class="iconfont icon-renyuanguanli"></i>
            		</c:if>
            		<c:if test="${cur.name=='报表管理'}">
                  	  <i class="iconfont icon-yunyingguanli"></i>
            		</c:if>
            		<c:if test="${cur.name=='理赔管理'}">
                  	  <i class="iconfont icon-lipeizhinan"></i>
            		</c:if>
            		<c:if test="${cur.name=='考勤管理'}">
                  	  <i class="iconfont icon-qinwu"></i>
            		</c:if>
            		<c:if test="${cur.name=='保全管理'}">
                  	  <i class="iconfont icon-baozhang"></i>
            		</c:if>
            		<c:if test="${cur.name=='保险课堂'}">
                  	  <i class="iconfont icon-share-lectures"></i>
            		</c:if>
                    <c:if test="${cur.name=='工作报告'}">
                      <i class="iconfont icon-gongzuobaogao"></i>
                    </c:if>
                    <c:if test="${cur.name=='签批管理'}">
                      <i class="iconfont icon-gongwen"></i>
                    </c:if>
                    <c:if test="${cur.name=='会议管理'}">
                      <i class="iconfont icon-huiyi"></i>
                    </c:if>
                    <c:if test="${cur.name=='客户管理'}">
                      <i class="iconfont icon-kehuguanli"></i>
                    </c:if>
                    <c:if test="${cur.name=='财务管理'}">
                      <i class="iconfont icon-caiwu"></i>
                    </c:if>
                    <c:if test="${cur.name=='投诉建议'}">
                      <i class="iconfont icon-tousu"></i>
                    </c:if>
                    <c:if test="${cur.name=='审批管理'}">
                      <i class="iconfont icon-shenpi1"></i>
                    </c:if>
                    <c:if test="${cur.name=='通知公告管理'}">
                      <i class="iconfont icon-tongzhigonggao"></i>
                    </c:if>
                    <c:if test="${cur.name=='龙虎榜'}">
                      <i class="iconfont icon-longhubang"></i>
                    </c:if>
                    <c:if test="${cur.name=='迅车贷'}">
                      <i class="iconfont icon-xunchedai"></i>
                    </c:if>
                    <c:if test="${cur.name=='轨迹管理'}">
                      <i class="iconfont icon-guiji"></i>
                    </c:if>
                    <c:if test="${cur.name=='商品管理'}">
                      <i class="iconfont icon-shop"></i>
                    </c:if>

                    <label>${cur.name}</label>
                    <c:if test="${cur.branch}">
                    	<i class="iconfont jiantou icon-jiantouxia"></i>
                    </c:if>
                </div>
            	<c:if test="${cur.branch}">
            		<ul class="submenu">
            		 <c:forEach items="${cur.menus}" var="submenu">
	                    <a href="${ctx}${submenu.url}"><li>${submenu.name}</li></a>
	                 </c:forEach>
                	</ul>
            	</c:if>
            </li>
            </c:forEach>
        </ul>
    </div>
</div>