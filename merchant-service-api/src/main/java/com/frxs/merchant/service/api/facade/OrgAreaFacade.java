package com.frxs.merchant.service.api.facade;

import com.frxs.merchant.service.api.dto.OrgAreaDto;
import com.frxs.merchant.service.api.dto.OrgAreaViewDto;
import com.frxs.merchant.service.api.result.MerchantResult;
import java.util.List;

/**
 * 省市县三级联动接口
 *
 * @author wushuo
 * @version $Id: OrgAreaFacade.java,v 0.1 2018年01月30日 11:49 $Exp
 */
public interface OrgAreaFacade {

    /**
     * 查询省市县三级联动
     * @param parentId
     * @return  MerchantResult<List<OrgAreaDto>>
     */
    MerchantResult<List<OrgAreaDto>> getByParentId(Integer parentId);

    /**
     * 通过行政区域名称查询行政区域信息
     * @param orgAreaViewDto
     * @return  MerchantResult<OrgAreaViewDto>
     */
    public MerchantResult<OrgAreaViewDto> getByOrgAreaName(OrgAreaViewDto orgAreaViewDto);
}
