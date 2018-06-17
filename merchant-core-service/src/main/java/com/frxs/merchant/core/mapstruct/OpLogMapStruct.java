/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.core.mapstruct;

import com.frxs.merchant.common.dal.entity.OpLog;
import com.frxs.merchant.service.api.dto.OpLogDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 操作就志转换
 *
 * @author sh
 * @version $Id: OpLogMapStruct.java,v 0.1 2018年03月17日 上午 10:57 $Exp
 */
@Mapper
public interface OpLogMapStruct {

  OpLogMapStruct MAPPER = Mappers.getMapper(OpLogMapStruct.class);

  /**
   * 转 OpLog
   *
   * @param opLogDto opLogDto
   * @return OpLog
   */
  OpLog toLog(OpLogDto opLogDto);
}
