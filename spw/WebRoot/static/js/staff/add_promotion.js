/**
 * 文件：新增晋升人员页面脚本
 * 作者：无名小强
 * 版本：v0.1
 * 版权：民盛集团
 * 日期：2016-4-18
**/
$(function(){
	
    //所有select在页面加载后显示值
    $("select").each(function(){
        var value = $(this).data("v");
        $(this).find("option[value='" + value + "']").attr("selected",true);
    });
    
    //点击搜索
    $("#search_name_num").click(function(){
        $("#tips_td").empty();
        var value = $.trim($("#name_num").val());
        if(value){
            $.ajax({
                type: "post",
                url: ctx + "/staff/getFrontPerson",
                data : {data: value},
                dataType: "json",
                success: function(msg) {
                    if (msg.result.toString() === "1") {
                        if (msg.members.length === 1) {
                            fillPerson(msg.members[0]);
                        } else {
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
    $("#add_pro_btn").click(function(){
    	if (checkRequired()) {
        	var promotion = JSON.stringify(formToObject($("#promotion_form")));
	        $.ajax({
	            type: "post",
	            url: ctx + "/staff/save_promotion",
	            data : {data: promotion},
	            dataType: "json",
	            success: function(msg) {
	                if (msg.result.toString() === "1") {
	                	parent.closeBox();
	                	parent.freshList();
	                }else{
	                	alert(msg.message);
	                } 
	            }
	        });
        }
    });

    //点击保存——编辑
    $("#edit_pro_btn").click(function(){
        submitForm("/staff/update_promotion");
    });

    //选择不同的候选人
    $("#tips_td").on("click", ".candidate", function(){
        var person = {
            name : $(this).data("name"),
            positionName : $(this).data("pname"),
            workNum : $(this).data("worknum"),
            position : $(this).data('position')
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
    	var selDom = $("#toJob");
    	selDom.empty();
    	selDom.append("<option value='"+person.position+"'>分部经理</option>");
        $("#name_num").val(person.name);
        $("#current_position").val(person.positionName);
        $("#worknum").val(person.workNum);
        
//        var pnum = parseInt(person.localName);
//        $('#toJob option').each(function(){
//            var val = parseInt($(this).val());
//            $(this).removeClass('hide');
//            if (val >= pnum) {
//                $(this).addClass('hide');
//            }
//        });
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
            btnsArry[i] = '<button type="button" class="btn_green candidate" data-name="' + members[i].name + '" data-pname="' + members[i].positionName + '" data-worknum="' + members[i].workNum + '" data-position="' + members[i].position + '">' + members[i].name + '，' + members[i].workNum + '，' + members[i].positionName + '</button>';
        }
        var btnsHtml = btnsArry.join("");
        $("#tips_td").html(btnsHtml);
    }
    
    /**
     * 函数：提交表格
     * 输入：
     * 输出：
    **/
    function submitForm(url) {
    	if (checkRequired()) {
        	var promotion = JSON.stringify(formToObject($("#promotion_form")));
	        $.ajax({
	            type: "post",
	            url: ctx + url,
	            data : {data: promotion},
	            dataType: "json",
	            success: function(msg) {
	                if (msg.result.toString() === "1") {
	                	parent.closeBox();
	                	parent.freshList();
	                }else{
	                	alert(msg.message);
	                } 
	            }
	        });
        }
    }
});
