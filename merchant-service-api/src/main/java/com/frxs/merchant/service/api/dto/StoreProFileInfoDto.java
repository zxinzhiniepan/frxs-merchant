package com.frxs.merchant.service.api.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 门店DTO
 *
 * @author wushuo
 * @version $Id: StoreDto.java,v 0.1 2018年01月29日 16:23 $Exp
 */
@Data
public class StoreProFileInfoDto implements Serializable{
    /**
     * 银行户名
     */
    private String bankAccountName;
    /**
     * 银行名称
     */
    private String bankName;
    /**
     * 银行账号
     */
    private String bankAccountNo;
}
