$(function () {
    $("#jqGrid").jqGrid({
        url: '../license/list',
        datatype: "json",
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '证照类型', name: 'licenseType', index: 'licenseType', width: 80},
			{label: '证照名称', name: 'licenseName', index: 'license_name', width: 80},
			{label: '证照号码', name: 'number', index: 'number', width: 80},
			{label: '到期时间', name: 'expireDate', index: 'expire_date', width: 80},
			{label: '添加人', name: 'creator', index: 'creator', width: 80}],
		viewrecords: true,
        height: 385,
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
		license: {},
        licenseTypeId: '',
        formValidate: {
            licenseType: '',
            licenseName: '',
            number:'',
            expireDate:''
        },
		rules: {
            licenseType: [
                {required: true, message: '请选择证照类型', trigger: 'change'}
            ],
            licenseName: [
                {required: true, message: '名称不能为空', trigger: 'blur'}
            ],
            number: [
                {required: true, message: '证照编号不能为空', trigger: 'blur'}
            ],
            expireDate: [
                {required: true, type: 'date', message: '请选择到期时间', trigger: 'change'}
            ]

		},
		q: {
		    name: ''
		},
		licenseTypeList:[]
	},
	methods: {
		query: function () {
			vm.reload();
		},
        loadAllLicenseType: function () {
            $.get("../licenseType/queryAll", function (r) {
                vm.licenseTypeList = r.list;
            });
        },
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.license = {};
            vm.licenseTypeList = [];
            vm.loadAllLicenseType();
		},
		update: function (event) {
            var id = getSelectedRow();
			if (id == null) {
				return;
			}
			vm.showList = false;
            vm.title = "修改";
            $.get("../licenseType/info/" + id[0].id, function (r) {
                vm.licenseType = r.id;
            });
            vm.loadAllLicenseType();

            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
            var url = vm.license.id == null ? "../license/save" : "../license/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.license),
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
				    url: "../license/delete",
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
			$.get("../license/info/"+id, function (r) {
                vm.license = r.license;
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
            vm.license.url = file.response.url;
        },
        eyeImagePicUrl: function () {
            var url = vm.license.url;
            eyeImage(url);
        }
	}
});