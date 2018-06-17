/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.service.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 商品信息
 * table name:  t_product_info
 * author name: sh
 * create time: 2018-01-25 19:50:51
 */
@Data
public class ProductInfoDto implements Serializable {


  private static final long serialVersionUID = -4961073004488059785L;
  /**
   * 商品信息id
   */
  private Long productInfoId;
  /**
   * 区域id
   */
  private Long areaId;
  /**
   * 商品模版id
   */
  private Long templetId;
  /**
   * 是否已创建为模版:TRUE-已成为基础商品,FALSE-非基础商品
   */
  private String isCreateTemplet;
  /**
   * 商品总库存(sku库存总和)
   */
  private BigDecimal totalStock;
  /**
   * 商品名称
   */
  private String productName;
  /**
   * 商品标题
   */
  private String productTitle;
  /**
   * 品牌id
   */
  private Long brandId;
  /**
   * 品牌名称
   */
  private String brandName;
}
