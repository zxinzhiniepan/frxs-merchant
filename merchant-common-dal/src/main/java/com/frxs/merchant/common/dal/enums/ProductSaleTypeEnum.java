/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.common.dal.enums;

import com.frxs.framework.common.enums.BaseEnum;
import java.io.Serializable;

/**
 * 商品销售类型:HOT-爆款,NORMAL-常规
 *
 * @author sh
 * @version $Id: ProductSaleTypeEnum.java,v 0.1 2018年02月05日 下午 14:05 $Exp
 */
public enum ProductSaleTypeEnum implements BaseEnum<String> {
  HOT("HOT", "爆款"),
  NORMAL("NORMAL", "常规款"),;

  private String value;

  private String desc;

  ProductSaleTypeEnum(String value, String desc) {
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
