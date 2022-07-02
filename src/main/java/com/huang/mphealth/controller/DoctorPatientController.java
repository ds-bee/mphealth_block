package com.huang.mphealth.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.huang.common.utils.PageUtils;
import com.huang.common.utils.R;
import com.huang.mphealth.entity.DoctorEntity;
import com.huang.mphealth.entity.DoctorPatientEntity;
import com.huang.mphealth.entity.PatientEntity;
import com.huang.mphealth.model.DoctorAndPatientDTO;
import com.huang.mphealth.service.DoctorPatientService;
import com.huang.mphealth.service.DoctorService;
import com.huang.mphealth.service.PatientService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 医生和病人关系
 *
 * @author huang
 * @email sunlightcs@gmail.com
 * @date 2022-06-30 16:50:03
 */
@RestController
@RequestMapping("mphealth/doctorpatient")
public class DoctorPatientController {
    @Autowired
    private DoctorPatientService doctorPatientService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = doctorPatientService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		DoctorPatientEntity doctorPatient = doctorPatientService.getById(id);

        return R.ok().put("doctorPatient", doctorPatient);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DoctorAndPatientDTO doctorAndPatientDTO){
        DoctorPatientEntity doctorPatient = new DoctorPatientEntity();
        String doctorName = doctorAndPatientDTO.getDoctorName();

        String patientName = doctorAndPatientDTO.getPatientName();
        LambdaQueryWrapper<DoctorEntity> queryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.eq(doctorName != null,DoctorEntity::getName,doctorName);
        DoctorEntity one = doctorService.getOne(queryWrapper);
        if(one != null){
            doctorAndPatientDTO.setDoctorId(one.getId() + "");
        }else {
            return R.error("未找到医生信息");
        }

        LambdaQueryWrapper<PatientEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        lambdaQueryWrapper.eq(patientName != null,PatientEntity::getName,patientName);
        PatientEntity one1 = patientService.getOne(lambdaQueryWrapper);
        if(one1 != null){
            doctorAndPatientDTO.setPatientId(one1.getId() + "");
        }else {
            return R.error("未找到病人信息");
        }
        BeanUtils.copyProperties(doctorAndPatientDTO,doctorPatient);


        doctorPatientService.save(doctorPatient);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody DoctorPatientEntity doctorPatient){
		doctorPatientService.updateById(doctorPatient);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		doctorPatientService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
