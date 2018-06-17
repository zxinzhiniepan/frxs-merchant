/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.core.mapper;

import com.frxs.framework.data.persistent.SuperMapper;
import com.frxs.merchant.common.dal.entity.ProductImg;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImgMapper extends SuperMapper<ProductImg> {

  ProductImg selectByPrimaryKey(Long productImgId);

  /**
   * 批量新增
   *
   * @param list 列表
   * @return 结果
   */
  int insertBatch(List<ProductImg> list);
}