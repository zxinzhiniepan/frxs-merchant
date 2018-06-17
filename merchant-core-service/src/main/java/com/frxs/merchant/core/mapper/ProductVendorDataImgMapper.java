/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.core.mapper;

import com.frxs.framework.data.persistent.SuperMapper;
import com.frxs.merchant.common.dal.entity.ProductVendorDataImg;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductVendorDataImgMapper extends SuperMapper<ProductVendorDataImg>{

    ProductVendorDataImg selectByPrimaryKey(Long vendorProductImg);


    List<ProductVendorDataImg> selectVendorDataImgByCondition(@Param("vendorId")Long vendorId,@Param("auditStatus")boolean auditStatus,
        @Param("vendorProductDataId")Long vendorProductDataId);
}