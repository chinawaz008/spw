
var ApplyBoard = React.createClass({
    render: function () {
        if (this.props.list.length) {
            return (
                <tbody>
                    {
                        this.props.list.map(function (item) {
                            var url, str, btn;
                            switch(item.statesFlag) {
                                case '0':
                                	if (item.eventType == 3) {
                                		url = ctx + '/document/send/detail/' + item.eventId;
                                	}
                                	else {
                                		url = ctx + '/application/detail/' + item.eventId;
                                	}
                                    str = '处理中';
                                    btn = 'blue';
                                    break;
                                case '1':

                                	if (item.eventType == 3) {
                                		url = ctx + '/document/send/detail/' + item.eventId;
                                	}
                                	else {
                                		url = ctx + '/application/detail/' + item.eventId;
                                	}
                                    str = '已完成';
                                    btn = 'green';
                                    break;
                                case '2':
                                	if (item.eventType == 3) {
                                		url = ctx + '/document/send/update/' + item.eventId;
                                	}
                                	else {
                                		url = ctx + '/application/detail/' + item.eventId;
                                	}
                                    str = '被驳回';
                                    btn = 'red';
                                    break;
                                case '3':
                                	if (item.eventType == 3) {
                                		url = ctx + '/document/recieve/add/' + item.eventId;
                                		str = '待审批';
                                	}
                                	else {
                                		url = ctx + '/application/detail/' + item.eventId;
                                		str = '待报到';
                                	}
                                    btn = 'orange';
                                    break;
                                case '8':
                                	if (item.eventType == 3) {
                                		url = ctx + '/document/recieve/add/' + item.eventId;
                                	}
                                	else {
                                		url = ctx + '/application/deal/' + item.eventId;
                                	}
                                	str = '待审批';
                                	btn = 'orange';
                                	break;
                                default:
                                    break;
                            }

                            return (
                                <tr>
                                    <td>{item.information}</td>
                                    <td>{item.eventTypeName}</td>
                                    <td>{item.createTime}</td>
                                    <td><a href={url}><button type="button" className={btn}>{str}</button></a></td>
                                </tr>
                            );  
                        })
                    }
                </tbody>
            );
        } else {
            return (
                <img src={ctx +'/static/img/index/no_apply.png'} className="placeholder" />
            );
        }
    }
});


/**
 * 函数：使用React生成公告列表
 * 输入：
 * 输出：
**/
function makeApplyList(list) {
    ReactDOM.render(
        <ApplyBoard list={list} />,
        document.getElementById('apply_table')
    );
}


/**
 * 函数：使用jQuery部分
 * 输入：
 * 输出：
**/
$(function(){
    
    //筛选条件
    var filterCollection = {};
    var query_url = '/document/recieve/getSignList';

    //获取待审批
    freshApplyList();
    
    //改变审批类型筛选条件
    $('#apply_type').change(function(){
        var url = $(this).val();
        query_url = url;
        freshApplyList({page: 1});
    });

    //点击公告分页
    $("#apply_pagers").on("click",".page",function(){
        var thePage = $(this).data("p");
        freshApplyList({
            page: thePage
        });
    });


    /**
     * 函数：刷新公告列表里的内容
     * 输入：options(对象，参数集合)
     * 输出：生成tbody和分页按钮部分
    **/
    function freshApplyList(options) {
        //接受参数
        var defaults = {
            page: 1,
            size: 6,
            url: query_url,
            filter: filterCollection
        };
        var opt = $.extend(defaults,options);
        //构造请求参数
        var datas = $.extend({page: opt.page, size: opt.size}, opt.filter);

        //请求后端数据
        $.ajax({
            type: "post",
            url: ctx + opt.url,
            data: {data:JSON.stringify(datas)},
            dataType: "json",
            success: function(msg) {
                // console.log(msg);
                if (msg.result === '1') {
                    //生成待审批列表
                    makeApplyList(msg.signList);
                    //生成新的分页按钮
                    makeApplyPagers(msg.total, opt.size, opt.page);
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
    function makeApplyPagers (total, size, currentPage) {
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
        $("#apply_pagers").html(pageArr.join(""));
    }

});


