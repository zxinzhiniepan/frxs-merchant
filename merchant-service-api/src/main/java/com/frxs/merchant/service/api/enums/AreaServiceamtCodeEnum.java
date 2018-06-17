/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.service.api.enums;

import com.frxs.framework.common.enums.BaseEnum;
import java.io.Serializable;

/**
 * 区域服务费用明细枚举
 *
 * @author sh
 * @version $Id: AreaServiceamtCodeEnum.java,v 0.1 2018年01月30日 下午 16:26 $Exp
 */
public enum AreaServiceamtCodeEnum implements BaseEnum<String> {

  WAREHOUSING_FEE("WAREHOUSING_FEE", "仓储费"),
  LOGISTICS_FEE("LOGISTICS_FEE", "物流费"),
  INFORMATION_PLATFORM_FEE("INFORMATION_PLATFORM_FEE", "信息平台费"),
  COMMODITY_PROMOTION_FEE("COMMODITY_PROMOTION_FEE", "商品推广费"),;

  private String value;

  private String desc;

  AreaServiceamtCodeEnum(String value, String desc) {
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
   * 根据value获取枚举
   *
   * @param value 值
   * @return 枚举
   */
  public static AreaServiceamtCodeEnum getAreaServiceamtCodeEnum(String value) {

    for (AreaServiceamtCodeEnum codeEnum : AreaServiceamtCodeEnum.values()) {
      if (codeEnum.getValueDefined().equals(value)) {
        return codeEnum;
      }
    }
    return null;
  }
}
