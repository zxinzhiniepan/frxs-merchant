package com.frxs.merchant.common.dal.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.*;
import java.io.Serializable;
import com.frxs.framework.data.persistent.AbstractSuperEntity;
import lombok.Data;
/**
 * table name:  t_product_attr_cate
 * author name: sh
 * create time: 2018-01-25 19:50:51
 */ 
@Data
@TableName("t_product_attr_cate")
public class ProductAttrCate extends AbstractSuperEntity<ProductAttrCate> {

	/**
 	* 商品属性分类id
 	*/ 
	@TableId
	private Long attrCateId;
	/**
 	* 商品属性id
 	*/ 
	private Long attrId;
	/**
 	* 分类id
 	*/ 
	private Long cateId;
	/**
 	* 创建人id
 	*/ 
	private Long createUserId;
	/**
 	* 创建人名称
 	*/ 
	private String createUserName;
	@Override
	protected Serializable pkVal() {
		return this.attrCateId;
	}
}
