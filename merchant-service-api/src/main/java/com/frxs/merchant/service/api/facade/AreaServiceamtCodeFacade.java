/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.service.api.facade;

import com.frxs.merchant.service.api.dto.AreaServiceamtCodeDto;
import com.frxs.merchant.service.api.dto.ParameterSettingDto;
import com.frxs.merchant.service.api.dto.SysDictDetailDto;
import com.frxs.merchant.service.api.result.MerchantResult;
import java.util.List;

/**
 * 区域服务明细接口
 *
 * @author sh
 * @version $Id: AreaServiceamtCodeFacade.java,v 0.1 2018年02月05日 下午 15:17 $Exp
 */
public interface AreaServiceamtCodeFacade {

  /**
   * 查询区域服务明细
   *
   * @param areaId 区域id
   * @return 明细
   */
  MerchantResult<List<AreaServiceamtCodeDto>> queryAreaServiceamtList(Long areaId);

  /**
   * 查询系统服务明细配置
   *
   * @return 配置列表
   */
  MerchantResult<List<SysDictDetailDto >> queryServiceamtSet();
}
