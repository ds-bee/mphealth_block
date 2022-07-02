package com.huang.mphealth.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 部门表
 * 
 * @author huang
 * @email sunlightcs@gmail.com
 * @date 2022-06-30 16:50:03
 */
@Data
@TableName("department")
public class DepartmentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Long id;
	/**
	 * 部门名称
	 */
	private String department;
	/**
	 * 团队名称
	 */
	private String name;
	//创建时间
	@TableField(fill = FieldFill.INSERT)
	private LocalDateTime createTime;


	//更新时间
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;


	//创建人
	@TableField(fill = FieldFill.INSERT)
	private Long createUser;


	//修改人
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Long updateUser;

}
