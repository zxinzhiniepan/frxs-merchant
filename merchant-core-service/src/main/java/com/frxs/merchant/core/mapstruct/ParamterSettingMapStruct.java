/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.core.mapstruct;

import com.frxs.merchant.common.dal.entity.ParameterSetting;
import com.frxs.merchant.service.api.dto.ParameterSettingDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 系统参数配置转换
 *
 * @author sh
 * @version $Id: ParamterSettingMapStruct.java,v 0.1 2018年02月05日 下午 15:29 $Exp
 */
@Mapper
public interface ParamterSettingMapStruct {

  ParamterSettingMapStruct MAPPER = Mappers.getMapper(ParamterSettingMapStruct.class);

  /**
   * 系统entity转系统dto
   *
   * @param parameterSetting 系统entity
   * @return 系统dto
   */
  ParameterSettingDto paramterSettingToParameterSettingDto(ParameterSetting parameterSetting);

  /**
   * 系统dto转系统entity
   *
   * @param parameterSettingDto
   * @return ParameterSetting
   */
  ParameterSetting parameterSettingDtoToParameterSetting(ParameterSettingDto parameterSettingDto);

  /**
   * 系统entity列表转系统dto列表
   *
   * @param parameterSettings 系统entity列表
   * @return 转系统dto列表
   */
  List<ParameterSettingDto> paramterSettingsToParameterSettingDtos(List<ParameterSetting> parameterSettings);

}
