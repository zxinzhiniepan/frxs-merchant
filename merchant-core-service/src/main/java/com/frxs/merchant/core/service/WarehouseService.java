package com.frxs.merchant.core.service;

import com.frxs.merchant.service.api.dto.WarehouseDto;
import com.frxs.merchant.service.api.result.MerchantResult;

import java.util.List;

/**
 * @author jiangboxuan
 * @version @version $Id: WarehouseService.java,v 0.1 2018年02月01日 下午 18:46 $Exp
 */
public interface WarehouseService {

    /**
     * 仓库列表展示
     *
     * @param warehouseDto 仓库dto
     * @return MerchantResult<List<WarehouseDto>>
     */
    MerchantResult<List<WarehouseDto>> listWarehouse(WarehouseDto warehouseDto);
}
