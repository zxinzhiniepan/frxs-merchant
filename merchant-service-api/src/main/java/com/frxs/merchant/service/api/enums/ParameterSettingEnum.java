package com.frxs.merchant.service.api.enums;

import com.frxs.framework.common.enums.BaseEnum;

import java.io.Serializable;

/**
 * @author jiangboxuan
 * @version @version $Id: ParameterSettingEnum.java,v 0.1 2018年02月27日 下午 16:15 $Exp
 */
public enum ParameterSettingEnum implements BaseEnum<String> {

    STORESALESRANKINGS("STORESALESRANKINGS", "门店销量排名"),
    WAREHOUSINGFEE("WAREHOUSINGFEE","仓储费"),
    LOGISTICSFEE("LOGISTICSFEE","物流费"),
    INFORMATIONPLATFORMFEE("INFORMATIONPLATFORMFEE","信息平台费"),
    COMMODITYPROMOTIONFEE("COMMODITYPROMOTIONFEE","商品推广费"),
    XS_VENDOR_CODE("9999999","兴盛社区供应商编号(固定7个9)"),
    STORE_TAX_RATE("STORE_TAX_RATE","门店代交税率%"),
    STORE_ISNEED_TAX("STORE_ISNEED_TAX","启用门店税金(false:不启用;true:启用)"),
    GRAND_TOTAL_COMMISSION("GRAND_TOTAL_COMMISSION","是否显示门店端累计销量"),
    GRAND_TOTAL_SALES("GRAND_TOTAL_SALES","是否显示门店端首页销量排名"),
    STORE_HOME_RANK("STORE_HOME_RANK","是否显示门店端首页销量排名");


    private String value;

    private String desc;

    ParameterSettingEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Serializable getValue() {
        return this.value;
    }

    @Override
    public String getValueDefined() {
        return this.value;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }
}

