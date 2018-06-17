/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.core.mapstruct;

import com.frxs.merchant.common.dal.entity.ProductImg;
import com.frxs.merchant.service.api.dto.ProductImgDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 商品图片DTO转换
 *
 * @author sh
 * @version $Id: ProductImgMapStruct.java,v 0.1 2018年02月05日 下午 14:19 $Exp
 */
@Mapper
public interface ProductImgMapStruct {

  ProductImgMapStruct MAPPER = Mappers.getMapper(ProductImgMapStruct.class);

  /**
   * 商品图片DTO转商品图片entity
   *
   * @param productImgDto 商品片DTO
   * @return 商品图片entity
   */
  ProductImg productImgDtoToProductImg(ProductImgDto productImgDto);

  /**
   * 商品图片entity转商品图片dto
   *
   * @param productImg 商品entity
   * @return 商品dto
   */
  ProductImgDto productImgToProductImgDto(ProductImg productImg);

  /**
   * 商图片dto列表转商品图片entity列表
   *
   * @param productImgDtoList 商品图片dto列表
   * @return 商品图片entity列表
   */
  List<ProductImg> productImgDtoListToProductImgList(List<ProductImgDto> productImgDtoList);

  /**
   * 商品图片entity列表转商品图片dto列表
   *
   * @param productImgList 商品Entity列表
   * @return 商品dto列表
   */
  List<ProductImgDto> productImgListToProductImgDtoList(List<ProductImg> productImgList);
}
