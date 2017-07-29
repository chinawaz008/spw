/**
 * 文件：新增车险页面脚本
 * 作者：无名小强
 * 版本：v0.1
 * 版权：民盛集团
 * 日期：2016-5-17
**/
$(function(){

    //初始化数据
    initData();

    //选择投保人是否同车主
    $("#as_the_same").change(function(){
        var flag = $(this).is(":checked");
        if (flag) {
        	$("#as_the_same").val(1);
            $("#bbr").addClass("hide").find('input').val('');
            $("#tbr").addClass("hide").find('input').val('');
            $('.maybe').removeClass('required');
        } else {
        	$("#as_the_same").val(0);
            $("#bbr").removeClass("hide");
            $("#tbr").removeClass("hide");
            $('.maybe').addClass('required');
        }
    });

    //点击提交
    $('#submit_btn').click(function(){
        if (checkRequired2() && check()) {
            saveData();
            openOverlay($(this));
            $("#address").val(localStorage.ms_contact_alladress);
//            applyPay();
             $('#appliant_form').submit();
        }
    });

    
    /**
     * 函数：检查地址是否输入
     * 输入：
     * 输出：
    **/
    function check(){
        var province = $("#deliveryProvince").val();
        var city = $("#deliveryCity").val();
        var district = $("#deliveryDistrict").val();
        var address = $.trim($('#address').val());
        if(!province || !city || !district || !address){
            alert("请完整选择收件地址省市区地址");
            return false;
        } else {
            return true;
        }
    }

    /**
     * 函数：检查必填项
     * 输入：
     * 输出：
    **/
    function checkRequired2() {
        var flag = true;
        $(".required").each(function(){
            var value = $.trim($(this).val());
            if ( value === "") {
                flag = false;
                showTips2($(this), "required");
            } else {
                showTips2($(this), "clear");
            }
        });
        return flag;
    }

    /**
     * 函数：显示提示信息
     * 输入：
     * 输出：
    **/
    function showTips2($input, str) {
        var item = $input.data("check") || '该选项';
        $input.parent().find(".input_tip").remove();
        var tip= "";
        switch (str) {
            case "required":
            tip = '<label class="input_tip"><span>!</span>' + item + '不得为空</label>';
            break;
            case "clear":
            tip = '';
            break;
        }
        $input.after(tip);
    }

    /**
     * 函数：初始化数据
     * 输入：
     * 输出：
    **/
    function initData() {
        $('input[name="runNme"]').val(localStorage.ms_tp_owner_name);
        $('input[name="receiverName"]').val(localStorage.ms_tp_owner_name);
        $('input[name="runIdentNo"]').val(localStorage.ms_tp_owner_idnum);
        $('input[name="orderNo"]').val(localStorage.ms_tp_car_order);
        $('input[name="bxbz"]').val(localStorage.ms_tp_insurance_bxbz);
    }

    /**
     * 函数：保存数据信息
     * 输入：
     * 输出：
    **/
    function saveData(){
        localStorage.ms_owner_name = $('input[name="runNme"]').val();
        localStorage.ms_owner_phone = $('input[name="runTel"]').val();
        localStorage.ms_owner_idnum = $('input[name="runIdentNo"]').val();
        localStorage.ms_owner_email = $('input[name="runEmail"]').val();
        localStorage.ms_app_name = $('input[name="appNme"]').val();
        localStorage.ms_app_phone = $('input[name="appTel"]').val();
        localStorage.ms_app_idnum = $('input[name="appIdentNo"]').val();
        localStorage.ms_app_email = $('input[name="appEmail"]').val();
        localStorage.ms_insrnt_name = $('input[name="insrntNme"]').val();
        localStorage.ms_insrnt_phone = $('input[name="insrntTel"]').val();
        localStorage.ms_insrnt_idnum = $('input[name="insrntIdentNo"]').val();
        localStorage.ms_insrnt_email = $('input[name="insrntEmail"]').val();
        localStorage.ms_contact_name = $('input[name="receiverName"]').val();
        localStorage.ms_contact_phone = $('input[name="receiverPhone"]').val();
        localStorage.ms_contact_alladress = $("#deliveryProvince option:selected").text() + $("#deliveryCity option:selected").text() + $("#deliveryDistrict option:selected").text() + $.trim($('#address').val());
        localStorage.ms_contact_province = $("#deliveryProvince").val();
        localStorage.ms_contact_city = $("#deliveryCity").val();
        localStorage.ms_contact_district = $("#deliveryDistrictd").val();
        localStorage.ms_contact_address = $.trim($('#address').val());
        localStorage.ms_contact_zipcode = $('input[name="appZipcode"]').val();
    }

    /**
     * 函数：申请支付
     * 输入：
     * 输出：
    **/
    function applyPay() {
        //组织数据
        var datas = {
            orderNo: localStorage.ms_car_order,
            realPrm: localStorage.ms_pay_realprm, // 商业险应缴保费
            prm: localStorage.ms_pay_prm,//商业险保费（折前）
            traffRealPrm: localStorage.ms_pay_traffrealprm,//交强险应缴保费
            traffPrm: localStorage.ms_pay_traffprm,// 交强险保费（折前）
            taxRealPrm: localStorage.ms_pay_taxrealprm,//车船税
            taxPrm: localStorage.ms_pay_taxprm,//车船税（折前）
            insrncBgn: localStorage.ms_pay_insrncbgn,//商业险保险起期
            insrncEnd: localStorage.ms_pay_insrncend,//商业险保险止期
            traffBgn: localStorage.ms_pay_traffbgn,//交强险保险起期
            traffEnd: localStorage.ms_pay_traffend,//交强险保险止期
            appnt: localStorage.ms_pay_appnt,//特别约定
            taxAppnt: localStorage.ms_pay_taxappnt,//特别约定（交强险）
            remark: localStorage.ms_pay_remark,//告知

            //关系人 InsuranceInfo info
            appNme: localStorage.ms_app_name || localStorage.ms_owner_name,//投保人姓名
            appIdentNo: localStorage.ms_app_idnum || localStorage.ms_owner_idnum,//投保人证件号
            appTel: localStorage.ms_app_phone || localStorage.ms_owner_phone,//投保人联系电话
            appAddr: localStorage.ms_contact_alladress,//投保人地址
            appEmail: localStorage.ms_app_email || localStorage.ms_owner_email,//投保人邮箱
            appZipcode: localStorage.ms_contact_zipcode,//投保人邮编
            insrntNme: localStorage.ms_insrnt_name || localStorage.ms_owner_name,//被保险人姓名
            insrntIdentNo: localStorage.ms_insrnt_idnum || localStorage.ms_owner_idnum,//被保人证件号
            insrntTel: localStorage.ms_insrnt_phone || localStorage.ms_owner_phone,//被保险人联系电话
            insrntAddr: localStorage.ms_contact_alladress,//被保险人地址
            insrntEmail: localStorage.ms_insrnt_email || localStorage.ms_owner_email,//被保险人邮箱
            insrntZipcode: localStorage.ms_contact_zipcode,//被保险人邮编
            contactName: localStorage.ms_contact_name || localStorage.ms_owner_name,//联系人姓名
            contactTel: localStorage.ms_contact_phone || localStorage.ms_owner_phone,//联系人电话
            contactEmail: localStorage.ms_owner_email,//联系人邮箱
            deliveryProvince: localStorage.ms_contact_province,//配送地址省
            deliveryCity: localStorage.ms_contact_city,//配送地址市
            deliveryDistrict: localStorage.ms_contact_district,//配送地址区
            address: localStorage.ms_contact_address,//配送地址

            //车辆信息
            dptCde: localStorage.ms_city_code, //市区代码
            lcnNo: localStorage.ms_car_num, //车牌号码
            newVhl: localStorage.ms_isnew,//是否新车
            vhlFrm: localStorage.ms_car_frm,//车架号
            engNo: localStorage.ms_car_eng,//发动机号
            fstRegDte: localStorage.ms_car_reg,//车辆初始登记日期
            vehicleCode: localStorage.ms_car_brand,//品牌型号

            //车主信息
            name: localStorage.ms_owner_name,//姓名
            cardNum: localStorage.ms_owner_idnum,//证件号
            phone: localStorage.ms_owner_phone,//手机
            email: localStorage.ms_owner_email,//邮箱
            zipCode: localStorage.ms_contact_zipcode,//邮编
            licenseNumber: localStorage.ms_owner_idnum,//驾驶证号
            getLicenseDate: '2015-05-05',//初次领证日期

            //商业险信息

            bx01: localStorage.ms_tp_insurance_bx01,
            bxn01: localStorage.ms_tp_insurance_bxn01,
            bx02: localStorage.ms_tp_insurance_bx02,
            bxn02: localStorage.ms_tp_insurance_bxn02,
            bx03: localStorage.ms_tp_insurance_bx03,
            bxn03: localStorage.ms_tp_insurance_bxn03,
            bx041: localStorage.ms_tp_insurance_bx041,
            bxn041: localStorage.ms_tp_insurance_bxn041,
            bx044: localStorage.ms_tp_insurance_bx044,
            bxn044: localStorage.ms_tp_insurance_bxn044,
            bx11: localStorage.ms_tp_insurance_bx11,
            bxn11: localStorage.ms_tp_insurance_bxn11,
            bx13: localStorage.ms_tp_insurance_bx13,
            bxn13: localStorage.ms_tp_insurance_bxn13,
            bx23: localStorage.ms_tp_insurance_bx23,
            bxn23: localStorage.ms_tp_insurance_bxn23
        };

        $.ajax({
            url: ctx + "/taiping/createOrder",
            data: datas,
            type: "post",
            dataType: "json",
            success:function(msg){
                // console.log(msg);
                if (msg.result.toString() === '1') {
                    $('#hidden_btn').click();
                    window.open(msg.url);
                } else {
                    alert(msg.message);
                }
            }
        });
    }


});

