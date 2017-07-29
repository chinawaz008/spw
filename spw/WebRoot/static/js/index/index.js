/**
 * 文件：首页
 * 作者：无名小强
 * 版本：v0.1
 * 版权：民盛集团
 * 日期：2016-7-14
**/

// 路径配置
require.config({
    paths: {
        echarts: 'http://echarts.baidu.com/build/dist'
    }
});

// 使用
require(
    [
        'echarts',
        'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
    ],
    function (ec) {
        // 基于准备好的dom，初始化echarts图表
        var myChart = ec.init(document.getElementById('main_chart')); 
        
        var option = {
            title : {
                text: '',
                subtext: '实时更新'
            },
            tooltip : {
                trigger: 'axis'
            },
            legend: {
                data:['达成量','目标量']
            },
            toolbox: {
                show : true,
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    magicType : {show: true, type: ['line', 'bar']},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            xAxis : [
                {
                    type : 'category',
                    data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    name:'达成量',
                    type:'bar',
                    data:[800, 300.23, 539.26, 653.45, 759.45, 1002.5, 1305.6, 622.2],
                    markPoint : {
                        data : [
                            {type : 'max', name: '最大值'},
                            {type : 'min', name: '最小值'}
                        ]
                    },
                    markLine : {
                        data : [
                            {type : 'average', name: '平均值'}
                        ]
                    }
                },
                {
                    name:'目标量',
                    type:'bar',
                    data:[800, 500, 600, 600, 800, 1000, 1000, 600, 800,  900, 1000, 1100],
                    
                    markLine : {
                        data : [
                            {type : 'average', name : '平均值'}
                        ]
                    }
                }
            ]
        };

        // 为echarts对象加载数据 
        myChart.setOption(option); 
    }
);

