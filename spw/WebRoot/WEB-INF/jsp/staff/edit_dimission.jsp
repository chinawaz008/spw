<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@include file="../public/public_head.jsp" %>
    <title>民盛个代业务人员晋升</title>
    <link rel="stylesheet" href="${ctx}/static/css/public/add.css" />
    <link rel="stylesheet" href="${ctx}/static/css/staff/add_promotion.css" />
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
                    <li>个代人员离职</li>
                </ul>
            </div>
            <div class="body">
                <div class="theme_lay">
                    <div class="theme">
                        <label class="main_theme">个代业务人员离职——办理</label>
                        <label class="sub_theme">（在搜索框中输入工号或姓名，确定离职人员后，填写相关信息）</label>
                    </div>
                    <div class="btns">
                        <button type="button" class="btn_blue goback"><i class="iconfont icon-quxiao"></i> 返回</button>
                        <button type="button" class="btn_green" id="add_pro_btn" ><i class="iconfont icon-zhengque"></i> 提交</button>
                    </div>
                </div>
                <div class="tab_lay">
                    <ul class="tab_controller">
                        <li class="active"><i class="iconfont icon-gongzuojingliline"></i> 离职申请办理</li>
                    </ul>
                    <div class="tab_bodies">
                        
                        <div class="tab_body">
                            <form id="promotion_form">
                                <table style="width:70%">
                                    <tbody>
                                        <tr>
                                            <td class="item_name">姓名：</td>
                                            <td>
                                            	${entity.name}
                                                 <input type="hidden" value="${entity.id}" name="applyId"></input>
                                                 <input type="hidden" value="${departId+1}"  name="department"></input>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="item_name">职级：</td>
                                            <td>${entity.position }</td>
                                         </tr>
                                        <tr>
                                            <td class="item_name">离职申请时间：</td>
                                            <td>${entity.applyTime }</td>
                                        </tr>
                                        <tr>
                                            <td class="item_name">是否同意离职：</td>
                                            <td>
                                            	<select name="isAgree">
                                            		<option value="1">是</option>
                                            		<option value="0">否</option>
                                            	</select>
                                            </td>
                                        </tr>
                                        <tr class="tips_tr">
                                            <td class="item_name">备注：</td>
                                            <td>
                                                <textarea name="remark"></textarea>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </form>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
<%@include file="../public/public_script.jsp" %>
<script src="${ctx}/static/js/public/public_add.js"></script>
<script src="${ctx}/static/js/staff/add_dimission.js"></script>
<script src="${ctx}/static/js/plugins/msdatepicker/msdatepicker.js"></script>

</body>
</html>