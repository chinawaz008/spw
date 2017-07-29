/**
 * 文件：页面脚本
 * 作者：无名大强
 * 版本：v0.2
 * 版权：民盛集团
 * 日期：2017-1-6
**/
$(function(){
    //点击是否通过审核radio
    $('input[name="agree"]').click(function() {
        if ($(this).val() === '1') {
            $('#reason').hide();
            $('#reasonTxt').removeClass('required')
            $('.may_required').addClass('required')
            // $('#bank').show();
        } else {
            // $('#bank').hide();
            $('#reasonTxt').addClass('required')
            $('#staffBankCardNum').parent().find('.input_tip').remove();
            $('.may_required').removeClass('required')
            $('#reason').show();
        }
    });
    
    //点击提交按钮
    $("#add_pro_btn").click(function(){
    		var s = $('input[name="agree"]:checked').val();
    		var applyId = $('#applyId').val();
        	if(s==0){
        		var reason = $("#reasonTxt").val();
        		if(reason=="") {
        			alert("请输入原因");return;
        		}
        		if (checkRequired() && window.confirm("确认提交吗？")) { 
        			showOverlay('img');
            		$.ajax({
            			type: "post",
            			url: ctx + "/staff/denyApply",
            			data : {"applyId":applyId,"reason":reason},
            			dataType: "json",
            			success: function(msg) {
            				hideOverlay();
            				if (msg.result == "1") {
            					alert("操作成功！");
            					parent.closeBox();
                                parent.freshList();
            				} else {
            					alert(msg.message);
            				}
            			}
            		});
        		}
        	} else {
        		var bankType = $("#bankCardType").val();
        		var bankNum = $("#bankCardNo").val();
                var staffBankType = $("#staffBankCardType").val();
                var staffBankNum = $("#staffBankCardNum").val();
                var isHaveCar = $('input[name="car"]:checked').val();
                if (checkRequired() && checkFormat()&& window.confirm("确认提交吗？")) {  
                	showOverlay('img');
                    $.ajax({
                    	type: "post",
                    	url: ctx + "/staff/passApply",
                    	data : {"applyId":applyId,"bankType":bankType,"bankNum":bankNum, staffBankNum:staffBankNum,
                    		staffBankType:staffBankType,isHaveCar:isHaveCar},
                    	dataType: "json",
                    	success: function(msg) {
                    		hideOverlay();
                    		if (msg.result == "1") {
                    			alert("操作成功！");
                    			parent.closeBox();
                                parent.freshList();
                    		} else {
                    			alert(msg.message);
                    		}
                    	}
                    });
        		}
        	}
    	
    })
    //鼠标点击图片进行放大
    $('.info_pic').click(function(){
        showOverlay();
        var src = $(this).attr("src");
        var htmlStr = '<div class="enlarged"><img src="' + src + '"></div>';
        $(this).after(htmlStr);
    })

    //点击放大之后的图来关闭放大图
    $('body').on('click', '.enlarged', function() {
        hideOverlay();
        $(".enlarged").remove();
    })

    //银行卡号输入框输入时，改变空格显示
    $('.bankCardNo').on('input', function() {
        var val = $(this).val().trim();
        var len = val.length;
        if (len > 19) {
            val = val.substr(0, 19);
            $(this).val(val);
        } else {
            len = val.length;

            switch(len) {
                case 19:
                case 18:
                case 17:
                    val = val.substr(0, 16) + ' ' + val.substr(16) 
                case 16:
                case 15:
                case 14:
                case 13:
                    val = val.substr(0, 12) + ' ' + val.substr(12) 
                case 12:
                case 11:
                case 10:
                case 9:
                    val = val.substr(0, 8) + ' ' + val.substr(8);
                case 8:
                case 7:
                case 6:
                case 5:
                    val = val.substr(0, 4) + ' ' + val.substr(4);
                    break;


            }

            $(this).parents('td').find('.bank_num').text(val);
            
        }

    })
})
    
    