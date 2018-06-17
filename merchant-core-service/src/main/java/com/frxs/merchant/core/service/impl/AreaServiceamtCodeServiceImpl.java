/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.core.service.impl;

import com.frxs.merchant.common.dal.entity.AreaServiceamtCode;
import com.frxs.merchant.common.dal.entity.SysDictDetail;
import com.frxs.merchant.core.mapper.AreaServiceamtCodeMapper;
import com.frxs.merchant.core.mapper.SysDictDetailMapper;
import com.frxs.merchant.core.mapstruct.SysDictDetailMapStruct;
import com.frxs.merchant.core.service.AreaServiceamtCodeService;
import com.frxs.merchant.service.api.dto.SysDictDetailDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品服务明细
 *
 * @author sh
 * @version $Id: AreaServiceamtCodeService.java,v 0.1 2018年02月05日 下午 14:44 $Exp
 */
@Service("areaServiceamtCodeService")
public class AreaServiceamtCodeServiceImpl implements AreaServiceamtCodeService {

  @Autowired
  private AreaServiceamtCodeMapper areaServiceamtCodeMapper;

  @Autowired
  private SysDictDetailMapper sysDictDetailMapper;

  /**
   * 平台服务费key
   */
  private static final String PLATFORM_SERVICE_FEE = "PLATFORM_SERVICE_FEE";

  /**
   * 查询区域服务明细
   *
   * @param areaId 区域id
   * @return 区域服务明细
   */
  @Override
  public List<AreaServiceamtCode> queryAreaServiceamt(Long areaId) {

    Map<String, Object> map = new HashMap<>();
    map.put("areaId", areaId);
    return areaServiceamtCodeMapper.selectByMap(map);
  }

  /**
   * 查询系统服务明细配置
   *
   * @return 系统服务明细配置列表
   */
  @Override
  public List<SysDictDetailDto> queryServiceamtSet() {

    Map<String, Object> map = new HashMap<>();
    map.put("dictCode", PLATFORM_SERVICE_FEE);
    List<SysDictDetail> details = sysDictDetailMapper.selectByMap(map);
    return SysDictDetailMapStruct.MAPPER.SysDictDetailsToSysDictDetailDtos(details);
  }


}
