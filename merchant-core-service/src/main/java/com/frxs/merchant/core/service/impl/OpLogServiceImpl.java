/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.core.service.impl;

import com.frxs.framework.util.common.log4j.LogUtil;
import com.frxs.merchant.common.dal.entity.OpLog;
import com.frxs.merchant.common.dal.enums.ErrorCodeDetailEnum;
import com.frxs.merchant.common.dal.enums.ErrorCodeScenarioEnum;
import com.frxs.merchant.core.helper.MerchantResultHelper;
import com.frxs.merchant.core.mapper.OpLogMapper;
import com.frxs.merchant.core.mapstruct.OpLogMapStruct;
import com.frxs.merchant.core.service.OpLogService;
import com.frxs.merchant.service.api.dto.OpLogDto;
import com.frxs.merchant.service.api.result.MerchantResult;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 后台日志实现
 *
 * @author sh
 * @version $Id: OpLogServiceImpl.java,v 0.1 2018年03月17日 上午 10:54 $Exp
 */
@Service
public class OpLogServiceImpl implements OpLogService {

  @Autowired
  private OpLogMapper opLogMapper;

  @Autowired
  private MerchantResultHelper<MerchantResult> merchantResultHelper;

  @Override
  public MerchantResult createLog(OpLogDto opLog) {

    MerchantResult merchantResult = new MerchantResult();
    try {
      OpLog log = OpLogMapStruct.MAPPER.toLog(opLog);
      log.setTmCreate(new Date());
      opLogMapper.insert(log);
      merchantResultHelper.fillWithSuccess(merchantResult);
    } catch (Exception e) {
      LogUtil.error("[OpLogServiceImpl:日志]保存日志异常", e);
      merchantResultHelper.fillWithFailure(merchantResult, ErrorCodeScenarioEnum.OP_LOG, ErrorCodeDetailEnum.OP_LOG_ERROR);
    }
    return merchantResult;
  }
}
