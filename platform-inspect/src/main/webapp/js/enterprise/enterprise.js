$(function () {
    $("#jqGrid").jqGrid({
        url: '../enterprise/list',
        datatype: "json",
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '企业名称', name: 'enterpriseName', index: 'enterprise_name', width: 80},
			{label: '联系电话', name: 'mobile', index: 'mobile', width: 80},
			{label: '负责人', name: 'owner', index: 'owner', width: 80},
			{label: '地址', name: 'address', index: 'address', width: 80},
			{label: '账号', name: 'account', index: 'account', width: 80},
			{label: '最后修改时间', name: 'updateTime', index: 'update_time', width: 80},
			{label: '最后修改人', name: 'updator', index: 'updator', width: 80}
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

var ztree;
var ztree1;

var vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		enterprise: {},
		ruleValidate: {
            enterpriseName: [
				{required: true, message: '企业名称不能为空', trigger: 'blur'}
			],
            mobile: [
                {required: true, message: '联系电话不能为空', trigger: 'blur'},
                { type: 'string',pattern:/^0?(13|15|18|14)[0-9]{9}$/, message:'联系电话不符合规范', trigger:'blur'},
            ],
            owner: [
                {required: true, message: '负责人不能为空', trigger: 'blur'}
            ],
            account: [
                {required: true, message: '帐号不能为空', trigger: 'blur'}
            ],
            password: [
                {required: true, message: '密码不能为空', trigger: 'blur'}
            ]

		},
		q: {
            enterpriseName: '',
            regionId:'',
		},

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
        getRegionTree: function () {
            //加载树
            $.get("../sys/region/getAreaTree", function (r) {
                var datas = r.node;
                ztree1 = $.fn.zTree.init($("#editRegionTree"), setting1,datas );

                console.log("id-----------------" + vm.enterprise.regionId)
                var node = ztree1.getNodeByParam("id", vm.enterprise.regionId);
                if (node) {
                    console.log("name-----------------" + node.name)
                    ztree1.selectNode(node);
                    //vm.enterprise.regionName = node.name;
                    Vue.set(vm.enterprise,'regionName',node.name);
                }
            })
        },
        layerTree: function () {
            openWindow({
                title: "选择菜单",
                area: ['300px', '450px'],
                content: jQuery("#regionLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = ztree1.getSelectedNodes();

                    //选择地域
                    Vue.set(vm.enterprise,'regionId',node[0].id);
                    Vue.set(vm.enterprise,'regionName',node[0].name);
                    //console.log("name ---------" + node[0].name)

                    layer.close(index);
                }
            });
        },
		query: function () {
			vm.reload();
		},
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.enterprise = {regionName:""};
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
            if(vm.enterprise.regionId == null || vm.enterprise.regionId == ""){
                alert("请选择企业所在地区，谢谢！");
                return;
            }
            var url = vm.enterprise.id == null ? "../enterprise/save" : "../enterprise/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.enterprise),
                success: function (r) {
                    if (r.code === 0) {
                        vm.enterprise = {};
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
				    url: "../enterprise/delete",
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
			$.get("../enterprise/info/"+id, function (r) {
                vm.enterprise = r.enterprise;
                vm.getRegionTree();
            });
		},
		reload: function (event) {
			vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'enterpriseName': vm.q.enterpriseName,
                    'mobile': vm.q.mobile,
                    'owner': vm.q.owner,
                    'account': vm.q.account,
                    'regionId':vm.q.regionId,
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
        }
    }
};