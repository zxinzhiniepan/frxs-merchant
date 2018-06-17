package com.frxs.merchant.core.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.frxs.framework.util.common.log4j.LogUtil;
import com.frxs.merchant.common.dal.entity.Vendor;
import com.frxs.merchant.common.dal.entity.VendorAccount;
import com.frxs.merchant.common.dal.enums.ErrorCodeDetailEnum;
import com.frxs.merchant.common.dal.enums.ErrorCodeScenarioEnum;
import com.frxs.merchant.common.dal.enums.StatusEnum;
import com.frxs.merchant.common.util.PwdUtils;
import com.frxs.merchant.core.generator.IdGenerator;
import com.frxs.merchant.core.helper.MerchantResultHelper;
import com.frxs.merchant.core.mapper.VendorAccountMapper;
import com.frxs.merchant.core.mapstruct.VendorAccountMapStruct;
import com.frxs.merchant.core.service.VendorAccountService;
import com.frxs.merchant.service.api.domain.request.VendorAccountRequest;
import com.frxs.merchant.service.api.dto.VendorAccountDto;
import com.frxs.merchant.service.api.dto.VendorDto;
import com.frxs.merchant.service.api.result.MerchantResult;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author wushuo
 * @version $Id: VendorAccountServiceImpl.java,v 0.1 2018年02月27日 15:24 $Exp
 */
@Service
public class VendorAccountServiceImpl implements VendorAccountService {

    @Autowired
    private VendorAccountMapper vendorAccountMapper;

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private MerchantResultHelper merchantResultHelper;

    /**
     * 根据手机号码查询账户信息
     * @param accountMoile
     * @return
     */
    @Override
    public MerchantResult<VendorAccountDto> findByMoile(String accountMoile) {
        VendorAccount vendorAccount = new VendorAccount();
        vendorAccount.setAccountMoile(accountMoile);
        EntityWrapper<VendorAccount> userWrapper = new EntityWrapper<>(vendorAccount);
        userWrapper.notIn("status",StatusEnum.DELETE.getValueDefined());
        List<VendorAccount> result= vendorAccountMapper.selectList(userWrapper);
        MerchantResult<VendorAccountDto> merchantResult = new MerchantResult<>();
        if(result == null || result.size()==0){
            return merchantResult;
        }
        VendorAccountDto vendorAccountDto = VendorAccountMapStruct.MAPPER.accountTOAccountDto(result.get(0));
        merchantResult.setData(vendorAccountDto);
        merchantResult.setSuccess(true);
        return merchantResult;

    }

    /**
     * 根据vendorId修改供应商账户状态
     * @param vendor
     * @return
     */
    @Override
    public MerchantResult updateStatus(Vendor vendor) {
        MerchantResult result = new MerchantResult();
            vendorAccountMapper.updateStatusByVendorId(vendor);
            result.setSuccess(true);
            return result;
    }

    /**
     * 保存供应商账户信息
     * @param vendor
     * @return
     */
    @Override
    public MerchantResult saveAccount(Vendor vendor,String username) {
        MerchantResult result = new MerchantResult();
            VendorAccount vendorAccount = new VendorAccount();
            vendorAccount.setAccountId(idGenerator.genVendorAccountId());
            vendorAccount.setAccountNo(username);
            vendorAccount.setVendorId(vendor.getVendorId());
            vendorAccount.setAccountMoile(vendor.getContactsTel());
            vendorAccount.setPwdSalt(PwdUtils.getUUID());
            vendorAccount.setPwd(PwdUtils.md5Hex(vendorAccount.getAccountMoile().substring(vendorAccount.getAccountMoile().length()-6)+vendorAccount.getPwdSalt()));
            vendorAccount.setStatus(StatusEnum.NORMAL.getValueDefined());
            vendorAccount.setCreateUserId(vendor.getCreateUserId());
            vendorAccount.setCreateUserName(vendor.getCreateUserName());
            vendorAccount.setTmCreate(vendor.getTmCreate());
            vendorAccountMapper.insert(vendorAccount);
            result.setSuccess(true);
            return result;
    }

    /**
     * 更新供应商账户信息
     * @param vendor
     * @return
     */
    @Override public MerchantResult updateAccount(Vendor vendor) {
        MerchantResult result = new MerchantResult();
            List<VendorAccount> accounts = vendorAccountMapper.selectList(
                new EntityWrapper<VendorAccount>().eq("vendorId", vendor.getVendorId()));
            if(accounts != null && accounts.size()>0){
                VendorAccount vendorAccount = accounts.get(0);
                vendorAccount.setAccountMoile(vendor.getContactsTel());
                vendorAccount.setStatus(StatusEnum.NORMAL.getValueDefined());
                vendorAccount.setModifyUserId(vendor.getModifyUserId());
                vendorAccount.setModifyUserName(vendor.getModifyUserName());
                vendorAccountMapper.updateById(vendorAccount);
                result.setSuccess(true);
                return result;
            }
            return result;
    }

    /**
     * 批量修改状态
     * @param vendorDto
     * @return
     */
    @Override public MerchantResult batchUpdateStatus(VendorDto vendorDto) {
        MerchantResult result = new MerchantResult();
            VendorAccountDto vendorAccountDto = new VendorAccountDto();
            vendorAccountDto.setModifyUserId(vendorDto.getModifyUserId());
            vendorAccountDto.setModifyUserName(vendorDto.getModifyUserName());
            vendorAccountDto.setStatus(vendorDto.getVendorStatus());
            vendorAccountDto.setVendorIds(vendorDto.getVendorIds());
            vendorAccountMapper.batchUpdateStatus(vendorDto);
            result.setSuccess(true);
            return result;
    }

