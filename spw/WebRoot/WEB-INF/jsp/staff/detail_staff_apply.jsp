<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("ctx", request.getContextPath());%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${ctx}/static/css/public/add3.css" />
    <link rel="stylesheet" href="${ctx}/static/css/staff/check_staff_apply.css" />
</head>
<body data-message="${message}">
            <div class="body">
                <div class="tab_lay">
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
                                        <td>代扣银行卡号：</td>
                                        <td><c:if test="${entity.bankCard==null || entity.bankCard==''}">无</c:if>${entity.bankCard}</td>
                                        <td>佣金发放卡号：</td>
                                        <td><c:if test="${entity.staffBankNum==null || entity.staffBankNum==''}">无</c:if>${entity.staffBankNum}</td>
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
				<div class="tab_lay">
                    <ul class="tab_controller">
                        <li class="active"><i class="iconfont icon-idcart"></i> 银行卡信息</li>
                    </ul>
                    <div class="tab_bodies">
                        <div class="tab_body">
                            <table class="others">
                                <tbody>
                                    <tr>
                                    	<c:if test="${entity.salaryBankImg!=null && entity.salaryBankImg!=''}">
                                     	<td>
                                            <img class="info_pic" src="${entity.salaryBankImg}">
                                             
                                            <p>佣金发放卡</p>
                                        </td>
                                        </c:if>
                                        <c:if test="${entity.depositTicket!=null && entity.depositTicket!=''}">
                                        	<td>
                                            <img class="info_pic" src="${entity.depositTicket}">
                                            <p>押金小票</p>
                                       		 </td>
                                        </c:if>
                                        <c:if test="${entity.bankcardImg!=null && entity.bankcardImg!=''}">
                                        	<td>
                                            <img class="info_pic" src="${entity.bankcardImg}">
                                            <p>代扣银行卡</p>
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
                                        <c:if test="${entity.receiptImg!=null && entity.receiptImg!=''}">
                                        <td>
                                            <img class="info_pic" src="${entity.receiptImg}">
                                            <p>收据</p>
                                        </td>
                                        </c:if>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                </div>
<%@include file="../public/public_script3.jsp" %>
<script src="${ctx}/static/js/public/public_add.js"></script>
<script src="${ctx}/static/js/staff/check_staff_apply.js"></script>
</body>
</html>