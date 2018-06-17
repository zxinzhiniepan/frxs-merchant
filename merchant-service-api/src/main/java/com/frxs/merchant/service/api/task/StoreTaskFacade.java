package com.frxs.merchant.service.api.task;

import com.frxs.merchant.service.api.result.MerchantResult;

/**
 * @author wushuo
 * @version $Id: StoreTaskFacade.java,v 0.1 2018年03月26日 14:55 $Exp
 */
public interface StoreTaskFacade {
    /**
     * 门店上线时间任务
     * @return
     */
    MerchantResult saveStoreTmOnLine();
}
