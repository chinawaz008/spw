/**
 * 文件：新增人员页面脚本
 * 作者：无名小强
 * 版本：v0.2
 * 版权：民盛集团
 * 日期：2016-3-4
**/
$(function(){
	 $("#fenbu").show();
    //点击提交
    $("#add_submit_btn").click(function(){
        if (checkName() && checkRequired4() && checkFormat() && confirm("您确认提交？")) {
            submitStaff("/staff/save",{});
        }
    });
    
    //合同类型改变
    $("#contractType").change(function(){
    	var v = $("#contractType").val();
    	var contractNum = $('#contractNum').data('v2'); //$("#contractNum0").val() ;
    	var contractNum1 = $('#contractNum').data('v'); //$("#contractNum1").val();
    	if(v=="0"){
    		$("#contractNum").val(contractNum);
            $("#contractSpan").text(contractNum);
    	}else{
    		$("#contractNum").val(contractNum1);
            $("#contractSpan").text(contractNum1);
    	}
    })
    
    //点击头像时，转化为点击上传
    // $("#staff_form .choose_pic").click(function(){
    //     $(this).parent().find(".ms_upload_file").click();
    // });

    //选择不同的从业时间
    $("#insurance_industry").change(function(){
        var time = $(this).find("option:selected").val();
        if (time !== "0") {
            $(".check_for_the_same").css("visibility","visible");
            $(".last_the_same").show().find("input[name='isCommonJob4']").val("1");
        } else {
            $(".check_for_the_same").css("visibility","hidden").find(".belong_to_same").attr("checked", false).next("input.isCommonJobInput").val("0");
            $(".last_the_same").hide().find("input").val("");
        }
    });

    //点击“属于同业”的小按钮
    $(".belong_to_same").change(function(){
        var $hidden = $(this).parent().find("input.isCommonJobInput");
        var checked = $(".belong_to_same:checked").length;
        if (checked) {
            $(".last_the_same").hide().find("input").val("");

        } else {
            $(".last_the_same").show().find("input[name='isCommonJob4']").val("1");
        }
        if($(this).is(":checked")) {
            $hidden.val("1");
        } else {
            $hidden.val("0");
        }
    });

    //由条线获取省分公司
    $("#line_type, #branch_company_id, #county_franchisees_id").change(function(){
        var id = $(this).find("option:selected").val();
        var lineType = $("#line_type").val();
        var elId = $(this).attr("id");
        var $target, urlTail, no, name, sin;
        switch (elId){
            case "line_type":
                $target = $("#branch_company_id");
                urlTail = "company";
                no = "id";
                name = "compName";
                sin = "省级机构";
                break;
            case "branch_company_id" :
                $target = $("#county_franchisees_id");
                urlTail = "dxq";
                no = "id";
                name = "name";
                sin = "督训区";
                break;
            case "county_franchisees_id" :
                $target = $("#fenbu");
                urlTail = "fenbu";
                no = "id";
                name = "name";
                sin = "分部";
                getPositions(lineType,1);
                $("#add_fenbu").click();
                break;
            default :
                break;

        }

        $.ajax({
            type: "post",
            url: ctx + "/organization/" + urlTail,
            data : {id: id},
            dataType: "json",
            success: function(msg) {
                //console.log(msg);
                var optionArr = [];
                for (var i = 0, len = msg.length; i < len; i++) {
                    optionArr[i] = '<option value="' + msg[i][no] + '">' + msg[i][name] + '</option>';
                }
                var htmlStr = '<option value="">' + '请选择' + sin + '</option>' + optionArr.join("");
                $target.html(htmlStr);
            }
        });

        $("#delete_fenbu").click();
    });

    //点击【分部+】,选择不同分部获得职级下拉框里的列表，显示分部名
//    $("#add_fenbu").click(function(){
//    	var lineType = $("#line_type").val();
//        emptyNewDate();
//        initSpecialTraining();
//        getPositions(lineType,1);
//        $("#fenbu").show();
//      $("#delete_fenbu").show();
//      $(".has_fenbu").hide();
//    });

//    //点击【撤销】，
//    $("#delete_fenbu").click(function(){
//    	var lineType = $("#line_type").val();
//        emptyNewDate();
//        initSpecialTraining();
//        getPositions(lineType,0);
//        $("#fenbu").hide();
//        $(this).hide();
//        $(".has_fenbu").show();
//    });

    //选择【职级后】，产生有无分部的列表
//    $("#position").change(function(){
//        $.ajax({
//            type: "post",
//            url: ctx + "/organization/no_manager/fenbu",
//            data: {},
//            dataType: "json",
//            success: function(msg){
//                //console.log(msg);
//                var optionArr = [];
//                for (var i = 0, len = msg.length; i < len; i++) {
//                    optionArr[i] = '<option value="' + msg[i].id + '">' + msg[i].name + '</option>';
//                }
//                var htmlStr = '<option value="">无</option>' + optionArr.join("");
//                $("#ownered_fenbu").html(htmlStr);
//            }
//        });
//        var val = $(this).val();
//        if (val.toString() === "3") {
//            $("#special_raining").show();
//        } else {
//            initSpecialTraining();
//        }
//        emptyNewDate();
//    });

    //选择是否是新筹
    $("#is_new").change(function(){
        var val = $(this).val();
        if (val.toString() === "1") {
            $("#new_date").show();
        } else {
            emptyNewDate();
        }
    });

    //通过身份证号找人
    $(".query_id_card").on("input propertychange",function(){
        var $that = $(this);
        var value = $that.val(); 
        var len = value.length;
        var $target = $that.parent().find("input[type='hidden']");
        $target.val(value);
        if (len === 18) {
            $.ajax({
                type: "post",
                url: ctx + "/staff/getByIdCard",
                data: {idCard:$(this).val()},
                dataType: "json",
                success: function(msg) {
                    if (msg) {
                        $that.val(msg.name);
                        $target.val(msg.id);
                    } else {
                        $that.val("未找到，请仔细核对身份信息");
                        $target.val("");
                    }

                }
            });
        }
    });

    //添加亲属
    $("#add_one_relative").click(function(){
        $(".add_relatives:hidden").eq(0).show();
    });

    //缩略图放大功能
    $(".ms_thumbnail").mouseover(function(){
        var src = $(this).find("img").attr("src");
        var htmlStr = '<div class="enlarged"><img src="' + src + '"></div>';
        $(this).append(htmlStr);
    }).mouseout(function(){
        $(".enlarged").remove();
    });

    //点击缩略图，打开新的页面用于显示图片
    $('.ms_thumbnail, .choose_pic').click(function() {
        var src = $(this).find("img").attr("src");
        window.open(src)
    })

    
    //点击上传图片的按钮
    var upload_btn;
    $('.ms_upload_file_btn').click(function(){
        $("#replace_input").click();
        upload_btn = this;
    });
    $("#replace_input").change(function(){
        if($(this).val() !== '') {
            $("#submit_form").submit();

            $("#exec_target").one("load", function(){
            var data = $(window.frames.exec_target.document.body).find("#imgurl").data("url");
            if(data !== null){
                $(upload_btn).parents("tr").find(".ms_feedback").attr("src",data);
                $(upload_btn).parents("tr").find("input[type='hidden']").val(data);
            }
        });
        }
    })



    //身份证失去焦点即完成生日和性别的默认值
    // $('#id_card').blur(function(){
    //     var val = $.trim($(this).val());
    //     if (val.length === 18) {
    //         var date = val.substring(6,10) + '-' + val.substring(10,12) + '-' + val.substring(12,14);
    //         $('#birth_day').val(date);
    //         var sexNum = (parseInt(val.substring(16, 17)) + 1) % 2;
    //         $('#sex_select option').attr('selected', false);
    //         $('#sex_select').find('option[value=' + sexNum + ']').attr('selected', true);
    //         $('#sex_select').change();
    //     }
    // });

    //银行卡号失去焦点即完成银行名称查询，接口被停止了
    // $('#bank_card_num').blur(function(){
    //     var val = $.trim($(this).val());
    //     $.ajax({
    //         type: "get",
    //         url: "http://apis.baidu.com/datatiny/cardinfo/cardinfo",
    //         headers: {'apikey':'7b1d0c59b875311e6a3619cb2a95322f'},
    //         data: {cardnum:val},
    //         dataType: "json",
    //         success: function(msg) {
    //             if(msg.data ==undefined) {
    //             	//暂不做验证
    //             	return;
    //             }
    //             var bank;
    //             if (msg.status) {
    //                 bank = msg.data.bankname;
    //             } else {
    //                 bank = msg.data.mess;
    //             }
    //             $('#bank_name').val(bank);
    //         }
    //     });
    // });

    /**
     * 函数：获取职级下拉框里的列表
     * 输入：type();
     * 输出：
    **/
    function getPositions(lineType,type) {
        $.ajax({
            type: "post",
            url: ctx + "/staff/getJobs",
            data: {lineType:lineType,type:type},
            dataType: "json",
            success: function(msg) {
                var optionArr = [];
                for (var i = 0, len = msg.length; i < len; i++) {
                    optionArr[i] = '<option value="' + msg[i].id + '">' + msg[i].name + '</option>';
                }
                var htmlStr = '<option value="">请选择职级</option>' + optionArr.join("");
                $("#position").html(htmlStr);
                $("#orgType").val(msg[0].departmentId);
            }
        });
    }
    

    /**
     * 函数：清空筹备期间的两个日期
     * 输入：
     * 输出：
    **/
    function emptyNewDate() {
        $("#new_date input").val("");
        $("#new_date").hide();
    }

    /**
     * 函数：初始化是否是专训
     * 输入：
     * 输出：
    **/
    function initSpecialTraining() {
        $('#is_special_training option[value="0"]').attr("selected", true);
        $('#is_new option[value="0"]').attr("selected", true);
        $('#is_special_training').val('');
        $('#is_new').val('');
        $("#special_raining").hide();
    }
});


