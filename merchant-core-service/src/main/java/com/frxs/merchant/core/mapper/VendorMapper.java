package com.frxs.merchant.core.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.frxs.framework.data.persistent.SuperMapper;
import com.frxs.merchant.common.dal.entity.Vendor;
import com.frxs.merchant.common.dal.entity.VendorQy;
import com.frxs.merchant.service.api.domain.request.VendorPageRequest;
import com.frxs.merchant.service.api.domain.request.VendorRequest;
import com.frxs.merchant.service.api.dto.VendorDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 供应商MAPPER
 *
 * @author wushuo
 * @version $Id: VendorMapper.java,v 0.1 2018年01月29日 16:23 $Exp
 */
@Repository
public interface VendorMapper extends SuperMapper<Vendor> {
    /**
     * 主键ID查询供应商信息
     * @param vendorId
     * @return Vendor
     */
    Vendor selectByPrimaryKey(@Param("vendorId") Long vendorId);

    /**
     * 分页
     * @param page
     * @param vendorPageRequest
     * @return
     */
    List<VendorQy> getPageList(Page<Vendor> page,@Param("vendorPageRequest") VendorPageRequest vendorPageRequest);

    /**
     * 无分页
     * @param vendorPageRequest
     * @return
     */
    List<VendorQy> getList(@Param("vendorPageRequest") VendorPageRequest vendorPageRequest);

    Integer getCount(@Param("vendorPageRequest") VendorPageRequest vendorPageRequest);

    /**
     * 批量更新状态
     * @param vendorDto
     */
    void batchUpdateStatus(@Param("vendorDto")VendorDto vendorDto);

    /**
     * 根据vnedorIds批量查询
     * @param vendorIds
     */
    List<Vendor> getBatchVendorIds(@Param("vendorIds")List<Long> vendorIds);


    /**
     * 根据unionPayMIDs批量查询
     * @param unionPayMIDs
     */
    List<Vendor> getBatchUnionPayMID(@Param("unionPayMIDs")List<String> unionPayMIDs);
    List<Vendor> getBatchUnionPayCID(@Param("unionPayCIDs")List<String> unionPayCIDs);
    /**
     * 依据供应商编号取得供应商对象;
     * @param vendorCode
     * @return
     */
    Vendor getBatchVendorByVendorCode(@Param("vendorCode") String vendorCode);

    /**
     * request条件查询
     * @param vendorRequest
     * @return
     */
    List<Vendor> getVendorListByVendorRequest(@Param("vendorRequest") VendorRequest vendorRequest);

}
