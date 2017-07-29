/**
 * 文件：列表类页面公共脚本，刷新table_lay、分页、搜索
 * 作者：无名小强
 * 版本：v0.1
 * 版权：民盛集团
 * 日期：2016-3-2
**/


/**
 * 函数：刷新table_lay里的内容
 * 输入：options(对象，参数集合)
 * 输出：生成tbody和分页按钮部分
**/
function freshTableLay(options) {
    //接受参数
    var defaults = {
        page: 1,
        size: 10,
        url: queryDataUrl,
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
        	if (msg.result == "1") {
                
                /*result:"0" , message : "错误信息"   (系统错误)
                **result:"1" , total: 0 , list[0]     (查询结果为空)
                **result:"1", total:n , list[n]       (正常情况)
                */

                //生成新的tbody
                makeTbody(msg.total, msg.list);
                
                //生成新的分页按钮
                makePagers(msg.total, opt.size, opt.page);
            } else {
                alert(msg.message);
            }
        }
    });
}

/**
 * 函数：生成分页按钮
 * 输入：total(数字，总的信息条数),size(数字，每页多少条记录),currentPage(数字，当前页数)
 * 输出：分页按钮
**/
function makePagers (total, size, currentPage) {
    var sum = (parseInt(total) > 1) ? parseInt(total) : 1 ;
    var c = (parseInt(currentPage) > 1) ? parseInt(currentPage) : 1 ;
    var n = Math.ceil(sum / size);
    var pageArr = [];
    var min = 1;
    var max = n;
    
    //拼接首页和前页
    if (c === 1) {
        pageArr = ['<li class="disabled" data-p="1"><span>首页</span></li><li class="disabled"><span>前一页</span></li>'];
    } else {
        pageArr = ['<li class="page" data-p="1"><span>首页</span></li><li class="page" data-p="'+ (c - 1) +'"><span>前一页</span></li>'];
    }
    
    //拼接数字页
    if (n > 10) {
        min = ((c - 5) <= 0) ? 1 : c - 5;
        max = ((c + 5) > n) ? n : c + 5;
    } 
    for (var i = min; i <= max; i++) {
        if ( i !== c ) {
            pageArr.push('<li class="page" data-p="' + i + '"><span>'+ i +'</span></li>');
        } else {
            pageArr.push('<li class="active"><span>'+ i +'</span></li>');
        }
    }
    
    //拼接下一页和末页
    if (c !== n) {
        pageArr.push('<li class="page" data-p="'+ (c + 1) +'"><span>下一页</span></li><li class="page" data-p="' + n + '"><span>末页</span></li>');
    } else {
        pageArr.push('<li class="disabled"><span>下一页</span></li><li class="disabled"><span>末页</span></li>');
    }
    
    //插入到页面中
    $("#pagers").html(pageArr.join(""));
}


/**
 * 函数：根据参数生成新的tbody的html代码字符串
 * 输入：toatl(信息总条数),list(记录数组)
 * 输出：#flexible_tbody插入新的代码
**/
function makeTbody(total, list) {
	var tbodyHtml;
    if (parseInt(total) !== 0 ) {
        var i, j, k,cloneArr, ifArr, trHtml;
        var lenL = list.length;
        var tbodyArr = [];
        //获取网页中的模板
        var trStr = '<tr>' + $("#flexible_tbody .templet").html() + '</tr>';
        var templetArr = trStr.split(/@{|}@/);
        var lenT = templetArr.length;
        
        //拼接每一行tr代码
        for (i = 0; i < lenL; i++) {
            cloneArr = templetArr.slice(0);
            for (j = 1; j < lenT; j = j + 2) {
                cloneArr[j] = list[i][$.trim(cloneArr[j])];
            }
            trHtml = cloneArr.join("");
            
            //实现条件判断
            if (trHtml.indexOf("^{") >= 0) {
                ifArr = trHtml.split(/\^{|}\^/);
                for (k = 1; k < ifArr.length; k += 2) {
                    statementArr = ifArr[k].split(/\^\^/);
                    try {
                    	if(eval(statementArr[0].replace(/&amp;/g, "&"))) {
	                        statementArr[2] = "";
	                    } else {
	                        statementArr[1] = "";
	                    }
                    } catch (e) {
                    	statementArr[1] = "";
                    	statementArr[2] = "";
                    }
                    statementArr[0] = "";
                    ifArr[k] = statementArr.join("");
                }
                trHtml = ifArr.join("");
            }
            tbodyArr.push(trHtml);   
        }
        //拼接tr全集
        tbodyHtml = tbodyArr.join("");
    } else {
        tbodyHtml = '<tr><td class="no_data" colspan="30">当前还没有数据！</td></tr>';
    }

    //插入页面中
    $("#flexible_tbody tr:visible").remove();
    $("#flexible_tbody .templet").after(tbodyHtml);
}


$(function(){

    //点击分页
    $("#pagers").on("click",".page",function(){
        var thePage = $(this).data("p");
        freshTableLay({
            page: thePage
        });
    });

    //点击搜索
    $(".search_btn").click(function(){
        filterCollection = formToObject($("#search_form"));
        freshTableLay({page:1});
    });

    //点击更多筛选条件
    $("#more_condition_btn").click(function(){
        var flag = $(this).hasClass("open");
        var $i = $(this).find(".iconfont");
        var $span = $(this).find("span");
        if (!flag) {
            $("#table_div").show();
            $i.removeClass("icon-xiala").addClass("icon-shang");
            $(this).addClass("open");
            $span.text("精简");
        } else {
            $("#table_div").hide();
            $i.removeClass("icon-shang").addClass("icon-xiala");
            $(this).removeClass("open");
            $span.text("更多");
        }
    });

    //筛选表单不提交，只用于ajax
    $('#search_form').submit(function() {
        return false;
    });

});