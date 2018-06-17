package com.frxs.merchant.service.api.domain.request;

import java.io.Serializable;
import lombok.Data;

/**
 * @author wushuo
 * @version $Id: VendorAccountRequest.java,v 0.1 2018年03月16日 15:02 $Exp
 */
@Data
public class VendorAccountRequest implements Serializable {
    private static final long serialVersionUID = 3618904627935341258L;
    /**
     * 账户ID
     */
    private Long accountId;
    /**
     * 账户号
     */
    private String accountNo;
    /**
     * 供应商ID
     */
    private Long vendorId;
    /**
     * 账户名称
     */
    private String accountName;
    /**
     * 绑定手机号码
     */
    private String accountMoile;

    /**
     * 状态值
     */
    private String status;
}
