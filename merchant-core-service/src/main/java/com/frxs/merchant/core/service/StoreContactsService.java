package com.frxs.merchant.core.service;

import com.frxs.merchant.service.api.dto.StoreContactsDto;
import com.frxs.merchant.service.api.result.MerchantResult;

/**
 * @author Cukor.fu
 * @version $Id: StoreContactsService.java,v 0.1 2018年03月16日 下午 16:03 $Exp
 */
public interface StoreContactsService {
    MerchantResult saveStoreContacts(StoreContactsDto storeContactsDto);

    MerchantResult getContactsByStoreId(Long storeId);

    MerchantResult getContactsLikeNameOrTel(String value);
}
