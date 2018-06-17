package com.frxs.merchant.core.mapper;

import com.frxs.framework.data.persistent.SuperMapper;
import com.frxs.merchant.common.dal.entity.DistributionLine;
import com.frxs.merchant.common.dal.entity.StoreLine;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreLineMapper extends SuperMapper<StoreLine>{

    StoreLine selectByPrimaryKey(Long id);

    boolean updateByStoreId(StoreLine storeLine);

    /**
     * 根据门店id查询线路ids
     * @param storeId
     * @return
     */
    List<Integer> getStoreLineIdsByStoreId(@Param("storeId") Long storeId);

    /**
     * 批量插入门店线路关系
     * @param storeLine
     */
    void batchInsertStoreLine(@Param("storeLine") StoreLine storeLine,@Param("lineIds") List<Integer> lineIds);

    /**
     * 门店线路关联物理删除
     * @param storeId
     */
    void delByStoreId(Long storeId);

    /**
     * 根据线路id查询门店线路关联信息
     * @param distributionLine
     */
    List<StoreLine> listStoreLineByLineId(DistributionLine distributionLine);

}
