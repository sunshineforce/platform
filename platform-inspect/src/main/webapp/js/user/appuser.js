$(function () {
    $("#jqGrid").jqGrid({
        url: '../sys/app/user/list',
        datatype: "json",
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '姓名', name: 'realname', index: 'realname', align: 'center', width:'100px'},
			{label: '手机号', name: 'mobile', index: 'mobile', align: 'center', width:'120px'},
            {label: '帐号', name: 'username', index: 'username', align: 'center', width:'100px'},
            {
                label: '身份', name: 'identify', width: '60px', formatter: function (value) {
                    return value === 0 ?
                        '<span>安全员</span>' :
                        '<span>领导</span>';
                }
            },
            {
                label: '是否启用', name: 'status', width: '80px', formatter: function (value) {
                    return value === 0 ?
                        '<span>否</span>' :
                        '<span>是</span>';
                }
            },
            {label: '上级领导', name: 'superiorStr', index: 'superior', align: 'center', width:'160px'},
            {label: '区域', name: 'regionName', index: '', align: 'center', width:'120px'},
            {label: '所属企业', name: 'enterpriseName', index: '', align: 'center', width:'120px'},
			{label: '修改时间', name: 'updateTime', index: 'update_time', align: 'center', width:'139px'},
			{label: '修改人', name: 'updateUserName', index: 'update_user_id', align: 'center', width:'80px'}],
		viewrecords: true,
        height: 555,
        rowNum: 10,
        rowList: [10, 30, 50],
        autowidth: true,
        multiselect: true,
        shrinkToFit: false,
        autoScroll: true,          //shrinkToFit: false,autoScroll: true,这两个属性产生水平滚动条
        autowidth: true,          //必须要,否则没有水平滚动条
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
           // $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
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

var ztree;
var ztree1;

var vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        roleList: {},
        title: null,
        showPaw:true,
		appUser: {
            identify:1,
            roleIdList:[]
        },
		ruleValidate: {
			userName: [
				{required: true, message: '姓名不能为空', trigger: 'blur'}
			],
            mobile: [
                {required: true, message: '手机号不能为空', trigger: 'blur'}
            ],
            account: [
                {required: true, message: '帐号不能为空', trigger: 'blur'}
            ],
            // identify: [
            //     {required: true, message: '请选择身份', trigger: 'change'}
            // ],
            password: [
                {required: true, message: '请设置初始密码', trigger: 'blur'}
            ]
		},
		q: {
		    name: '',
            regionId:null,
		},
        eq: {
            name: '',
            regionId:null,
        },
        enabled:'0',
        identifyList: [
            {id:0,name:"安全员"},
            {id:1,name:"领导"},
        ],
        ///上级列表
        superiorList:[],
        //接受选择结果
        superiorArr:[],
        //所属企业
        enterpriseList:[],
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
        reloadEnterprise: function (event) {
            vm.showList = true;
            var page = $("#jqGrid1").jqGrid('getGridParam', 'page');
            $("#jqGrid1").jqGrid('setGridParam', {
                postData: {'enterpriseName': vm.eq.name,'regionId':vm.eq.regionId},
                page: page
            }).trigger("reloadGrid");
        },
        ztreeClick:function () {
            var node = ztree.getSelectedNodes();
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
        getRegionTree: function () {
            //加载树
            $.get("../sys/region/getAreaTree", function (r) {
                var datas = r.node;
                ztree1 = $.fn.zTree.init($("#editRegionTree"), setting1,datas );

                console.log("id-----------------" + vm.appUser.regionId)
                var node = ztree1.getNodeByParam("id", vm.appUser.regionId);
                if (node) {
                    console.log("name-----------------" + node.name)
                    ztree1.selectNode(node);
                    //vm.enterprise.regionName = node.name;
                    Vue.set(vm.appUser,'regionName',node.name);
                }
            })
        },
        layerTree: function () {
            openWindow({
                title: "选择区域",
                area: ['300px', '450px'],
                content: jQuery("#regionLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = ztree1.getSelectedNodes();

                    //选择地域
                    Vue.set(vm.appUser,'regionId',node[0].id);
                    Vue.set(vm.appUser,'regionName',node[0].name);
                    //console.log("name ---------" + node[0].name)
                    //加载企业信息
                    vm.queryEnterpriseList(node[0].id);

                    layer.close(index);
                }
            });
        },
        ///通过区域id加载企业列表
        queryEnterpriseList:function (regionId) {
            if (undefined == regionId || null == regionId){return;}
            $.ajax({
                type: "GET",
                url: "../enterprise/queryAll",
                contentType: "application/json",
                data: {
                    regionId:regionId,
                },
                success: function (r) {
                    if (r.code == 0) {
                        vm.enterpriseList = []; //清空上一次数据
                        vm.enterpriseList = r.list;
                    } else {
                        console.log("error msg ---- " + r.msg)
                    }
                }
            });
        },
	    querySuperiorList:function () {
            $.get("../sys/app/user/appUserListByIdentify/1", function (r) {
                vm.superiorList = r.list;
            });
        },
		query: function () {
			vm.reload();
		},
		add: function () {
			vm.showList = false;
			vm.title = "新增";
            vm.roleList = {};
			vm.appUser = { identify:1,status:1,enterpriseId:''};
            vm.querySuperiorList();
            vm.getRegionTree();
            vm.enterpriseList = [];
            //this.getRoleList();
		},
		update: function (event) {
            var id = getSelectedRow();
			if (id == null) {
				return;
			}
			vm.showList = false;
            vm.title = "修改";
            vm.showPaw = false;
            //vm.getRoleList();
            console.log("id----------------------" + id)
            vm.getInfo(id)

		},
		saveOrUpdate: function (event) {
            var url = vm.appUser.id == null ? "../sys/app/user/save" : "../sys/app/user/update";
            vm.appUser.superior = vm.superiorArr.join(",");
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.appUser),
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
        getRoleList: function () {
            $.get("../sys/role/select", function (r) {
                vm.roleList = r.list;
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
				    url: "../sys/app/user/delete",
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
			$.get("../sys/app/user/info/"+id, function (r) {
                vm.appUser = r.user;
                vm.superiorList = vm.appUser.superiorList;
                var arr = [];
                if (vm.appUser.superior != null){
                    var sArr = vm.appUser.superior.split(",");
                    for (var i = 0; i <sArr.length; i++){
                        arr[i] = parseInt(sArr[i]);
                    }
                }
                vm.superiorArr = arr;
                vm.getRegionTree();
                //加载企业信息
                vm.queryEnterpriseList(vm.appUser.regionId);
               // console.log("superiorArr-----------" + vm.superiorArr)
            });
		},
		reload: function (event) {
		    var enterpriseArr = $("#jqGrid1").getGridParam("selarrrow");
		    var enterpriseIdsArr = [];
		    if (null != enterpriseArr && enterpriseArr.length > 0){
		        for (var i = 0 ; i < enterpriseArr.length ; i++){
                    enterpriseIdsArr[i] = enterpriseArr[i];
                }
            }
			vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
                postData: {'realname': vm.q.name,'regionId':vm.q.regionId,'enterpriseIds':enterpriseIdsArr.join(","),},
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
        //处理身份变化
        listenIdentifyChange:function () {
            if (vm.appUser.identify == 2){
                vm.appUser.certificateUrl = "";
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
            vm.appUser.certificateUrl = file.response.url;
        },
        eyeImagePicUrl: function () {
            var url = vm.appUser.certificateUrl;
            eyeImage(url);
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