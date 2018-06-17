package com.frxs.merchant.core.service.impl;

import com.frxs.merchant.common.dal.entity.VendorAccount;
import com.frxs.merchant.common.dal.enums.StatusEnum;
import com.frxs.merchant.common.util.PwdUtils;
import com.frxs.merchant.core.cache.CacheTool;
import com.frxs.merchant.core.mapstruct.VendorAccountMapStruct;
import com.frxs.merchant.core.service.VendorAccountService;
import com.frxs.merchant.core.service.VendorLoginService;
import com.frxs.merchant.service.api.dto.VendorAccountDto;
import com.frxs.merchant.service.api.result.MerchantResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author wushuo
 * @version $Id: VendorLoginServiceImpl.java,v 0.1 2018年02月27日 15:17 $Exp
 */
@Service
public class VendorLoginServiceImpl implements VendorLoginService {

    @Autowired
    private VendorAccountService vendorAccountService;

    @Autowired
    private CacheTool cacheTool;

    /**
     * 登录
     * @param username
     * @param pwd
     * @return
     */
    @Override
    public MerchantResult vendorLogin(String username, String pwd) {
        MerchantResult result = new MerchantResult();
        MerchantResult<VendorAccountDto> merchantResult = vendorAccountService.findByMoile(username);
        if(!merchantResult.isSuccess()){
            merchantResult =  vendorAccountService.findByAccountNo(username);
        }
        if (merchantResult.isSuccess()){
            VendorAccountDto vendorAccountDto = merchantResult.getData();
            StatusEnum statusEnum = StatusEnum.getByValue(vendorAccountDto.getStatus());
            if (!Objects.equals(statusEnum, StatusEnum.NORMAL)){
                result.setSuccess(false);
                result.setData("该供应商目前是"+statusEnum.getDesc()+"状态，请与管理员联系");
                return result;
            }
            //判断密码是否正确
            if (PwdUtils.verify(pwd,vendorAccountDto.getPwd(),vendorAccountDto.getPwdSalt())){
                VendorAccount vendorAccount = VendorAccountMapStruct.MAPPER.accountDtoToAccount(vendorAccountDto);
                String key = PwdUtils.getUUID();
                cacheTool.saveVendorAccount(key,vendorAccount);
                result.setSuccess(true);
                result.setData(key);
            }else {
                result.setSuccess(false);
            }
        }else {
            result.setData("账号或手机号码不存在");
        }
        return result;
    }

    /**
     * 登出
     * @param vendorKey
     * @return
     */
    @Override
    public MerchantResult vendorLogOut(String vendorKey) {
        MerchantResult merchantResult = new MerchantResult();
        cacheTool.deleteVendorAccount(vendorKey);
        merchantResult.setSuccess(true);
        return merchantResult;
    }

    /**
     * 获取已登录信息
     * @param vendorKey
     * @return
     */
    @Override
    public MerchantResult<VendorAccountDto> getVendorbyKey(String vendorKey) {
        MerchantResult<VendorAccountDto> merchantResult = new MerchantResult<>();
        VendorAccount account = cacheTool.findVendorAccountByKey(vendorKey);
        if (account == null){
            merchantResult.setSuccess(false);
        }else{
            VendorAccountDto vendorAccountDto = VendorAccountMapStruct.MAPPER.accountTOAccountDto(account);
            merchantResult.setSuccess(true);
            merchantResult.setData(vendorAccountDto);
        }
        return merchantResult;
    }
}
