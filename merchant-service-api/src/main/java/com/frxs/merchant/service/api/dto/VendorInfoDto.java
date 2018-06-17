package com.frxs.merchant.service.api.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * @author wushuo
 * @version $Id: VendorInfoDto.java,v 0.1 2018年02月27日 15:56 $Exp
 */
@Data
public class VendorInfoDto implements Serializable{

    private static final long serialVersionUID = -6353899116305809438L;
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
     * 供应商Logo
     */
    private String vendorLogo;
    /**
     * 供应商状态
     */
    private String vendorStatus;
}
