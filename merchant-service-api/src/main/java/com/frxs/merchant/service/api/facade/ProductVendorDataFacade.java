package com.frxs.merchant.service.api.facade;

import com.baomidou.mybatisplus.plugins.Page;
import com.frxs.merchant.service.api.dto.ProductVendorDataDto;
import com.frxs.merchant.service.api.dto.ProductVendorDataQueryDto;
import com.frxs.merchant.service.api.dto.ProductVendorDto;
import com.frxs.merchant.service.api.dto.VendorProductDataOperateDto;
import com.frxs.merchant.service.api.result.MerchantResult;

/**
 * 供应商商品管理
 *
 * @author fygu
 * @version $Id: ProductVendorDataFacade.java,v 0.1 2018年01月26日 15:10 $Exp
 */
public interface ProductVendorDataFacade {

  /**
   * 区域后台删除
   *
   * @param vendorProductDataOperateDto 供应商商品资料id
   * @return MerchantResult
   */
  MerchantResult areaDel (VendorProductDataOperateDto vendorProductDataOperateDto);

  /**
   * 供应商商品删除
   *
   * @param vendorProductDataOperateDto 供应商商品资料id
   * @return MerchantResult
   */
  MerchantResult isVendorDel (VendorProductDataOperateDto vendorProductDataOperateDto);

  /**
   * 供应商商品提交审核
   * 供应商端提交审核 auditStatus = PENDING
   * 区域后台审核通过 auditStatus = PASS
   * 区域后台驳回 auditStatus = REJECT
   *
   * @param vendorProductDataOperateDto 供应商商品资料id不能为空 auditStatus 审核状态 不能为空
   * @return MerchantResult
   */
  MerchantResult updateAuditStatus (VendorProductDataOperateDto vendorProductDataOperateDto);

  /**
   * 供应商端 供应商商品管理列表展示
   * 仅展示isVendorDel='FALSE' and isAreaDel ='FALSE'即供应商端或区域后台都没有删除。
   *
   * @param vendorId 供应商Id
   * @param auditStatus auditStatus false  展示待提交列 true 展示已提交列
   * @return  MerchantResult<List<ProductVendorDataDto>>
   */
  MerchantResult<Page<ProductVendorDto>> productVendorDtoList(Long vendorId, boolean auditStatus,Page<ProductVendorDto> page) ;

  /**
   * 创建供应商商品
   *
   * @param productVendorDto  供应商商品
   * @return MerchantResult
   */
  MerchantResult createProductVendor(ProductVendorDto productVendorDto);


  /**
   * 预览供应商商品
   *
   * @param vendorProductDataId 供应商商品属性id
   * @return MerchantResult
   */
  MerchantResult<ProductVendorDto> previewProductVendor(Long vendorProductDataId);

  /**
   *  区域端 供应商商品列表
   */
  MerchantResult<Page<ProductVendorDataDto>>   productVendorDataList(ProductVendorDataQueryDto productVendorDataQueryDto,Page<ProductVendorDataDto> page);


  /**
   * 供应商商品编辑保存
   *
   * @param productVendorDto
   * @return MerchantResult
   */
  MerchantResult updateProductVendor(ProductVendorDto productVendorDto);
}
