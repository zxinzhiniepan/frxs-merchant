package com.frxs.merchant.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.frxs.framework.integration.dubbo.annotation.FrxsAutowired;
import com.frxs.merchant.core.service.AreaService;
import com.frxs.merchant.service.api.dto.AreaDto;
import com.frxs.merchant.service.api.facade.AreaFacade;
import com.frxs.merchant.service.api.result.MerchantResult;
import java.util.List;

/**
 * @author wushuo
 * @version $Id: AreaFacadeImpl.java,v 0.1 2018年02月09日 9:58 $Exp
 */

@Service(version = "1.0.0")
public class AreaFacadeImpl implements AreaFacade{
    @FrxsAutowired AreaService areaService;

    @Override public MerchantResult<List<AreaDto>> getList(String isDelete) {
        return areaService.getList(isDelete);
    }

    @Override public MerchantResult saveArea(AreaDto areaDto) {
        return areaService.saveArea(areaDto);
    }

    @Override public MerchantResult updateArea(AreaDto areaDto) {
        return areaService.updateArea(areaDto);
    }

    @Override public MerchantResult<AreaDto> getAreaById(Integer areaId) {
        return areaService.getAreaById(areaId);
    }

    @Override
    public MerchantResult<AreaDto> getAreaByAreaName(String areaName) {
        return areaService.getAreaByAreaName(areaName);
    }
}
