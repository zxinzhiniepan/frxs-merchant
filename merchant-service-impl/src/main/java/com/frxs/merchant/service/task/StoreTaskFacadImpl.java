package com.frxs.merchant.service.task;

import com.alibaba.dubbo.config.annotation.Service;
import com.frxs.framework.integration.dubbo.annotation.FrxsAutowired;
import com.frxs.merchant.core.service.StoreTaskSerivce;
import com.frxs.merchant.service.api.result.MerchantResult;
import com.frxs.merchant.service.api.task.StoreTaskFacade;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wushuo
 * @version $Id: StoreTaskFacadImpl.java,v 0.1 2018年03月26日 15:14 $Exp
 */
@Service(version="1.0.0")
public class StoreTaskFacadImpl implements StoreTaskFacade{

    @FrxsAutowired
    private StoreTaskSerivce storeTaskSerivce;

    @Override
    public MerchantResult saveStoreTmOnLine() {
        return storeTaskSerivce.saveStoreTmOnLine();
    }
}
