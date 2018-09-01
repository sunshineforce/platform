$(function () {
    $("#jqGrid").jqGrid({
        url: '../integratedtask/list',
        datatype: "json",
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '名称', name: 'name', index: 'name', align: 'center', width:'80px'},
			{label: '要求', name: 'requires', index: 'requires', align: 'center', width:'80px'},
			{label: '备注', name: 'bake', index: 'bake', align: 'center', width:'80px'},
			{label: '发布时间', name: 'publishTime', index: 'publish_time', align: 'center', width:'80px'}],
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
		integratedTask: {},
		ruleValidate: {
			name: [
				{required: true, message: '名称不能为空', trigger: 'blur'}
			]
		},
		q: {
		    name: '',
            regionId:'',
		}
	},
    created:function () {
        //加载树
        $.get("../sys/region/getAreaTree", function (r) {
            var datas = r.node;
            ztree = $.fn.zTree.init($("#regionTree"), setting,datas );

            var treeObj = $.fn.zTree.getZTreeObj("regionTree");
            var nodes = treeObj.getNodes();

            for (var i = 0; i < nodes.length; i++) { //设置节点展开
                treeObj.expandNode(nodes[i], true, false, true);
            }
        })
    },
	methods: {
        ztreeClick:function () {
            var node = ztree.getSelectedNodes();
            //alert(node[0].id)
            vm.q.regionId = node[0].id;
            vm.reload();
        },
		query: function () {
			vm.reload();
		},

		//去到发布任务
        publish:function () {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            window.location.href = "/task/task.html?itId=" + id;
        },

        detail:function () {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            window.location.href = "/task/task.html?itId=" + id;
        },

		//到查看任务页面
		eye:function () {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            window.location.href = "/task/taskrel.html?itId=" + id;
        },

		add: function () {
            var regionId = vm.q.regionId;
            if(regionId == undefined || regionId == ""){
            	alert("请选择任务的地域，谢谢！");
            	return;
			}
			vm.showList = false;
			vm.title = "发布";
			vm.integratedTask = {regionId:regionId};
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
            var url = vm.integratedTask.id == null ? "../integratedtask/save" : "../integratedtask/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.integratedTask),
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
				    url: "../integratedtask/delete",
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
			$.get("../integratedtask/info/"+id, function (r) {
                vm.integratedTask = r.integratedTask;
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

var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            id: 0
        }
    },
    callback:{
        onClick:vm.ztreeClick,
    }
};