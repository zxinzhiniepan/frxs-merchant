package com.frxs.merchant.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.plugins.Page;
import com.frxs.framework.integration.dubbo.annotation.FrxsAutowired;
import com.frxs.merchant.core.service.ParameterSettingService;
import com.frxs.merchant.service.api.dto.ParameterSettingDto;
import com.frxs.merchant.service.api.facade.ParameterSettingFacade;
import com.frxs.merchant.service.api.result.MerchantResult;

import java.util.Map;

/**
 * @author jiangboxuan
 * @version @version $Id: SysDictDetailFacadeImpl.java,v 0.1 2018年01月31日 上午 10:09 $Exp
 */
@Service(version="1.0.0")
public class ParameterSettingFacadeImpl implements ParameterSettingFacade {

    @FrxsAutowired
    ParameterSettingService parameterSettingService;

    @Override
    public MerchantResult<Page<ParameterSettingDto>> listParameterSetting(ParameterSettingDto parameterSettingDto, Integer pageNo, Integer pageSize) {
        return parameterSettingService.listParameterSetting(parameterSettingDto,pageNo,pageSize);

    }

    @Override
    public MerchantResult listParameterSettingArea(ParameterSettingDto parameterSettingDto, Integer pageNo, Integer pageSize) {
        return parameterSettingService.listParameterSettingArea(parameterSettingDto,pageNo,pageSize);
    }

    @Override
    public MerchantResult<ParameterSettingDto> getParameterSetting(Integer id) {
        return parameterSettingService.getParameterSetting(id);
    }

    /**
     * 依据系统参数取得
     *
     * @param areaId   （区域id,区域id不传只取总部的;传入优先取区域，再取总部);
     * @param paraCode (参数Code,必传);
     * @return MerchantResult<ParameterSettingDto>
     */
    @Override
    public MerchantResult<ParameterSettingDto> getParameterByParaCode(Integer areaId, String paraCode) {

        return parameterSettingService.getParameterByParaCode(areaId,paraCode);
    }

    @Override
    public MerchantResult<Map<String, String>> getParameterSettingByAreaId(Integer areaId) {
        return parameterSettingService.getParameterSettingByAreaId(areaId);
    }

    @Override
    public MerchantResult modifyState(ParameterSettingDto parameterSettingDto) {
        return parameterSettingService.modifyState(parameterSettingDto);
    }
}
