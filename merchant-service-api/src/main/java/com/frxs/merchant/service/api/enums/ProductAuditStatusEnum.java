/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.service.api.enums;

import java.io.Serializable;

/**
 * @author fygu
 * @version $Id: ProductAuditStatusEnum.java,v 0.1 2018年02月01日 18:02 $Exp
 */
public enum ProductAuditStatusEnum {

  /**
   *  草稿
   */
  DRAFT("DRAFT", "草稿"),
  /**
   *  草稿
   */
  PENDING("PENDING", "待审核"),
  /**
   *  草稿
   */
  PASS("PASS", "审核通过"),
  /**
   *  草稿
   */
  REJECT("REJECT", "驳回");

  /**
   *  枚举值
   */
  private String value;
  /**
   *  枚举描述
   */
  private String desc;

  ProductAuditStatusEnum(final String value, final String desc) {
    this.value = value;
    this.desc = desc;
  }

  public Serializable getValue() {
    return this.value;
  }

  public String getValueDefined() {
    return this.value;
  }

  public String getDesc(){
    return this.desc;
  }

  /**
   * 通过枚举<code>value</code>获得枚举
   *
   * @param value value
   * @return AgeEnum
   */
  public static ProductAuditStatusEnum getByValue(String value) {
    for (ProductAuditStatusEnum auditStatusEnum : values()) {
      if (auditStatusEnum.getValue().equals(value)) {
        return auditStatusEnum;
      }
    }
    return null;
  }
}
