package com.frxs.merchant.core.service.impl;

import com.frxs.merchant.common.dal.entity.SysDictDetail;
import com.frxs.merchant.common.util.OTCAssembleUtils;
import com.frxs.merchant.core.mapper.SysDictDetailMapper;
import com.frxs.merchant.core.service.SysDictDetailService;
import com.frxs.merchant.service.api.dto.SysDictDetailDto;
import com.frxs.merchant.service.api.result.MerchantResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiangboxuan
 * @version @version $Id: SysDictDetailServiceImpl.java,v 0.1 2018年03月06日 上午 9:10 $Exp
 */
@Service
public class SysDictDetailServiceImpl implements SysDictDetailService{

    @Autowired
    SysDictDetailMapper sysDictDetailMapper;

    @Override
    public MerchantResult<List<SysDictDetailDto>> listSysDictDetail(SysDictDetailDto sysDictDetailDto) {
        MerchantResult<List<SysDictDetailDto>> merchantResult = new MerchantResult<List<SysDictDetailDto>>();
        SysDictDetail sysDictDetail = new SysDictDetail();
        OTCAssembleUtils.assemble(sysDictDetailDto,sysDictDetail);
        List<SysDictDetail> sysDictDetails = sysDictDetailMapper.listSysDictDetail(sysDictDetail);
        List<SysDictDetailDto> sysDictDetailDtos = new ArrayList<SysDictDetailDto>();
        for (SysDictDetail sysDictDetailObj : sysDictDetails){
            SysDictDetailDto sysDictDetailDtoObj = new SysDictDetailDto();
            OTCAssembleUtils.assemble(sysDictDetailObj,sysDictDetailDtoObj);
            sysDictDetailDtos.add(sysDictDetailDtoObj);
        }
        merchantResult.setData(sysDictDetailDtos);
        merchantResult.setSuccess(true);
        return merchantResult;
    }
}
