$(function () {
    // $("#jqGrid").jqGrid({
    //     url: '../materialtype/list',
    //     datatype: "json",
    //     colModel: [
		// 	{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
		// 	{label: '物品名称', name: 'materialTypeName', index: 'material_type_name', align: 'center', width:'80px'},
		// 	{label: '备注', name: 'remark', index: 'remark', align: 'center', width:'80px'},
		// 	{label: '创建时间', name: 'createTime', index: 'create_time', align: 'center', width:'80px'},
		// 	{label: '最后修改时间', name: 'updateTime', index: 'update_time', align: 'center', width:'80px'},
		// 	{label: '最后修改人', name: 'updator', index: 'updator', align: 'center', width:'80px'}],
		// viewrecords: true,
    //     height: 555,
    //     rowNum: 10,
    //     rowList: [10, 30, 50],
    //     rownumbers: true,
    //     rownumWidth: 25,
    //     autowidth: true,
    //     multiselect: true,
    //     pager: "#jqGridPager",
    //     jsonReader: {
    //         root: "page.list",
    //         page: "page.currPage",
    //         total: "page.totalPage",
    //         records: "page.totalCount"
    //     },
    //     prmNames: {
    //         page: "page",
    //         rows: "limit",
    //         order: "order"
    //     },
    //     gridComplete: function () {
    //         $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
    //     }
    // });
    initialPage();
    getGrid();
});



function initialPage() {
    $(window).resize(function () {
        TreeGrid.table.resetHeight({height: $(window).height() - 100});
    });
}

function getGrid() {
    var colunms = TreeGrid.initColumn();
    var table = new TreeTable(TreeGrid.id, '../materialtype/queryAll', colunms);
    table.setExpandColumn(2);
    table.setIdField("id");
    table.setCodeField("id");
    table.setParentCodeField("parentId");
    table.setExpandAll(true);
    table.setHeight($(window).height() - 100);
    table.init();
    TreeGrid.table = table;
}

var TreeGrid = {
    id: "jqGrid",
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
TreeGrid.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: '编号', field: 'id', visitable: false, align: 'center', valign: 'middle', width: '50px'},
        {title: '名称', field: 'name', align: 'center', valign: 'middle', width: '120px'},
        {title: '备注', field: 'remark', align: 'center', valign: 'middle', width: '160px'},
        {title: '上级类目名称', field: 'parentName', align: 'center', valign: 'middle', width: '100px'}];
    return columns;
};

var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url: "nourl"
        }
    }
};
var ztree;

var vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		materialType: {
            parentName: null,
            parentId: 0,
		},
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

        materialTypeTree: function () {
            openWindow({
                title: "选择类目",
                area: ['300px', '450px'],
                content: jQuery("#materialTypeLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = ztree.getSelectedNodes();
                    console.log("node -------" + node.toString())
                    //选择上级菜单
                   // vm.materialType.parentId = node[0].id;
                    Vue.set(vm.materialType, 'parentId', node[0].id);
                    Vue.set(vm.materialType, 'parentName', node[0].name);
                    //vm.materialType.parentName = node[0].name;

                    layer.close(index);
                }
            });
        },
        getMaterialType: function () {
            //加载树
            $.get("../materialtype/queryAll", function (r) {
            	var datas = r.list;
                ztree = $.fn.zTree.init($("#materialTypeTree"), setting,datas );
                var node = ztree.getNodeByParam("id", vm.materialType.parentId);
                if (node) {
                    ztree.selectNode(node);
                    vm.materialType.parentName = node.name;
                } else {
                    // node = ztree.getNodeByParam("id", 0);
                    // ztree.selectNode(node);
                    // vm.materialType.parentName = node.name;
                }
            })
        },
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.materialType = {};
            var id = TreeGrid.table.getSelectedRow();
            var pid = 0;
            if (id.length != 0) {
                pid = id[0].id;
            }
            vm.materialType = {
                parentName: null,
                parentId: pid,
			};
			vm.getMaterialType();
		},
		update: function (event) {
            var id = TreeGrid.table.getSelectedRow();
			if (id.length == 0) {
				return;
			}
			vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id[0].id)
		},
		saveOrUpdate: function (event) {
            var url = vm.materialType.id == null ? "../materialtype/save" : "../materialtype/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.materialType),
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
            var tids = TreeGrid.table.getSelectedRow(), ids = [];
			if (0 == tids.length){
				return;
			}

			confirm('确定要删除选中的记录？', function () {
                $.each(tids, function (idx, item) {
                    ids[idx] = item.id;
                });
				$.ajax({
					type: "POST",
				    url: "../materialtype/delete",
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
			$.get("../materialtype/info/"+id, function (r) {
                vm.materialType = r.materialType;
                vm.getMaterialType();
            });
		},
		reload: function (event) {
			vm.showList = true;
            // var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            // $("#jqGrid").jqGrid('setGridParam', {
            //     postData: {'name': vm.q.name},
            //     page: page
            // }).trigger("reloadGrid");
            vm.showList = true;
            TreeGrid.table.refresh();
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