/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.core.mapper;

import com.frxs.framework.data.persistent.SuperMapper;
import com.frxs.merchant.common.dal.entity.ProductTempletArea;

public interface ProductTempletAreaMapper extends SuperMapper<ProductTempletArea>{
    ProductTempletArea selectByPrimaryKey(Long templetOpareaId);
}