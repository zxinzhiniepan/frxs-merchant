package com.frxs.merchant.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.frxs.framework.integration.dubbo.annotation.FrxsAutowired;
import com.frxs.merchant.core.service.WarehouseService;
import com.frxs.merchant.service.api.dto.WarehouseDto;
import com.frxs.merchant.service.api.facade.WarehouseFacade;
import com.frxs.merchant.service.api.result.MerchantResult;

/**
 * @author jiangboxuan
 * @version @version $Id: WarehouseFacadeImpl.java,v 0.1 2018年02月01日 下午 18:44 $Exp
 */
@Service(version="1.0.0")
public class WarehouseFacadeImpl implements WarehouseFacade {

    @FrxsAutowired
    WarehouseService warehouseService;

    @Override
    public MerchantResult listWarehouse(WarehouseDto warehouseDto) {

        return warehouseService.listWarehouse(warehouseDto);
    }
}
