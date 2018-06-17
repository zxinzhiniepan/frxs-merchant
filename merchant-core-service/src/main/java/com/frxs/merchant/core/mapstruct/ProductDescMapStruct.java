/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.core.mapstruct;

import com.frxs.merchant.common.dal.entity.ProductDesc;
import com.frxs.merchant.service.api.dto.ProductDescDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * 商品详情DTO转换
 *
 * @author sh
 * @version $Id: ProductDescMapStruct.java,v 0.1 2018年02月05日 下午 19:10 $Exp
 */
@Mapper
public interface ProductDescMapStruct {

  ProductDescMapStruct MAPPER = Mappers.getMapper(ProductDescMapStruct.class);

  /**
   * 商品描述DTO转商品描述entity
   *
   * @param productDescDto 商品描述DTO
   * @return 商品描述entity
   */
  @Mappings({
      @Mapping(target = "shareTitle", expression = "java(productDescDto.getShareTitle()!=null?com.frxs.framework.util.common.StringUtil.trim(productDescDto.getShareTitle()):productDescDto.getShareTitle())"),
      @Mapping(target = "briefDesc", expression = "java(productDescDto.getBriefDesc()!=null?com.frxs.framework.util.common.StringUtil.trim(productDescDto.getBriefDesc()):productDescDto.getBriefDesc())"),
      @Mapping(target = "detailDesc", expression = "java(productDescDto.getDetailDesc()!=null?com.frxs.framework.util.common.StringUtil.trim(productDescDto.getDetailDesc()):productDescDto.getDetailDesc())")
  })
  ProductDesc productDescDtoToProductDesc(ProductDescDto productDescDto);

  /**
   * 商品描述entity转商品描述Dto
   *
   * @param productDesc 商品描述entity
   * @return 商品描述Dto
   */
  ProductDescDto productDescToProductDescDto(ProductDesc productDesc);

}
