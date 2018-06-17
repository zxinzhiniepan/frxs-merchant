/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.service.api.enums;

import com.frxs.framework.common.enums.BaseEnum;
import java.io.Serializable;

/**
 * 商品sku状态:UP-上架，DOWN-下架，DELETED-已删除
 *
 * @author sh
 * @version $Id: ProductSkuStatusEnum.java,v 0.1 2018年02月03日 上午 9:38 $Exp
 */
public enum ProductSkuStatusEnum implements BaseEnum<String> {

  UP("UP", "上架"),

  DOWN("DOWN", "下架"),

  DELETED("DELETED", "已删除"),;

  private String value;
  private String desc;

  ProductSkuStatusEnum(String value, String desc) {
    this.value = value;
    this.desc = desc;
  }

  @Override
  public Serializable getValue() {
    return this.value;
  }

  @Override
  public String getValueDefined() {
    return this.value;
  }

  @Override
  public String getDesc() {
    return this.desc;
  }
}
