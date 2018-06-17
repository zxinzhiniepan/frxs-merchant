package com.frxs.merchant.common.dal.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.*;
import java.io.Serializable;
import com.frxs.framework.data.persistent.AbstractSuperEntity;
import lombok.Data;
/**
 * table name:  t_org_area
 * author name: sh
 * create time: 2018-01-25 19:50:51
 */ 
@Data
@TableName("t_org_area")
public class OrgArea extends AbstractSuperEntity<OrgArea> {

	/**
 	* 区域编号
 	*/ 
	@TableId
	private Integer orgAreaId;
	/**
 	* 区域名称
 	*/ 
	private String orgAreaName;
	/**
 	* 全称
 	*/ 
	private String orgAreaFullName;
	/**
 	* 层级
 	*/ 
	private Integer level;
	/**
 	* 上级编号
 	*/ 
	private Integer parentId;
	/**
 	* 排序
 	*/ 
	private Integer SortId;
	/**
 	* 最后更新用户ID
 	*/ 
	private Long modifyUserId;
	/**
 	* 最后更新用户名称
 	*/ 
	private String modifyUserName;
	/**
 	* 创建用户ID
 	*/ 
	private Long createUserId;
	/**
 	* 创建用户名称
 	*/ 
	private String createUserName;
	@Override
	protected Serializable pkVal() {
		return this.orgAreaId;
	}
}
