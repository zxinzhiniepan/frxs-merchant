package com.frxs.merchant.service.api.facade;

import com.frxs.merchant.service.api.dto.ParameterSettingDto;
import com.frxs.merchant.service.api.result.MerchantResult;

import java.util.Map;

/**
 * @author jiangboxuan
 * @version @version $Id: sysDictDetailFacade.java,v 0.1 2018年01月29日 下午 20:37 $Exp
 */
public interface ParameterSettingFacade {

/*    *//**
     * 系统参数列表展示
     *
     * @param parameterSettingDto 系统参数dto
     * @return MerchantResult<Page<ParameterSettingDto>>
     */
    MerchantResult listParameterSetting(ParameterSettingDto parameterSettingDto,Integer pageNo,Integer pageSize);

    /*    *//**
     * 区域系统参数列表展示
     *
     * @param parameterSettingDto 系统参数dto
     * @return MerchantResult<Page<ParameterSettingDto>>
     */
    MerchantResult listParameterSettingArea(ParameterSettingDto parameterSettingDto,Integer pageNo,Integer pageSize);

    /**
     * 系统参数详情
     *
     * @param id
     * @return MerchantResult<SysDictDetailDto>
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
