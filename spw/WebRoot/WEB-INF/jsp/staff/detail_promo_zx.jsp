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
                        <label class="main_theme">督训晋升专训达标情况</label>
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
                        <div class="sub_title"><i class="iconfont icon-kaohe"> |</i> 育成考核</div>
                        <div class="item_introduction">
                            <ul>
                                <li>
                                    <label>通过标准：</label>
                                    <label>累计育成督训室 &ge; 3个（含本人直辖督训室）</label>
                                </li>
                                <li>
                                    <label>本次累计育成：</label>
                                    <label><span class="pointer_num">${fenbuNum }个</span></label>
                                </li>
                            </ul>
                        </div>
                        <table>
                            <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>督训室</th>
                                    <th>联系电话</th>
                                    <th>人力</th>
                                    <th>标保</th>
                                </tr>
                            </thead>
                            <tbody>
                              <c:forEach items="${slist}" var="item" varStatus="st">
                                <tr>
                                    <td>${st.count }</td>
                                    <td>${item.name }</td>
                                    <td>${item.phoneNum }</td>
                                    <td>${item.personCount }</td>
                                    <td>${item.floatPay}</td>
                                </tr>
                              </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="table_lay">
                    <div class="table_window">
                        <div class="sub_title"><i class="iconfont icon-renli"> |</i> 人力考核</div>
                        <div class="item_introduction">
                            <ul>
                                <li>
                                    <label>通过标准：</label>
                                    <label>所辖团队总人力 &ge; 90人</label>
                                </li>
                                <li>
                                    <label>目前总人力：</label>
                                    <label><span class="pointer_num">${personCount }人</span></label>
                                </li>
                            </ul>
                        </div>
                        
                    </div>
                </div>

                <div class="table_lay">
                    <div class="table_window">
                        <div class="sub_title"><i class="iconfont icon-leiji"> |</i> 累计标保</div>
                        <div class="item_introduction">
                            <ul>
                                <li>
                                    <label>通过标准：</label>
                                    <label>所辖团队晋升期间标准保费 &ge; 30万（本人自保件不计入）</label>
                                </li>
                                <li>
                                    <label>目前累计标保：</label>
                                    <label><span class="pointer_num">${biaobao }元</span></label>
                                </li>
                            </ul>
                        </div>
                        
                    </div>
                </div>

                <div class="table_lay">
                    <div class="table_window">
                        <div class="sub_title"><i class="iconfont icon-kaoshi"> |</i> 测评考核</div>
                        <div class="item_introduction">
                            <ul>
                                <li>
                                    <label>是否通过公司民主测评及素质考核：</label>
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