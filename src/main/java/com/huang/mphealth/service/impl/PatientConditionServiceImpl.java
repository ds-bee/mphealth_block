package com.huang.mphealth.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.common.utils.PageUtils;
import com.huang.common.utils.Query;

import com.huang.mphealth.dao.PatientConditionDao;
import com.huang.mphealth.entity.PatientConditionEntity;
import com.huang.mphealth.service.PatientConditionService;


@Service("patientConditionService")
public class PatientConditionServiceImpl extends ServiceImpl<PatientConditionDao, PatientConditionEntity> implements PatientConditionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PatientConditionEntity> page = this.page(
                new Query<PatientConditionEntity>().getPage(params),
                new QueryWrapper<PatientConditionEntity>()
        );

        return new PageUtils(page);
    }

}