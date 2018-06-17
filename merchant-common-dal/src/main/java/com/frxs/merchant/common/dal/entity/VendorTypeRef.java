package com.frxs.merchant.common.dal.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.*;
import java.io.Serializable;
import com.frxs.framework.data.persistent.AbstractSuperEntity;
import lombok.Data;
/**
 * table name:  t_vendor_type_ref
 * author name: sh
 * create time: 2018-01-25 19:50:51
 */ 
@Data
@TableName("t_vendor_type_ref")
public class VendorTypeRef extends AbstractSuperEntity<VendorTypeRef> {

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
 	* 供应商类型ID
 	*/ 
	private Integer vendorTypeId;
	/**
 	* 是否删除
 	*/ 
	private String isDeleted;
	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}
