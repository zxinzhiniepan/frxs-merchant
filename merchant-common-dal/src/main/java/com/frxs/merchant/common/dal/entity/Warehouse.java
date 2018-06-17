package com.frxs.merchant.common.dal.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.*;
import java.io.Serializable;
import com.frxs.framework.data.persistent.AbstractSuperEntity;
import lombok.Data;
/**
 * table name:  t_warehouse
 * author name: sh
 * create time: 2018-01-25 19:50:51
 */ 
@Data
@TableName("t_warehouse")
public class Warehouse extends AbstractSuperEntity<Warehouse> {

	/**
 	* 仓库ID
 	*/ 
	@TableId
	private Integer warehouseId;
	/**
 	* 仓库名称
 	*/ 
	private String warehouseName;
	/**
 	* 区域ID
 	*/ 
	private Integer areaId;
	/**
 	* 区域名称
 	*/ 
	private String areaName;

	/**
	 * 详细地址
	 */
	private String address;
	@Override
	protected Serializable pkVal() {
		return this.warehouseId;
	}
}
