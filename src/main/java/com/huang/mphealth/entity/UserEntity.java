package com.huang.mphealth.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 注册信息
 * 
 * @author huang
 * @email sunlightcs@gmail.com
 * @date 2022-06-30 16:50:03
 */
@Data
@TableName("user")
public class UserEntity implements Serializable {
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
	 * 手机号
	 */
	private String phone;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 身份证号
	 */
	private String idNumber;
	/**
	 * 状态 0:禁用，1:正常
	 */
	private Integer status;

}
