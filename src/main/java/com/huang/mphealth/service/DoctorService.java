package com.huang.mphealth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.common.utils.PageUtils;
import com.huang.mphealth.entity.DoctorEntity;

import java.util.Map;

/**
 * 医生管理
 *
 * @author huang
 * @email sunlightcs@gmail.com
 * @date 2022-06-30 16:50:03
 */
public interface DoctorService extends IService<DoctorEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

