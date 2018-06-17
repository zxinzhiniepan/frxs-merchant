package com.frxs.merchant.core.mapstruct;

import com.frxs.merchant.common.dal.entity.Product;
import com.frxs.merchant.service.api.dto.ProductDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * 商品转换类
 *
 * @author sh
 * @version $Id: ProductMapStruct.java,v 0.1 2018年01月26日 16:00 $Exp
 */
@Mapper
public interface ProductMapStruct {

  /**
   * MAPPER
   */
  ProductMapStruct MAPPER = Mappers.getMapper(ProductMapStruct.class);

  /**
   * 商品DTO转商品entity
   *
   * @param productDto 商品DTO
   * @return 商品entity
   */
  @Mappings({
      @Mapping(target = "productName", expression = "java(productDto.getProductName()!=null?com.frxs.framework.util.common.StringUtil.trim(productDto.getProductName()):productDto.getProductName())"),
      @Mapping(target = "productTitle", expression = "java(productDto.getProductTitle()!=null?com.frxs.framework.util.common.StringUtil.trim(productDto.getProductTitle()):productDto.getProductTitle())"),
      @Mapping(target = "yieldly", expression = "java(productDto.getYieldly()!=null?com.frxs.framework.util.common.StringUtil.trim(productDto.getYieldly()):productDto.getYieldly())"),
      @Mapping(target = "supplyAmt", ignore = true, expression = "java( new com.frxs.framework.common.domain.Money(productDto.getSupplyAmt()))"),
      @Mapping(target = "marketAmt", expression = "java( new com.frxs.framework.common.domain.Money(productDto.getMarketAmt()))"),
      @Mapping(target = "perServiceAmt", expression = "java( new com.frxs.framework.common.domain.Money(productDto.getPerServiceAmt()))"),
      @Mapping(target = "perCommission", expression = "java( new com.frxs.framework.common.domain.Money(productDto.getPerCommission()))"),
      @Mapping(target = "saleAmt", expression = "java( new com.frxs.framework.common.domain.Money(productDto.getSaleAmt()))")
  })
  Product productDtoToProduct(ProductDto productDto);

  /**
   * 商品entity转商品dto
   *
   * @param product 商品entity
   * @return 商品dto
   */
  @Mappings({
      @Mapping(target = "supplyAmt", expression = "java( product.getSupplyAmt().getAmount())"),
      @Mapping(target = "marketAmt", expression = "java(product.getMarketAmt().getAmount())"),
      @Mapping(target = "perServiceAmt", expression = "java( product.getPerServiceAmt().getAmount())"),
      @Mapping(target = "perCommission", expression = "java(product.getPerCommission().getAmount())"),
      @Mapping(target = "saleAmt", expression = "java(product.getSaleAmt().getAmount())"),
  })
  ProductDto productToProductDto(Product product);

  /**
   * 商品DTO列表转商品entity列表
   *
   * @param productDtos 商品DTO列表
   * @return List<Product> entity列表
   */
  List<Product> productDtosToProducts(List<ProductDto> productDtos);

  /**
   * 商品entity列表转商品dto列表
   *
   * @param products 商品enitity列表
   * @return List<ProductDto> 商品列表entity
   */
  List<ProductDto> productsToProductDtos(List<Product> products);

}
