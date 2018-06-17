package com.frxs.merchant.common.dal.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.*;
import java.io.Serializable;
import com.frxs.framework.data.persistent.AbstractSuperEntity;
import lombok.Data;
/**
 * table name:  t_product_attrval_relation
 * author name: sh
 * create time: 2018-01-25 19:50:51
 */ 
@Data
@TableName("t_product_attrval_relation")
public class ProductAttrvalRelation extends AbstractSuperEntity<ProductAttrvalRelation> {

	/**
 	* 商品属性关联表id
 	*/ 
	@TableId
	private Long productAttrValId;
	/**
 	* 商品id
 	*/ 
	private Long productId;
	/**
 	* 商品信息id:冗余为方面根据商品信息查所有属性
 	*/ 
	private Long productInfoId;
	/**
 	* 属性id
 	*/ 
	private Long attrId;
	/**
 	* 属性名称
 	*/ 
	private String attrName;
	/**
 	* 属性值id
 	*/ 
	private Long attrValId;
	/**
 	* 属性值
 	*/ 
	private String attrVal;
	/**
 	* 层级:从0开始
 	*/ 
	private Integer attrLevel;
	/**
 	* 创建人id
 	*/ 
	private Long createUserId;
	/**
 	* 创建人用户名
 	*/ 
	private String craeteUserName;
	@Override
	protected Serializable pkVal() {
		return this.productAttrValId;
	}
}