/**
 * 函数：从页面获取整理数据，提交给后台
 * 输入：toUrl(提交地址),datas(额外的数据)
 * 输出：
**/
function submitStaff(toUrl,datas) {
    var staffForm = formToObject($("#staff_form"));
    var oStaff = $.extend(staffForm,{list: []});
    var organization = JSON.stringify(formToObject($("#organization_form")));
    var infoFiles = JSON.stringify(formToObject($("#info_files_form")));
    var formWorkExp = formToObject($("#work_exp_form"));
    var formRelationship = formToObject($("#relationship_form"));
    var oWorkExp = {insurancePeriod:formWorkExp.insurancePeriod, exps:[]};
    var cardId, obt, accountObj, cardIdArr = [], bondsManArr = [];
    
    //拼接打款账户
    for (var l = 1; l < 4; l++) {
        accountObj = {
            payTime: staffForm['payTime' + l],
            payMoney: staffForm['payMoney' + l],
            account: staffForm['account' + l]
        };
    	delete staffForm['payTime' + l];
    	delete staffForm['payMoney' + l];
    	delete staffForm['account' + l];
    	
        oStaff.list.push(accountObj);
    }

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
    
    //拼接亲属
    for (var j = 1; j < 6; j++) {
        cardId = formRelationship["relativesWorking" + j];
        if (cardId) {
            delete formRelationship["relativesWorking" + j];
            cardIdArr.push(cardId);
        }
    }

    //拼接担保人
    for (var k = 1; k < 3; k++) {
        cardId = formRelationship["bondsman" + k];
        if (cardId) {
            delete formRelationship["bondsman" + k];
            bondsManArr.push(cardId);
        }
    }
    var oRelationship = (cardIdArr.length > 0) ? $.extend(formRelationship,{relativesWorking: cardIdArr.join("@")}) : formRelationship;
    oRelationship = (bondsManArr.length > 0) ? $.extend(formRelationship,{bondsman: bondsManArr.join("@")}) : formRelationship;
    var relationship = JSON.stringify(oRelationship);
    var workExp = JSON.stringify(oWorkExp);
    var staff = JSON.stringify(oStaff);
    datas = $.extend(datas,{staff:staff, workExp:workExp, organization:organization,relationship:relationship,infoFiles:infoFiles});

    $.ajax({
        type: "post",
        url: ctx + toUrl,
        data: datas,
        dataType: "json",
        success: function(msg) {
        	alert(msg.message);
            if(msg.result == 1){
            	alert('姓名：'+msg.name+'\r\n职位：'+msg.positon+'\r\n岗位号：'+msg.worknum+'\r\n合同编号：'+msg.contractNum);
                parent.closeBox();
                parent.freshList()
            } else {
                alert(msg.message)
            }
        }
    });
    
}

 function checkName() {
 	var username = $('#username').val();
 	var id = $("#edit_submit_btn").data("i");
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
