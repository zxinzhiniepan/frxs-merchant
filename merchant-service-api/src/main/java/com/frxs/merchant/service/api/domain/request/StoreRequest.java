package com.frxs.merchant.service.api.domain.request;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author wushuo
 * @version $Id: StoreRequest.java,v 0.1 2018年03月12日 14:31 $Exp
 */
@Data
public class StoreRequest implements Serializable{
    private static final long serialVersionUID = 6113597616421404611L;

    /**
     * 门店编号
     */
    private String storeCode;

    /**
     * 门店名称
     */
    private String storeName;

    /**
     * 门店开发人员
     */
    private String storeDeveloper;

    /**
     * 门店状态
     */
    private String storeStatus;

    /**
     * 门店创建时间起始日期
     */
    private String tmOnLineStart;

    /**
     * 门店创建时间结束日期
     */
    private String tmOnLineEnd;
}
