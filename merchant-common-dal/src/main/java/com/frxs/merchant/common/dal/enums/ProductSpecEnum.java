/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.common.dal.enums;

import com.frxs.framework.common.enums.BaseEnum;
import java.io.Serializable;

/**
 * 商品规格：SINGLE-单规格,MULTI-多规格
 *
 * @author sh
 * @version $Id: ProductSpecEnum.java,v 0.1 2018年02月03日 上午 9:36 $Exp
 */
public enum ProductSpecEnum implements BaseEnum<String> {
  SINGLE("SINGLE", "单规格"),
  MULTI("MULTI", "多规格"),;

  private String value;

  private String desc;

  ProductSpecEnum(String value, String desc) {
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
