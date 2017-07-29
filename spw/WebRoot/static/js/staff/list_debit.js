
//请求数据的后台地址，注意：此为全局变量
var queryDataUrl = "/staff/list_debit";

var queryCountUrl = "/staff/debitCount";

//筛选数据的条件集合，注意：此为全局变量
var filterCollection = {};

//页面上的操作
$(function(){
    //生成首页内容
    freshTableLay();
    
    debit_count();
    
  //点击搜索
    $(".debit_search").click(function(){
        filterCollection = formToObject($("#search_form"));
        debit_count();
        freshTableLay({page:1});
    });
    
    $('#flexible_tbody').on('click', '.btn_print', function() {
    	if(confirm("确定打印吗？")){
    		//打开遮罩层
    	    showOverlay('img');
    		var src = $(this).data("src"); 
        	//请求后端数据
            $.ajax({
                type: "post",
                url: src,
                dataType: "json",
                success: function(msg) {
                	//关闭遮罩层
                    hideOverlay();
                	alert(msg.message);
                }
            });
    	}
    })
    
      $('#flexible_tbody').on('click', '.btn_print2', function() {
    	if(confirm("确定打印吗？")){
    		//打开遮罩层
    	    showOverlay('img');
    		var src = $(this).data("src"); 
        	//请求后端数据
            $.ajax({
                type: "post",
                url: src,
                dataType: "json",
                success: function(msg) {
                	//关闭遮罩层
                    hideOverlay();
                    if(msg.result==="1"){
                    	alert("打印成功！");
                    	parent.closeBox();
                    	parent.freshList();
                    }else{
                    	alert(msg.message);
                    }
                }
            });
    	}
    })
    
    
    function debit_count(options) {
        //接受参数
        var defaults = {
            page: 1,
            size: 10,
            url: queryCountUrl,
            filter: filterCollection
        };
        var opt = $.extend(defaults,options);
        //构造请求参数
        var datas = $.extend({page: opt.page, size: opt.size}, opt.filter);
        //构成查询对象
        var dato = {data:JSON.stringify(datas)};
        //请求后端数据
        $.ajax({
            type: "post",
            url: ctx + opt.url,
            data: dato,
            dataType: "json",
            success: function(msg) {
                console.log(123)
            	if (msg.result == "1") {
                	// 拼接统计数据
            		console.log(345)
                	var htmlArr = [];
                	var showList = msg.showList;
                	for (var i= 0;i<showList.length;i++) {
                		htmlArr.push('<span class="red" id="jy_count">'+ showList[i].countName+ '</span> <span class="item">'+showList[i].name+'</span>');
                	}
                	$('.tongji').html(htmlArr.join(''));
//                	$("#jy_count").html(msg.count+"笔");
//                	$("#jy_money").html(msg.money+"元");
//                	$("#fj_count").html(msg.fj_count+"位");
//                	$("#fjl_count").html(msg.fjl_count+"位");
//                	$("#kj_count").html(msg.kj_count+"位");
                }
            }
        });
    }
});



$("#excelPrint").click(function(){
	var datas = JSON.stringify(formToObject($("#search_form")));
	if(confirm("确定导出至excel吗？")){
		var len=$("tr[name='insuranceTr']").size(); 
         if(len<=0){
           alert("没有数据！");
         }else{
           // openOverlay($(this));
        	var url = ctx+"/staff/export_list_debit?data=" + datas;
    		$("#search_form").attr("action", url); 
    		document.getElementById('search_form').submit();
         }
	}
})