$(function(){
    var TaipingCitys = function () {
        var init = function ($input) {
            this.el = $input;
            this.str = $input.val();
            this.num_input = $input.data("num");
            this.code_input = $input.data("code");
            this.destroy();
            this.createBox();
        };

        init.prototype = {
            
            destroy : function () {
                $('.ms_car_citys').remove();
                $(document).off("click.ms_carcitys");
            },

            bind : function () {
                var _this = this;
                var $box = this.box;

                //点击空白处关闭弹出窗口
                $(document).on("click.ms_carcitys",function(e){
                    if ($(e.target).closest(".ms_car_citys").length === 0 && !$(e.target).hasClass("ms_taiping_citys_input")) {
                        _this.destroy();
                    }
                });

                //绑定关闭按钮的操作
                $box.on("click",".close_ms_carcitys",function(){
                    _this.destroy();
                });

                //点击选择不同的城市
                $box.on("click",".car_city",function(){
                    var citycode = $(this).data("citycode");
                    var carid = $(this).data("pre2");
                    var text = $(this).text();
                    $(_this.num_input).val(carid).data('n', carid);
                    $(_this.code_input).val(citycode);
                    _this.el.val(text);
                    _this.destroy();
                });
            },

            createBox : function (html) {
                var _this = this;
                $.ajax({
                    type: "post",
                    url: ctx + "/taiping/getCityList",
                    data : {searchTxt: _this.str},
                    dataType: "json",
                    success: function(msg) {
                        var hotCity = msg.pager;
                        var hotCityArr = [];
                        for (var i = 0, len = hotCity.length; i < len; i++) {
                            hotCityArr[i] = '<li><a href="javascript:;" class="car_city" data-pre2="' + hotCity[i].licenseNoPre2 + '" data-citycode="' + hotCity[i].cityCode + '">' + hotCity[i].cityName + '</a></li>';
                        }
                        var hotCityHtml = hotCityArr.join("");
                        var boxArr = [];
                        boxArr[0] = '<div class="hide ms_car_citys"><span class="close_ms_carcitys">X</span><div class="content"><ul>';
                        boxArr[1] = hotCityHtml;
                        boxArr[2] = '</ul></div></div>';

                        var box = _this.box = $(boxArr.join("")).css({
                                "left": _this.el.offset().left + 'px',
                                "top": _this.el.offset().top + _this.el.outerHeight()  + 5 + 'px'
                            }).show();
                        _this.destroy();
                        $("body").append(box);
                        _this.bind();
                    }
                });
            }
        };
        return init;
    }();

    $(".ms_taiping_citys_input").on("keyup focus",function(){
        var val = $.trim($(this).val());
        $(this).val(val);
        var theTaipingCitys = new TaipingCitys($(this));
    });

});