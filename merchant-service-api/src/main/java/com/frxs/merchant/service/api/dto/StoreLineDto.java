package com.frxs.merchant.service.api.dto;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * @author wushuo
 * @version $Id: StoreLineDto.java,v 0.1 2018年02月05日 19:39 $Exp
 */
@Data
public class StoreLineDto implements Serializable {

    private static final long serialVersionUID = 5806858321494758941L;

    /**
     * 主键id
     */
    private Long id;
    /**
     * 门店ID
     */
    private Long storeId;
    /**
     * 线路ID
     */
    private Integer lineId;
    /**
     * 线路顺序
     */
    private Integer lineSort;

}
