package com.frxs.merchant.common.dal.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.*;
import java.io.Serializable;
import com.frxs.framework.data.persistent.AbstractSuperEntity;
import lombok.Data;
/**
 * table name:  t_product_templet
 * author name: sh
 * create time: 2018-01-25 19:50:51
 */ 
@Data
@TableName("t_product_templet")
public class ProductTemplet extends AbstractSuperEntity<ProductTemplet> {

	/**
 	* 商品模版id
 	*/ 
	@TableId
	private Long templetId;
	/**
 	* 模版创建来源:HEAD-总部,AREA-区域
 	*/ 
	private String source;
	/**
 	* 创建模版的区域id
 	*/ 
	private Long createTempletAreaId;
	/**
 	* 开放范围:ALL-所有区域,PART-部分区域,PRIVATE-提交审核区域私有,CLOSED-关闭(暂不开放)
 	*/ 
	private String openRange;
	/**
 	* 模版来源商品信息id
 	*/ 
	private Long productInfoId;
	/**
 	* 模版名称
 	*/ 
	private String templetName;
	/**
 	* 模版标题
 	*/ 
	private String templetTitle;
	/**
 	* 生产地
 	*/ 
	private String yieldly;
	/**
 	* 品牌id
 	*/ 
	private Long brandId;
	/**
 	* 品牌名称
 	*/ 
	private String brandName;
	/**
 	* 模版状态：PENDING-待审核,PASS-审核通过,REJECT-驳回，DELETED-已删除
 	*/ 
	private String status;
	/**
 	* 分类id
 	*/ 
	private Long cateId;
	/**
 	* 审核人id
 	*/ 
	private Long auditUserId;
	/**
 	* 审核人名
 	*/ 
	private String auditUserName;
	/**
 	* 审核时间
 	*/ 
	private Date tmAudit;
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
		return this.templetId;
	}
}
