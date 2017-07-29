/**
 * 文件：首页日历逐渐
 * 作者：无名小强
 * 版本：v0.1
 * 版权：民盛集团
 * 日期：2016-9-20
**/


var Canlendar = React.createClass({
    getInitialState: function() {
        var nowDate = new Date();
        return {
            year : nowDate.getFullYear(),
            month: nowDate.getMonth() + 1,
            day_list: []
        };
    },

    changeMonth: function(n, event) {         //以当前状态为基础，请求并显示新的日历
        
        var that = this;
        
        var d3 = new Date();
        d3.setYear(this.state.year);
        d3.setDate(1);
        d3.setMonth(this.state.month -1 + n);


        var year = d3.getFullYear();
        var month = d3.getMonth() + 1;
        var month_str = (month < 10)? "0" + month : month;
        var datas = year + "-" + month_str;

        var blank = [];
        
        //添加每月投几天的空白格
        var first_day = (d3.getDay() - 1 + 14) % 7;
        for (var k = 0 ; k < first_day; k++) {
            blank.push({date:'-1'});
        }

        $.ajax({   
            url: ctx+"/mreport/getCurrentMonthReport",
            type: "post",
            data: {date: datas},
            dataType: "json",
            success: function(msg){   
                // console.log(msg);
                that.setState({
                    year: year,
                    month: month,
                    day_list: blank.concat(msg)
                });
            }
                
        });
    },

    componentDidMount: function() {
        var that = this;
        var year = this.state.year;
        var month = this.state.month;
        var month_str = (month < 10)? "0" + month : month;
        var datas = year + "-" + month_str;

        var blank = [];
        
        //添加每月投几天的空白格
        var d = new Date(this); 
        d.setYear(year);
        d.setMonth(month - 1);
        d.setDate(1); 
        var first_day = (d.getDay() - 1 + 14) % 7;
        for (var k = 0 ; k < first_day; k++) {
            blank.push({date:'-1'});
        }

        $.ajax({   
            url: ctx+"/mreport/getCurrentMonthReport",
            type: "post",
            data: {date: datas},
            dataType: "json",
            success: function(msg){   
                that.setState({
                    day_list: blank.concat(msg)
                });
            }
                
        });  

    },

    render: function(){
        var week_day_name = ['一', '二', '三', '四', '五', '六', '日'];
        var year = this.state.year;
        var month = this.state.month;
        
        //从后端获取该月每日完成情况
        var day_list = this.state.day_list;

        
        
        //构造日历矩阵
        var table_list = [];
        var line_num = Math.ceil(day_list.length / 7);
        for (var i = 0; i < line_num; i++) {
            var tr_arr = [];
            for (var j = 0; j < 7; j++) {
                var index = i * 7 + j;
                if (index < day_list.length) {
                    tr_arr.push(day_list[index]);
                }
            }
            table_list.push(tr_arr);
        }
        
        return (
            <table className="calendar_table">
                <thead>
                    <tr>
                        <th></th>
                        <th className="go_before"><i className="iconfont icon-iconfontzuojiantou" onClick={this.changeMonth.bind(this, -1)}></i></th>
                        <th colSpan="3" className="month">{year}年{month}月</th>
                        <th className="go_after"><i className="iconfont icon-jiantou" onClick={this.changeMonth.bind(this, 1)}></i></th>
                        <th></th>
                    </tr>
                    <tr className="week_days">
                        {
                            week_day_name.map(function (name) {
                                return <th>{name}</th>
                            })
                        }
                    </tr>
                </thead>
                <tbody>
                    {
                        table_list.map(function (tr_day) {
                        
                            return <tr>{
                                tr_day.map(function (day) {
                                    if (day.date !== '-1') {
                                        var status = day.status;
                                        var class_name = "";
                                        switch(parseInt(status)) {
                                            case 0:
                                                class_name = "future";
                                                break;
                                            case 1:
                                                class_name = "no";
                                                break;
                                            case 2:
                                                class_name = "ok";
                                                break;
                                            case 3:
                                                class_name = "current";
                                                break;
                                            default:
                                                class_name = "";
                                                break;
                                        }

                                        if (parseInt(status) === 0 ) {
                                            return <td className={class_name} data-date={day.date}>{day.date.substr(8, 10)}</td>
                                        } else {
                                            return <td className={class_name} data-date={day.date}><a href={ctx + '/mreport/self_new_detail?date=' + day.date + '&class=' + class_name}>{day.date.substr(8, 10)}</a></td>
                                            
                                        }
                                        
                                    
                                    } else {
                                        return <td></td> 
                                    }
                                })

                            }</tr>   
                        })
                    }
                </tbody>
            </table>
        );
    }
});

//渲染到页面
ReactDOM.render(
    <Canlendar />,
    document.getElementById('calendar_div')
);


/**
 * 函数：使用jQuery
 * 输入：
 * 输出：
**/
// $(function(){

//     freshCanlendar();
    
//     renderCanlendar(list);

//     *
//      * 函数：渲染日历到页面
//      * 输入：
//      * 输出：
//     *
//     function renderCanlendar(list) {

//     }
// });