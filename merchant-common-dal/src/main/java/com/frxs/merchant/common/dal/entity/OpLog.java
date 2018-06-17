package com.frxs.merchant.common.dal.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.*;
import java.io.Serializable;
import com.frxs.framework.data.persistent.AbstractSuperEntity;
import lombok.Data;

/**
 * table name:  t_op_log
 * author name: sh
 * create time: 2018-01-25 19:50:51
 */
@Data
@TableName("t_op_log")
public class OpLog {

  /**
   * 主键ID
   */
  @TableId
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
  /**
   * 创建时间
   */
  private Date tmCreate;
}
