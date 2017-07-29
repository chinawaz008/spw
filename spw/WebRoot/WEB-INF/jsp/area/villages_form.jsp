<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("ctx", request.getContextPath());%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${ctx}/static/css/public/add3.css" />
</head>
<body data-message="${message}">
<div class="body">
 	<form id="add_villages_form" method="post" role="form">
    <div class="theme_lay">
        <div class="theme">
            <label class="main_theme"><c:if test="${!empty flag}">修改</c:if><c:if test="${empty flag}">新增</c:if>居委会</label>
              <label class="sub_theme">（请录入居委会信息）</label>
          </div>
          <div class="btns">
              <button type="submit" id="btn_submit" class="btn_green"><i class="iconfont icon-zhengque"></i> 提交</button>
          </div>
      </div>
      <div class="tab_lay">
          <ul class="tab_controller">
              <li class="active"><i class="iconfont icon-jibenxinxi"></i> 基本信息</li>
          </ul>
		<input type="hidden" id="villageId" name="villageId" value="<c:if test="${empty flag}">0</c:if><c:if test="${!empty flag}">${entity.villageId}</c:if>"/>
		<input type="hidden" id="provinceNo" name="provinceNo" value="${entity.provinceNo}"/>
		<input type="hidden" id="regionNo" name="regionNo" value="${entity.regionNo}"/>
		<input type="hidden" id="countyNo" name="countyNo" value="${entity.countyNo}"/> 
		<input type="hidden" id="townNo" name="townNo" value="${entity.townNo}"/>
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
                               <td class="item_name">所在街道：</td>
                               <td>
                                  <select  id="townId"  onchange="setVillagesName()" class="required" data-c="街道">
             						<option value="">请选择街道</option>
                       			 </select> 
                       			  <input type="hidden" id="townName" name="townName">
                            </td> 
                         	<td class="item_name">居委会名称：</td>
                        	<td><input type="Text" name="villageName" id="villageName" value="${entity.villageName}" class="required" data-c="居委会名称"></td> 
                         	<td class="item_name">居委会编号：</td>
                        	<td><input type="Text" name="villageNo" id="villageNo" value="${entity.villageNo}" class="required" data-c="居委会编号"></td> 
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
<script src="${ctx}/static/js/area/add_villages.js"></script>
</body>
</html>