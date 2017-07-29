<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@include file="../public/public_head3.jsp" %>
    <title>民盛新增业务人员</title>
    <link rel="stylesheet" href="${ctx}/static/css/public/add3.css" />
    <link rel="stylesheet" href="${ctx}/static/css/public/sub_add.css" />
</head>
<body>
    <div class="btns">
        <button type="button" id="add_submit_btn" data-i="${staff.id}">提交</button>
    </div>
    <ul class="tab_controller">
        <li class="active">常规信息</li>
        <li>基本信息</li>
        <li>工作经历</li>
        <li>人际关系</li>
        <li>资料信息</li>
    </ul>

    <div class="tab_bodies">                
        <div class="tab_body">
            <form id="organization_form">
                <table>
                    <tbody>
                        <tr>
                            <td colspan="6" class="orange">依次选择 【渠道】 &rarr; 【省级机构】 &rarr; 【督训区】<br />如果是督训区的职能岗位人员，请直接选择 【职级】；如果是分部业务人员，请点击 【分部+】 ，并选择对应分部。</td>
                        </tr>
                        <tr>
                            <td class="item_name"><span class="red">*</span> 姓名</td>
                            <td>
                                <input type="text" class="required" data-c="姓名" id= "username" onblur="checkName()" name="name">
                            </td>
                            <td class="item_name"><span class="red">*</span> 手机号码</td>
                            <td>
                                <input type="text" class="required formated" data-c="手机号码" name="phoneNum">
                            </td>
                            <td class="item_name"><span class="red">*</span> 身份证号码</td>
                            <td>
                                <input type="text" class="required formated" id="id_card" data-c="身份证号码" name="idCard">
                            </td>
                        </tr>
                        <tr>
                            <td class="item_name"><span class="red">*</span> 入职时间</td>
                            <td>
                                <input type="text" class="input_m ms_datepicker required" data-c="入职时间" name="entryDate" placeholder="例2013-01-02">
                            </td>

                        </tr>
                        <tr>
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
                                    <option value="">请选择所属省级机构</option>
                                </select>
                            </td>
                        </tr>
                        
                        <tr>
                            <td class="item_name"><span class="red">*</span> 督训区</td>
                            <td>
                                <select id="county_franchisees_id" class="select_m required" name="countyFranchiseesId">
                                    <option value="">请选择督训区</option>
                                </select>
                            </td>
                            <td class="item_name"><span class="red">*</span>分部  </td>
                            <td>
                                <select id="fenbu" class="select_m" name="storeId">
                                    <option value="">请选择分部</option>
                                </select>
                                <input type="hidden" name="orgType"  id="orgType"></input>
                                <button type="button" class="btn_red delete_fenbu" id="delete_fenbu">撤销</button>
                            </td>
                        </tr>
                        
                        <tr>
                            <td class="item_name"><span class="red">*</span> 职级</td>
                            <td>
                                <select id="position" class="select_m required" name="position">
                                    <option value="">请选择职级</option>
                                </select>
                            </td>
                           <!--  <td class="has_fenbu item_name">有无直属分部</td>
                            <td class="has_fenbu">
                                <select id="ownered_fenbu"  class="select_m" name="havePartId">
                                    <option value="">无</option>
                                </select>
                            </td> -->
                        </tr>
                        <tr class="hide" id="special_raining">
                            <td class="item_name">是否是专训</td>
                            <td>
                                <select name="isSpecialTraining" class="select_m" id="is_special_training">
                                    <option value="0">否</option>
                                    <option value="1">是</option>
                                </select>
                            </td>
                            <td class="item_name">是否是新筹</td>
                            <td>
                                <select  name="isNew" class="select_m" id="is_new">
                                    <option value="0">否</option>
                                    <option value="1">是</option>
                                </select>
                            </td>
                            <td></td>
                        </tr>

                        <tr class="hide" id="new_date">
                            <td class="item_name">筹备开始日期</td>
                            <td>
                                <input type="text" class="input_m ms_datepicker" name="newDateBegin" value=""> 
                            </td>
                            <td class="item_name">筹备结束日期</td>
                            <td><input type="text" class="input_m ms_datepicker" name="newDateEnd" value=""></td>
                            <td></td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>

        <div class="tab_body hide">
            <form id="staff_form">
                <table>
                    <tbody>
                        <tr>
                            <td class="item_name"><span class="red">*</span> 合同类型</td>
                            <td><select name="contractType" id="contractType">
                                    <option value="0" selected="selected">代理合同</option>
                                    <option value="1">劳务合同</option>
                                 </select>
                            </td>
                            <td class="item_name">合同编号</td>
                            <td>
                                <span id="contractSpan">${contractNum2 }</span>
                                <input type="hidden" value="${contractNum2 }" name="contractNum"  id="contractNum" data-v="${contractNum}" data-v2="${contractNum2}"/>
                                <!-- <input type="hidden"  value="${contractNum2}" id="contractNum0" />
                                <input type="hidden"  value="${contractNum}" id="contractNum1" /> -->
                            </td>
                            <td class="item_name">
                                上传照片
                            </td>
                            
                            <td rowspan="6" class="choose_pic_td">
                                <div class="choose_pic">
                                    <img class="ms_feedback" src="${ctx}/static/img/staff/1.png">
                                    <input type="hidden" name="photo" value="">
                                </div>
                                <p>图片比例 2:3
                                    <br>
                                    <button type="button" class="ms_upload_file_btn btn_blue">浏览</button>
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <td class="item_name"><span class="red">*</span> 银行卡号</td>
                            <td><input type="text" class="required formated" name="bankCardNum" id="bank_card_num"></td>
                            <td class="item_name"><span class="red">*</span> 开户银行</td>
                            <td><input type="text" class="required" name="bankName" id="bank_name"></td>
                        </tr>

                        <tr>
                            <td class="item_name">婚姻状况</td>
                            <td>
                                <select name="maritalStatus">
                                    <option value="0">未婚</option>
                                    <option value="1">已婚</option>
                                    <option value="2">离异</option>
                                    <option value="3">丧偶</option>
                                </select>
                            </td>
                            <td class="item_name">民族</td>
                            <td>
                                <input type="text" class="input_s" name="nation">
                            </td>
                        </tr>

        
                        <tr>
                            <td class="item_name">QQ号码</td>
                            <td><input type="text" name="qq"></td>
                            <td class="item_name">微信号</td>
                            <td><input type="text" class="input_m" name="wechatId"></td>
                        </tr>

                        <tr>
                            <td class="item_name">资格证号码</td>
                            <td><input type="text" name="generationCertification"></td>
                            <td class="item_name">政治面貌</td>
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
                        </tr>
                        <tr>
                            <td class="item_name">固定电话</td>
                            <td><input type="text" name="telephone"></td>
                            <td class="item_name">是否有车补</td>
                            <td><input type="checkbox" name="haveCar" value="是"></td>
                        </tr>

                        <tr>
                            <td class="item_name">健康状况</td>
                            <td colspan="3">
                                <select name="physicalCondition">
                                    <option value="0">健康</option>
                                    <option value="1">一般</option>
                                    <option value="2">较差</option>
                                </select>
                                （备注 <input type="text" name="physicalRemark">）
                            </td>
                        </tr>


                        <tr>
                            <td class="item_name">学历</td>
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
                            <td class="item_name">毕业学校</td>
                            <td><input type="text" name="graduateInstitutions"></td>
                            <td class="item_name">专业</td>
                            <td><input type="text" name="major"></td>
                        </tr>
                    
                        <tr>
                            <td class="item_name">户口所在地</td>
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
                                <input class="long" type="text" data-c="户籍地址" name="domicilePlaceDetail" placeholder="请输入详细地址">
                            </td>
                        </tr>
                        <tr>
                            <td class="item_name">家庭住址</td>
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
                                <input class="long" type="text"  name="address" placeholder="请输入详细地址">
                            </td>
                        </tr>
                        <tr>
                            <td class="item_name">打款时间</td>
                            <td><input type="text" class="ms_datepicker" name="payTime1"></td>
                            <td class="item_name">打款金额</td>
                            <td><input type="text"  name="payMoney1" class="formated" data-c="金额"></td>
                            <td class="item_name">打款账户</td>
                            <td>
                                <input type="text" name="account1" value="宋总账户">
                            </td>
                        </tr>
                    
                        <tr>
                            <td class="item_name">打款时间</td>
                            <td><input type="text" class="ms_datepicker" name="payTime2"></td>
                            <td class="item_name">打款金额</td>
                            <td><input type="text"  name="payMoney2" class="formated" data-c="金额"></td>
                            <td class="item_name">打款账户</td>
                            <td>
                                <input type="text" name="account2" value="宋总账户">
                            </td>
                        </tr>
                        
                        <tr>
                            <td class="item_name">打款时间</td>
                            <td><input type="text" class="ms_datepicker" name="payTime3"></td>
                            <td class="item_name">打款金额</td>
                            <td><input type="text"  name="payMoney3" class="formated" data-c="金额"></td>
                            <td class="item_name">打款账户</td>
                            <td>
                                <input type="text" name="account3" value="宋总账户">
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
            <form id="relationship_form">
                <table>
                    <tbody>
                        <tr>
                            <td class="item_name">推荐人</td>
                            <td>
                                <input type="text" name="referrerIdCardName" class="query_id_card" placeholder="输入身份证号查询">
                                <input type="hidden" name="referrerIdCard"  value="">
                            </td>
                            <td class="item_name">担保人</td>
                            <td>
                                <input type="text" class="query_id_card" placeholder="输入身份证号或姓名">
                                <input type="hidden" name="bondsman1" value="">
                            </td>
                            <td class="item_name">有无亲属在本公司</td>
                            <td>
                                <input type="text" class="query_id_card" placeholder="若有，请输入身份证号">
                                <input type="hidden" name="relativesWorking1" value="">
                            </td>
                        </tr>
                        
                        <tr>
                            <td></td>
                            <td></td>
                            <td class="item_name">担保人</td>
                            <td>
                                <input type="text" class="query_id_card" placeholder="输入身份证号或姓名">
                                <input type="hidden" name="bondsman2" value="">
                            </td>
                            <td class="item_name">有无亲属在本公司</td>
                            <td>
                                <input type="text" class="query_id_card" placeholder="若有，请输入身份证号">
                                <input type="hidden" name="relativesWorking2" value="">
                            </td>
                        </tr>
                        <tr class="add_relatives hide">
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td class="item_name">有无亲属在本公司</td>
                            <td>
                                <input type="text" class="query_id_card" placeholder="若有，请输入身份证号">
                                <input type="hidden" name="relativesWorking3" value="">
                            </td>
                        </tr>
                        <tr class="add_relatives hide">
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td class="item_name">有无亲属在本公司</td>
                            <td>
                                <input type="text" class="query_id_card" placeholder="若有，请输入身份证号">
                                <input type="hidden" name="relativesWorking4" value="">
                            </td>
                        </tr>
                        <tr class="add_relatives hide">
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td class="item_name">有无亲属在本公司</td>
                            <td>
                                <input type="text" class="query_id_card" placeholder="若有，请输入身份证号">
                                <input type="hidden" name="relativesWorking5" value="">
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td class="item_name"><button id="add_one_relative" type="button" class="btn_orange">添加亲属+</button></td>
                            <td></td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>

        <div class="tab_body hide">
            <form id="info_files_form">
                <table class="files_table">
                    <thead>
                        <tr>
                            <th>序号</th>
                            <th>资料清单</th>
                            <th>图片</th>
                            <th>上传</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>1</td>
                            <td>本人有效身份证复印件</td>
                            <td>
                                <div class="ms_thumbnail">
                                    <img class="ms_feedback" src="${ctx}/static/img/staff/1.png">
                                    <input type="hidden" name="idCardCopy" value="">
                                </div>
                            </td>
                            <td>
                                <button type="button" class="ms_upload_file_btn btn_blue">浏览</button>
                            </td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>本人最高学历证明复印件</td>
                            <td>
                                <div class="ms_thumbnail">
                                    <img class="ms_feedback" src="${ctx}/static/img/staff/1.png">
                                    <input type="hidden" name="qualificationCopy" value="">
                                </div>

                            </td>
                            <td>
                                <button type="button" class="ms_upload_file_btn btn_blue">浏览</button>
                            </td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td>免冠一寸彩照（三张）</td>
                            <td>
                                <div class="ms_thumbnail">
                                    <img class="ms_feedback" src="${ctx}/static/img/staff/1.png">
                                    <input type="hidden" name="oneInchPhoto" value="">
                                </div>

                            </td>
                            <td>
                                <button type="button" class="ms_upload_file_btn btn_blue">浏览</button>
                            </td>
                        </tr>
                        <tr>
                            <td>4</td>
                            <td>存折或银行卡复印件</td>
                            <td>
                                <div class="ms_thumbnail">
                                    <img class="ms_feedback" src="${ctx}/static/img/staff/1.png">
                                    <input type="hidden" name="bankCardCopy" value="">
                                </div>
                            </td>
                            <td>
                                <button type="button" class="ms_upload_file_btn btn_blue">浏览</button>
                            </td>
                        </tr>
                        <tr class="no_border">
                            <td>5</td>
                            <td>保证金收据</td>
                            <td>
                                <div class="ms_thumbnail">
                                    <img class="ms_feedback" src="${ctx}/static/img/staff/1.png">
                                    <input type="hidden" name="depositReceipt1" value="">
                                </div>
                            </td>
                            <td>
                                <button type="button" class="ms_upload_file_btn btn_blue">浏览</button>
                            </td>
                        </tr>
                        <tr class="no_border">
                             <td colspan="2"></td>
                             <td>
                                <div class="ms_thumbnail">
                                    <img class="ms_feedback" src="${ctx}/static/img/staff/1.png">
                                    <input type="hidden" name="depositReceipt2" value="">
                                </div>
                            </td>
                            <td>
                                <button type="button" class="ms_upload_file_btn btn_blue">浏览</button>
                            </td>
                        </tr>
                        <tr>
                             <td colspan="2"></td>
                             <td>
                                <div class="ms_thumbnail">
                                    <img class="ms_feedback" src="${ctx}/static/img/staff/1.png">
                                    <input type="hidden" name="depositReceipt3" value="">
                                </div>
                            </td>
                            <td>
                                <button type="button" class="ms_upload_file_btn btn_blue">浏览</button>
                            </td>
                        </tr>
                        <tr>
                            <td>6</td>
                            <td>岗前培训结业证书复印件</td>
                            <td>
                                <div class="ms_thumbnail">
                                    <img class="ms_feedback" src="${ctx}/static/img/staff/1.png">
                                    <input type="hidden" name="certificateCopy" value="">
                                </div>
                            </td>
                            <td>
                                <button type="button" class="ms_upload_file_btn btn_blue">浏览</button>
                            </td>
                        </tr>
                        <tr>
                            <td>7</td>
                            <td>原公司解约申请书复印件或登报声明</td>
                            <td>
                                <div class="ms_thumbnail">
                                    <img class="ms_feedback" src="${ctx}/static/img/staff/1.png">
                                    <input type="hidden" name="leavingCertificate" value="">
                                </div>
                            </td>
                            <td>
                                <button type="button" class="ms_upload_file_btn btn_blue">浏览</button>
                            </td>
                        </tr>
                        <tr>
                            <td>8</td>
                            <td>《代理人承诺书》</td>
                            <td>
                                <div class="ms_thumbnail">
                                    <img class="ms_feedback" src="${ctx}/static/img/staff/1.png">
                                    <input type="hidden" name="agentCommitment" value="">
                                </div>
                            </td>
                            <td>
                                <button type="button" class="ms_upload_file_btn btn_blue">浏览</button>
                            </td>
                        </tr>
                        <tr>
                            <td>9</td>
                            <td>《代理人自动扣款授权书》</td>
                            <td>
                                <div class="ms_thumbnail">
                                    <img class="ms_feedback" src="${ctx}/static/img/staff/1.png">
                                    <input type="hidden" name="agentAutoDeduction" value="">
                                </div>
                            </td>
                            <td>
                                <button type="button" class="ms_upload_file_btn btn_blue">浏览</button>
                            </td>
                        </tr>
                        <tr>
                            <td>10</td>
                            <td>《代理人担保书》</td>
                            <td>
                                <div class="ms_thumbnail">
                                    <img class="ms_feedback" src="${ctx}/static/img/staff/1.png">
                                    <input type="hidden" name="agentGuarantee" value="">
                                </div>
                            </td>
                            <td>
                                <button type="button" class="ms_upload_file_btn btn_blue">浏览</button>
                            </td>
                        </tr>
                        <tr>
                            <td>11</td>
                            <td>已签名《代理人合同书》</td>
                            <td>
                                <div class="ms_thumbnail">
                                    <img class="ms_feedback" src="${ctx}/static/img/staff/1.png">
                                    <input type="hidden" name="agentContract" value="">
                                </div>
                            </td>
                            <td>
                                <button type="button" class="ms_upload_file_btn btn_blue">浏览</button>
                            </td>
                        </tr>
                        <tr>
                            <td>12</td>
                            <td>晋级培训综合考试合格证书复印件</td>
                            <td>
                                <div class="ms_thumbnail">
                                    <img class="ms_feedback" src="${ctx}/static/img/staff/1.png">
                                    <input type="hidden" name="testCertificate" value="">
                                </div>
                            </td>
                            <td>
                                <button type="button" class="ms_upload_file_btn btn_blue">浏览</button>
                            </td>
                        </tr>
                        <tr>
                            <td>13</td>
                            <td>晋级申请书（及其他定级资料）原件</td>
                            <td>
                                <div class="ms_thumbnail">
                                    <img class="ms_feedback" src="${ctx}/static/img/staff/1.png">
                                    <input type="hidden" name="promotionApplication" value="">
                                </div>
                            </td>
                            <td>
                                <button type="button" class="ms_upload_file_btn btn_blue">浏览</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>

<%@include file="../public/public_script3.jsp" %>
<form class="hide" id="submit_form" method="post" action="${ctx}/upload/images" target="exec_target" enctype="multipart/form-data">
    <input type="file" id="replace_input" name="files">
</form>
<iframe id="exec_target" class="hide" name="exec_target"></iframe>
<script src="${ctx}/static/js/public/public_add3.js"></script>
<script src="${ctx}/static/js/staff/add_staff.js"></script>
<script src="${ctx}/static/js/plugins/msdatepicker/msdatepicker.js"></script>

</body>
</html>