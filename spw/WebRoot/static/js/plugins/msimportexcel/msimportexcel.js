/**
 * 文件：民盛用Excel表格导入数据插件
 * 作者：无名小强
 * 版本：v0.1
 * 版权：民盛集团
 * 日期：2016-3-22
**/
$(function(){
    var MsImportExcel = function(){
        var init = function($btn) {
            this.title = $btn.data("title");
            this.step1 = $btn.data("step1");
            this.step2 = $btn.data("step2");
            this.box = this.newBox();
            this.show();
            this.returnData = {};
            this.boundListener();
        };

        init.prototype = {
            newBox: function() {
                var htmlArr = [];
                htmlArr[0] = '<div class="ms_import_box"><div class="import_box_title"><label>';
                htmlArr[1] = this.title;
                htmlArr[2] = '</label><label class="close_import_box" id="close_import_box">&times;</label></div><div class="import_box_body"><p>选择可以上传的Excel文件，务必保证表格满足规格要求</p></div><div class="import_box_foot"><button class="btn_orange" id="select_excel">选择文件</button><form id="import_form" method="post" action="';
                htmlArr[3] = ctx + this.step1;
                htmlArr[4] = '" target="excel_target" enctype="multipart/form-data"><input type="file" id="excel_input" name="files"></form></div><iframe id="excel_target" name="excel_target"></iframe></div>';
                var html = htmlArr.join("");
                return $(html);
            },
            show: function() {
                showOverlay();
                $("body").append(this.box);
            },
            boundListener: function() {
                var that = this;
                var $box = this.box;
                //绑定关闭按钮的操作
                $box.on("click",".close_import_box",function(){
                    that.destroy();
                });

                //绑定上传文件按钮
                $box.on("click","#select_excel",function(){
                    that.openFilesSelector();
                });

                //上传Excel表格
                $box.find("#excel_input").change(function(){
                    if($(this).val() !== '') {
                        $("#import_form").submit();
                        that.loadingBox();
                    }
                    
                    $box.find("#excel_target").one("load",function(){
                        var $datas = $(window.frames.excel_target.document.body).find("#public_excel");
                        that.returnData = $datas;
                        if ($datas.data("state") === "success") {
                            that.checkSuccess();
                        } else {
                            that.checkFails();
                        }
                    });
                });

                //点击导入数据库
                $box.on("click","#import_to_db",function(){
                    that.loadingBox();
                    $.ajax({
                        type: "post",
                        url: ctx + that.step2,
                        data: {data:that.returnData.data("detail")},
                        dataType: "json",
                        success: function(msg){
                            if (msg.result === "1") {
                                that.importSuccess(msg.message);
                            } else {
                                that.importFails(msg.message);
                            }
                        }
                    });
                });
            },
            openFilesSelector: function() {
                this.box.find("#excel_input").click();
            },
            checkSuccess: function() {
                var $datas = this.returnData;
                var body = '<table><tbody><tr><td rowspan="2"><i class="iconfont icon-zhengque1 success"></i></td><td>文件名：' + $datas.data("name") + 
                            '</td></tr><tr><td>文件总共：'+ $datas.data("total") + '条信息</td><td><span class="success">验证成功！</span></td></tr></tbody></table>';
                var btns = '<button class="btn_green" id="import_to_db">写入数据库</button><button class="btn_red close_import_box">取消导入</button>';
                this.freshBox(body, btns);
            },
            checkFails: function(){
                var $datas = this.returnData;
                var body = '<table><tbody><tr><td rowspan="2"><i class="iconfont icon-cuowu fails"></i></td><td>文件名：' + $datas.data("name") + 
                            '</td></tr><tr><td>文件总共：'+ $datas.data("total") + '条信息</td><td>有<span class="fails">'+ $datas.data("failnum") +'</span>条错误</td></tr></tbody></table><div class="error_board">'+ $datas.data("detail") +'</div>';
                var btns = '<button class="btn_red close_import_box">取消导入</button>';
                this.freshBox(body, btns);
            },
            importSuccess: function(str) {
                var body = '<p>'+ str +'</p>';
                var btns = '<button class="btn_green close_import_box">完成</button>';
                this.freshBox(body, btns);
            },
            importFails: function(str) {
                var body = '<p>导入失败！ <br>' + str + '</p>';
                var btns = '<button class="btn_orange close_import_box">关闭</button>';
                this.freshBox(body, btns);
            },
            loadingBox: function() {
                var body = '<p class="loading"><img src="'+ ctx + '/static/img/public/upload.gif"></p>';
                var btns = '';
                this.freshBox(body, btns);
            },
            freshBox: function(body, btns) {
                this.box.find(".import_box_body").html(body);
                this.box.find(".import_box_foot").html(btns);
            },
            destroy: function() {
                hideOverlay();
                this.box.remove();
                location.reload(true);
            }
        };
        return init;
    }();

    $(".ms_import_excel").click(function(){
        if(!$(".ms_import_box").length) {
            var myImport = new MsImportExcel($(this));
        }
    });
});