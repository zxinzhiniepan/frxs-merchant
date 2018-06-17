/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.frxs.framework.integration.dubbo.annotation.FrxsAutowired;
import com.frxs.merchant.core.service.OpLogService;
import com.frxs.merchant.service.api.dto.OpLogDto;
import com.frxs.merchant.service.api.facade.OpLogFacade;
import com.frxs.merchant.service.api.result.MerchantResult;

/**
 * 操作日志实现
 *
 * @author sh
 * @version $Id: OpLogFacadeImpl.java,v 0.1 2018年03月17日 上午 10:53 $Exp
 */
@Service(version = "1.0.0")
public class OpLogFacadeImpl implements OpLogFacade {

  @FrxsAutowired
  private OpLogService opLogService;

  @Override
  public MerchantResult createLog(OpLogDto opLog) {
    return opLogService.createLog(opLog);
  }
}
