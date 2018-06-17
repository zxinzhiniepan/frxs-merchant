package com.frxs.merchant.service.api.domain.request;

import java.io.Serializable;
import lombok.Data;

/**
 * 保存门店线路数据
 * @author wushuo
 * @version $Id: StoreLineRequest.java,v 0.1 2018年03月07日 14:22 $Exp
 */
@Data
public class StoreLineRequest implements Serializable{
    private static final long serialVersionUID = 5499704649992844586L;
    /**
     * 门店Id
     */
    private Long storeId;
    /**
     * 仓库Id
     */
    private Integer warehouseId;
    /**
     * 仓库名称
     */
    private String warehouseName;
    /**
     * 线路Id
     */
    private Integer lineId;
    /**
     * 线路顺序
     */
    private Integer lineSort;
}
