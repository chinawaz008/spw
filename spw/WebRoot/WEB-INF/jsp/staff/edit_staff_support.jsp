<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@include file="../public/public_head3.jsp" %>
    <title>民盛编辑后援人员</title>
    <link rel="stylesheet" href="${ctx}/static/css/public/add3.css" />
    <link rel="stylesheet" href="${ctx}/static/css/public/sub_add.css" />
    <link rel="stylesheet" href="${ctx}/static/css/staff/add_staff_support.css" />
</head>
<body>
    <div class="btns">
        <button type="button" id="edit_support_submit_btn" data-i="${staff.id}">提交</button>
    </div>
    <ul class="tab_controller">
        <li class="active">基本信息</li>
        <li>工作经历</li>
        <li>组织关系</li>
        <li>薪资信息</li>
    </ul>
    <div class="tab_bodies">                
        <div class="tab_body">
            <form id="staff_form">
                <table>
                    <tbody>
                        <tr>
                            <td class="item_name">姓名：</td>
                            <td><input type="text" class="required" data-c="姓名" id= "username" name="name" value="${staff.name}" onblur="checkSupportName()"></td>
                            <td class="item_name">入职时间：</td>
                            <td><input type="text" class="input_m ms_datepicker required" data-c="入职时间" name="entryDate" value="${staff.entryDate}"></td>
                            <td class="item_name">
                                上传照片
                            </td>
                            <td rowspan="6" class="choose_pic_td">
                                <div class="choose_pic">
                                    <img class="ms_feedback" 
                                        <c:if test="${staff.photo!='' && staff.photo!=null}">
                                            src="${staff.photo}"
                                        </c:if> 
                                        <c:if test="${staff.photo=='' || staff.photo==null}">
                                            src="${ctx}/static/img/staff/1.png"
                                        </c:if> 
                                    >
                                    <input type="hidden" name="photo"  value="${staff.photo}">
                                </div>
                                <p>
                                    图片比例 2:3<br>
                                    <button type="button" class="ms_upload_file_btn btn_blue">浏览</button>
                                </p>
                                
                            </td>
                        </tr>
                        <tr>
                            <td class="item_name">性别：</td>
                            <td>
                                <select name="sex" id="sex_select" data-v="${staff.sex}">
                                    <option value="0">男</option>
                                    <option value="1">女</option>
                                </select>
                            </td>
                            <td class="item_name">婚姻状况：</td>
                            <td>
                                <select name="maritalStatus" data-v="${staff.maritalStatus}">
                                    <option value="0">未婚</option>
                                    <option value="1">已婚</option>
                                    <option value="2">离异</option>
                                    <option value="3">丧偶</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td class="item_name">政治面貌：</td>
                            <td>
                                <select name="politicalStatus" data-v="${staff.politicalStatus}">
                                    <option value="0">普通公民</option>
                                    <option value="1">中共党员</option>
                                    <option value="2">中共预备党员</option>
                                    <option value="3">共青团员</option>
                                    <option value="4">民盟盟员</option>
                                    <option value="5">民建会员</option>
                                    <option value="6">民进会员</option>
                                    <option value="7">农工党党员</option>
                                    <option value="8">致公党党员</option>
                                    <option value="9">九三学社社员</option>
                                    <option value="10">台盟盟员</option>
                                    <option value="11">民革党党员</option>
                                    <option value="12">无党派人士</option>
                                </select>
                            </td>
                            <td class="item_name">民族：</td>
                            <td><input type="text" class="input_s" name="nation" value="${staff.nation}"></td>
                        </tr>
                        <tr>
                            <td class="item_name">身份证号码：</td>
                            <td><input type="text" class="required formated" id="id_card" data-c="身份证号码" name="idCard" value="${staff.idCard}"></td>
                            <td class="item_name">出生日期：</td>
                            <td><input type="text" class="input_m ms_datepicker required " id="birth_day" data-c="出生日期" name="birthDate" value="${staff.birthDate}"></td>
                        </tr>
                        <tr>
                            <td class="item_name">健康状况：</td>
                            <td>
                                <select name="physicalCondition" data-v="${staff.physicalCondition}">
                                    <option value="0">健康</option>
                                    <option value="1">一般</option>
                                    <option value="2">较差</option>
                                </select>
                            </td>
                            <td class="item_name">（备注：</td>
                            <td><input type="text" name="physicalRemark" value="${staff.physicalRemark}">）</td>
                        </tr>
                        
                        <tr>
                            <td class="item_name">QQ号码：</td>
                            <td><input type="text" name="qq"></td>
                            <td class="item_name">微信号：</td>
                            <td><input type="text" class="input_m" name="wechatId"></td>
                        </tr>
                        <tr>
                            <td class="item_name">户口所在地：</td>
                            <td colspan="3">
                                <select class="s_province" name="domicilePlaceProvince" data-v="${staff.domicilePlaceProvince}">
                                    <option value="">请选择省</option>
                                    <c:forEach items="${plist}" var="itme" varStatus="st">
                                         <option value="${itme.provinceNo}">${itme.provinceName}</option>
                                    </c:forEach>
                                </select>
                                <select class="s_region" name="domicilePlaceRegion" data-v="${staff.domicilePlaceRegion}">
                                    <option value="">请选择市</option>
                                    <c:forEach items="${relist}" var="itme" varStatus="st">
                                         <option value="${itme.regionNo}">${itme.regionName}</option>
                                    </c:forEach>
                                </select>
                                <select class="s_county" name="domicilePlaceCounty" data-v="${staff.domicilePlaceCounty}">
                                    <option value="">请选择区县</option>
                                    <c:forEach items="${clist}" var="itme" varStatus="st">
                                         <option value="${itme.countyNo}">${itme.countyName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td colspan="3">
                                <input type="text" class="required long" data-c="户籍地址" name="domicilePlaceDetail" value="${staff.domicilePlaceDetail}">
                            </td>
                        </tr>
                        <tr>
                            <td class="item_name">家庭住址：</td>
                            <td colspan="3">
                               <select class="s_province" id="provinceId" name="provinceId" data-v="${staff.provinceId}">
                                    <option value="">请选择省</option>
                                    <c:forEach items="${p2list}" var="itme" varStatus="st">
                                         <option value="${itme.provinceNo}">${itme.provinceName}</option>
                                    </c:forEach>
                                </select>
                                <select class="s_region" name="regionId" data-v="${staff.regionId}">
                                    <option value="">请选择市</option>
                                    <c:forEach items="${re2list}" var="itme" varStatus="st">
                                         <option value="${itme.regionNo}">${itme.regionName}</option>
                                    </c:forEach>
                                </select>
                                <select class="s_county" name="countyId" data-v="${staff.countyId}">
                                    <option value="">请选择区县</option>
                                    <c:forEach items="${c2list}" var="itme" varStatus="st">
                                         <option value="${itme.countyNo}">${itme.countyName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td colspan="3">
                                <input type="text"  name="address" class="long">
                            </td>
                        </tr>
                        <tr>
                            <td class="item_name">工作手机号：</td>
                            <td><input type="text" class="required formated" data-c="手机号码" name="phoneNum"  value="${staff.phoneNum}"></td>
                            <td class="item_name">私人手机号：</td>
                            <td><input type="text" class="required formated" data-c="手机号码" name="personalPhone" value="${staff.personalPhone}"></td>
                            <td class="item_name">固定电话：</td>
                            <td><input type="text" name="telephone" value="${staff.telephone}"></td>
                        </tr>
                        
                        <tr>
                            <td class="item_name">毕业学校：</td>
                            <td><input type="text" name="graduateInstitutions" value="${staff.graduateInstitutions}"></td>
                            <td class="item_name">专业：</td>
                            <td><input type="text" name="major" value="${staff.major}"></td>
                            <td class="item_name">学历：</td>
                            <td>
                                <select name="education" data-v="${staff.education}">
                                    <option value="0">高中</option>
                                    <option value="1">专科</option>
                                    <option value="2" selected="selected">本科</option>
                                    <option value="3">硕士</option>
                                    <option value="4">博士</option>
                                    <option value="5">其他</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td class="item_name">银行卡号：</td>
                            <td><input type="text" class="required formated" name="bankCardNum" id="bank_card_num" value="${staff.bankCardNum}"></td>
                            <td class="item_name">开户银行：</td>
                            <td><input type="text" class="required" name="bankName" id="bank_name" value="${staff.bankName}"></td>
                        </tr>
                        <tr>
                            <td class="item_name">合同类型：</td>
                            <td><select name="contractType" id="contractType" disabled="disabled" data-v="${staff.contractType}"> 
                                    <option value="0">代理合同</option>
                                    <option value="1" selected="selected">劳务合同</option>
                                 </select>
                            </td>
                            <td class="item_name">合同编号：</td>
                            <td><input type="text" readonly="readonly" value="${staff.contractNum }" name="contractNum"  id="contractNum">
                            <input type="hidden"  value="${contractNum2 }" id="contractNum0" />
                            <input type="hidden"  value="${contractNum }" id="contractNum1" />
                            </td>
                        </tr>
                        
                    </tbody>
                </table>
            </form>
        </div>
        <div class="tab_body hide">
            <form id="work_exp_form">
                <table>
                    <tbody>
                        <tr>
                            <td class="item_name">保险行业从业时间</td>
                            <td>
                                <select id="insurance_industry" name="insurancePeriod" data-v="${insurancePeriod}">
                                    <option value="0">无</option>
                                    <option value="1">1年以下</option>
                                    <option value="2">1-3年</option>
                                    <option value="3">4-5年</option>
                                    <option value="4">5-10年</option>
                                    <option value="5">10-15年</option>
                                    <option value="6">15年以上</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="0">
                                最近三次工作经历
                            </td>
                        </tr>
                        <tr>
                            <td class="item_name">起止年月</td>
                            <td colspan="3"><input type="text" class="input_m ms_datepicker" name="workBeginDate1" value="${workExp[0].workBeginDate}"> — <input type="text" class="input_m ms_datepicker" name="workEndDate1" value="${workExp[0].workEndDate}"></td>
                            <td class="item_name">所属行业</td>
                            <td><input type="text" name="industryInvolved1"></td>
                        </tr>
                        <tr>
                            
                            <td class="item_name">工作单位</td>
                            <td><input type="text" name="workUnit1" value="${workExp[0].workUnit}"></td>
                            <td class="item_name">职务</td>
                            <td><input type="text" name="workPosition1" value="${workExp[0].workPosition}"></td>
                            <td class="item_name"><span class="check_for_the_same">属于同业</span></td>
                            <td>
                                <p class="check_for_the_same">
                                <!-- <label>
                                    属于同业  -->
                                    <input type="checkbox" class="belong_to_same" <c:if test="${workExp[0].isCommonJob==1}">checked=="checked"</c:if> >
                                    <input type="hidden" class="isCommonJobInput" name="isCommonJob1" value="0" ></input>
                                    <input type="hidden" name="wid1" value="<c:if test='${empty workExp[0].id}'>0</c:if>${workExp[0].id}">
                                <!-- </label> -->
                                </p>
                            </td>
                        </tr>
                        
                        <tr>
                            <td class="item_name">起止年月</td>
                            <td colspan="3"><input type="text" class="input_m ms_datepicker" name="workBeginDate2" value="${workExp[1].workBeginDate}"> — <input type="text" class="input_m ms_datepicker" name="workEndDate2" value="${workExp[1].workEndDate}"></td>
                            <td class="item_name">所属行业</td>
                            <td><input type="text" name="industryInvolved2" value="${workExp[1].industryInvolved}"></td>
                        </tr>
                        <tr>
                            <td class="item_name">工作单位</td>
                            <td><input type="text" name="workUnit2" value="${workExp[1].workUnit}"></td>
                            <td class="item_name">职务</td>
                            <td><input type="text" name="workPosition2" value="${workExp[1].workPosition}"></td>
                            <td class="item_name">
                                <span class="check_for_the_same">属于同业</span>
                            </td>
                            <td>
                                <p class="check_for_the_same">
                                    <!-- <label>
                                        属于同业 -->
                                    <input type="checkbox" class="belong_to_same" <c:if test="${workExp[1].isCommonJob==1}">checked=="checked"</c:if>>
                                    <input type="hidden" class="isCommonJobInput"  name="isCommonJob2" value="0" ></input>
                                    <input type="hidden" name="wid2" value="<c:if test='${empty workExp[1].id}'>0</c:if>${workExp[1].id}">
                                    <!-- </label> -->
                                </p>
                            </td>
                        </tr>
                        
                        <tr>
                            <td class="item_name">起止年月</td>
                            <td colspan="3"><input type="text" class="input_m ms_datepicker" name="workBeginDate3" value="${workExp[2].workBeginDate}"> — <input type="text" class="input_m ms_datepicker" name="workEndDate3" value="${workExp[2].workEndDate}"></td>
                            <td class="item_name">所属行业</td>
                            <td><input type="text" name="industryInvolved3" value="${workExp[2].industryInvolved}"></td>
                        </tr>
                        <tr>
                            <td class="item_name">工作单位</td>
                            <td><input type="text" name="workUnit3" value="${workExp[2].workUnit}"></td>
                            <td class="item_name">职务</td>
                            <td><input type="text" name="workPosition3" value="${workExp[2].workPosition}"></td>
                            <td class="item_name">
                                <span class="check_for_the_same">属于同业</span>
                            </td>
                            <td>
                                <p class="check_for_the_same">
                                    <!-- <label>
                                        属于同业  -->
                                        <input type="checkbox" class="belong_to_same" <c:if test="${workExp[2].isCommonJob==1}">checked=="checked"</c:if> >
                                        <input type="hidden" class="isCommonJobInput" name="isCommonJob3" value="0" >
                                        <input type="hidden" name="wid3" value="<c:if test='${empty workExp[2].id}'>0</c:if>${workExp[2].id}">
                                    <!-- </label> -->
                                </p>
                            </td>
                        </tr>
                        
                        <tr class="last_the_same">
                            <td colspan="4">最近同业单位</td>
                        </tr>
                        
                        <tr class="last_the_same">
                            <td class="item_name">起止年月</td>
                            <td colspan="3"><input type="text" class="input_m ms_datepicker" name="workBeginDate4" value="${workExp[3].workBeginDate}"> — <input type="text" class="input_m ms_datepicker" name="workEndDate4"></td>
                        </tr>
                        <tr class="last_the_same">
                            <td class="item_name">工作单位</td>
                            <td><input type="text" name="workUnit4" value="${workExp[3].workUnit}"></td>
                            <td class="item_name">职务</td>
                            <td>
                                <input type="text" name="workPosition4" value="${workExp[3].workPosition}">
                                <input type="hidden" class="isCommonJobInput" name="isCommonJob4" value="1">
                                <input type="hidden" name="wid4" value="<c:if test='${empty workExp[3].id}'>0</c:if>${workExp[3].id}">
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
        <div class="tab_body hide">
            <form id="organization_form">
                <table>
                    <tbody class="organization_table">
                        <tr>
                            <td class="item_name">公司类别：</td>
                            <td>
                                <select class="select_m" id="company_type" name="companyType" data-v="${staff.branchCompanyName}">
                                    <option value="1" selected="selected">总公司</option>
                                    <option value="2">分公司</option>
                                </select>
                            </td>
                        </tr>
                        
                        <tr class="<c:if test="${empty bumenList}">hide</c:if>" id="position_ready">
                            <td class="item_name">已选岗位：</td>
                            <td id="position_ready_td">
                                <c:forEach items="${bumenList}" var="item" varStatus="st">
                                    <div data-id="${item.position}" data-line="${item.lineType}" data-company="${item.branchCompanyId}" data-dxq="${item.countyFranchiseesId}" class="selected_position">
                                         ${item.companyName} ${item.positionName}<i class="iconfont icon-cuowu"></i>
                                    </div>
                                </c:forEach>
                            </td>
                        </tr>
                        
                        <tr class="select_position_tr hide">
                            <td class="item_name">部门：</td>
                            <td id="section_tr">
                                <!-- <select class="select_m hide" data-c="条线" id="line_type" name="lineID"></select>
                                <select class="select_m hide" data-c="分公司名称" id="sub_company" name="subComID"></select> -->
                            </td>
                        </tr>
                        <tr class="select_position_tr hide">
                            <td class="item_name">岗位：</td>
                            <td>
                                <select id="position_select" class="select_m" class="required">
                                    <option value="">请选择...</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                        <td class="item_name">
                                <button type="button" class="btn_orange" id="add_gangwei">岗位 + </button>
                            </td>
                        </tr>
                        <tr>
	                        <td class="item_name">前线职级</td>
	                            <td>
                                <select id="ownFenbu"  class="select_m"  onchange="getDirectPart()">
                                    <option value="0">无</option>
                                    <option value="1">有</option>
                                </select>
                            </td> 
                        </tr>
                        <tr id="qudao" style="display: none;">
                            <td class="item_name"><span class="red">*</span> 渠道</td>
                            <td>
                                <select id="line_type" class="select_m required" name="lineType" data-v="${staff.lineType}">
                                    <option value="">请选择条线部门</option>
                                    <c:forEach items="${lineType}" var="itme" varStatus="st">
                                         <option value="${itme.id}">${itme.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td class="item_name"><span class="red">*</span> 省级机构</td>
                            <td>
                                <select id="branch_company_id" class="select_m required" data-v="${staff.branchCompanyId}" name="branchCompanyId">
                                    <option value="">请选择省级机构</option>
                                    <c:forEach items="${comList}" var="itme" varStatus="st">
                                        <option value="${itme.id}">${itme.compName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        
                        <tr id="duxunqu" style="display: none;">
                            <td class="item_name"><span class="red">*</span> 督训区</td>
                            <td>
                                <select id="county_franchisees_id" class="select_m required" data-v="${staff.countyFranchiseesId}" name="countyFranchiseesId">
                                    <option value="">请选择督训区</option>
                                    <c:forEach items="${dxlist}" var="itme" varStatus="st">
                                        <option value="${itme.id}">${itme.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td class="item_name"><span class="red">*</span>分部  </td>
                            <td>
                                <select id="fenbu" class="select_m"  data-v="${staff.havePartId}" name="havePartId">
                                    <option value="">请选择分部</option>
                                    <c:forEach items="${fblist}" var="itme" varStatus="st">
                                        <option value="${itme.id}">${itme.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr id="zhiji" style="display: none;">
                            <td class="item_name"><span class="red">*</span> 职级</td>
                            <td>
                            	<select id="position" class="select_m required" name="position" data-v="${staff.partIdPosition}">
                                    <option value="">请选择职级</option>
                                    <c:forEach items="${zlist}" var="itme" varStatus="st">
                                        <option value="${itme.id}">${itme.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>

        </div>
        <div class="tab_body hide">
            <form id="salary_form">
                <table>
                    <tbody>
                        <tr>
                            <td class="item_name">基本工资：</td>
                            <td>
                                <input type="text" class="query_id_card required" name="basePay" placeholder="输入基本工资" value="${staff.basePay}">
                            </td>
                            <td class="item_name">社保 + 公积金：</td>
                            <td>
                                <input type="text" class="query_id_card required" name="socialPay" placeholder="输入社保 +公积金" value="${staff.socialPay}" >
                            </td>
                            <td class="item_name">浮动工资：</td>
                            <td>
                                <input type="text" class="query_id_card required" name="floatPay" placeholder="输入浮动工资" value="${staff.floatPay}">
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

<input type="hidden" id="lists" value="${rlist}">

<%@include file="../public/public_script3.jsp" %>
<form id="submit_form" class="hide" method="post" action="${ctx}/upload/images" target="exec_target" enctype="multipart/form-data">
    <input type="file" id="replace_input" class="hide" name="files">
</form>
<iframe id="exec_target" class="hide" name="exec_target"></iframe>
<script src="${ctx}/static/js/public/public_add3.js"></script>
<script src="${ctx}/static/js/staff/add_staff.js"></script>
<script src="${ctx}/static/js/staff/add_staff_support.js"></script>
<script src="${ctx}/static/js/staff/edit_staff.js"></script>
<script src="${ctx}/static/js/plugins/msdatepicker/msdatepicker.js"></script>

</body>
</html>