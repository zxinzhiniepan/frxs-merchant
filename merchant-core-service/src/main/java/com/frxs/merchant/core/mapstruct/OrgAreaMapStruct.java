package com.frxs.merchant.core.mapstruct;

import com.frxs.merchant.common.dal.entity.OrgArea;
import com.frxs.merchant.service.api.dto.OrgAreaDto;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 行政区域领域模型转换
 *
 * @author wushuo
 * @version $Id: OrgAreaMapStruct.java,v 0.1 2018年01月30日 14:02 $Exp
 */
@Mapper
public interface OrgAreaMapStruct {

    OrgAreaMapStruct MAPPER = Mappers.getMapper(OrgAreaMapStruct.class);

    /**
     *  orgAreas 转 orgAreaDtos
     *
     * @param orgAreas 领域模型List
     * @return List<OrgAreaDto>
     */
    List<OrgAreaDto> orgAreasToOrgAreaDtos(List<OrgArea> orgAreas);

    /**
     *  orgAreaDtos 转 orgAreas
     *
     * @param orgAreaDtos OrgAreaDto List
     * @return List<OrgArea>
     */
    List<OrgArea> orgAreaDtosToOrgAreas(List<OrgAreaDto> orgAreaDtos);
}
