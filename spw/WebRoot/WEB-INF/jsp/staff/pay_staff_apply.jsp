<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@include file="../public/public_head.jsp" %>
    <title>民盛业务人员申请</title>
    <link rel="stylesheet" href="${ctx}/static/css/public/add.css" />
    <link rel="stylesheet" href="${ctx}/static/css/staff/check_staff_apply.css" />
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
                    <li>业务人员申请</li>
                </ul>
            </div>
            <div class="body">
                <div class="theme_lay">
                    <div class="theme">
                        <label class="main_theme">业务人员申请</label>
                        <label class="sub_theme">（选择是否通过审核，并填上审核意见）</label>
                    </div>
                    <div class="btns">
                        <button type="button" class="btn_blue goback"><i class="iconfont icon-quxiao"></i> 返回</button>
                        <button type="button" class="btn_green" id="add_pro_btn" ><i class="iconfont icon-zhengque"></i> 支付</button>
                    </div>
                </div>
                <div class="tab_lay">
                    <div class="tab_bodies">
                        <div class="tab_body">
                            <table class="basic">
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
                <div class="tab_lay">
                    <ul class="tab_controller">
                        <li class="active"><i class="iconfont icon-idcart"></i> 其他信息</li>
                    </ul>
                    <div class="tab_bodies">
                        <div class="tab_body">
                            <table class="others">
                                <tbody>
                                    <tr>
                                        <td>
                                            <img class="info_pic" src="${entity.bankcardImg}">
                                             
                                            <p>银行卡正面</p>
                                        </td>
                                        <td>
                                            <img class="info_pic" src="${entity.signImg}">
                                             
                                            <p>本人签字</p>
                                        </td>
                                        <td>
                                            <img class="info_pic" src="${entity.contractImg}">
                                             
                                            <p>本人手持合同</p>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="tab_lay">
                    <div class="tab_bodies">
                        <div class="tab_body">
                            <table class="result">
                                <tbody>
                                    <tr>
                                        <td id="bank">
                                            <p>开户银行：<input type="text" id="bankCardType"></p>
                                            <p>银行卡号：<input type="text" id="bankCardNo"></p>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>  
            </div>
        </div>
    </div>
<%@include file="../public/public_script.jsp" %>
<script src="${ctx}/static/js/public/public_add.js"></script>
<script src="${ctx}/static/js/staff/check_staff_apply.js"></script>

</body>
</html>