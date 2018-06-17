package com.frxs.merchant.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.plugins.Page;
import com.frxs.framework.integration.dubbo.annotation.FrxsAutowired;
import com.frxs.merchant.core.service.ProductService;
import com.frxs.merchant.service.api.dto.ProductAttrvalRelationDto;
import com.frxs.merchant.service.api.dto.ProductDescDto;
import com.frxs.merchant.service.api.dto.ProductDto;
import com.frxs.merchant.service.api.dto.ProductImgDto;
import com.frxs.merchant.service.api.dto.ProductQueryDto;
import com.frxs.merchant.service.api.dto.ProductSortDto;
import com.frxs.merchant.service.api.facade.ProductFacade;
import com.frxs.merchant.service.api.result.MerchantResult;
import java.util.List;

/**
 * 商品接口实现
 *
 * @author fygu
 * @version $Id: ProductFacadeImpl.java,v 0.1 2018年02月01日 13:16 $Exp
 */
@Service(version = "1.0.0")
public class ProductFacadeImpl implements ProductFacade {


  @FrxsAutowired
  private ProductService productService;


  @Override
  public MerchantResult<Long> createSingleProduct(ProductDto product,
      ProductAttrvalRelationDto productAttrvalRelation, List<ProductImgDto> productImgList) {

    return productService.createSingleProduct(product, productAttrvalRelation, productImgList);
  }

  @Override
  public MerchantResult updateProductDesc(ProductDescDto productDesc, List<ProductImgDto> descImgList) {
    return productService.updateProductDesc(productDesc, descImgList);
  }

  @Override
  public MerchantResult updateSingleProduct(ProductDto product,
      ProductAttrvalRelationDto productAttrvalRelation, List<ProductImgDto> productImgList) {

    return productService.updateSingleProduct(product, productAttrvalRelation, productImgList);
  }

  @Override
  public MerchantResult<Page<ProductDto>> productQueryList(ProductQueryDto productQueryDto, Page<ProductDto> page) {
    return productService.productQueryList(productQueryDto, page);
  }

  @Override
  public MerchantResult delProducts(ProductSortDto productSortDto) {
    return productService.delProducts(productSortDto);
  }

  @Override
  public MerchantResult updateSkuStatus(ProductSortDto productSortDto) {
    return productService.updateSkuStatus(productSortDto);
  }

  @Override
  public MerchantResult<ProductDto> queryProductDetail(Long productId) {
    return productService.queryProductDetail(productId);
  }

  @Override
  public MerchantResult<List<ProductDto>> queryProductImgAndDesc(List<Long> productIds) {
    return productService.queryProductImgAndDesc(productIds);
  }

  @Override
  public MerchantResult<List<ProductDto>> queryProductSku(List<Long> productIds) {
    return productService.queryProductSku(productIds);
  }
}
