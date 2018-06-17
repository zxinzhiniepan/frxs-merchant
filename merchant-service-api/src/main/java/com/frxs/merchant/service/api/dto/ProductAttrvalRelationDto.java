/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.service.api.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * 商品属性关联
 * table name:  t_product_attrval_relation
 * author name: sh
 * create time: 2018-01-25 19:50:51
 */
@Data
public class ProductAttrvalRelationDto extends AbstractSuperDto implements Serializable {

  private static final long serialVersionUID = -4411496723475210090L;
  /**
   * 商品属性关联表id
   */
  private Long productAttrValId;
  /**
   * 商品id
   */
  private Long productId;
  /**
   * 商品信息id:冗余为方面根据商品信息查所有属性
   */
  private Long pruductInfoId;
  /**
   * 属性id
   */
  private Long attrId;
  /**
   * 属性名称
   */
  private String attrName;
  /**
   * 属性值id
   */
  private Long attrValId;
  /**
   * 属性值
   */
  private String attrVal;
  /**
   * 层级:从0开始
   */
  private Integer attrLevel;
}
