package com.frxs.merchant.common.dal.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.*;
import java.io.Serializable;
import com.frxs.framework.data.persistent.AbstractSuperEntity;
import lombok.Data;
/**
 * table name:  t_product_desc
 * author name: sh
 * create time: 2018-01-25 19:50:51
 */ 
@Data
@TableName("t_product_desc")
public class ProductDesc extends AbstractSuperEntity<ProductDesc> {

	/**
 	* 商品信息id
 	*/ 
	@TableId
	private Long productInfoId;
	/**
 	* 商品分享副标题
 	*/ 
	private String shareTitle;
	/**
 	* 商品简介
 	*/ 
	private String briefDesc;
	/**
 	* 商品详情
 	*/ 
	private String detailDesc;
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
		return this.productInfoId;
	}
}
