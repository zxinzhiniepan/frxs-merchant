package com.frxs.merchant.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.frxs.framework.integration.dubbo.annotation.FrxsAutowired;
import com.frxs.merchant.core.service.VendorAccountService;
import com.frxs.merchant.service.api.domain.request.VendorAccountRequest;
import com.frxs.merchant.service.api.dto.VendorAccountDto;
import com.frxs.merchant.service.api.facade.VendorAccountFacade;
import com.frxs.merchant.service.api.result.MerchantResult;

/**
 * @author wushuo
 * @version $Id: VendorAccountFacadeImpl.java,v 0.1 2018年03月02日 10:19 $Exp
 */
@Service(version = "1.0.0")
public class VendorAccountFacadeImpl implements VendorAccountFacade {
    @FrxsAutowired
    private VendorAccountService vendorAccountService;

    @Override public MerchantResult<VendorAccountDto> getAccountByMoileNo(String molieNo) {
        return vendorAccountService.findByMoile(molieNo);
    }

    @Override public MerchantResult<VendorAccountDto> modifyPwd(String molieNo, String pwd,String pwdSalt) {
        return vendorAccountService.modifyPwd(molieNo,pwd,pwdSalt);
    }

    @Override public MerchantResult<VendorAccountDto> getVendorAccount(
        VendorAccountRequest vendorAccountRequest) {
        return vendorAccountService.getVendorAccount(vendorAccountRequest);
    }

    @Override public MerchantResult<VendorAccountDto> resetPwd(VendorAccountDto vendorAccountDto) {
        return vendorAccountService.resetPwd(vendorAccountDto);
    }
}
