/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.service.api.facade;

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
 * 商品接口
 *
 * @author sh
 * @version $Id: ProductFacade.java,v 0.1 2018年01月26日 上午 8:52 $Exp
 */
public interface ProductFacade {

  /**
   * 创建单规格属性商品
   *
   * @param product 商品SKU信息
   * @param productAttrvalRelation 商品属性
   * @param productImgList 商品SKU图片：广告图片主图
   */
  MerchantResult<Long> createSingleProduct(ProductDto product, ProductAttrvalRelationDto productAttrvalRelation, List<ProductImgDto> productImgList);

  /**
   * 更新商品图文
   *
   * @param productDesc 描述
   * @param descImgList 图文详情图片
   * @return 结果
   */
  MerchantResult updateProductDesc(ProductDescDto productDesc, List<ProductImgDto> descImgList);


  /**
   * 修改单规格属性商品
   *
   * @param product 商品SKU信息
   * @param productAttrvalRelation 商品属性
   * @param productImgList 商品SKU图片
   */
  MerchantResult updateSingleProduct(ProductDto product, ProductAttrvalRelationDto productAttrvalRelation, List<ProductImgDto> productImgList);


  /**
   * 区域后台：商品管理列表展示
   *
   * @param productQueryDto 商品id
   * @param page 分页数
   * @return productList 商品列表
   */
  MerchantResult<Page<ProductDto>> productQueryList(ProductQueryDto productQueryDto, Page<ProductDto> page);


  /**
   * 商品详情
   *
   * @param productId 商品id
   * @return 商品详情
   */
  MerchantResult<ProductDto> queryProductDetail(Long productId);

  /**
   * 删除商品
   * 校验逻辑：
   * 1.未被活动引用的商品
   * 2.参加过活动、活动已经被删除的商品
   * skuStatus DELETE-已删除
   *
   * @param productSortDto 商品id 参数：productIds，areaId，operateId，operateName，operateDate
   * @return MerchantResult
   */
  MerchantResult delProducts(ProductSortDto productSortDto);

  /**
   * 上下架商品
   * skuStatus UP-上架，DOWN-下架
   *
   * @param productSortDto 参数：productIds，areaId，operateId，operateName，operateDate，skuStatus
   * @return MerchantResult
   */
  MerchantResult updateSkuStatus(ProductSortDto productSortDto);

  /**
   * 商品图片和简介
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
