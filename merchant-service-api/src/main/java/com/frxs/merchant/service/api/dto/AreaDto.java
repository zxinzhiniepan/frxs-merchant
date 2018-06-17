package com.frxs.merchant.service.api.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * 区域DTO
 *
 * @author wushuo
 * @version $Id: AreaDto.java,v 0.1 2018年01月29日 15:35 $Exp
 */

@Data
public class AreaDto implements Serializable{

  private static final long serialVersionUID = 9071035200397684882L;
  /**
     * 区域ID
     */
    private Integer areaId;
    /**
     * 区域名称
     */
    private String areaName;
    /**
     * 省
     */
    private Integer provinceId;
    /**
     * 市
     */
    private Integer cityId;
    /**
     * 县
     */
    private Integer countyId;
    /**
     * 经度
     */
    private Float mapX;
    /**
     * 纬度
     */
    private Float mapY;
    /**
     * 删除状态(是：Y 否：N)
     */
    private String isDelete;
    /**
     * 创建用户ID
     */
    private Long createUserId;
    /**
     * 创建用户名称
     */
    private String createUserName;
    /**
     * 最后修改删除用户ID
     */
    private Long modifyUserId;
    /**
     * 最后修改删除用户名称
     */
    private String modifyUserName;
}
