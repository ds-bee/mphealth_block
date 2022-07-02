package com.huang.mphealth.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * 医生和病人关系
 * 
 * @author huang
 * @email sunlightcs@gmail.com
 * @date 2022-06-30 16:50:03
 */
@Data
@TableName("doctor_patient")
public class DoctorPatientEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Long id;
	/**
	 * 医生id 
	 */
	private String doctorId;
	/**
	 * 病人id
	 */
	private String patientId;
	/**
	 * 病症描述 （冗余字段）
	 */
	private String illness;
	/**
	 * 问诊费用（冗余字段）
	 */
	private BigDecimal price;
	/**
	 * 确认费用上链哈希
	 */
	private String hash;
	/**
	 * 排序
	 */
	private Integer sort;
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
	/**
	 * 是否删除
	 */
	private Integer isDeleted;

}
