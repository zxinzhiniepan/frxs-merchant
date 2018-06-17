package com.frxs.merchant.common.dal.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.*;
import java.io.Serializable;
import com.frxs.framework.data.persistent.AbstractSuperEntity;
import lombok.Data;
/**
 * table name:  t_product_templet_area
 * author name: sh
 * create time: 2018-01-25 19:50:51
 */ 
@Data
@TableName("t_product_templet_area")
public class ProductTempletArea extends AbstractSuperEntity<ProductTempletArea> {

	/**
 	* 商品模版区域id
 	*/ 
	@TableId
	private Long templetOpareaId;
	/**
 	* 模版id
 	*/ 
	private Long templetId;
	/**
 	* 区域id
 	*/ 
	private Long opAreaId;
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
		return this.templetOpareaId;
	}
}
