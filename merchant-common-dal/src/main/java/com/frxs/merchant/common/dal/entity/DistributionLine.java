package com.frxs.merchant.common.dal.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.*;
import java.io.Serializable;
import com.frxs.framework.data.persistent.AbstractSuperEntity;
import lombok.Data;
/**
 * table name:  t_distribution_line
 * author name: sh
 * create time: 2018-01-25 19:50:51
 */ 
@Data
@TableName("t_distribution_line")
public class DistributionLine extends AbstractSuperEntity<DistributionLine> {

	/**
 	* 路线ID
 	*/ 
	@TableId
	private Integer id;
	/**
 	* 路线名称
 	*/ 
	private String lineName;
	/**
 	* 区域ID
 	*/ 
	private Integer areaId;
	/**
 	* 区域名称
 	*/ 
	private String areaName;
	/**
 	* 仓库ID
 	*/ 
	private Integer warehouseId;
	/**
 	* 仓库名称
 	*/ 
	private String warehouseName;
	/**
 	* 配送员ID
 	*/ 
	private Integer distributionClerkId;
	/**
 	* 状态(正常：normal 冻结：frozen 删除：delete)
 	*/ 
	private String status;
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
 	* 最后修改删除用户名
 	*/ 
	private String modifyUserName;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}
