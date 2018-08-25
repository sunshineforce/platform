var photosMap  = new Map();
$(function () {
    $("#jqGrid").jqGrid({
        url: '../inspectorder/list?inspectStatus=1',
        datatype: "json",
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '物品名称', name: 'materialId', index: 'material_id', align: 'center', width:'80px'},
			{label: '工单状态 ', name: 'status', index: 'status', align: 'center', width:'80px',formatter:formatOrderStatus},
			{label: '检查时间', name: 'inspectTime', index: 'inspect_time', align: 'center', width:'80px'},
			{label: '检查人', name: 'userId', index: 'user_id', align: 'center', width:'80px'},
			{label: '检查人', name: 'userName', index: 'user_name', align: 'center', width:'80px'},
			{label: '检查结果', name: 'inspectStatus', index: 'inspect_status', align: 'center', width:'80px',formatter:formatInspectStatus},
			{label: '上级姓名', name: 'chiefName', index: 'chief_name', align: 'center', width:'80px'},
			{label: '现场照片', name: 'photos', index: 'photos', align: 'center', width:'200px',formatter:formatImgs},
			{label: '创建时间', name: 'createTime', index: 'create_time', align: 'center', width:'80px'},
			{label: '更新时间', name: 'updateTime', index: 'update_time', align: 'center', width:'80px'}],
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

    $("#jqGrid1").jqGrid({
        url: '../sys/app/user/list?identify=1',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '姓名', name: 'realname', index: 'realname', align: 'center', width:'120px'},
            {label: '手机号', name: 'mobile', index: 'mobile', align: 'center', width:'120px'},
            {label: '区域', name: 'regionName', index: '', align: 'center', width:'120px'},
            {label: '所属企业', name: 'enterpriseName', index: '', align: 'center', width:'160px'}],
        viewrecords: true,
        height: 260,
        rowNum: 10,
        rowList: [10, 30, 50],
        multiselect: true,
        autowidth: true,
        pager: "#jqGridPager1",
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
            // $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
});
///格式化工单状态
const InspectStatus = ["正常","异常"];
function formatInspectStatus(t) {
    return '<span>' + InspectStatus[t] + '</span>';
}

///格式化工单状态
const Status = ["待处理","待复查","已完成"];
function formatOrderStatus(t) {
    return '<span>' + Status[t] + '</span>';
}

//格式化图片
function formatImgs(t) {
    if (t == null || t == "" ){return "";}
    var imgs = t.split(",");
    // var photoDatas = [];
    // for (var j = 0; j < imgs.length ; j++ ){
    //     var data = {
    //         "alt": j + "",
    //         "pid": j, //图片id
    //         "src": imgs[j], //原图地址
    //         "thumb": "" //缩略图地址
    //     };
    //     photoDatas.push(data);
    //
    // }
    var imgStr = "<div style='width: 195px; '> <ul>";
    for(var i = 0 ; i < imgs.length ; i++){
        imgStr += '<li style="float:left;margin-left:1px;"><img alt="image" style="height: 64px; width: 64px;" src="'+imgs[i]+'" ></li>';

    }
    imgStr +="</ul></div>";
    return imgStr;
}

function view(data) {
	var d = JSON.parse(data);
    eyeImages(d);
}

var vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		inspectOrder: {},
		ruleValidate: {
			name: [
				{required: true, message: '名称不能为空', trigger: 'blur'}
			]
		},
		q: {
		    name: ''
		},
        u: {
            name: ''
        }
	},
	methods: {
        reloadUser: function (event) {
            var page = $("#jqGrid1").jqGrid('getGridParam', 'page');
            $("#jqGrid1").jqGrid('setGridParam', {
                postData: {'realname': vm.u.name},
                page: page
            }).trigger("reloadGrid");
        },
        replace:function () {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }

            openWindow({
                title: "选择上级",
                area: ['575px', '500px'],
                content: jQuery("#userDiv"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var grid = $("#jqGrid1");
                    var rowKey = grid.getGridParam("selrow");
                    if (!rowKey) {
                        iview.Message.error("请选择一条记录");
                        return;
                    }

                    var selectedIDs = grid.getGridParam("selarrrow");
                    if (selectedIDs.length > 1) {
                        iview.Message.error("只能选择一条记录");
                        return;
                    }

                    var rowData = $("#jqGrid1").jqGrid('getRowData', selectedIDs[0]);
                    $.ajax({
                        type: "POST",
                        url: "../inspectorder/replace",
                        contentType: "application/json",
                        data: JSON.stringify({id:id,chiefIds:rowData.id,chiefName:rowData.realname}),
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
                    layer.close(index);
                }
            });
        },
        eyeImages:function () {
            var data = getSelectedRowData();
            var imgs = t.split(",");
            var photoDatas = [];
            for (var j = 0; j < imgs.length ; j++ ){
                var data = {
                    "alt": j + "",
                    "pid": j, //图片id
                    "src": imgs[j], //原图地址
                    "thumb": "" //缩略图地址
                };
                photoDatas.push(data);
            }
        },
        toDetail:function () {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
			window.location.href = "/inspect/orderDetail.html?id="+id;
        },
		query: function () {
			vm.reload();
		},
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.inspectOrder = {};
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
            var url = vm.inspectOrder.id == null ? "../inspectorder/save" : "../inspectorder/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.inspectOrder),
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
				    url: "../inspectorder/delete",
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
			$.get("../inspectorder/info/"+id, function (r) {
                vm.inspectOrder = r.inspectOrder;
            });
		},
		reload: function (event) {
			vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
                postData: {'materialName': vm.q.name},
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