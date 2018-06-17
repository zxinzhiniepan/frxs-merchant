package com.frxs.merchant.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.plugins.Page;
import com.frxs.framework.integration.dubbo.annotation.FrxsAutowired;
import com.frxs.merchant.core.service.DistributionLineService;
import com.frxs.merchant.service.api.dto.DistributionLineDto;
import com.frxs.merchant.service.api.dto.StoreDto;
import com.frxs.merchant.service.api.facade.DistributionLineFacade;
import com.frxs.merchant.service.api.result.MerchantResult;

import java.util.List;
import java.util.Map;

/**
 * @author jiangboxuan
 * @version @version $Id: DistributionLineFacadeImpl.java,v 0.1 2018年01月31日 上午 8:49 $Exp
 */
@Service(version="1.0.0")
public class DistributionLineFacadeImpl implements DistributionLineFacade {

    @FrxsAutowired
    DistributionLineService distributionLineService;

    @Override
    public MerchantResult<Page<DistributionLineDto>> listDistributionLine(DistributionLineDto distributionLineDto, Integer pageNo, Integer pageSize) {
        return distributionLineService.listDistributionLine(distributionLineDto,pageNo,pageSize);
    }

    @Override
    public MerchantResult listAll(DistributionLineDto distributionLineDto) {
        return distributionLineService.listAll(distributionLineDto);
    }

    @Override
    public MerchantResult<DistributionLineDto> getDistributionLine(Integer id) {
        return distributionLineService.getDistributionLine(id);
    }

    @Override
    public MerchantResult modifyState(DistributionLineDto distributionLineDto) {
        return distributionLineService.modifyState(distributionLineDto);
    }

    @Override
    public MerchantResult saveDistributionLine(DistributionLineDto distributionLineDto) {
        return distributionLineService.saveDistributionLine(distributionLineDto);
    }

    @Override
    public MerchantResult<Map<DistributionLineDto, List<StoreDto>>> listDistributionLineAndStore(Integer warehouseId) {
        return distributionLineService.listDistributionLineAndStore(warehouseId);
    }
}
