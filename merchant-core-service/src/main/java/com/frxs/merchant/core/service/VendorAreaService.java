package com.frxs.merchant.core.service;

import com.frxs.merchant.common.dal.entity.Vendor;
import com.frxs.merchant.service.api.result.MerchantResult;
import java.util.List;

/**
 * 供应商区域关联
 * @author wushuo
 * @version $Id: VendorAreaService.java,v 0.1 2018年02月28日 15:08 $Exp
 */
public interface VendorAreaService {

    /**
     * 保存供应商区域
     * @param vendor
     * @param areaIds
     * @return
     */
    MerchantResult saveVendorArea(Vendor vendor, List<Integer> areaIds);

    /**
     * 删除供应商区域
     * @param vendorId
     * @return
     */
    MerchantResult deleteVendroArea(Long vendorId);

    /**
     * 更新供应商区域信息
     * @param vendor
     * @param areaIds
     * @return
     */
    MerchantResult updateVendorArea(Vendor vendor, List<Integer> areaIds);
}
