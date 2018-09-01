$(function () {
    $("#jqGrid").jqGrid({
        url: '../exam/list',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '试题名称', name: 'examName', index: 'exam_name', align: 'center', width:'120px'},
            {label: '试题介绍', name: 'introduce', index: 'introduce', align: 'center', width:'160px'},
            {label: '题目数量', name: 'questionNum', index: 'question_num', align: 'center', width:'60px'},
            {label: '总分值', name: 'totalScore', index: 'total_score', align: 'center', width:'60px'},
            {label: '开始时间', name: 'beginTime', index: 'begin_time', align: 'center', width:'80px'},
            {label: '结束时间', name: 'endTime', index: 'end_time', align: 'center', width:'80px'},
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
                    {label: '姓名', name: 'userName', index: '', align: 'center', width:'160px'},
                    {label: '手机号', name: 'mobile', index: '', align: 'center', width:'80px'},
                    {label: '答题进度', name: 'answerProcess', index: '', align: 'center', width:'120px'},
                    {label: '分数', name: 'score', index: '', align: 'center', width:'60px'},
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
            examName: [
                {required: true, message: '试题名称不能为空', trigger: 'blur'}
            ],
            beginTime: [
                {required: true, type: 'date', message: '请选择开始时间', trigger: 'change'}
            ],
            endTime: [
                {required: true, type: 'date', message: '请选择结束时间', trigger: 'change'}
            ]
        },
        q: {
            name: ''
        },
        totalSocre:0,
        userList:[],
        questionList:[],
        memberArr:[],
    },
    methods: {
        queryUserList:function (id) {
            // $.get("../sys/app/user/appUserListByIdentify/0", function (r) {
            //     vm.userList = r.list;
            // });
            $.ajax({
                type: "GET",
                url: "../sys/app/user/appUserList",
                contentType: "application/json",
                success: function (r) {
                    if (r.code == 0) {
                        vm.userList = r.list;
                    } else {
                        console.log("error msg ---- " + r.msg)
                    }
                }
            });
        },
        ///添加问题
        addQuestion:function () {
            var questionVo = {
                score:0,
                question:"",
                questionItems:[
                    {
                        id : 0,
                        questionId:0,
                        label:"",
                        item:"",
                        isRight:0, // 0 否 1 是
                    }
                ]
            };
            vm.questionList.push(questionVo);
            //重新渲染文本编辑器
            vm.$nextTick(function(){
                if (vm.questionList){
                    for (var  i = 0; i < vm.questionList.length; i++ ){
                        var question = "";
                        if(i != vm.questionList.length - 1){
                            question = $('#question_'+ i).editable('getHTML');
                        }
                        initQ(i,question);
                    }
                }
            })
        },
        //设置答案
        setRightAnswer:function (q, i ,r) {
            var questionVo =  vm.questionList[q];
            var item = questionVo.questionItems[i];
            item.isRight = r;
            Vue.set(vm.questionList, q, questionVo);
        },
        ///添加选项
        addItem:function (q) {
            var questionVo = vm.questionList[q];
            var item = {
                id : 0,
                questionId:0,
                label:"",
                item:"",
                isRight:0, // 0 否 1 是
            };
            vm.questionList[q].questionItems.push(item);
            Vue.set(vm.questionList, q, questionVo);
        },
        //删除选项
        deleteItem:function (q, i) {
            var questionVo = vm.questionList[q];
            vm.questionList[q].questionItems.splice(i, 1);
            Vue.set(vm.questionList, q, questionVo);
        },
        //计算分值
        calScore:function () {
            var t = 0;
            vm.questionList.forEach(function(item,i){
                t+= parseInt(item.score);
            })
            vm.totalSocre = t;
        },
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.questionList = []; //清空缓存
            vm.totalSocre = 0;
            vm.showList = false;
            vm.title = "新增";
            vm.exam = {};
            vm.queryUserList(0);
            vm.questionList[0] = {
                score:0,
                question:"",
                questionItems:[
                    {
                        id : "",
                        label:"",
                        item:"",
                        isRight:0, // 0 否 1 是
                    }
                ]

            };
            //重新渲染文本编辑器
            vm.$nextTick(function(){
                if (vm.questionList){
                    for (var  i = 0; i < vm.questionList.length; i++ ){
                        var question = "";
                        if(i != vm.questionList.length - 1){
                            question = $('#question_'+ i).editable('getHTML');
                        }
                        initQ(i,question);
                    }
                }
            })
        },
        update: function (event) {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";
            vm.questionList = []; //清空缓存
            vm.totalSocre = 0;
            vm.queryUserList(0);
            vm.getInfo(id)
        },
        saveOrUpdate: function (event) {
            var url = vm.exam.id == null ? "../exam/save" : "../exam/update";
            vm.exam.member = vm.memberArr.length > 0 ? vm.memberArr.join(",") : "";
            if (vm.questionList){
                for (var  i = 0; i < vm.questionList.length; i++ ){
                    vm.questionList[i].question = $('#question_'+ i).editable('getHTML');
                }
            }
            vm.exam.questionJson = JSON.stringify(vm.questionList);
            vm.exam.totalScore = vm.totalSocre;
            vm.exam.questionNum = vm.questionList.length;
            $.ajax({
                type: "POST",
                url: url,
                contentType: "application/json",
                data: JSON.stringify(vm.exam),
                success: function (r) {
                    if (r.code === 0) {
                        vm.questionList = []; //清空缓存
                        vm.totalSocre = 0;
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
                vm.questionList = vm.exam.questionList;

                var arr = [];
                if (vm.exam.member != null){
                    var sArr = vm.exam.member.split(",");
                    for (var i = 0; i <sArr.length; i++){
                        arr[i] = parseInt(sArr[i]);
                    }
                }
                vm.memberArr = arr;
                vm.calScore();
                //重新渲染文本编辑器
                vm.$nextTick(function(){
                    if (vm.questionList){
                        for (var  i = 0; i < vm.questionList.length; i++ ){
                            ///知识内容
                            var question = vm.questionList[i].question != null ? vm.questionList[i].question : "";
                            initQ(i,question);
                        }
                    }
                })


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
        generateId:function(index){
            return "question_" + index;
        }
    }
});

function initQ(i,question) {
    $('#question_'+ i).editable({
        inlineMode: false,
        alwaysBlank: true,
        height: '300px', //高度
        minHeight: '200px',
        language: "zh_cn",
        spellcheck: false,
        plainPaste: true,
        enableScript: false,
        imageButtons: ["floatImageLeft", "floatImageNone", "floatImageRight", "linkImage", "replaceImage", "removeImage"],
        allowedImageTypes: ["jpeg", "jpg", "png", "gif"],
        imageUploadURL: '../sys/oss/uploadFtp?platformCode=mall&dirFolderName=goods',
        imageUploadParams: {id: "edit"},
        imagesLoadURL: '../sys/oss/queryAll'
    });

    $('#question_'+ i).editable('setHTML', question);


}