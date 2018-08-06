$(function () {
    //statTask();

    vm.queryStatDatas();
});

function statTask(xData,tasksSeries) {
    var colorList = [
        '#ff7f50','#87cefa','#da70d6','#32cd32','#6495ed',
        '#ff69b4','#ba55d3','#cd5c5c','#ffa500','#40e0d0'
    ];

    var option = {
        title: {
            text: '2010-2013年中国城镇居民家庭人均消费构成（元）',
            subtext: '数据来自国家统计局',
            sublink: 'http://data.stats.gov.cn/search/keywordlist2?keyword=%E5%9F%8E%E9%95%87%E5%B1%85%E6%B0%91%E6%B6%88%E8%B4%B9'
        },
        tooltip: {
            trigger: 'axis',
            backgroundColor: 'rgba(255,255,255,0.7)',
            axisPointer: {
                type: 'shadow'
            },
            formatter: function(params) {
                // for text color
                var color = colorList[params[0].dataIndex];
                var res = '<div style="color:' + color + '">';
                res += '<strong>' + params[0].name + '</strong>'
                for (var i = 0, l = params.length; i < l; i++) {
                    res += '<br/>' + params[i].seriesName + ' : ' + params[i].value
                }
                res += '</div>';
                return res;
            }
        },
        legend: {
            x: 'right',
            data:['待执行', '执行中', '已完成', '已超时']
        },
        toolbox: {
            show: true,
            orient: 'vertical',
            y: 'center',
            feature: {
                // mark: {show: true},
                // dataView: {show: true, readOnly: false},
                // restore: {show: true},
               // saveAsImage: {show: true}
            }
        },
        calculable: true,
        grid: {
            y: 80,
            y2: 40,
            x2: 40
        },
        xAxis: [
            {
                splitLine:{show: false},//去除网格线
                type: 'category',
                data:xData
            }
        ],
        yAxis: [
            {
                splitLine:{show: false},//去除网格线
                type: 'value'
            }
        ],
        series: tasksSeries
    };

    // 基于准备好的dom，初始化echarts实例
    var chart = echarts.init(document.getElementById("taskStatDiv"));
    // 使用刚指定的配置项和数据显示图表。
    chart.setOption(option);
}

function statExceptions() {
    var option = {
        title : {
            text: '南丁格尔玫瑰图',
            subtext: '纯属虚构',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            x : 'center',
            y : 'bottom',
            data:['rose1','rose2','rose3','rose4','rose5','rose6','rose7','rose8']
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {
                    show: true,
                    type: ['pie', 'funnel']
                },
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        series : [
            {
                name:'半径模式',
                type:'pie',
                radius : [20, 110],
                center : ['25%', 200],
                roseType : 'radius',
                width: '40%',       // for funnel
                max: 40,            // for funnel
                itemStyle : {
                    normal : {
                        label : {
                            show : false
                        },
                        labelLine : {
                            show : false
                        }
                    },
                    emphasis : {
                        label : {
                            show : true
                        },
                        labelLine : {
                            show : true
                        }
                    }
                },
                data:[
                    {value:10, name:'rose1'},
                    {value:5, name:'rose2'},
                    {value:15, name:'rose3'},
                    {value:25, name:'rose4'},
                    {value:20, name:'rose5'},
                    {value:35, name:'rose6'},
                    {value:30, name:'rose7'},
                    {value:40, name:'rose8'}
                ]
            },
            {
                name:'面积模式',
                type:'pie',
                radius : [30, 110],
                center : ['75%', 200],
                roseType : 'area',
                x: '50%',               // for funnel
                max: 40,                // for funnel
                sort : 'ascending',     // for funnel
                data:[
                    {value:10, name:'rose1'},
                    {value:5, name:'rose2'},
                    {value:15, name:'rose3'},
                    {value:25, name:'rose4'},
                    {value:20, name:'rose5'},
                    {value:35, name:'rose6'},
                    {value:30, name:'rose7'},
                    {value:40, name:'rose8'}
                ]
            }
        ]
    };
    // 基于准备好的dom，初始化echarts实例
    var chart = echarts.init(document.getElementById("exceptionStatDiv"));
    // 使用刚指定的配置项和数据显示图表。
    chart.setOption(option);
}
var vm = new Vue({
    el: '#rrapp',
    data: {
        regionId:"",
        startTime:"2018-08-05",
        endTime:"2018-08-06",
    },
    methods: {
       queryStatDatas:function(){
           $.ajax({
               type: "GET",
               url: "../stat/statTaskAndOrder",
               contentType: "application/json",
               data: {
                   regionId:vm.regionId,
                   startTime:vm.startTime,
                   endTime:vm.endTime
               },
               success: function (r) {
                   if (r.code == 0) {
                       statTask(r.xData,r.tasksSeries);
                       statExceptions();
                   }
               }
           });
       }

    }
});