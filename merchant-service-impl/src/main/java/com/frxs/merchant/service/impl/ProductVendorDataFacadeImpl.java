package com.frxs.merchant.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.plugins.Page;
import com.frxs.framework.integration.dubbo.annotation.FrxsAutowired;
import com.frxs.merchant.core.service.ProductVendorDataService;
import com.frxs.merchant.service.api.dto.ProductVendorDataDto;
import com.frxs.merchant.service.api.dto.ProductVendorDataQueryDto;
import com.frxs.merchant.service.api.dto.ProductVendorDto;
import com.frxs.merchant.service.api.dto.VendorProductDataOperateDto;
import com.frxs.merchant.service.api.facade.ProductVendorDataFacade;
import com.frxs.merchant.service.api.result.MerchantResult;

/**
 * @author fygu
 * @version $Id: ProductVendorDataFacadeImpl.java,v 0.1 2018年01月31日 16:25 $Exp
 */
@Service(version = "1.0.0")
public class ProductVendorDataFacadeImpl implements ProductVendorDataFacade {


  @FrxsAutowired
  ProductVendorDataService productVendorDataService;

  /**
   * 区域后台删除
   *
   * @param vendorProductDataOperateDto 供应商商品资料id
   * @return
   */
  @Override
  public MerchantResult areaDel(VendorProductDataOperateDto vendorProductDataOperateDto) {
     return productVendorDataService.areaDel(vendorProductDataOperateDto);
  }

  /**
   * 供应商端 未提交删除
   *
   * @param vendorProductDataOperateDto 供应商商品资料id
   */
  @Override
  public MerchantResult isVendorDel(VendorProductDataOperateDto vendorProductDataOperateDto) {

     return productVendorDataService.isVendorDel(vendorProductDataOperateDto);
  }

  /**
   * 供应商端 供应商商品管理列表展示
   * @param vendorId 供应商Id
   * @param auditStatus auditStatus true  展示待提交列 false 展示已提交列
   * @return  MerchantResult<List<ProductVendorDataDto>>
   */
  @Override
  public MerchantResult<Page<ProductVendorDto>> productVendorDtoList(
      Long vendorId, boolean auditStatus,Page<ProductVendorDto> page) {
    return productVendorDataService.productVendorDtoList(vendorId,auditStatus,page);
  }

  /**
   * 供应商商品提交审核
   *
   * @param vendorProductDataOperateDto 供应商商品资料id
   * @return MerchantResult
   */
  @Override
  public MerchantResult updateAuditStatus(VendorProductDataOperateDto vendorProductDataOperateDto) {
    return productVendorDataService.updateAuditStatus(vendorProductDataOperateDto);
  }

  /**
   * 创建供应商商品
   *
   * @param productVendorDto 供应商商品
   * @return MerchantResult
   */
  @Override
  public MerchantResult createProductVendor(ProductVendorDto productVendorDto) {

    return productVendorDataService.createProductVendor(productVendorDto);
  }

  /**
   * 预览供应商商品
   *
   * @param vendorProductDataId 供应商商品属性id
   * @return MerchantResult
   */
  @Override
  public MerchantResult<ProductVendorDto> previewProductVendor(Long vendorProductDataId) {
    return productVendorDataService.previewProductVendor(vendorProductDataId);
  }


  @Override
  public MerchantResult<Page<ProductVendorDataDto>> productVendorDataList(
      ProductVendorDataQueryDto productVendorDataQueryDto,Page<ProductVendorDataDto> page) {
    return productVendorDataService.productVendorDataList(productVendorDataQueryDto,page);
  }

  /**
   * 供应商商品编辑保存
   *
   * @param productVendorDto
   * @return MerchantResult
   */
  @Override
  public MerchantResult updateProductVendor(ProductVendorDto productVendorDto) {
    return productVendorDataService.updateProductVendor(productVendorDto);
  }
}
