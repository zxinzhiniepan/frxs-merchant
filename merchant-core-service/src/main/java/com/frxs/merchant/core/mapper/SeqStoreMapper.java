package com.frxs.merchant.core.mapper;

import com.frxs.framework.data.persistent.SuperMapper;
import com.frxs.merchant.common.dal.entity.SeqStore;
import org.springframework.stereotype.Repository;

/**
 * @author wushuo
 * @version $Id: SeqStoreMapper.java,v 0.1 2018年02月27日 10:58 $Exp
 */
@Repository
public interface SeqStoreMapper extends SuperMapper<SeqStore> {
    //    @Select("select nextval for seq_user")
    Long getNextSeqForStore();
}
