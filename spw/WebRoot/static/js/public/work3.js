/**
 * 文件：工作页面脚本
 * 作者：无名大强
 * 版本：v0.1
 * 版权：民盛集团
 * 日期：2017-2-15
 **/

//打开编辑、新增、详情框，供列表子页面调用
function openBox(type, src, confirm) {
    showOverlay();
    var obj = document.getElementById("sub_iframe");
    $('#title').text(type);
    $('#box').removeClass('detail_box');
    $('#box').removeClass('sub_box');
    obj.style.height = "";
    if (type === '详情') {
        $('#box').addClass('detail_box');
    } else if (type === 'sub_box') {
        $('#box').addClass('sub_box');
        obj.style.height = "300px";
        $('#title').text('详情');
    }

    $('#sub_iframe').attr('src', src);
    $('#box').data('confirm', confirm).show();
}

//关闭编辑、新增、详情框，
function closeBox() {
    hideOverlay();
    $('.box').hide();
    $('#sub_iframe').attr('src', '');
}

//调用list_iframe子页面的freshTableLay()函数，从而实现带筛选条件刷新列表页的功能
function freshList() {
    $("#list_iframe")[0].contentWindow.freshTableLay();
}
function freshsub(obj) {
    $("#sub_iframe").on('load', function() {
        var iframeurl = $('#sub_iframe').attr('src');
        if (iframeurl.indexOf('public_tree')>0) {
            $("#sub_iframe")[0].contentWindow.freshListLay(obj);
        }
    })
};
// $("#sub_iframe")[0].contentWindow.freshListLay(obj);
$(function() {
    //左侧菜单点击切换列表页面,并回到顶部
    $('.sub_ul li').click(function() {
        var src = $(this).data('href');
        $('#list_iframe').attr('src', src);
        $('body,html').animate({ scrollTop: 0 }, 100);
    });
    selectedNames();
    //点击 编辑、新增、详情 关闭按钮
    $('.close_btn').click(function() {
        var needConfirm = $('#box').data('confirm');
        if (needConfirm) {
            if (confirm('您确定关闭该窗口？')) {
                closeBox();
            }
        } else {
            closeBox();
        }
    });

    //获取url参数 方法
    function getUrlParam(name) {
        //构造一个含有目标参数的正则表达式对象  
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        //匹配目标参数  
        var r = window.location.search.substr(1).match(reg);
        //返回参数值  
        if (r !== null) return decodeURI(r[2]);
        return null;
    }

    //选中当前横向导航
    function selectedNames() {
        var path = document.location.pathname.split('/')
        var index = path[path.length - 1] / 10000;
        $('#menu li:eq(' + index + ')').addClass('selected');
    }






});