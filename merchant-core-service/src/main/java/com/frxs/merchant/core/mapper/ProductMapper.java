/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.core.mapper;

import com.frxs.framework.data.persistent.SuperMapper;
import com.frxs.merchant.common.dal.entity.Product;
import com.frxs.merchant.common.dal.model.Pagination;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMapper extends SuperMapper<Product>{

    Product selectByPrimaryKey(Long productId);

    /**
     * 商品管理列表展示
     *
     * @param map 商品id
     * @param pageSize 商品sku
     * @param pageNo 品牌
     * @return com.frxs.merchant.common.dal.entity.Product
     */
    List<Product> findProductByConditions(@Param("productList") Map<String, Object> map,@Param("pageSize")Integer pageSize,@Param("pageNo")Integer pageNo);


    int countProductList(@Param("productList") Map<String, Object> map);

  /**
   * 更新商品状态
   *
   * @param productIdList 商品id
   * @param skuStatus 商品状态
   * @param areaId 区域Id
   * @return int
   */
   int updateSkuStatus(@Param("productIdList") List<Long> productIdList,@Param("skuStatus") String skuStatus,@Param("areaId") Long areaId,
       @Param("operateId") Long operateId,@Param("operateName") String operateName);

  /**
   *
   * @param productIdList
   * @return
   */
   List<Product> findProductList(@Param("productIdList") List<Long> productIdList);


   Product queryProductDetail(Long productId);

}