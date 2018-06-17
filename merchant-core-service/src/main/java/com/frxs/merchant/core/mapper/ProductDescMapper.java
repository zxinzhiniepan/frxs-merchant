/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.core.mapper;

import com.frxs.framework.data.persistent.SuperMapper;
import com.frxs.merchant.common.dal.entity.ProductDesc;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDescMapper extends SuperMapper<ProductDesc> {

  ProductDesc selectByPrimaryKey(Long productInfoId);

  /**
   * 新增商品描述
   *
   * @param productDesc 商品描述
   * @return 结果
   */
  int insertDesc(ProductDesc productDesc);
}