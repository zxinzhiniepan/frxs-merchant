package com.frxs.merchant.service.api.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * 行政区域DTO
 *
 * @author wushuo
 * @version $Id: OrgAreaDto.java,v 0.1 2018年01月30日 11:50 $Exp
 */

@Data
public class OrgAreaDto implements Serializable{

    private static final long serialVersionUID = -1324532042046211576L;
    /**
     * 区域编号
     */
    private Integer orgAreaId;
    /**
     * 区域名称
     */
    private String orgAreaName;
    /**
     * 全称
     */
    private String orgAreaFullName;
    /**
     * 层级
     */
    private Integer level;
    /**
     * 上级编号
     */
    private Integer parentId;
    /**
     * 排序
     */
    private Integer SortId;
    /**
     * 最后更新用户ID
     */
    private Long modifyUserId;
    /**
     * 最后更新用户名称
     */
    private String modifyUserName;
    /**
     * 创建用户ID
     */
    private Long createUserId;
    /**
     * 创建用户名称
     */
    private String createUserName;
}
