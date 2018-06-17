package com.frxs.merchant.core.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.frxs.merchant.service.api.dto.DistributionLineDto;
import com.frxs.merchant.service.api.dto.StoreDto;
import com.frxs.merchant.service.api.result.MerchantResult;

import java.util.List;
import java.util.Map;

public interface DistributionLineService {
    /**
     * 配送路线列表展示
     *
     * @param distributionLineDto 配送路线dto
     * @return MerchantResult<List<DistributionLineDto>>
     */
    MerchantResult<Page<DistributionLineDto>> listDistributionLine(DistributionLineDto distributionLineDto,Integer pageNo,Integer pageSize);

    /**
     * 全部配送路线信息
     *
     * @param distributionLineDto 配送路线dto
     * @return MerchantResult<List<DistributionLineDto>>
     */
    MerchantResult<List<DistributionLineDto>> listAll(DistributionLineDto distributionLineDto);

    /**
     * 配送路线详情
     *
     * @param id
     * @return MerchantResult<DistributionLineDto>
     */
    MerchantResult<DistributionLineDto> getDistributionLine(Integer id);

    /**
     * 配送路线删除
     *
     * @param distributionLineDto
     * @return MerchantResult<Integer>
     */
    MerchantResult modifyState(DistributionLineDto distributionLineDto);

    /**
     * 配送路线保存
     *
     * @param distributionLineDto
     * @return MerchantResult<Integer>
     */
    MerchantResult saveDistributionLine(DistributionLineDto distributionLineDto);

    /**
     * 通过仓库id查询线路以及线路下门店
     *
     * @param warehouseId 仓库id
     * @return MerchantResult<Map<DistributionLineDto,List<StoreDto>>>
     */
    MerchantResult<Map<DistributionLineDto,List<StoreDto>>> listDistributionLineAndStore(Integer warehouseId);
}