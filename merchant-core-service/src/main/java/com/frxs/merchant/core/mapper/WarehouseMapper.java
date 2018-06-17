package com.frxs.merchant.core.mapper;

import com.frxs.framework.data.persistent.SuperMapper;
import com.frxs.merchant.common.dal.entity.Warehouse;

import java.util.List;

public interface WarehouseMapper extends SuperMapper<Warehouse>{

    /**
     * 仓库列表展示
     *
     * @param warehouse 仓库
     * @return com.frxs.merchant.common.dal.entity.Warehouse
     */
    List<Warehouse> listWarehouse(Warehouse warehouse);
}