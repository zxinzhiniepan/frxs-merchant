/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.common.dal.entity;

import com.frxs.framework.common.domain.Money;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.*;
import java.io.Serializable;
import com.frxs.framework.data.persistent.AbstractSuperEntity;
import lombok.Data;
/**
 * table name:  t_product_service_detail
 * author name: sh
 * create time: 2018-01-25 19:50:51
 */ 
@Data
@TableName("t_product_service_detail")
public class ProductServiceDetail extends AbstractSuperEntity<ProductServiceDetail> {

	/**
 	* 供应商商品服务费id
 	*/ 
	@TableId
	private Long serviceDetailId;
	/**
 	* 商品id
 	*/ 
	private Long productId;
	/**
 	* 平台服务费编码
 	*/ 
	private String serviceAmtCode;
	/**
 	* 平台服务费值
 	*/
	@TableField("serviceAmt")
	private Money serviceAmt;
	/**
 	* 平台服务费百分比
 	*/ 
	private BigDecimal serviceRate;
	/**
 	* 描述
 	*/ 
	private String serviceDesc;
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
		return this.serviceDetailId;
	}
}
