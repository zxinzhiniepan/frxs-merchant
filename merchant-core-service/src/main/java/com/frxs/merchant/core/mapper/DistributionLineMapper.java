package com.frxs.merchant.core.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.frxs.framework.data.persistent.SuperMapper;
import com.frxs.merchant.common.dal.entity.DistributionLine;
import com.frxs.merchant.service.api.dto.DistributionLineDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

/*@Mapper
@Component(value = "distributionLineMapper")*/
@Repository
public interface DistributionLineMapper extends SuperMapper<DistributionLine> {

    /**
     * 配送路线详情
     *
     * @param id
     * @return com.frxs.merchant.common.dal.entity.DistributionLine
     */
    DistributionLine selectByPrimaryKey(Integer id);

    /**
     * 配送路线列表展示
     *
     * @param distributionLineDto 配送路线
     * @return List<DistributionLine>
     */
    List<DistributionLine> listDistributionLine(Page<DistributionLine> pageObject,DistributionLineDto distributionLineDto);

    /**
     * 全部配送路线信息
     *
     * @param distributionLine 配送路线
     * @return List<DistributionLine>
     */
    List<DistributionLine> listAll(DistributionLine distributionLine);

    /**
     * 全部配送路线数量
     *
     * @param distributionLineDto 配送路线
     * @return count
     */
    Integer getCount(DistributionLineDto distributionLineDto);

    /**
     * 批量删除
     *
     * @param ids id的list
     * @return
     */
    void updateByIds(@Param("ids") List<Integer> ids, @Param("status") String status);

    /**
     * 通过仓库id查询配送路线信息
     *
     * @param ids id的list
     * @return List<DistributionLine>
     */
    List<DistributionLine> listDistributionLineByWarehouseIds(@Param("ids") List<Integer> ids);

    /**
     * 门店Id查询线路
     * @param storeId
     * @return
     */
    DistributionLine getDistributionLineByStoreId(@Param("storeId") Long storeId);
}
