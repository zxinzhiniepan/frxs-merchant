package com.frxs.merchant.service.api.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author jiangboxuan
 * @version @version $Id: SetActivityDynamicPromptDto.java,v 0.1 2018年01月29日 下午 16:43 $Exp
 */
@Data
public class SetActivityDynamicPromptDto implements Serializable {


    private static final long serialVersionUID = 3984139275973114138L;

    /**
     * 自增编号
     */
    private Integer dynamicPromptId;
    /**
     * 名称
     */
    private String dynamicPromptName;
    /**
     * 提示内容
     */
    private String content;
    /**
     * 展示开始时间(yyyy-MM-dd HH:mm:ss)
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date tmDisplayStart;
    /**
     * 展示截止时间(yyyy-MM-dd HH:mm:ss)
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date tmDisplayEnd;
    /**
     * 温馨提示
     */
    private String reminder;
    /**
     * 启用状态(0:关闭;1:启动)
     */
    private String status;
    /**
     * 创建用户ID
     */
    private Long createUserId;
    /**
     * 创建用户名称
     */
    private String createUserName;
    /**
     * 最新修改删除ID
     */
    private Long modifyUserId;
    /**
     * 最新修改删除用户名称
     */
    private String modifyUserName;
    /**
     * 操作区域ID
     */
    private Integer areaId;
    /**
     * 创建时间
     */
    private Date tmCreate;
    /**
     * 最新修改删除时间
     */
    private Date tmSmp;

    private String ids;
}
