<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("ctx", request.getContextPath());%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${ctx}/static/css/public/add3.css" /><link rel="stylesheet" href="${ctx}/static/css/public/subframe.css" />
    <link rel="stylesheet" href="${ctx}/static/css/staff/check_staff_apply.css" />
</head>
<body data-message="${message}">
            <div class="body">
                    <div class="btn_div">
                        <button type="button" class="btn_green" id="add_pro_btn" ><i class="iconfont icon-zhengque"></i> 提交</button>
                    </div>
                <div class="tab_lay">
                    <ul class="tab_controller">
                        <li class="active"><i class="iconfont icon-gongzuojingliline"></i> 基本信息</li>
                    </ul>
                    <div class="tab_bodies">
                        <div class="tab_body">
                            <table class="basic" style="width:100%">
                                <tbody>
                                    <tr>
                                        <td>增员姓名：</td>
                                        <td>${entity.name}</td>
                                        <td>申请职位：</td>
                                        <td>${entity.position}</td>
                                        <td>申请日期：</td>
                                        <td>${entity.applyTime}</td>
                                    </tr>
                                    <tr>
                                        <td>手机号码：</td>
                                        <td>${entity.phone}</td>
                                        <td>身份证号：</td>
                                        <td>${entity.idCard}</td>
                                    </tr>
                                    <tr>
                                    	 <td>未通过原因：</td>
                                         <td colspan="5"><c:if test="${entity.descriptions==null || entity.descriptions==''}">无</c:if>${entity.descriptions}</td>
                                   </tr> 
                                   <tr>
                                         <td>支付问题：</td>
                                         <td colspan="5"><c:if test="${entity.costReason==null || entity.costReason==''}">无</c:if>${entity.costReason}</td>
                                    </tr> 
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <div class="tab_lay">
                    <ul class="tab_controller">
                        <li class="active"><i class="iconfont icon-gongzuojingliline"></i> 身份证信息</li>
                    </ul>
                    <div class="tab_bodies">
                        <div class="tab_body">
                            <table class="idcard">
                                <tbody>
                                    <tr>
                                        <td>
                                            <img class="info_pic" src="${entity.idcardFontImg}">
                                             
                                            <p>身份证正面</p>
                                        </td>
                                        <td>
                                            <img class="info_pic" src="${entity.idcardBackImg}">
                                             
                                            <p>身份证背面</p>
                                        </td>
                                        <td>
                                            <img class="info_pic" src="${entity.idcardAll}">
                                             
                                            <p>手持身份证</p>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
				<input type="hidden" id="applyId" value="${entity.id}"> 
				<form action="" method="post">
				<div class="tab_lay">
                    <ul class="tab_controller">
                        <li class="active"><i class="iconfont icon-idcart"></i> 银行卡信息</li>
                    </ul>
                    <div class="tab_bodies">
                        <div class="tab_body">
                            <table class="others">
                                <tbody>
                                	<c:if test="${entity.salaryBankImg!=null && entity.salaryBankImg!=''}">
                                    <tr>
                                     	<td>
                                            <img class="info_pic" src="${entity.salaryBankImg}">
                                             
                                            <p>佣金发放卡</p>
                                        </td>
                                        <td>
                                            <p>
                                                <span class="bank_info">开户银行(佣金发放卡)：</span>
                                                <input type="text" class="bankCardNos may_required" id="staffBankCardType" 
                                                placeholder="佣金发放卡开户银行" style="width:170px;">
                                            <p>
                                                <span class="bank_info">银行卡号：</span>
                                                <span class="bank_num" class="bank_num"></span><br/>
                                            </p>
                                            <p>
                                                <span class="bank_info"></span>
                                                <input type="text" class="bankCardNo may_required" data-c="银行卡号"  id="staffBankCardNum" placeholder="输入佣金发放卡号" style="width:170px; margin-bottom: 20px">
                                            </p>
                                        </td>
                                     </tr>
                                      </c:if>
                                     <c:if test="${entity.depositTicket!=null && entity.depositTicket!=''}">
	                                     <tr>
		                                        <td>
		                                            <img class="info_pic" src="${entity.depositTicket}">
		                                             
		                                            <p>押金小票</p>
		                                        </td>
		                                    </tr>
	                                     </c:if>
                                     <c:if test="${entity.bankcardImg!=null && entity.bankcardImg!=''}">
	                                     <tr>
	                                        <td>
	                                            <img class="info_pic" src="${entity.bankcardImg}">
	                                             
	                                            <p>代扣银行卡</p>
	                                        </td>
	                                        <td>
	                                            <p>
	                                                <span class="bank_info">开户银行(代扣银行卡)：</span>
	                                                <select id="bankCardType" style="width:170px">
	                                                    <option value="04023610">安徽农村信用社</option>
		                                                <option value="0103">中国农业银行</option>
	                                               		<option value="0105">中国建设银行</option>
	                                               		<option value="0308">招商银行</option>
	                                               		<option value="0403">中国邮政储蓄银行</option>
	                                               		<option value="0302">中信银行</option>
	                                               		<option value="0305">中国民生银行</option>
	                                                    <!--<option value="14144500">山东农村信用社</option>
	                                                    <option value="03143910">福建农村信用社</option>
	                                                    <option value="04024210">江西农村信用社</option>
	                                                    <option value="04025350">湖北农村信用社</option>
	                                                    <option value="04025510">湖南农村信用社</option>
	                                                    <option value="04021910">内蒙农村信用社</option>
	                                                    <option value="04026530">重庆农商行</option> -->
	                                                    
	                                                </select>
	                                            <p>
	                                                <span class="bank_info">银行卡号：</span>
	                                                <span   class="bank_num"></span><br/>
	                                            </p>
	                                            <p>
	                                                <span class="bank_info"></span>
	                                                <input type="text" id="bankCardNo" placeholder="输入代扣银行卡号" class="bankCardNo may_required required formated"  data-c="银行卡号" style="width:170px">
	                                            </p>
	                                        </td>
	                                    </tr>
                                     </c:if>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
				
                <div class="tab_lay">
                    <ul class="tab_controller">
                        <li class="active"><i class="iconfont icon-idcart"></i> 车辆信息</li>
                    </ul>
                    <div class="tab_bodies">
                        <div class="tab_body">
                            <table class="others">
                                <tbody>
                                    <tr>
                                        	<c:if test="${entity.fontDriverLicense!=null && entity.fontDriverLicense!=''}">
                                        <td>
	                                            <img class="info_pic" src="${entity.fontDriverLicense}">
	                                             
	                                            <p>驾驶证正面</p>
                                        </td>
                                        	</c:if>
                                        	<c:if test="${entity.backDriverLicense!=null && entity.backDriverLicense!=''}">
                                        <td>
                                            	<img class="info_pic" src="${entity.backDriverLicense}">
                                             
                                           		<p>驾驶证反面</p>
                                        </td>
                                             </c:if>
                                        	<c:if test="${entity.fontDrivingLicense!=null && entity.fontDrivingLicense!=''}">
                                        <td>
                                            <img class="info_pic" src="${entity.fontDrivingLicense}">
                                             
                                            <p>行驶证正面</p>
                                        </td>
                                            </c:if>
                                    </tr>
                                    <tr>
                                       		<c:if test="${entity.backDrivingLicense!=null && entity.backDrivingLicense!=''}">
                                        <td>
                                            <img class="info_pic" src="${entity.backDrivingLicense}">
                                             
                                            <p>行驶证反面</p>
                                        </td>
                                            </c:if>
                                      		<c:if test="${entity.marriageCertificate!=null && entity.marriageCertificate!=''}">
                                        <td>
                                            <img class="info_pic" src="${entity.marriageCertificate}">
                                             
                                            <p>结婚证</p>
                                        </td>
                                            </c:if>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                
                <div class="tab_lay">
                    <ul class="tab_controller">
                        <li class="active"><i class="iconfont icon-idcart"></i> 确认信息</li>
                    </ul>
                    <div class="tab_bodies">
                        <div class="tab_body">
                            <table class="others">
                                <tbody>
                                    <tr>
                                        <td>
                                            <img class="info_pic" src="${entity.sureBookImg}">
                                             
                                            <p>确认书</p>
                                        </td>
                                        <td>
                                            <img class="info_pic" src="${entity.signImg}">
                                            <p>本人签字</p>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                
                <div class="tab_lay">
                    <ul class="tab_controller">
                        <li class="active"><i class="iconfont icon-idcart"></i> 审核结果</li>
                    </ul>
                    <div class="tab_bodies">
                        <div class="tab_body">
                            <table class="result">
                                <tbody>
                                    <tr>
                                    	<td style="vertical-align: middle">
                                            <i class="iconfont icon-gongwen"></i> 是否发车补？
                                        </td>
                                        <td>
                                            <label><input type="radio" name="car" checked="checked" value="否">否</label>
                                            <label><input type="radio" name="car"  value="是">是</label>
                                        </td>
                                     </tr>
                                     <tr>
                                     	 <td style="vertical-align: middle">
                                            <i class="iconfont icon-gongwen"></i> 是否通过审核？
                                        </td>
                                        <td>
                                            <label><input type="radio" name="agree" checked="checked" value="1">是</label>
                                            <label><input type="radio" name="agree" value="0">否</label>
                                        </td>
                                        <td class="hide" id="reason">
                                            <textarea placeholder="请填写未通过的原因... ..." id="reasonTxt"></textarea>
                                        </td>
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
<script src="${ctx}/static/js/staff/check_staff_apply.js"></script>

</body>
</html>