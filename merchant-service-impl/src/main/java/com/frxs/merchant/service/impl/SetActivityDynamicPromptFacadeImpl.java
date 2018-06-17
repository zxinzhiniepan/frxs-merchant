package com.frxs.merchant.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.plugins.Page;
import com.frxs.framework.integration.dubbo.annotation.FrxsAutowired;
import com.frxs.merchant.core.service.SetActivityDynamicPromptService;
import com.frxs.merchant.service.api.dto.SetActivityDynamicPromptDto;
import com.frxs.merchant.service.api.facade.SetActivityDynamicPromptFacade;
import com.frxs.merchant.service.api.result.MerchantResult;

import java.util.List;


/**
 * @author jiangboxuan
 * @version @version $Id: SetActivityDynamicPromptFacadeImpl.java,v 0.1 2018年01月31日 上午 8:56 $Exp
 */
@Service(version="1.0.0")
public class SetActivityDynamicPromptFacadeImpl implements SetActivityDynamicPromptFacade{

    @FrxsAutowired
    SetActivityDynamicPromptService setActivityDynamicPromptService;

    @Override
    public MerchantResult<Page<SetActivityDynamicPromptDto>> listSetActivityDynamicPrompt(SetActivityDynamicPromptDto setActivityDynamicPromptDto,Integer pageNo,Integer pageSize) {
        return setActivityDynamicPromptService.listSetActivityDynamicPrompt(setActivityDynamicPromptDto,pageNo,pageSize);
    }

    @Override
    public MerchantResult<SetActivityDynamicPromptDto> getSetActivityDynamicPromptDto(Integer dynamicPromptId) {
        return setActivityDynamicPromptService.getSetActivityDynamicPromptDto(dynamicPromptId);
    }

    @Override
    public MerchantResult saveSetActivityDynamicPromptDto(SetActivityDynamicPromptDto setActivityDynamicPromptDto) {
        return setActivityDynamicPromptService.saveSetActivityDynamicPromptDto(setActivityDynamicPromptDto);
    }

    @Override
    public MerchantResult modifyState(SetActivityDynamicPromptDto setActivityDynamicPromptDto) {
        return setActivityDynamicPromptService.modifyState(setActivityDynamicPromptDto);
    }

    @Override
    public MerchantResult<SetActivityDynamicPromptDto> selectByTime(Integer areaId) {
        return setActivityDynamicPromptService.selectByTime(areaId);
    }

    @Override
    public MerchantResult<List<SetActivityDynamicPromptDto>> listSetActivityDynamicPromptByTimePeriod(SetActivityDynamicPromptDto setActivityDynamicPromptDto) {
        return setActivityDynamicPromptService.listSetActivityDynamicPromptByTimePeriod(setActivityDynamicPromptDto);
    }
}
