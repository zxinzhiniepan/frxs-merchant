package com.frxs.merchant.core.service.impl;

import com.frxs.merchant.common.dal.entity.OrgArea;
import com.frxs.merchant.common.dal.entity.OrgAreaView;
import com.frxs.merchant.common.util.OTCAssembleUtils;
import com.frxs.merchant.core.mapper.OrgAreaMapper;
import com.frxs.merchant.core.mapper.OrgAreaViewMapper;
import com.frxs.merchant.core.mapstruct.OrgAreaMapStruct;
import com.frxs.merchant.core.service.OrgAreaService;
import com.frxs.merchant.service.api.dto.OrgAreaDto;
import com.frxs.merchant.service.api.dto.OrgAreaViewDto;
import com.frxs.merchant.service.api.result.MerchantResult;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 行政区域内部实现类
 *
 * @author wushuo
 * @version $Id: OrgAreaService.java,v 0.1 2018年01月30日 14:24 $Exp
 */
@Service
public class OrgAreaServiceImpl implements OrgAreaService{

    @Autowired
    private OrgAreaMapper mapper;

    @Autowired
    private OrgAreaViewMapper orgAreaViewMapper;
    /**
     * 查询省市县三级联动
     * @param parentId
     * @return  MerchantResult<List<OrgAreaDto>>
     */
    @Override
    public MerchantResult<List<OrgAreaDto>> getByParentId(Integer parentId) {
        List<OrgArea> orgAreas = mapper.getByParentId(parentId);
        List<OrgAreaDto> orgAreaDtos = OrgAreaMapStruct.MAPPER.orgAreasToOrgAreaDtos(orgAreas);
        MerchantResult<List<OrgAreaDto>> result = new MerchantResult<List<OrgAreaDto>>();
        result.setData(orgAreaDtos);
        return result;
    }

    @Override
    public MerchantResult<OrgAreaViewDto> getByOrgAreaName(OrgAreaViewDto orgAreaViewDto) {

        MerchantResult<OrgAreaViewDto> result = new MerchantResult<OrgAreaViewDto>();
        OrgAreaView orgAreaView = new OrgAreaView();
        OTCAssembleUtils.assemble(orgAreaViewDto,orgAreaView);
        orgAreaView = orgAreaViewMapper.getByOrgAreaName(orgAreaView);
        OTCAssembleUtils.assemble(orgAreaView,orgAreaViewDto);
        result.setData(orgAreaViewDto);
        result.setSuccess(true);
        return result;
    }
}
