package com.frxs.merchant.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.frxs.framework.integration.dubbo.annotation.FrxsAutowired;
import com.frxs.merchant.core.service.StoreContactsService;
import com.frxs.merchant.service.api.dto.StoreContactsDto;
import com.frxs.merchant.service.api.facade.StoreContactsFacade;
import com.frxs.merchant.service.api.result.MerchantResult;

/**
 * @author Cukor.fu
 * @version $Id: StoreContactsFacadeImpl.java,v 0.1 2018年03月16日 下午 16:19 $Exp
 */
@Service(version = "1.0.0")
public class StoreContactsFacadeImpl implements StoreContactsFacade{
    @FrxsAutowired
    private StoreContactsService storeContactsService;

    @Override
    public MerchantResult saveStoreContacts(StoreContactsDto storeContactsDto) {
        return storeContactsService.saveStoreContacts(storeContactsDto);
    }

    @Override
    public MerchantResult getContactsByStoreId(Long storeId) {
        return storeContactsService.getContactsByStoreId(storeId);
    }

    @Override
    public MerchantResult getContactsLikeNameOrTel(String value) {
        return storeContactsService.getContactsLikeNameOrTel(value);
    }
}
