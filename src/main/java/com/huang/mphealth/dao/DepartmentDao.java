package com.huang.mphealth.dao;

import com.huang.mphealth.entity.DepartmentEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 部门表
 * 
 * @author huang
 * @email sunlightcs@gmail.com
 * @date 2022-06-30 16:50:03
 */
@Mapper
public interface DepartmentDao extends BaseMapper<DepartmentEntity> {
	
}
