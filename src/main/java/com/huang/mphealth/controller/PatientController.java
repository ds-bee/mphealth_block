package com.huang.mphealth.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.huang.common.utils.PageUtils;
import com.huang.common.utils.R;
import com.huang.mphealth.entity.DoctorEntity;
import com.huang.mphealth.entity.PatientEntity;
import com.huang.mphealth.model.PatientDTO;
import com.huang.mphealth.service.DoctorService;
import com.huang.mphealth.service.PatientService;
import com.huang.mphealth.utlis.CryptoUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 病人管理
 *
 * @author huang
 * @email sunlightcs@gmail.com
 * @date 2022-06-30 16:50:03
 */
@RestController
@RequestMapping("mphealth/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = patientService.queryPage(params);

        return R.ok().put("page", page);
    }




    /**
     * 信息点击详情多返回一个医生姓名信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		PatientEntity patient = patientService.getById(id);
        DoctorEntity doctorEntity = doctorService.getById(patient.getCategoryId());
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setDorctorName(doctorEntity.getName());
        BeanUtils.copyProperties(patient,patientDTO);
        return R.ok().put("patient", patientDTO);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody PatientDTO patientDTO){
        PatientEntity patientEntity = new PatientEntity();

        BeanUtils.copyProperties(patientDTO,patientEntity);
        String dorctorName = patientDTO.getDorctorName();
        LambdaQueryWrapper<DoctorEntity> queryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.eq(dorctorName != null,DoctorEntity::getName,dorctorName);

        DoctorEntity one = doctorService.getOne(queryWrapper);
        patientEntity.setCategoryId(one.getId());
        //上链操作
        patientEntity.setPatientHash(CryptoUtil.sHA256(""+ patientDTO.getId()));
        patientService.save(patientEntity);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody PatientDTO patientDTO){
        LambdaQueryWrapper<DoctorEntity> queryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.eq(patientDTO.getDorctorName() != null,DoctorEntity::getName,patientDTO.getDorctorName() );

        DoctorEntity one = doctorService.getOne(queryWrapper);
        if(one != null){

            PatientEntity patient = new PatientEntity();

            patientService.updateById(patient);

            return R.ok();
        }else {
            return R.error("医生信息有误，请确认");
        }
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		patientService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
