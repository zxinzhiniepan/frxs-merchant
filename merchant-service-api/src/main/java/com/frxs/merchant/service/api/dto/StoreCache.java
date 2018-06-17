package com.frxs.merchant.service.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author jiangboxuan
 * @version @version $Id: StoreCache.java,v 0.1 2018年02月27日 下午 16:39 $Exp
 */
@Data
public class StoreCache implements Serializable {
    /**
     * 门店主键编号
     */
    private Long storeId;
    /**
     * 门店编码
     */
    private String storeCode;

    /**
     * 门店签名
     */
    private String storeSign;
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
     * 门店开发人员
     */
    private String storeDeveloper;
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
     * 营业面积
     */
    private String shopArea;
    /**
     * 营业执照
     */
    private String busiLicenseFullName;
    /**
     * 食品流通许可证
     */
    private String foodCirculationLicense;
    /**
     * 营业执照图片地址
     */
    private String busiLicenseFullNameImgSrc;
    /**
     * 食品流通许可证图片地址
     */
    private String foodCirculationLicenseImgSrc;
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
    /**
     * 上线时间YYYY-MM-dd
     */
    private String tmOnLine;
    /**
     * 创建用户名称
     */
    private String createUserName;
    /**
     * 创建用户ID
     */
    private Long createUserId;
    /**
     * 最后修改用户ID
     */
    private Long modifyUserId;
    /**
     * 最后修改用户名称
     */
    private String modifyUserName;

    /**
     * 是否排名
     */
    private String isRank;

    /**
     * 线路ID
     */
    private Integer lineId;

    /**
     * 线路名称
     */
    private String lineName;

    /**
     * 线路顺序
     */
    private Integer lineSort;
}
