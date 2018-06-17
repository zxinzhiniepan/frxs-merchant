package com.frxs.merchant.service.api.facade;

import com.frxs.merchant.service.api.dto.WarehouseDto;
import com.frxs.merchant.service.api.result.MerchantResult;

/**
 * @author jiangboxuan
 * @version @version $Id: WarehouseFacade.java,v 0.1 2018年02月01日 下午 18:39 $Exp
 */
public interface WarehouseFacade {

    /**
     * 仓库列表展示
     *
     * @param warehouseDto 仓库dto
     * @return MerchantResult<List<WarehouseDto>>
     */
    MerchantResult listWarehouse(WarehouseDto warehouseDto);

}
