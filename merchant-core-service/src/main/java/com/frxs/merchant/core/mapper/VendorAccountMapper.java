package com.frxs.merchant.core.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.frxs.merchant.common.dal.entity.Vendor;
import com.frxs.merchant.common.dal.entity.VendorAccount;
import com.frxs.merchant.service.api.dto.VendorDto;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;

@Repository
public interface VendorAccountMapper extends BaseMapper<VendorAccount>{
    VendorAccount selectByPrimaryKey(Long accountId);

    /**
     * 根据vendorId修改状态
     * @param vendor
     */
    void updateStatusByVendorId(@Param("vendor") Vendor vendor);

    /**
     * 根据vendorId批量修改状态
     * @param vendorDto
     */
    void batchUpdateStatus(@Param("vendorDto")VendorDto vendorDto);
}
