$(function () {
    $("#jqGrid").jqGrid({
        url: '../task/list',
        datatype: "json",
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '任务组id', name: 'taskGroupId', index: 'task_group_id', width: 80},
			{label: '任务类型 0 单次任务 1 循环任务', name: 'type', index: 'type', width: 80},
			{label: '任务状态  0 ', name: 'status', index: 'status', width: 80},
			{label: '人员ids', name: 'userIds', index: 'user_ids', width: 80},
			{label: '人员姓名', name: 'userNames', index: 'user_names', width: 80},
			{label: '开始时间', name: 'startTime', index: 'start_time', width: 80},
			{label: '截止时间', name: 'endTime', index: 'end_time', width: 80},
			{label: '执行时限(单位天)', name: 'schedule', index: 'schedule', width: 80},
			{label: '循环周期 每天 1 每周 2 每月 3 每年 4', name: 'scheduleCycle', index: 'schedule_cycle', width: 80},
			{label: '创建时间', name: 'createTime', index: 'create_time', width: 80},
			{label: '更新时间', name: 'updateTime', index: 'update_time', width: 80},
			{label: '数据状态 0  正常 1 删除', name: 'dataStatus', index: 'data_status', width: 80}],
		viewrecords: true,
        height: 385,
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
		task: {},
		ruleValidate: {
			name: [
				{required: true, message: '名称不能为空', trigger: 'blur'}
			]
		},
		q: {
		    name: ''
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.task = {};
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
            var url = vm.task.id == null ? "../task/save" : "../task/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.task),
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
				    url: "../task/delete",
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
			$.get("../task/info/"+id, function (r) {
                vm.task = r.task;
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