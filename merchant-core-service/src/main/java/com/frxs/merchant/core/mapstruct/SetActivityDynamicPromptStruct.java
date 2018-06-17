package com.frxs.merchant.core.mapstruct;

import com.frxs.merchant.common.dal.entity.SetActivityDynamicPrompt;
import com.frxs.merchant.service.api.dto.SetActivityDynamicPromptDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author jiangboxuan
 * @version @version $Id: SetActivityDynamicPromptStruct.java,v 0.1 2018年03月15日 下午 18:54 $Exp
 */
@Mapper
public interface SetActivityDynamicPromptStruct {

    SetActivityDynamicPromptStruct MAPPER = Mappers.getMapper(SetActivityDynamicPromptStruct.class);

    /**
     * 系统entity转系统dto
     *
     * @param setActivityDynamicPrompt 系统entity
     * @return 系统dto
     */
    SetActivityDynamicPromptDto setActivityDynamicPromptToSetActivityDynamicPromptDto(SetActivityDynamicPrompt setActivityDynamicPrompt);

    /**
     *  SetActivityDynamicPromptDto 转 SetActivityDynamicPrompt
     *
     * @param setActivityDynamicPromptDto SetActivityDynamicPromptDto
     * @return SetActivityDynamicPrompt 领域模型
     */
    SetActivityDynamicPrompt setActivityDynamicPromptDtoToSetActivityDynamicPrompt(SetActivityDynamicPromptDto setActivityDynamicPromptDto);
}
