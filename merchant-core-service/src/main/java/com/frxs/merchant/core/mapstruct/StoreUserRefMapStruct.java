package com.frxs.merchant.core.mapstruct;

import com.frxs.merchant.common.dal.entity.StoreUserRef;
import com.frxs.merchant.service.api.dto.StoreUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author wushuo
 * @version $Id: StoreUserRefMapStruct.java,v 0.1 2018年03月24日 11:15 $Exp
 */
@Mapper
public interface StoreUserRefMapStruct {
    StoreUserRefMapStruct MAPPER =Mappers.getMapper(StoreUserRefMapStruct.class);

    /**
     * storeUserRef 转 storeUserDto
     * @param storeUserRef
     * @return
     */
    StoreUserDto storeUserRefToStoreUserDto(StoreUserRef storeUserRef);
}
