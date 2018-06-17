package com.frxs.merchant.common.dal.enums;

import lombok.Getter;

/**
 * @author Cukor.fu
 * @version $Id: CacheKeyEnum.java,v 0.1 2018年02月02日 下午 18:52 $Exp
 */
@Getter
public enum CacheKeyEnum {

    STORE("STORE", "store信息"),
    STOREUSER("STOREUSER", "storeUser信息"),
    STORELINE("STORELINE", "storeLine信息"),
    VENDORACCOUNT("VENDORACCOUNT","vendorAccount信息"),
    VENDOR("VENDOR","vendor信息"),
    AREA("AREA","area信息"),
    PARAMETERSETTING("PARAMETERSETTING", "parameterSetting信息");
    /**
     * 前缀
     */
    private String prefix;
    /**
     * 说明
     */
    private String desc;

    private CacheKeyEnum(String prefix, String desc) {
        this.prefix = prefix;
        this.desc = desc;
    }

}
