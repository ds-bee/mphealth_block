package com.huang.mphealth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.mphealth.common.PageUtils;
import com.huang.mphealth.entity.PatientConditionEntity;

import java.util.Map;

/**
 * 病人和治疗方案关系表
 *
 * @author huang
 * @email sunlightcs@gmail.com
 * @date 2022-06-30 16:50:03
 */
public interface PatientConditionService extends IService<PatientConditionEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

