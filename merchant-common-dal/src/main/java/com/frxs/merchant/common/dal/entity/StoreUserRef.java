package com.frxs.merchant.common.dal.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.frxs.framework.data.persistent.AbstractSuperEntity;
import java.io.Serializable;
import lombok.Data;

/**
 * @author wushuo
 * @version $Id: StoreUserRef.java,v 0.1 2018年02月28日 18:30 $Exp
 */
@Data
@TableName("t_store_user_ref")
public class StoreUserRef extends AbstractSuperEntity<StoreUserRef> {

    /**
     * 主键id
     */
    @TableId
    private Long id;
    /**
     * 用户Id
     */
    private Long userId;
    /**
     * 门店Id
     */
    private Long storeId;
    /**
     * 账号
     */
    private String userName;
    /**
     * 是否删除
     */
    private String isDeleted;

    @Override protected Serializable pkVal() {
        return this.id;
    }
}
