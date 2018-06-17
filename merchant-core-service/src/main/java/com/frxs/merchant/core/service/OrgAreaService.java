package com.frxs.merchant.core.service;

import com.frxs.merchant.common.dal.entity.OrgAreaView;
import com.frxs.merchant.service.api.dto.OrgAreaDto;
import com.frxs.merchant.service.api.dto.OrgAreaViewDto;
import com.frxs.merchant.service.api.result.MerchantResult;
import java.util.List;

/**
 * 行政区域内部接口
 *
 * @author wushuo
 * @version $Id: OrgAreaService.java,v 0.1 2018年01月31日 9:38 $Exp
 */

public interface OrgAreaService {

    /**
     * 查询省市县三级联动
     * @param parentId
     * @return  MerchantResult<List<OrgAreaDto>>
     */
    public MerchantResult<List<OrgAreaDto>> getByParentId(Integer parentId);

    /**
     * 通过行政区域名称查询行政区域信息
     * @param orgAreaViewDto
     * @return  MerchantResult<List<OrgAreaDto>>
     */
    public MerchantResult<OrgAreaViewDto> getByOrgAreaName(OrgAreaViewDto orgAreaViewDto);
}
