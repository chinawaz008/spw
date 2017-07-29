var NoticeBoard = React.createClass({
    render: function () {
        if (this.props.list.length) {
            return (
                <tbody>
                    {
                        this.props.list.map(function (notice) {
                            return (
                                <tr>
                                    <td><i className="iconfont icon-tishi"></i></td>
                                    <td><a href={ctx + "/notice/detail/" + notice.noticeid}>{notice.title}</a></td>
                                    <td><a href="#">{notice.orgType}</a></td>
                                    <td><a href="#">{notice.date}</a></td>
                                </tr>
                            );  
                        })
                    }
                </tbody>
            );
        } else {
            return (
                <img src={ctx + '/static/img/index/no_notice.png'} className="placeholder" />
            );
        }

    }
});

/**
 * 函数：使用React生成公告列表
 * 输入：
 * 输出：
**/
function makeNoticeList(list) {
    console.log(list);
    ReactDOM.render(
        <NoticeBoard list={list} />,
        document.getElementById('notice_table')
    );
}


/**
 * 函数：使用jQuery部分
 * 输入：
 * 输出：
**/
$(function(){
    
    //获取第一页公告
    freshNoticeList();
    
    //筛选条件
    var filterCollection = {};

    //改变公告发布部门筛选条件
    $('#orgType').change(function(){
        var type = $(this).val();
        filterCollection = {orgType: type};
        freshNoticeList({page: 1});
    });

    //点击分页
    $("#notice_pagers").on("click",".page",function(){
        var thePage = $(this).data("p");
        freshNoticeList({
            page: thePage
        });
    });


    /**
     * 函数：刷新公告列表里的内容
     * 输入：options(对象，参数集合)
     * 输出：生成tbody和分页按钮部分
    **/
    function freshNoticeList(options) {
        //接受参数
        var defaults = {
            page: 1,
            size: 7,
            url: '/notice/getNoticeList',
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
                if (msg.result === '1') {
                    console.log(msg.noticeList);
                    //生成公告列表
                    makeNoticeList(msg.noticeList);
                    //生成新的分页按钮
                    makeNoticePagers(msg.total, opt.size, opt.page);
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
    function makeNoticePagers (total, size, currentPage) {
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
        $("#notice_pagers").html(pageArr.join(""));
    }

});


