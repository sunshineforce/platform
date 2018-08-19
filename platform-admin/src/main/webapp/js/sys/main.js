$(function () {
    vm.queryStatDatas();
});

function statTask(xData,tasksSeries) {
    var colorList = [
        '#ff7f50','#87cefa','#da70d6','#32cd32','#6495ed',
        '#ff69b4','#ba55d3','#cd5c5c','#ffa500','#40e0d0'
    ];

    var option = {
        title: {
            text: '任务执行统计'
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
            y: 'center'
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

function statExceptions(xData,pendingData,reviewData,finishData) {
    var option = {
        title : {
            text: '异常处理统计',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient : 'vertical',
            x : 'left',
            data:xData
        },
        calculable : true,
        series : [
            {
                name:'待处理',
                type:'pie',
                radius : '55%',
                center: ['25%', '50%'],
                data:pendingData
            },
            {
                name:'待复查',
                type:'pie',
                radius : '55%',
                center: ['55%', '50%'],
                data:reviewData
            },
            {
                name:'已完成',
                type:'pie',
                radius : '55%',
                center: ['85%', '50%'],
                data:finishData
            }
        ]
    }
    // 基于准备好的dom，初始化echarts实例
    var chart = echarts.init(document.getElementById("exceptionStatDiv"));
    // 使用刚指定的配置项和数据显示图表。
    chart.setOption(option);
}
var vm = new Vue({
    el: '#rrapp',
    data: {
        regionId:12718,
        startTime: DateUtils.date2String(DateUtils.dateOffset(new Date(),"day",-7),1),
        endTime:DateUtils.date2String(new Date(),1),
        cityList:[],
    },
    created:function () {
        $.ajax({
            type: "GET",
            url: "../sys/region/getAllCity",
            contentType: "application/json",
            data: {
                areaId:12717 //浙江省
            },
            success: function (r) {
                if (r.code == 0 && r.list) {
                    for (var i = 0 ; i < r.list.length; i++){
                        Vue.set(vm.cityList,i,r.list[i]);
                    }

                }
            }
        });
    },
    methods: {
       queryStatDatas:function(){
           console.log("startTime ---" + DateUtils.date2String(DateUtils.dateOffset(new Date(),"day",-8),1))
           $.ajax({
               type: "GET",
               url: "../stat/statTaskAndOrder",
               contentType: "application/json",
               data: {
                   regionId:vm.regionId,
                   startTime:DateUtils.date2String(new Date(vm.startTime),1),
                   endTime:DateUtils.date2String(new Date(vm.endTime),1)
               },
               success: function (r) {
                   if (r.code == 0) {
                       statTask(r.xData,r.tasksSeries);
                       statExceptions(r.xData,r.pendingData,r.reviewData,r.finishData);
                   }
               }
           });
       }

    }
});