package com.frxs.merchant.service.api.facade;

import com.frxs.merchant.service.api.dto.AreaDto;
import com.frxs.merchant.service.api.result.MerchantResult;
import java.util.List;

/**
 * 区域服务接口
 *
 * @author wushuo
 * @version $Id: AreaFacade.java,v 0.1 2018年01月29日 15:34 $Exp
 */
public interface AreaFacade {

    /**
     * 区域信息
     * @return  getAll 区域列表
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
