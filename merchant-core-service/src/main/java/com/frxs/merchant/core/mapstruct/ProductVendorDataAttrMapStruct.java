package com.frxs.merchant.core.mapstruct;

import com.frxs.merchant.common.dal.entity.ProductVendorDataAttr;
import com.frxs.merchant.service.api.dto.ProductVendorDataAttrDto;
import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author fygu
 * @version $Id: ProductVendorDataAttrMapStruct.java,v 0.1 2018年02月02日 12:47 $Exp
 */
@Mapper
public interface ProductVendorDataAttrMapStruct {

  ProductVendorDataAttrMapStruct MAPPER = Mappers.getMapper(ProductVendorDataAttrMapStruct.class);

  @Mappings({
      @Mapping(source = "vendorProductAttrId", target = "vendorProductAttrId"),
      @Mapping(source = "vendorProductDataId", target = "vendorProductDataId"),
      @Mapping(source = "vendorProductAttr", target = "vendorProductAttr"),
      @Mapping(target = "vendorProductAmt", expression = "java( new com.frxs.framework.common.domain.Money(productVendorDataAttrDto.getVendorProductAmt()))"),
      @Mapping(source = "vendorProductQty", target = "vendorProductQty"),
      @Mapping(source = "createUserId", target = "createUserId"),
      @Mapping(source = "createUserName", target = "createUserName"),
      @Mapping(source = "modifyUserId", target = "modifyUserId"),
      @Mapping(source = "modifyUserName", target = "modifyUserName")
  })
  ProductVendorDataAttr toProductVendorDataAttr(ProductVendorDataAttrDto productVendorDataAttrDto);

  /**
   *
   * @param productVendorDataAttr
   * @return
   */
  @Mappings({
      @Mapping(source = "vendorProductAttrId", target = "vendorProductAttrId"),
      @Mapping(source = "vendorProductDataId", target = "vendorProductDataId"),
      @Mapping(source = "vendorProductAttr", target = "vendorProductAttr"),
      @Mapping(target = "vendorProductAmt", expression = "java( productVendorDataAttr.getVendorProductAmt().getAmount())"),
      @Mapping(source = "vendorProductQty", target = "vendorProductQty"),
      @Mapping(source = "createUserId", target = "createUserId"),
      @Mapping(source = "createUserName", target = "createUserName"),
      @Mapping(source = "modifyUserId", target = "modifyUserId"),
      @Mapping(source = "modifyUserName", target = "modifyUserName")
  })
  ProductVendorDataAttrDto fromProductVendorDataAttr(ProductVendorDataAttr productVendorDataAttr);


  List<ProductVendorDataAttrDto> fromProductVendorDataAttrs(List<ProductVendorDataAttr> productVendorDataAttrs);

}
