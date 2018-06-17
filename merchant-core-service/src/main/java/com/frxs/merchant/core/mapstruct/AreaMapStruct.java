package com.frxs.merchant.core.mapstruct;

import com.frxs.merchant.common.dal.entity.Area;
import com.frxs.merchant.service.api.dto.AreaDto;
import com.frxs.merchant.service.api.dto.AreaInfoDto;
import java.util.List;

import org.checkerframework.checker.units.qual.A;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 区域领域模型转换
 *
 * @author wushuo
 * @version $Id: AreaMapStruct.java,v 0.1 2018年01月29日 16:15 $Exp
 */
@Mapper
public interface AreaMapStruct {

    /**
     * MAPPER
     */
    AreaMapStruct MAPPER = Mappers.getMapper(AreaMapStruct.class);

    /**
     * areas 转 areaDtos
     *
     * @param areas
     * @return List<Area>
     */
    List<AreaDto> areasToAreaDtos (List<Area> areas);

    /**
     * area 转 areaDto
     *
     * @param area
     * @return AreaDto
     */
    AreaDto areaToAreaDto (Area area);

    /**
     * areaDto 转 area
     * @param areaDto
     * @return
     */
    Area areaDtoToArea (AreaDto areaDto);

    /**
     * area 转 areaInfoDto
     * @param area
     * @return
     */
    AreaInfoDto areaToAreaInfoDto(Area area);


    /**
     * areas 转 areaInfoDtos
     * @param areas
     * @return
     */
    List<AreaInfoDto> areasTOAreaInfoDtos(List<Area> areas);
}
