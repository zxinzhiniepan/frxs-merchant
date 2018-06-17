package com.frxs.merchant.core.service;

import com.frxs.merchant.common.dal.entity.SysDictDetail;
import com.frxs.merchant.service.api.dto.SysDictDetailDto;
import com.frxs.merchant.service.api.result.MerchantResult;

import java.util.List;

/**
 * @author jiangboxuan
 * @version @version $Id: SysDictDetailService.java,v 0.1 2018年03月06日 上午 9:08 $Exp
 */
public interface SysDictDetailService {

    /**
     * 条件查询SysDictDetailDto List
     *
     * @param sysDictDetailDto
     * @return com.frxs.merchant.service.api.dto.SysDictDetailDto
     */
    MerchantResult<List<SysDictDetailDto>> listSysDictDetail(SysDictDetailDto sysDictDetailDto);
}
