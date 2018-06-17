/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.service.api.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 后台操作日志
 * table name:  t_op_log
 * author name: sh
 * create time: 2018-01-25 19:50:51
 */
@Data
public class OpLogDto implements Serializable {

  private static final long serialVersionUID = 6416587029734493173L;
  /**
   * 主键ID
   */
  private Long id;
  /**
   * 仓库ID
   */
  private Integer areaId;
  /**
   * 模块编号
   */
  private String menuId;
  /**
   * 模块名称
   */
  private String menuName;
  /**
   * 动作
   */
  private String action;
  /**
   * 备注
   */
  private String remark;
  /**
   * 操作用户ID
   */
  private Integer operatorId;
  /**
   * 操作用户名称
   */
  private String operatorName;
}
