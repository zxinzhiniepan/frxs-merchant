package com.frxs.merchant.core.service;

import com.frxs.merchant.service.api.dto.VendorAccountDto;
import com.frxs.merchant.service.api.result.MerchantResult;
import java.util.Map;

/**
 * @author wushuo
 * @version $Id: VendorLoginService.java,v 0.1 2018年02月27日 15:16 $Exp
 */
public interface VendorLoginService {
    /**
     * 供应商web端登录
     * @param username
     * @param pwd
     * @return
     */
    MerchantResult vendorLogin(String username, String pwd);

    /**
     * 供应商web端登出
     * @param vendorKey
     * @return
     */
    MerchantResult vendorLogOut(String vendorKey);

    /**
     * 获取已登录信息
     * @param vendorKey
     * @return
     */
    MerchantResult<VendorAccountDto> getVendorbyKey(String vendorKey);
}
