package com.frxs.merchant.common.dal.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.*;
import java.io.Serializable;
import com.frxs.framework.data.persistent.AbstractSuperEntity;
import lombok.Data;
/**
 * table name:  t_sys_dict
 * author name: sh
 * create time: 2018-01-25 19:50:51
 */ 
@Data
@TableName("t_sys_dict")
public class SysDict extends AbstractSuperEntity<SysDict> {

	/**
 	* 主键
 	*/ 
	@TableId
	private Integer dictId;
	/**
 	* 字典编码
 	*/ 
	private String dictCode;
	/**
 	* 字典名称
 	*/ 
	private String dictName;
	/**
 	* 是否可编辑
 	*/ 
	private Integer isEnableEdit;
	/**
 	* 是否删除
 	*/ 
	private Integer IsDeleted;
	/**
 	* 备注
 	*/ 
	private String remark;
	/**
 	* 创建用户ID
 	*/ 
	private Integer createUserId;
	/**
 	* 创建用户名称
 	*/ 
	private String createUserName;
	/**
 	* 修改用户ID
 	*/ 
	private Integer modifyUserId;
	/**
 	* 修改用户名称
 	*/ 
	private String modifyUserName;
	@Override
	protected Serializable pkVal() {
		return this.dictId;
	}
}
