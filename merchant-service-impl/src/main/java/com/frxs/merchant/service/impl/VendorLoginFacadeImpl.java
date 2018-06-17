package com.frxs.merchant.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.frxs.framework.integration.dubbo.annotation.FrxsAutowired;
import com.frxs.merchant.core.service.VendorLoginService;
import com.frxs.merchant.service.api.dto.VendorAccountDto;
import com.frxs.merchant.service.api.facade.VendorLoginFacade;
import com.frxs.merchant.service.api.result.MerchantResult;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wushuo
 * @version $Id: VendorLoginFacadeImpl.java,v 0.1 2018年02月27日 16:28 $Exp
 */

@Service(version = "1.0.0")
public class VendorLoginFacadeImpl implements VendorLoginFacade{

    @FrxsAutowired
    private VendorLoginService vendorLoginService;

    @Override public MerchantResult vendorLogin(String username, String pwd) {
        return vendorLoginService.vendorLogin(username,pwd);
    }

    @Override public MerchantResult vendorLogOut(String vendorKey) {
        return vendorLoginService.vendorLogOut(vendorKey);
    }

    @Override public MerchantResult<VendorAccountDto> getVendorbyKey(String vendorKey) {
        return vendorLoginService.getVendorbyKey(vendorKey);
    }
}
