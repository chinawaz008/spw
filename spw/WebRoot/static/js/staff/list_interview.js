
//请求数据的后台地址，注意：此为全局变量
var queryDataUrl = "/interview/apply_list";

//筛选数据的条件集合，注意：此为全局变量
var filterCollection = {};

//页面上的操作
$(function(){
    //生成首页内容
    freshTableLay();

});

function deal(id,status){
	var str = "确认通过吗？";
	if(status === 1) {
		str = "确认不通过吗？";
	}
	 if (window.confirm(str)) {  
		 $.ajax({
            type: "post",
            url: ctx + "/interview/update",
            data : {"id":id,"status":status},
            dataType: "json",
            success: function(msg) {
                if (msg.result == "1") {
                	alert("操作成功！");
                	location.href = ctx +"/interview/apply_list";
                } else {
                    alert(msg.message);
                }
            }
        });
   }
}