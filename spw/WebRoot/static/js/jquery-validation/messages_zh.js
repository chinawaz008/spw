/*
 * Translated default messages for the jQuery validation plugin.
 * Locale: ZH (Chinese, 中文 (Zhōngwén), 汉语, 漢語)
 */
(function($) {
    $.validator.addMethod("mobile", function(value, element) {
        return this.optional(element) || /^1[3|4|5|8][0-9]\d{8}$/.test(value);
    }, "Please specify a valid mobile number");
    
    $.validator.addMethod("email", function(value, element) {
    	return this.optional(element) || /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/.test(value);
    }, "Please specify a valid email");
    
    $.validator.addMethod("tencentqq", function(value, element) {
        return this.optional(element) || /^[1-9]\d{4,8}$/.test(value);
    }, "Please specify a valid tencent-qq number");
    
    $.validator.addMethod("phone", function(value, element) {
        return this.optional(element) || /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/.test(value);
    }, "Please specify a valid phone number");
    
    $.validator.addMethod("integer", function(value, element) {
        return this.optional(element) || /^[0-9]*[1-9][0-9]*$/.test(value);
    }, "Please specify a valid integer");
    
    $.validator.addMethod("float2", function(value, element) {
        return this.optional(element) || /^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$/.test(value);
    }, "Please specify a valid float2");
    
    $.validator.addMethod("float", function(value, element) {
        return this.optional(element) || /^(([1-9]\d*)|0)(\.(\d){1,2})?$/.test(value);
    }, "Please specify a valid float");
    
    $.validator.addMethod("mobilePhone", function(value, element) {
        return this.optional(element) || /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/.test(value);
    }, "Please specify a valid mobilePhone");
    
    $.validator.addMethod("range", function(value, element) {
        return this.optional(element) || /^(0\.(?!0+$)\d{1,2}|1(\.0{1,2})?)$/.test(value);
    }, "Please specify a valid range");
    
    $.validator.addMethod("idCard", function(value, element) {
    	return this.optional(element) || /(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(value);
    }, "Please specify a valid idCard");
    
    $.validator.addMethod("bankCard", function(value, element) {
    	return this.optional(element) || /^\d{16}|\d{19}$/.test(value);
    }, "Please specify a valid bankCard");
    
    $.validator.addMethod("postcode",function(value,element){
    	return this.optional(element) || /^[1-9]\d{5}$/.test(value);
    },"Please specify a valid postcode");
    
    $.validator.addMethod("mainName",function(value,element){
    	return this.optional(element) || /^[\u4e00-\u9fa5]{2,10}$/.test(value);
    },"Please specify a valid mainName");
    
    $.validator.addMethod("username",function(value,element){
    	return this.optional(element) || /^[\w]{6,20}$/.test(value);
    },"Please specify a valid username");
    $.extend($.validator.messages, {
        required: "该项为必填项",
    	//required: "",
        remote: "请修正该字段",
        email: "请输入正确格式的电子邮件",
        url: "请输入合法的网址",
        date: "请输入合法的日期",
        dateISO: "请输入合法的日期 (ISO).",
        number: "请输入合法的数字",
        digits: "只能输入整数",
        creditcard: "请输入合法的信用卡号",
        equalTo: "请再次输入相同的值",
        accept: "请输入拥有合法后缀名的字符串",
        maxlength: $.validator.format("请输入一个长度最多是 {0} 的字符串"),
        minlength: $.validator.format("请输入一个长度最少是 {0} 的字符串"),
        rangelength: $.validator.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
        range: $.validator.format("请输入0~1之间的数值,并且只有2位小数"),
        max: $.validator.format("请输入一个最大为 {0} 的值"),
        min: $.validator.format("请输入一个最小为 {0} 的值"),
        mobile: "手机号码格式不正确",
        tencentqq: "QQ号格式不正确",
        phone:"电话号码格式不正确",
        integer: "请输入大于0正整数",
        float: "请输入最多2位小数的数字",
        mobilePhone: "请输入正确的电话号码或手机号码",
        float2: "请输入最多2位小数,并且大于0的数字",
        idCard: "请输入有效的身份证号",
        bankCard: "请输入有效的银行卡号",
        postcode:"请输入有效的邮编",
        mainName:"请输入正确的姓名",
        username:"6-20位字母、数字或'_'组成"
    });
}(jQuery));