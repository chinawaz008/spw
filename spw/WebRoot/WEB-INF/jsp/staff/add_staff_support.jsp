<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@include file="../public/public_head3.jsp" %>
    <title>民盛新增后援人员</title>
    <link rel="stylesheet" href="${ctx}/static/css/public/add3.css" />
    <link rel="stylesheet" href="${ctx}/static/css/public/sub_add.css" />
    <link rel="stylesheet" href="${ctx}/static/css/staff/add_staff_support.css" />
</head>
<body>
    <div class="btns">
        <button type="button" id="add_support_submit_btn" data-i="${staff.id}">提交</button>
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
                            <td><input type="text" class="required" data-c="姓名" id= "username" name="name" onblur="checkSupportName()"></td>
                            <td class="item_name">入职时间：</td>
                            <td><input type="text" class="input_m ms_datepicker required" data-c="入职时间" name="entryDate"></td>
                            <td class="item_name">
                                上传照片
                            </td>
                            <td rowspan="6" class="choose_pic_td">
                                <div class="choose_pic">
                                    <img class="ms_feedback" src="${ctx}/static/img/staff/1.png">
                                    <input type="hidden" name="photo" value="">
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
                                <select name="sex" id="sex_select">
                                    <option value="0">男</option>
                                    <option value="1">女</option>
                                </select>
                            </td>
                            <td class="item_name">婚姻状况：</td>
                            <td>
                                <select name="maritalStatus">
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
                                <select name="politicalStatus">
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
                            <td><input type="text" class="input_s" name="nation"></td>
                        </tr>
                        <tr>
                            <td class="item_name">身份证号码：</td>
                            <td><input type="text" class="required formated" id="id_card" data-c="身份证号码" name="idCard"></td>
                            <td class="item_name">出生日期：</td>
                            <td><input type="text" class="input_m ms_datepicker required " id="birth_day" data-c="出生日期" name="birthDate"></td>
                        </tr>
                        <tr>
                            <td class="item_name">健康状况：</td>
                            <td>
                                <select name="physicalCondition">
                                    <option value="0">健康</option>
                                    <option value="1">一般</option>
                                    <option value="2">较差</option>
                                </select>
                            </td>
                            <td class="item_name">（备注：</td>
                            <td><input type="text" name="physicalRemark">）</td>
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
                                <select class="s_province" name="domicilePlaceProvince">
                                    <option value="">请选择省</option>
                                    <c:forEach items="${plist}" var="itme" varStatus="st">
                                         <option value="${itme.provinceNo}">${itme.provinceName}</option>
                                    </c:forEach>
                                </select>
                                <select class="s_region" name="domicilePlaceRegion">
                                    <option value="">请选择市</option>
                                </select>
                                <select class="s_county" name="domicilePlaceCounty">
                                    <option value="">请选择区县</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td colspan="3">
                                <input type="text" class="required long" data-c="户籍地址" name="domicilePlaceDetail">
                            </td>
                        </tr>
                        <tr>
                            <td class="item_name">家庭住址：</td>
                            <td colspan="3">
                               <select class="s_province" id="provinceId" name="provinceId">
                                    <option value="">请选择省</option>
                                    <c:forEach items="${plist}" var="itme" varStatus="st">
                                         <option value="${itme.provinceNo}">${itme.provinceName}</option>
                                    </c:forEach>
                                </select>
                                <select class="s_region" name="regionId">
                                    <option value="">请选择市</option>
                                </select>
                                <select class="s_county" name="countyId">
                                    <option value="">请选择区县</option>
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
                            <td><input type="text" class="required formated" data-c="手机号码" name="phoneNum"></td>
                            <td class="item_name">私人手机号：</td>
                            <td><input type="text" class="required formated" data-c="手机号码" name="personalPhone"></td>
                            <td class="item_name">固定电话：</td>
                            <td><input type="text" name="telephone"></td>
                        </tr>
                        
                        <tr>
                            <td class="item_name">毕业学校：</td>
                            <td><input type="text" name="graduateInstitutions"></td>
                            <td class="item_name">专业：</td>
                            <td><input type="text" name="major"></td>
                             <td class="item_name">学历：</td>
                            <td>
                                <select name="education">
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
                            <td><input type="text" class="required formated" name="bankCardNum" id="bank_card_num"></td>
                            <td class="item_name">开户银行：</td>
                            <td><input type="text" class="required" name="bankName" id="bank_name"></td>
                        </tr>
                        <tr>
                            <td class="item_name">合同类型：</td>
                            <td><select name="contractType" id="contractType">
                                    <option value="0">代理合同</option>
                                    <option value="1" selected="selected">劳务合同</option>
                                 </select>
                            </td>
                            <td class="item_name">合同编号：</td>
                            <td><input type="text" readonly="readonly" value="${contractNum }" name="contractNum"  id="contractNum">
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
                                <select id="insurance_industry" name="insurancePeriod">
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
                            <td colspan="3"><input type="text" class="input_m ms_datepicker" name="workBeginDate1"> — <input type="text" class="input_m ms_datepicker" name="workEndDate1"></td>
                            <td class="item_name">所属行业</td>
                            <td><input type="text" name="industryInvolved1"></td>
                        </tr>
                        <tr>
                            
                            <td class="item_name">工作单位</td>
                            <td><input type="text" name="workUnit1"></td>
                            <td class="item_name">职务</td>
                            <td><input type="text" name="workPosition1"></td>
                            <td class="item_name"><span class="check_for_the_same">属于同业</span></td>
                            <td>
                                <p class="check_for_the_same">
                                <!-- <label>
                                    属于同业  -->
                                    <input type="checkbox" class="belong_to_same">
                                    <input type="hidden" class="isCommonJobInput" name="isCommonJob1" value="0" ></input>
                                <!-- </label> -->
                                </p>
                            </td>
                        </tr>
                        
                        <tr>
                            <td class="item_name">起止年月</td>
                            <td colspan="3"><input type="text" class="input_m ms_datepicker" name="workBeginDate2"> — <input type="text" class="input_m ms_datepicker" name="workEndDate2"></td>
                            <td class="item_name">所属行业</td>
                            <td><input type="text" name="industryInvolved2"></td>
                        </tr>
                        <tr>
                            <td class="item_name">工作单位</td>
                            <td><input type="text" name="workUnit2"></td>
                            <td class="item_name">职务</td>
                            <td><input type="text" name="workPosition2" ></td>
                            <td class="item_name">
                                <span class="check_for_the_same">属于同业</span>
                            </td>
                            <td>
                                <p class="check_for_the_same">
                                    <!-- <label>
                                        属于同业 -->
                                        <input type="checkbox" class="belong_to_same">
                                        <input type="hidden" class="isCommonJobInput" name="isCommonJob2" value="0">
                                    <!-- </label> -->
                                </p>
                            </td>
                        </tr>
                        
                        <tr>
                            <td class="item_name">起止年月</td>
                            <td colspan="3"><input type="text" class="input_m ms_datepicker" name="workBeginDate3"> — <input type="text" class="input_m ms_datepicker" name="workEndDate3"></td>
                            <td class="item_name">所属行业</td>
                            <td><input type="text" name="industryInvolved3"></td>
                        </tr>
                        <tr>
                            <td class="item_name">工作单位</td>
                            <td><input type="text" name="workUnit3"></td>
                            <td class="item_name">职务</td>
                            <td><input type="text" name="workPosition3"></td>
                            <td class="item_name">
                                <span class="check_for_the_same">属于同业</span>
                            </td>
                            <td>
                                <p class="check_for_the_same">
                                    <!-- <label>
                                        属于同业  -->
                                        <input type="checkbox" class="belong_to_same">
                                        <input type="hidden" class="isCommonJobInput" name="isCommonJob3" value="0"></input>
                                    <!-- </label> -->
                                </p>
                            </td>
                        </tr>
                        
                        <tr class="last_the_same">
                            <td colspan="4">最近同业单位</td>
                        </tr>
                        
                        <tr class="last_the_same">
                            <td class="item_name">起止年月</td>
                            <td colspan="3"><input type="text" class="input_m ms_datepicker" name="workBeginDate4"> — <input type="text" class="input_m ms_datepicker" name="workEndDate4"></td>
                        </tr>
                        <tr class="last_the_same">
                            <td class="item_name">工作单位</td>
                            <td><input type="text" name="workUnit4"></td>
                            <td class="item_name">职务</td>
                            <td><input type="text" name="workPosition4"><input type="hidden" class="isCommonJobInput" name="isCommonJob4" value="1"></td>
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
                                <select class="select_m" id="company_type" name="companyType">
                                    <option value="1" selected="selected">总公司</option>
                                    <option value="2">分公司</option>
                                </select>
                            </td>
                        </tr>
                        <tr class="hide" id="position_ready">
                            <td class="item_name">已选岗位：</td>
                            <td id="position_ready_td"></td>
                        </tr>
                        <tr class="select_position_tr">
                            <td class="item_name">部门：</td>
                            <td id="section_tr">
                                <!-- <select class="select_m hide" data-c="条线" id="line_type" name="lineID"></select>
                                <select class="select_m hide" data-c="分公司名称" id="sub_company" name="subComID"></select> -->
                            </td>
                        </tr>
                        <tr class="select_position_tr">
                            <td class="item_name">岗位：</td>
                            <td>
                                <select id="position_select" class="select_m" class="required">
                                    <option value="">请选择...</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                        <td class="item_name">
                                <button type="button" class="btn_orange hide" id="add_gangwei">岗位 + </button>
                            </td>
                        </tr>
                        <tr>
	                        <td class="item_name">前线职级</td>
	                            <td class="has_fenbu">
                                <select id="ownFenbu"  class="select_m"  onchange="getDirectPart()">
                                    <option value="0">无</option>
                                    <option value="1">有</option>
                                </select>
                            </td> 
                        </tr>
                        <tr id="qudao" style="display: none;">
                            <td class="item_name"><span class="red">*</span> 渠道</td>
                            <td>
                                <select id="line_type" class="select_m required" name="lineType">
                                    <option value="">请选择条线部门</option>
                                    <c:forEach items="${lineType}" var="itme" varStatus="st">
                                         <option value="${itme.id}">${itme.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td class="item_name"><span class="red">*</span> 省级机构</td>
                            <td>
                                <select id="branch_company_id" class="select_m required" name="branchCompanyId">
                                    <option value="">请选择省级机构</option>
                                </select>
                            </td>
                        </tr>
                        
                        <tr id="duxunqu" style="display: none;">
                            <td class="item_name"><span class="red">*</span> 督训区</td>
                            <td>
                                <select id="county_franchisees_id" class="select_m required" name="countyFranchiseesId">
                                    <option value="">请选择督训区</option>
                                </select>
                            </td>
                            <td class="item_name"><span class="red">*</span>分部  </td>
                            <td>
                                <select id="fenbu" class="select_m" name="havePartId">
                                    <option value="">请选择分部</option>
                                </select>
                            </td>
                        </tr>
                        
                        <tr id="zhiji" style="display: none;">
                            <td class="item_name"><span class="red">*</span> 职级</td>
                            <td>
                                <select id="position" class="select_m required" name="position">
                                    <option value="">请选择职级</option>
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
                                <input type="text" class="query_id_card required" name="basePay" placeholder="输入基本工资">
                            </td>
                            <td class="item_name">社保 + 公积金：</td>
                            <td>
                                <input type="text" class="query_id_card required" name="socialPay" placeholder="输入社保 +公积金">
                            </td>
                            <td class="item_name">浮动工资：</td>
                            <td>
                                <input type="text" class="query_id_card required" name="floatPay" placeholder="输入浮动工资">
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>
<%@include file="../public/public_script3.jsp" %>
<form id="submit_form" class="hide" method="post" action="${ctx}/upload/images" target="exec_target" enctype="multipart/form-data">
    <input type="file" id="replace_input" name="files">
</form>
<iframe id="exec_target" class="hide" name="exec_target"></iframe>
<script src="${ctx}/static/js/public/public_add3.js"></script>
<script src="${ctx}/static/js/staff/add_staff.js"></script>
<script src="${ctx}/static/js/staff/add_staff_support.js"></script>
<script src="${ctx}/static/js/plugins/msdatepicker/msdatepicker.js"></script>

</body>
</html>