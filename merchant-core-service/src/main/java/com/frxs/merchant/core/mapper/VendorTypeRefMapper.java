package com.frxs.merchant.core.mapper;

import com.frxs.framework.data.persistent.SuperMapper;
import com.frxs.merchant.common.dal.entity.VendorType;
import com.frxs.merchant.common.dal.entity.VendorTypeRef;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 供应商分类关联MAPPER
 *
 * @author wushuo
 * @version $Id: VendorTypeServiceImpl.java,v 0.1 2018年01月31日 10:01 $Exp
 */
@Repository
public interface VendorTypeRefMapper extends SuperMapper<VendorTypeRef> {
    /**
     * 主键查询
     * @param id
     * @return
     */
    VendorTypeRef selectByPrimaryKey(Integer id);

    /**
     * 供应商分类删除
     * @param vendorTypeRef
     */
    void deleteByVendorTypeId(VendorTypeRef vendorTypeRef);

    /**
     * 供应商删除
     * @param vendorTypeRef
     */
    void deleteByVendorId(VendorTypeRef vendorTypeRef);

    /**
     * 供应商id查询供应商分类ID
     * @param vendorId
     * @param isDeleted
     * @return List<VendorTypeRef>
     */
    List<Integer> getListByVendorId(@Param("vendorId") Long vendorId,@Param("isDeleted") String isDeleted);

    /**
     * 供应商物理删除
     * @param vendorId
     */
    void delByVendorId(Long vendorId);

    /**
     *
     * @param list
     */
    void addByVendorId(List<VendorTypeRef> list);

    /**
     * 批量删除关联关系
     * @param vendorIds
     * @param isDeleted
     */
    void batchVendorTypeByVendorIds(@Param("vendorIds") List<Long> vendorIds, @Param("isDeleted") String isDeleted);

}
