package com.platform.service.stat;

import com.platform.utils.R;

import java.util.Map;

/**
 * 统计
 *
 * @author bjsonghongxu
 * @create 2018-08-19 10:01
 **/
public interface IStatService {


    R stat(Map<String, Object> params);
}
