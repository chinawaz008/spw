<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("ctx", request.getContextPath());%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${ctx}/static/css/public/add3.css" />
</head>
<body>
            <div class="body">
             	<form id="add_towns_form" method="post" role="form">
                <div class="theme_lay">
                    <div class="theme">
                        <label class="main_theme"><c:if test="${!empty flag}">修改</c:if><c:if test="${empty flag}">新增</c:if>街道</label>
                        <label class="sub_theme">（请录入街道信息）</label>
                    </div>
                    <div class="btns">
                        <button type="submit" id="btn_submit" class="btn_green"><i class="iconfont icon-zhengque"></i> 提交</button>
                    </div>
                </div>
                <div class="tab_lay">
                    <ul class="tab_controller">
                        <li class="active"><i class="iconfont icon-jibenxinxi"></i> 基本信息</li>
                    </ul>
			        <input type="hidden" id="townId" name="townId" value="<c:if test="${empty flag}">0</c:if><c:if test="${!empty flag}">${entity.townId}</c:if>"/>
			        <input type="hidden" id="provinceNo" name="provinceNo" value="${entity.provinceNo}"/>
			        <input type="hidden" id="regionNo" name="regionNo" value="${entity.regionNo}"/>
			        <input type="hidden" id="countyNo" name="countyNo" value="${entity.countyNo}"/> 
			        <input type="hidden" id="flag" value="${flag}">
                    <div class="tab_bodies">
                        <div class="tab_body">
                            <table>
                                <tbody id="Tbl">
                                    <tr>
                                        <td class="item_name">所在省份：</td>
                                        <td>
                                            <select id="provinceId" name="provinceId" onchange="setRegionName()" class="required" data-c="省份">
	                        					<option value="">请选择省份</option>
                                            </select>
                                    		<input type="hidden" id="provinceName" name="provinceName">
                                        </td> 
                                        
                                        <td class="item_name">所在城市：</td>
                                        <td>
                                            <select  id="regionId" name="regionId" onchange="setCountyName()" class="required" data-c="城市">
	                        					<option value="">请选择城市</option>
                                    		</select>  
                                    		<input type="hidden" id="regionName" name="regionName">
                                        </td> 
                                        
                                        <td class="item_name">所在县区：</td>
                                        <td>
                                            <select  id="countyId" name="countyId" onchange="setTownsName()" class="required" data-c="县区">
	                        					<option value="">请选择县区</option>
                                   			 </select> 
                                   			 <input type="hidden" id="countyName" name="countyName">
                                        </td> 
                                    </tr> 
                                    <tr>
	                                     <td class="item_name">街道名称：</td>
	                                     <td><input type="Text" name="townName" id="townName" value="${entity.townName}" class="required" data-c="街道名称"></td>
	                                     <td class="item_name">街道编码：</td>
                                   		 <td><input type="Text" name="townNo" id="townNo" value="${entity.townNo }" class="required" data-c="街道编码"></td>
                                   		 <td class="item_name">邮编：</td>
                                   		 <td><input type="Text" name="zipCode" id="zipCode" value="${entity.zipCode }" class="required formated" data-c="邮编"></td>
                                    </tr>  
                      
                                </tbody>
                            </table>
                        </div>
                </div>
            </div>
            </form>
        </div>
<%@include file="../public/public_script3.jsp" %>
<script src="${ctx}/static/js/public/public_add.js"></script>
<script src="${ctx}/static/js/plugins/kuidate/MSCalendar.js"></script>
<script src="${ctx}/static/js/area/add_towns.js"></script>
</body>
</html>