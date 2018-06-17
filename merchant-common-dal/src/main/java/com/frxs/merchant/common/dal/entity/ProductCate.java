package com.frxs.merchant.common.dal.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.*;
import java.io.Serializable;
import com.frxs.framework.data.persistent.AbstractSuperEntity;
import lombok.Data;
/**
 * table name:  t_product_cate
 * author name: sh
 * create time: 2018-01-25 19:50:51
 */ 
@Data
@TableName("t_product_cate")
public class ProductCate extends AbstractSuperEntity<ProductCate> {

	/**
 	* 商品分类id
 	*/ 
	@TableId
	private Long productCateId;
	/**
 	* 分类id
 	*/ 
	private Long cateId;
	/**
 	* 商品id
 	*/ 
	private Long productId;
	/**
 	* 创建人id
 	*/ 
	private Long createUserId;
	/**
 	* 创建人用户名
 	*/ 
	private String createUserName;
	@Override
	protected Serializable pkVal() {
		return this.productCateId;
	}
}
