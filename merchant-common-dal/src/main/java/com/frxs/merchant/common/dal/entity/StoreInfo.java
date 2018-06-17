package com.frxs.merchant.common.dal.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author jiangboxuan
 * @version @version $Id: StoreInfo.java,v 0.1 2018年02月23日 下午 19:08 $Exp
 */
@Data
public class StoreInfo {
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
     * 地区编号
     */
    private Integer regionId;
    /**
     * 仓库ID
     */
    private Integer warehouseId;
    /**
     * 仓库名称
     */
    private String warehouseName;
    /**
     * 详细地址
     */
    private String detailAddress;
    /**
     * 经度
     */
    private Float mapX;
    /**
     * 纬度
     */
    private Float mapY;
    /**
     * 联系人
     */
    private String contacts;
    /**
     * 上级门店
     */
    private Long parentStoreId;
    /**
     * 门店状态(,?正常：normal冻结：frozen 删除：delete)
     */
    private String storeStatus;
    /**
     * 联系电话
     */
    private String contactsTel;
    /**
     * 门店微信群名称
     */
    private String wechatGroupName;
    /**
     * 银行户名
     */
    private String bankAccountName;
    /**
     * 银行名称
     */
    private String bankName;
    /**
     * 银行账号
     */
    private String bankAccountNo;
    /**
     * 银行行号
     */
    private String bankNo;
    /**
     * 银联商户号
     */
    private String unionPayMID;
    /**
     * 银联企业号
     */
    private String unionPayCID;
    /**
     * 提现方式
     */
    private String withdrawalsType;
}
