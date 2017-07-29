<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("ctx", request.getContextPath());%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${ctx}/static/css/public/add3.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/static/css/staff/detail_promotion.css">
    <script type="text/javascript" src="${ctx }/static/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
            <div class="body">
                <div class="theme_lay">
                    <div class="theme">
                        <label class="main_theme">分部成员晋升分部经理达标情况</label>
                        <label class="sub_theme">（管理晋升通过）</label>
                    </div>
                    <c:if test="${entity.status=='0'}">
	                    <div class="btns">
	                        <a href="javascript:;">
	                            <button type="button" id="pass_btn" class="btn_green" data-id="${entity.id}"><i class="iconfont icon-jinsheng"></i> 晋升通过</button>
	                        </a>
	                        <a href="javascript:;">
	                            <button type="button" id="no_btn" class="btn_red" data-id="${entity.id}"><i class="iconfont icon-quxiao1"></i> 晋升失败</button>
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
                        <div class="sub_title"><i class="iconfont icon-kaohe"> |</i> 累计标保</div>
                        <div class="item_introduction">
                            <ul>
                                <li>
                                    <label>通过标准：</label>
                                    <label>所辖团队（含本人）累计标保 &ge; 4万元</label>
                                </li>
                                <li>
                                    <label>累计标保：</label>
                                    <label><span class="pointer_num">${biaobao }</span></label>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="table_lay">
                    <div class="table_window">
                        <div class="sub_title"><i class="iconfont icon-leiji"> |</i> 推荐考核</div>
                        <div class="item_introduction">
                            <ul>
                                <li>
                                    <label>通过标准：</label>
                                    <label>直接推荐分部成员 &ge; 4人，且除本人外有效人力 &ge; 2人</label>
                                </li>
                                <li>
                                    <label>推荐成员：</label>
                                    <label><span class="pointer_num">${peopleNum }人</span></label>
                                </li>
                                <li>
                                    <label>有效人力：</label>
                                    <label><span class="pointer_num">${efficientPeople }人</span></label>
                                </li>
                            </ul>
                        </div>
                        <table>
                            <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>成员姓名</th>
                                    <th>联系电话</th>
                                    <th>标保</th>
                                    <th>是否为有效人力</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach items="${slist}" var="item" varStatus="st">
	                            	 <tr>
	                                    <td>${st.count}</td>
	                                    <td>${item.name }</td>
	                                    <td>${item.phoneNum }</td>
	                                    <td>${item.floatPay }</td>
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
<%@include file="../public/public_script3.jsp" %>
<script src="${ctx}/static/js/public/public_add.js"></script>
<script src="${ctx}/static/js/staff/detail_promotion.js"></script>
</body>
</html>