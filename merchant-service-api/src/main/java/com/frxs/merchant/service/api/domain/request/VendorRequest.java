package com.frxs.merchant.service.api.domain.request;

import java.io.Serializable;
import lombok.Data;

/**
 * @author wushuo
 * @version $Id: VendorRequest.java,v 0.1 2018年03月13日 14:41 $Exp
 */
@Data
public class VendorRequest implements Serializable{
    private static final long serialVersionUID = 2129445211089033969L;

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
     * 电话
     */
    private String contactsTel;
    /**
     * 企业用户号
     */
    private String unionPayCID;
    /**
     * 银联商户号
     */
    private String unionPayMID;

}
