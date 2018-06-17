package com.frxs.merchant.service.api.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 门店联系人DTO
 *
 * @author wushuo
 * @version $Id: StoreDto.java,v 0.1 2018年01月29日 16:23 $Exp
 */
@Data
public class StoreContactsDto implements Serializable{
    private Long id;
    /**
     * 门店主键
     */
    private Long storeId;

    private String contactsName;

    private String contactsTel;

}
