$(function () {
    $("#jqGrid").jqGrid({
        url: '../taskgroupmaterial/list',
        datatype: "json",
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '任务组', name: 'groupName', index: 'task_group_id',  align: 'center', width: '100px'},
            {label: '物品名称', name: 'materialName', index: 'name', align: 'center',width: '120px'},
            {label: '物品类型', name: 'materialTypeName', index: 'material_type_name', align: 'center',width: '60px'},
            {label: '二维码', name: 'qrCode', index: '', align: 'center',width: '160px',formatter:formatQR},
            {label: '位置', name: 'location', index: 'location', align: 'center',width: '80px'},
			{label: '状态', name: 'materialStatus', index: 'materialStatus', align: 'center',width: '60px',formatter:formatStatus},
            {label: '最近检查时间', name: 'updateTime', index: 'update_time', align: 'center'}
		],
		viewrecords: true,
        height: 560,
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
const Status = ["未检查","正常","异常","报废"];
function formatStatus(t) {
    return '<span>' + Status[t] + '</span>';
}
var vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		taskGroupMaterial: {},
		ruleValidate: {
			name: [
				{required: true, message: '名称不能为空', trigger: 'blur'}
			]
		},
		q: {
		    name: ''
		},
        taskGroupList:[
            {id:"",name:"选择任务组"}
        ], //查询使用
	},
    created:function () {
        //console.log("created..........")
        $.get("../taskgroup/queryAll", function (r) {
            vm.taskGroupList = vm.taskGroupList.concat(r.list);
        });
    },
	methods: {
		query: function () {
			vm.reload();
		},
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.taskGroupMaterial = {};
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
            var url = vm.taskGroupMaterial.id == null ? "../taskgroupmaterial/save" : "../taskgroupmaterial/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.taskGroupMaterial),
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
				    url: "../taskgroupmaterial/delete",
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
			$.get("../taskgroupmaterial/info/"+id, function (r) {
                vm.taskGroupMaterial = r.taskGroupMaterial;
            });
		},
		reload: function (event) {
			vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'materialName': vm.q.name,
                    'taskGroupId' : (vm.q.taskGroupId != "") ? vm.q.taskGroupId : null,
                },
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