package com.frxs.merchant.core.mapstruct;

import com.frxs.merchant.common.dal.entity.VendorAccount;
import com.frxs.merchant.service.api.domain.request.VendorAccountRequest;
import com.frxs.merchant.service.api.dto.VendorAccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author wushuo
 * @version $Id: VendorAccountMapStruct.java,v 0.1 2018年03月23日 17:08 $Exp
 */
@Mapper
public interface VendorAccountMapStruct {

    VendorAccountMapStruct MAPPER = Mappers.getMapper(VendorAccountMapStruct.class);

    /**
     * accountDto 转 account
     * @param vendorAccountDto
     * @return
     */
    VendorAccount accountDtoToAccount(VendorAccountDto vendorAccountDto);

    /**
     * account 转 accountDto
     * @param vendorAccount
     * @return
     */
    VendorAccountDto accountTOAccountDto(VendorAccount vendorAccount);

    /**
     * accountRequest 转 account
     * @param vendorAccountRequest
     * @return
     */
    VendorAccount accountRequestToAccount(VendorAccountRequest vendorAccountRequest);
}
