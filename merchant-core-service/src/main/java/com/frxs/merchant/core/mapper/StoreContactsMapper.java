package com.frxs.merchant.core.mapper;

import com.frxs.framework.data.persistent.SuperMapper;
import com.frxs.merchant.common.dal.entity.StoreContacts;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Cukor.fu
 * @version $Id: StoreContactsMapper.java,v 0.1 2018年03月14日 上午 11:16 $Exp
 */
@Repository
public interface StoreContactsMapper extends SuperMapper<StoreContacts> {

    List<StoreContacts> getContactsByStoreId(Long storeId);

    List<StoreContacts> getContactsLikeNameOrTel(String value);
}
