/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.core.mapper;

import com.frxs.framework.data.persistent.SuperMapper;
import com.frxs.merchant.common.dal.entity.ProductVendorData;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductVendorDataMapper extends SuperMapper<ProductVendorData> {

  ProductVendorData selectByPrimaryKey(Long vendorProductDataId);


  /**
   *
   * @param vendorProductDataId
   * @return
   */
  ProductVendorData selectDataById(@Param("vendorProductDataId") Long vendorProductDataId);

  /**
   * 查询供应商商品列表
   */
  List<ProductVendorData> selectVendorDataList(@Param("vendorProductDataIds") List<Long> vendorProductDataIdList);

  /**
   *
   * @param productVendorData
   * @return
   */
  Integer insertProductVendorData(ProductVendorData productVendorData);

  /**
   * 区域后台更新供应商商品状态
   *
   * @param vendorProductDataIds 商品id
   * @return int
   */
  int updateAreaDel(@Param("vendorProductDataIds") List<Long> vendorProductDataIds, @Param("operateId") Long operateId, @Param("operateName") String operateName);


  /**
   * 供应商后台更新供应商商品状态
   *
   * @param vendorProductDataId 商品id
   * @return int
   */
  int updateVendorDel(@Param("vendorProductDataId") Long vendorProductDataId, @Param("operateId") Long operateId, @Param("operateName") String operateName);

  /**
   * @param vendorProductDataIds 商品id
   * @return int
   */
  int updateAuditStatus(@Param("vendorProductDataIds") List<Long> vendorProductDataIds, @Param("auditRession") String auditRession, @Param("auditStatus") String auditStatus,
      @Param("operateId") Long operateId, @Param("operateName") String operateName);

  /**
   * 供应商端查询商品列表
   */
  int countProductVendorList(@Param("query") Map<String, Object> map);

  /**
   * 供应商端查询商品列表
   *
   * @param map 供应商Id
   * @return List<ProductVendorData>
   */
  List<ProductVendorData> productVendorList(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("query") Map<String, Object> map);

  /**
   * 区域后台 供应商商品列表
   */
  List<ProductVendorData> productVendorDataList(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize, @Param("productVendorDataQueryDto") Map<String, Object> map);

  /**
   * 区域后台 供应商商品列表
   */
  int countProductVendorDataList(@Param("productVendorDataQueryDto") Map<String, Object> map);
}