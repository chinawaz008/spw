<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("ctx", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@include file="../public/public_head_list.jsp" %>  
</head>
<body>
            <div class="body">
                <div class="theme_lay">
                    <div class="theme">
                        <label class="main_theme">后援人员调岗管理</label>
                        <label class="sub_theme">（管理后援人员调岗）</label>
                    </div>
                    <div class="btns">
                        <a href="add_position">
							<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
								<c:if test="${item == 72}">
		                            <button type="button" class="btn_blue"><i class="iconfont icon-jiahao"></i> 新增调岗</button>
								</c:if> 
							</c:forEach>
                        </a>
                    </div>
                </div>
                <div class="search_lay">
                 <div class="controller_div">
			        <i class="iconfont icon-sousuo-sousuo"> |</i> 查询筛选
			        <i class="iconfont right_icon icon-shangla" id="right_icon"></i>               
			    </div>
                    <form id="search_form">
                       <div class="search_div" id="search_div">
                        		    人员姓名
                            <input type="text" name="name" placeholder="请输入人员姓名"/><button type="button" class="first_search search_btn">条件搜索</button>
                            <button type="button" class="btn_orange" id="more_condition_btn"><i class="iconfont icon-xiala"></i> <span>更多</span></button>
                        </div>
                        <div class="table_div" id="table_div">
                            <table style="width: 50%">
                                <tbody>
                                    <tr>
                                        <td>岗位号</td>
                                        <td><input type="text" name="workNum" placeholder="请输入岗位号"></td>
                                        <td>身份证号</td>
                                        <td><input type="text" name="idCard" placeholder="请输入身份证号"></td>
                                    </tr>
                                </tbody>
                            </table>
                            <button type="button" class="btn_blue search_btn"><i class="iconfont icon-sousuo"></i> 搜索</button>
                        </div>
                    </form>

                </div>
                <div class="table_lay">
                    <div class="table_window" data-style="overflow-x: scroll;">
                        <table data-style="width:1300px">
                            <thead>
                                <tr>
                                    <!-- <th>省级机构</th>
                                    <th>督训区</th>
                                    <th>分部</th> -->
                                    <th>当前职级</th>
                                    <th>予晋升职级</th>
                                    <th>姓名</th>
                                    <th>岗位号</th>
                                    <th>身份证号</th>
                                    <th>手机号码</th>
                                    <th>状态</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody id="flexible_tbody">
                                <tr class="templet hide">
                                    <td>@{currentJob}@</td>
                                    <td>@{toJob}@</td>
                                    <td>@{name}@</td>
                                    <td>@{worknum}@</td>
                                    <td>@{idCard}@</td>
                                    <td>@{phoneNum}@</td>
                                    <td>@{status}@</td>
                                    <td>
										<c:forEach var="item" items="${buttonAuthorization }" varStatus="st">
											<c:if test="${item == 73}">
			                                    <a href="update_position/@{id}@">
			                                        <button type="button" class="btn_green" >编辑</button>
						                        </a>
											</c:if> 
										</c:forEach>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <ul class="pagination" id="pagers"></ul>
                    <i class="clear"></i>
                </div>
            </div>
<%@include file="../public/public_script3.jsp" %>
<script src="${ctx}/static/js/public/public_list3.js"></script>
<script src="${ctx}/static/js/staff/list_position.js"></script>
</body>
</html>