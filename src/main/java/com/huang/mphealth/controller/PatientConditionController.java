package com.huang.mphealth.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.common.utils.PageUtils;
import com.huang.common.utils.R;
import com.huang.mphealth.entity.PatientConditionEntity;
import com.huang.mphealth.entity.PatientEntity;
import com.huang.mphealth.model.PatientConditionDTO;
import com.huang.mphealth.service.DoctorService;
import com.huang.mphealth.service.PatientConditionService;
import com.huang.mphealth.service.PatientService;
import com.huang.mphealth.utlis.CryptoUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



/**
 * 病人和治疗方案关系表
 *
 * @author huang
 * @email sunlightcs@gmail.com
 * @date 2022-06-30 16:50:03
 */
@RestController
@RequestMapping("mphealth/patientcondition")
public class PatientConditionController {
    @Autowired
    private PatientConditionService patientConditionService;
    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = patientConditionService.queryPage(params);

        return R.ok().put("page", page);
    }

    @GetMapping("/page")
    public com.huang.mphealth.common.R<Page> page(int page, int pageSize, String name){

        //构造分页构造器对象
        Page<PatientConditionEntity> pageInfo = new Page<>(page,pageSize);
        Page<PatientConditionDTO> PatientConditionDTOPage = new Page<>();

//        //条件构造器
//        LambdaQueryWrapper<PatientConditionEntity> queryWrapper = new LambdaQueryWrapper<>();
//        //添加过滤条件
//        queryWrapper.like(name != null,PatientConditionEntity::getName,name);
//        //添加排序条件
//        queryWrapper.orderByDesc(PatientConditionEntity::getUpdateTime);


        //执行分页查询
        patientConditionService.page(pageInfo);

        //对象拷贝
        BeanUtils.copyProperties(pageInfo,PatientConditionDTOPage);

        List<PatientConditionEntity> records = pageInfo.getRecords();

        List<PatientConditionDTO> list = records.stream().map((item) -> {
            PatientConditionDTO dishDto = new PatientConditionDTO();

            BeanUtils.copyProperties(item,dishDto);

            Long categoryId = item.getPatientId();//分类id
            //根据id查询分类对象
            PatientEntity patient = patientService.getById(categoryId);

            if(patient != null){
                String patientName = patient.getName();
                dishDto.setPatientName(patientName);
            }
            return dishDto;
        }).collect(Collectors.toList());

        PatientConditionDTOPage.setRecords(list);

        return com.huang.mphealth.common.R.success(PatientConditionDTOPage);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		PatientConditionEntity patientCondition = patientConditionService.getById(id);

        return R.ok().put("patientCondition", patientCondition);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody PatientConditionDTO patientConditionDTO){
        String patientName = patientConditionDTO.getPatientName();
//        条件构造器
        LambdaQueryWrapper<PatientEntity> queryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.eq(patientName != null,PatientEntity::getName,patientName);
        PatientEntity one = patientService.getOne(queryWrapper);
        if(one != null){
            PatientConditionEntity patientConditionEntity = new PatientConditionEntity();
            patientConditionEntity.setPatientId(one.getId());
            patientConditionService.save(patientConditionEntity);
            return R.ok();
        }else {
            return R.error("病人不存在");
        }

    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody PatientConditionEntity patientCondition){
        patientCondition.setHash(CryptoUtil.sHA256(patientCondition.getId() + ""));
		patientConditionService.updateById(patientCondition);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		patientConditionService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
