/**
 * 文件：新增车险页面脚本
 * 作者：无名小强
 * 版本：v0.1
 * 版权：民盛集团
 * 日期：2016-5-16
**/
$(function(){
    
    //默认保险起期为第二天0时
    initDate();

    //点击主险项目，右侧显示对应的项目细节
    $(".insurance_tr").click(function(){
        var $trs = $(".insurance_tr");
        $trs.removeClass("selected");
        $(this).addClass("selected");
        var index = $trs.index(this);
        var $details = $(".insurance_detail");
        $details.removeClass("selected");
        $details.eq(index).addClass("selected");
    });

    //在每个项目里选择不同的投保金额
    $(".insurance_detail").on("click", ".span_radio", function(){
        var bx = $(this).parents('.insurance_detail').data('bx');
        var $radios = $(this).parents("tbody").find(".span_radio");
        $radios.removeClass("selected");
        $(this).addClass("selected");
        var text = $(this).parent().text();
        var val = $(this).data("v").toString();
        $(".insurance_tr.selected td:last").text(text);
        $('.bx_item[name="' + bx +'"]').val(val);
        var $checkbox = $(this).parents("tbody").find(".span_checkbox");
        var nbx = $checkbox.data('bx');
        var id = $checkbox.data("i");
        if (val === "-1") {
            selectNonDeductible(id, nbx, false);
            $(".span_checkbox.i" + id).addClass("cannot");
        } else {
            selectNonDeductible(id, nbx, true);
            $(".span_checkbox.i" + id).removeClass("cannot");
        }
    });

    //在每个项目里勾选是否不计免赔
    $(".insurance_detail").on("click", ".span_checkbox", function(){
        var isCan = !$(this).hasClass("cannot");
        if (isCan) {
            var flag = $(this).hasClass("selected");
            var id = $(this).data("i");
            var bx = $(this).data('bx');
            selectNonDeductible(id, bx ,!flag);
        }
    });

    //选择是否购买交强险
    $("#selecte_compulsory").click(function(){
        var flag = $(this).find("span").hasClass("selected");
        if (flag) {
            $(this).find("span").removeClass("selected");
            $('input[name="bxbz"]').val('-1');
        } else {
            $(this).find("span").addClass("selected");
            $('input[name="bxbz"]').val('0');
        }
    });

    //点击提交
    $("#submit_insurance").click(function(){    
        saveData();
        openOverlay($(this));
        $('#submit_form').submit();
    });

    /**
     * 函数：不计免赔的切换
     * 输入：
     * 输出：
    **/
    function selectNonDeductible(id, bx, isSelected) {
        if (isSelected) {
            $(".non_deductible span").eq(id).addClass("selected");
            $(".bx_item[name='" + bx + "']").val("0");
            $(".span_checkbox.i" + id).addClass("selected");
        } else {
            $(".non_deductible span").eq(id).removeClass("selected");
            $(".bx_item[name='" + bx + "']").val("-1");
            $(".span_checkbox.i" + id).removeClass("selected");
        }
    }

    /**
     * 函数：默认保险起期
     * 输入：
     * 输出：
    **/
    function initDate() {
        var dd = new Date();
        dd.setDate(dd.getDate() + 1);//获取1天后的日期
        var y = dd.getFullYear();
        var m = dd.getMonth() + 1;//获取当前月份的日期
        var d = dd.getDate();
        $('.ms_datepicker').val(y + "-" + m + "-"+ d);
        //$('input[name="orderNo"]').val(localStorage.ms_car_order);
    }

    /**
     * 函数：保存数据信息
     * 输入：
     * 输出：
    **/
    function saveData(){
        //交强险
        localStorage.ms_tp_insurance_bxbz = $('input[name="bxbz"]').val();

        //商业险
        localStorage.ms_tp_insurance_bx01 = $('input[name="bx01"]').val();
        localStorage.ms_tp_insurance_bxn01 = $('input[name="bxn01"]').val();
        localStorage.ms_tp_insurance_bx02 = $('input[name="bx02"]').val();
        localStorage.ms_tp_insurance_bxn02 = $('input[name="bxn02"]').val();
        localStorage.ms_tp_insurance_bx03 = $('input[name="bx03"]').val();
        localStorage.ms_tp_insurance_bxn03 = $('input[name="bxn03"]').val();
        localStorage.ms_tp_insurance_bx041 = $('input[name="bx041"]').val();
        localStorage.ms_tp_insurance_bxn041 = $('input[name="bxn041"]').val();
        localStorage.ms_tp_insurance_bx044 = $('input[name="bx044"]').val();
        localStorage.ms_tp_insurance_bxn044 = $('input[name="bxn044"]').val();
        localStorage.ms_tp_insurance_bx11 = $('input[name="bx11"]').val();
        localStorage.ms_tp_insurance_bxn11 = $('input[name="bxn11"]').val();
        localStorage.ms_tp_insurance_bx13 = $('input[name="bx13"]').val();
        localStorage.ms_tp_insurance_bxn13 = $('input[name="bxn13"]').val();
        localStorage.ms_tp_insurance_bx23 = $('input[name="bx23"]').val();
        localStorage.ms_tp_insurance_bxn23 = $('input[name="bxn23"]').val();
        localStorage.ms_tp_car_order = $('input[name="orderNo"]').val().toString();
    }
});
