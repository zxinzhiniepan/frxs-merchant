package com.frxs.merchant.service.api.dto;

import java.util.List;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Cukor.fu
 * @version $Id: VendorDto.java,v 0.1 2018年01月25日 下午 16:35 $Exp
 */
@Data
public class VendorDto implements Serializable {

    private static final long serialVersionUID = -5631765672235941646L;
    /**
     * 供应商ID
     */
    private Long vendorId;
    /**
     * 供应商编码
     */
    private String vendorCode;
    /**
     * 供应商名称
     */
    private String vendorName;
    /**
     * 供应商简称
     */
    private String vendorShortName;
    /**
     * 省
     */
    private Integer provinceId;
    /**
     * 市
     */
    private Integer cityId;
    /**
     * 区
     */
    private Integer countyId;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 联络人
     */
    private String contacts;
    /**
     * 电话
     */
    private String contactsTel;
    /**
     * 供应商状态(审核：auditing 正常：normal 冻结：frozen 删除：delete)
     */
    private String vendorStatus;
    /**
     * 供应商Logo
     */
    private String vendorLogo;
    /**
     * 法定代表人
     */
    private String legalPeople;
    /**
     * 营业面积
     */
    private String vendorArea;
    /**
     * 营业执照
     */
    private String busiLicenseFullName;
    /**
     * 食品流通许可证
     */
    private String foodCirculationLicense;
    /**
     * 银行户名
     */
    private String bankAccountName;
    /**
     * 银行名称
     */
    private String bankName;
    /**
     * 银行帐号
     */
    private String bankAccountNo;
    /**
     * 营业执照图片
     */
    private String busiLicenseFullNameImgSrc;
    /**
     * 食品流通许可证图片
     */
    private String foodCirculationLicenseImgSrc;
    /**
     * 创建区域ID
     */
    private Integer createAreaId;
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
     * 创建用户ID
     */
    private Long createUserId;
    /**
     * 创建用户名称
     */
    private String createUserName;
    /**
     * 最后修改用户ID
     */
    private Long modifyUserId;
    /**
     * 最后修改用户名称
     */
    private String modifyUserName;
    /**
     * 商品数量
     */
    private Integer productCount;
    /**
     * 供应商类型名称
     */
    private String vendorTypeName;
    /**
     * 区域
     */
    private String areaName;
    /**
     * 账号
     */
    private String userName;

    /**
     * 供应商Ids
     */
    private List<Long> vendorIds;

}
