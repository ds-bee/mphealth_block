package com.huang.mphealth.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.common.utils.PageUtils;
import com.huang.common.utils.Query;

import com.huang.mphealth.dao.PatientDao;
import com.huang.mphealth.entity.PatientEntity;
import com.huang.mphealth.service.PatientService;


@Service("patientService")
public class PatientServiceImpl extends ServiceImpl<PatientDao, PatientEntity> implements PatientService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PatientEntity> page = this.page(
                new Query<PatientEntity>().getPage(params),
                new QueryWrapper<PatientEntity>()
        );

        return new PageUtils(page);
    }

}