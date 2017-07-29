
//请求数据的后台地址，注意：此为全局变量
var queryDataUrl = "/staff/list_dimission";

//筛选数据的条件集合，注意：此为全局变量
var filterCollection = {};

//页面上的操作
$(function(){
    //生成首页内容
    freshTableLay();
});

function dimiss(id){
	if(window.confirm("确认离职吗？")){
		$.ajax({
            type: "post",
            url: ctx + "/staff/sure_dimission",
            data : {id: id},
            dataType: "json",
            success: function(msg) {
                if (msg.result.toString() === "1") {
                     location.href = ctx +"/staff/list_dimission";
                } else {
                    alert("离职失败！");
                }
            }
        });
	}
}

