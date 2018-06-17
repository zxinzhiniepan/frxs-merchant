package com.frxs.merchant.core.service.impl;

import com.frxs.merchant.common.dal.entity.Warehouse;
import com.frxs.merchant.core.mapper.WarehouseMapper;
import com.frxs.merchant.core.mapstruct.WarehouseMapStruct;
import com.frxs.merchant.core.service.WarehouseService;
import com.frxs.merchant.service.api.dto.WarehouseDto;
import com.frxs.merchant.service.api.result.MerchantResult;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jiangboxuan
 * @version @version $Id: WarehouseServiceImpl.java,v 0.1 2018年02月01日 下午 18:47 $Exp
 */
@Service
public class WarehouseServiceImpl implements WarehouseService{

    @Autowired
    WarehouseMapper warehouseMapper;

    @Override
    public MerchantResult<List<WarehouseDto>> listWarehouse(WarehouseDto warehouseDto) {
        MerchantResult<List<WarehouseDto>> result = new MerchantResult<>();
        Warehouse warehouse = WarehouseMapStruct.MAPPER.warehouseDtoToWarehouse(warehouseDto);

        //OTCAssembleUtils.assemble(warehouseDto,warehouse);
        List<Warehouse> warehouses = warehouseMapper.listWarehouse(warehouse);
        if(warehouses == null || warehouses.size() == 0){
            return result;
        }
        List<WarehouseDto> warehouseDtos = WarehouseMapStruct.MAPPER.warehousesToWarehouseDtos(warehouses);
/*        for (Warehouse item : warehouses){
            WarehouseDto dtoItem = new WarehouseDto();
            OTCAssembleUtils.assemble(item,dtoItem);
            warehouseDtos.add(dtoItem);
        }*/
        result.setSuccess(true);
        result.setData(warehouseDtos);
        return result;
    }
}
