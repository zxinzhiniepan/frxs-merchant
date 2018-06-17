package com.frxs.merchant.core.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.frxs.framework.util.common.log4j.LogUtil;
import com.frxs.fund.service.api.domain.dto.store.StoreBalanceDto;
import com.frxs.merchant.common.dal.entity.Store;
import com.frxs.merchant.common.dal.entity.StoreContacts;
import com.frxs.merchant.common.dal.entity.StoreLine;
import com.frxs.merchant.common.dal.enums.ErrorCodeDetailEnum;
import com.frxs.merchant.common.dal.enums.ErrorCodeScenarioEnum;
import com.frxs.merchant.common.util.OTCAssembleUtils;
import com.frxs.merchant.core.helper.MerchantResultHelper;
import com.frxs.merchant.core.mapper.StoreContactsMapper;
import com.frxs.merchant.core.service.StoreContactsService;
import com.frxs.merchant.service.api.dto.StoreContactsDto;
import com.frxs.merchant.service.api.dto.StoreDto;
import com.frxs.merchant.service.api.result.MerchantResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Cukor.fu
 * @version $Id: StoreContactsServiceImpl.java,v 0.1 2018年03月16日 下午 16:05 $Exp
 */
@Service
public class StoreContactsServiceImpl implements StoreContactsService {

    @Autowired
    private StoreContactsMapper storeContactsMapper;
    @Autowired
    private TransactionTemplate newTransactionTemplate;
    @Autowired
    private MerchantResultHelper<MerchantResult> merchantResultHelper;

    @Override
    public MerchantResult saveStoreContacts(StoreContactsDto storeContactsDto) {
        MerchantResult result = newTransactionTemplate.execute(new TransactionCallback<MerchantResult>() {
            MerchantResult<StoreDto> result = new MerchantResult<>();

            @Override
            public MerchantResult doInTransaction(TransactionStatus transactionStatus) {
                try {
                    StoreContacts storeContacts = new StoreContacts();
                    OTCAssembleUtils.assemble(storeContactsDto, storeContacts);

                    Wrapper<StoreContacts> wrapper = new EntityWrapper<>(new StoreContacts(storeContacts.getStoreId(),storeContacts.getContactsName()));
                    List<StoreContacts> contacts = storeContactsMapper.selectList(wrapper);
                    if (contacts != null&&contacts.size()>0) {
                        StoreContacts sc = new StoreContacts();
                        sc.setId(contacts.get(0).getId());
                        sc.setOrderNumber(storeContacts.getOrderNumber()==null?1:storeContacts.getOrderNumber()+1);
                        sc.setContactsTel(storeContacts.getContactsTel());
                        storeContactsMapper.updateById(sc);
                    } else {
                        storeContacts.setTmCreate(new Date());
                        storeContactsMapper.insert(storeContacts);
                    }

                    result.setSuccess(true);
                    return result;
                } catch (Exception e) {
                    LogUtil.error("[store-contacts-service:保存门店联系人信息]处理异常", e);
                    merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.STORE, ErrorCodeDetailEnum.STORE_CONTACTS_SAVE_ERROR);
                    // 事务回滚
                    transactionStatus.setRollbackOnly();
                }
                return result;
            }
        });
        return result;
    }

    @Override
    public MerchantResult getContactsByStoreId(Long storeId) {
        MerchantResult result = new MerchantResult();
        List<StoreContacts> storeContactsList = storeContactsMapper.getContactsByStoreId(storeId);
        if (storeContactsList.size() > 0) {
            List<StoreContactsDto> storeContactsDtos = new ArrayList<>();
            storeContactsList.forEach(item -> {
                StoreContactsDto storeContacts = new StoreContactsDto();
                OTCAssembleUtils.assemble(item, storeContacts);
                storeContactsDtos.add(storeContacts);
            });
            result.setData(storeContactsDtos);
        }
        result.setSuccess(true);
        return result;
    }

    @Override
    public MerchantResult getContactsLikeNameOrTel(String value) {
        MerchantResult result = new MerchantResult();
        List<StoreContacts> storeContactsList = storeContactsMapper.getContactsLikeNameOrTel(value);
        List<StoreContactsDto> storeContactsDtos = new ArrayList<>();
        storeContactsList.forEach(item -> {
            StoreContactsDto storeContacts = new StoreContactsDto();
            OTCAssembleUtils.assemble(item, storeContacts);
            storeContactsDtos.add(storeContacts);
        });
        result.setData(storeContactsDtos);
        result.setSuccess(true);
        return result;
    }
}
