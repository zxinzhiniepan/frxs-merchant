package com.frxs.merchant.service.api.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * @author wushuo
 * @version $Id: VendorTypeDto.java,v 0.1 2018年01月30日 18:42 $Exp
 */
@Data
public class VendorTypeDto implements Serializable{

    private static final long serialVersionUID = -3470592524056711224L;

    /**
     * 供应商类型ID
     */
    private Integer vendorTypeId;
    /**
     * 供应商分类名称
     */
    private String vendorTypeName;
    /**
     * 是否删除
     */
    private String isDeleted;
    /**
     * 创建用户ID
     */
    private Long createUserId;
    /**
     * 创建用户名称
     */
    private String createUserName;
    /**
     * 最后修改用户ID
     */
    private Long modifyUserId;
    /**
     * 最后修改用户名称
     */
    private String modifyUserName;
}
