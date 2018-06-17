package com.frxs.merchant.core.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.frxs.merchant.service.api.dto.SetActivityDynamicPromptDto;
import com.frxs.merchant.service.api.result.MerchantResult;

import java.util.List;

/**
 * @author jiangboxuan
 * @version @version $Id: SetActivityDynamicPromptService.java,v 0.1 2018年01月31日 上午 8:53 $Exp
 */
public interface SetActivityDynamicPromptService {

    /**
     * 活动设置列表展示
     *
     * @param setActivityDynamicPromptDto 活动动态提示dto
     * @return MerchantResult<Page<SetActivityDynamicPromptDto>>
     */
    MerchantResult<Page<SetActivityDynamicPromptDto>> listSetActivityDynamicPrompt(SetActivityDynamicPromptDto setActivityDynamicPromptDto,Integer pageNo,Integer pageSize);

    /**
     * 活动设置详情
     *
     * @param dynamicPromptId
     * @return MerchantResult<SetActivityDynamicPromptDto>
     */
    MerchantResult<SetActivityDynamicPromptDto> getSetActivityDynamicPromptDto(Integer dynamicPromptId);

    /**
     * 活动设置保存
     *
     * @param setActivityDynamicPromptDto
     * @return MerchantResult
     */
    MerchantResult saveSetActivityDynamicPromptDto(SetActivityDynamicPromptDto setActivityDynamicPromptDto);

    /**
     * 活动设置修改状态
     *
     * @param setActivityDynamicPromptDto
     * @return MerchantResult
     */
    MerchantResult modifyState(SetActivityDynamicPromptDto setActivityDynamicPromptDto);

    /**
     * 通过时间段查询活动动态提示
     *
     * @return MerchantResult
     */
    MerchantResult<SetActivityDynamicPromptDto> selectByTime(Integer areaId);

    /**
     * 活动提示设置通过时间段查询时间段
     *
     * @param setActivityDynamicPromptDto 活动动态提示dto
     * @return MerchantResult<List<SetActivityDynamicPromptDto>>
     */
    MerchantResult<List<SetActivityDynamicPromptDto>> listSetActivityDynamicPromptByTimePeriod(SetActivityDynamicPromptDto setActivityDynamicPromptDto);
}
