package com.frxs.merchant.common.dal.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.*;
import java.io.Serializable;
import com.frxs.framework.data.persistent.AbstractSuperEntity;
import lombok.Data;
/**
 * table name:  t_user_store
 * author name: sh
 * create time: 2018-01-25 19:50:51
 */ 
@Data
@TableName("t_user_store")
public class UserStore extends AbstractSuperEntity<UserStore> {

	/**
 	* 主键
 	*/ 
	@TableId
	private Long id;
	/**
 	* 用户编号
 	*/ 
	private Long userId;
	/**
 	* 门店主键编号
 	*/ 
	private Long storeId;
	/**
 	* 用户名称(昵称，头像，手机)
 	*/ 
	private String userName;
	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}
