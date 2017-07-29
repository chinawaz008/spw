/**
 * 文件：车型选择页面脚本
 * 作者：无名小强
 * 版本：v0.1
 * 版权：民盛集团
 * 日期：2016-6-6
**/
$(function(){
    
    //点击提交按钮
    $("#submit_style").click(function(){
        var $checked = $('input:radio[name="car_type"]:checked');
        var style= $checked.val();
        if (style) {
            var $form = $("#style_form");
            $("#style_form input.style_data").each(function(){
                var key = $(this).attr('name').toLowerCase();
                $(this).val($checked.data(key));
            });           
            //saveData();
            openOverlay($(this));
            $form.submit();
        } else {
            alert('请选择车型！');
            return false;
        }
    });

    /**
     * 函数：保存数据
     * 输入：
     * 输出：
    **/
    function saveData() {
        localStorage.ms_car_order = $('input[name="orderNo"]').val().toString();
    }
});
