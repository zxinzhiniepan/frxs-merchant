package com.frxs.merchant.core.mapstruct;

import com.frxs.merchant.common.dal.entity.ProductVendorData;
import com.frxs.merchant.service.api.dto.ProductVendorDataDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author fygu
 * @version $Id: ProductVendorDataMapStruct.java,v 0.1 2018年02月02日 11:43 $Exp
 */
@Mapper
public interface ProductVendorDataMapStruct {

  ProductVendorDataMapStruct MAPPER = Mappers.getMapper(ProductVendorDataMapStruct.class);

  @Mappings({
      @Mapping(source = "vendorProductDataId", target = "vendorProductDataId"),
      @Mapping(source = "vendorId", target = "vendorId"),
      @Mapping(source = "vendorCode", target = "vendorCode"),
      @Mapping(source = "vendorName", target = "vendorName"),
      @Mapping(source = "vendorProductName", target = "vendorProductName"),
      @Mapping(source = "vendorProductDesc", target = "vendorProductDesc"),
      @Mapping(source = "tmPublish", target = "tmPublish"),
      @Mapping(source = "auditStatus", target = "auditStatus"),
      @Mapping(source = "auditUserId", target = "auditUserId"),
      @Mapping(source = "auditUserName", target = "auditUserName"),
      @Mapping(source = "auditRession", target = "auditRession"),
      @Mapping(source = "tmAudit", target = "tmAudit"),
      @Mapping(source = "specType", target = "specType"),
      @Mapping(source = "createUserId", target = "createUserId"),
      @Mapping(source = "createUserName", target = "createUserName"),
      @Mapping(source = "modifyUserId", target = "modifyUserId"),
      @Mapping(source = "modifyUserName", target = "modifyUserName")
  })
  ProductVendorData toProductVendorData(ProductVendorDataDto productVendorDataDto);

  ProductVendorDataDto fromProductVendorData(ProductVendorData productVendorData);

}
