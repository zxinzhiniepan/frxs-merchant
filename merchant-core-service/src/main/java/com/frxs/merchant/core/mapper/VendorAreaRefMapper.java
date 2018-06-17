/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.core.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.frxs.merchant.common.dal.entity.VendorAreaRef;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorAreaRefMapper extends BaseMapper<VendorAreaRef>{

    VendorAreaRef selectByPrimaryKey(Long id);

    /**
     * 添加供应商区域关系
     * @param list
     */
    void saveVendorArea(@Param("list") List<VendorAreaRef> list);

    /**
     * 删除供应商区域关系
     * @param vendorId
     */
    void deleteVendorArea(@Param("vendorId") Long vendorId);

    /**
     * 批量修改状态
     * @param vendorAreaRefIds
     * @param isDeleted
     */
    void batchUpdateVendorAreaRefStatus(@Param("vendorAreaRefIds") List<Long> vendorAreaRefIds, @Param("isDeleted") String isDeleted);

    /**
     * 批量修改
     * @param vendorAreaRefs
     */
    void batchUpdateVendorAreaRef(@Param("vendorAreaRefs") List<VendorAreaRef> vendorAreaRefs);
}
