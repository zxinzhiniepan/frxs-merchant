package com.frxs.merchant.core.dubbo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.frxs.trade.service.api.OrderQryAreaFacade;
import com.frxs.trade.service.api.dto.TradeMobResult;
import com.frxs.trade.service.api.dto.stat.StoreUserDto;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 * @author wushuo
 * @version $Id: TradeDubboProcess.java,v 0.1 2018年03月26日 19:55 $Exp
 */
@Component
public class TradeDubboProcess {

    @Reference(check = false, version = "1.0.0",timeout = 30000)
    private OrderQryAreaFacade orderQryAreaFacade;

    public TradeMobResult<StoreUserDto> getOrderStoreList(Map<Integer,List<Long>> map){
        TradeMobResult<StoreUserDto> tradeMobResult = orderQryAreaFacade.getStoreList(map);
        return tradeMobResult;
    }


}
