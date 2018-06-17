/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.core.mapstruct;

import com.frxs.merchant.common.dal.entity.Vendor;
import com.frxs.merchant.common.dal.entity.VendorQy;
import com.frxs.merchant.service.api.domain.request.VendorRequest;
import com.frxs.merchant.service.api.dto.VendorDto;
import com.frxs.merchant.service.api.dto.VendorInfoDto;
import com.frxs.merchant.service.api.dto.VendorProFileInfoDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 供应商转换类;
 *
 * @author huyong
 */
@Mapper
public interface VendorMapStruct {

    VendorMapStruct MAPPER = Mappers.getMapper(VendorMapStruct.class);

    /**
     * vendor entity转换为vendorDto;
     *
     * @param vendor
     * @return VendorDto
     */
    VendorDto vendorToVendorDto(Vendor vendor);

    /**
     * vendorDto 转 vendor
     * @param vendorDto
     * @return
     */
    Vendor vendorDtoToVendor(VendorDto vendorDto);

    /**
     *  vendors 转 vendorDtos
     *
     * @param vendors 领域模型List
     * @return  List<VendorDto>
     */
    List<VendorDto> vendorsToVendorDtos(List<Vendor> vendors);

    /**
     *  vendorDtos 转 vendors
     *
     * @param vendorDtos
     * @return List<Vendor>
     */
    List<Vendor> vendorDtosTovendors(List<VendorDto> vendorDtos);

    /**
     *  vendorQys 转 vendorDtos
     * @param vendorQys
     * @return List<StoreDto>
     */
    List<VendorDto> vendorQysToVendorDtos(List<VendorQy> vendorQys);

    /**
     * vendorRequest 转 vendor
     * @param vendorRequest
     * @return
     */
    Vendor vendorRequestToVendor(VendorRequest vendorRequest);

    /**
     * vendor 转 vendorInforDto
     * @param vendor
     * @return
     */
    VendorInfoDto vendorToVendorInfoDto(Vendor vendor);

    /**
     * vendor 转 VendorProFileInfoDto
     * @param vendor
     * @return
     */
    VendorProFileInfoDto vendorToVendorProFileInfoDto(Vendor vendor);

}
