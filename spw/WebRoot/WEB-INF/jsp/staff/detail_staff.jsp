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
                            <td class="item_name"><span class="red">*</span> 姓名</td>
                            <td>
                                <input type="text" class="required"  value="${staff.name}" data-c="姓名" id= "username" name="name" readonly="readonly">
                            </td>
                            <td class="item_name"><span class="red">*</span> 手机号码</td>
                            <td>
                                <input type="text" class="required formated" value="${staff.phoneNum}" data-c="手机号码" name="phoneNum" readonly="readonly">
                            </td>
                            <td class="item_name"><span class="red">*</span> 身份证号码</td>
                            <td>
                                <input type="text" class="required formated" value="${staff.idCard}" id="id_card" data-c="身份证号码" name="idCard" readonly="readonly">
                            </td>
                        </tr>
                        <tr>
                            <td class="item_name"><span class="red">*</span> 入职时间</td>
                            <td>
                                <input type="text" class="input_m ms_datepicker required" value="${staff.entryDate}" data-c="入职时间" name="entryDate" placeholder="例2013-01-02" readonly="readonly">
                            </td>

                        </tr>
                        <tr>
                            <td class="item_name"><span class="red">*</span> 渠道</td>
                            <td>
                                <select id="line_type" class="select_m required" name="lineType" data-v="${staff.lineType}" disabled="disabled">
                                    <option value="">请选择条线部门</option>
                                    <c:forEach items="${lineType}" var="itme" varStatus="st">
                                         <option value="${itme.id}">${itme.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td class="item_name"><span class="red">*</span> 省级机构</td>
                            <td>
                                <select id="branch_company_id" class="select_m required" data-v="${staff.branchCompanyId}" name="branchCompanyId" disabled="disabled">
                                    <option value="">请选择所属省级机构</option>
                                    <c:forEach items="${comList}" var="itme" varStatus="st">
                                        <option value="${itme.id}">${itme.compName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        
                        <tr>
                            <td class="item_name"><span class="red">*</span> 督训区</td>
                            <td>
                                <select id="county_franchisees_id" class="select_m required" data-v="${staff.countyFranchiseesId}" name="countyFranchiseesId" disabled="disabled">
                                    <option value="">请选择督训区</option>
                                    <c:forEach items="${dxlist}" var="itme" varStatus="st">
                                        <option value="${itme.id}">${itme.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                           <td class="item_name"><span class="red">*</span>分部  </td>
                            <td>
                                <select id="fenbu" class="select_m"  data-v="${staff.storeId}" name="storeId" disabled="disabled">
                                    <option value="">请选择分部</option>
                                    <c:forEach items="${fblist}" var="itme" varStatus="st">
                                        <option value="${itme.id}">${itme.name}</option>
                                    </c:forEach>
                                </select>
                                <input type="hidden" name="orgType"  value="${staff.orgType}" id="orgType" />
                                <button type="button" class="btn_red delete_fenbu" id="delete_fenbu">撤销</button>
                            </td>
                        </tr>
                        
                        <tr>
                            <td class="item_name"><span class="red">*</span> 职级</td>
                            <td>
                                <select id="position" class="select_m required" name="position" data-v="${staff.position}" disabled="disabled">
                                    <option value="">请选择职级</option>
                                    <c:forEach items="${zlist}" var="itme" varStatus="st">
                                        <option value="${itme.id}">${itme.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td class="has_fenbu item_name">有无直属分部</td>
                            <td class="has_fenbu">
                                <select id="ownered_fenbu"  class="select_m" data-v="${staff.havePartId}" name="havePartId">
                                    <option value="">无</option>
                                    <c:forEach items="${olist}" var="itme" varStatus="st">
                                        <option value="${itme.id}">${itme.name}</option>
                                     </c:forEach>
                                </select>
                            </td>
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
                            <td><select name="contractType" id="contractType" data-v="${staff.contractType}" disabled="disabled">
                                    <option value="0" selected="selected">代理合同</option>
                                    <option value="1">劳务合同</option>
                                 </select>
                            </td>
                            <td class="item_name">合同编号</td>
                            <td>
                                <span id="contractSpan">${staff.contractNum }</span>
                                <input type="hidden" value="${staff.contractNum }" name="contractNum" readonly="readonly" id="contractNum" data-v="${staff.contractNum }" data-v2="${staff.contractNum }"/>
                                <!-- <input type="hidden"  value="${contractNum2}" id="contractNum0" />
                                <input type="hidden"  value="${contractNum}" id="contractNum1" /> -->
                            </td>
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
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="item_name"><span class="red">*</span> 银行卡号</td>
                            <td><input type="text" class="required formated" value="${staff.bankCardNum}" readonly="readonly" name="bankCardNum" id="bank_card_num"></td>
                            <td class="item_name"><span class="red">*</span> 开户银行</td>
                            <td><input type="text" class="required" value="${staff.bankName}" name="bankName" readonly="readonly" id="bank_name"></td>
                        </tr>

                        <tr>
                            <td class="item_name">婚姻状况</td>
                            <td>
                                <select name="maritalStatus" data-v="${staff.maritalStatus}" disabled="disabled">
                                    <option value="0">未婚</option>
                                    <option value="1">已婚</option>
                                    <option value="2">离异</option>
                                    <option value="3">丧偶</option>
                                </select>
                            </td>
                            <td class="item_name">民族</td>
                            <td>
                                <input type="text" class="input_s" name="nation" value="${staff.nation}" readonly="readonly">
                            </td>
                        </tr>

        
                        <tr>
                            <td class="item_name">QQ号码</td>
                            <td><input type="text" name="qq" value="${staff.qq}" readonly="readonly"></td>
                            <td class="item_name">微信号</td>
                            <td><input type="text" class="input_m" name="wechatId" value="${staff.wechatId}" readonly="readonly"></td>
                        </tr>

                        <tr>
                            <td class="item_name">资格证号码</td>
                            <td><input type="text" name="generationCertification" value="${staff.generationCertification}" readonly="readonly"></td>
                            <td class="item_name">政治面貌</td>
                            <td>
                                <select name="politicalStatus" data-v="${staff.politicalStatus}" disabled="disabled">
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
                            <td><input type="text" name="telephone" value="${staff.telephone}"  readonly="readonly"></td>
                            <td class="item_name">是否有车补</td>
                            <td><input type="checkbox" name="haveCar" value="是" id="haveCar" data-v ="${staff.haveCar}" disabled="disabled"></td>
                        </tr>

                        <tr>
                            <td class="item_name">健康状况</td>
                            <td colspan="3">
                                <select name="physicalCondition" data-v="${staff.physicalCondition}"  disabled="disabled">
                                    <option value="0">健康</option>
                                    <option value="1">一般</option>
                                    <option value="2">较差</option>
                                </select>
                                （备注 <input type="text" name="physicalRemark" value="${staff.physicalRemark}"  readonly="readonly">）
                            </td>
                        </tr>


                        <tr>
                            <td class="item_name">学历</td>
                            <td>
                                <select name="education" data-v="${staff.education}"  disabled="disabled">
                                    <option value="0">高中</option>
                                    <option value="1">专科</option>
                                    <option value="2" selected="selected">本科</option>
                                    <option value="3">硕士</option>
                                    <option value="4">博士</option>
                                    <option value="5">其他</option>
                                </select>
                            </td>
                            <td class="item_name">毕业学校</td>
                            <td><input type="text" name="graduateInstitutions" value="${staff.graduateInstitutions}"  readonly="readonly"></td>
                            <td class="item_name">专业</td>
                            <td><input type="text" name="major" value="${staff.major}"  readonly="readonly"></td>
                        </tr>
                    
                        <tr>
                            <td class="item_name">户口所在地</td>
                            <td colspan="3">
                                <select class="s_province" name="domicilePlaceProvince" data-v="${staff.domicilePlaceProvince}" disabled="disabled">
                                    <option value="">请选择省</option>
                                    <c:forEach items="${plist}" var="itme" varStatus="st">
                                         <option value="${itme.provinceNo}">${itme.provinceName}</option>
                                    </c:forEach>
                                </select>
                                <select class="s_region" name="domicilePlaceRegion" data-v="${staff.domicilePlaceRegion}" disabled="disabled">
                                    <option value="">请选择市</option>
                                     <c:forEach items="${relist}" var="itme" varStatus="st">
                                                     <option value="${itme.regionNo}">${itme.regionName}</option>
                                                  </c:forEach>
                                </select>
                                <select class="s_county" name="domicilePlaceCounty" data-v="${staff.domicilePlaceCounty}" disabled="disabled">
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
                                <input class="long" type="text" data-c="户籍地址" value="${staff.domicilePlaceDetail}" name="domicilePlaceDetail" placeholder="请输入详细地址"readonly="readonly">
                            </td>
                        </tr>
                        <tr>
                            <td class="item_name">家庭住址</td>
                            <td colspan="3">
                               <select class="s_province" id="provinceId" name="provinceId" data-v="${staff.provinceId}" disabled="disabled">
                                    <option value="">请选择省</option>
                                    <c:forEach items="${plist}" var="itme" varStatus="st">
                                         <option value="${itme.provinceNo}">${itme.provinceName}</option>
                                    </c:forEach>
                                </select>
                                <select class="s_region" name="regionId" data-v="${staff.regionId}" disabled="disabled">
                                    <option value="">请选择市</option>
                                    <c:forEach items="${re2list}" var="itme" varStatus="st">
                                                     <option value="${itme.regionNo}">${itme.regionName}</option>
                                                  </c:forEach>
                                </select>
                                <select class="s_county" name="countyId" data-v="${staff.countyId}" disabled="disabled">
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
                                <input class="long" type="text"  name="address" placeholder="请输入详细地址"  readonly="readonly">
                            </td>
                        </tr>
                        <tr>
                            <td class="item_name">打款时间</td>
                            <td><input type="text" class="ms_datepicker" name="payTime1" value="${staff.list[0].payTime }"  readonly="readonly"></td>
                            <td class="item_name">打款金额</td>
                            <td><input type="text"  name="payMoney1" class="formated" data-c="金额" value="${staff.list[0].payMoney }"  readonly="readonly"></td>
                            <td class="item_name">打款账户</td>
                            <td>
                                <c:if test="${staff.list[0].account==null}"><input type="text" name="account1" value="宋总账户"  readonly="readonly"></c:if>
                                <c:if test="${staff.list[0].account!=null}"><input type="text" name="account1" value="${staff.list[0].account }"  readonly="readonly" ></c:if>
                            </td>
                        </tr>
                    
                        <tr>
                            <td class="item_name">打款时间</td>
                            <td><input type="text" class="ms_datepicker" name="payTime2" value="${staff.list[1].payTime }"  readonly="readonly"></td>
                            <td class="item_name">打款金额</td>
                            <td><input type="text"  name="payMoney2" class="formated" data-c="金额" value="${staff.list[1].payMoney }"  readonly="readonly"></td>
                            <td class="item_name">打款账户</td>
                            <td>
                                <c:if test="${staff.list[1].account==null}"><input type="text" name="account2" value="宋总账户"  readonly="readonly"></c:if>
                                <c:if test="${staff.list[1].account!=null}"><input type="text" name="account2" value="${staff.list[1].account }" readonly="readonly" ></c:if>
                            </td>
                        </tr>
                        
                        <tr>
                            <td class="item_name">打款时间</td>
                            <td><input type="text" class="ms_datepicker" name="payTime3" value="${staff.list[2].payTime }"  readonly="readonly"></td>
                            <td class="item_name">打款金额</td>
                            <td><input type="text"  name="payMoney3" class="formated" data-c="金额" value="${staff.list[2].payMoney }"  readonly="readonly"></td>
                            <td class="item_name">打款账户</td>
                            <td>
                                <c:if test="${staff.list[2].account==null}"><input type="text" name="account3" value="宋总账户"   readonly="readonly"></c:if>
                                <c:if test="${staff.list[2].account!=null}"><input type="text" name="account3" value="${staff.list[2].account }"  readonly="readonly"></c:if>
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
                                <select id="insurance_industry" name="insurancePeriod" data-v="${insurancePeriod}" disabled="disabled">
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
                            <td colspan="3"><input type="text" class="input_m ms_datepicker" name="workBeginDate1" value="${workExp[0].workBeginDate}" readonly="readonly"> 
                            — <input type="text" class="input_m ms_datepicker" name="workEndDate1" value="${workExp[0].workEndDate}" readonly="readonly"></td>
                            <td class="item_name">所属行业</td>
                            <td><input type="text" name="industryInvolved1" value="${workExp[0].industryInvolved}" readonly="readonly"></td>
                        </tr>
                        <tr>
                            
                            <td class="item_name">工作单位</td>
                            <td><input type="text" name="workUnit1" value="${workExp[0].workUnit}" readonly="readonly"></td>
                            <td class="item_name">职务</td>
                            <td><input type="text" name="workPosition1" value="${workExp[0].workPosition}" readonly="readonly"></td>
                            <td class="item_name"><span class="check_for_the_same">属于同业</span></td>
                            <td>
                                <p class="check_for_the_same">
                                <!-- <label>
                                    属于同业  -->
                                    <input type="checkbox" class="belong_to_same" readonly="readonly" <c:if test="${workExp[0].isCommonJob==1}">checked=="checked"</c:if>>
                                    <input type="hidden" class="isCommonJobInput" readonly="readonly" name="isCommonJob1" value="0" ></input>
                                    <input type="hidden" name="wid1"  readonly="readonly" value="<c:if test='${empty workExp[0].id}'>0</c:if>${workExp[0].id}" />
                                <!-- </label> -->
                                </p>
                            </td>
                        </tr>
                        
                        <tr>
                            <td class="item_name">起止年月</td>
                            <td colspan="3"><input type="text" class="input_m ms_datepicker" name="workBeginDate2" readonly="readonly" value="${workExp[1].workBeginDate}">
                             — <input type="text" class="input_m ms_datepicker" name="workEndDate2"readonly="readonly" value="${workExp[1].workEndDate}"></td>
                            <td class="item_name">所属行业</td>
                            <td><input type="text" name="industryInvolved2" readonly="readonly"value="${workExp[1].industryInvolved}"></td>
                        </tr>
                        <tr>
                            <td class="item_name">工作单位</td>
                            <td><input type="text" name="workUnit2" readonly="readonly"value="${workExp[1].workUnit}"></td>
                            <td class="item_name">职务</td>
                            <td><input type="text" name="workPosition2"readonly="readonly"  value="${workExp[1].workPosition}"></td>
                            <td class="item_name">
                                属于同业 
                            </td>
                            <td>
                                <p class="check_for_the_same">
                                    <!-- <label>
                                        属于同业 -->
                                        <input type="checkbox" class="belong_to_same" readonly="readonly"<c:if test="${workExp[1].isCommonJob==1}">checked=="checked"</c:if>>
                                        <input type="hidden" class="isCommonJobInput" name="isCommonJob2" value="0">
                                        <input type="hidden" name="wid2" value="<c:if test='${empty workExp[1].id}'>0</c:if>${workExp[1].id}">
                                    <!-- </label> -->
                                </p>
                            </td>
                        </tr>
                        
                        <tr>
                            <td class="item_name">起止年月</td>
                            <td colspan="3"><input type="text" class="input_m ms_datepicker" readonly="readonly"name="workBeginDate3" value="${workExp[2].workBeginDate}"> — <input type="text" class="input_m ms_datepicker" name="workEndDate3" value="${workExp[2].workEndDate}"></td>
                            <td class="item_name">所属行业</td>
                            <td><input type="text" name="industryInvolved3"readonly="readonly" value="${workExp[2].industryInvolved}"></td>
                        </tr>
                        <tr>
                            <td class="item_name">工作单位</td>
                            <td><input type="text" name="workUnit3" readonly="readonly"value="${workExp[2].workUnit}"></td>
                            <td class="item_name">职务</td>
                            <td><input type="text" name="workPosition3"readonly="readonly" value="${workExp[2].workPosition}"></td>
                            <td class="item_name">
                                属于同业 
                            </td>
                            <td>
                                <p class="check_for_the_same">
                                    <!-- <label>
                                        属于同业  -->
                                        <input type="checkbox" class="belong_to_same"readonly="readonly" <c:if test="${workExp[2].isCommonJob==1}">checked=="checked"</c:if>>
                                        <input type="hidden" class="isCommonJobInput" name="isCommonJob3" value="0"></input>
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
                            <td colspan="3"><input type="text"readonly="readonly" class="input_m ms_datepicker" name="workBeginDate4" value="${workExp[3].workBeginDate}"> 
                            — <input type="text" class="input_m ms_datepicker" name="workEndDate4" readonly="readonly" value="${workExp[3].workEndDate}"></td>
                        </tr>
                        <tr class="last_the_same">
                            <td class="item_name">工作单位</td>
                            <td><input type="text" name="workUnit4" readonly="readonly"value="${workExp[3].workUnit}"></td>
                            <td class="item_name"readonly="readonly" value="${workExp[3].workPosition}">职务</td>
                            <td>
                                <input type="text"readonly="readonly" name="workPosition4">
                                <input type="hidden" class="isCommonJobInput" name="isCommonJob4" value="1">
                                <input type="hidden" name="wid4" value="<c:if test='${empty workExp[3].id}'>0</c:if>${workExp[3].id}">
                            </td>
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
                                <input type="text"readonly="readonly" name="referrerIdCardName" class="query_id_card" value="${staff.referrerIdCardName}"  placeholder="输入身份证号查询">
                                <input type="hidden" name="referrerIdCard"  value="">
                            </td>
                            <td class="item_name">担保人</td>
                            <td>
                                <input type="text"readonly="readonly" class="query_id_card"  value="${bondsman1}" placeholder="输入身份证号或姓名">
                                <input type="hidden" name="bondsman1"  value="${bondsman1}">
                            </td>
                            <td class="item_name">有无亲属在本公司</td>
                            <td>
                                <input type="text"readonly="readonly" class="query_id_card" placeholder="若有，请输入身份证号" value="${rlist[0]}">
                                <input type="hidden" name="relativesWorking1" value="${rlist[0]}">
                            </td>
                        </tr>
                        
                        <tr>
                            <td></td>
                            <td></td>
                            <td class="item_name">担保人</td>
                            <td>
                                <input type="text" class="query_id_card"readonly="readonly" value="${bondsman2}" placeholder="输入身份证号或姓名">
                                <input type="hidden" name="bondsman2"  value="${bondsman2}">
                            </td>
                            <td class="item_name">有无亲属在本公司</td>
                            <td>
                                <input type="text" class="query_id_card"readonly="readonly"placeholder="若有，请输入身份证号" value="${rlist[1]}">
                                <input type="hidden" name="relativesWorking2"  value="${rlist[1]}">
                            </td>
                        </tr>
                        <tr class="add_relatives hide">
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td class="item_name">有无亲属在本公司</td>
                            <td>
                                <input type="text" class="query_id_card"readonly="readonly"placeholder="若有，请输入身份证号" value="${rlist[2]}">
                                <input type="hidden" name="relativesWorking3"  value="${rlist[2]}">
                            </td>
                        </tr>
                        <tr class="add_relatives hide">
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td class="item_name">有无亲属在本公司</td>
                            <td>
                                <input type="text" class="query_id_card" readonly="readonly"placeholder="若有，请输入身份证号" value="${rlist[3]}">
                                <input type="hidden" name="relativesWorking4"  value="${rlist[3]}">
                            </td>
                        </tr>
                        <tr class="add_relatives hide">
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td class="item_name">有无亲属在本公司</td>
                            <td>
                                <input type="text" class="query_id_card" readonly="readonly"placeholder="若有，请输入身份证号" value="${rlist[4]}">
                                <input type="hidden" name="relativesWorking5"  value="${rlist[4]}">
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
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
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>1</td>
                            <td>本人有效身份证复印件</td>
                            <td>
                                <div class="ms_thumbnail">
                                    <img class="ms_feedback" <c:if test="${staff.idCardCopy!='' && staff.idCardCopy!=null}">
                                                      src="${staff.idCardCopy}"
                                                    </c:if> src="${ctx}/static/img/staff/1.png">
                                    <input type="hidden" name="idCardCopy" value="${staff.idCardCopy}">
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>本人最高学历证明复印件</td>
                            <td>
                                <div class="ms_thumbnail">
                                    <img class="ms_feedback" <c:if test="${staff.qualificationCopy!='' && staff.qualificationCopy!=null}">
                                                      src="${staff.qualificationCopy}"
                                                    </c:if> src="${ctx}/static/img/staff/1.png">
                                    <input type="hidden" name="qualificationCopy" value="${staff.qualificationCopy}">
                                </div>

                            </td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td>免冠一寸彩照（三张）</td>
                            <td>
                                <div class="ms_thumbnail">
                                    <img class="ms_feedback" <c:if test="${staff.oneInchPhoto!='' && staff.oneInchPhoto!=null}">
                                                      src="${staff.oneInchPhoto}"
                                                    </c:if>src="${ctx}/static/img/staff/1.png">
                                    <input type="hidden" name="oneInchPhoto" value="${staff.oneInchPhoto}">
                                </div>

                            </td>
                        </tr>
                        <tr>
                            <td>4</td>
                            <td>存折或银行卡复印件</td>
                            <td>
                                <div class="ms_thumbnail">
                                    <img class="ms_feedback" <c:if test="${staff.bankCardCopy!='' && staff.bankCardCopy!=null }">
                                                      src="${staff.bankCardCopy}"
                                                    </c:if> src="${ctx}/static/img/staff/1.png">
                                    <input type="hidden" name="bankCardCopy" value="${staff.bankCardCopy}">
                                </div>
                            </td>
                        </tr>
                        <tr class="no_border">
                            <td>5</td>
                            <td>保证金收据</td>
                            <td>
                                <div class="ms_thumbnail">
                                    <img class="ms_feedback" <c:if test="${staff.depositReceipt1!='' && staff.depositReceipt1!=null}">
                                                      src="${staff.depositReceipt1}"
                                                    </c:if> src="${ctx}/static/img/staff/1.png">
                                    <input type="hidden" name="depositReceipt1" value="${staff.depositReceipt1}">
                                </div>
                            </td>
                        </tr>
                        <tr class="no_border">
                             <td colspan="2"></td>
                             <td>
                                <div class="ms_thumbnail">
                                    <img class="ms_feedback"  <c:if test="${staff.depositReceipt2!='' && staff.depositReceipt2!=null}">
                                                      src="${staff.depositReceipt2}"
                                                    </c:if> src="${ctx}/static/img/staff/1.png">
                                    <input type="hidden" name="depositReceipt2" value="${staff.depositReceipt2}">
                                </div>
                            </td>
                        </tr>
                        <tr>
                             <td colspan="2"></td>
                             <td>
                                <div class="ms_thumbnail">
                                    <img class="ms_feedback" <c:if test="${staff.depositReceipt3!='' && staff.depositReceipt3!=null}">
                                                      src="${staff.depositReceipt3}"
                                                    </c:if>  src="${ctx}/static/img/staff/1.png">
                                    <input type="hidden" name="depositReceipt3" value="${staff.depositReceipt3}">
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>6</td>
                            <td>岗前培训结业证书复印件</td>
                            <td>
                                <div class="ms_thumbnail">
                                    <img class="ms_feedback" <c:if test="${staff.certificateCopy!='' && staff.certificateCopy!=null}">
                                                      src="${staff.certificateCopy}"
                                                    </c:if>  src="${ctx}/static/img/staff/1.png">
                                    <input type="hidden" name="certificateCopy" value="${staff.certificateCopy}">
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>7</td>
                            <td>原公司解约申请书复印件或登报声明</td>
                            <td>
                                <div class="ms_thumbnail">
                                    <img class="ms_feedback" <c:if test="${staff.leavingCertificate!='' && staff.leavingCertificate!=null}">
                                                      src="${staff.leavingCertificate}"
                                                    </c:if> src="${ctx}/static/img/staff/1.png">
                                    <input type="hidden" name="leavingCertificate" value="${staff.leavingCertificate}">
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>8</td>
                            <td>《代理人承诺书》</td>
                            <td>
                                <div class="ms_thumbnail">
                                    <img class="ms_feedback" <c:if test="${staff.agentCommitment!='' && staff.agentCommitment!=null}">
                                                      src="${staff.agentCommitment}"
                                                    </c:if>  src="${ctx}/static/img/staff/1.png">
                                    <input type="hidden" name="agentCommitment" value="${staff.agentCommitment}">
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>9</td>
                            <td>《代理人自动扣款授权书》</td>
                            <td>
                                <div class="ms_thumbnail">
                                    <img class="ms_feedback" <c:if test="${staff.agentAutoDeduction!='' && staff.agentAutoDeduction!=null}">
                                                      src="${staff.agentAutoDeduction}"
                                                    </c:if>  src="${ctx}/static/img/staff/1.png">
                                    <input type="hidden" name="agentAutoDeduction" value="${staff.agentAutoDeduction}">
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>10</td>
                            <td>《代理人担保书》</td>
                            <td>
                                <div class="ms_thumbnail">
                                    <img class="ms_feedback" <c:if test="${staff.agentGuarantee!='' && staff.agentGuarantee!=null}">
                                                      src="${staff.agentGuarantee}"
                                                    </c:if> src="${ctx}/static/img/staff/1.png">
                                    <input type="hidden" name="agentGuarantee" value="${staff.agentGuarantee}">
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>11</td>
                            <td>已签名《代理人合同书》</td>
                            <td>
                                <div class="ms_thumbnail">
                                    <img class="ms_feedback"  <c:if test="${staff.agentContract!='' && staff.agentContract!=null}">
                                                      src="${staff.agentContract}"
                                                    </c:if> src="${ctx}/static/img/staff/1.png">
                                    <input type="hidden" name="agentContract" value="${staff.agentContract}">
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>12</td>
                            <td>晋级培训综合考试合格证书复印件</td>
                            <td>
                                <div class="ms_thumbnail">
                                    <img class="ms_feedback"  <c:if test="${staff.testCertificate!='' && staff.testCertificate!=null}">
                                                      src="${staff.testCertificate}"
                                                    </c:if> src="${ctx}/static/img/staff/1.png">
                                    <input type="hidden" name="testCertificate" value="${staff.testCertificate}">
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>13</td>
                            <td>晋级申请书（及其他定级资料）原件</td>
                            <td>
                                <div class="ms_thumbnail">
                                    <img class="ms_feedback" <c:if test="${staff.promotionApplication!='' && staff.promotionApplication!=null}">
                                                      src="${staff.promotionApplication}"
                                                    </c:if> src="${ctx}/static/img/staff/1.png">
                                    <input type="hidden" name="promotionApplication" value="${staff.promotionApplication}">
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>
<input type="hidden" id="lists" value="${rlist}">

<%@include file="../public/public_script3.jsp" %>
<script src="${ctx}/static/js/public/public_add3.js"></script>
<script src="${ctx}/static/js/staff/add_staff.js"></script>
<script src="${ctx}/static/js/staff/edit_staff.js"></script>
</body>
</html>