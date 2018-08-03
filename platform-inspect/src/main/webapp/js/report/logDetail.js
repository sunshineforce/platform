/**
 * Created by bjsonghongxu on 2018/7/23.
 */

var vm = new Vue({
    el: '#rrapp',
    data: {
        reportLog: {},
    },
    created: function(){
        var id = T.p('id');
        $.get("../reportlog/info/"+id, function (r) {
            vm.reportLog = r.reportLog;
        });
    },
    methods: {
        toOrderList:function () {
            window.location.href = "/report/reportlog.html";
        },
        getDetail: function(){

        }
    }
});