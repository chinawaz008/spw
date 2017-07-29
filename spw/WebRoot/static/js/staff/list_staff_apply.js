
//请求数据的后台地址，注意：此为全局变量
var queryDataUrl = "/staff/list_staff_apply";

//筛选数据的条件集合，注意：此为全局变量
var filterCollection = {};

//页面上的操作
$(function(){
    //生成首页内容
    freshTableLay();
});

function removes(id){
	 if (window.confirm("确认删除吗？")) {  
		 showOverlay('img');
		 $.ajax({
            type: "post",
            url: ctx + "/staff/deleteApply",
            data : {"applyId":id},
            dataType: "json",
            success: function(msg) {
            	hideOverlay();
                if (msg.result == "1") {
               	 alert("删除成功！");
               	 location.href = ctx +"/staff/list_staff_apply";
                } else {
                    alert(msg.message);
                }
            }
        });
   }
}
function pay(id){
	if (window.confirm("确认支付吗？")) {  
		showOverlay('img');
		 $.ajax({
           type: "post",
           url: ctx + "/staff/pay",
           data : {"applyId":id},
           dataType: "json",
           success: function(msg) {
        	   hideOverlay();
               if (msg.result == "1") {
            	 alert("正在支付,请稍候...");
              	 location.href = ctx +"/staff/list_staff_apply";
               } else {
                   alert(msg.message);
               }
           }
       });
  }
}

function fresh(id){
	if (window.confirm("确认刷新吗？")) {  
		showOverlay('img');
		 $.ajax({
           type: "post",
           url: ctx + "/staff/fresh",
           data : {"applyId":id},
           dataType: "json",
           success: function(msg) {
        	   hideOverlay();
               if (msg.result == "1") {
              	 alert("支付成功！");
              	 location.href = ctx +"/staff/list_staff_apply";
               } else {
                   alert(msg.message);
               }
           }
       });
  }
}

function returnMoney(id){
	if (window.confirm("确认退款吗？")) {  
		showOverlay('img');
		 $.ajax({
           type: "post",
           url: ctx + "/staff/returnMoney",
           data : {"applyId":id},
           dataType: "json",
           success: function(msg) {
        	   hideOverlay();
               if (msg.result == "1") {
              	 alert("退款成功！");
              	 location.href = ctx +"/staff/list_staff_apply";
               } else {
                   alert(msg.message);
               }
           }
       });
  }
}


//后援人员导出
function exportOrder(){
    var len=$("tr[name='staffSupportTr']").size(); 
    if(len<=0){
      alert("没有数据！");
    }else{
    	if (window.confirm("确认导出吗？")) {    
	   	  $("#search_form").attr("action", ctx+"/staff/exportApplyStaff"); 
    	  document.getElementById('search_form').submit();
    	}
    }
 } 
