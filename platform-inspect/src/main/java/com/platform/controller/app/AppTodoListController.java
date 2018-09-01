package com.platform.controller.app;

import com.platform.entity.dto.TodoVo;
import com.platform.entity.exam.ExamEntity;
import com.platform.entity.exam.ExamMemberEntity;
import com.platform.entity.inspect.InspectOrderEntity;
import com.platform.entity.inspect.InspectOrderFlowEntity;
import com.platform.entity.material.MaterialEntity;
import com.platform.entity.task.vo.TaskVo;
import com.platform.service.exam.ExamMemberService;
import com.platform.service.exam.ExamService;
import com.platform.service.inspect.IInspectOrderService;
import com.platform.service.inspect.InspectOrderFlowService;
import com.platform.service.material.MaterialService;
import com.platform.service.task.TaskGroupService;
import com.platform.utils.R;
import com.platform.utils.enums.InspectOrderStatusEnum;
import com.platform.utils.enums.TaskStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * ProjectName: inspect
 * CreateUser:  lixiaopeng
 * CreateTime : 2018/9/1 11:50
 * ModifyUser: bjlixiaopeng
 * Class Description:
 * To change this template use File | Settings | File and Code Template
 */

@Controller
@RequestMapping("/app")
public class AppTodoListController {

    @Autowired
    private TaskGroupService taskGroupService;

    @Autowired
    private InspectOrderFlowService inspectOrderFlowService;

    @Autowired
    private IInspectOrderService inspectOrderService;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private ExamService examService;

    @Autowired
    private ExamMemberService examMemberService;

    @RequestMapping("/todoList")
    public R queryTodoList(){
        return R.succeed().put("list",queryAllTodoList());
    }

    private List<TodoVo> queryAllTodoList(){
        List<TodoVo> list = new ArrayList<TodoVo>();

        List<TaskVo> taskTodoList = taskGroupService.queryTaskGroupTodoList();
        for (TaskVo taskVo : taskTodoList) {
            TodoVo todoVo = new TodoVo();
            todoVo.setId(Integer.valueOf(String.valueOf(taskVo.getTaskId())));
            todoVo.setName("任务-"+taskVo.getName());
            todoVo.setType(0);
            todoVo.setCreateTime(taskVo.getCreateTime());
            todoVo.setStatus(TaskStatusEnum.getDesc(taskVo.getStatus()));

            list.add(todoVo);
        }

        List<InspectOrderFlowEntity> inspectOrderFlowList = inspectOrderFlowService.queryAnomalyTodoList();
        for (InspectOrderFlowEntity orderFlowEntity : inspectOrderFlowList) {
            TodoVo todoVo = new TodoVo();

            InspectOrderEntity inspectOrder = inspectOrderService.queryObject(orderFlowEntity.getOrderId());
            MaterialEntity material = materialService.queryObject(inspectOrder.getMaterialId());
            if (orderFlowEntity.getType().intValue() == InspectOrderStatusEnum.PENDING.getCode().intValue()) {
                todoVo.setId(material.getId());
                todoVo.setName("异常-"+material.getMaterialName());
                todoVo.setType(1);
            }else if (orderFlowEntity.getType().intValue() == InspectOrderStatusEnum.REVIEW.getCode().intValue()) {
                todoVo.setName("复查-"+material.getMaterialName());
                todoVo.setType(2);
            }
            todoVo.setCreateTime(orderFlowEntity.getCreateTime());
            todoVo.setStatus(InspectOrderStatusEnum.getDesc(orderFlowEntity.getType()));

            list.add(todoVo);
        }

        List<ExamMemberEntity> examList = examMemberService.queryExamTodoList();
        for (ExamMemberEntity examMember : examList) {
            ExamEntity exam = examService.queryObject(examMember.getExamId());
            Long startTime = exam.getBeginTime().getTime();
            Long endTime = exam.getEndTime().getTime();
            Long today = new Date().getTime();
            if (today.longValue() >= startTime.longValue() && today.longValue()<=endTime && examMember.getScore().doubleValue()==0) {
                TodoVo todoVo = new TodoVo();
                todoVo.setId(Integer.valueOf(String.valueOf(examMember.getExamId())));
                todoVo.setName("考试-"+exam.getExamName());
                todoVo.setType(3);
                todoVo.setCreateTime(exam.getCreateTime());
                todoVo.setStatus("待完成");

                list.add(todoVo);
            }
        }

        return list;
    }

}
