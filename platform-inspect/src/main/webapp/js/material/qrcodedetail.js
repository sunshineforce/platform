$(function () {
    var id = T.p('id');
    $("#jqGrid").jqGrid({
        url: '../qrcodedetail/list?applyId='+id,
        datatype: "json",
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '二维码', name: 'qrCode', index: 'qr_code', align: 'center', width:'80px',formatter:formatQR},
            {label: '批次号', name: 'batchNo', index: 'batch_no', align: 'center', width:'80px'},
			{label: '生成人员', name: 'generateUserName', index: 'apply_id', align: 'center', width:'80px'},
            {label: '生成时间', name: 'generateTime', index: 'apply_id', align: 'center', width:'80px'},
            {label: '绑定人姓名', name: 'bindUserName', index: 'bind_user_id', align: 'center', width:'80px'},
            {label: '绑定时间', name: 'bindTime', index: 'bind_time', align: 'center', width:'80px'},
			{label: '物品名称', name: 'matetialName', index: 'matetial_id', align: 'center', width:'80px'},
			{label: '物品类型', name: 'materialTypeName', index: 'material_type_id', align: 'center', width:'80px'}
		],
		viewrecords: true,
        height: 555,
        rowNum: 10,
        rowList: [10, 30, 50],
        // rownumbers: true,
        // rownumWidth: 25,
        autowidth: true,
        // multiselect: true,
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


///格式二维码
function formatQR(t) {
    return '<img alt="image"  style="height: 64px; width: 64px;" src="'+t+'">';
}

var vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		qrCodeDetail: {},
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
        toList:function () {
            window.location.href = "/material/qrcodeapply.html";
        },
        //导出
        exportExecel:function () {

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