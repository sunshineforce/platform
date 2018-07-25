$(function () {
    $("#jqGrid").jqGrid({
        url: '../material/list',
        datatype: "json",
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '二维码', name: 'qrCode', index: 'qr_code', align: 'center', width:'80px',formatter:formatQR},
			{label: '物品名称', name: 'materialName', index: 'material_name', align: 'center', width:'80px'},
			{label: '所在位置', name: 'location', index: 'location', align: 'center', width:'80px'},
			{label: '物品类型', name: 'materialTypeName', index: '', align: 'center', width:'80px'},
			{label: '生产日期', name: 'producedDate', index: 'produced_date', align: 'center', width:'80px'},
			{label: '到期时间', name: 'expireDate', index: 'expire_date', align: 'center', width:'80px'},
			{label: '最近检查时间', name: 'checkDate', index: 'check_date', align: 'center', width:'80px'},
			{label: '状态', name: 'materialStatus', index: 'material_status', align: 'center', width:'80px',formatter:formatStatus},
			{label: '所属企业', name: 'materialOwner', index: 'material_owner', align: 'center', width:'80px'},
			{label: '创建时间', name: 'createTime', index: 'create_time', align: 'center', width:'80px'},
			{label: '更新时间', name: 'updateTime', index: 'update_time', align: 'center', width:'80px'}],
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
///格式二维码
function formatQR(t) {
    return '<img alt="image"  style="height: 64px; width: 64px;" src="'+t+'">';
}
//0：正常；1：报废；2：异常
///格式化任务状态
const Status = ["正常","报废","异常"];
function formatStatus(t) {
    return '<span>' + Status[t] + '</span>';
}
var vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		material: {},
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
			vm.material = {};
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
            var url = vm.material.id == null ? "../material/save" : "../material/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.material),
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
				    url: "../material/delete",
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
			$.get("../material/info/"+id, function (r) {
                vm.material = r.material;
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