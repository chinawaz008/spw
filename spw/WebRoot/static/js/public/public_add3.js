/**
 * 文件：新增类页面公共脚本，检查必填项，检查数据格式
 * 作者：无名小强
 * 版本：v0.1
 * 版权：民盛集团
 * 日期：2016-3-4
**/

/**
 * 函数：检查所有必填项
 * 输入：
 * 输出：
**/
function checkRequired() {
    var flag = true;
    $(".table_window").each(function(index,element){
        var ok = true;
        $(element).find(".required").each(function(){
            var value = $.trim($(this).val());
            if (value === "") {
                showTips($(this), "required");
                flag = false;
                ok = false;
            } else {
                showTips($(this), "clear");
            }
        });
        if (ok) {
            $(".table_lay .sub_title").eq(index).removeClass("wrong");
        } else {
            $(".table_lay .sub_title").eq(index).addClass("wrong");
        }
    });
    return flag;
}

/**
 * 函数：检查所有必填项，专为新增人员
 * 输入：
 * 输出：
**/
function checkRequired4() {
    var flag = true;
    $(".tab_body").each(function(index,element){
        var ok = true;
        $(element).find(".required").each(function(){
            var value = $.trim($(this).val());
            if (value === "") {
                showTips($(this), "required");
                flag = false;
                ok = false;
            } else {
                showTips($(this), "clear");
            }
        });
        if (ok) {
            $(".tab_controller li").eq(index).removeClass("wrong");
        } else {
            $(".tab_controller li").eq(index).addClass("wrong");
        }
    });
    return flag;
}

/**
 * 函数：检查格式错误
 * 输入：
 * 输出：
**/
function checkFormat() {
    var flag = true;
    $(".tab_body").each(function(index,element){
        var ok = true;
        $(element).find(".formated").each(function(){
            var value = $.trim($(this).val());
            if (value !== "") {
                var type = $(this).data("c");
                if (!checkEachFormat(value, type)) {
                    showTips($(this), "formated");
                    flag = false;
                    ok = false;
                } else {
                    showTips($(this), "clear");
                }
            }
        });
        if (ok) {
            $(".tab_controller li").eq(index).removeClass("wrong");
        } else {
            $(".tab_controller li").eq(index).addClass("wrong");
        }
    });
    return flag;
}

/**
 * 函数：检查每一种数据的格式
 * 输入：
 * 输出：
**/
function checkEachFormat(value, type) {
    var reg;
    switch (type){
        case "姓名":
            reg = /^[\u4e00-\u9fa5]{2,10}$/;
            break;
        case "身份证号码":
            reg = /(^[1-9]\d{5}[1-2]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}(\d|X|x)$)|(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)/;
            break;
        case "手机号码":
            reg = /^(13[0-9]|15[0-9]|17[0-9]|18[0-9]|14[57])[0-9]{8}$/;
            break;
        case "电子邮箱":
            reg = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;
            break;
        case "邮编":
            reg = /^\d{6}$/;
            break;
        case "地址":
            reg = /^(?=.*?[\u4E00-\u9FA5])[\dA-Za-z\u4E00-\u9FA5]+/;
            break;
        case "关系":
            reg = /^[\u4E00-\u9FA5]+$/;
            break;
        case "百分比":
            reg = /^(\d{1,2}(\.\d{1,3})?|100)$/;
            break;
        case "顺序":
            reg = /^[1-9]([0-9]+)?$/;
            break;
        case "名称":
            reg = /^[\u4e00-\u9fa5]{2,15}$/;
            break;
        case "金额":
            reg = /^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/;
            break;
        case "正整数":
        	reg = /^\d+$/;
        	break;
        case "银行卡号":
            reg = /^(\d{16}|\d{19})$/;
            break;

        default:
            reg = /^\w+$/;
    }
    return reg.test(value);
}

/**
 * 函数：显示每一个input的提示信息
 * 输入：
 * 输出：
**/
function showTips($input, str) {
    var c = $input.data("c") || '该选项';
    var index = $input.parents(".tab_body").index();
    var label = "";
    $input.parent().find(".input_tip").remove();
    switch (str) {
        case "required":
            label = '<label class="input_tip">' +  c + "不得为空！" + '</label>';
            break;
        case "clear":
            label = "";
            break;
        case "formated":
            label = '<label class="input_tip">' +  c + "格式不正确！" + '</label>';
            break;
        default:
            break;
    }
    $input.after(label);
}

$(function(){

    //右侧tab页切换
    $(".tab_controller").on("click","li",function() {
        var index = $(this).index();
        $(this).addClass("active").siblings().removeClass("active");
        $(".tab_bodies .tab_body").eq(index).removeClass("hide").siblings().addClass("hide");
    });

    //input输入时，清除非空检查和格式检查的提示信息
    $(".tab_body").find('.required, .formated').click(function(){
        $(this).siblings('.input_tip').remove();
    });
});

