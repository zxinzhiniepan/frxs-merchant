package com.frxs.merchant.common.dal.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.*;
import java.io.Serializable;
import com.frxs.framework.data.persistent.AbstractSuperEntity;
import lombok.Data;
/**
 * table name:  t_store_line
 * author name: sh
 * create time: 2018-01-25 19:50:51
 */ 
@Data
@TableName("t_store_line")
public class StoreLine extends AbstractSuperEntity<StoreLine> {

	/**
 	* 主键id
 	*/ 
	@TableId
	private Long id;
	/**
 	* 门店ID
 	*/ 
	private Long storeId;
	/**
 	* 线路ID
 	*/ 
	private Integer lineId;
	/**
 	* 线路顺序
 	*/ 
	private Integer lineSort;
	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}
