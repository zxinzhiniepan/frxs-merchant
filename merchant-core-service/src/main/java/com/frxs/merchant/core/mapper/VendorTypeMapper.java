package com.frxs.merchant.core.mapper;

import com.frxs.framework.data.persistent.SuperMapper;
import com.frxs.merchant.common.dal.entity.VendorType;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


/**
 * 供应商分类MAPPER接口
 *
 * @author wushuo
 * @version $Id: VendorTypeMapper.java,v 0.1 2018年01月31日 10:01 $Exp
 */
@Repository
public interface VendorTypeMapper extends SuperMapper<VendorType>{
    /**
     * 主键ID查询供应商分类信息
     * @param vendorTypeId
     * @return VendorType
     */
    VendorType selectByPrimaryKey(Integer vendorTypeId);

    /**
     *  查询
     * @param vendorType
     * @return
     */
    List<VendorType> getList(VendorType vendorType);

    /**
     *  vendorId查询
     * @param vendorId
     * @param idDeleted
     * @return
     */
    List<VendorType> getListByVendorId(@Param("vendorId")Integer vendorId, @Param("idDeleted")String idDeleted);
}
