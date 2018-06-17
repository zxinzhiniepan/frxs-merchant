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
 * table name:  t_product
 * author name: sh
 * create time: 2018-01-25 19:50:51
 */
@Data
@TableName("t_product")
public class Product extends AbstractSuperEntity<Product> {

  /**
   * 商品id
   */
  @TableId
  private Long productId;
  /**
   * 商品信息id
   */
  private Long productInfoId;
  /**
   * 商品sku编码
   */
  private String sku;
  /**
   * 商品sku名称
   */
  private String productName;
  /**
   * 商品sku标题
   */
  private String productTitle;
  /**
   * 商品销售类型:HOT-爆款,NORMAL-常规
   */
  private String saleType;
  /**
   * 供应商id
   */
  private Long vendorId;
  /**
   * 区域id
   */
  private Long areaId;
  /**
   * 库存
   */
  private BigDecimal stock;
  /**
   * 剩余库存
   */
  private BigDecimal surplusStock;
  /**
   * 限量
   */
  private BigDecimal limitQty;
  /**
   * 已售量
   */
  private BigDecimal saleQty;
  /**
   * 商品sku状态:UP-上架，DOWN-下架，DELETED-已删除
   */
  private String skuStatus;
  /**
   * 供货价
   */
  @TableField("supplyAmt")
  private Money supplyAmt;
  /**
   * 市场价
   */
  @TableField("marketAmt")
  private Money marketAmt;
  /**
   * 销售价
   */
  @TableField("saleAmt")
  private Money saleAmt;
  /**
   * 每份平台服务费
   */
  @TableField("perServiceAmt")
  private Money perServiceAmt;
  /**
   * 每份提成
   */
  @TableField("perCommission")
  private Money perCommission;
  /**
   * 生产地
   */
  private String yieldly;
  /**
   * 商品规格类型:SINGLE-单规格(单规格即从该商品系列中移除,可独立售卖),MULTI-多规格
   */
  private String specType;
  /**
   * 包装数
   */
  private BigDecimal packageQty;
  /**
   * 售后时限
   */
  private BigDecimal saleLimitTime;
  /**
   * 售后时限单位：DAY-天，HOUR-时
   */
  private String saleLimitTimeUnit;
  /**
   * 条形码列表,以","分隔
   */
  private String barCodes;
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
    return this.productId;
  }
}
