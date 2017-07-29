/**
 * 文件：中华新增车险页面公共脚本
 * 作者：无名小强
 * 版本：v0.1
 * 版权：民盛集团
 * 日期：2016-6-12
**/
$(function(){

    //错误信息会隐藏在message里，加载完页面即读取是否有错误信息
    var message = $('body').data('m');
    if (message) {
        alert(message);
    }

    //车牌照，输入变大写
    $('.upper').keyup(function(){
        var str = $(this).val();
        $(this).val(str.toUpperCase());
    });

    //
    $(window).unload(function(){
        hideOverlay();
    });

});

/**
 * 函数：打开遮罩
 * 输入：$btn(触发按钮)
 * 输出：
**/
function openOverlay($btn) {
    $btn.text('请稍后···');
    showOverlay('img');
}

/**
 * 函数：表格序列化
 * 输入：$form(jQuery对象，将目标表格里非空的数据转变为js对象)
 * 输出：js对象
**/
function formToObject($form) {
    var serializeObj={}; 
    $($form.serializeArray()).each(function(){  
        if ($.trim(this.value) !== "") {
            serializeObj[this.name]=this.value;   
        }
    });  
    return serializeObj;  
}

/**
 * 函数：检查必填项
 * 输入：
 * 输出：
**/
function checkRequired() {
    var flag = true;
    $(".required").each(function(){
        var value = $.trim($(this).val());
        showTips($(this), "clear");
        if ( value === "") {
            flag = false;
            showTips($(this), "required");
        } 
    });
    return flag;
}

/**
 * 函数：显示提示信息
 * 输入：
 * 输出：
**/
function showTips($input, str) {
    var $tr = $input.parents("tr");
    var $td = $tr.find("td:last");
    var item = $input.data("check") || '该选项';
    var tip= "";
    switch (str) {
        case "required":
        tip = '<label class="label_tips"><span>!</span>' + item + '不得为空</label>';
        break;
        case "clear":
        tip = '';
        break;
    }
    $td.html(tip);
}