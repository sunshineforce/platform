/**
 * Created by bjsonghongxu on 2018/7/23.
 */

var vm = new Vue({
    el: '#rrapp',
    data: {
        inspectOrder: {},
    },
    created: function(){
        var id = T.p('id');
        $.ajax({
            type: "POST",
            url: "../inspectorder/queryDetail",
            contentType: "application/json",
            data: JSON.stringify({id:id}),
            success: function (r) {
                if (r.code === 0) {
                    vm.inspectOrder = r.order;
                    if (vm.inspectOrder != null && vm.inspectOrder.photos != null){
                        vm.inspectOrder.photoList =  vm.inspectOrder.photos.split(",");
                        if (null != vm.inspectOrder.photoList){
                            var photoDatas = [];
                            for (var i = 0; i < vm.inspectOrder.photoList.length ; i++ ){
                                 var data = {
                                     "alt": i + "",
                                     "pid": i, //图片id
                                     "src": vm.inspectOrder.photoList[i], //原图地址
                                     "thumb": "" //缩略图地址
                                 };
                                 photoDatas.push(data);
                            }
                            vm.inspectOrder.photoDatas  = photoDatas;
                        }
                        if (vm.inspectOrder.orderFlows != null  ){
                            for (var i = 0; i < vm.inspectOrder.orderFlows.length ; i++ ){
                                if(vm.inspectOrder.orderFlows[i].photos != null){
                                    vm.inspectOrder.orderFlows[i].photoList =  vm.inspectOrder.orderFlows[i].photos.split(",");
                                    if (null != vm.inspectOrder.orderFlows[i].photoList){
                                        var photoDatas = [];
                                        for (var j = 0; j < vm.inspectOrder.photoList.length ; j++ ){
                                            var data = {
                                                "alt": j + "",
                                                "pid": j, //图片id
                                                "src": vm.inspectOrder.orderFlows[i].photoList[j], //原图地址
                                                "thumb": "" //缩略图地址
                                            };
                                            photoDatas.push(data);
                                        }
                                        vm.inspectOrder.orderFlows[i].photoDatas  = photoDatas;
                                    }
                                }else{
                                    vm.inspectOrder.orderFlows[i].photoList = [];
                                }
                            }
                        }
                    }else{
                        vm.inspectOrder.photoList = [];
                    }
                }
            }
        });
    },
    methods: {
        toOrderList:function () {
            window.location.href = "/inspect/inspectorder.html";
        },
        getDetail: function(){

        }
    }
});