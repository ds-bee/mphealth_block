package com.huang.mphealth.dao;

import com.huang.mphealth.entity.DoctorPatientEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 医生和病人关系
 * 
 * @author huang
 * @email sunlightcs@gmail.com
 * @date 2022-06-30 16:50:03
 */
@Mapper
public interface DoctorPatientDao extends BaseMapper<DoctorPatientEntity> {
	
}
