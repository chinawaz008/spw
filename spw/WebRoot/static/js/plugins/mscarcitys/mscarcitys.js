$(function(){
    var CarCitys = function () {
        var init = function ($input) {
            this.el = $input;
            this.num_input = $input.data("num");
            this.code_input = $input.data("code");
            this.insureHotCity=[
                {"cityName":"北京","cityCode":"110100","pinyin":"BeiJingShi","jp":"bjs","provName":"北京","provCode":"110000","license":"京"},
                {"cityName":"上海","cityCode":"310100","pinyin":"ShangHaiShi","jp":"shs","provName":"上海","provCode":"310000","license":"沪"},
                {"cityName":"深圳","cityCode":"830100","pinyin":"ShenZhenShi","jp":"szs","provName":"广东","provCode":"440000","license":"粤B"},
                {"cityName":"重庆","cityCode":"500100","pinyin":"ChongQingShi","jp":"cqs","provName":"重庆","provCode":"500000","license":"渝"},
                {"cityName":"天津","cityCode":"120100","pinyin":"TianJin","jp":"tjs","provName":"天津","provCode":"120000","license":"津"},
                {"cityName":"合肥","cityCode":"340100","pinyin":"HeFeiShi","jp":"hfs","provName":"安徽","provCode":"340000","license":"皖A"},
                {"cityName":"徐州","cityCode":"320300","pinyin":"XuZhouShi","jp":"xzs","provName":"江苏","provCode":"320000","license":"苏C"},
                {"cityName":"南京","cityCode":"320100","pinyin":"NanJingShi","jp":"njs","provName":"江苏","provCode":"320000","license":"苏A"}
            ];
            this.show();
            this.bind();
            this.insureCity=[
                {"cityName":"广州市","cityCode":"440100","pinyin":"GuangZhouShi","jp":"gzs","provName":"广东","provCode":"440000","license":"粤A"},
                {"cityName":"韶关市","cityCode":"440200","pinyin":"ShaoGuanShi","jp":"sgs","provName":"广东","provCode":"440000","license":"粤F"},
                {"cityName":"深圳市","cityCode":"830100","pinyin":"ShenZhenShi","jp":"szs","provName":"广东","provCode":"440000","license":"粤B"},
                {"cityName":"珠海市","cityCode":"440400","pinyin":"ZhuHaiShi","jp":"zhs","provName":"广东","provCode":"440000","license":"粤C"},
                {"cityName":"汕头市","cityCode":"440500","pinyin":"ShanTouShi","jp":"sts","provName":"广东","provCode":"440000","license":"粤D"},
                {"cityName":"佛山市","cityCode":"440600","pinyin":"FoShanShi","jp":"fss","provName":"广东","provCode":"440000","license":"粤E"},
                {"cityName":"江门市","cityCode":"440700","pinyin":"JiangMenShi","jp":"jms","provName":"广东","provCode":"440000","license":"粤J"},
                {"cityName":"湛江市","cityCode":"440800","pinyin":"ZhanJiangShi","jp":"zjs","provName":"广东","provCode":"440000","license":"粤G"},
                {"cityName":"茂名市","cityCode":"440900","pinyin":"MaoMingShi","jp":"mms","provName":"广东","provCode":"440000","license":"粤K"},
                {"cityName":"肇庆市","cityCode":"441200","pinyin":"ZhaoQingShi","jp":"zqs","provName":"广东","provCode":"440000","license":"粤H"},
                {"cityName":"惠州市","cityCode":"441300","pinyin":"HuiZhouShi","jp":"hzs","provName":"广东","provCode":"440000","license":"粤L"},
                {"cityName":"梅州市","cityCode":"441400","pinyin":"MeiZhouShi","jp":"mzs","provName":"广东","provCode":"440000","license":"粤M"},
                {"cityName":"汕尾市","cityCode":"441500","pinyin":"shanweishi","jp":"sws","provName":"广东","provCode":"440000","license":"粤N"},
                {"cityName":"河源市","cityCode":"441600","pinyin":"heyuanshi","jp":"hys","provName":"广东","provCode":"440000","license":"粤P"},
                {"cityName":"阳江市","cityCode":"441700","pinyin":"yangjiangshi","jp":"yjs","provName":"广东","provCode":"440000","license":"粤Q"},
                {"cityName":"清远市","cityCode":"441800","pinyin":"QingYuanShi","jp":"qys","provName":"广东","provCode":"440000","license":"粤R"},
                {"cityName":"东莞市","cityCode":"441900","pinyin":"DongGuanShi","jp":"dgs","provName":"广东","provCode":"440000","license":"粤S"},
                {"cityName":"中山市","cityCode":"442000","pinyin":"ZhongShanShi","jp":"zss","provName":"广东","provCode":"440000","license":"粤T"},
                {"cityName":"潮州市","cityCode":"445100","pinyin":"chaozhoushi","jp":"czs","provName":"广东","provCode":"440000","license":"粤U"},
                {"cityName":"揭阳市","cityCode":"445200","pinyin":"jieyangshi","jp":"jys","provName":"广东","provCode":"440000","license":"粤V"},
                {"cityName":"云浮市","cityCode":"445300","pinyin":"yunfushi","jp":"yfs","provName":"广东","provCode":"440000","license":"粤W"},
                {"cityName":"南京市","cityCode":"320100","pinyin":"NanJingShi","jp":"njs","provName":"江苏","provCode":"320000","license":"苏A"},
                {"cityName":"无锡市","cityCode":"320200","pinyin":"WuXiShi","jp":"wxs","provName":"江苏","provCode":"320000","license":"苏B"},
                {"cityName":"徐州市","cityCode":"320300","pinyin":"XuZhouShi","jp":"xzs","provName":"江苏","provCode":"320000","license":"苏C"},
                {"cityName":"常州市","cityCode":"320400","pinyin":"ChangZhouShi","jp":"czs","provName":"江苏","provCode":"320000","license":"苏D"},
                {"cityName":"苏州市","cityCode":"320500","pinyin":"SuZhouShi","jp":"szs","provName":"江苏","provCode":"320000","license":"苏E"},
                {"cityName":"南通市","cityCode":"320600","pinyin":"NanTongShi","jp":"nts","provName":"江苏","provCode":"320000","license":"苏F"},
                {"cityName":"连云港市","cityCode":"320700","pinyin":"LianYunGangShi","jp":"lygs","provName":"江苏","provCode":"320000","license":"苏G"},
                {"cityName":"淮安市","cityCode":"320800","pinyin":"HuaiAnShi","jp":"has","provName":"江苏","provCode":"320000","license":"苏H"},
                {"cityName":"盐城市","cityCode":"320900","pinyin":"YanChengShi","jp":"ycs","provName":"江苏","provCode":"320000","license":"苏J"},
                {"cityName":"扬州市","cityCode":"321000","pinyin":"YangZhouShi","jp":"yzs","provName":"江苏","provCode":"320000","license":"苏K"},
                {"cityName":"镇江市","cityCode":"321100","pinyin":"ZhenJiangShi","jp":"zjs","provName":"江苏","provCode":"320000","license":"苏L"},
                {"cityName":"泰州市","cityCode":"321200","pinyin":"TaiZhouShi","jp":"tzs","provName":"江苏","provCode":"320000","license":"苏M"},
                {"cityName":"宿迁市","cityCode":"321300","pinyin":"SuQianShi","jp":"sqs","provName":"江苏","provCode":"320000","license":"苏N"},
                {"cityName":"济南市","cityCode":"370100","pinyin":"JiNanShi","jp":"jns","provName":"山东","provCode":"370000","license":"鲁A"},
                {"cityName":"青岛市","cityCode":"820200","pinyin":"qingdaoshi","jp":"qds","provName":"山东","provCode":"370000","license":"鲁B"},
                {"cityName":"淄博市","cityCode":"370300","pinyin":"ZiBoShi","jp":"zbs","provName":"山东","provCode":"370000","license":"鲁C"},
                {"cityName":"枣庄市","cityCode":"370400","pinyin":"ZaoZhuangShi","jp":"zzs","provName":"山东","provCode":"370000","license":"鲁D"},
                {"cityName":"东营市","cityCode":"370500","pinyin":"DongYingShi","jp":"dys","provName":"山东","provCode":"370000","license":"鲁E"},
                {"cityName":"烟台市","cityCode":"370600","pinyin":"YanTaiShi","jp":"yts","provName":"山东","provCode":"370000","license":"鲁F"},
                {"cityName":"潍坊市","cityCode":"370700","pinyin":"WeiFangShi","jp":"wfs","provName":"山东","provCode":"370000","license":"鲁G"},
                {"cityName":"济宁市","cityCode":"370800","pinyin":"JiNingShi","jp":"jns","provName":"山东","provCode":"370000","license":"鲁H"},
                {"cityName":"泰安市","cityCode":"370900","pinyin":"TaiAnShi","jp":"tas","provName":"山东","provCode":"370000","license":"鲁J"},
                {"cityName":"威海市","cityCode":"371000","pinyin":"WeiHaiShi","jp":"whs","provName":"山东","provCode":"370000","license":"鲁K"},
                {"cityName":"日照市","cityCode":"371100","pinyin":"RiZhaoShi","jp":"rzs","provName":"山东","provCode":"370000","license":"鲁L"},
                {"cityName":"莱芜市","cityCode":"371200","pinyin":"LaiWuShi","jp":"lws","provName":"山东","provCode":"370000","license":"鲁S"},
                {"cityName":"临沂市","cityCode":"371300","pinyin":"LinYiShi","jp":"lys","provName":"山东","provCode":"370000","license":"鲁Q"},
                {"cityName":"德州市","cityCode":"371400","pinyin":"DeZhouShi","jp":"dzs","provName":"山东","provCode":"370000","license":"鲁N"},
                {"cityName":"聊城市","cityCode":"371500","pinyin":"LiaoChengShi","jp":"lcs","provName":"山东","provCode":"370000","license":"鲁P"},
                {"cityName":"滨州市","cityCode":"371600","pinyin":"BinZhouShi","jp":"bzs","provName":"山东","provCode":"370000","license":"鲁M"},
                {"cityName":"菏泽市","cityCode":"371700","pinyin":"HeZeShi","jp":"hzs","provName":"山东","provCode":"370000","license":"鲁R"},
                {"cityName":"成都市","cityCode":"510100","pinyin":"ChengDuShi","jp":"cds","provName":"四川","provCode":"510000","license":"川A"},
                {"cityName":"自贡市","cityCode":"510300","pinyin":"ZiGongShi","jp":"zgs","provName":"四川","provCode":"510000","license":"川C"},
                {"cityName":"攀枝花市","cityCode":"510400","pinyin":"panzhihua","jp":"pzh","provName":"四川","provCode":"510000","license":"川D"},
                {"cityName":"泸州市","cityCode":"510500","pinyin":"LuZhouShi","jp":"lzs","provName":"四川","provCode":"510000","license":"川E"},
                {"cityName":"德阳市","cityCode":"510600","pinyin":"deyangshi","jp":"dys","provName":"四川","provCode":"510000","license":"川F"},
                {"cityName":"绵阳市","cityCode":"510700","pinyin":"MianYangShi","jp":"mys","provName":"四川","provCode":"510000","license":"川B"},
                {"cityName":"广元市","cityCode":"510800","pinyin":"GuangYuanShi","jp":"gys","provName":"四川","provCode":"510000","license":"川H"},
                {"cityName":"遂宁市","cityCode":"510900","pinyin":"SuiNingShi","jp":"sns","provName":"四川","provCode":"510000","license":"川J"},
                {"cityName":"内江市","cityCode":"511000","pinyin":"NeiJiangShi","jp":"njs","provName":"四川","provCode":"510000","license":"川K"},
                {"cityName":"乐山市","cityCode":"511100","pinyin":"LeShanShi","jp":"lss","provName":"四川","provCode":"510000","license":"川L"},
                {"cityName":"南充市","cityCode":"511300","pinyin":"NanChongShi","jp":"ncs","provName":"四川","provCode":"510000","license":"川R"},
                {"cityName":"眉山市","cityCode":"511400","pinyin":"MeiShanShi","jp":"mss","provName":"四川","provCode":"510000","license":"川Z"},
                {"cityName":"宜宾市","cityCode":"511500","pinyin":"YiBinShi","jp":"ybs","provName":"四川","provCode":"510000","license":"川Q"},
                {"cityName":"广安市","cityCode":"511600","pinyin":"GuangAnShi","jp":"gas","provName":"四川","provCode":"510000","license":"川X"},
                {"cityName":"达州市","cityCode":"511700","pinyin":"dazhoushi","jp":"dzs","provName":"四川","provCode":"510000","license":"川S"},
                {"cityName":"雅安市","cityCode":"511800","pinyin":"yananshi","jp":"yas","provName":"四川","provCode":"510000","license":"川T"},
                {"cityName":"巴中市","cityCode":"511900","pinyin":"bazhongshi","jp":"bzs","provName":"四川","provCode":"510000","license":"川Y"},
                {"cityName":"资阳市","cityCode":"512000","pinyin":"ZiYangShi","jp":"zys","provName":"四川","provCode":"510000","license":"川M"},
                {"cityName":"阿坝自治州","cityCode":"513200","pinyin":"ABaZiZhiZhou","jp":"abzzz","provName":"四川","provCode":"510000","license":"川U"},
                {"cityName":"甘孜自治州","cityCode":"513300","pinyin":"GanZiZiZhiZhou","jp":"gzzzz","provName":"四川","provCode":"510000","license":"川V"},
                {"cityName":"凉山自治州","cityCode":"513400","pinyin":"LiangShanZiZhiZhou","jp":"lszzz","provName":"四川","provCode":"510000","license":"川W"},
                {"cityName":"郑州市","cityCode":"410100","pinyin":"ZhengZhouShi","jp":"zzs","provName":"河南","provCode":"410000","license":"豫A"},
                {"cityName":"开封市","cityCode":"410200","pinyin":"KaiFengShi","jp":"kfs","provName":"河南","provCode":"410000","license":"豫B"},
                {"cityName":"洛阳市","cityCode":"410300","pinyin":"LuoYangShi","jp":"lys","provName":"河南","provCode":"410000","license":"豫C"},
                {"cityName":"平顶山市","cityCode":"410400","pinyin":"PingDingShanShi","jp":"pdss","provName":"河南","provCode":"410000","license":"豫D"},
                {"cityName":"安阳市","cityCode":"410500","pinyin":"AnYangShi","jp":"ays","provName":"河南","provCode":"410000","license":"豫E"},
                {"cityName":"鹤壁市","cityCode":"410600","pinyin":"HeBiShi","jp":"hbs","provName":"河南","provCode":"410000","license":"豫F"},
                {"cityName":"新乡市","cityCode":"410700","pinyin":"XinXiangShi","jp":"xxs","provName":"河南","provCode":"410000","license":"豫G"},
                {"cityName":"焦作市","cityCode":"410800","pinyin":"JiaoZuoShi","jp":"jzs","provName":"河南","provCode":"410000","license":"豫H"},
                {"cityName":"济源市","cityCode":"419000","pinyin":"jiyuanshi","jp":"JYS","provName":"河南","provCode":"410000","license":"豫U"},
                {"cityName":"濮阳市","cityCode":"410900","pinyin":"PuYangShi","jp":"pys","provName":"河南","provCode":"410000","license":"豫J"},
                {"cityName":"许昌市","cityCode":"411000","pinyin":"XuChangShi","jp":"xcs","provName":"河南","provCode":"410000","license":"豫K"},
                {"cityName":"漯河市","cityCode":"411100","pinyin":"LuoHeShi","jp":"lhs","provName":"河南","provCode":"410000","license":"豫L"},
                {"cityName":"三门峡市","cityCode":"411200","pinyin":"SanMenXiaShi","jp":"smxs","provName":"河南","provCode":"410000","license":"豫M"},
                {"cityName":"南阳市","cityCode":"411300","pinyin":"NanYangShi","jp":"nys","provName":"河南","provCode":"410000","license":"豫R"},
                {"cityName":"商丘市","cityCode":"411400","pinyin":"ShangQiuShi","jp":"sqs","provName":"河南","provCode":"410000","license":"豫N"},
                {"cityName":"信阳市","cityCode":"411500","pinyin":"XinYangShi","jp":"xys","provName":"河南","provCode":"410000","license":"豫S"},
                {"cityName":"周口市","cityCode":"411600","pinyin":"ZhouKouShi","jp":"zks","provName":"河南","provCode":"410000","license":"豫P"},
                {"cityName":"驻马店市","cityCode":"411700","pinyin":"ZhuMaDianShi","jp":"zmds","provName":"河南","provCode":"410000","license":"豫Q"},
                {"cityName":"杭州市","cityCode":"330100","pinyin":"HangZhouShi","jp":"hzs","provName":"浙江","provCode":"330000","license":"浙A"},
                {"cityName":"宁波市","cityCode":"810200","pinyin":"NingBoShi","jp":"NBS","provName":"浙江","provCode":"330000","license":"浙B"},
                {"cityName":"温州市","cityCode":"330300","pinyin":"WenZhouShi","jp":"wzs","provName":"浙江","provCode":"330000","license":"浙C"},
                {"cityName":"嘉兴市","cityCode":"330400","pinyin":"JiaXingShi","jp":"jxs","provName":"浙江","provCode":"330000","license":"浙F"},
                {"cityName":"湖州市","cityCode":"330500","pinyin":"HuZhouShi","jp":"hzs","provName":"浙江","provCode":"330000","license":"浙E"},
                {"cityName":"绍兴市","cityCode":"330600","pinyin":"ShaoXingShi","jp":"sxs","provName":"浙江","provCode":"330000","license":"浙D"},
                {"cityName":"金华市","cityCode":"330700","pinyin":"JinHuaShi","jp":"jhs","provName":"浙江","provCode":"330000","license":"浙G"},
                {"cityName":"衢州市","cityCode":"330800","pinyin":"QuZhouShi","jp":"qzs","provName":"浙江","provCode":"330000","license":"浙H"},
                {"cityName":"舟山市","cityCode":"330900","pinyin":"ZhouShanShi","jp":"zss","provName":"浙江","provCode":"330000","license":"浙L"},
                {"cityName":"台州市","cityCode":"331000","pinyin":"TaiZhouShi","jp":"tzs","provName":"浙江","provCode":"330000","license":"浙J"},
                {"cityName":"丽水市","cityCode":"331100","pinyin":"LiShuiShi","jp":"lss","provName":"浙江","provCode":"330000","license":"浙K"},
                {"cityName":"石家庄市","cityCode":"130100","pinyin":"ShiJiaZhuangShi","jp":"sjzs","provName":"河北","provCode":"130000","license":"冀A"},
                {"cityName":"唐山市","cityCode":"130200","pinyin":"TangShanShi","jp":"tss","provName":"河北","provCode":"130000","license":"冀B"},
                {"cityName":"秦皇岛市","cityCode":"130300","pinyin":"HuangDaoShi","jp":"hds","provName":"河北","provCode":"130000","license":"冀C"},
                {"cityName":"邯郸市","cityCode":"130400","pinyin":"HanDanShi","jp":"hds","provName":"河北","provCode":"130000","license":"冀D"},
                {"cityName":"邢台市","cityCode":"130500","pinyin":"XingTaiShi","jp":"xts","provName":"河北","provCode":"130000","license":"冀E"},
                {"cityName":"保定市","cityCode":"130600","pinyin":"BaoDingShi","jp":"bds","provName":"河北","provCode":"130000","license":"冀F"},
                {"cityName":"张家口市","cityCode":"130700","pinyin":"ZhangJiaKouShi","jp":"zjks","provName":"河北","provCode":"130000","license":"冀G"},
                {"cityName":"承德市","cityCode":"130800","pinyin":"ChengDeShi","jp":"cds","provName":"河北","provCode":"130000","license":"冀H"},
                {"cityName":"沧州市","cityCode":"130900","pinyin":"CangZhouShi","jp":"czs","provName":"河北","provCode":"130000","license":"冀J"},
                {"cityName":"廊坊市","cityCode":"131000","pinyin":"LangFangShi","jp":"lfs","provName":"河北","provCode":"130000","license":"冀R"},
                {"cityName":"衡水市","cityCode":"131100","pinyin":"HengShuiShi","jp":"hss","provName":"河北","provCode":"130000","license":"冀T"},
                {"cityName":"武汉市","cityCode":"420100","pinyin":"WuHanShi","jp":"whs","provName":"湖北","provCode":"420000","license":"鄂A"},
                {"cityName":"黄石市","cityCode":"420200","pinyin":"HuangShiShi","jp":"hss","provName":"湖北","provCode":"420000","license":"鄂B"},
                {"cityName":"十堰市","cityCode":"420300","pinyin":"ShiYanShi","jp":"sys","provName":"湖北","provCode":"420000","license":"鄂C"},
                {"cityName":"宜昌市","cityCode":"420500","pinyin":"YiChangShi","jp":"ycs","provName":"湖北","provCode":"420000","license":"鄂E"},
                {"cityName":"襄樊市","cityCode":"420600","pinyin":"XiangFanShi","jp":"xfs","provName":"湖北","provCode":"420000","license":"鄂F"},
                {"cityName":"鄂州市","cityCode":"420700","pinyin":"ezhoushi","jp":"ezs","provName":"湖北","provCode":"420000","license":"鄂G"},
                {"cityName":"荆门市","cityCode":"420800","pinyin":"JingMenShi","jp":"jms","provName":"湖北","provCode":"420000","license":"鄂H"},
                {"cityName":"孝感市","cityCode":"420900","pinyin":"XiaoGanshi","jp":"xgs","provName":"湖北","provCode":"420000","license":"鄂K"},
                {"cityName":"荆州市","cityCode":"421000","pinyin":"JingZhouShi","jp":"jzs","provName":"湖北","provCode":"420000","license":"鄂D"},
                {"cityName":"黄冈市","cityCode":"421100","pinyin":"HuangGangShi","jp":"hgs","provName":"湖北","provCode":"420000","license":"鄂J"},
                {"cityName":"咸宁市","cityCode":"421200","pinyin":"xianningshi","jp":"xns","provName":"湖北","provCode":"420000","license":"鄂L"},
                {"cityName":"随州市","cityCode":"421300","pinyin":"suizhoushi","jp":"szs","provName":"湖北","provCode":"420000","license":"鄂S"},
                {"cityName":"恩施市","cityCode":"422800","pinyin":"enshishi","jp":"ess","provName":"湖北","provCode":"420000","license":"鄂Q"},
                {"cityName":"仙桃市","cityCode":"429000","pinyin":"XianTaoShi","jp":"xts","provName":"湖北","provCode":"420000","license":"鄂M"},
                {"cityName":"潜江市","cityCode":"429000","pinyin":"QianJiangShi","jp":"qjs","provName":"湖北","provCode":"420000","license":"鄂N"},
                {"cityName":"天门市","cityCode":"429000","pinyin":"TianMenShi","jp":"tms","provName":"湖北","provCode":"420000","license":"鄂R"},
                {"cityName":"神农架林区","cityCode":"429000","pinyin":"ShenNongJiaLinQu","jp":"snjlq","provName":"湖北","provCode":"420000","license":"鄂P"},
                {"cityName":"合肥市","cityCode":"340100","pinyin":"HeFeiShi","jp":"hfs","provName":"安徽","provCode":"340000","license":"皖A"},
                {"cityName":"芜湖市","cityCode":"340200","pinyin":"WuHuShi","jp":"whs","provName":"安徽","provCode":"340000","license":"皖B"},
                {"cityName":"蚌埠市","cityCode":"340300","pinyin":"BengBuShi","jp":"bbs","provName":"安徽","provCode":"340000","license":"皖C"},
                {"cityName":"淮南市","cityCode":"340400","pinyin":"HuaiNanShi","jp":"hns","provName":"安徽","provCode":"340000","license":"皖D"},
                {"cityName":"马鞍山市","cityCode":"340500","pinyin":"maanshan","jp":"mas","provName":"安徽","provCode":"340000","license":"皖E"},
                {"cityName":"淮北市","cityCode":"340600","pinyin":"huibeishi","jp":"hbs","provName":"安徽","provCode":"340000","license":"皖F"},
                {"cityName":"铜陵市","cityCode":"340700","pinyin":"tonglingshi","jp":"tls","provName":"安徽","provCode":"340000","license":"皖G"},
                {"cityName":"安庆市","cityCode":"340800","pinyin":"AnQingShi","jp":"aqs","provName":"安徽","provCode":"340000","license":"皖H"},
                {"cityName":"黄山市","cityCode":"341000","pinyin":"huangshanshi","jp":"hss","provName":"安徽","provCode":"340000","license":"皖J"},
                {"cityName":"滁州市","cityCode":"341100","pinyin":"ChuZhouShi","jp":"czs","provName":"安徽","provCode":"340000","license":"皖M"},
                {"cityName":"阜阳市","cityCode":"341200","pinyin":"FuYangShi","jp":"fys","provName":"安徽","provCode":"340000","license":"皖K"},
                {"cityName":"宿州市","cityCode":"341300","pinyin":"suzhoushi","jp":"szs","provName":"安徽","provCode":"340000","license":"皖L"},
                //{"cityName":"巢湖市","cityCode":"341400","pinyin":"chaohushi","jp":"chs","provName":"安徽","provCode":"340000","license":"皖Q"},
                {"cityName":"六安市","cityCode":"341500","pinyin":"liuanshi","jp":"las","provName":"安徽","provCode":"340000","license":"皖N"},
                {"cityName":"亳州市","cityCode":"341600","pinyin":"bozhoushi","jp":"bzs","provName":"安徽","provCode":"340000","license":"皖S"},
                {"cityName":"池州市","cityCode":"341700","pinyin":"chizhoushi","jp":"czs","provName":"安徽","provCode":"340000","license":"皖R"},
                {"cityName":"宣城市","cityCode":"341800","pinyin":"xuanchengshi","jp":"xcs","provName":"安徽","provCode":"340000","license":"皖P"},
                {"cityName":"长沙市","cityCode":"430100","pinyin":"ChangShaShi","jp":"css","provName":"湖南","provCode":"430000","license":"湘A"},
                {"cityName":"株洲市","cityCode":"430200","pinyin":"ZhuZhouShi","jp":"zzs","provName":"湖南","provCode":"430000","license":"湘B"},
                {"cityName":"湘潭市","cityCode":"430300","pinyin":"XiangTanShi","jp":"xts","provName":"湖南","provCode":"430000","license":"湘C"},
                {"cityName":"衡阳市","cityCode":"430400","pinyin":"HengYangShi","jp":"hys","provName":"湖南","provCode":"430000","license":"湘D"},
                {"cityName":"邵阳市","cityCode":"430500","pinyin":"ShaoYangShi","jp":"sys","provName":"湖南","provCode":"430000","license":"湘E"},
                {"cityName":"岳阳市","cityCode":"430600","pinyin":"YueYangShi","jp":"yys","provName":"湖南","provCode":"430000","license":"湘F"},
                {"cityName":"常德市","cityCode":"430700","pinyin":"ChangDeShi","jp":"cds","provName":"湖南","provCode":"430000","license":"湘J"},
                {"cityName":"张家界市","cityCode":"430800","pinyin":"ZhangJiaJieShi","jp":"zjjs","provName":"湖南","provCode":"430000","license":"湘G"},
                {"cityName":"益阳市","cityCode":"430900","pinyin":"yiyangshi","jp":"yys","provName":"湖南","provCode":"430000","license":"湘H"},
                {"cityName":"郴州市","cityCode":"431000","pinyin":"ChenZhouShi","jp":"czs","provName":"湖南","provCode":"430000","license":"湘L"},
                {"cityName":"永州市","cityCode":"431100","pinyin":"YongZhouShi","jp":"yzs","provName":"湖南","provCode":"430000","license":"湘M"},
                {"cityName":"怀化市","cityCode":"431200","pinyin":"HuaiHuaShi","jp":"hhs","provName":"湖南","provCode":"430000","license":"湘N"},
                {"cityName":"娄底市","cityCode":"431300","pinyin":"LouDiShi","jp":"lds","provName":"湖南","provCode":"430000","license":"湘K"},
                {"cityName":"湘西自治州","cityCode":"433100","pinyin":"XiangXiZiZhiZhou","jp":"xxzzz","provName":"湖南","provCode":"430000","license":"湘U"},
                {"cityName":"福州市","cityCode":"350100","pinyin":"FuZhouShi","jp":"fzs","provName":"福建","provCode":"350000","license":"闽A"},
                {"cityName":"厦门市","cityCode":"850200","pinyin":"XiaMenShi","jp":"xms","provName":"福建","provCode":"350000","license":"闽D"},
                {"cityName":"莆田市","cityCode":"350300","pinyin":"PuTianShi","jp":"pts","provName":"福建","provCode":"350000","license":"闽B"},
                {"cityName":"三明市","cityCode":"350400","pinyin":"SanMingShi","jp":"sms","provName":"福建","provCode":"350000","license":"闽G"},
                {"cityName":"泉州市","cityCode":"350500","pinyin":"QuanZhouShi","jp":"qzs","provName":"福建","provCode":"350000","license":"闽C"},
                {"cityName":"漳州市","cityCode":"350600","pinyin":"ZhangZhouShi","jp":"zzs","provName":"福建","provCode":"350000","license":"闽E"},
                {"cityName":"南平市","cityCode":"350700","pinyin":"nanpingshi","jp":"nps","provName":"福建","provCode":"350000","license":"闽H"},
                {"cityName":"龙岩市","cityCode":"350800","pinyin":"longyan","jp":"lys","provName":"福建","provCode":"350000","license":"闽F"},
                {"cityName":"宁德市","cityCode":"350900","pinyin":"ningdeshi","jp":"nds","provName":"福建","provCode":"350000","license":"闽J"},
                {"cityName":"沈阳市","cityCode":"210100","pinyin":"ShenYangShi","jp":"sys","provName":"辽宁","provCode":"210000","license":"辽A"},
                {"cityName":"大连市","cityCode":"840200","pinyin":"DaLianShi","jp":"dls","provName":"辽宁","provCode":"210000","license":"辽B"},
                {"cityName":"鞍山市","cityCode":"210300","pinyin":"AnShanShi","jp":"ass","provName":"辽宁","provCode":"210000","license":"辽C"},
                {"cityName":"抚顺市","cityCode":"210400","pinyin":"FuShunShi","jp":"fss","provName":"辽宁","provCode":"210000","license":"辽D"},
                {"cityName":"本溪市","cityCode":"210500","pinyin":"BenXiShi","jp":"bxs","provName":"辽宁","provCode":"210000","license":"辽E"},
                {"cityName":"丹东市","cityCode":"210600","pinyin":"dandongshi","jp":"dds","provName":"辽宁","provCode":"210000","license":"辽F"},
                {"cityName":"锦州市","cityCode":"210700","pinyin":"JinZhouShi","jp":"jzs","provName":"辽宁","provCode":"210000","license":"辽G"},
                {"cityName":"营口市","cityCode":"210800","pinyin":"YingKouShi","jp":"yks","provName":"辽宁","provCode":"210000","license":"辽H"},
                {"cityName":"阜新市","cityCode":"210900","pinyin":"FuXinShi","jp":"fxs","provName":"辽宁","provCode":"210000","license":"辽J"},
                {"cityName":"辽阳市","cityCode":"211000","pinyin":"LiaoYangShi","jp":"lys","provName":"辽宁","provCode":"210000","license":"辽K"},
                {"cityName":"盘锦市","cityCode":"211100","pinyin":"PanJinShi","jp":"pjs","provName":"辽宁","provCode":"210000","license":"辽L"},
                {"cityName":"铁岭市","cityCode":"211200","pinyin":"TieLinShi","jp":"tls","provName":"辽宁","provCode":"210000","license":"辽M"},
                {"cityName":"朝阳市","cityCode":"211300","pinyin":"ZhaoYangShi","jp":"zys","provName":"辽宁","provCode":"210000","license":"辽N"},
                {"cityName":"葫芦岛市","cityCode":"211400","pinyin":"HuLuDaoShi","jp":"hlds","provName":"辽宁","provCode":"210000","license":"辽P"},
                {"cityName":"哈尔滨市","cityCode":"230100","pinyin":"HaErBinShi","jp":"hebs","provName":"黑龙江","provCode":"230000","license":"黑A"},
                // {"cityName":"齐齐哈尔市","cityCode":"230200","pinyin":"QiQiHaErShi","jp":"qqhes","provName":"黑龙江","provCode":"230000","license":"黑B"},
                // {"cityName":"鸡西市","cityCode":"230300","pinyin":"JiXiShi","jp":"jxs","provName":"黑龙江","provCode":"230000","license":"黑G"},
                // {"cityName":"鹤岗市","cityCode":"230400","pinyin":"HeGangShi","jp":"hgs","provName":"黑龙江","provCode":"230000","license":"黑H"},
                // {"cityName":"双鸭山市","cityCode":"230500","pinyin":"ShuangYaShanShi","jp":"syss","provName":"黑龙江","provCode":"230000","license":"黑J"},
                // {"cityName":"大庆市","cityCode":"230600","pinyin":"DaQingShi","jp":"dqs","provName":"黑龙江","provCode":"230000","license":"黑E"},
                // {"cityName":"伊春市","cityCode":"230700","pinyin":"YiChunShi","jp":"ycs","provName":"黑龙江","provCode":"230000","license":"黑F"},
                // {"cityName":"佳木斯市","cityCode":"230800","pinyin":"JiaMuSiShi","jp":"jmss","provName":"黑龙江","provCode":"230000","license":"黑D"},
                // {"cityName":"七台河市","cityCode":"230900","pinyin":"QiTaiHeShi","jp":"qths","provName":"黑龙江","provCode":"230000","license":"黑K"},
                // {"cityName":"牡丹江市","cityCode":"231000","pinyin":"MuDanJiangShi","jp":"mdjs","provName":"黑龙江","provCode":"230000","license":"黑C"},
                // {"cityName":"黑河市","cityCode":"231100","pinyin":"HeiHeShi","jp":"hhs","provName":"黑龙江","provCode":"230000","license":"黑N"},
                // {"cityName":"绥化市","cityCode":"231200","pinyin":"SuiHuaShi","jp":"shs","provName":"黑龙江","provCode":"230000","license":"黑M"},
                // {"cityName":"大兴安岭市","cityCode":"232700","pinyin":"DaXingAnLingShi","jp":"dxals","provName":"黑龙江","provCode":"230000","license":"黑P"},
                {"cityName":"西安市","cityCode":"610100","pinyin":"XiAnShi","jp":"xas","provName":"陕西","provCode":"610000","license":"陕A"},
                {"cityName":"铜川市","cityCode":"610200","pinyin":"TongChuanShi","jp":"tcs","provName":"陕西","provCode":"610000","license":"陕B"},
                {"cityName":"宝鸡市","cityCode":"610300","pinyin":"BaoJiShi","jp":"bjs","provName":"陕西","provCode":"610000","license":"陕C"},
                {"cityName":"咸阳市","cityCode":"610400","pinyin":"XianYangShi","jp":"xys","provName":"陕西","provCode":"610000","license":"陕D"},
                {"cityName":"渭南市","cityCode":"610500","pinyin":"WeiNanShi","jp":"wns","provName":"陕西","provCode":"610000","license":"陕E"},
                {"cityName":"延安市","cityCode":"610600","pinyin":"YanAnShi","jp":"yas","provName":"陕西","provCode":"610000","license":"陕J"},
                {"cityName":"汉中市","cityCode":"610700","pinyin":"HanZhongShi","jp":"hzs","provName":"陕西","provCode":"610000","license":"陕F"},
                {"cityName":"榆林市","cityCode":"610800","pinyin":"yulinshi","jp":"yls","provName":"陕西","provCode":"610000","license":"陕K"},
                {"cityName":"安康市","cityCode":"610900","pinyin":"ankangshi","jp":"aks","provName":"陕西","provCode":"610000","license":"陕G"},
                {"cityName":"商洛市","cityCode":"611000","pinyin":"shangluoshi","jp":"sls","provName":"陕西","provCode":"610000","license":"陕H"},
                {"cityName":"太原市","cityCode":"140100","pinyin":"TaiYuanShi","jp":"tys","provName":"山西","provCode":"140000","license":"晋A"},
                {"cityName":"大同市","cityCode":"140200","pinyin":"DaTongShi","jp":"dts","provName":"山西","provCode":"140000","license":"晋B"},
                {"cityName":"阳泉市","cityCode":"140300","pinyin":"YangQuanShi","jp":"yqs","provName":"山西","provCode":"140000","license":"晋C"},
                {"cityName":"长治市","cityCode":"140400","pinyin":"ChangZhiShi","jp":"czs","provName":"山西","provCode":"140000","license":"晋D"},
                {"cityName":"晋城市","cityCode":"140500","pinyin":"JinChengShi","jp":"jcs","provName":"山西","provCode":"140000","license":"晋E"},
                {"cityName":"朔州市","cityCode":"140600","pinyin":"ShuoZhouShi","jp":"szs","provName":"山西","provCode":"140000","license":"晋F"},
                {"cityName":"晋中市","cityCode":"140700","pinyin":"jinzhongshi","jp":"jzs","provName":"山西","provCode":"140000","license":"晋K"},
                {"cityName":"运城市","cityCode":"140800","pinyin":"YunChengShi","jp":"ycs","provName":"山西","provCode":"140000","license":"晋M"},
                //{"cityName":"忻州市","cityCode":"140900","pinyin":"xinzhoushi","jp":"xzs","provName":"山西","provCode":"140000","license":"晋H"},
                {"cityName":"临汾市","cityCode":"141000","pinyin":"linfenshi","jp":"lfs","provName":"山西","provCode":"140000","license":"晋L"},
                {"cityName":"吕梁市","cityCode":"141100","pinyin":"LvLiangShi","jp":"lls","provName":"山西","provCode":"140000","license":"晋J"},
                // {"cityName":"南昌市","cityCode":"360100","pinyin":"NanChangShi","jp":"ncs","provName":"江西","provCode":"360000","license":"赣A"},
                // {"cityName":"景德镇市","cityCode":"360200","pinyin":"JingDeZhenShi","jp":"jdzs","provName":"江西","provCode":"360000","license":"赣H"},
                // {"cityName":"萍乡市","cityCode":"360300","pinyin":"PingXiangShi","jp":"pxs","provName":"江西","provCode":"360000","license":"赣J"},
                // {"cityName":"九江市","cityCode":"360400","pinyin":"JiuJiangShi","jp":"jjs","provName":"江西","provCode":"360000","license":"赣G"},
                // {"cityName":"新余市","cityCode":"360500","pinyin":"xinyushi","jp":"xys","provName":"江西","provCode":"360000","license":"赣K"},
                // {"cityName":"鹰潭市","cityCode":"360600","pinyin":"YingTanShi","jp":"yts","provName":"江西","provCode":"360000","license":"赣L"},
                // {"cityName":"赣州市","cityCode":"360700","pinyin":"GanZhouShi","jp":"gzs","provName":"江西","provCode":"360000","license":"赣B"},
                // {"cityName":"吉安市","cityCode":"360800","pinyin":"JiAnShi","jp":"jas","provName":"江西","provCode":"360000","license":"赣D"},
                // {"cityName":"宜春市","cityCode":"360900","pinyin":"YiChunShi","jp":"ycs","provName":"江西","provCode":"360000","license":"赣C"},
                // {"cityName":"抚州市","cityCode":"361000","pinyin":"FuZhouShi","jp":"fzs","provName":"江西","provCode":"360000","license":"赣F"},
                // {"cityName":"上饶市","cityCode":"361100","pinyin":"ShangRaoShi","jp":"srs","provName":"江西","provCode":"360000","license":"赣E"},
                {"cityName":"昆明市","cityCode":"530100","pinyin":"KunMingShi","jp":"kms","provName":"云南","provCode":"530000","license":"云A"},
                {"cityName":"曲靖市","cityCode":"530300","pinyin":"QuJingShi","jp":"qjs","provName":"云南","provCode":"530000","license":"云D"},
                // {"cityName":"玉溪市","cityCode":"530400","pinyin":"YuXiShi","jp":"yxs","provName":"云南","provCode":"530000","license":"云F"},
                // {"cityName":"保山市","cityCode":"530500","pinyin":"BaoShanShi","jp":"bss","provName":"云南","provCode":"530000","license":"云M"},
                // {"cityName":"昭通市","cityCode":"530600","pinyin":"ZhaoTongShi","jp":"zts","provName":"云南","provCode":"530000","license":"云C"},
                // {"cityName":"普洱市","cityCode":"530821","pinyin":"PuErShi","jp":"pes","provName":"云南","provCode":"530000","license":"云J"},
                // {"cityName":"临沧市","cityCode":"530900","pinyin":"LinCangShi","jp":"lcs","provName":"云南","provCode":"530000","license":"云S"},
                // {"cityName":"楚雄市","cityCode":"532301","pinyin":"ChuXiongShi","jp":"cxs","provName":"云南","provCode":"530000","license":"云E"},
                // {"cityName":"红河市","cityCode":"532500","pinyin":"HongHeShi","jp":"hhs","provName":"云南","provCode":"530000","license":"云G"},
                {"cityName":"文山市","cityCode":"532600","pinyin":"WenShanShi","jp":"wss","provName":"云南","provCode":"530000","license":"云H"},
                // {"cityName":"西双版纳市","cityCode":"532800","pinyin":"XiShuangBanNaShi","jp":"xsbns","provName":"云南","provCode":"530000","license":"云K"},
                // {"cityName":"大理市","cityCode":"532901","pinyin":"DaLiShi","jp":"dls","provName":"云南","provCode":"530000","license":"云L"},
                // {"cityName":"德宏市","cityCode":"533100","pinyin":"DeHongShi","jp":"dhs","provName":"云南","provCode":"530000","license":"云N"},
                {"cityName":"长春市","cityCode":"220100","pinyin":"ChangChunShi","jp":"ccs","provName":"吉林","provCode":"220000","license":"吉A"},
                // {"cityName":"吉林市","cityCode":"220200","pinyin":"JiLinShi","jp":"jls","provName":"吉林","provCode":"220000","license":"吉B"},
                // {"cityName":"四平市","cityCode":"220300","pinyin":"SiPingShi","jp":"sps","provName":"吉林","provCode":"220000","license":"吉C"},
                // {"cityName":"辽源市","cityCode":"220400","pinyin":"LiaoYuanShi","jp":"lys","provName":"吉林","provCode":"220000","license":"吉D"},
                // {"cityName":"通化市","cityCode":"220500","pinyin":"TongHuaShi","jp":"ths","provName":"吉林","provCode":"220000","license":"吉E"},
                // {"cityName":"白山市","cityCode":"220600","pinyin":"BaiShanShi","jp":"bss","provName":"吉林","provCode":"220000","license":"吉F"},
                // {"cityName":"松原市","cityCode":"220700","pinyin":"SongYuanShi","jp":"sys","provName":"吉林","provCode":"220000","license":"吉J"},
                // {"cityName":"白城市","cityCode":"220800","pinyin":"BaiChengShi","jp":"bcs","provName":"吉林","provCode":"220000","license":"吉G"},
                // {"cityName":"延边市","cityCode":"222400","pinyin":"YanBianShi","jp":"ybs","provName":"吉林","provCode":"220000","license":"吉H"},
                {"cityName":"乌鲁木齐","cityCode":"650100","pinyin":"wulumuqi","jp":"wlmq","provName":"新疆","provCode":"650000","license":"新A"},
                {"cityName":"克拉玛依","cityCode":"650200","pinyin":"KeLaMaYi","jp":"klmy","provName":"新疆","provCode":"650000","license":"新J"},
                {"cityName":"吐鲁番地区","cityCode":"652100","pinyin":"TuLuFan","jp":"tlf","provName":"新疆","provCode":"650000","license":"新K"},
                {"cityName":"哈密地区","cityCode":"652200","pinyin":"hami","jp":"hm","provName":"新疆","provCode":"650000","license":"新L"},
                {"cityName":"昌吉","cityCode":"652300","pinyin":"changji","jp":"cj","provName":"新疆","provCode":"650000","license":"新B"},
                {"cityName":"博尔塔拉","cityCode":"652700","pinyin":"boertala","jp":"betl","provName":"新疆","provCode":"650000","license":"新E"},
                {"cityName":"巴音郭楞","cityCode":"652800","pinyin":"BaYinGuoLengMengGuZiZhiZhou","jp":"byglmgzzz","provName":"新疆","provCode":"650000","license":"新M"},
                {"cityName":"阿克苏地区","cityCode":"652900","pinyin":"AKeSuDiQu","jp":"aksdq","provName":"新疆","provCode":"650000","license":"新N"},
                {"cityName":"克孜勒苏柯尔克孜自治州","cityCode":"653000","pinyin":"KeZiLeSuKeErKeZi","jp":"kzlskekz","provName":"新疆","provCode":"650000","license":"新P"},
                {"cityName":"喀什","cityCode":"653100","pinyin":"KaShi","jp":"ks","provName":"新疆","provCode":"650000","license":"新Q"},
                {"cityName":"和田","cityCode":"653200","pinyin":"hetian","jp":"ht","provName":"新疆","provCode":"650000","license":"新R"},
                {"cityName":"伊犁","cityCode":"654000","pinyin":"YiLi","jp":"yl","provName":"新疆","provCode":"650000","license":"新F"},
                {"cityName":"塔城地区","cityCode":"654200","pinyin":"tacheng","jp":"tc","provName":"新疆","provCode":"650000","license":"新G"},
                {"cityName":"阿勒泰地区","cityCode":"654300","pinyin":"ALeTaiDiQu","jp":"altdq","provName":"新疆","provCode":"650000","license":"新H"},
                {"cityName":"奎屯市","cityCode":"658900","pinyin":"KuiTunShi","jp":"kts","provName":"新疆","provCode":"650000","license":"新D"},
                {"cityName":"石河子市","cityCode":"659100","pinyin":"ShiHeZi","jp":"shz","provName":"新疆","provCode":"650000","license":"新C"},
                {"cityName":"阿拉尔市","cityCode":"659200","pinyin":"ALaErShi","jp":"ale","provName":"新疆","provCode":"650000","license":"新"},
                {"cityName":"呼和浩特市","cityCode":"150100","pinyin":"HuHeHaoTeShi","jp":"hhhts","provName":"内蒙古","provCode":"150000","license":"蒙A"},
                {"cityName":"包头市","cityCode":"150200","pinyin":"BaoTouShi","jp":"bts","provName":"内蒙古","provCode":"150000","license":"蒙B"},
                {"cityName":"乌海市","cityCode":"150300","pinyin":"WuHaiShi","jp":"whs","provName":"内蒙古","provCode":"150000","license":"蒙C"},
                {"cityName":"赤峰市","cityCode":"150400","pinyin":"ChiFengShi","jp":"cfs","provName":"内蒙古","provCode":"150000","license":"蒙D"},
                {"cityName":"通辽市","cityCode":"150500","pinyin":"TongLiaoShi","jp":"tls","provName":"内蒙古","provCode":"150000","license":"蒙G"},
                {"cityName":"鄂尔多斯市","cityCode":"150600","pinyin":"EErDuoSiShi","jp":"eedss","provName":"内蒙古","provCode":"150000","license":"蒙K"},
                {"cityName":"呼伦贝尔市","cityCode":"150700","pinyin":"HuLunBeiEr","jp":"hlbe","provName":"内蒙古","provCode":"150000","license":"蒙E"},
                {"cityName":"巴彦淖尔市","cityCode":"150800","pinyin":"BaYanNaoErShi","jp":"bynes","provName":"内蒙古","provCode":"150000","license":"蒙L"},
                {"cityName":"乌兰察布市","cityCode":"150900","pinyin":"WuLanChaBuShi","jp":"wlcbs","provName":"内蒙古","provCode":"150000","license":"蒙J"},
                {"cityName":"兴安盟","cityCode":"152200","pinyin":"XingAnShi","jp":"xas","provName":"内蒙古","provCode":"150000","license":"蒙F"},
                //{"cityName":"乌兰浩特市","cityCode":"152201","pinyin":"wulanhaoteshi","jp":"wlhts","provName":"内蒙古","provCode":"150000","license":"蒙F"},
                {"cityName":"锡林郭勒","cityCode":"152500","pinyin":"Xilinguole","jp":"xlgl","provName":"内蒙古","provCode":"150000","license":"蒙H"},
                //{"cityName":"锡林浩特市","cityCode":"152502","pinyin":"XiLinHaoTeShi","jp":"xlhts","provName":"内蒙古","provCode":"150000","license":"蒙H"},
                {"cityName":"阿拉善盟","cityCode":"152900","pinyin":"ALaShanMeng","jp":"alsm","provName":"内蒙古","provCode":"150000","license":"蒙M"},
                {"cityName":"南宁市","cityCode":"450100","pinyin":"NanNingShi","jp":"nns","provName":"广西","provCode":"450000","license":"桂A"},
                {"cityName":"柳州市","cityCode":"450200","pinyin":"LiuZhouShi","jp":"lzs","provName":"广西","provCode":"450000","license":"桂B"},
                {"cityName":"桂林市","cityCode":"450300","pinyin":"GuiLinShi","jp":"gls","provName":"广西","provCode":"450000","license":"桂C"},
                //{"cityName":"梧州市","cityCode":"450400","pinyin":"WuZhouShi","jp":"wzs","provName":"广西","provCode":"450000","license":"桂D"},
                {"cityName":"北海市","cityCode":"450500","pinyin":"BeiHaiShi","jp":"bhs","provName":"广西","provCode":"450000","license":"桂E"},
                // {"cityName":"防城港市","cityCode":"450600","pinyin":"fangchenggang","jp":"fcg","provName":"广西","provCode":"450000","license":"桂P"},
                // {"cityName":"钦州市","cityCode":"450700","pinyin":"QinZhouShi","jp":"qzs","provName":"广西","provCode":"450000","license":"桂N"},
                // {"cityName":"贵港市","cityCode":"450800","pinyin":"GuiGangShi","jp":"ggs","provName":"广西","provCode":"450000","license":"桂R"},
                {"cityName":"玉林市","cityCode":"450900","pinyin":"YuLinShi","jp":"yls","provName":"广西","provCode":"450000","license":"桂K"},
                // {"cityName":"百色市","cityCode":"451000","pinyin":"BaiSeShi","jp":"bss","provName":"广西","provCode":"450000","license":"桂L"},
                // {"cityName":"贺州市","cityCode":"451100","pinyin":"hezhou","jp":"hz","provName":"广西","provCode":"450000","license":"桂J"},
                // {"cityName":"河池市","cityCode":"451200","pinyin":"hechi","jp":"hc","provName":"广西","provCode":"450000","license":"桂M"},
                // {"cityName":"来宾市","cityCode":"451300","pinyin":"laibin","jp":"lb","provName":"广西","provCode":"450000","license":"桂G"},
                // {"cityName":"崇左市","cityCode":"451400","pinyin":"chongzuo","jp":"cz","provName":"广西","provCode":"450000","license":"桂F"},
                {"cityName":"兰州市","cityCode":"620100","pinyin":"LanZhouShi","jp":"lzs","provName":"甘肃","provCode":"620000","license":"甘A"},
                {"cityName":"嘉峪关市","cityCode":"620200","pinyin":"jiayuguanshi","jp":"jyg","provName":"甘肃","provCode":"620000","license":"甘B"},
                {"cityName":"金昌市","cityCode":"620300","pinyin":"jinchangshi","jp":"jcs","provName":"甘肃","provCode":"620000","license":"甘C"},
                {"cityName":"白银市","cityCode":"620400","pinyin":"BaiYinShi","jp":"bys","provName":"甘肃","provCode":"620000","license":"甘D"},
                {"cityName":"天水市","cityCode":"620500","pinyin":"tianshuishi","jp":"tss","provName":"甘肃","provCode":"620000","license":"甘E"},
                {"cityName":"武威市","cityCode":"620600","pinyin":"wuweishi","jp":"wws","provName":"甘肃","provCode":"620000","license":"甘E"},
                {"cityName":"张掖市","cityCode":"620700","pinyin":"zhangyeshi","jp":"zys","provName":"甘肃","provCode":"620000","license":"甘G"},
                {"cityName":"平凉市","cityCode":"620800","pinyin":"pingliangshi","jp":"pls","provName":"甘肃","provCode":"620000","license":"甘L"},
                {"cityName":"酒泉市","cityCode":"620900","pinyin":"JiuQuanShi","jp":"jqs","provName":"甘肃","provCode":"620000","license":"甘F"},
                {"cityName":"庆阳市","cityCode":"621000","pinyin":"qingyangshi","jp":"qys","provName":"甘肃","provCode":"620000","license":"甘M"},
                {"cityName":"定西市","cityCode":"621100","pinyin":"DingXiShi","jp":"dxs","provName":"甘肃","provCode":"620000","license":"甘J"},
                {"cityName":"陇南市","cityCode":"621200","pinyin":"longnanshi","jp":"lns","provName":"甘肃","provCode":"620000","license":"甘K"},
                {"cityName":"临夏市","cityCode":"622900","pinyin":"linxiashi","jp":"lxs","provName":"甘肃","provCode":"620000","license":"甘N"},
                {"cityName":"甘南","cityCode":"623000","pinyin":"gannan","jp":"gn","provName":"甘肃","provCode":"620000","license":"甘P"}
                // {"cityName":"贵阳市","cityCode":"520100","pinyin":"GuiYangShi","jp":"gys","provName":"贵州","provCode":"520000","license":"贵A"},
                // {"cityName":"六盘水市","cityCode":"520200","pinyin":"LiuPanShuiShi","jp":"lpss","provName":"贵州","provCode":"520000","license":"贵B"},
                // {"cityName":"遵义市","cityCode":"520300","pinyin":"ZunYiShi","jp":"zys","provName":"贵州","provCode":"520000","license":"贵C"},
                // {"cityName":"安顺市","cityCode":"520400","pinyin":"AnShunShi","jp":"ass","provName":"贵州","provCode":"520000","license":"贵G"},
                // {"cityName":"铜仁市","cityCode":"522200","pinyin":"tongrenshi","jp":"trs","provName":"贵州","provCode":"520000","license":"贵D"},
                // {"cityName":"兴义市","cityCode":"522300","pinyin":"xingyishi","jp":"xys","provName":"贵州","provCode":"520000","license":"贵E"},
                // {"cityName":"黔西南","cityCode":"522300","pinyin":"xingyishi","jp":"xys","provName":"贵州","provCode":"520000","license":"贵E"},
                // {"cityName":"毕节市","cityCode":"522400","pinyin":"BiJieShi","jp":"bjs","provName":"贵州","provCode":"520000","license":"贵F"},
                // {"cityName":"凯里市","cityCode":"522600","pinyin":"kailishi","jp":"kls","provName":"贵州","provCode":"520000","license":"贵H"},
                // {"cityName":"黔东南","cityCode":"522600","pinyin":"kailishi","jp":"kls","provName":"贵州","provCode":"520000","license":"贵H"},
                // {"cityName":"都匀市","cityCode":"522700","pinyin":"duyunshi","jp":"dys","provName":"贵州","provCode":"520000","license":"贵J"},
                // {"cityName":"海口市","cityCode":"460100","pinyin":"HaiKouShi","jp":"hks","provName":"海南","provCode":"460000","license":"琼A"},
                // {"cityName":"三亚市","cityCode":"460200","pinyin":"SanYaShi","jp":"sys","provName":"海南","provCode":"460000","license":"琼B"},
                // {"cityName":"五指山市","cityCode":"469001","pinyin":"WuZhiShanShi","jp":"wzss","provName":"海南","provCode":"460000","license":"琼D"},
                // {"cityName":"琼海市","cityCode":"469002","pinyin":"QiongHaiShi","jp":"qhs","provName":"海南","provCode":"460000","license":"琼C"},
                // {"cityName":"儋州市","cityCode":"469003","pinyin":"DanZhouShi","jp":"dzs","provName":"海南","provCode":"460000","license":"琼C"},
                // {"cityName":"文昌市","cityCode":"469005","pinyin":"WenChangShi","jp":"wcs","provName":"海南","provCode":"460000","license":"琼C"},
                // {"cityName":"万宁市","cityCode":"469006","pinyin":"WanNingShi","jp":"wns","provName":"海南","provCode":"460000","license":"琼C"},
                // {"cityName":"东方市","cityCode":"469007","pinyin":"DongFangShi","jp":"dfs","provName":"海南","provCode":"460000","license":"琼D"},
                // {"cityName":"澄迈市","cityCode":"469027","pinyin":"ChengMaiShi","jp":"cms","provName":"海南","provCode":"460000","license":"琼C"},
                // {"cityName":"昌江市","cityCode":"469031","pinyin":"ChangJiangShi","jp":"cjs","provName":"海南","provCode":"460000","license":"琼D"},
                // {"cityName":"乐东市","cityCode":"469033","pinyin":"LeDongShi","jp":"lds","provName":"海南","provCode":"460000","license":"琼D"},
                // {"cityName":"琼中市","cityCode":"469036","pinyin":"QiongZhongShi","jp":"qzs","provName":"海南","provCode":"460000","license":"琼D"},
                // {"cityName":"银川","cityCode":"640100","pinyin":"YinChuan","jp":"yc","provName":"宁夏","provCode":"640000","license":"宁A"},
                // {"cityName":"石嘴山市","cityCode":"640200","pinyin":"shizuishanshi","jp":"szss","provName":"宁夏","provCode":"640000","license":"宁B"},
                // {"cityName":"吴忠市","cityCode":"640300","pinyin":"wuzhongshi","jp":"wzs","provName":"宁夏","provCode":"640000","license":"宁C"},
                // {"cityName":"固原市","cityCode":"640400","pinyin":"guyuanshi","jp":"gys","provName":"宁夏","provCode":"640000","license":"宁D"},
                // {"cityName":"中卫市","cityCode":"640500","pinyin":"zhongweishi","jp":"zws","provName":"宁夏","provCode":"640000","license":"宁E"},
                // {"cityName":"西宁市","cityCode":"630100","pinyin":"XiNingShi","jp":"xns","provName":"青海","provCode":"630000","license":"青A"},
                // {"cityName":"海东地区","cityCode":"632100","pinyin":"HaiDongShi","jp":"hds","provName":"青海","provCode":"630000","license":"青B"},
                // {"cityName":"海西","cityCode":"632800","pinyin":"haiximenggu","jp":"hxmg","provName":"青海","provCode":"630000","license":"青H"},
                // {"cityName":"拉萨市","cityCode":"540100","pinyin":"LaSaShi","jp":"lss","provName":"西藏","provCode":"540000","license":"藏A"}
            ];

        };

        init.prototype = {
            show : function () {
                this.createBox();
                this.box.css({
                    "left": this.el.offset().left + 'px',
                    "top": this.el.offset().top + this.el.outerHeight()  + 5 + 'px'
                }).show();
            },

            showCitys : function (provcode) {
                var citysArr = [];
                var i = 0;
                $.each(this.insureCity,function(){
                    if (this.provCode == provcode) {
                        citysArr[i] = '<li><a class="car_city" data-citycode="' + this.cityCode + '" data-carid="' + this.license + '" href="javascript:;">' + this.cityName + '</a></li>';
                        i++;
                    }
                });
                var citysHtml = citysArr.join("");
                this.box.find("#citys_ul").html(citysHtml).show();
            },

            destroy : function () {
                this.box.remove();
                $(document).off("click.ms_carcitys");
            },

            bind : function () {
                var _this = this;
                var $box = this.box;

                //点击空白处关闭弹出窗口
                $(document).on("click.ms_carcitys",function(e){
                    if ($(e.target).closest(".ms_car_citys").length === 0 && !$(e.target).hasClass("ms_car_citys_input")) {
                        _this.destroy();
                    }
                });

                //绑定关闭按钮的操作
                $box.on("click",".close_ms_carcitys",function(){
                    _this.destroy();
                });

                //点击选择不同的省
                $box.on("click",".car_pro",function(){
                    var provcode = $(this).data("provcode");
                    $box.find(".car_pro").removeClass("current");
                    $(this).addClass("current");
                    _this.showCitys(provcode);
                });

                //点击选择不同的城市
                $box.on("click",".car_city",function(){
                    var citycode = $(this).data("citycode");
                    var carid = $(this).data("carid");
                    var text = $(this).text();
                    $(_this.num_input).val(carid).data('n', carid);
                    $(_this.code_input).val(citycode);
                    _this.el.val(text);
                    _this.destroy();
                });
            },

            createBox : function (html) {
                var hotCityArr = [];
                var hotCity = this.insureHotCity;
                for (var i = 0, len = hotCity.length; i < len; i++) {
                    hotCityArr[i] = '<li><a href="javascript:;" class="car_city" data-carid="' + hotCity[i].license + '" data-citycode="' + hotCity[i].cityCode + '">' + hotCity[i].cityName + '</a></li>';
                }
                var hotCityHtml = hotCityArr.join("");
                var boxArr = [];
                boxArr[0] = '<div class="hide ms_car_citys"><span class="close_ms_carcitys">X</span><div class="content"><ul class="zxs">';
                boxArr[1] = hotCityHtml;
                boxArr[2] = '</ul><ul class="prov _clearFix">';
                boxArr[3] = '<li><a href="javascript:;" class="car_pro" data-provcode="340000">安徽</a></li>';
                boxArr[4] = '<li><a href="javascript:;" class="car_pro" data-provcode="320000">江苏</a></li>';
                boxArr[5] = '<li><a href="javascript:;" class="car_pro" data-provcode="440000">广东</a></li>';
                boxArr[6] = '<li><a href="javascript:;" class="car_pro" data-provcode="370000">山东</a></li>';
                boxArr[7] = '<li><a href="javascript:;" class="car_pro" data-provcode="510000">四川</a></li>';
                boxArr[8] = '<li><a href="javascript:;" class="car_pro" data-provcode="410000">河南</a></li>';
                boxArr[9] = '<li><a href="javascript:;" class="car_pro" data-provcode="330000">浙江</a></li>';
                boxArr[10] = '<li><a href="javascript:;" class="car_pro" data-provcode="130000">河北</a></li>';
                boxArr[11] = '<li><a href="javascript:;" class="car_pro" data-provcode="420000">湖北</a></li>';
                boxArr[12] = '<li><a href="javascript:;" class="car_pro" data-provcode="430000">湖南</a></li>';
                boxArr[13] = '<li><a href="javascript:;" class="car_pro" data-provcode="350000">福建</a></li>';
                boxArr[14] = '<li><a href="javascript:;" class="car_pro" data-provcode="210000">辽宁</a></li>';
                boxArr[15] = '<li><a href="javascript:;" class="car_pro" data-provcode="230000">黑龙江</a></li>';
                boxArr[16] = '<li><a href="javascript:;" class="car_pro" data-provcode="610000">陕西</a></li>';
                boxArr[17] = '<li><a href="javascript:;" class="car_pro" data-provcode="140000">山西</a></li>';
                boxArr[18] = '<li><a href="javascript:;" class="car_pro" data-provcode="360000">江西</a></li>';
                boxArr[19] = '<li><a href="javascript:;" class="car_pro" data-provcode="530000">云南</a></li>';
                boxArr[20] = '<li><a href="javascript:;" class="car_pro" data-provcode="220000">吉林</a></li>';
                boxArr[21] = '<li><a href="javascript:;" class="car_pro" data-provcode="650000">新疆</a></li>';
                boxArr[22] = '<li><a href="javascript:;" class="car_pro" data-provcode="150000">内蒙古</a></li>';
                boxArr[23] = '<li><a href="javascript:;" class="car_pro" data-provcode="450000">广西</a></li>';
                boxArr[24] = '<li><a href="javascript:;" class="car_pro" data-provcode="620000">甘肃</a></li>';
                boxArr[25] = '<li><a href="javascript:;" class="car_pro" data-provcode="520000">贵州</a></li>';
                boxArr[26] = '<li><a href="javascript:;" class="car_pro" data-provcode="460000">海南</a></li>';
                boxArr[27] = '<li><a href="javascript:;" class="car_pro" data-provcode="640000">宁夏</a></li>';
                boxArr[28] = '<li><a href="javascript:;" class="car_pro" data-provcode="630000">青海</a></li>';
                boxArr[29] = '<li><a href="javascript:;" class="car_pro" data-provcode="540000">西藏</a></li>';
                boxArr[30] = '</ul><ul style="display: none;" id="citys_ul" class="city _clearFix"></ul></div></div>';

                var box = this.box = $(boxArr.join(""));
                $("body").append(box);
            }
        };
        return init;
    }();

    $(".ms_car_citys_input").on("click focus",function(){
        if(!$(".ms_car_citys").length) {
            var thecarCitys = new CarCitys($(this));
        }
    });

});