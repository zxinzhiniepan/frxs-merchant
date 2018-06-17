package com.frxs.merchant.common.dal.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.*;
import java.io.Serializable;
import com.frxs.framework.data.persistent.AbstractSuperEntity;
import lombok.Data;
/**
 * table name:  t_product_attrvalue
 * author name: sh
 * create time: 2018-01-25 19:50:51
 */ 
@Data
@TableName("t_product_attrvalue")
public class ProductAttrvalue extends AbstractSuperEntity<ProductAttrvalue> {

	/**
 	* 商品属性值id
 	*/ 
	@TableId
	private Long attrValId;
	/**
 	* 属性id
 	*/ 
	private Long attrId;
	/**
 	* 属性值
 	*/ 
	private String val;
	/**
 	* 创建人id
 	*/ 
	private Long createUserId;
	/**
 	* 创建人用户名
 	*/ 
	private String createUserName;
	/**
 	* 修改人id
 	*/ 
	private Long modifyUserId;
	/**
 	* 修改人用户名
 	*/ 
	private String modifyUserName;
	@Override
	protected Serializable pkVal() {
		return this.attrValId;
	}
}
