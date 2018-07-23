$(function () {
    $("#jqGrid").jqGrid({
        url: '../appUser/list',
        datatype: "json",
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '姓名', name: 'userName', index: 'user_name', align: 'center', width:'80px'},
			{label: '手机号', name: 'mobile', index: 'mobile', align: 'center', width:'80px'},
            {label: '帐号', name: 'account', index: 'account', align: 'center', width:'80px'},
            {label: '身份', name: 'identify', index: 'identify', align: 'center', width:'80px'},
            {label: '是否启用（0：启用；1：禁用）', name: 'enabled', index: 'enabled', align: 'center', width:'80px'},
            {label: '层级', name: 'superior', index: 'superior', align: 'center', width:'80px'},
			{label: '创建人', name: 'creator', index: 'creator', align: 'center', width:'80px'},
			{label: '修改时间', name: 'updateTime', index: 'update_time', align: 'center', width:'80px'},
			{label: '修改人', name: 'updator', index: 'updator', align: 'center', width:'80px'}],
		viewrecords: true,
        height: 555,
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
});

var vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		appUser: {},
		ruleValidate: {
			userName: [
				{required: true, message: '姓名不能为空', trigger: 'blur'}
			],
            mobile: [
                {required: true, message: '手机号不能为空', trigger: 'blur'}
            ],
            account: [
                {required: true, message: '帐号不能为空', trigger: 'blur'}
            ],
            identify: [
                {required: true, message: '请选择身份', trigger: 'change'}
            ],
            password: [
                {required: true, message: '请设置初始密码', trigger: 'blur'}
            ]
		},
		q: {
		    name: ''
		},
        enabled:'0'
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.appUser = {};
		},
		update: function (event) {
            var id = getSelectedRow();
			if (id == null) {
				return;
			}
			vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
            var url = vm.appUser.id == null ? "../appUser/save" : "../appUser/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.appUser),
                success: function (r) {
                    if (r.code === 0) {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
			});
		},
		del: function (event) {
            var ids = getSelectedRows();
			if (ids == null){
				return;
			}

			confirm('确定要删除选中的记录？', function () {
				$.ajax({
					type: "POST",
				    url: "../appUser/delete",
				    contentType: "application/json",
				    data: JSON.stringify(ids),
				    success: function (r) {
						if (r.code == 0) {
							alert('操作成功', function (index) {
								$("#jqGrid").trigger("reloadGrid");
							});
						} else {
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get("../appUser/info/"+id, function (r) {
                vm.appUser = r.appUser;
            });
		},
		reload: function (event) {
			vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
                postData: {'name': vm.q.name},
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
		},
        handleSubmit: function (name) {
            handleSubmitValidate(this, name, function () {
                vm.saveOrUpdate()
            });
        },
        handleReset: function (name) {
            handleResetForm(this, name);
        }
	}
});