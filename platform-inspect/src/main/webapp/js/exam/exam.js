$(function () {
    $("#jqGrid").jqGrid({
        url: '../exam/list',
        datatype: "json",
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '试题名称', name: 'examName', index: 'exam_name', align: 'center', width:'120px'},
			{label: '试题介绍', name: 'introduce', index: 'introduce', align: 'center', width:'160px'},
            {label: '题目数量', name: 'introduce', index: '', align: 'center', width:'60px'},
            {label: '总分值', name: 'introduce', index: '', align: 'center', width:'60px'},
			{label: '开始时间', name: 'beginTime', index: 'begin_time', align: 'center', width:'80px'},
			{label: '结束时间', name: 'endTime', index: 'end_time', align: 'center', width:'80px'},
			{label: '参考人员', name: 'member', index: 'member', align: 'center', width:'80px'},
			{label: '修改时间', name: 'updateTime', index: 'update_time', align: 'center', width:'80px'},
			{label: '修改人', name: 'updator', index: 'updator', align: 'center', width:'80px'}],
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
                url: "../exammember/list?examId="+row_id,  // (7)子表格数据对应的url，注意传入的contact.id参数
                datatype: "json",
                //colNames: ['编号','内部编码','名称','申请号'],
                colModel: [
                    {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
                    {label: '姓名', name: 'examId', index: '', align: 'center', width:'80px'},
                    {label: '得分', name: 'memberId', index: '', align: 'center', width:'80px'},
                    {label: '考试时间', name: 'memberId', index: '', align: 'center', width:'80px'},
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
		exam: {},
		ruleValidate: {
			name: [
				{required: true, message: '名称不能为空', trigger: 'blur'}
			]
		},
		q: {
		    name: ''
		},
		userList:[],
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.exam = {};
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
            var url = vm.exam.id == null ? "../exam/save" : "../exam/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.exam),
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
				    url: "../exam/delete",
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
			$.get("../exam/info/"+id, function (r) {
                vm.exam = r.exam;
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