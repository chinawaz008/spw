
//请求数据的后台地址，注意：此为全局变量
var queryDataUrl = "/staff/list_dimission_ago";

//筛选数据的条件集合，注意：此为全局变量
var filterCollection = {};

//页面上的操作
$(function(){
	
	$("#personType").change(function(){
		if ($("#personType").val() == 1) {
			$(".person1").removeClass("hide");
			$(".person2").addClass("hide");
		}
		else {
			$(".person2").removeClass("hide");
			$(".person1").addClass("hide");
		}
		filterCollection = formToObject($("#search_form"));
		freshTableLay({
			page: 1
		});
	});
	
	//生成首页内容
	filterCollection = formToObject($("#search_form"));
	freshTableLay();

});

