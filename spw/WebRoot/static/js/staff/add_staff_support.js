/**
 * 文件：新增后援人员页面脚本
 * 作者：无名小强
 * 版本：v0.2
 * 版权：民盛集团
 * 日期：2016-5-11
**/
$(function(){
    
    //初始化
    init()

    //点击新增提交
    $("#add_support_submit_btn").click(function(){
    	if (checkName() && checkRequired() && checkFormat() && checkPosition() && confirm("您确认提交？")) {
            submitStaffSupport('/staff/save_support', {});
        }
    });
    
  //合同类型改变
    $("#contractType").change(function(){
    	var v = $("#contractType").val();
    	var contractNum = $("#contractNum0").val();
    	var contractNum1 = $("#contractNum1").val();
    	if(v=="0"){
    		$("#contractNum").val(contractNum);
    	}else{
    		$("#contractNum").val(contractNum1);
    	}
    })

    //点击编辑提交
    $("#edit_support_submit_btn").click(function(){
        if (checkRequired() && checkFormat() && checkPosition() && confirm("您确认提交？")) {
            var id = $(this).data('i');
            submitStaffSupport('/staff/updateEntitySupport', {staffId:id});
        }
    });

    //选择总公司还是分公司
    $("#company_type").change(function(){
        var val = $(this).val();
        var type;
        var url = ctx + "/staff/ajax_section";
        var data = {upid: val, departmentType:'nofenbu'}
        
        //如果是分公司
        if (val === '2') {
            type = 'line'
        } else {
            type = 'department'
        }

        initSections();
        getNextSection(url, data, type);
        getPositions(val);
    });

    //选择不同的部门
    $("#section_tr").on("change","select",function(){
        var val = $(this).val();
        var type = $(this).data('type');
        $(this).nextAll().remove();
        if (val === "") {
            if ($(this).index() === 0) {
                initPositions();
                return;
            }
        } else {
        	switch (type) {
                case 'department':
                    getNextSection(ctx + "/staff/ajax_section", {upid: val, departmentType:'nofenbu'}, 'department');
                    getPositions(val)
                    break;
                case 'line':
                    getNextSection(ctx + '/organization/company', {id: val}, 'company');
                    getPositions(val)
                    break;
                case 'company':
                    getNextSection(ctx + '/organization/dxq', {id: val}, 'dxq');
                    break;
                case 'dxq':
                    getJobs();
                    break;   
                default :
                    break;
            }
        }
    });

    //确定选择某个岗位以后
    $('#position_select').change(function(){
        var val = $(this).val();
        if (val !== '') {
            if (checkRepeat()) {
                buildOnePosition();

                $('.select_position_tr').hide();  
                $("#add_gangwei").show();
            } else {
                alert("岗位不能重复！");
            }
        }
    });

    //添加多个岗位
    $("#add_gangwei").click(function(){
        // initSections();
        // getNextSection();
        // initPositions();
        init();
        $('.select_position_tr').show();
        $(this).hide();
    });

    //删除已确定的岗位
    $('#position_ready_td').on('click','.selected_position',function(){
        $(this).remove();
        var len = $('#position_ready_td .selected_position').length;
        //console.log(len);
        if (len === 0) {
            $("#position_ready").hide();
            $("#add_gangwei").click();
        }
    });

    //初始化页面
    function init() {
        initSections()
        initPositions()    
        var data = {upid: $('#company_type').val(), departmentType:'nofenbu'}
        //获取总公司的部门
        getNextSection(ctx + "/staff/ajax_section", data, 'department');

        //获取总公司的岗位
        getPositions($('#company_type').val());    
        getDirectPart();
    }

    /**
     * 函数：检查岗位是否有选择
     * 输入：
     * 输出：
    **/
    function checkPosition() {
        var flag = true;
        var len = $('.selected_position').length;
        if (!len) {
            flag = false;
            alert("组织关系中 岗位 不得为空！");
        }
        return flag;
    }


    /**
     * 函数：初始化部门选择框
     * 输入：
     * 输出：
    **/
    function initSections(){
        $('#section_tr').empty();
    }

    /**
     * 函数：初始化岗位
     * 输入：
     * 输出：
    **/
    function initPositions() {
        $('#position_select').html('<option value="">请选择...</option>');
    }

    
    /**
     * 函数：由数组生成option的html代码
     * 输入：[{id,name}]
     * 输出：html字符串
    **/
    function makeOptionHtml(list) {
        var len = list.length;
        var html = '<option value="">请选择...</option>';
        var optionArr = [];
        //console.log(list);
        optionArr[0] = '<option value="';
        optionArr[1] = '';
        optionArr[2] = '">';
        optionArr[3] = '';
        optionArr[4] = '</option>';
        for (var i = 0; i < len; i++) {
            optionArr[1] = list[i].id ;
            optionArr[3] = list[i].name || list[i].compName;
            html += optionArr.join('');
        }
        return html;
    }

    /**
     * 函数：由数组生成部门option的html代码
     * 输入：[{id,name,hasNext}]
     * 输出：html字符串
    **/
    function makeSectionOptionHtml(list) {
        var len = list.length;
        var html = '<option value="" data-next="0">请选择...</option>';
        var optionArr = [];
        optionArr[0] = '<option value="';
        optionArr[1] = '';
        optionArr[2] = '">';
        optionArr[3] = '';
        optionArr[4] = '</option>';
        for (var i = 0; i < len; i++) {
            optionArr[1] = list[i].id ;
            optionArr[3] = list[i].name || list[i].compName;
            html += optionArr.join('');
        }
        return html;
    }

    /**
     * 函数：请求下一级部门
     * 输入：上一级的ID，下一级的类别
     * 输出：
    **/
    function getNextSection(url, data, type) {
        $.ajax({
            type: "post",
            url: url,
            data: data,
            dataType: "json",
            success: function(msg) {
                if ((msg.list || msg).length > 0 ) {
                    var $tr = $("#section_tr");
                    var $select = $('<select class="select_m" data-type="' + type + '"></select>');
                    $select.html(makeSectionOptionHtml(msg.list || msg));
                    $tr.append($select);
                }   
            }
        });
    }

    /**
     * 函数：生成部门路由名称
     * 输入：
     * 输出：
    **/
    function makeSectionRouteName() {
        var str = $('#company_type').find('option:selected').text();
        $('#section_tr select ').each(function(){
            if ($(this).val()) {
                str += '-' + $(this).find('option:selected').text();
            }
        });
        return str;
    }

    /**
     * 函数：获取当前部门路由下的岗位
     * 输入：
     * 输出：
    **/
    function getPositions(sectionid) {
        $.ajax({
            type: "post",
            url: ctx + "/staff/ajax_position",
            data: {sectionid: sectionid},
            dataType: "json",
            success: function(msg) {
                //console.log(msg);
                if (msg.result.toString() === "1") {
                    var $select = $('#position_select');
                    $select.html(makeOptionHtml(msg.list));
                } else {
                    alert(msg.message);
                }
            }
        });
    }

    //如果是分公司下的，获取督训区下的岗位
    function getJobs() {
        $.ajax({
            type: "post",
            url: ctx + "/staff/getJobs",
            data : {type: 0, lineType: $('#section_tr select:first').val()},
            dataType: "json",
            success: function(msg) {
                $('#position_select').html(makeSectionOptionHtml(msg))
            }
        });
    }

    /**
     * 函数：检查新选择的岗位是否与已选择的重复
     * 输入：
     * 输出：
    **/
    function checkRepeat() {
        var id =  $('#position_select option:selected').val();
        var flag = true;
        $('.selected_position').each(function(){
            if ($(this).data('id') == id) {
                flag = false;
                return false;
            }
        });
        return flag;
    }

    /**
     * 函数：固化当前选定的这个岗位
     * 输入：
     * 输出：
    **/
    function buildOnePosition() {
        var line = getValByDataType('line');
        var company = getValByDataType('company');
        var dxq = getValByDataType('dxq');
        var route_name = makeSectionRouteName();
        var $option = $('#position_select option:selected');
        var name = $option.text() + ' : ' + route_name;
        var id = $option.val();
        var html = '<div class="selected_position" data-id="' + id + '" data-line="' + line + '" data-company="' + company + '" data-dxq="' + dxq + '">' + name + '<i class="iconfont icon-cuowu"></i></div>';
        $('#position_ready_td').append(html);
        $('#position_ready').show();
        
    }

    //根据data-type的值来选择元素，并取值
    function getValByDataType(str) {
        var val = '';
        $('#section_tr select').each(function(){
            if($(this).data('type') === str) {
                val = $(this).val();
                return false
            }
        })
        return val
    }

    /**
     * 函数：从页面获取整理数据，提交给后台
     * 输入：toUrl(提交地址),datas(额外的数据)
     * 输出：
    **/
    function submitStaffSupport(toUrl, datas) {
        var staff = JSON.stringify(formToObject($("#staff_form")));
        var salary = JSON.stringify(formToObject($("#salary_form")));
        var formWorkExp = formToObject($("#work_exp_form"));
        var oWorkExp = {insurancePeriod:formWorkExp.insurancePeriod, exps:[]};
        var oOrganization = $.extend({list:[]},formToObject($("#organization_form")));
        var obt, obt2;
        
        //拼接工作经验
        for (var i = 1; i < 5; i++) {
            obt = {
                workBeginDate: formWorkExp["workBeginDate" + i],
                workEndDate: formWorkExp["workEndDate" + i],
                workUnit: formWorkExp["workUnit" + i],
                workPosition: formWorkExp["workPosition" + i],
                industryInvolved: formWorkExp["industryInvolved" + i],
                isCommonJob: formWorkExp["isCommonJob" + i],
                wid: formWorkExp["wid" + i]
            };
            if (obt.workBeginDate) {
                oWorkExp.exps.push(obt);
            }
        }

        //拼接工作岗位
        $('#position_ready_td .selected_position').each(function(){
            obt2 = {
                lineID: $(this).data('line'),         //分公司岗位 渠道ID
                subComID: $(this).data('company'),       //分公司岗位 省分公司id
                subCountyID: $(this).data('dxq'),        //分公司岗位 督训区id
                positionID: $(this).data('id'),     //岗位ID
            };
            oOrganization.list.push(obt2);
        });
 
        var workExp = JSON.stringify(oWorkExp);
        var organization = JSON.stringify(oOrganization); 
        datas = $.extend(datas,{staff:staff, workExp:workExp, organization:organization,salary:salary});

        $.ajax({
            type: "post",
            url: ctx + toUrl,
            data: datas,
            dataType: "json",
            success: function(msg) {
                if(msg.result.toString() === '1'){
                	alert('姓名：'+msg.name+'\r\n职位：'+msg.positon+'\r\n岗位号：'+msg.worknum+'\r\n合同编号：'+msg.contractNum);
                	parent.closeBox();
                    parent.freshList()
                } else {
                    alert(msg.message);
                }
            }
        });
    }
});
/**
 * 
 */
function getDirectPart(){
	var id =$("#ownFenbu").val();
	if(id==1){
		$("#qudao").show();
		$("#duxunqu").show();
		$("#zhiji").show();
		$("#fenbu").attr("name","havePartId");
		$("#position").attr("name","position");
	}else{
		$("#qudao").hide();
		$("#duxunqu").hide();
		$("#zhiji").hide();
		$("#fenbu").removeAttr("name");
		$("#position").removeAttr("name");
	}
}
function checkSupportName() {
 	var username = $('#username').val();
 	var id = $("#edit_support_submit_btn").data("i");
     $.ajax({
         type: "post",
         url: ctx + "/app/check_staff_name",
         data: {name:username,"id":id,"isApply":"0"},
         dataType: "json",
         success: function(msg) {
             if(msg.result == "1"){
            		if(msg.num===0){
            			$('#username').val(username);
            			return true;
            		}else{
            			alert("已有"+msg.num+"个重名人员");
            			return false;
            		}
        	  }else{
	        		alert(msg.message);
	        		return false;
        	  }
         }
     });
     return true;
 }