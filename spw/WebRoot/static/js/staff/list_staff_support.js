
//请求数据的后台地址，注意：此为全局变量
var queryDataUrl = "/staff/list_staff_support";

//筛选数据的条件集合，注意：此为全局变量
var filterCollection = {};

//页面上的操作
$(function(){
    //生成首页内容
    freshTableLay();
    
    //  数据保存
    $(".modal").on('click', '.save', function(){
    	var anId = $(this).data('id'); 
    	alert(anId);
    	// 关闭模态框
        $(this).parents(".modal").hide();
        hideOverlay();
        // 数据重载
        freshTableLay();
    });
});

// function edit(id){
// 	window.location.href=ctx +"/staff/update_support?staffId="+id;
// }
function removes(id){
	  if (confirm("您确认离职？")) {
		  $.ajax({
	             type: "post",
	             url: ctx + "/staff/remove",
	             data : {"staffId":id,"types":"support"},
	             dataType: "json",
	             success: function(msg) {
	                 if (msg.result == "1") {
	                	 alert("离职成功！");
	                	 location.href = ctx +"/staff/list_staff_support";
	                 } else {
	                     alert(msg.message);
	                 }
	             }
	         });
	  }
}

// 后援人员导出
function exportOrder(){
    var len=$("tr[name='staffSupportTr']").size(); 
    if(len<=0){
      alert("没有数据！");
    }else{
    	if (window.confirm("确认导出吗？")) {    
	   	  $("#search_form").attr("action", ctx+"/staff/exportExcel"); 
        document.getElementById('search_form').submit();
    	}
    }
  }   