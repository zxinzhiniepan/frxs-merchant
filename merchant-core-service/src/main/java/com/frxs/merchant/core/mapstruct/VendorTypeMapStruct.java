package com.frxs.merchant.core.mapstruct;

import com.frxs.merchant.common.dal.entity.VendorType;
import com.frxs.merchant.service.api.dto.VendorTypeDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author wushuo
 * @version $Id: VendorTypeMapStruct.java,v 0.1 2018年03月23日 17:16 $Exp
 */
@Mapper
public interface VendorTypeMapStruct {
    VendorTypeMapStruct MAPPER = Mappers.getMapper(VendorTypeMapStruct.class);

    /**
     * vendorTypeDto 转 vendorType
     * @param vendorTypeDto
     * @return
     */
    VendorType typeDtoTOType(VendorTypeDto vendorTypeDto);

    /**
     * vendorType 转 vendorTypeDto
     * @param vendorType
     * @return
     */
    VendorTypeDto typeTOTypeDto(VendorType vendorType);

    /**
     * vendorTypes 转 vendorTypeDtos
     * @param vendorTypes
     * @return
     */
    List<VendorTypeDto> typesToTypeDtos(List<VendorType> vendorTypes);
}
