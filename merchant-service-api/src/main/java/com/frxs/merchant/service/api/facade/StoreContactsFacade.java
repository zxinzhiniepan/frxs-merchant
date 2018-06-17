package com.frxs.merchant.service.api.facade;

import com.frxs.merchant.service.api.dto.StoreContactsDto;
import com.frxs.merchant.service.api.result.MerchantResult;

/**
 * @author Cukor.fu
 * @version $Id: StoreContactsFacade.java,v 0.1 2018年03月16日 下午 16:17 $Exp
 */
public interface StoreContactsFacade {
    MerchantResult saveStoreContacts(StoreContactsDto storeContactsDto);

    MerchantResult getContactsByStoreId(Long storeId);

    MerchantResult getContactsLikeNameOrTel(String value);
}
