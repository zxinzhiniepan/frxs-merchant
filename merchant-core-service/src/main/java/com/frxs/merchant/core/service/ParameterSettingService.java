package com.frxs.merchant.core.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.frxs.merchant.service.api.dto.ParameterSettingDto;
import com.frxs.merchant.service.api.result.MerchantResult;

import java.util.List;
import java.util.Map;

/**
 * @author jiangboxuan
 * @version @version $Id: SysDictDetailService.java,v 0.1 2018年01月31日 上午 10:07 $Exp
 */
public interface ParameterSettingService {

    /**
     * 系统参数列表展示
     *
     * @param parameterSettingDto 系统参数dto
     * @return MerchantResult<List<ParameterSettingDto>>
     */
    MerchantResult<Page<ParameterSettingDto>> listParameterSetting(ParameterSettingDto parameterSettingDto,Integer pageNo,Integer pageSize);

    /**
     * 区域系统参数列表展示
     *
     * @param parameterSettingDto 系统参数dto
     * @return MerchantResult<List<ParameterSettingDto>>
     */
    MerchantResult<Page<ParameterSettingDto>> listParameterSettingArea(ParameterSettingDto parameterSettingDto,Integer pageNo,Integer pageSize);

    /**
     * 系统参数详情
     *
     * @param id
     * @return MerchantResult<ParameterSettingDto>
     */
    MerchantResult<ParameterSettingDto> getParameterSetting(Integer id);

    /**
     * 系统参数状态修改
     *
     * @param parameterSettingDto
     * @return MerchantResult<ParameterSettingDto>
     */
    MerchantResult modifyState(ParameterSettingDto parameterSettingDto);

    /**
     * 依据系统参数取得
     * @param areaId （区域id,区域id不传取总部的);
     * @param paraCode (参数Code,必传);
     * @return MerchantResult<ParameterSettingDto>
     */
    MerchantResult<ParameterSettingDto> getParameterByParaCode(Integer areaId,String paraCode);

    /**
     * 系统参数详情
     *
     * @param areaId
     * @return MerchantResult<List<ParameterSettingDto>>
     */
    MerchantResult<Map<String,String>> getParameterSettingByAreaId(Integer areaId);
}
