var taskGroupId = getQueryString("id");
var source = getQueryString("source");
$(function () {
    var isHide = !(source == 1);
    if (isHide){
        $("#addAllBtn").hide();
        $("#removeAllBtn").hide();
        $("#retunListBtn").hide();
    }else{
        $("#updateBtn").hide();
        $("#giveupBtn").hide();
        $("#delBtn").hide();
    }
    $("#jqGrid").jqGrid({
        url: '../material/list?taskGroupId='+taskGroupId,
        datatype: "json",
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '二维码', name: 'qrCode', index: 'qr_code', align: 'center', width:'80px',formatter:formatQR},
			{label: '物品名称', name: 'materialName', index: 'material_name', align: 'center', width:'80px'},
			{label: '所在位置', name: 'location', index: 'location', align: 'center', width:'80px'},
			{label: '物品类型', name: 'materialTypeName', index: '', align: 'center', width:'80px'},
			{label: '生产日期', name: 'producedDate', index: 'produced_date', align: 'center', width:'80px'},
			{label: '到期时间', name: 'expireDate', index: 'expire_date', align: 'center', width:'80px'},
			{label: '最近检查时间', name: 'checkDate', index: 'check_date', align: 'center', width:'80px'},
			{label: '状态', name: 'materialStatus', index: 'material_status', align: 'center', width:'80px',formatter:formatStatus},
			{label: '所属企业', name: 'materialOwner', index: 'material_owner', align: 'center', width:'80px'},
			{label: '创建时间', name: 'createTime', index: 'create_time', align: 'center', width:'80px'},
			{label: '更新时间', name: 'updateTime', index: 'update_time', align: 'center', width:'80px'},
            {label: '操作', name: 'id', hidden: isHide, index: 'id', align: 'center',width: 80,formatter:function (cellvalue, options, rowObject) {
                var str = "<a href='javascript:add2TaskGroup("+taskGroupId+","+rowObject.id+")'>添加到此任务组</a>";
                if (rowObject.taskGroupStatus == 1){
                    str = "<a href='javascript:removeTaskGroup("+taskGroupId+","+rowObject.id+")'>移除此任务组</a>";
                }
                return str;
            }}],
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
        url: '../enterprise/list',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '企业名称', name: 'enterpriseName', index: 'enterprise_name', width: 210}
        ],
        viewrecords: true,
        height: 555,
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        shrinkToFit: false,
        autoScroll: true,          //shrinkToFit: false,autoScroll: true,这两个属性产生水平滚动条
        autowidth: true,          //必须要,否则没有水平滚动条
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
            // $("#jqGrid1").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });

});

///格式二维码
function formatQR(t) {
    return '<img alt="image"  style="height: 64px; width: 64px;" src="'+t+'">';
}
//0：正常；1：报废；2：异常
///格式化任务状态
const Status = ["未检查","正常","异常","报废"];
function formatStatus(t) {
    return '<span>' + Status[t] + '</span>';
}



///添加到任务组
function add2TaskGroup(tgId,mId) {
    vm.add2TaskGroup(tgId,mId);
}
///从任务组移除
function removeTaskGroup(tgId,mId) {
    vm.removeTaskGroup(tgId,mId);
}

var ztree;
var ztree0;

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




var vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		material: {},
		ruleValidate: {
			name: [
				{required: true, message: '名称不能为空', trigger: 'blur'}
			]
		},
		q: {
		    name: ''
		},
        eq: {
            name: '',
            regionId:null,
            status:null,
        },
        statusList:[
            {id:"",name:"选择状态"},
            {id:0,name:"未检查"},
            {id:1,name:"正常"},
            {id:2,name:"异常"},
            {id:3,name:"报废"},
        ],

	},
    created:function () {
        //加载树
        $.get("../sys/region/getAreaTree", function (r) {
            var datas = r.node;
            ztree0 = $.fn.zTree.init($("#regionTree"), setting0,datas );

            var treeObj = $.fn.zTree.getZTreeObj("regionTree");
            var nodes = treeObj.getNodes();

            for (var i = 0; i < nodes.length; i++) { //设置节点展开
                treeObj.expandNode(nodes[i], true, false, true);
            }
        })
    },
	methods: {
        reloadEnterprise: function (event) {
            vm.showList = true;
            var page = $("#jqGrid1").jqGrid('getGridParam', 'page');
            $("#jqGrid1").jqGrid('setGridParam', {
                postData: {'enterpriseName': vm.eq.name,'regionId':vm.eq.regionId},
                page: page
            }).trigger("reloadGrid");
        },
        ztreeClick:function () {
            var node = ztree0.getSelectedNodes();
            //alert(node[0].id)
            if (node[0].id == 0){
                vm.eq.regionId = null;
                vm.q.regionId = null;
            }else{
                vm.eq.regionId = node[0].id;
                vm.q.regionId = node[0].id;
            }

            vm.reloadEnterprise();
        },
        retunList:function () {
            //window.location.href="/task/taskgroup.html";
            history.back();
        },
        add2TaskGroup:function(tgId,mId){
            $.ajax({
                type: "POST",
                url: "../taskgroupmaterial/save",
                contentType: "application/json",
                data: JSON.stringify({taskGroupId:tgId,materialId:mId}),
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
        removeTaskGroup:function (tgId,mId) {
            $.ajax({
                type: "POST",
                url: "../taskgroupmaterial/remove",
                contentType: "application/json",
                data: JSON.stringify({taskGroupId:tgId,materialId:mId}),
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
        addAll2TaskGroup:function () {

            $.ajax({
                type: "GET",
                url: "../taskgroupmaterial/saveAll",
                contentType: "application/json",
                data: vm.getQueryParams(),
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
        removeAllFromTaskGroup:function () {
            $.ajax({
                type: "POST",
                url: "../taskgroupmaterial/remove",
                contentType: "application/json",
                data: JSON.stringify({taskGroupId:taskGroupId}),
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
                    Vue.set(vm.material, 'materialTypeId', node[0].id);
                    Vue.set(vm.material, 'materialTypeName', node[0].name);

                    layer.close(index);
                }
            });
        },
        getMaterialType: function () {
            //加载树
            $.get("../materialtype/queryAll", function (r) {
                var datas = r.list;
                ztree = $.fn.zTree.init($("#materialTypeTree"), setting,datas );
                console.log(vm.material.materialTypeId)
                var node = ztree.getNodeByParam("id", vm.material.materialTypeId);
                if (node) {
                    ztree.selectNode(node);
                    vm.material.materialTypeName = node.name;
                }
            })
        },
		query: function () {
			vm.reload();
		},
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.material = {};
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
            var url = vm.material.id == null ? "../material/save" : "../material/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.material),
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
        giveup:function () {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            var url =  "../material/giveUp";
            $.ajax({
                type: "POST",
                url: url,
                contentType: "application/json",
                data: JSON.stringify({id:id}),
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
				    url: "../material/delete",
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
			$.get("../material/info/"+id, function (r) {
                vm.material = r.material;
                console.log("typeId ---- " + vm.material.materialTypeId)
                vm.getMaterialType();
            });
		},
        getQueryParams:function () {
            var enterpriseArr = $("#jqGrid1").getGridParam("selarrrow");
            var enterpriseIdsArr = [];
            if (null != enterpriseArr && enterpriseArr.length > 0){
                for (var i = 0 ; i < enterpriseArr.length ; i++){
                    enterpriseIdsArr[i] = enterpriseArr[i];
                }
            }
            return {
                'materialStatus':(vm.q.status !== "") ? vm.q.status : null,
                'materialName': vm.q.name,
                'taskGroupId':taskGroupId,
                'regionId':vm.q.regionId,
                'enterpriseIds':enterpriseIdsArr.join(",")
            };
        },
		reload: function (event) {

			vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
                postData: vm.getQueryParams(),
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
            //vm.material.qrCode = file.response.url;
            Vue.set(vm.material,"qrCode",file.response.url);
        },
        eyeImagePicUrl: function () {
            var url = vm.material.qrCode;
            eyeImage(url);
        }
	}
});

var setting0 = {
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