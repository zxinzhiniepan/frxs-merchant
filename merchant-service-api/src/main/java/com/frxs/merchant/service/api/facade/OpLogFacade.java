/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.service.api.facade;

import com.frxs.merchant.service.api.dto.OpLogDto;
import com.frxs.merchant.service.api.result.MerchantResult;

/**
 * 管理操作日志接口
 *
 * @author sh
 * @version $Id: OpLogFacade.java,v 0.1 2018年03月17日 上午 10:48 $Exp
 */
public interface OpLogFacade {

  /**
   * 创建日志
   *
   * @param opLog 日志
   * @return 结果
   */
  MerchantResult createLog(OpLogDto opLog);
}
