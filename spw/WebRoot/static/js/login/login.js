/**
 * 文件：登录页脚本
 * 作者：无名小强
 * 版本：v0.1
 * 版权：民盛集团
 * 日期：2016-10-11
**/
//调用父页面的去登录页面
if (parent.goLogin) {
    parent.goLogin(); 
}
$(function(){

    //点击提交按钮
    $("#btn").click(function(){
        var worknum = $.trim($("#worknum").val()).toString();
        var password = $.trim($("#password").val()).toString();
        var ctx = $(this).data('ctx');
        if(worknum === ""){
            alert("请输入岗位号"); 
            $("#worknum").focus();
            return false;
        }
        if(password === ""){
            alert("请输入密码"); 
            $("#password").focus();
            return false;
        }
        $(this).text("正在登录...");
        $.ajax({
            url: ctx + "/login",
            data: {"username":worknum, "password":password},
            type: "post",
            dataType: "json",
            success: function(msg){
                if (msg.result=="1"){
                    window.location.href = ctx;
                } else {
                    alert(msg.message);
                    $("#btn").text("登 录");
                }
            }
        });
    });

    //按回车键提交
    $(document).keyup(function(event){
        if(event.keyCode ==13){
            $("#btn").click();
        }
    });
});