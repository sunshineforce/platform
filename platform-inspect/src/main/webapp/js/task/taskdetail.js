$(function () {
    $("#jqGrid").jqGrid({
        url: '../taskdetail/list',
        datatype: "json",
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '名称', name: 'name', index: 'name', align: 'center', width:'80px'},
            {label: '任务组', name: 'taskGroupName', index: 'task_group_id', align: 'center',width: '120px'},
            {label: '开始时间', name: 'startTime', index: 'start_time', align: 'center',width: '80px',formatter:formatDay},
            {label: '截止时间', name: 'endTime', index: 'end_time', align: 'center',width: '80px',formatter:formatDay},
            {label: '检查区域', name: 'regionName', index: 'regionP_id', align: 'center',width: '120px'},
            {label: '检查企业', name: 'enterpriseName', index: 'enterprise_id', align: 'center',width: '120px'},
            {label: '检查人', name: 'userNames', index: 'user_names', align: 'center',width: '80px'},
            {label: '状态', name: 'status', index: 'status',align: 'center', width: '60px',formatter:formatStatus},
            {label: '最后执行时间', name: 'inspectTime', index: 'inspect_time', align: 'center'},
            {label: '备注', name: 'remark', index: 'remark', align: 'center',width: '100px'}
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
        subGrid: true,  // (1)开启子表格支持
        subGridRowExpanded: function(subgrid_id, row_id) {  // (2)子表格容器的id和需要展开子表格的行id，将传入此事件函数
            var subgrid_table_id;
            subgrid_table_id = subgrid_id + "_t";   // (3)根据subgrid_id定义对应的子表格的table的id

            var subgrid_pager_id;
            subgrid_pager_id = subgrid_id + "_pgr"  // (4)根据subgrid_id定义对应的子表格的pager的id

            // (5)动态添加子报表的table和pager
            $("#" + subgrid_id).html("<table id='"+subgrid_table_id+"' class='scroll'></table><div id='"+subgrid_pager_id+"' class='scroll'></div>");

            // (6)创建jqGrid对象
            $("#" + subgrid_table_id).jqGrid({
                url: "../taskdetail/detailStat?id="+row_id,  // (7)子表格数据对应的url，注意传入的contact.id参数
                datatype: "json",
                colModel: [
                    {label: '物品类型', name: 'name', index: '', align: 'center'},
                    {label: '进度', name: 'progress', index: '', align: 'center'},
                    {label: '正常数量', name: 'nomarlNum', index: '', align: 'center'},
                    {label: '异常数量', name: 'exceptNum', index: '', align: 'center'},
                ],
                height: 300,
                pager: subgrid_pager_id,
                viewrecords: true,
                height: "100%",
                rowNum: 10,
                rowList: [10, 30, 50],
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
                }
            });
        },
        gridComplete: function () {
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
});

///格式化任务状态
const Status = ["待执行","执行中","已完成","已超时"];
function formatStatus(t) {
    return '<span>' + Status[t] + '</span>';
}

///格式化日期
function formatDay (v) {
    if (v == null || v== "") {return "";}
    return DateUtils.long2String(v,1);
}

var vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		taskDetail: {},
		ruleValidate: {
			name: [
				{required: true, message: '名称不能为空', trigger: 'blur'}
			]
		},
        taskGroupList:[], //查询使用
        statusList:[
            {id:"",name:"状态"},
            {id:"0",name:"待执行"},
            {id:"1",name:"执行中"},
            {id:"2",name:"已完成"},
            {id:"3",name:"已超时"},
        ],
		q: {
		    name: '',
            status:'',
            regionName:'',
            enterpriseName:'',
		}
	},
    created:function () {
        $.get("../taskgroup/queryAll", function (r) {
            vm.taskGroupList = r.list;
            if(vm.taskGroupList == null){
                vm.taskGroupList = [];
            }
            vm.taskGroupList.unshift({id:"",name:"选择任务组"});
        });
    },
	methods: {
		query: function () {
			vm.reload();
		},
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.taskDetail = {};
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
            var url = vm.taskDetail.id == null ? "../taskdetail/save" : "../taskdetail/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.taskDetail),
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
				    url: "../taskdetail/delete",
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
			$.get("../taskdetail/info/"+id, function (r) {
                vm.taskDetail = r.taskDetail;
            });
		},
		reload: function (event) {
			vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'name': vm.q.name,
                    'regionName': vm.q.regionName,
                    'enterpriseName': vm.q.enterpriseName,
                    "status":(vm.q.status != "") ? vm.q.status : null,
                    "taskGroupId":(vm.q.taskGroupId != "") ? vm.q.taskGroupId : null,
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