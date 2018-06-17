package com.frxs.merchant.core.mapstruct;

import com.baomidou.mybatisplus.plugins.Page;
import com.frxs.merchant.common.dal.entity.DistributionLine;
import com.frxs.merchant.service.api.dto.DistributionLineDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author jiangboxuan
 * @version @version $Id: DistributionLineMapStruct.java,v 0.1 2018年01月29日 下午 18:37 $Exp
 */
@Mapper
public interface DistributionLineMapStruct {
    /**
     * MAPPER
     */
    DistributionLineMapStruct MAPPER = Mappers.getMapper(DistributionLineMapStruct.class);

    /**
     * distributionLine 转 DistributionLineDto
     *
     * @param distributionLine 领域模型
     * @return DistributionLineDto
     */
    DistributionLineDto distributionLineToDistributionLineDto(DistributionLine distributionLine);

    /**
     *  DistributionLineDto 转 DistributionLine
     *
     * @param distributionLineDto DistributionLineDto
     * @return DistributionLine 领域模型
     */
    DistributionLine distributionLineDtoToDistributionLine(DistributionLineDto distributionLineDto);

    /**
     *  distributionLines 转 distributionLineDtos
     *
     * @param distributionLines 领域模型List
     * @return List<DistributionLineDto>
     */
    List<DistributionLineDto> distributionLinesToDistributionLineDtos(List<DistributionLine> distributionLines);

    /**
     *  distributionLineDtos 转 distributionLines
     *
     * @param distributionLineDtos DistributionLine List
     * @return List<DistributionLine>
     */
    List<DistributionLine> distributionLineDtosToDistributionLines(List<DistributionLineDto> distributionLineDtos);

    /**
     *  distributionLines 转 distributionLineDtoPages
     *
     * @param distributionLines 领域模型Page
     * @return Page<DistributionLineDto>
     */
    Page<DistributionLineDto> distributionLinesPageDistributionLineDtos(Page<DistributionLine> distributionLines);

}
