package com.frxs.merchant.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.frxs.framework.integration.dubbo.annotation.FrxsAutowired;
import com.frxs.merchant.common.dal.entity.OrgAreaView;
import com.frxs.merchant.core.service.OrgAreaService;
import com.frxs.merchant.service.api.dto.OrgAreaDto;
import com.frxs.merchant.service.api.dto.OrgAreaViewDto;
import com.frxs.merchant.service.api.facade.OrgAreaFacade;
import com.frxs.merchant.service.api.result.MerchantResult;
import java.util.List;

/**
 * 行政区域外部实现类
 * @author wushuo
 * @version $Id: OrgAreaFacadeImpl.java,v 0.1 2018年01月31日 9:40 $Exp
 */
@Service(version = "1.0.0")
public class OrgAreaFacadeImpl implements OrgAreaFacade {

    @FrxsAutowired()
    private OrgAreaService orgAreaService;

    /**
     * 查询省市县三级联动
     * @param parentId
     * @return  MerchantResult<List<OrgAreaDto>>
     */
    @Override
    public MerchantResult<List<OrgAreaDto>> getByParentId(Integer parentId) {
        return orgAreaService.getByParentId(parentId);
    }

    @Override
    public MerchantResult<OrgAreaViewDto> getByOrgAreaName(OrgAreaViewDto orgAreaViewDto) {
        return orgAreaService.getByOrgAreaName(orgAreaViewDto);
    }
}
