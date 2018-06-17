/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.core.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.frxs.merchant.service.api.dto.ProductAttrvalRelationDto;
import com.frxs.merchant.service.api.dto.ProductDescDto;
import com.frxs.merchant.service.api.dto.ProductDto;
import com.frxs.merchant.service.api.dto.ProductImgDto;
import com.frxs.merchant.service.api.dto.ProductQueryDto;
import com.frxs.merchant.service.api.dto.ProductSortDto;
import com.frxs.merchant.service.api.result.MerchantResult;
import java.util.List;

/**
 * @author fygu
 * @version $Id: ProductService.java,v 0.1 2018年01月26日 12:23 $Exp
 */
public interface ProductService {

  /**
   * 创建单规格商品
   *
   * @param product 商品信息
   * @param productAttrvalRelation 商品关联的属性
   * @param productImgList 商品图片
   */
  MerchantResult<Long> createSingleProduct(ProductDto product, ProductAttrvalRelationDto productAttrvalRelation, List<ProductImgDto> productImgList);


  /**
   * 修改单规格商品
   *
   * @param product 商品
   * @param productAttrvalRelation 属性
   * @param productImgList 图片
   * @return 结果
   */
  MerchantResult updateSingleProduct(ProductDto product, ProductAttrvalRelationDto productAttrvalRelation, List<ProductImgDto> productImgList);

  /**
   * 商品展示列表
   *
   * @param productQueryDto 商品id
   * @param page 分页页数
   */
  MerchantResult<Page<ProductDto>> productQueryList(ProductQueryDto productQueryDto, Page<ProductDto> page);

  /**
   * 更新商品图文详情
   *
   * @param productDesc 商品详情
   * @param descImgList 详情图片
   * @return 结果
   */
  MerchantResult updateProductDesc(ProductDescDto productDesc, List<ProductImgDto> descImgList);

  /**
   * 逻辑删除商品  productIds可以是一个也可以是多个 根据删除商品时需要判断商品是否已经加入活动
   *
   * @param productSortDto 商品id
   */
  MerchantResult delProducts(ProductSortDto productSortDto);

  /**
   * 批量上下架 productIds可以是一个也可以是多个
   *
   * @param productSortDto 商品上下架状态 商品上架 UP   商品下架 DOWN
   */
  MerchantResult updateSkuStatus(ProductSortDto productSortDto);

  /**
   * 查询商品详情
   *
   * @param productId 商品id
   * @return 商品详情
   */
  MerchantResult<ProductDto> queryProductDetail(Long productId);

  /**
   * 查询商品图片和简介
   *
   * @param productIds 商品id列表
   * @return 商品图片和简介
   */
  MerchantResult<List<ProductDto>> queryProductImgAndDesc(List<Long> productIds);

  /**
   * 查询商品sku的基本信息
   *
   * @param productIds 商品id列表
   * @return 商品sku的基本信息
   */
  MerchantResult<List<ProductDto>> queryProductSku(List<Long> productIds);

}
