package com.platform.entity.task.vo;

import com.platform.vo.SelectVo;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TaskGroupVo extends SelectVo implements Serializable{

    private static final long serialVersionUID = -2963096973056954051L;

    List<SelectVo> taskList;

    public List<SelectVo> getTaskList() {
        if (CollectionUtils.isEmpty(taskList)) {
            taskList = new ArrayList<SelectVo>();
        }
        return taskList;
    }

    public void setTaskList(List<SelectVo> taskList) {
        this.taskList = taskList;
    }
}
