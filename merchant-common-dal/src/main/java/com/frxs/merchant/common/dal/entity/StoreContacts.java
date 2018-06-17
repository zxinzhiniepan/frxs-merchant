package com.frxs.merchant.common.dal.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.frxs.framework.data.persistent.AbstractSuperEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
@TableName("t_store_contacts")
public class StoreContacts extends AbstractSuperEntity<StoreContacts> {

    @TableId
    private Long id;
    /**
     * 门店主键
     */
    private Long storeId;

    private String contactsName;

    private String contactsTel;

    /**
     * 创建用户ID
     */
    private Long createUserId;
    /**
     * 创建用户名称
     */
    private String createUserName;
    /**
     * 最新修改删除ID
     */
    private Long modifyUserId;
    /**
     * 最新修改删除用户名称
     */
    private String modifyUserName;

    private Integer orderNumber;

    public StoreContacts(){
        super();
    }

    public StoreContacts(Long storeId,String contactsName) {
        this.storeId = storeId;
        this.contactsName = contactsName;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
