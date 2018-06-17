package com.frxs.merchant.service.api.facade;

import com.frxs.merchant.service.api.dto.VendorAccountDto;
import com.frxs.merchant.service.api.result.MerchantResult;
import java.util.Map;

/**
 * 供应商登录登出接口
 * @author wushuo
 * @version $Id: VendorLoginFacade.java,v 0.1 2018年02月27日 14:54 $Exp
 */
public interface VendorLoginFacade {

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
