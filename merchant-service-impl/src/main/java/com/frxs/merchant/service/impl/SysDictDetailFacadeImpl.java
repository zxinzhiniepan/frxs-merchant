package com.frxs.merchant.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.frxs.framework.integration.dubbo.annotation.FrxsAutowired;
import com.frxs.merchant.core.service.SysDictDetailService;
import com.frxs.merchant.service.api.dto.SysDictDetailDto;
import com.frxs.merchant.service.api.facade.SysDictDetailFacade;
import com.frxs.merchant.service.api.result.MerchantResult;

import java.util.List;

/**
 * @author jiangboxuan
 * @version @version $Id: SysDictDetailFacadeImpl.java,v 0.1 2018年03月06日 上午 9:26 $Exp
 */
@Service(version = "1.0.0")
public class SysDictDetailFacadeImpl implements SysDictDetailFacade{

    @FrxsAutowired
    SysDictDetailService sysDictDetailService;

    @Override
    public MerchantResult<List<SysDictDetailDto>> listSysDictDetail(SysDictDetailDto sysDictDetailDto) {
        return sysDictDetailService.listSysDictDetail(sysDictDetailDto);
    }
}
