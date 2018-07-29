
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