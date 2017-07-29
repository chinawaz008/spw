/**
 * 文件：人员晋升详情页面脚本
 * 作者：无名小强
 * 版本：v0.1
 * 版权：民盛集团
 * 日期：2016-6-12
**/
$(function(){

    //考试通过或不通过
    $("#exam button").click(function(){
        $(this).addClass('selected').siblings().removeClass('selected');
    });

    //点击晋升通过
    $("#pass_btn").click(function(){
    	 if(confirm("确定晋升通过吗？"))
    	 {
    		var id = $(this).data('id');
	        var isPass = $("#exam button.selected").data('v');
	        $.ajax({
	            url: ctx+"/staff/promotionResult",
	            data: {id: id, isPass: isPass,result:"1"},
	            type: "post",
	            dataType: "json",
	            success:function(msg){
	                if(msg.result == '1') {
	                    location.href = ctx + "/staff/list_promotion";
	                } else {
	                    alert(msg.message);
	                }
	                
	            }
	        });
    	 }
    });
    
    //点击晋升失败
    $("#no_btn").click(function(){
    	if(confirm("确定晋升失败吗？"))
    	{
    		var id = $(this).data('id');
    		var isPass = $("#exam button.selected").data('v');
    		$.ajax({
    			url: ctx+"/staff/promotionResult",
    			data: {id: id, isPass: isPass,result:"0"},
    			type: "post",
    			dataType: "json",
    			success:function(msg){
    				if(msg.result == '1') {
    					location.href = ctx + "/staff/list_promotion";
    				} else {
    					alert(msg.message);
    				}
    				
    			}
    		});
    	}
    });
});
