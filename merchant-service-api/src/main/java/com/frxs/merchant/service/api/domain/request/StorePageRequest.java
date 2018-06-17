package com.frxs.merchant.service.api.domain.request;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * @author wushuo
 * @version $Id: StorePageRequest.java,v 0.1 2018年03月14日 16:05 $Exp
 */
@Data
public class StorePageRequest implements Serializable{
    private static final long serialVersionUID = -3845172131351990006L;
    /**
     * 当前页
     */
    private Integer page;
    /**
     * 条数
     */
    private Integer rows;
    /**
     * 门店编码
     */
    private String storeCode;
    /**
     * 门店名称
     */
    private String storeName;
    /**
     * 门店账号
     */
    private String userName;
    /**
     * 状态
     */
    private String storeStatus;
    /**
     * 区域ID
     */
    private Integer areaId;
    /**
     * 联系人
     */
    private String contacts;
    /**
     * 仓库Id
     */
    private Integer warehouseId;
    /**
     * 线路Id
     */
    private Integer lineId;
    /**
     * 多次下单数
     */
    private Integer userOrderNumber;

    /**
     * 省
     */
    private Integer provinceId;
    /**
     * 市
     */
    private Integer cityId;
    /**
     * 区
     */
    private Integer countyId;

    /**
     * 仓库Ids
     */
    private List<Integer> listWarehouse;
}
