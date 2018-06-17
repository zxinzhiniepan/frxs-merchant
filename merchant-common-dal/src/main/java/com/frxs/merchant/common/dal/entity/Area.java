package com.frxs.merchant.common.dal.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.*;
import java.io.Serializable;
import com.frxs.framework.data.persistent.AbstractSuperEntity;
import lombok.Data;
/**
 * table name:  t_area
 * author name: sh
 * create time: 2018-01-25 19:50:51
 */ 
@Data
@TableName("t_area")
public class Area extends AbstractSuperEntity<Area> {

	/**
 	* 区域ID
 	*/ 
	@TableId
	private Integer areaId;
	/**
 	* 区域名称
 	*/ 
	private String areaName;
	/**
 	* 省
 	*/ 
	private Integer provinceId;
	/**
 	* 市
 	*/ 
	private Integer cityId;
	/**
 	* 县
 	*/ 
	private Integer countyId;
	/**
 	* 经度
 	*/ 
	private Float mapX;
	/**
 	* 纬度
 	*/ 
	private Float mapY;
	/**
 	* 删除状态(是：Y 否：N)
 	*/ 
	private String isDelete;
	/**
 	* 创建用户ID
 	*/ 
	private Long createUserId;
	/**
 	* 创建用户名称
 	*/ 
	private String createUserName;
	/**
 	* 最后修改删除用户ID
 	*/ 
	private Long modifyUserId;
	/**
 	* 最后修改删除用户名称
 	*/ 
	private String modifyUserName;
	@Override
	protected Serializable pkVal() {
		return this.areaId;
	}
}
