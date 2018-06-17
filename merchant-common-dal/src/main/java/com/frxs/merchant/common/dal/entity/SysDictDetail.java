package com.frxs.merchant.common.dal.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.*;
import java.io.Serializable;
import com.frxs.framework.data.persistent.AbstractSuperEntity;
import lombok.Data;

/**
 * table name:  t_sys_dict_detail
 * author name: sh
 * create time: 2018-01-25 19:50:51
 */
@Data
@TableName("t_sys_dict_detail")
public class SysDictDetail extends AbstractSuperEntity<SysDictDetail> {

  /**
   * 主键
   */
  @TableId
  private Integer dictDetailId;
  /**
   * 明细编码
   */
  private String detailCode;
  /**
   * 字典编码
   */
  private String dictCode;
  /**
   * 字典值
   */
  private String dictValue;
  /**
   * 字典标签
   */
  private String dictLabel;
  /**
   * 排序
   */
  private Integer sort;
  /**
   * 备注
   */
  private String remark;
  /**
   * 修改用户ID
   */
  private Integer modifyUserId;
  /**
   * 修改用户名称
   */
  private String modifyUserName;

  /**
   * 区域ID
   */
  private Integer areaId;

  @Override
  protected Serializable pkVal() {
    return this.dictDetailId;
  }
}
