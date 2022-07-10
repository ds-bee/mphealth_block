package com.huang.mphealth.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.mphealth.common.PageUtils;
import com.huang.mphealth.entity.DepartmentEntity;
import com.huang.mphealth.entity.DoctorEntity;
import com.huang.mphealth.model.DoctorDto;
import com.huang.mphealth.service.DepartmentService;
import com.huang.mphealth.service.DoctorService;
import com.huang.mphealth.utils.R;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 医生管理
 *
 * @author huang
 * @email sunlightcs@gmail.com
 * @date 2022-06-30 16:50:03
 */
@RestController
@RequestMapping("mphealth/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DepartmentService departmentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = doctorService.queryPage(params);

        return R.ok().put("page", page);
    }

    @GetMapping("/page")
    public com.huang.mphealth.common.R<Page> page(int page, int pageSize, String name){

        //构造分页构造器对象
        Page<DoctorEntity> pageInfo = new Page<>(page,pageSize);
        Page<DoctorDto> doctorDtoPage = new Page<>();

        //条件构造器
        LambdaQueryWrapper<DoctorEntity> queryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.like(name != null,DoctorEntity::getName,name);
        //添加排序条件
        queryWrapper.orderByDesc(DoctorEntity::getUpdateTime);


        //执行分页查询
        doctorService.page(pageInfo,queryWrapper);

        //对象拷贝
        BeanUtils.copyProperties(pageInfo,doctorDtoPage);

        List<DoctorEntity> records = pageInfo.getRecords();

        List<DoctorDto> list = records.stream().map((item) -> {
            DoctorDto doctorDto = new DoctorDto();

            BeanUtils.copyProperties(item,doctorDto);
            //分类id
            String departmentId = item.getDepartmentId();
            //根据id查询分类对象
            DepartmentEntity department = departmentService.getById(departmentId);

            if(department != null){
                String patientName = department.getName();
                String department1 = department.getDepartment();
                doctorDto.setTeamName(patientName);
                doctorDto.setDepartmentName(department1);
            }
            return doctorDto;
        }).collect(Collectors.toList());

        doctorDtoPage.setRecords(list);

        return com.huang.mphealth.common.R.success(doctorDtoPage);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		DoctorEntity doctor = doctorService.getById(id);

        return R.ok().put("doctor", doctor);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DoctorDto doctorDto){
        DoctorEntity doctorEntity = new DoctorEntity();

        BeanUtils.copyProperties(doctorDto,doctorEntity);
        String departmentName = doctorDto.getDepartmentName();
        LambdaQueryWrapper<DepartmentEntity> queryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.eq(departmentName != null,DepartmentEntity::getDepartment,departmentName);

        DepartmentEntity one = departmentService.getOne(queryWrapper);
        doctorEntity.setDepartmentId(one.getId() + "");
//        //上链操作
//        patientEntity.setPatientHash(CryptoUtil.sHA256(""+ patientDTO.getId()));
//        patientService.save(patientEntity);
        doctorService.save(doctorEntity);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody DoctorDto doctorDto){
        LambdaQueryWrapper<DepartmentEntity> queryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.eq(doctorDto.getDepartmentName() != null,DepartmentEntity::getDepartment,doctorDto.getDepartmentName());

        DepartmentEntity one = departmentService.getOne(queryWrapper);
        if( one != null){

            DoctorEntity doctorEntity = new DoctorEntity();
            BeanUtils.copyProperties(doctorDto,doctorEntity);
            doctorService.updateById(doctorEntity);
            return R.ok();
        }else {
            return R.error("部门信息有误，添加信息失败");
        }
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		doctorService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
