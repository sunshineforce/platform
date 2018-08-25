$(function () {
    $("#jqGrid").jqGrid({
        url: '../task/list',
        datatype: "json",
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '任务名称', name: 'name', index: 'name', align: 'center',width: '160px'},
			{label: '任务组', name: 'taskGroupName', index: 'task_group_id', align: 'center',width: '120px'},
            {label: '任务类型', name: 'type', index: 'type',align: 'center', width: '70px',formatter:formatType},
            {label: '开始时间', name: 'startTime', index: 'start_time', align: 'center',width: '80px',formatter:formatDay},
            {label: '截止时间', name: 'endTime', index: 'end_time', align: 'center',width: '80px',formatter:formatDay},
            {label: '执行时限', name: 'schedule', index: 'schedule',align: 'center', width: '80px'},
            {label: '循环周期', name: 'scheduleCycle', index: 'schedule_cycle',align: 'center', width: '50px',formatter:formatScheduleCycle},
            {label: '检查区域', name: 'regionName', index: 'regionP_id', align: 'center',width: '120px'},
            {label: '检查企业', name: 'enterpriseName', index: 'enterprise_id', align: 'center',width: '120px'},
            {label: '检查人', name: 'userNames', index: 'user_names', align: 'center',width: '80px'},
            {label: '状态', name: 'status', index: 'status',align: 'center', width: '60px',formatter:formatStatus},
            {label: '最后执行时间', name: 'updateTime', index: 'update_time', align: 'center'},
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
        gridComplete: function () {
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
});

///格式化日期
function formatDay (v) {
    if (v == null || v== "") {return "";}
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
var ztree1;
var setting1 = {
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            id: 0
        },

    }
};
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
        taskGroupList:[], //查询使用
        statusList:[
            {id:"",name:"状态"},
            {id:"0",name:"待执行"},
            {id:"1",name:"执行中"},
            {id:"2",name:"已完成"},
        ],
        typeList:[
            {id:"",name:"类型"},
            {id:"0",name:"单次任务"},
            {id:"1",name:"循环任务"},
        ],
        userList:[],
        //接受选择结果
        userArr:[],
		///循环周期
        scheduleCycleList:[
			{id:0,name:"每天"},
            {id:1,name:"每周"},
            {id:2,name:"每月"},
            {id:3,name:"每年"},
		],
        //所属企业
        enterpriseList:[],
        scheduleMin:1,
        scheduleMax:1,
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
        queryAppUserList:function (arr) {
            $.get("../sys/app/user/appUserListByIdentify/0", function (r) {
                vm.userList = r.list;
                if (arr){
                    vm.userArr = arr;
                }
            });
        },
        getRegionTree: function () {
            //加载树
            $.get("../sys/region/getAreaTree", function (r) {
                var datas = r.node;
                ztree1 = $.fn.zTree.init($("#editRegionTree"), setting1,datas );

                console.log("id-----------------" + vm.task.regionId)
                var node = ztree1.getNodeByParam("id", vm.task.regionId);
                if (node) {
                    console.log("name-----------------" + node.name)
                    ztree1.selectNode(node);
                    Vue.set(vm.task,'regionName',node.name);
                }
            })
        },
        layerTree: function () {
            openWindow({
                title: "选择区域",
                area: ['300px', '450px'],
                content: jQuery("#regionLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = ztree1.getSelectedNodes();

                    //选择地域
                    Vue.set(vm.task,'regionId',node[0].id);
                    Vue.set(vm.task,'regionName',node[0].name);
                    //console.log("name ---------" + node[0].name)
                    vm.task.enterpriseId = null;
                    vm.enterpriseList = [];
                    vm.userList = [];
                    vm.userArr = [];
                    //加载企业信息
                    vm.queryEnterpriseList(node[0].id);

                    layer.close(index);
                }
            });
        },
        ///通过区域id加载企业列表
        queryEnterpriseList:function (regionId) {
            if (undefined == regionId || null == regionId){return;}
            $.ajax({
                type: "GET",
                url: "../enterprise/queryAll",
                contentType: "application/json",
                data: {
                    regionId:regionId,
                },
                success: function (r) {
                    if (r.code == 0) {
                        vm.enterpriseList = []; //清空上一次数据
                        vm.enterpriseList = r.list;
                    } else {
                        console.log("error msg ---- " + r.msg)
                    }
                }
            });
        },
        listenEnterpriseChange : function () {
           vm.queryUserByEnterpriseId(vm.task.enterpriseId,[]);
        },
        ///查询企业下app 用户
        queryUserByEnterpriseId : function(enterpriseId,arr){
            if (undefined == enterpriseId || null == enterpriseId){return;}
            $.ajax({
                type: "POST",
                url: "../sys/app/user/appUserList",
                contentType: "application/json",
                data: JSON.stringify({
                    identify:0,
                    enterpriseId:enterpriseId,
                }),
                success: function (r) {
                    if (r.code == 0) {
                        vm.userList = []; //清空上一次数据
                        vm.userList = r.list;

                        if (arr){
                            //vm.userArr = arr;
                            for(var i =0 ; i < arr.length; i++){
                                Vue.set(vm.userArr,i,arr[i]);
                            }
                        }
                    } else {
                        console.log("error msg ---- " + r.msg)
                    }
                }
            });

        },
		query: function () {
			vm.reload();
		},
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.task = { type: 0 ,status:0};
			vm.getTaskGroups(); ///加载任务组
            vm.getRegionTree();

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
            vm.task.userIds = vm.userArr.length > 0 ? vm.userArr.join(",") : null;
            console.log("vm.task.userIds ----- " + vm.task.userIds)
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.task),
                success: function (r) {
                    if (r.code === 0) {
                        vm.userArr = [];
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
                var arr = [];
                if(vm.task.userIds){
                    var ids = vm.task.userIds.split(",");
                    for (var i = 0; i <ids.length; i++){
                        arr[i] = parseInt(ids[i]);
                    }
                }
                vm.getRegionTree();
                vm.queryEnterpriseList(vm.task.regionId);
                vm.queryUserByEnterpriseId(vm.task.enterpriseId,arr);

            });
		},
		reload: function (event) {
			vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
                postData: {
                	'name': vm.q.name,
					"status":(vm.q.status != "") ? vm.q.status : null,
					"taskGroupId":(vm.q.taskGroupId != "") ? vm.q.taskGroupId : null,
                    "type":(vm.q.type != "") ? vm.q.type : null,
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
        //
        changeScheduleCycle:function () {
		    vm.task.schedule = 1;
            vm.scheduleMin = 1;
		    if (vm.task.scheduleCycle == 0){
		        vm.scheduleMax = 1;
            }else if (vm.task.scheduleCycle == 1){
                vm.scheduleMax = 7;
            }else if (vm.task.scheduleCycle == 2){
                vm.scheduleMax = 30;
            }else if (vm.task.scheduleCycle == 3){
                vm.scheduleMax = 365;
            }
        },
		///获取任务组
		getTaskGroups:function () {
            $.get("../taskgroup/queryAll", function (r) {
                vm.taskGroups = r.list;
                console.log("===="+vm.taskGroups);
            });
        },
	}
});