package com.frxs.merchant.service.api.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * @author wushuo
 * @version $Id: VendorProFileInfoDto.java,v 0.1 2018年02月27日 16:10 $Exp
 */
@Data
public class VendorProFileInfoDto implements Serializable {

    private static final long serialVersionUID = 4954813487294035480L;

    /**
     * 供应商ID
     */
    private Long vendorId;
    /**
     * 银行户名
     */
    private String bankAccountName;
    /**
     * 银行名称
     */
    private String bankName;
    /**
     * 银行帐号
     */
    private String bankAccountNo;
}
