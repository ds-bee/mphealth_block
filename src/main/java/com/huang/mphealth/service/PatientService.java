package com.huang.mphealth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.mphealth.common.PageUtils;
import com.huang.mphealth.entity.PatientEntity;

import java.util.Map;

/**
 * 病人管理
 *
 * @author huang
 * @email sunlightcs@gmail.com
 * @date 2022-06-30 16:50:03
 */
public interface PatientService extends IService<PatientEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

