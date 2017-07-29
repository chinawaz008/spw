
//请求数据的后台地址，注意：此为全局变量
var queryDataUrl = "/staff/list_staff_search";

//筛选数据的条件集合，注意：此为全局变量
var filterCollection = {};

//后援人员导出
function exportOrder(){
    var len=$("tr[name='staffSupportTr']").size(); 
    if(len<=0){
      alert("没有数据！");
    }else{
    	if (window.confirm("确认导出吗？")) {    
	   	  $("#search_form").attr("action", ctx+"/staff/exportLookExcel"); 
	   	  document.getElementById('search_form').submit();
          // $("#search_form").submit();
    	}
    }
  }

//页面上的操作
$(function(){
    //生成首页内容
    freshTableLay();
});

