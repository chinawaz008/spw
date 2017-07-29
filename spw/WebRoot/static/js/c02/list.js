/**
 * 文件：新增车险页面脚本
 * 作者：无名小强
 * 版本：v0.1
 * 版权：民盛集团
 * 日期：2016-5-17
**/
$(function(){
    
    //点击提交
    $('.submit_btn').click(function(){
        openOverlay($(this));
        $('#submit_form').submit();
    });
    
});
