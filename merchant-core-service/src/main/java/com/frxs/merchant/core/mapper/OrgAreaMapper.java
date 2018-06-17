package com.frxs.merchant.core.mapper;

import com.frxs.framework.data.persistent.SuperMapper;
import com.frxs.merchant.common.dal.entity.OrgArea;
import java.util.List;
import org.springframework.stereotype.Repository;


/**
 * 行政区域MAPPER
 *
 * @author wushuo
 * @version $Id: AreaMapper.java,v 0.1 2018年01月30日 16:23 $Exp
 */
@Repository
public interface OrgAreaMapper extends SuperMapper<OrgArea>{
    /**
     * 根据主键ID查询
     * @param orgAreaId
     * @return
     */
    OrgArea selectByPrimaryKey(Integer orgAreaId);

    List<OrgArea> getByParentId(Integer parentId);
}
