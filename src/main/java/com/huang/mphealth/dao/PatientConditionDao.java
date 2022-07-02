package com.huang.mphealth.dao;

import com.huang.mphealth.entity.PatientConditionEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 病人和治疗方案关系表
 * 
 * @author huang
 * @email sunlightcs@gmail.com
 * @date 2022-06-30 16:50:03
 */
@Mapper
public interface PatientConditionDao extends BaseMapper<PatientConditionEntity> {
	
}
