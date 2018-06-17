package com.frxs.merchant.core.service;

import com.frxs.merchant.common.dal.entity.Store;
import com.frxs.merchant.service.api.result.MerchantResult;
import java.util.List;

/**
 * 门店用户
 * @author wushuo
 * @version $Id: StoreUserService.java,v 0.1 2018年02月28日 17:19 $Exp
 */
public interface StoreUserService {

    /**
     * 添加门店用户信息
     * @param store
     * @return
     */
    MerchantResult saveStoreUser(Store store,String username);

    /**
     * 修改门店用户状态
     * @param storeId
     * @param status
     * @return
     */
    MerchantResult updateStoreUserStatus(Long storeId,String status);

    /**
     * 批量修改状态
     * @param storeIds
     * @param status
     * @return
     */
    MerchantResult batchUpdateStatus(List<Long> storeIds,String status);
}
