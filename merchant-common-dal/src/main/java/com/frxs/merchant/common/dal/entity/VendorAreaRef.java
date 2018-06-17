package com.frxs.merchant.common.dal.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.*;
import java.io.Serializable;
import com.frxs.framework.data.persistent.AbstractSuperEntity;
import lombok.Data;
/**
 * table name:  t_vendor_area_ref
 * author name: sh
 * create time: 2018-01-25 19:50:51
 */ 
@Data
@TableName("t_vendor_area_ref")
public class VendorAreaRef extends AbstractSuperEntity<VendorAreaRef> {

	/**
 	* 主键
 	*/ 
	@TableId
	private Long id;
	/**
 	* 供应商ID
 	*/ 
	private Long vendorId;
	/**
 	* 区域ID
 	*/ 
	private Integer areaId;
	/**
	 * 是否删除
	 */
	private String isDeleted;
	/**
 	* 创建用户ID
 	*/ 
	private Long createUserId;
	/**
 	* 创建用户名称
 	*/ 
	private String createUserName;
	/**
 	* 最后修改用户ID
 	*/ 
	private Long modifyUserId;
	/**
 	* 最后修改用户名称
 	*/ 
	private String modifyUserName;
	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}
