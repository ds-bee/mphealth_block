package com.huang.mphealth.dao;

import com.huang.mphealth.entity.EmployeeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户信息
 * 
 * @author huang
 * @email sunlightcs@gmail.com
 * @date 2022-06-30 16:50:03
 */
@Mapper
public interface EmployeeDao extends BaseMapper<EmployeeEntity> {
	
}
