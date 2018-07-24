$(function () {
    $("#jqGrid").jqGrid({
        url: '../regulation/list',
        datatype: "json",
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '法规名称', name: 'knowledgeName', index: 'knowledge_name', align: 'center', width:'120px'},
			{label: '描述', name: 'description', index: 'description', align: 'center', width:'160px'},
			{label: '图片', name: 'url', index: 'url', align: 'center', width:'80px',formatter:formatImage},
			{label: '链接', name: 'link', index: 'link', align: 'center', width:'140px'},
			{label: '创建时间', name: 'createTime', index: 'create_time', align: 'center', width:'80px'},
			{label: '更改时间', name: 'updateTime', index: 'update_time', align: 'center', width:'80px'},
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
        gridComplete: function () {
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
});

///格式图片
function formatImage(t) {
    return '<img alt="image"  style="height: 64px; width: 64px;" src="'+t+'">';
}

var vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		regulation: {},
		ruleValidate: {
			name: [
				{required: true, message: '名称不能为空', trigger: 'blur'}
			]
		},
		q: {
		    name: ''
		},
		examList:[],
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.regulation = {
                type:0,
			};
		},
        ///获取考试列表
        getExamList:function () {
            $.get("../exam/queryAll", function (r) {
                vm.examList = r.list;
            });
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
            var url = vm.regulation.id == null ? "../regulation/save" : "../regulation/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.regulation),
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
				    url: "../regulation/delete",
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
			$.get("../regulation/info/"+id, function (r) {
                vm.regulation = r.regulation;
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
        },
        changeType:function () {
            if(vm.regulation.type == 0){
                vm.regulation.link = "";
            }else {
                vm.regulation.content = "";
            }
        },
        handleFormatError: function (file) {
            this.$Notice.warning({
                title: '文件格式不正确',
                desc: '文件 ' + file.name + ' 格式不正确，请上传 jpg 或 png 格式的图片。'
            });
        },
        handleMaxSize: function (file) {
            this.$Notice.warning({
                title: '超出文件大小限制',
                desc: '文件 ' + file.name + ' 太大，不能超过 2M。'
            });
        },
        handleSuccessPicUrl: function (res, file) {
            vm.regulation.url = file.response.url;
        },
        eyeImagePicUrl: function () {
            var url = vm.regulation.url;
            eyeImage(url);
        }
	}
});