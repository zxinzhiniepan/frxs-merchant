package com.frxs.merchant.core.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.frxs.framework.util.common.DateUtil;
import com.frxs.framework.util.common.log4j.LogUtil;
import com.frxs.merchant.common.dal.entity.Store;
import com.frxs.merchant.common.dal.enums.ErrorCodeDetailEnum;
import com.frxs.merchant.common.dal.enums.ErrorCodeScenarioEnum;
import com.frxs.merchant.common.dal.enums.StatusEnum;
import com.frxs.merchant.core.dubbo.TradeDubboProcess;
import com.frxs.merchant.core.helper.MerchantResultHelper;
import com.frxs.merchant.core.mapper.StoreMapper;
import com.frxs.merchant.core.service.StoreTaskSerivce;
import com.frxs.merchant.service.api.result.MerchantResult;
import com.frxs.trade.service.api.dto.TradeMobResult;
import com.frxs.trade.service.api.dto.stat.StoreUserDto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author wushuo
 * @version $Id: StoreTaskSerivceImpl.java,v 0.1 2018年03月26日 15:17 $Exp
 */
@Service
public class StoreTaskSerivceImpl implements StoreTaskSerivce {

    @Autowired
    private StoreMapper storeMapper;

    @Autowired
    private TransactionTemplate newTransactionTemplate;

    @Autowired
    private MerchantResultHelper merchantResultHelper;

    @Autowired
    private TradeDubboProcess tradeDubboProcess;


    @Override
    public MerchantResult saveStoreTmOnLine() {
        MerchantResult result = newTransactionTemplate.execute(new TransactionCallback<MerchantResult>() {
            MerchantResult result = new MerchantResult();

            @Override public MerchantResult doInTransaction(TransactionStatus transactionStatus) {
                try {
                    List<Store> stores = storeMapper.selectList(new EntityWrapper<Store>().
                        isNull("tmOnLine").
                        notIn("storeStatus", StatusEnum.DELETE.getValueDefined()));
                    if(stores == null || stores.size() == 0){
                        result.setData("不存在未上线门店！");
                        return result;
                    }
                    Map<Integer,List<Long>> map = new HashMap<>();
                    for (Store store : stores) {
                        List<Long> list = map.get(store.getAreaId());
                        if(list == null || list.size() == 0){
                            list = new ArrayList<>();
                        }
                        list.add(store.getStoreId());
                        map.put(store.getAreaId(),list);
                    }
                    TradeMobResult<StoreUserDto> tradeMobResult =
                        tradeDubboProcess.getOrderStoreList(map);
                    if(!tradeMobResult.isSuccess() || tradeMobResult.getData() == null || tradeMobResult.getData().size() == 0){
                        result.setData("不存在订单信息！");
                        return result;
                    }
                    List<StoreUserDto> storeUserDtoList = tradeMobResult.getData();
                    for (StoreUserDto storeUserDto : storeUserDtoList) {
                        Store store = new Store();
                        store.setStoreId(storeUserDto.getStoreId());
                        store.setTmOnLine(DateUtil.getWebDateString(DateUtil.getBeforeDate()));
                        storeMapper.updateById(store);
                    }
                    result.setSuccess(true);
                    return result;
                } catch (Exception e) {
                    merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.STORE,
                        ErrorCodeDetailEnum.STORE_UPDATE_ERROR);
                    LogUtil.error(e,"[store-service:保存门店上线时间]处理异常  errorCode={}",result.getErrorContext().fetchCurrentErrorCode());
                    // 事务回滚
                    transactionStatus.setRollbackOnly();
                }
                return result;
            }
        });
        return result;
    }

}
