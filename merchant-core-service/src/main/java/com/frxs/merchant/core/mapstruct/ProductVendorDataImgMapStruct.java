package com.frxs.merchant.core.mapstruct;

import com.frxs.merchant.common.dal.entity.ProductVendorDataImg;
import com.frxs.merchant.service.api.dto.ProductVendorDataImgDto;
import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author fygu
 * @version $Id: ProductVendorDataImgMapStruct.java,v 0.1 2018年02月02日 12:39 $Exp
 */
@Mapper(componentModel = "spring")
public interface ProductVendorDataImgMapStruct {

  @Mappings({
      @Mapping(source = "vendorProductImgId", target = "vendorProductImgId"),
      @Mapping(source = "vendorProductDataId", target = "vendorProductDataId"),
      @Mapping(source = "originalImgUrl", target = "originalImgUrl"),
      @Mapping(source = "imgUrl60", target = "imgUrl60"),
      @Mapping(source = "imgUrl120", target = "imgUrl120"),
      @Mapping(source = "imgUrl200", target = "imgUrl200"),
      @Mapping(source = "imgUrl400", target = "imgUrl400"),
      @Mapping(source = "sortSeq", target = "sortSeq"),
      @Mapping(source = "createUserId", target = "createUserId"),
      @Mapping(source = "createUserName", target = "createUserName"),
      @Mapping(source = "modifyUserId", target = "modifyUserId"),
      @Mapping(source = "modifyUserName", target = "modifyUserName")
  })

  /**
   *
   */
  ProductVendorDataImg toProductVendorDataImg(ProductVendorDataImgDto productVendorDataImgDto);

  /**
   *
   * @param productVendorDataImg
   * @return
   */
  @InheritInverseConfiguration
  ProductVendorDataImgDto fromProductVendorDataImg(ProductVendorDataImg productVendorDataImg);

  List<ProductVendorDataImgDto> fromProductVendorDataImgs(List<ProductVendorDataImg> productVendorDataImgs);

}
