/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.service.api.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * 商品图片
 * table name:  t_product_img
 * author name: sh
 * create time: 2018-01-25 19:50:51
 */
@Data
public class ProductImgDto extends AbstractSuperDto implements Serializable {

  private static final long serialVersionUID = -8904469768934833033L;
  /**
   * 商品图片id
   */
  private Long productImgId;
  /**
   * 商品id
   */
  private Long productId;
  /**
   * 800*800原图地址
   */
  private String originalImgUrl;
  /**
   * 60*60图片地址
   */
  private String imgUrl60;
  /**
   * 120*120图片地址
   */
  private String imgUrl120;
  /**
   * 200*200图片地址
   */
  private String imgUrl200;
  /**
   * 400*400图片地址
   */
  private String imgUrl400;
  /**
   * 图片类型:AD-广告图，PRIMARY-主图，DETAIL-详情图
   */
  private String imgType;
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
}
