/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.core.service;

import com.frxs.merchant.common.dal.entity.AreaServiceamtCode;
import com.frxs.merchant.service.api.dto.SysDictDetailDto;
import java.util.List;

/**
 * 商品服务明细
 *
 * @author sh
 * @version $Id: AreaServiceamtCodeService.java,v 0.1 2018年02月05日 下午 14:44 $Exp
 */
public interface AreaServiceamtCodeService {


  /**
   * 查询区域服务明细
   *
   * @param areaId 区域id
   * @return 区域服务明细
   */
  List<AreaServiceamtCode> queryAreaServiceamt(Long areaId);

  /**
   * 查询系统服务明细配置
   *
   * @return 系统服务明细配置列表
   */
  List<SysDictDetailDto> queryServiceamtSet();

}
