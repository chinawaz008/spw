<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("ctx", request.getContextPath());%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setAttribute("basePath", request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/");%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1">
  <base href="${basePath}" />
  <%@include file="../public/public_icon.jsp" %>  
  <link rel="stylesheet" href="./static/css/public/subframe.css" />
  <link rel="stylesheet" href="./static/css/staff/detail_salary.css" />
</head>
<body>
	<div class="theme_lay">
		<div class="theme">
			<label class="main_theme">工资详情</label>
		</div>
		<div class="btns">
			
		</div>
	</div>
	<div class="board">
		<div class="simple">
			<div class="up">
				<i class="iconfont icon-geren"> </i>
				<span>${salary.name}</span>
				<label>${salary.position}</label>
			</div>
			<div class="down">
				<p><span class="strong">所在督训区：</span><span>${salary.countyFranchiseesName}督训区</span></p>
				<p><span class="strong">联系地址：</span><span>${salary.address}</span></p>
			</div>
		</div>

		<div class="simple">
			<span class="month">${fn:substring(salary.salaryDate, 5, 8)}月</span>
			<div class="income"><div class="inner"><span class="red">${salary.total}</span><br/><span class="coffee">收入总额</span></div></div>
			<ul class="ul_list">
				<li><span class="strong">基本薪：</span> <span>${salary.basicSalary}</span></li>
				<li><span class="strong">首年佣金：</span> <span>${salary.initialCommission}</span></li>
				<li><span class="strong">续期佣金：</span> <span>${salary.renewalCommission}</span></li>
				<li><span class="strong">推荐奖：</span> <span>${salary.recommendationAward}</span></li>
				<li><span class="strong">管理津贴：</span> <span>${salary.managementAllowance}</span></li>
				<li><span class="strong">育成奖：</span> <span>${salary.ycAward}</span></li>
			</ul>
		</div>

		<div class="salary_item">
			<div class="top">
				基本薪
				<span class="tip"><i class="iconfont icon-yichang"> </i>该数据2天更新一次，请以实际数据为准</span>
				<i class="iconfont icon-shangla controller"> </i>
			</div>
			<div class="body">
				<p class="come_on">加油！分部级别离<span class="">"${nextLevel}"档</span>还差${distance}元，再努力一下收入更上一个台阶哦~</p>
				<div class="allowance">
					<span>基本薪</span><br />
					<span class="num">${salary.basicSalary+salary.operatingExpenses+salary.carAllowance}</span>
					<div class="arrow-left"></div>
					<div class="arrow-right"></div>
				</div>
				<ul class="ul_list">
					<li><span class="strong">分部级别：</span> <span>${salary.currentLevel}</span></li>
					<li><span class="strong">月度分部业绩：</span> <span>${salary.fbAchievement}</span></li>
					<li><span class="strong">月度分部基本薪：</span> <span>${salary.basicSalary}</span></li>
					<li><span class="strong">月度经营费用：</span> <span>${salary.operatingExpenses}</span></li>
					<li><span class="strong">月度车辆补贴：</span> <span>${salary.carAllowance}</span></li>
				</ul>
			</div>
		</div>

		<div class="salary_item">
			<div class="top"> 
				推荐奖
				<span class="tip"><i class="iconfont icon-yichang"> </i>该数据2天更新一次，请以实际数据为准</span>
				<i class="iconfont icon-shangla controller"> </i>
			</div>
			<div class="body">
				<div class="recommend">
					<div class="outer">
						<div class="inner">
							<span class="coffee">推荐奖</span><br />
							<span class="num">${salary.recommendationAward }</span>
						</div>
					</div>
				</div>
				<table class="table_item">
					<thead>
						<tr>
							<th>姓名</th>
							<th>工号</th>
							<th>新单佣金</th>
							<th>新单佣金15%</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>张三</td>
							<td>12345678911</td>
							<td>4000.00</td>
							<td>750</td>
						</tr>
						<tr>
							<td>张三</td>
							<td>12345678911</td>
							<td>4000.00</td>
							<td>750</td>
						</tr>
						<tr>
							<td>张三</td>
							<td>12345678911</td>
							<td>4000.00</td>
							<td>750</td>
						</tr>
					</tbody>
				</table>
				<ul class="pages">
					<li class="selected">1</li>
					<li>2</li>
					<li>3</li>
					<li>4</li>
					<li>5</li>
				</ul>
			</div>
		</div>

		<div class="salary_item">
			<div class="top">
				首年佣金
				<span class="tip"><i class="iconfont icon-yichang"> </i>该数据2天更新一次，请以实际数据为准</span>
				<i class="iconfont icon-shangla controller"> </i>
			</div>
			<div class="body">
				<ul class="ul_commission">
					<c:forEach var="item" items="${salary.initialCommissionDetail }" varStatus="st">
						<li>
							<div class="arrow-right"></div>
							<div class="commission">
								<span class="num">${item.money}</span><br/>
								首年佣金
							</div>
							<div class="year_rate">
								缴费年限 <span class="red">${item.name}年</span><br />
								佣金比例 <span class="red">
								<c:choose> 
								<c:when test="${item.name=='20' }">30%</c:when>
								<c:otherwise>20%</c:otherwise>
								</c:choose>
								</span>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div class="salary_item">
			<div class="top">
				续期佣金
				<span class="tip"><i class="iconfont icon-yichang"> </i>该数据2天更新一次，请以实际数据为准</span>
				<i class="iconfont icon-shangla controller"> </i>
			</div>
			<div class="body">
				<div class="persistency">
					<div class="arrow-left"></div>
					<div class="arrow-right"></div>
					<span>13个月继续率</span><br />
					<span class="num">${renewalRateCommissionDetail[0].fee*100}%</span><br />
					<span>续期佣金 </span><span class="red">0${renewalRateCommissionDetail[0].money}</span>
				</div>
				<ul class="history">
					<li>
						<div class="arrow-right"></div>
						<span class="year">三年度</span>
						<span>继续率 <span>${renewalRateCommissionDetail[1].fee*100}%</span></span>
						<span class="red">0${renewalRateCommissionDetail[1].money}</span>
					</li>
					<li>
						<div class="arrow-right"></div>
						<span class="year">四年度</span>
						<span>继续率 <span>${renewalRateCommissionDetail[2].fee*100}%</span></span>
						<span class="red">0${renewalRateCommissionDetail[2].money}</span>
					</li>
					<li>
						<div class="arrow-right"></div>
						<span class="year">五年度</span>
						<span>继续率 <span>${renewalRateCommissionDetail[3].fee*100}%</span></span>
						<span class="red">0${renewalRateCommissionDetail[3].money}</span>
					</li>
				</ul>
			</div>
		</div>

		<div class="salary_item">
			<div class="top">
				育成奖
				<span class="tip"><i class="iconfont icon-yichang"> </i>该数据2天更新一次，请以实际数据为准</span>
				<i class="iconfont icon-shangla controller"> </i>
			</div>
			<div class="body">
				<div class="yucheng">
					<div class="arrow-left"></div>
					<div class="arrow-right"></div>
					<span>育成津贴总额</span><br />
					<span class="num">${salary.ycAward }</span><br />
				</div>
				<div class="up">
					<div class="table_theme">月度分部业绩4万以下</div>
					<table class="table_item">
						<thead>
							<tr>
								<th>分部名称</th>
								<th>分部业绩</th>
								<th>新单佣金15%</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>张三</td>
								<td>4000.00</td>
								<td>750</td>
							</tr>
							<tr>
								<td>张三</td>
								<td>4000.00</td>
								<td>750</td>
							</tr>
							<tr>
								<td>张三</td>
								<td>4000.00</td>
								<td>750</td>
							</tr>
						</tbody>
					</table>
					<ul class="pages">
						<li class="selected">1</li>
						<li>2</li>
						<li>3</li>
						<li>4</li>
						<li>5</li>
					</ul>
					
				</div>
				<div class="down">
					<div class="table_theme">月度分部业绩4万以上</div>
					<table class="table_item">
						<thead>
							<tr>
								<th>分部名称</th>
								<th>分部业绩</th>
								<th>新单佣金15%</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>张三</td>
								<td>4000.00</td>
								<td>750</td>
							</tr>
							<tr>
								<td>张三</td>
								<td>4000.00</td>
								<td>750</td>
							</tr>
							<tr>
								<td>张三</td>
								<td>4000.00</td>
								<td>750</td>
							</tr>
						</tbody>
					</table>
					<ul class="pages">
						<li class="selected">1</li>
						<li>2</li>
						<li>3</li>
						<li>4</li>
						<li>5</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="salary_item">
			<div class="top">
				管理津贴
				<span class="tip"><i class="iconfont icon-yichang"> </i>该数据2天更新一次，请以实际数据为准</span>
				<i class="iconfont icon-shangla controller"> </i>
			</div>
			<div class="body">
				<div class="yucheng">
					<div class="arrow-left"></div>
					<div class="arrow-right"></div>
					<span>管理津贴总额</span><br />
					<span class="num">${salary.managementAllowance }</span><br />
				</div>
				<table class="achievement">
					<tbody>
						<tr>
							<td>分部业绩</td>
							<td><span class="red">${salary.fbAchievement }</span></td>
						</tr>
						<tr>
							<td><span class="red">${fn:length(manageCommissionDetail)}人</span><br />有效人力</td>
							<td class="bg_fe">
								<div class="bg">
									<i class="iconfont icon-man1"></i>
									<i class="iconfont icon-man1"></i>
									<i class="iconfont icon-man1"></i>
									<i class="iconfont icon-man1"></i>
									<i class="iconfont icon-man1"></i>
									<i class="iconfont icon-man1"></i>
									<i class="iconfont icon-man1"></i>
								</div>
								<div class="bg red">
									<c:forEach items="${manageCommissionDetail }" var="item" varStatus="st">
										<i class="iconfont icon-man1"></i>
									</c:forEach>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
				<table class="table_item">
						<thead>
							<tr>
								<th>姓名</th>
								<th>工号</th>
								<th>月度价值保费</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>张三</td>
								<td>12005454545</td>
								<td>4000.00</td>
							</tr>
							<tr>
								<td>张三</td>
								<td>12005454545</td>
								<td>4000.00</td>
							</tr>
							<tr>
								<td>张三</td>
								<td>12005454545</td>
								<td>4000.00</td>
							</tr>
						</tbody>
					</table>
					<ul class="pages">
						<li class="selected">1</li>
						<li>2</li>
						<li>3</li>
						<li>4</li>
						<li>5</li>
					</ul>
			</div>
		</div>
	</div>


	<%@include file="../public/public_script3.jsp"%>

</body>
</html>