/**
 * 文件：新增车险页面脚本
 * 作者：无名小强
 * 版本：v0.1
 * 版权：民盛集团
 * 日期：2016-5-16
**/
$(function(){
    
    //点击“我要报价”按钮
    $("#go_to_form").click(function(){
        if (checkRequired()) {         
            saveData();
            openOverlay($(this));
            $("#input_form").submit();
        }

    });

    //选择是否新车
    $("#label_check").click(function(){
        $span = $(this).find("span");
        if ($span.hasClass("selected")) {
            $("#ms_car_num_input").removeAttr("readonly");
            $span.removeClass("selected");
            $("#ms_car_num_input").addClass("required").val('');
            $("#isnew").val("0");
        } else {
            var $num = $("#ms_car_num_input");
            var str = $num.data('n');
            $num.attr("readonly","readonly").removeClass("required").val('暂未上牌'); 
            $span.addClass("selected");
            $("#isnew").val("1");
            showTips($(this), "clear");
        }
    });

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
     * 函数：检查格式
     * 输入：
     * 输出：
    **/
    function checkFormat() {

        var re=/^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$/;

        return true;
    }

    /**
     * 函数：保存有用数据到本地
     * 输入：
     * 输出：
    **/
    function saveData() {
        var oForm = formToObject($('#input_form'));
        localStorage.ms_tp_city_name = oForm.cityName;
        localStorage.ms_tp_city_code = oForm.cityCode;
        localStorage.ms_tp_owner_idnum = oForm.identifyNumber;
        localStorage.ms_tp_isnew = oForm.isnew.toString();
        if (oForm.isnew.toString() === '1') {
            localStorage.ms_tp_car_num = '暂未上牌';
        } else {
            localStorage.ms_tp_car_num = oForm.licenseNo;
        }
    }


});