/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.service.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 区域服务费用百分比
 * table name:  t_area_serviceamt_code
 * author name: sh
 * create time: 2018-01-25 19:50:51
 */
@Data
public class AreaServiceamtCodeDto implements Serializable {

  /**
   * 服务编码id
   */
  private Long serviceAmtId;
  /**
   * 编码
   */
  private String code;
  /**
   * 描述
   */
  private String serviceAmtDesc;
  /**
   * 费率
   */
  private BigDecimal rate;

  /**
   * 区域id
   */
  private Long areaId;
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
