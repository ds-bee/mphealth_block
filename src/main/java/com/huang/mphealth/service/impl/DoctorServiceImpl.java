package com.huang.mphealth.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.common.utils.PageUtils;
import com.huang.common.utils.Query;

import com.huang.mphealth.dao.DoctorDao;
import com.huang.mphealth.entity.DoctorEntity;
import com.huang.mphealth.service.DoctorService;


@Service("doctorService")
public class DoctorServiceImpl extends ServiceImpl<DoctorDao, DoctorEntity> implements DoctorService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DoctorEntity> page = this.page(
                new Query<DoctorEntity>().getPage(params),
                new QueryWrapper<DoctorEntity>()
        );

        return new PageUtils(page);
    }

}