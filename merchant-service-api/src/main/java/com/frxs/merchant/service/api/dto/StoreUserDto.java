package com.frxs.merchant.service.api.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * @author wushuo
 * @version $Id: StoreUserDto.java,v 0.1 2018年03月24日 11:08 $Exp
 */
@Data
public class StoreUserDto implements Serializable{

    private static final long serialVersionUID = 437174696587481837L;
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
}
