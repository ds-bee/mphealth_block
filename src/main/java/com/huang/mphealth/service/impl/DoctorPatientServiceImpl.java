package com.huang.mphealth.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.common.utils.PageUtils;
import com.huang.common.utils.Query;

import com.huang.mphealth.dao.DoctorPatientDao;
import com.huang.mphealth.entity.DoctorPatientEntity;
import com.huang.mphealth.service.DoctorPatientService;


@Service("doctorPatientService")
public class DoctorPatientServiceImpl extends ServiceImpl<DoctorPatientDao, DoctorPatientEntity> implements DoctorPatientService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<DoctorPatientEntity> page = this.page(
                new Query<DoctorPatientEntity>().getPage(params),
                new QueryWrapper<DoctorPatientEntity>()
        );

        return new PageUtils(page);
    }

}