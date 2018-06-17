package com.frxs.merchant.core.mapper;

import com.frxs.framework.data.persistent.SuperMapper;
import com.frxs.merchant.common.dal.entity.OrgArea;
import com.frxs.merchant.common.dal.entity.OrgAreaView;
import com.frxs.merchant.service.api.dto.OrgAreaViewDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 行政区域MAPPER
 *
 * @author wushuo
 * @version $Id: AreaMapper.java,v 0.1 2018年01月30日 16:23 $Exp
 */
@Repository
public interface OrgAreaViewMapper extends SuperMapper<OrgAreaView>{

    /**
     * 根据行政区域名称查询行政区域视图
     * @param orgAreaView
     * @return
     */
    OrgAreaView getByOrgAreaName(OrgAreaView orgAreaView);
}
