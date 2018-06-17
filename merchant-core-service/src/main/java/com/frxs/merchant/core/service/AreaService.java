package com.frxs.merchant.core.service;

import com.frxs.merchant.service.api.dto.AreaDto;
import com.frxs.merchant.service.api.result.MerchantResult;
import java.util.List;

/**
 * 区域内部service
 * @author wushuo
 * @version $Id: AreaService.java,v 0.1 2018年02月05日 17:26 $Exp
 */
public interface AreaService {
    /**
     * 区域信息
     * @param isDelete
     * @return  MerchantResult<List<AreaDto>>
     */
    MerchantResult<List<AreaDto>> getList(String isDelete);

    /**
     * 保存区域信息
     * @param areaDto
     * @return MerchantResult
     */
    MerchantResult saveArea(AreaDto areaDto);

    /**
     * 修改区域信息
     * @param areaDto
     * @return MerchantResult
     */
    MerchantResult updateArea(AreaDto areaDto);

    /**
     * 根据主键ID查询区域
     * @param areaId
     * @return  MerchantResult<AreaDto>
     */
    MerchantResult<AreaDto> getAreaById(Integer areaId);

    /**
     * 根据名称查询区域
     * @param areaName
     * @return  MerchantResult<AreaDto>
     */
    MerchantResult<AreaDto> getAreaByAreaName(String areaName);
}
