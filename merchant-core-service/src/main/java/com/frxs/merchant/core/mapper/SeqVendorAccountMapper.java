package com.frxs.merchant.core.mapper;

import com.frxs.framework.data.persistent.SuperMapper;
import com.frxs.merchant.common.dal.entity.SeqVendorAccount;
import org.springframework.stereotype.Repository;

/**
 * @author wushuo
 * @version $Id: SeqVendorAccountMapper.java,v 0.1 2018年02月27日 10:59 $Exp
 */
@Repository
public interface SeqVendorAccountMapper extends SuperMapper<SeqVendorAccount> {
    //    @Select("select nextval for seq_user")
    Long getNextSeqForVendorAccount();
}
