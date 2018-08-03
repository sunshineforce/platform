$(function () {
    $("#jqGrid").jqGrid({
        url: '../qrcodeapply/list',
        datatype: "json",
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '批次号', name: 'batchNo', index: 'batch_no', align: 'center', width:'80px'},
			{label: '生成数量', name: 'quantity', index: 'quantity', align: 'center', width:'80px'},
			{label: '二维码前缀', name: 'prefix', index: 'prefix', align: 'center', width:'80px'},
            {label: '申请企业', name: 'applicant', index: 'applicant', align: 'center', width:'80px'},
			{label: '申请人', name: 'applicant', index: 'applicant', align: 'center', width:'80px'},
			{label: '状态', name: 'qrCodeStatus', index: 'qr_code_status', align: 'center', width:'80px',formatter:formatQrCodeStatus}
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
//格式化任务状态
const QrCodeStatus = ["审核中","已发放","已驳回"];
function formatQrCodeStatus(t) {
    return '<span>' + QrCodeStatus[t] + '</span>';
}
var vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		qrCodeApply: {},
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
			vm.qrCodeApply = {};
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
            var url = vm.qrCodeApply.id == null ? "../qrcodeapply/save" : "../qrcodeapply/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.qrCodeApply),
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
				    url: "../qrcodeapply/delete",
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
			$.get("../qrcodeapply/info/"+id, function (r) {
                vm.qrCodeApply = r.qrCodeApply;
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