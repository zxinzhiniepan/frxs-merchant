package com.frxs.merchant.core.mapstruct;

import com.baomidou.mybatisplus.plugins.Page;
import com.frxs.merchant.common.dal.entity.DistributionLine;
import com.frxs.merchant.common.dal.entity.Warehouse;
import com.frxs.merchant.service.api.dto.DistributionLineDto;
import com.frxs.merchant.service.api.dto.WarehouseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author jiangboxuan
 * @version @version $Id: DistributionLineMapStruct.java,v 0.1 2018年01月29日 下午 18:37 $Exp
 */
@Mapper
public interface WarehouseMapStruct {
    /**
     * MAPPER
     */
    WarehouseMapStruct MAPPER = Mappers.getMapper(WarehouseMapStruct.class);

    /**
     * warehouse 转 warehouseDto
     *
     * @param warehouse 领域模型
     * @return warehouseDto
     */
    WarehouseDto warehouseToWarehouseDto(Warehouse warehouse);

    /**
     *  WarehouseDto 转 Warehouse
     *
     * @param warehouseDto WarehouseDto
     * @return Warehouse 领域模型
     */
    Warehouse warehouseDtoToWarehouse(WarehouseDto warehouseDto);

    /**
     *  warehouses 转 warehouseDtos
     *
     * @param warehouses 领域模型List
     * @return List<WarehouseDto>
     */
    List<WarehouseDto> warehousesToWarehouseDtos(List<Warehouse> warehouses);

    /**
     *  warehouseDtos 转 warehouses
     *
     * @param warehouseDtos WarehouseDto List
     * @return List<Warehouse>
     */
    List<Warehouse> warehouseDtosToWarehouses(List<WarehouseDto> warehouseDtos);

    /**
     *  warehouses 转 warehouseDtoPages
     *
     * @param warehouses 领域模型Page
     * @return Page<WarehouseDto>
     */
    Page<WarehouseDto> warehousesPageWarehouseDtos(Page<Warehouse> warehouses);

}
