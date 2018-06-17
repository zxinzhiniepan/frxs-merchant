package com.frxs.merchant.core.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.frxs.merchant.common.dal.entity.StoreUserRef;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author wushuo
 * @version $Id: StoreUserRefMapper.java,v 0.1 2018年02月28日 18:34 $Exp
 */
@Repository
public interface StoreUserRefMapper extends BaseMapper<StoreUserRef>{

    /**
     * 主键ID查询
     * @param id
     * @return Store
     */
    StoreUserRef selectByPrimaryKey(Long id);

    /**
     * 多个门店id查询
     * @param storeIds
     * @return
     */
    List<StoreUserRef> getByStoreIds(@Param("storeIds") List<Long> storeIds);
}
