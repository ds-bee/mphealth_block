package com.huang.mphealth.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 病人管理
 * 
 * @author huang
 * @email sunlightcs@gmail.com
 * @date 2022-06-30 16:50:03
 */
@Data
@TableName("patient")
public class PatientEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Long id;
	/**
	 * 病人名字
	 */
	private String name;
	/**
	 * 医生id
	 */
	private Long categoryId;
	/**
	 * 病情描述信息
	 */
	private String description;
	/**
	 * 医治状态：0 完成 1 未完成
	 */
	private Integer status;
	/**
	 * 确认病情上链哈希
	 */
	private String patientHash;
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
