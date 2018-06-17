/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.service.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author jiangboxuan
 * @version @version $Id: DistributionLineDto.java,v 0.1 2018年01月29日 下午 16:43 $Exp
 */
@Data
public class DistributionLineDto implements Serializable{
    private static final long serialVersionUID = 1L;

    /**
     * 路线ID
     */
    private Integer id;
    /**
     * 路线名称
     */
    private String lineName;
    /**
     * 区域ID
     */
    private Integer areaId;
    /**
     * 区域名称
     */
    private String areaName;
    /**
     * 仓库ID
     */
    private Integer warehouseId;
    /**
     * 仓库名称
     */
    private String warehouseName;
    /**
     * 配送员ID
     */
    private Integer distributionClerkId;
    /**
     * 状态(正常：normal 冻结：frozen 删除：delete)
     */
    private String status;
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
     * 最后修改删除用户名
     */
    private String modifyUserName;
    /**
     * 创建时间
     */
    private Date tmCreate;
    /**
     * 最新修改删除时间
     */
    private Date tmSmp;

    private String ids;

    private List<Integer> listWarehouse;
}
