$(function(){
    //点击提交——新增
    $("#btn_submit").click(function(){
		var promotion = JSON.stringify(formToObject($("#add_salaryTime_form")));
        var flag = $("#btn_submit").data("v");
//        alert(promotion);
        if(flag==null || flag==""){//新增
        	  $.ajax({
                  type: "post",
                  url: ctx + "/staff/add_salary_time",
                  data: {data: promotion},
                  dataType: "json",
                  success: function(msg) {
                      if (msg.result.toString() === "1") {
                    	  parent.closeBox();
                    	  parent.freshList();
                      }else{
                    	  alert(msg.message);
                      }
                  }
              });
        }else{//更新
        	  $.ajax({
                  type: "post",
                  url: ctx + "/staff/update_salary_time",
                  data: {data: promotion},
                  dataType: "json",
                  success: function(msg) {
                      if (msg.result.toString() === "1") {
                    	  parent.closeBox();
                    	  parent.freshList();
                      }else{
                    	  alert(msg.message);
                      }
                  }
              });
        }
    });
});
