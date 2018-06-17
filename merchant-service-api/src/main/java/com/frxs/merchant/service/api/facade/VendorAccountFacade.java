package com.frxs.merchant.service.api.facade;

import com.frxs.merchant.service.api.domain.request.VendorAccountRequest;
import com.frxs.merchant.service.api.dto.VendorAccountDto;
import com.frxs.merchant.service.api.result.MerchantResult;

/**
 * 供应商账户dubbo接口
 * @author wushuo
 * @version $Id: VendorAccountFacade.java,v 0.1 2018年03月02日 10:17 $Exp
 */
public interface VendorAccountFacade {

    /**
     * 根据手机号查询供应商账户信息
     * @param molieNo
     * @return MerchantResult<VendorAccountDto>
     */
    MerchantResult<VendorAccountDto> getAccountByMoileNo(String molieNo);

    /**
     * 修改密码
     * @param molieNo
     * @param pwd
     * @return MerchantResult<VendorAccountDto>
     */
    MerchantResult<VendorAccountDto> modifyPwd(String molieNo,String pwd,String pwdSalt);

    /**
     *  条件查询
     * @param vendorAccountRequest
     * @return MerchantResult<VendorAccountDto>
     */
    MerchantResult<VendorAccountDto> getVendorAccount(VendorAccountRequest vendorAccountRequest);

    /**
     * 重置密码
     * @param vendorAccountDto
     * @return MerchantResult<VendorAccountDto>
     */
    MerchantResult<VendorAccountDto> resetPwd(VendorAccountDto vendorAccountDto);
}
