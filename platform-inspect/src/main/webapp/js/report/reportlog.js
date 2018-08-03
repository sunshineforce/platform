$(function () {
    $("#jqGrid").jqGrid({
        url: '../reportlog/list',
        datatype: "json",
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '发送时间', name: 'sendTime', index: 'send_time', align: 'center', width:'80px'},
			{label: '事件编号', name: 'eventNo', index: 'event_no', align: 'center', width:'80px'},
			{label: '推送结果', name: 'result', index: 'result', align: 'center', width:'80px',formatter:formatResult},
			{label: '告警位置', name: 'alarmLocation', index: 'alarm_location', align: 'center', width:'80px'},
			{label: '位置标签', name: 'localtionTag', index: 'localtion_tag', align: 'center', width:'80px'},
			{label: '设备类型', name: 'materialTypeName', index: 'device_type', align: 'center', width:'80px'},
			{label: '客户名称', name: 'userName', index: 'customer_id', align: 'center', width:'80px'},
            {label: '联系电话', name: 'mobile', index: 'customer_id', align: 'center', width:'80px'}
			],
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

//0：正常；1：发送失败；2：异常
///格式化任务状态
const Result = ["发送成功","发送失败","发送异常"];
function formatResult(t) {
    return '<span>' + Result[t] + '</span>';
}
var vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		reportLog: {},
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
        toDetail:function () {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            window.location.href = "/report/logDetail.html?id="+id;
        },
		query: function () {
			vm.reload();
		},
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.reportLog = {};
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
            var url = vm.reportLog.id == null ? "../reportlog/save" : "../reportlog/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.reportLog),
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
				    url: "../reportlog/delete",
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
			$.get("../reportlog/info/"+id, function (r) {
                vm.reportLog = r.reportLog;
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