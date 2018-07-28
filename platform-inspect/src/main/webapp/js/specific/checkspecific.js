$(function () {
    $("#jqGrid").jqGrid({
        url: '../checkspecific/list',
        datatype: "json",
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '规范名称', name: 'specificName', index: 'specific_name', align: 'center', width:'80px'},
			{label: '备注', name: 'remark', index: 'remark', align: 'center', width:'80px'},
			{label: '创建时间', name: 'createTime', index: 'create_time', align: 'center', width:'80px'},
			{label: '创建者', name: 'creator', index: 'creator', align: 'center', width:'80px'},
			{label: '最后修改人', name: 'updateTime', index: 'update_time', align: 'center', width:'80px'},
			{label: '最后修改人', name: 'updator', index: 'updator', align: 'center', width:'80px'}],
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
                url: "../checkspecificitem/list?specificId="+row_id,  // (7)子表格数据对应的url，注意传入的contact.id参数
                datatype: "json",
                //colNames: ['编号','内部编码','名称','申请号'],
                colModel: [
                    {label: '检查项', name: 'name', index: '', align: 'center'},
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

var vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		checkSpecific: {},
		ruleValidate: {
			name: [
				{required: true, message: '名称不能为空', trigger: 'blur'}
			]
		},
		q: {
		    name: ''
		},
        specificItems:[], //检查项
	},
	methods: {
        ///添加选项
        addItem:function () {
            var item = {
                id : 0,
                specificId:0,
                name:"",
            };
            vm.specificItems.push(item);
        },
        //删除选项
        deleteItem:function (i) {
            vm.specificItems.splice(i, 1);
            //Vue.set(vm.questionList, q, questionVo);
        },

		query: function () {
			vm.reload();
		},
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.checkSpecific = {};
			vm.specificItems = [];
			vm.addItem();
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
            var url = vm.checkSpecific.id == null ? "../checkspecific/save" : "../checkspecific/update";
            vm.checkSpecific.specificItemsJson = JSON.stringify(vm.specificItems);
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.checkSpecific),
                success: function (r) {
                    if (r.code === 0) {
                    	vm.specificItems = [];
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
				    url: "../checkspecific/delete",
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
			$.get("../checkspecific/info/"+id, function (r) {
                vm.checkSpecific = r.checkSpecific;
                vm.specificItems = vm.checkSpecific.specificItems;
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