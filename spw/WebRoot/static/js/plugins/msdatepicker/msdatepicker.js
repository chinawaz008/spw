$(function(){
    var DatePicker = function () {
        var init = function ($input) {
            this.D = new Date();
            this.el = $input;
            this.el.data("destroy","no");
            this.update();
            this.bind();
        };

        init.prototype = {
            update : function (y, m) {
                var con = [], week = ['日','一','二','三','四','五','六'], D = this.D;
                var fn = function (a, b, c) {return '<td data-destroy="no" class="change_date" data-y="'+a+'" data-m="'+ b +'">'+c+'</td>';};
                var format = function (a) {return (parseInt(a) < 10) ? "0" + a : a;};
                y && D.setYear(D.getFullYear() + y);
                m && D.setMonth(D.getMonth() + m);
                var year = D.getFullYear(), month = D.getMonth() + 1, date = D.getDate(); 
                var firstDay = D._fd(), totalDay = D._fc();
                var ym = year + '-' + format(month) + '-';

                //拼接第一行的箭头和年月
                con[0] = '<tr>';
                con[1] = fn(-1, null, '<i data-destroy="no" class="iconfont icon-iconfontzuojiantou"></i>');
                con[2] = fn(null, -1, '<i data-destroy="no" class="iconfont icon-zuojiantou"></i>');
                con[3] = '<td data-destroy="no" colspan="3" >' + year + '年 ' + month + '月' + '</td>';
                con[4] = fn(null, 1, '<i data-destroy="no" class="iconfont icon-youjiantou"></i>');
                con[5] = fn(1, null, '<i data-destroy="no" class="iconfont icon-jiantou"></i>');
                con[6] = '';

                //拼接星期一、二、三、四的
                for (var l = 0; l < 7; l++) {
                    con[(l + 7)] = '<td data-destroy="no" class="day_name">'+ week[l] +'</td>';
                }
                
                //拼接第一行可能带空格的日期
                for (var i = 0; i < firstDay; i++ ) {
                    con.push('<td data-destroy="no" >&nbsp;</td>');
                }
                
                //拼接一个月中的所有天
                for (var j = 1; j <= totalDay; j++ ) {
                    con.push('<td class="pick_day" data-d="'+ ym + format((j )) + '">' + j +'</td>');
                }
                
                //拼接最后一行,补全到一行7个td
                var toend = 7 - (con.length % 7);
                if (toend !== 7) {
                    for (var k = 0 ; k < toend; k++) {
                        con.push('<td data-destroy="no" >&nbsp;</td>');
                    }
                }
                
                //拼接tbody里的所有tr的代码字符串
                var _html = "";
                for (var n = 0, len = con.length; n < len; n++) {
                    _html += ( n % 7 === 0 ? '</tr><tr>' : '') + con[n] ;
                }
                _html += '</tr>';

                //插入到页面中
                !!this.box ? this.box.find("tbody").html(_html) : this.createBox(_html);
            },

            show : function () {
                this.box.css({
                    "left": this.el.offset().left - 10 + 'px',
                    "top": this.el.offset().top + this.el.outerHeight()  + 5 + 'px'
                }).show();
            },

            destroy : function () {
                this.el.data("destroy","yes");
                this.box.remove();
                $(document).off("click.ms_datepicker");
            },

            bind : function () {
                var _this = this;
                $(document).on("click.ms_datepicker",function(e){
                    if ($(e.target).data("destroy") !== 'no') {_this.destroy();} else {_this.show();}
                });
            },

            createBox : function (html) {
                var _this = this;
                var boxArr = ['<div class="ms_datepicker_box"><table><tbody>'];
                boxArr[1] = html;
                boxArr[2] = '</tbody></table></div>';
                var box = this.box = $(boxArr.join(""));
                box.on("click", ".pick_day", function(){
                    _this.el.val($(this).data("d"));
                });
                box.on("click", ".change_date", function(){
                    var y= $(this).data("y");
                    var m = $(this).data("m");
                    _this.update(y,m);
                });
                $("body").append(box);
            }
        };
        return init;
    }();

    $(".ms_datepicker").on("click focus",function(){
        //为Date对象新增两个方法，返回这个月第一天是星期几       
        Date.prototype._fd = function () {var d = new Date(this); d.setDate(1); return d.getDay();};
        //为Date对象新增两个方法，返回这个月一共有多少天
        Date.prototype._fc = function () {var d1 = new Date(this), d2 = new Date(this); d1.setDate(1); d2.setDate(1); d2.setMonth(d2.getMonth()+1); return (d2-d1)/86400000;};
        if(!$(".ms_datepicker_box").length) {
            var theDatePicker = new DatePicker($(this));
        }
    });

});