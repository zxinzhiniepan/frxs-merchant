/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.core.mapper;

import com.frxs.framework.data.persistent.SuperMapper;
import com.frxs.merchant.common.dal.entity.ProductServiceDetail;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductServiceDetailMapper extends SuperMapper<ProductServiceDetail> {

  ProductServiceDetail selectByPrimaryKey(Long serviceDetailId);

  /**
   * 批量新增
   *
   * @param list 列表
   * @return 结果
   */
  int insertBatch(List<ProductServiceDetail> list);
}