package com.frxs.merchant.service.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 门店DTO
 *
 * @author wushuo
 * @version $Id: StoreDto.java,v 0.1 2018年01月29日 16:23 $Exp
 */
@Data
public class StoreInfoDto implements Serializable{

    /**
     * 门店主键编号
     */
    private Long storeId;
    /**
     * 门店编码
     */
    private String storeCode;
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
     * 门店状态(,?正常：NOEMAL冻结：FORZEN 删除：DELETE)
     */
    private String storeStatus;
    /**
     * 仓库名称
     */
    private String warehouseName;
    /**
     * 店铺名称
     */
    private String storeName;
    /**
     * 店铺LOGO
     */
    private String logoUrl;
    /**
     * 店铺简介
     */
    private String storeDescription;

    /**
     * 短语
     */
    private String storeSign;

    /**
     * 详细地址
     */
    private String detailAddress;

    /**
     * 联系人
     */
    private String contacts;

    /**
     * 联系电话
     */
    private String contactsTel;

    /**
     * 是否排名
     */
    private String isRank;
}
