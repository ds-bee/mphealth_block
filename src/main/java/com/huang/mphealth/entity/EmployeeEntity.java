package com.huang.mphealth.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * 用户信息
 * 
 * @author huang
 * @email sunlightcs@gmail.com
 * @date 2022-06-30 16:50:03
 */
@Data
@TableName("employee")
public class EmployeeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Long id;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 权限 0：管理员 1：医生 2：其他
	 */
	private String authority;
	/**
	 * 状态 0:禁用，1:正常
	 */
	private Integer status;
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
