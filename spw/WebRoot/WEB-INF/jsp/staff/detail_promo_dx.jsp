<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@include file="../public/public_head.jsp" %>
    <title>民盛个代人员晋升详情</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/staff/detail_promotion.css">
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
                    <li>个代人员晋升</li>
                </ul>
            </div>
            <div class="body">
                <div class="theme_lay">
                    <div class="theme">
                        <label class="main_theme">分部经理晋升督训达标情况</label>
                        <label class="sub_theme">（管理晋升通过）</label>
                    </div>
                    <c:if test="${entity.status=='0'}">
	                    <div class="btns">
	                        <a href="javascript:;">
	                            <button type="button" id="pass_btn" class="btn_green" data-id="${entity.id}"><i class="iconfont icon-jinsheng"></i> 晋升通过</button>
	                        </a>
	                    </div>
                    </c:if>
                </div>

                <div class="table_lay">
                    <div class="table_window">
                        <div class="sub_title"><i class="iconfont icon-jibenxinxi"> |</i> 人员信息</div>
                        <div class="item_introduction">
                            <ul>
                               <li>
                                    <label>姓名：</label>
                                    <label>${entity.name}</label>
                                </li>
                                <li>
                                    <label>电话：</label>
                                    <label>${entity.phone}</label>
                                </li>
                                <li>
                                    <label>开始时间：</label>
                                    <label>${entity.promotionBeginTime }</label>
                                </li>
                                <li>
                                    <label>结束时间：</label>
                                    <label>${entity.promotionEndTime }</label>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="table_lay">
                    <div class="table_window">
                        <div class="sub_title"><i class="iconfont icon-kaohe"> |</i> 品质考核</div>
                        <div class="item_introduction">
                            <ul>
                                <li>
                                    <label>通过标准：</label>
                                    <label>13个月继续率 &ge; 85%</label>
                                </li>
                                <li>
                                    <label>本次13个月继续率：</label>
                                    <label><span class="pointer_num">${renewalRate }%</span></label>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="table_lay">
                    <div class="table_window">
                        <div class="sub_title"><i class="iconfont icon-leiji"> |</i> 育成分部</div>
                        <div class="item_introduction">
                            <ul>
                                <li>
                                    <label>通过标准：</label>
                                    <label>累计直接育成分部 &ge; 10个，其中1+4+4分部 &ge; 6个</label>
                                </li>
                                <li>
                                    <label>本次累计直接育成分部：</label>
                                    <label><span class="pointer_num">${fenbuNum }个</span></label>
                                </li>
                                <li>
                                    <label>其中1+4+4分部：</label>
                                    <label><span class="pointer_num">${oneFourFour }个</span></label>
                                </li>
                            </ul>
                        </div>
                        <table>
                            <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>分部名称</th>
                                    <th>所属督训区</th>
                                    <th>所属省级机构</th>
                                    <th>1+<span class="red">4</span>+4</th>
                                    <th>1+4+<span class="red">4</span></th>
                                    <th>是否为1+4+4</th>
                                </tr>
                            </thead>
                            <tbody>
                          	  <c:forEach items="${slist}" var="item" varStatus="st">
                                <tr>
                                    <td>${st.count}</td>
                                    <td>${item.storeName}</td>
                                    <td>${item.countyFranchiseesName}</td>
                                    <td>${item.provinceName}</td>
                                    <td>${item.remark}</td>
                                    <td>${item.floatPay}</td>
                                    <c:if test="${item.isStandard=='是'}">
	                                    	<td class="yes">是</td>
	                                </c:if>
                                    <c:if test="${item.isStandard=='否'}">
                                    	<td class="no">否</td>
                                    </c:if>
                                </tr>
                               </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="table_lay">
                    <div class="table_window">
                        <div class="sub_title"><i class="iconfont icon-kaoshi"> |</i> 培训考试</div>
                        <div class="item_introduction">
                            <ul>
                                <li>
                                    <label>是否通过培训和考试：</label>
                                    <span class="exam" id="exam">
                                    	 <c:if test="${entity.status=='0'}">
	                                        <button class="success" type="button" data-v="1">通过</button>
	                                        <button class="failed selected" type="button" data-v="0">未通过</button>
                                    	 </c:if>
                                    	 <c:if test="${entity.status!='0' && entity.isPass=='1'}">
                                    	 	<button class="success selected" type="button" data-v="1">通过</button>
                                    	 </c:if>
                                    	 <c:if test="${entity.status!='0' && entity.isPass!='1'}">
                                    	 	<button class="failed selected" type="button" data-v="0">未通过</button>
                                    	 </c:if>
                                    </span>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<%@include file="../public/public_script.jsp" %>
<script src="${ctx}/static/js/staff/detail_promotion.js"></script>
</body>
</html>