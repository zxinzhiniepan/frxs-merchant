package com.frxs.merchant.core.service;

import com.frxs.merchant.common.dal.entity.Vendor;
import com.frxs.merchant.common.dal.entity.VendorAccount;
import com.frxs.merchant.service.api.domain.request.VendorAccountRequest;
import com.frxs.merchant.service.api.dto.VendorAccountDto;
import com.frxs.merchant.service.api.dto.VendorDto;
import com.frxs.merchant.service.api.result.MerchantResult;
import java.util.List;

/**
 * @author wushuo
 * @version $Id: VendorAccountService.java,v 0.1 2018年02月27日 15:18 $Exp
 */
public interface VendorAccountService {

    /**
     * 根据手机号码查询供应商账户信息
     * @param accountMoile
     * @return
     */
    MerchantResult<VendorAccountDto> findByMoile(String accountMoile);

    /**
     * 更改状态
     * @param vendor
     * @return MerchantResult
     */
    MerchantResult updateStatus(Vendor vendor);

    /**
     * 保存供应商账户
     * @param vendor
     * @return
     */
    MerchantResult saveAccount(Vendor vendor,String username);


    /**
     * 更新供应商账户
     * @param vendor
     * @return
     */
    MerchantResult updateAccount(Vendor vendor);

    /**
     * 批量修改状态
     * @param vendorDto
     * @return
     */
    MerchantResult batchUpdateStatus(VendorDto vendorDto);

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

    /**
     * 根据账号查询供应商账户信息
     * @param accountNo
     * @return
     */
    MerchantResult<VendorAccountDto> findByAccountNo(String accountNo);

}
