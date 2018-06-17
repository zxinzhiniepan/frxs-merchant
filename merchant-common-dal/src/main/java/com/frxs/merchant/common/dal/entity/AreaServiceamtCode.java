package com.frxs.merchant.common.dal.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.*;
import java.io.Serializable;
import com.frxs.framework.data.persistent.AbstractSuperEntity;
import lombok.Data;
/**
 * table name:  t_area_serviceamt_code
 * author name: sh
 * create time: 2018-01-25 19:50:51
 */ 
@Data
@TableName("t_area_serviceamt_code")
public class AreaServiceamtCode extends AbstractSuperEntity<AreaServiceamtCode> {

	/**
 	* 服务编码id
 	*/ 
	@TableId
	private Long serviceAmtId;
	/**
 	* 编码
 	*/ 
	private String code;
	/**
 	* 描述
 	*/ 
	private String serviceAmtDesc;
	/**
 	* 费率
 	*/ 
	private BigDecimal rate;

	/**
 	* 区域id
 	*/ 
	private Long areaId;
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
		return this.serviceAmtId;
	}
}
