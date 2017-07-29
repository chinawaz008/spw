<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@include file="../public/public_head.jsp" %>
<title>民盛钱包明细管理</title>
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
                    <li>钱包明细管理</li>
                </ul>
            </div>
            <div class="body">
                <div class="theme_lay">
                    <div class="theme">
                        <label class="main_theme">钱包明细管理</label>
                        <label class="sub_theme">（管理所有钱包明细）</label>
                    </div>
                  <!--   <div class="btns">
                        <a href="add_dimission">
                            <button type="button" class="btn_blue"><i class="iconfont icon-jiahao"></i> 新增离职</button>
                        </a>
                    </div> -->
                </div>
                <div class="search_lay">
                    <form id="search_form">
                        <div class="search_div">
                        		    人员姓名
                            <input type="text" name="staffName" placeholder="请输入人员姓名"/> 
                               	   身份证号
                            <input type="text" name="idCard" placeholder="请输入身份证号码"/> 
                            <button type="button" class="first_search search_btn">条件搜索</button>
                            <!-- <button type="button" class="btn_orange" id="more_condition_btn"><i class="iconfont icon-xiala"></i> <span>更多</span></button> -->
                        </div>
                    </form>
                </div>
                <div class="table_lay">
                
                    <div class="table_window" data-style="overflow-x: scroll;">
                        <table data-style="width:1300px">
                            <thead>
                                <tr> 
                                	<th>条线</th> 
                                	<th>省公司</th>
                                	<th>督训区</th>
                                    <th>分部</th> 
                                    <th>交易人员</th>
                                    <th>交易类型</th>
                                    <th>交易种类</th>
                                    <th>交易金额</th>
                                    <th>交易日期</th>
                                </tr>
                            </thead>
                            <tbody id="flexible_tbody">
                                <tr class="templet hide">  
                                	<td>@{lineName}@</td> 
                                	<td>
                                		^{ '@{lineType}@'!='999'^^ @{branchCompany}@ ^^ @{provinceName}@ }^
                                	</td> 
                                	<td>@{dxq}@</td> 
                                	<td>@{branch}@</td>
                                	<td>@{staffName}@</td>
                                    <td>@{dealTypeName}@</td>
                                    <td>@{dealCategoryName}@</td>
                                    <td>@{dealMoney}@</td>
                                    <td>@{dealTime}@</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <ul class="pagination" id="pagers"></ul>
                    <i class="clear"></i>
                </div>
            </div>
        </div>
    </div>
<%@include file="../public/public_script.jsp" %>
<script src="${ctx}/static/js/public/public_list.js"></script>
<script src="${ctx}/static/js/staff/money_pack_detail_list.js"></script>
</body>
</html>