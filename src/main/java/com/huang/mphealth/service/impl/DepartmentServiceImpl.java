package com.huang.mphealth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.mphealth.common.PageUtils;
import com.huang.mphealth.common.Query;
import com.huang.mphealth.dao.DepartmentDao;
import com.huang.mphealth.entity.DepartmentEntity;
import com.huang.mphealth.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("departmentService")
public class DepartmentServiceImpl extends ServiceImpl<DepartmentDao, DepartmentEntity> implements DepartmentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DepartmentEntity> page = this.page(
                new Query<DepartmentEntity>().getPage(params),
                new QueryWrapper<DepartmentEntity>()
        );

        return new PageUtils(page);
    }

}