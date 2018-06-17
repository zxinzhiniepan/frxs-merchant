/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.frxs.framework.integration.dubbo.annotation.FrxsAutowired;
import com.frxs.framework.util.common.log4j.LogUtil;
import com.frxs.merchant.common.dal.entity.AreaServiceamtCode;
import com.frxs.merchant.common.dal.enums.ErrorCodeDetailEnum;
import com.frxs.merchant.common.dal.enums.ErrorCodeScenarioEnum;
import com.frxs.merchant.core.exception.BaseMerchantException;
import com.frxs.merchant.core.helper.MerchantResultHelper;
import com.frxs.merchant.core.mapstruct.AreaServiceamtCodeMapStruct;
import com.frxs.merchant.core.service.AreaServiceamtCodeService;
import com.frxs.merchant.service.api.dto.AreaServiceamtCodeDto;
import com.frxs.merchant.service.api.dto.SysDictDetailDto;
import com.frxs.merchant.service.api.facade.AreaServiceamtCodeFacade;
import com.frxs.merchant.service.api.result.MerchantResult;
import java.util.List;

/**
 * 区域服务明细接口实现
 *
 * @author sh
 * @version $Id: AreaServiceamtCodeFacadeImpl.java,v 0.1 2018年02月05日 下午 15:20 $Exp
 */
@Service(version = "1.0.0")
public class AreaServiceamtCodeFacadeImpl implements AreaServiceamtCodeFacade {

  @FrxsAutowired
  private AreaServiceamtCodeService areaServiceamtCodeService;

  @FrxsAutowired
  private MerchantResultHelper<MerchantResult> merchantResultHelper;

  @Override
  public MerchantResult<List<AreaServiceamtCodeDto>> queryAreaServiceamtList(Long areaId) {

    MerchantResult<List<AreaServiceamtCodeDto>> result = new MerchantResult<>();
    try {
      List<AreaServiceamtCode> list = areaServiceamtCodeService.queryAreaServiceamt(areaId);
      result.setData(AreaServiceamtCodeMapStruct.MAPPER.AreaServiceamtCodesToAreaServiceamtCodeDtos(list));
      merchantResultHelper.fillWithSuccess(result);
    } catch (Exception e) {
      LogUtil.error("[AreaServiceamtCodeFacadeImpl:商品]商品区域服务明细查询异常", e);
      merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.PRODUCT, new BaseMerchantException(ErrorCodeDetailEnum.PRODUCT_SERVICE_DETAIL_ERROR, "商品区域服务明细配置查询失败"));
    }
    return result;
  }

  @Override
  public MerchantResult<List<SysDictDetailDto>> queryServiceamtSet() {

    MerchantResult<List<SysDictDetailDto>> result = new MerchantResult<>();
    try {
      List<SysDictDetailDto> list = areaServiceamtCodeService.queryServiceamtSet();
      result.setData(list);
      merchantResultHelper.fillWithSuccess(result);
    } catch (Exception e) {
      LogUtil.error("[AreaServiceamtCodeFacadeImpl:商品]商品服务明细系统配配置查询异常", e);
      merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.PRODUCT, new BaseMerchantException(ErrorCodeDetailEnum.PRODUCT_SERVICE_DETAIL_ERROR, "商品服务明细系统配配置查询失败"));
    }
    return result;
  }
}
