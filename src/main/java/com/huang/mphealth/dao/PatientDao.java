package com.huang.mphealth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huang.mphealth.entity.PatientEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 病人管理
 * 
 * @author huang
 * @email sunlightcs@gmail.com
 * @date 2022-06-30 16:50:03
 */
@Mapper
public interface PatientDao extends BaseMapper<PatientEntity> {
	
}
