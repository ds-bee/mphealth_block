package com.huang.mphealth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.mphealth.common.PageUtils;
import com.huang.mphealth.entity.EmployeeEntity;

import java.util.Map;

/**
 * 用户信息
 *
 * @author huang
 * @email sunlightcs@gmail.com
 * @date 2022-06-30 16:50:03
 */
public interface EmployeeService extends IService<EmployeeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

