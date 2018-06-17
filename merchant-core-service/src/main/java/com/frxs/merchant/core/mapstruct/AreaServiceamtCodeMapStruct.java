/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.core.mapstruct;

import com.frxs.merchant.common.dal.entity.AreaServiceamtCode;
import com.frxs.merchant.service.api.dto.AreaServiceamtCodeDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 区域服务明细转换
 *
 * @author sh
 * @version $Id: AreaServiceamtCodeMapStruct.java,v 0.1 2018年02月05日 下午 15:24 $Exp
 */
@Mapper
public interface AreaServiceamtCodeMapStruct {

  AreaServiceamtCodeMapStruct MAPPER = Mappers.getMapper(AreaServiceamtCodeMapStruct.class);

  /**
   * 服务明细entity转服务明细dto
   *
   * @param areaServiceamtCode 服务明细entity
   * @return 服务明细dto
   */
  AreaServiceamtCodeDto AreaServiceamtCodeToAreaServiceamtCodeDto(AreaServiceamtCode areaServiceamtCode);

  /**
   * 服务明细entity列表转服务明细dto
   *
   * @param areaServiceamtCodeList 服务明细Entity列表
   * @return 服务明细dto列表
   */
  List<AreaServiceamtCodeDto> AreaServiceamtCodesToAreaServiceamtCodeDtos(List<AreaServiceamtCode> areaServiceamtCodeList);
}
