$(function () {
    statTask();
    statExceptions();
});

function statTask() {
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
            data:['2010','2011','2012','2013']
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
                data: ['待执行', '执行中', '已完成', '已超时']
            }
        ],
        yAxis: [
            {
                splitLine:{show: false},//去除网格线
                type: 'value'
            }
        ],
        series: [
            {
                name: '2010',
                type: 'bar',
                data: [4804.7,1444.3,1332.1,908,871.8,1983.7,1627.6,499.2]
            },
            {
                name: '2011',
                type: 'bar',
                data: [5506.3,1674.7,1405,1023.2,969,2149.7,1851.7,581.3]
            },
            {
                name: '2012',
                type: 'bar',
                data: [6040.9,1823.4,1484.3,1116.1,1063.7,2455.5,2033.5,657.1]
            },
            {
                name: '2013',
                type: 'bar',
                data: [6311.9,1902,1745.1,1215.1,1118.3,2736.9,2294,699.4]
            }
        ]
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
        q: {
            regionId:0,
        },
        form:{
            subWay:"GET"
        },
        rs:"",

    },
    methods: {
        handleSubmit: function () {

            if (vm.form.subWay == "GET"){
                $.get(vm.form.url, function (r) {
                    vm.rs = r;
                });
            }else{
                $.ajax({
                    type: vm.form.subWay,
                    url: vm.form.url,
                    contentType: "application/json",
                    data: vm.form.json,
                    success: function (r) {
                        vm.rs = r;
                    }
                });
            }

        },
        handleReset: function (name) {
            vm.form = {subWay:"GET"};
            vm.rs = "";
        }

    }
});