package com.frxs.merchant.service.api.domain.request;

import java.io.Serializable;
import lombok.Data;

/**
 * @author wushuo
 * @version $Id: VendorPageRequest.java,v 0.1 2018年03月13日 16:42 $Exp
 */
@Data
public class VendorPageRequest implements Serializable{
    private static final long serialVersionUID = 1789270116941592945L;

    /**
     * 当前页
     */
    private Integer page;
    /**
     * 条数
     */
    private Integer rows;
    /**
     *  供应商编码
     */
    private String vendorCode;
    /**
     * 供应商名称
     */
    private String vendorName;
    /**
     * 供应商分类
     */
    private Integer vendorTypeId;
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
     * 供应商区域
     */
    private Integer areaId;
    /**
     * 供应商状态
     */
    private String vendorStatus;

}
