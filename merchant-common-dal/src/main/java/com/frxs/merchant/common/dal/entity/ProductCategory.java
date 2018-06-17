package com.frxs.merchant.common.dal.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.*;
import java.io.Serializable;
import com.frxs.framework.data.persistent.AbstractSuperEntity;
import lombok.Data;
/**
 * table name:  t_product_category
 * author name: sh
 * create time: 2018-01-25 19:50:51
 */ 
@Data
@TableName("t_product_category")
public class ProductCategory extends AbstractSuperEntity<ProductCategory> {

	/**
 	* 分类id
 	*/ 
	@TableId
	private Long cateId;
	/**
 	* 上级分类id
 	*/ 
	private Long parentId;
	/**
 	* 分类名称
 	*/ 
	private String cateName;
	/**
 	* 分类图标
 	*/ 
	private String cateImgUrl;
	/**
 	* 层级:根为0
 	*/ 
	private Integer cateLevel;
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
		return this.cateId;
	}
}
