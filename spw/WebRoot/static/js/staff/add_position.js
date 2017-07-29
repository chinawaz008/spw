/**
 * 文件：新增晋升人员页面脚本
 * 作者：无名小强
 * 版本：v0.1
 * 版权：民盛集团
 * 日期：2016-4-18
**/
$(function(){
	
    //获取总公司的部门
    getNextSection();

    //所有select在页面加载后显示值
    $("select").each(function(){
        var value = $(this).data("v");
        $(this).find("option[value='" + value + "']").attr("selected",true);
    });
   
    //选择总公司还是分公司
    $("#company_type").change(function(){
        var val = $(this).val();
        initSections();
        if(val === '1') {
            initCompanys();
        } else if (val === '2') {
            getLineType();
        }
        getNextSection();
    });

    //选择不同的条线
    $('#line_type').change(function(){
        var val = $(this).val();
        if (val === '') {
            $('#sub_company').hide().empty();
        } else {
            $.ajax({
                type: "post",
                url: ctx + "/staff/ajax_sub_company",
                data: {lineid:val},
                dataType: "json",
                success: function(msg) {
                    //console.log(msg);
                    if (msg.result.toString() === "1") {
                        $("#sub_company").addClass('required').html(makeOptionHtml(msg.list)).show();
                    } else {
                        alert(msg.message);
                    }
                }
            });
        }
    });

    //选择不同的部门
    $("#section_tr").on("change","select",function(){
        var val = $(this).val();
        $(this).nextAll().remove();
        if (val === "") {
            if ($(this).index() === 0) {
                initPositions();
                return;
            }
        } else {
//            var next = $(this).find('option:selected').data('next');
//            if (next.toString() === '1') {
                getNextSection();
//            }
        }
        getPositions();
    });

    //确定选择某个岗位以后
    $('#position_select').change(function(){
        var val = $(this).val();
        if (val !== '') {
            buildOnePosition();
            $('.select_position_tr').hide(); 
        }
        $("#add_gangwei").show();
    });

    //添加多个岗位
    $("#add_gangwei").click(function(){
        initSections();
        getNextSection();
        initPositions();
        $('.select_position_tr').show();
        $(this).hide();
    });

    //删除已确定的岗位
    $('#position_ready_td').on('click','.selected_position',function(){
        $(this).remove();
        var len = $('#position_ready_td .selected_position').length;
        console.log(len);
        if (len === 0) {
            $("#position_ready").hide();
            $("#add_gangwei").click();
        }
    });


    /**
     * 函数：初始化条线和分公司的选择框
     * 输入：
     * 输出：
    **/
    function initCompanys() {
        $('#line_type').hide().removeClass('required').empty();
        $('#sub_company').hide().removeClass('required').empty();
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
     * 函数：获取条线列表，并显示出来
     * 输入：
     * 输出：
    **/
    function getLineType() {
        $.ajax({
            type: "post",
            url: ctx + "/staff/ajax_line_type",
            dataType: "json",
            success: function(msg) {
                //console.log(msg);
                if (msg.result.toString() === "1") {
                    initCompanys();
                    $("#line_type").addClass('required').html(makeOptionHtml(msg.list)).show();
                } else {
                    alert(msg.message);
                }
            }
        });
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
        //console.log(list);
        optionArr[0] = '<option value="';
        optionArr[1] = '';
        optionArr[2] = '" data-next="';
        optionArr[3] = '0';
        optionArr[4] = '">';
        optionArr[5] = '';
        optionArr[6] = '</option>';
        for (var i = 0; i < len; i++) {
            optionArr[1] = list[i].id ;
            optionArr[3] = list[i].hasNext;
            optionArr[5] = list[i].name || list[i].compName;
            html += optionArr.join('');
        }
        return html;
    }

    /**
     * 函数：请求下一级部门
     * 输入：
     * 输出：
    **/
    function getNextSection() {
        var str = makeSectionRoute();
        $.ajax({
            type: "post",
            url: ctx + "/staff/ajax_section",
            data: {upid: str, departmentType:'nofenbu'},
            dataType: "json",
            success: function(msg) {
                //console.log(msg);
                if (msg.result.toString() === "1") {
                    var $tr = $("#section_tr");
                    var $select = $('<select class="select_m"></select>');
                    $select.html(makeSectionOptionHtml(msg.list));
                    $tr.append($select);
                    
                } else {
                    alert(msg.message);
                }
            }
        });
    }

    /**
     * 函数：生成部门路由
     * 输入：
     * 输出：
    **/
    function makeSectionRoute() {
        var str = $('#company_type').val();
        $('#section_tr select').each(function(){
//            str += '@' + $(this).val();
            str = $(this).val();
        });
        return str;
    }

    /**
     * 函数：获取当前部门路由下的岗位
     * 输入：
     * 输出：
    **/
    function getPositions() {
        var str = makeSectionRoute();
        $.ajax({
            type: "post",
            url: ctx + "/staff/ajax_position",
            data: {sectionid: str},
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

    /**
     * 函数：固化当前选定的这个岗位
     * 输入：
     * 输出：
    **/
    function buildOnePosition() {
        var line = getValByDataType('line');
        var company = getValByDataType('company');
        var route = makeSectionRoute();
        var $option = $('#position_select option:selected');
        var name = $option.text();
        var id = $option.val();
        var html = '<div class="selected_position" data-route="' + route + '" data-id="' + id + '" data-line="' + line + '" data-company="' + company +  '">' + name + '<i class="iconfont icon-cuowu"></i></div>';
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
    
    //点击搜索
    $("#search_name_num").click(function(){
        $("#tips_td").empty();
        var value = $.trim($("#name_num").val());
        if(value){
            $.ajax({
                type: "post",
                url: ctx + "/staff/getBackPerson",
                data : {data: value},
                dataType: "json",
                success: function(msg) {
                    if (msg.result.toString() === "1") {
                        if (msg.members.length === 1) {
                            fillPerson(msg.members[0]);
                        } else {
                            console.log(msg.members);
                            listCandidate(msg.members);
                        }
                    } else {
                        $("#tips_td").html('<span style="color:red">' + msg.message + '</span>');
                    }
                }
            });
        } else {
            alert("请输入姓名或工号");
        }
    });

    //点击提交——新增
    $("#change_position_btn").click(function(){
    	var oOrganization = $.extend({list:[]},formToObject($("#organization_form")));

        console.log(oOrganization);
        //拼接工作岗位
        $('#position_ready_td .selected_position').each(function(){
            obt2 = {
                lineID: $(this).data('line'),         //分公司岗位 渠道ID
                subComID: $(this).data('company'),       //分公司岗位 省分公司id
                section: $(this).data('route'),
                positionID: $(this).data('id')
            };
            oOrganization.list.push(obt2);
        });

        if (oOrganization.list.length) {
        	var organization = JSON.stringify(oOrganization);
	        $.ajax({
	            type: "post",
	            url: ctx + "/staff/save_position",
	            data : {data: organization},
	            dataType: "json",
	            success: function(msg) {
	                if (msg.result.toString() === "1") {
	                    location.href = ctx +"/staff/list_position";
	                }else{
	                	alert(msg.message);
	                }
	            }
	        });
        }
    });

    //点击保存——编辑
//    $("#edit_pro_btn").click(function(){
//        submitForm("/staff/update_position");
//    });
    
    
  //点击提交——修改
    $("#edit_pro_btn").click(function(){
    	var oOrganization = $.extend({list:[]},formToObject($("#organization_form")));

        //拼接工作岗位
        $('#position_ready_td .selected_position').each(function(){
            obt2 = {
                lineID: $(this).data('line'),         //分公司岗位 渠道ID
                subComID: $(this).data('company'),       //分公司岗位 省分公司id
                section: $(this).data('route'),
                positionID: $(this).data('id')
            };
            oOrganization.list.push(obt2);
        });

        if (oOrganization.list.length) {
        	var organization = JSON.stringify(oOrganization);
	        $.ajax({
	            type: "post",
	            url: ctx + "/staff/update_position",
	            data : {data: organization},
	            dataType: "json",
	            success: function(msg) {
	            	if (msg.result.toString() === "1") {
	                    location.href = "../list_position";
	                }else{
	                	alert(msg.message);
	                }
	            }
	        });
        }
    });
    

    //选择不同的候选人
    $("#tips_td").on("click", ".candidate", function(){
        var person = {
            name : $(this).data("name"),
            position : $(this).data("position"),
            workNum : $(this).data("worknum"),
            localName : $(this).data('localname')
        };
        fillPerson(person);
        $("#tips_td").empty();
    });
    
    /**
     * 函数：将选中的人填入页面中
     * 输入：
     * 输出：
    **/
    function fillPerson(person) {
        $("#name_num").val(person.name);
        $("#current_position").val(person.position);
        $("#worknum").val(person.workNum);
        var pnum = parseInt(person.localName);
        $('#toJob option').each(function(){
            var val = parseInt($(this).val());
            $(this).removeClass('hide');
            console.log(val,pnum);
            if (val >= pnum) {
                $(this).addClass('hide');
            }
        });
    }

    /**
     * 函数：显示多个查询结果
     * 输入：
     * 输出：
    **/
    function listCandidate(members) {
        var len = members.length;
        var btnsArry = [];
        for (var i = 0; i < len; i++) {
            btnsArry[i] = '<button type="button" class="btn_green candidate" data-name="' + members[i].name + '" data-position="' + members[i].position + '" data-worknum="' + members[i].workNum + '" data-localname="' + members[i].localName + '">' + members[i].name + '，' + members[i].workNum + '，' + members[i].position + '</button>';
        }
        var btnsHtml = btnsArry.join("");
        $("#tips_td").html(btnsHtml);
    }
    
    /**
     * 函数：提交表格
     * 输入：
     * 输出：
    **/
    /*function submitForm(url) {
    	if (checkRequired()) {
        	var promotion = JSON.stringify(formToObject($("#organization_form")));
	        $.ajax({
	            type: "post",
	            url: ctx + url,
	            data : {data: promotion},
	            dataType: "json",
	            success: function(msg) {
	                if (msg.result.toString() === "1") {
	                    location.href = "../list_position";
	                }else{
	                	alert(msg.message);
	                } 
	            }
	        });
        }
    }*/
    
    var companyType = $("#company_type").data("v");
    if(companyType!=null){
    	if (companyType === 1) {
        	$("#company_type").find("option[value='" + 1 + "']").attr("selected",true);
        }else{
        	$("#company_type").find("option[value='" + 2 + "']").attr("selected",true);
        }
    }
});
