package com.huang.mphealth.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.mphealth.common.PageUtils;
import com.huang.mphealth.entity.DepartmentEntity;
import com.huang.mphealth.service.DepartmentService;
import com.huang.mphealth.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 部门表
 *
 * @author huang
 * @email sunlightcs@gmail.com
 * @date 2022-06-30 16:50:03
 */
@RestController
@RequestMapping("mphealth/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = departmentService.queryPage(params);

        return R.ok().put("page", page);
    }

    @GetMapping("/page")
    public com.huang.mphealth.common.R<Page> page(int page, int pageSize){
        //分页构造器
        Page<DepartmentEntity> pageInfo = new Page<>(page,pageSize);
        //条件构造器
        LambdaQueryWrapper<DepartmentEntity> queryWrapper = new LambdaQueryWrapper<>();
        //分页查询
        departmentService.page(pageInfo,queryWrapper);
        return com.huang.mphealth.common.R.success(pageInfo);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		DepartmentEntity department = departmentService.getById(id);

        return R.ok().put("department", department);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DepartmentEntity department){
		departmentService.save(department);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody DepartmentEntity department){
		departmentService.updateById(department);

        return R.ok();
    }

    /**
     * 删除
     *
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		departmentService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