    /**
     * 修改密码
     * @param molieNo
     * @param pwd
     * @return
     */
    @Override public MerchantResult<VendorAccountDto> modifyPwd(String molieNo, String pwd,String pwdSalt) {
        MerchantResult result = transactionTemplate.execute(new TransactionCallback<MerchantResult>(){
            MerchantResult<VendorAccountDto> result = new MerchantResult();
            @Override
            public MerchantResult doInTransaction(TransactionStatus transactionStatus) {
                try {
                    VendorAccount vendorAccount = new VendorAccount();
                    vendorAccount.setPwd(PwdUtils.md5Hex(pwd+pwdSalt));
                    vendorAccount.setPwdSalt(pwdSalt);
                    vendorAccount.setTmLastModifyPwd(new Date());

                    VendorAccount account = new VendorAccount();
                    account.setAccountMoile(molieNo);
                    EntityWrapper<VendorAccount> wrapper = new EntityWrapper<>(account);
                    vendorAccountMapper.update(vendorAccount,wrapper);
                    result.setSuccess(true);
                    VendorAccountDto dto = VendorAccountMapStruct.MAPPER.accountTOAccountDto(vendorAccount);
                    result.setData(dto);
                }catch (Exception e){
                    merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.VENDOR_ACCOUNT, ErrorCodeDetailEnum.VENDOR_ACCOUNT_PWD_ERROR);
                    LogUtil.error(e,"[vendorAccount-service:修改供应商账户密码信息]处理异常  errorCode={}", result.getErrorContext().fetchCurrentErrorCode());
                    // 事务回滚
                    transactionStatus.setRollbackOnly();
                }
                return result;
            }
        });
        return result;
    }

    /**
     * 条件查询
     * @param vendorAccountRequest
     * @return
     */
    @Override
    public MerchantResult<VendorAccountDto> getVendorAccount(
        VendorAccountRequest vendorAccountRequest) {
        MerchantResult<VendorAccountDto> result = new MerchantResult();
        VendorAccount vendorAccount = VendorAccountMapStruct.MAPPER.accountRequestToAccount(vendorAccountRequest);
        VendorAccount selectOne = vendorAccountMapper.selectOne(vendorAccount);

        if (selectOne == null) {
            return result;
        }
        VendorAccountDto vendorAccountDto = VendorAccountMapStruct.MAPPER.accountTOAccountDto(selectOne);
        result.setSuccess(true);
        result.setData(vendorAccountDto);
        return result;
    }

    /**
     * 重置密码
     * @param vendorAccountDto
     * @return MerchantResult<VendorAccountDto>
     */
    @Override
    public MerchantResult<VendorAccountDto> resetPwd(VendorAccountDto vendorAccountDto) {
        MerchantResult result = transactionTemplate.execute(new TransactionCallback<MerchantResult>(){
            MerchantResult result = new MerchantResult();
            @Override
            public MerchantResult doInTransaction(TransactionStatus transactionStatus) {
                try {
                    VendorAccount vendorAccountRequest = new VendorAccount();
                    vendorAccountRequest.setVendorId(vendorAccountDto.getVendorId());
                    VendorAccount vendorAccount = vendorAccountMapper.selectOne(vendorAccountRequest);
                    vendorAccount.setPwdSalt(PwdUtils.getUUID());
                    vendorAccount.setPwd(PwdUtils.md5Hex(vendorAccount.getAccountMoile().substring(vendorAccount.getAccountMoile().length()-6)+vendorAccount.getPwdSalt()));
                    vendorAccount.setModifyUserId(vendorAccountDto.getModifyUserId());
                    vendorAccount.setModifyUserName(vendorAccountDto.getModifyUserName());
                    vendorAccount.setTmLastModifyPwd(new Date());
                    vendorAccountMapper.updateById(vendorAccount);
                    result.setSuccess(true);
                    return result;
                }catch (Exception e){
                    merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.VENDOR_ACCOUNT, ErrorCodeDetailEnum.VENDOR_ACCOUNT_PWD_ERROR);
                    LogUtil.error(e,"[vendorAccount-service:供应商账户重置密码]处理异常  errorCode={}", result.getErrorContext().fetchCurrentErrorCode());
                    // 事务回滚
                    transactionStatus.setRollbackOnly();
                }
                return result;
            }
        });
        return result;
    }

    /**
     * 根据账号查询供应商账户信息
     * @param accountNo
     * @return
     */
    @Override
    public MerchantResult<VendorAccountDto> findByAccountNo(String accountNo) {
        VendorAccount vendorAccount = new VendorAccount();
        vendorAccount.setAccountNo(accountNo);
        EntityWrapper<VendorAccount> userWrapper = new EntityWrapper<>(vendorAccount);
        userWrapper.notIn("status",StatusEnum.DELETE.getValueDefined());
        List<VendorAccount> result= vendorAccountMapper.selectList(userWrapper);
        MerchantResult<VendorAccountDto> merchantResult = new MerchantResult<>();
        if(result == null || result.size()==0){
            return merchantResult;
        }
        VendorAccountDto vendorAccountDto = VendorAccountMapStruct.MAPPER.accountTOAccountDto(result.get(0));
        merchantResult.setData(vendorAccountDto);
        merchantResult.setSuccess(true);
        return merchantResult;

    }
}
