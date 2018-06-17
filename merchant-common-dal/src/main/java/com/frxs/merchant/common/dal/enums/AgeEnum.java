/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2017. All Rights Reserved.
 */

package com.frxs.merchant.common.dal.enums;

import java.io.Serializable;

/**
 *  AgeEnum
 *
 * @author mingbo.tang
 * @version $Id: AgeEnum.java,v 0.1 2017年12月29日 下午 16:43 $Exp
 */
public enum AgeEnum {
    /**
     *  一岁
     */
    ONE("1", "一岁"),
    /**
     *  二岁
     */
    TWO("2", "二岁");

    /**
     *  枚举值
     */
    private String value;
    /**
     *  枚举描述
     */
    private String desc;

    AgeEnum(final String value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Serializable getValue() {
        return this.value;
    }

    public String getValueDefined() {
        return this.value;
    }

    public String getDesc(){
        return this.desc;
    }

    /**
     * 通过枚举<code>value</code>获得枚举
     *
     * @param value value
     * @return AgeEnum
     */
    public static AgeEnum getByValue(String value) {
        for (AgeEnum ageEnum : values()) {
            if (ageEnum.getValue().equals(value)) {
                return ageEnum;
            }
        }
        return null;
    }
}
