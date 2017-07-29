<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@include file="public/public_head.jsp" %>
    <title>信息修改</title>
    <link rel="stylesheet" href="${ctx}/static/css/public/add.css" />
    <script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript">
	/**
	 * 函数：从页面获取整理数据，提交给后台
	 * 输入：toUrl(提交地址),datas(额外的数据)
	 * 输出：
	**/
	function submits(toUrl,datas) {
	    var staff = JSON.stringify(formToObject($("#staff_form")));
	    datas = $.extend(datas,{staff:staff});
	    $.ajax({
	        type: "post",
	        url: ctx + toUrl,
	        data: datas,
	        dataType: "json",
	        success: function(msg) {
	            if(msg.result == 1){
	           		 window.location.href = ctx;
	        	}else{
	        		alert(msg.message);
	        	}
	       }
    });
	}
	$(function(){
	//所有select在页面加载后显示值
    $("select").each(function(){
        var value = $(this).data("v");
        $(this).find("option[value='" + value + "']").attr("selected",true);
    });
		
  	 $("#edit_submit_btn").click(function(){
  		var old = $("#oldPasswd").val();
       	var new1 = $("#newPasswd1").val();
       	var new2 = $("#newPasswd2").val();
       	if(old!=null&&old!=""){
       		if(new1==null||new1==""){ 
       			alert("请输入新密码");
       			return false;
       		}else if(new2==null||new2=="") {
       			alert("请再次输入新密码");
       			return false;
       		}else if(new1!=new2) {
       			alert("两次密码输入不一致");
       			return false;
       		}
       	}
       	if(new1!=null&&new1!=""){
       		if(old==null||old==""){ 
       			alert("请输旧密码");
       			return false;
       		}else if(new2==null||new2=="") {
       			alert("请再次输入新密码");
       			return false;
       		}
       	}
    	if(new2!=null&&new2!=""){
       		if(old==null||old==""){ 
       			alert("请输旧密码");
       			return false;
       		}else if(new1==null||new1=="") {
       			alert("请输入新密码");
       			return false;
       		}
       	}
        if (checkRequired() && checkFormat() && confirm("您确定保存修改？")) {
        	var datas = {staffId:$(this).data("i"),"oldPasswd":old,"newPasswd1":new1};
        	submits("/staff/updateInfo",datas);
        }
    });
})
</script>
</head>
<body>
    <div class="box">
        <%@include file="public/public_side.jsp" %>
        <div class="main">
            <%@include file="public/public_title.jsp" %>
            <div class="crumbs">
                <ul>
                    <li><a href="${ctx}/"><i class="iconfont icon-zhuye"></i> 首页</a></li>
                    <li>信息修改</li>
                </ul>
            </div>
            <div class="body">
                <div class="theme_lay">
                    <div class="theme">
                        <label class="main_theme">个人信息密码修改</label>
                    </div>
                    <div class="btns">
                        <button type="button" class="btn_blue goback"><i class="iconfont icon-quxiao"></i> 返回</button>
                        <button type="button" class="btn_orange reload"><i class="iconfont icon-shuaxin"></i> 恢复</button>
                        <button type="button" class="btn_green" id="edit_submit_btn" data-i="${staff.id}"><i class="iconfont icon-zhengque"></i> 保存</button>
                    </div>
                </div>
                <div class="tab_lay">
                    <ul class="tab_controller">
                        <li class="active"><i class="iconfont icon-jibenxinxi"></i> 基本信息</li>
                    </ul>
                    <div class="tab_bodies">
                        
                        <div class="tab_body">
                            <form id="staff_form">
                                <table>
                                    <tbody>
                                        <tr>
                                            <td class="item_name">姓名：</td>
                                            <td><input type="text" name="name" value="${staff.name}" readonly="readonly"></td>
                                        </tr>
                                        
                                        <tr>
                                            <td class="item_name">QQ号码：</td>
                                            <td><input type="text" name="qq" value="${staff.qq}"></td>
                                        </tr>
                                        <tr>
                                            <td class="item_name">微信号：</td>
                                            <td><input type="text" class="input_m" name="wechatId" value="${staff.wechatId}"></td>
                                        </tr>
                                        <tr>
                                            <td class="item_name">现住址：</td>
                                            <td colspan="7">
                                               <select class="s_province" id="provinceId" name="provinceId" data-v="${staff.provinceId}">
                                                	<option value="">请选择省</option>
                                                    <c:forEach items="${plist}" var="itme" varStatus="st">
                                                		 <option value="${itme.provinceNo}">${itme.provinceName}</option>
                                                	</c:forEach>
    						                    </select>
                                                <select class="s_region" name="regionId" data-v="${staff.regionId}">
                                                    <option value="">请选择市</option>
                                                     <c:forEach items="${relist}" var="itme" varStatus="st">
                                                		 <option value="${itme.regionNo}">${itme.regionName}</option>
                                                	</c:forEach>
                                                </select>
                                                <select class="s_county" name="countyId" data-v="${staff.countyId}">
                                                    <option value="">请选择区县</option>
                                                    <c:forEach items="${clist}" var="itme" varStatus="st">
                                                		 <option value="${itme.countyNo}">${itme.countyName}</option>
                                                	</c:forEach>
                                                </select>
                                                <input type="text"  name="address" value="${staff.address}">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="item_name">移动电话：</td>
                                            <td><input type="text" name="phoneNum" data-c="手机号码"  class="required formated" value="${staff.phoneNum}"></td>
                                            <td class="item_name">固定电话：</td>
                                            <td><input type="text" name="telephone" data-c="固定电话"  class="required formated"  value="${staff.telephone}"></td>
                                        </tr>
                                        <tr>
                                            <td class="item_name">旧密码：</td>
                                            <td><input type="password" id="oldPasswd" min="6" ></td>
                                        </tr>
                                         <tr>
                                        	<td class="item_name">新密码：</td>
                                            <td><input type="password" id="newPasswd1" ></td>
                                         </tr>
                                         <tr>
                                            <td class="item_name">新密码确认：</td>
                                            <td><input type="password" id="newPasswd2" ></td>
                                        <tr>
                                    </tbody>
                                </table>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<%@include file="public/public_script.jsp" %>
<script src="${ctx}/static/js/public/public_add.js"></script>
<script src="${ctx}/static/js/staff/add_staff.js"></script>
</body>
</html>