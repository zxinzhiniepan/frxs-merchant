/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.core.mapstruct;

import com.frxs.merchant.common.dal.entity.SysDictDetail;
import com.frxs.merchant.service.api.dto.SysDictDetailDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 字典明细转换
 *
 * @author sh
 * @version $Id: SysDictDetailMapStruct.java,v 0.1 2018年02月10日 下午 15:19 $Exp
 */
@Mapper
public interface SysDictDetailMapStruct {

  SysDictDetailMapStruct MAPPER = Mappers.getMapper(SysDictDetailMapStruct.class);

  /**
   * 字典明细entity转字典明细DTO
   *
   * @param sysDictDetail 字典明细entity
   * @return 字典明细DTO
   */
  SysDictDetailDto SysDictDetailToSysDictDetailDto(SysDictDetail sysDictDetail);

  /**
   * 字典明细entity列表转字典明细DTO列表
   *
   * @param sysDictDetails 字典明细entity列表
   * @return 字典明细DTO列表
   */
  List<SysDictDetailDto> SysDictDetailsToSysDictDetailDtos(List<SysDictDetail> sysDictDetails);
}
