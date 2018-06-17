/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.core.mapper;

import com.frxs.framework.data.persistent.SuperMapper;
import com.frxs.merchant.common.dal.entity.ProductSkuCode;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSkuCodeMapper extends SuperMapper<ProductSkuCode> {

  /**
   * 替换新增商品编码
   *
   * @param skuCode 商品sku编码
   */
  void replaceInsertCode(ProductSkuCode skuCode);
}