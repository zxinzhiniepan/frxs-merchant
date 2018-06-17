package com.frxs.merchant.core.mapper;

import com.frxs.framework.data.persistent.SuperMapper;
import com.frxs.merchant.common.dal.entity.SeqVendor;
import org.springframework.stereotype.Repository;

/**
 * @author wushuo
 * @version $Id: SeqVendorMapper.java,v 0.1 2018年02月27日 10:59 $Exp
 */
@Repository
public interface SeqVendorMapper extends SuperMapper<SeqVendor> {
    //    @Select("select nextval for seq_user")
    Long getNextSeqForVendor();
}
