/**
 * 文件：新增车险页面脚本
 * 作者：无名小强
 * 版本：v0.1
 * 版权：民盛集团
 * 日期：2016-5-16
**/
$(function(){
    
    //选择是否是过户
    $(".label_radio").click(function(){
        var $span = $(this).find("span");
        if (!$span.hasClass("selected")) {
            var val = $(this).data("v").toString();
            $span.addClass("selected");
            $(this).siblings().find("span").removeClass("selected");
            $(this).parents("tr").find("input").val(val);
            if (val === '10') {
                $('input[name="transferDate"]').addClass('required');
                $('#transfer').show();
            } else {
                $('#transfer').hide();
                $('input[name="transferDate"]').removeClass('required');
            }
        }
    });
    
    //点击不同的输入框，行驶证上显示不同的提示
    $(".fill_tag_input").focus(function(){
        var fill = $(this).data("fill");
        var fillname = ".fill_" + fill;
        $(fillname).addClass("focused");
    }).blur(function(){
        $(".fill_main_tag").removeClass("focused");
    });


    //点击下一步
    $("#next_submit").click(function(){
        if (checkRequired()) {
            saveData();
            openOverlay($(this));
            $("#input_form").submit();
        }
    });

    /**
     * 函数：检查格式
     * 输入：
     * 输出：
    **/
    function checkFormat() {
        //暂时不检查
        return true;
    }

    /**
     * 函数：将有用数据保存在本地
     * 输入：
     * 输出：
    **/
    function saveData() {
        var oForm = formToObject($('#input_form'));
        localStorage.ms_tp_city_name = oForm.cityName;
        localStorage.ms_tp_city_code = oForm.cityCode;
        localStorage.ms_tp_car_frm = oForm.frameNo;
        localStorage.ms_tp_car_eng = oForm.engineNo;
        localStorage.ms_tp_car_reg = oForm.SingeinDate;
        localStorage.ms_tp_car_brand = oForm.modelCode;
        localStorage.ms_tp_owner_name = oForm.name;
        localStorage.ms_tp_owner_idnum = oForm.identifyNumber;
        localStorage.ms_tp_owner_changed = oForm.chgOwnerFlag;
        localStorage.ms_tp_isnew = oForm.isnew.toString();
        if (oForm.isnew.toString() === '1') {
            localStorage.ms_tp_car_num = '暂未上牌';
        } else {
            localStorage.ms_tp_car_num = oForm.licenseNo;
        }
    }

});
