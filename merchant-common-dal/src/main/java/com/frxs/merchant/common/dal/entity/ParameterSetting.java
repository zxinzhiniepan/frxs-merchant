package com.frxs.merchant.common.dal.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.frxs.framework.data.persistent.AbstractSuperEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author jiangboxuan
 * @version @version $Id: ParameterSetting.java,v 0.1 2018年01月31日 下午 14:49 $Exp
 */
@Data
@TableName("t_parameter_setting")
public class ParameterSetting  extends AbstractSuperEntity<ParameterSetting> {
    /**
     * 系统参数id
     */
    @TableId
    private Integer id;
    /**
     * 参数编码
     */
    private String paraCode;
    /**
     * 参数名称
     */
    private String paraName;
    /**
     * 参数值
     */
    private String paraValue;
    /**
     * 备注
     */
    private String paraDescription;
    /**
     * 排序
     */
    private Integer sort;
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
     * 操作区域名称
     */
    private String areaName;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
