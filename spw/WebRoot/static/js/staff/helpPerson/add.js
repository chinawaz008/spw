var flag;
$(function(){
		//所有select在页面加载后显示值
		$("select").each(function(){
		    var value = $(this).data("v");
		    $(this).find("option[value='" + value + "']").attr("selected",true);
		});
        $("#btn_submit").click(function(){
			if(checkRequired() && confirm("确定提交吗？")){
				var staffForm = JSON.stringify(formToObject($("#add_help_form")));
				 $.ajax ({
				        type : "post",
				        url : ctx+"/staff/save_help_person",
				        data : {"data": staffForm},
				        dataType : "json",
				        success: function(msg) {
				        	if(msg.result=="0"){
				        		alert(msg.message);
				        	}else{
				        		if (parent.fresh) {
					        	     parent.fresh(); 
					        	}
				        	}
				        	
				        }
				    });
			}
			return false;
         });
});
function getDXQ(){       
	var id = $("#province").find("option:selected").val();  
    $.ajax ({
        type : "post",
        url : ctx+"/organization/dxq",
        data : {"id": id},
        dataType : "json",
        success: function(msg) {
				$("#county").empty(); 
				$("<option  value=''>" + "--请选择督训区--" + "</option>").appendTo($("#county"));
	        	$.each($(msg),function(i, item){
	        		var html = "<option value=" + item.id + ">" + item.name + "</option>'";
	        		$("#county").append(html);
	        	});
        }
    });
}  

function getProvince(){       
	var id = $("#line_type").find("option:selected").val();  
    $.ajax ({
        type : "post",
        url : ctx+"/organization/company",
        data : {"id": id},
        success: function(msg) {
    			$("#province").empty(); 
				$("<option  value=''>" + "--请选择分公司--" + "</option>").appendTo($("#province"));
	        	$.each($(msg),function(i, item){
	        		var html = "<option value=" + item.id+ ">" + item.compName + "</option>'";
	        		$("#province").append(html);
	        	});
        }
    });
}
