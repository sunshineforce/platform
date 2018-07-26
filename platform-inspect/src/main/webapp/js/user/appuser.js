$(function () {
    $("#jqGrid").jqGrid({
        url: '../sys/app/user/list',
        datatype: "json",
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '姓名', name: 'username', index: 'username', align: 'center', width:'80px'},
			{label: '手机号', name: 'mobile', index: 'mobile', align: 'center', width:'80px'},
            {label: '帐号', name: 'account', index: 'account', align: 'center', width:'80px'},
            {
                label: '身份', name: 'status', width: 80, formatter: function (value) {
                    return value === 1 ?
                        '<span>安全员</span>' :
                        '<span>领导</span>';
                }
            },
            {
                label: '是否启用', name: 'status', width: 80, formatter: function (value) {
                    return value === 0 ?
                        '<span>否</span>' :
                        '<span>是</span>';
                }
            },
            {label: '层级', name: 'superior', index: 'superior', align: 'center', width:'80px'},
			{label: '创建人', name: 'creator', index: 'creator', align: 'center', width:'80px'},
			{label: '修改时间', name: 'updateTime', index: 'update_time', align: 'center', width:'80px'},
			{label: '修改人', name: 'updateUserId', index: 'update_user_id', align: 'center', width:'80px'}],
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
        roleList: {},
        title: null,
		appUser: {
            roleIdList: []
        },
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
            vm.roleList = {};
			vm.appUser = {};

            this.getRoleList();
		},
		update: function (event) {
            var id = getSelectedRow();
			if (id == null) {
				return;
			}
			vm.showList = false;
            vm.title = "修改";
            vm.getRoleList();
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
            var url = vm.appUser.id == null ? "../sys/app/user/save" : "../sys/app/user/update";
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
        getRoleList: function () {
            $.get("../sys/role/select", function (r) {
                vm.roleList = r.list;
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
				    url: "../sys/app/user/delete",
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
			$.get("../sys/app/user/info/"+id, function (r) {
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