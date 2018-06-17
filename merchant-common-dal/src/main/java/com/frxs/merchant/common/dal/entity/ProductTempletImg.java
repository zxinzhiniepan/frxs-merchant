package com.frxs.merchant.common.dal.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.*;
import java.io.Serializable;
import com.frxs.framework.data.persistent.AbstractSuperEntity;
import lombok.Data;
/**
 * table name:  t_product_templet_img
 * author name: sh
 * create time: 2018-01-25 19:50:51
 */ 
@Data
@TableName("t_product_templet_img")
public class ProductTempletImg extends AbstractSuperEntity<ProductTempletImg> {

	/**
 	* 商品模版图片id
 	*/ 
	@TableId
	private Long templetImgId;
	/**
 	* 商品模版id
 	*/ 
	private Long templetId;
	/**
 	* 800*800原图地址
 	*/ 
	private String originalImgUrl;
	/**
 	* 60*60图片地址
 	*/ 
	private String imgUrl60;
	/**
 	* 120*120图片地址
 	*/ 
	private String imgUrl120;
	/**
 	* 200*200图片地址
 	*/ 
	private String imgUrl200;
	/**
 	* 400*400图片地址
 	*/ 
	private String imgUrl400;
	/**
 	* 图片类型:AD-广告图，PRIMARY-主图，DETAIL-详情图
 	*/ 
	private String imgType;
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
		return this.templetImgId;
	}
}
