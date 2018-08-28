$(function () {
    $("#jqGrid").jqGrid({
        url: '../sys/user/list',
        datatype: "json",
        colModel: [
            {label: '用户ID', name: 'userId', index: "user_id", key: true, hidden: true},
            {label: '姓名', name: 'realname', width: 75, align: 'center'},
            {label: '手机号', name: 'mobile', width: 100, align: 'center'},
            {label: '帐号', name: 'username', width: 100, align: 'center'},
            {label: '角色', name: 'roleNames', width: 100, align: 'center'},
            {label: '区域名称', name: 'regionName', width: 80, align: 'center'},
            {
                label: '是否启用', name: 'status', width: 80, align: 'center', formatter: function (value) {
                return value === 0 ?
                    '<span class="label label-danger">否</span>' :
                    '<span class="label label-success">是</span>';
            }
            },
            {label: '最后修改时间', name: 'updateTime', width: 75, align: 'center'}
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
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
});
var ztree;
var ztree1;



var vm = new Vue({
    el: '#rrapp',
    data: {
        q: {
            username: null,
            regionId:null,
            realname:null,
            mobile:null,
        },
        showList: true,
        title: null,
        roleList: {},
        user: {
            status: 1,
            deptName: '',
            roleIdList: []
        },
        ruleValidate: {
            realname: [
                {required: true, message: '姓名不能为空', trigger: 'blur'}
            ],
            username: [
                {required: true, message: '账户不能为空', trigger: 'blur'}
            ],
            email: [
                {required: true, message: '邮箱不能为空', trigger: 'blur'},
                {type: 'email', message: '邮箱格式不正确', trigger: 'blur'}
            ],
            mobile: [
                {required: true, message: '手机号不能为空', trigger: 'blur'},
                { type: 'string',pattern:/^0?(13|15|18|14)[0-9]{9}$/, message:'手机号不符合规范', trigger:'blur'},
            ]
        },
        canSetPwd:true,
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

                console.log("id-----------------" + vm.user.regionId)
                var node = ztree1.getNodeByParam("id", vm.user.regionId);
                if (node) {
                    console.log("name-----------------" + node.name)
                    ztree1.selectNode(node);
                    Vue.set(vm.user,'regionName',node.name);
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
                    Vue.set(vm.user,'regionId',node[0].id);
                    Vue.set(vm.user,'regionName',node[0].name);
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
            vm.title = "新增(默认密码：888888)";
            vm.roleList = {};
            vm.user = {status: 1, roleIdList: [], deptId: '', deptName: ''};
            vm.canSetPwd = true;
            vm.getRegionTree();
            //获取角色信息
            this.getRoleList();
            //vm.getDept();
        },
        getDept: function () {
            //加载部门树
            $.get("../sys/dept/list", function (r) {
                ztree = $.fn.zTree.init($("#deptTree"), setting, r.list);
                var node = ztree.getNodeByParam("deptId", vm.user.deptId);
                if (node != null) {
                    ztree.selectNode(node);

                    vm.user.deptName = node.name;
                }
            })
        },
        update: function () {
            var userId = getSelectedRow();
            if (userId == null) {
                return;
            }

            vm.showList = false;
            vm.title = "修改";
            vm.canSetPwd = false;
            $.get("../sys/user/info/" + userId, function (r) {
                vm.user = r.user;
                //获取角色信息
                vm.getRoleList();
                vm.getRegionTree();
                //vm.getDept();
            });

        },
        del: function () {
            var userIds = getSelectedRows();
            if (userIds == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: "../sys/user/delete",
                    contentType: "application/json",
                    data: JSON.stringify(userIds),
                    success: function (r) {
                        if (r.code == 0) {
                            alert('操作成功', function (index) {
                                vm.reload();
                            });
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        saveOrUpdate: function (event) {
            var url = vm.user.userId == null ? "../sys/user/save" : "../sys/user/update";
            $.ajax({
                type: "POST",
                url: url,
                contentType: "application/json",
                data: JSON.stringify(vm.user),
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
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'username': vm.q.username,
                    "regionId":vm.q.regionId,
                    "mobile":vm.q.mobile,
                    "realname":vm.q.realname,
                },
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
        },
        deptTree: function () {
            openWindow({
                title: "选择部门",
                area: ['300px', '450px'],
                content: jQuery("#deptLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = ztree.getSelectedNodes();
                    //选择上级部门
                    vm.user.deptId = node[0].deptId;
                    vm.user.deptName = node[0].name;

                    layer.close(index);
                }
            });
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