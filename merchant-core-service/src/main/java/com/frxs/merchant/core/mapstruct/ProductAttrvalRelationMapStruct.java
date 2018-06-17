/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.core.mapstruct;

import com.frxs.merchant.common.dal.entity.ProductAttrvalRelation;
import com.frxs.merchant.service.api.dto.ProductAttrvalRelationDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 商品属性关联DTO转换
 *
 * @author sh
 * @version $Id: ProductAttrvalRelationMapStruct.java,v 0.1 2018年02月05日 下午 14:14 $Exp
 */
@Mapper
public interface ProductAttrvalRelationMapStruct {

  ProductAttrvalRelationMapStruct MAPPER = Mappers.getMapper(ProductAttrvalRelationMapStruct.class);

  /**
   * 商品属性DTO转商品属性entity
   *
   * @param productAttrvalRelationDto 商品属性DTO
   * @return 商品属性entity
   */
  ProductAttrvalRelation productAttrvalRelationDtoToProductAttrvalRelation(ProductAttrvalRelationDto productAttrvalRelationDto);

  /**
   * 商品属性entity转商品属性dto
   *
   * @param productAttrvalRelation 商品属性entity
   * @return 商品属性dto
   */
  ProductAttrvalRelationDto productAttrvalRelationToProductAttrvalRelationDto(ProductAttrvalRelation productAttrvalRelation);

  /**
   * 商品属性entity列表转商品属性dto列表
   *
   * @param productAttrvalRelations 商品属性entity列表
   * @return 商品属性dto列表
   */
  List<ProductAttrvalRelationDto> productAttrvalRelationsToProductAttrvalRelationDtos(List<ProductAttrvalRelation> productAttrvalRelations);
}
