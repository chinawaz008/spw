
//请求数据的后台地址，注意：此为全局变量
var queryDataUrl = "/staff/list_staff";

//筛选数据的条件集合，注意：此为全局变量
var filterCollection = {};

//页面上的操作
$(function(){
    //生成首页内容
    freshTableLay();
    // $("#add_btn").click(function(){
    // 	window.location.href=ctx +"/staff/add_staff";
    // });
    /*$("#pi_btn").click(function(){
    	 $.ajax({
             type: "post",
             url: ctx + "/staff/fenpei_staff",
             data : "",
             dataType: "json",
             success: function(msg) {
                 if (msg.result == "1") {
                	 alert("匹配成功！");
                 } else {
                     alert(msg.message);
                 }
             }
         });
    });*/
});

// function edit(id){
// 	window.location.href=ctx +"/staff/update?staffId="+id;
// }
function removes(id){
	 if (window.confirm("确认离职吗？")) {  
		 $.ajax({
             type: "post",
             url: ctx + "/staff/remove",
             data : {"staffId":id},
             dataType: "json",
             success: function(msg) {
                 if (msg.result == "1") {
                	 alert("离职成功！");
                	 location.href = ctx +"/staff/list_staff";
                 } else {
                     alert(msg.message);
                 }
             }
         });
    }
}


function jiebang(id){
	 if (window.confirm("确认解绑吗？")) {  
		 $.ajax({
            type: "post",
            url: ctx + "/staff/jiebang",
            data : {"staffId":id},
            dataType: "json",
            success: function(msg) {
                if (msg.result == "1") {
               	 alert("解绑成功！");
               	 location.href = ctx +"/staff/list_staff";
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
    var title = $('#pagers li:last-child').attr('title');
	var total = title.split('：')[1] - 0;
	if(total==0){
		alert("没有数据要导出！");
	}else if(total>2000){
		alert("条数过多，请添加检索条件！");
    }else{
    	if (window.confirm("确认导出吗？")) {    
	   	  $("#search_form").attr("action", ctx+"/staff/exportExcelFirm"); 
    	  document.getElementById('search_form').submit();
//	   	  $("#search_form").submit();
    	}
    }
  } 
