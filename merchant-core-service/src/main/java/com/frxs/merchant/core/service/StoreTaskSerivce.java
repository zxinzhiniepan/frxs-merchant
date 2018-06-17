package com.frxs.merchant.core.service;

import com.frxs.merchant.service.api.result.MerchantResult;

/**
 * @author wushuo
 * @version $Id: StoreTaskSerivce.java,v 0.1 2018年03月26日 15:16 $Exp
 */
public interface StoreTaskSerivce {

    /**
     * 门店上线时间任务
     * @return
     */
    MerchantResult saveStoreTmOnLine();
}
