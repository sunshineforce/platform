$(function () {
    $("#jqGrid").jqGrid({
        url: '../task/list',
        datatype: "json",
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '任务名称', name: 'name', index: 'name', align: 'center',width: '160px'},
            {label: '备注', name: 'remark', index: 'remark', align: 'center',width: '100px'},
			{label: '任务组', name: 'taskGroupName', index: 'task_group_id', align: 'center',width: '120px'},
			{label: '任务类型', name: 'type', index: 'type',align: 'center', width: '70px',formatter:formatType},
			{label: '开始时间', name: 'startTime', index: 'start_time', align: 'center',width: '80px',formatter:formatDay},
			{label: '截止时间', name: 'endTime', index: 'end_time', align: 'center',width: '80px',formatter:formatDay},
			{label: '执行时限', name: 'schedule', index: 'schedule',align: 'center', width: '80px'},
			{label: '循环周期', name: 'scheduleCycle', index: 'schedule_cycle',align: 'center', width: '50px',formatter:formatScheduleCycle},
            {label: '检查区域', name: 'chekArea', index: '', align: 'center',width: '120px'},
            {label: '检查企业', name: 'checkCompany', index: '', align: 'center',width: '120px'},
            {label: '检查人', name: 'userNames', index: 'user_names', align: 'center',width: '80px'},
            {label: '状态', name: 'status', index: 'status',align: 'center', width: '60px',formatter:formatStatus},
			{label: '最后执行时间', name: 'createTime', index: 'create_time', align: 'center'}
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

///格式化日期
function formatDay (v) {
  return DateUtils.long2String(v,1);
}
///循环周期
const  ScheduleCycle = ["每天","每周","每月","每年"];
function formatScheduleCycle(t) {
	if (t == null) {
		return "";
	}
    return '<span>' + ScheduleCycle[t] + '</span>';
}
///格式化任务状态
const Status = ["待执行","执行中","已完成"];
function formatStatus(t) {
    return '<span>' + Status[t] + '</span>';
}
///格式化类型
function formatType(t) {
	return (t == 1) ? "循环任务":"单次任务";
}
var vm = new Vue({
	el: '#rrapp',

	data: {
        showList: true,
        title: null,
		task: {
		},
		ruleValidate: {
			name: [
				{required: true, message: '名称不能为空', trigger: 'blur'}
			]
		},
		q: {
		    name: '',
			status:'',
		},
		taskGroups:[],
        taskGroupList:[
			{id:"",name:"任务组"}
		], //查询使用
        statusList:[
            {id:"",name:"状态"},
            {id:"0",name:"待执行"},
            {id:"1",name:"执行中"},
            {id:"2",name:"已完成"},
        ],
        typeList:[
            {id:"",name:"类型"},
            {id:"0",name:"循环任务"},
            {id:"1",name:"单次任务"},
        ],
        userList:[],
		///循环周期
        scheduleCycleList:[
			{id:0,name:"每天"},
            {id:1,name:"每周"},
            {id:2,name:"每月"},
            {id:3,name:"每年"},
		],
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
			vm.task = { type: 0 ,status:0};
			vm.getTaskGroups(); ///加载任务组

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
                postData: {
                	'name': vm.q.name,
					"status":vm.q.status,
					"taskGroupId":vm.q.taskGroupId,
                    "type":vm.q.type
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
        },
		///监听改变类型
        changeType:function () {
			if (vm.task.type == 0){
				vm.task.schedule = "";
				vm.task.scheduleCycle="";
			}else {
				vm.task.endTime = "";
			}
        },
		///获取任务组
		getTaskGroups:function () {
            $.get("../taskgroup/queryAll", function (r) {
                vm.taskGroups = r.list;
            });
        },
	}
});