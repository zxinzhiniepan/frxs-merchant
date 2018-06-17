/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.common.dal.entity;

import com.frxs.framework.common.domain.Money;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.*;
import java.io.Serializable;
import com.frxs.framework.data.persistent.AbstractSuperEntity;
import lombok.Data;

/**
 * table name:  t_product_vendor_data_attr
 * author name: sh
 * create time: 2018-01-25 19:50:51
 */
@Data
@TableName("t_product_vendor_data_attr")
public class ProductVendorDataAttr extends AbstractSuperEntity<ProductVendorDataAttr> {

  /**
   * 供应商商品属性id
   */
  @TableId
  private Long vendorProductAttrId;
  /**
   * 供应商商品资料id
   */
  private Long vendorProductDataId;
  /**
   * 商品规格
   */
  private String vendorProductAttr;
  /**
   * 商品价格
   */
  @TableField("vendorProductAmt")
  private Money vendorProductAmt;
  /**
   * 商品数量
   */
  private BigDecimal vendorProductQty;
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
    return this.vendorProductAttrId;
  }
}
