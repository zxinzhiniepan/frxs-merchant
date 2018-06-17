package com.frxs.merchant.common.dal.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.*;
import java.io.Serializable;
import com.frxs.framework.data.persistent.AbstractSuperEntity;
import lombok.Data;
/**
 * table name:  t_vendor_type
 * author name: sh
 * create time: 2018-01-25 19:50:51
 */ 
@Data
@TableName("t_vendor_type")
public class VendorType extends AbstractSuperEntity<VendorType> {

	/**
 	* 供应商类型ID
 	*/ 
	@TableId
	private Integer vendorTypeId;
	/**
 	* 供应商分类名称
 	*/ 
	private String vendorTypeName;
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
		return this.vendorTypeId;
	}
}
