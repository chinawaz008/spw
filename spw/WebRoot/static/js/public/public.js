/**
 * 文件：公共脚本，工程名、表格序列化、底部遮罩、左侧菜单、退出、省市县
 * 作者：无名小强
 * 版本：v0.1
 * 版权：民盛集团
 * 日期：2016-3-2
**/

//获得工程名，并保存在全局变量ctx中
var ctx = $("#side").data("ctx");

//全局函数

/**
 * 函数：表格序列化
 * 输入：$form(jQuery对象，将目标表格里非空的数据转变为js对象,checkbox拼接成逗号分隔的字符串)
 * 输出：js对象
**/
function formToObject($form) {
    var serializeObj={}; 
    $($form.serializeArray()).each(function(){  
        if ($.trim(this.value) !== "") {
            if (serializeObj[this.name] === undefined) {
                serializeObj[this.name] = this.value;   
            } else {
                serializeObj[this.name] += ',' + this.value; 
            }
            
        }
    });  
    return serializeObj;  
}

/**
 * 底部遮罩相关
**/

/* 显示遮罩层 */
function showOverlay(flag) {
    $("#overlay").show();
    $("#overlay").fadeTo(200, 0.6);
    if (flag === "img") {
      $("#overlay img").show();
    }
}

/* 隐藏覆盖层 */
function hideOverlay(flag) {
    if (flag === "img") {
      $("#overlay img").hide();
    } else {
        $("#overlay").fadeOut(200);
    }
}


$(function(){
/**
 * 左侧菜单相关
**/
    //根据地址判断哪个左侧菜单是选中的
    selectSubMenu();
    
    //抛出错误信息
    var msg = $('body').data('message');
    if (msg) {
    	alert(msg);
    }

    //左侧的菜单栏的打开与闭合
    $("#nav_list").on("click",".nav_item",function(){
        var $parent = $(this).parent();
        if ($parent.hasClass("opened")) {
            $parent.removeClass("opened");
            $parent.find(".jiantou").removeClass("icon-jiantoushang").addClass("icon-jiantouxia");
        } else {
            $parent.addClass("opened");
            $parent.find(".jiantou").removeClass("icon-jiantouxia").addClass("icon-jiantoushang");
        }
    });
    
    //点击退出
    $("#logout").click(function(){
        if (confirm("您确定退出当前系统？")) {
            location.href = ctx +"/logout";  
        }
    });

    //点击返回，退回到上一页
    $(".goback").click(function(){
        history.go(-1);
    });

    //点击刷新当前页
    $(".reload").click(function(){
        location.reload(true);
    });

    //选择省市县
    $(".s_province, .s_region, .s_county, .s_towns").change(function(){
        var $parent = $(this).parent().parent();
        var id = $(this).find("option:selected").val();
        
        var sin = "市";
        var postUrl = "region";
        var $target = $parent.find(".s_region");
        var name = "regionName";
        var no = "regionNo";
        if ($(this).hasClass("s_province")) {
        	$parent.find(".s_region").html('<option value="">请选择市</option>');
        	$parent.find(".s_county").html('<option value="">请选择区县</option>');
            $parent.find(".s_towns").html('<option value="">请选择乡镇</option>');
            $parent.find(".s_villages").html('<option value="">请选择村</option>');
        }else if ($(this).hasClass("s_region")) {
        	$parent.find(".s_county").html('<option value="">请选择区县</option>');
            $parent.find(".s_towns").html('<option value="">请选择乡镇</option>');
            $parent.find(".s_villages").html('<option value="">请选择村</option>');
            sin = "区县";
            postUrl = "county";
            $target = $parent.find(".s_county");
            name = "countyName";
            no = "countyNo";
        }else if ($(this).hasClass("s_county")) {
        	$parent.find(".s_towns").html('<option value="">请选择乡镇</option>');
            $parent.find(".s_villages").html('<option value="">请选择村</option>');
            sin = "乡镇";
            postUrl = "towns";
            $target = $parent.find(".s_towns");
            name = "townName";
            no = "townNo";
        }else if ($(this).hasClass("s_towns")) {
            $parent.find(".s_villages").html('<option value="">请选择村</option>');
            sin = "村";
            postUrl = "villages";
            $target = $parent.find(".s_villages");
            name = "villageName";
            no = "villageNo";
        }

        $.ajax({
            type: "post",
            url: ctx + "/webService/" + postUrl,
            data: {id:id},
            dataType:"json",
            success:function(msg){
                // console.log(msg);
                var optionArr = [];
                for (var i = 0, len= msg.length; i < len; i++) {
                    optionArr[i] = '<option value="' + msg[i][no] + '">' + msg[i][name] + '</option>';
                }
                var html = '<option value="">' + '请选择' + sin + '</option>' + optionArr.join("");
                $target.html(html);
            }
        });
    });

    //打开模态框
    $("body").on("click",".open_modal",function(){
        var id = $(this).data("target");
        showOverlay();
        $(id).show();
    });

    //关闭模态框
    $(".modal").on('click', '.close', function(){
        $(this).parents(".modal").hide();
        hideOverlay();
    });


    //全局的AJAX访问，处理AJAX清求时SESSION超时  
    if(typeof($)!="undefined"){  
        $.ajaxSetup({  
            contentType : "application/x-www-form-urlencoded;charset=utf-8",  
            complete : function(XMLHttpRequest, textStatus) {  
                // 通过XMLHttpRequest取得响应头，sessionstatus  
                var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus");  
                if (sessionstatus == "timeout") {  
                    // 这里怎么处理在你，这里跳转的登录页面  
                    alert("登录失效，请重新登录");  
                    window.location.replace(ctx);  
                }  
            }  
        });  
    }  


    /**
     * 函数：根据当前网址打开左侧菜单栏
     * 输入：
     * 输出：
    **/
    function selectSubMenu() {
        var path = location.pathname;
        var $target = $(".submenu a[href='" + path + "']");
        $target.parent().parent().addClass("opened").find(".jiantou").removeClass("icon-jiantouxia").addClass("icon-jiantoushang");
        $target.find("li").addClass("selected");
    }
    

//    //下载附件
//    $('#excelPrint').on('click', function(event) {
//    	event.preventDefault();
//    	$('#downloadExcelFile').submit();
//    });

});