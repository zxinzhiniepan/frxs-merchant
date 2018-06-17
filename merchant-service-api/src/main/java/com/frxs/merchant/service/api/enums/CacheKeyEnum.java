package com.frxs.merchant.service.api.enums;

import lombok.Getter;

/**
 * @author Cukor.fu
 * @version $Id: CacheKeyEnum.java,v 0.1 2018年02月02日 下午 18:52 $Exp
 */
@Getter
public enum CacheKeyEnum {

    SOTRE("SOTRE", "门店信息"),
    TOKEN("TOKEN", "用户token信息"),;
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
