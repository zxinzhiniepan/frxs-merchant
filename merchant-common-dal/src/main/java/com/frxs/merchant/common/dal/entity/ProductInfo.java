/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.common.dal.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.*;
import java.io.Serializable;
import com.frxs.framework.data.persistent.AbstractSuperEntity;
import lombok.Data;
/**
 * table name:  t_product_info
 * author name: sh
 * create time: 2018-01-25 19:50:51
 */ 
@Data
@TableName("t_product_info")
public class ProductInfo extends AbstractSuperEntity<ProductInfo> {

	/**
 	* 商品信息id
 	*/ 
	@TableId
	private Long productInfoId;
	/**
 	* 区域id
 	*/ 
	private Long areaId;
	/**
 	* 商品模版id
 	*/ 
	private Long templetId;
	/**
 	* 是否已创建为模版:TRUE-已成为基础商品,FALSE-非基础商品
 	*/ 
	private String isCreateTemplet;
	/**
 	* 商品总库存(sku库存总和)
 	*/ 
	private BigDecimal totalStock;
	/**
 	* 商品名称
 	*/ 
	private String productName;
	/**
 	* 商品标题
 	*/ 
	private String productTitle;
	/**
 	* 品牌id
 	*/ 
	private Long brandId;
	/**
 	* 品牌名称
 	*/ 
	private String brandName;
	/**
 	* 创建人id
 	*/ 
	private Long createUserId;
	/**
 	* 创建人名称
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
		return this.productInfoId;
	}
}
