package com.huang.mphealth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.common.utils.PageUtils;
import com.huang.mphealth.entity.DoctorPatientEntity;

import java.util.Map;

/**
 * 医生和病人关系
 *
 * @author huang
 * @email sunlightcs@gmail.com
 * @date 2022-06-30 16:50:03
 */
public interface DoctorPatientService extends IService<DoctorPatientEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

