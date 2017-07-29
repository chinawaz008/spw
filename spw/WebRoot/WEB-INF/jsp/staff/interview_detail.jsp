<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% request.setAttribute("ctx", request.getContextPath());%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="${ctx}/static/css/public/iframe_detail.css" />
<title>民盛</title>
</head>
<body>
	<ul class="tab_controllers" id="tab_controllers">
		<li class="selected">个人信息</li>
		<li>工作经历</li>
		<li>教育经历</li>
	</ul>
	<div class="info_cards">
		<table class="detail_table">
			<tbody>
				<tr>
					<td>姓名</td>
					<td>${entity.applyName}</td>
				</tr>
				<tr>
					<td>性别</td>
					<td>${entity.sex}</td>
				</tr>
				<tr>
					<td>生日</td>
					<td>${entity.birthDate }</td>
				</tr>
				<tr>
					<td>联系方式</td>
					<td>${entity.phone }</td>
				</tr>
				<tr>
					<td>最高学历毕业院校及专业</td>
					<td>${entity.educationSchool }</td>
				</tr>
				<tr>
					<td>民族</td>
					<td>${entity.nation }</td>
				</tr>
				<tr>
					<td>政治面貌</td>
					<td>${entity.politicalStatus }</td>
				</tr>
				<c:if test="${!empty entity.skills }">
					<tr>
						<td>技能/获得证书</td>
						<td>${entity.skills }</td>
					</tr>
				</c:if>
				<tr>
					<td>自我评价</td>
					<td>${entity.memo }</td>
				</tr>
				<tr>
					<td>是否有亲友在本公司任职</td>
					<td>${entity.relationType }</td>
				</tr>
				<tr>
					<td>亲友姓名</td>
					<td>${entity.relationName }</td>
				</tr>
				<tr>
					<td>亲友部门</td>
					<td>${entity.relationDepartment }</td>
				</tr>
				<tr>
					<td>亲友职务</td>
					<td>${entity.relationPosition }</td>
				</tr>
				<tr>
					<td>亲友关系</td>
					<td>${entity.relation }</td>
				</tr>
				<tr>
					<td>亲友电话</td>
					<td>${entity.relationPhone }</td>
				</tr>
			</tbody>
		</table>
		

		<table class="detail_table hide">
			<tbody>
				<c:forEach items="${experiences }" var="item" varStatus="st">
					<!-- <tr>
						<td>
							<hr style="border:1px dashed black;">
						</td>
					</tr> -->
					<tr>
						<td>时间</td>
						<td>${item.beginDate} - ${item.endDate}</td>
					</tr>
					<tr>
						<td>工作单位</td>
						<td>${item.workPlace}</td>
					</tr>
					<tr>
						<td>职位</td>
						<td>${item.workPosition }</td>
					</tr>
					<tr>
						<td>离职原因</td>
						<td>${item.leaveReason }</td>
					</tr>
					<tr>
						<td>证明人及电话</td>
						<td>${item.certifyInfo }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<table class="detail_table hide">
			<tbody>
				<c:forEach items="${educationExperiences }" var="item" varStatus="st">
					<tr>
						<td>时间</td>
						<td>${item.beginDate} - ${item.endDate}</td>
					</tr>
					<tr>
						<td>学校</td>
						<td>${item.educationName }</td>
					</tr>
					<tr>
						<td>专业</td>
						<td>${item.major }</td>
					</tr>
					<tr>
						<td>学历</td>
						<td>${item.education }</td>
					</tr>
					<tr>
						<td>学习形式</td>
						<td>${item.educationWay }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
<script src="${ctx}/static/js/public/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
$(function() {
    //切换tab页
    $('#tab_controllers li').click(function(){
        var index = $(this).index();
        $(this).addClass('selected').siblings().removeClass('selected');
        $('.detail_table:eq(' + index +')').show().siblings().hide();
    });
})
</script>
</body>
</html>