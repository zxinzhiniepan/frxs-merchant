/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.core.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.frxs.merchant.service.api.dto.ProductVendorDataDto;
import com.frxs.merchant.service.api.dto.ProductVendorDataQueryDto;
import com.frxs.merchant.service.api.dto.ProductVendorDto;
import com.frxs.merchant.service.api.dto.VendorProductDataOperateDto;
import com.frxs.merchant.service.api.result.MerchantResult;

/**
 * @author fygu
 * @version $Id: ProductVendorDataService.java,v 0.1 2018年01月29日 10:06 $Exp
 */
public interface ProductVendorDataService {

  /**
   * 供应商端 供应商商品管理列表展示 仅展示isVendorDel='FALSE' and isAreaDel ='FALSE'即供应商端或区域后台都没有删除。
   *
   * @param vendorId 供应商Id
   * @param auditStatus auditStatus false  展示待提交列 true 展示已提交列
   * @return MerchantResult<List               <               ProductVendorDataDto>>
   */
  MerchantResult<Page<ProductVendorDto>> productVendorDtoList(
      Long vendorId, boolean auditStatus,Page<ProductVendorDto> page);


  MerchantResult areaDel(VendorProductDataOperateDto vendorProductDataOperateDto);

  MerchantResult isVendorDel(VendorProductDataOperateDto vendorProductDataOperateDto);

  MerchantResult updateAuditStatus(VendorProductDataOperateDto vendorProductDataOperateDto);

  MerchantResult createProductVendor(ProductVendorDto productVendorDto);

  MerchantResult updateProductVendor(ProductVendorDto productVendorDto);

  MerchantResult<ProductVendorDto> previewProductVendor(Long vendorProductDataId);

  MerchantResult<Page<ProductVendorDataDto>> productVendorDataList(
      ProductVendorDataQueryDto productVendorDataQueryDto, Page<ProductVendorDataDto> page);

}
