package com.frxs.merchant.core.mapstruct;

import com.baomidou.mybatisplus.plugins.Page;

import com.frxs.merchant.common.dal.entity.SetActivityDynamicPrompt;

import com.frxs.merchant.service.api.dto.SetActivityDynamicPromptDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author jiangboxuan
 * @version @version $Id: SetActivityDynamicPromptMapStruct.java,v 0.1 2018年01月30日 下午 14:53 $Exp
 */
@Mapper
public interface SetActivityDynamicPromptMapStruct {
    /**
     * MAPPER
     */
    SetActivityDynamicPromptMapStruct MAPPER = Mappers.getMapper(SetActivityDynamicPromptMapStruct.class);

    /**
     * setActivityDynamicPrompt 转 SetActivityDynamicPromptDto
     *
     * @param setActivityDynamicPrompt 领域模型
     * @return SetActivityDynamicPromptDto
     */
    SetActivityDynamicPromptDto setActivityDynamicPromptToSetActivityDynamicPromptDto(SetActivityDynamicPrompt setActivityDynamicPrompt);

    /**
     *  SetActivityDynamicPromptDto 转 SetActivityDynamicPrompt
     *
     * @param setActivityDynamicPromptDto SetActivityDynamicPromptDto
     * @return SetActivityDynamicPrompt 领域模型
     */
    SetActivityDynamicPrompt setActivityDynamicPromptDtoToSetActivityDynamicPrompt(SetActivityDynamicPromptDto setActivityDynamicPromptDto);

    /**
     *  setActivityDynamicPrompts 转 setActivityDynamicPromptDtos
     *
     * @param setActivityDynamicPrompts 领域模型List
     * @return List<SetActivityDynamicPromptDto>
     */
    List<SetActivityDynamicPromptDto> setActivityDynamicPromptsToSetActivityDynamicPromptDtos(List<SetActivityDynamicPrompt> setActivityDynamicPrompts);

    /**
     *  setActivityDynamicPromptDtos 转 setActivityDynamicPrompts
     *
     * @param setActivityDynamicPromptDtos SetActivityDynamicPrompt List
     * @return List<SetActivityDynamicPrompt>
     */
    List<SetActivityDynamicPrompt> setActivityDynamicPromptDtosToSetActivityDynamicPrompts(List<SetActivityDynamicPromptDto> setActivityDynamicPromptDtos);

    /**
     *  setActivityDynamicPrompts 转 setActivityDynamicPromptDtoPages
     *
     * @param setActivityDynamicPrompts 领域模型Page
     * @return Page<SetActivityDynamicPromptDto>
     */
    Page<SetActivityDynamicPromptDto> setActivityDynamicPromptsPageSetActivityDynamicPromptDtos(Page<SetActivityDynamicPrompt> setActivityDynamicPrompts);
}
