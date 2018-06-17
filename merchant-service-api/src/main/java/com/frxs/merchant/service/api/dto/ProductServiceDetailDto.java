/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.service.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 商品服务费用明细
 * table name:  t_product_service_detail
 * author name: sh
 * create time: 2018-01-25 19:50:51
 */
@Data
public class ProductServiceDetailDto extends AbstractSuperDto implements Serializable {


  private static final long serialVersionUID = -9123194674730548903L;
  /**
   * 供应商商品服务费id
   */
  private Long serviceDetailId;
  /**
   * 商品id
   */
  private Long productId;
  /**
   * 平台服务费编码
   */
  private String serviceAmtCode;
  /**
   * 平台服务费值
   */
  private BigDecimal serviceAmt;
  /**
   * 平台服务费百分比
   */
  private BigDecimal serviceRate;
  /**
   * 描述
   */
  private String serviceDesc;

}
