package com.frxs.merchant.common.dal.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.*;
import java.io.Serializable;
import com.frxs.framework.data.persistent.AbstractSuperEntity;
import lombok.Data;
/**
 * table name:  t_vendor_account
 * author name: sh
 * create time: 2018-01-25 19:50:51
 */ 
@Data
@TableName("t_vendor_account")
public class VendorAccount extends AbstractSuperEntity<VendorAccount> {

	/**
 	* 账户ID
 	*/ 
	@TableId(type = IdType.INPUT)
	private Long accountId;
	/**
 	* 账户号
 	*/ 
	private String accountNo;
	/**
	 * 供应商ID
	 */
	private Long vendorId;
	/**
 	* 账户名称
 	*/ 
	private String accountName;
	/**
 	* 绑定手机号码
 	*/ 
	private String accountMoile;
	/**
 	* 开放编号
 	*/ 
	private String openId;
	/**
 	* 开放类型
 	*/ 
	private Integer openType;
	/**
 	* 微信号
 	*/ 
	private String weChatNo;
	/**
 	* 微信昵称
 	*/ 
	private String nickName;
	/**
 	* 微信头像
 	*/ 
	private String headImage;
	/**
 	* 密码
 	*/ 
	private String pwd;
	/**
 	* MD5加密盐值
 	*/ 
	private String pwdSalt;
	/**
 	* 状态：（正常：normal 冻结：frozen 删除：delete）
 	*/ 
	private String status;
	/**
 	* 微信是否已登录
 	*/ 
	private String wechatLoginStatus;
	/**
 	* 最后登录时间
 	*/ 
	private Date tmLastLogin;
	/**
 	* 最后修改密码时间
 	*/ 
	private Date tmLastModifyPwd;
	/**
 	* 创建用户 ID
 	*/ 
	private Long createUserId;
	/**
 	* 创建用户名称
 	*/ 
	private String createUserName;
	/**
 	* 最后修改用户ID
 	*/ 
	private Long modifyUserId;
	/**
 	* 最后修改用户名称
 	*/ 
	private String modifyUserName;
	@Override
	protected Serializable pkVal() {
		return this.accountId;
	}
}
