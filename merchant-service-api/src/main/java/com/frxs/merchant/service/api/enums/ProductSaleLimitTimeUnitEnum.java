/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.service.api.enums;

import com.frxs.framework.common.enums.BaseEnum;
import java.io.Serializable;

/**
 * 售后时限单位：DAY-天，HOUR-时
 *
 * @author sh
 * @version $Id: ProductSaleLimitTimeUnitEnum.java,v 0.1 2018年02月03日 上午 10:07 $Exp
 */
public enum ProductSaleLimitTimeUnitEnum implements BaseEnum<String> {
  DAY("DAY", "天"),
  HOUR("HOUR", "时");

  private String value;
  private String desc;

  ProductSaleLimitTimeUnitEnum(String value, String desc) {
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

  /**
   * 获取售后时限
   *
   * @param value 值
   * @return 枚举
   */
  public static ProductSaleLimitTimeUnitEnum getProductSaleLimitTimeUnitEnum(String value) {
    for (ProductSaleLimitTimeUnitEnum unit : ProductSaleLimitTimeUnitEnum.values()) {
      if (unit.getValue().equals(value)) {
        return unit;
      }
    }
    return null;
  }
}
