package com.frxs.merchant.core.mapstruct;

import com.frxs.merchant.common.dal.entity.StoreLine;
import com.frxs.merchant.service.api.dto.StoreLineDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author wushuo
 * @version $Id: StoreLineMapStruct.java,v 0.1 2018年03月23日 15:26 $Exp
 */
@Mapper
public interface StoreLineMapStruct {
    /**
     * MAPPER
     */
    StoreLineMapStruct MAPPER = Mappers.getMapper(StoreLineMapStruct.class);

    /**
     * storeLineDto 转 storeLine
     * @param storeLineDto
     * @return
     */
    StoreLine storeLineDtoToStoreLine(StoreLineDto storeLineDto);

    /**
     * storeLine 转 storeLineDto
     * @param storeLine
     * @return
     */
    StoreLineDto storeLineToStoreLineDto(StoreLine storeLine);

    /**
     * storeLines 转 storeLineDtos
     * @param storeLines
     * @return
     */
    List<StoreLineDto> storeLinesToStoreLineDtos(List<StoreLine> storeLines);

}
