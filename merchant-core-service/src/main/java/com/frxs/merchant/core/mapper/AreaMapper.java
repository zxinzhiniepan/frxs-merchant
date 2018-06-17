package com.frxs.merchant.core.mapper;

import com.frxs.framework.data.persistent.SuperMapper;
import com.frxs.merchant.common.dal.entity.Area;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 区域MAPPER
 *
 * @author wushuo
 * @version $Id: AreaMapper.java,v 0.1 2018年01月29日 16:23 $Exp
 */
@Repository
public interface AreaMapper extends SuperMapper<Area>{
    /**
     * 根据主键ID查询
     *
     * @param areaId
     * @return Area
     */
    Area selectByPrimaryKey(Integer areaId);

    List<Area> findByIsDelete(String isDelete);

    /**
     *
     * @param vendorId
     * @return List<Area>
     */
    List<Area> getByVendorId(@Param("vendorId") Long vendorId);
}
