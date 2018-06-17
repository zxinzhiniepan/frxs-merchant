package com.frxs.merchant.service.api.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * @author jiangboxuan
 * @version @version $Id: SysDictDetailDto.java,v 0.1 2018年01月29日 下午 20:00 $Exp
 */
@Data
public class SysDictDetailDto implements Serializable {

  private static final long serialVersionUID = -8539144517018690127L;
  /**
   * 主键
   */
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
}
