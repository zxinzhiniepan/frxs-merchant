package com.frxs.merchant.common.dal.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.frxs.framework.data.persistent.AbstractSuperEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author jiangboxuan
 * @version @version $Id: SetActivityDynamicPrompt.java,v 0.1 2018年01月29日 下午 17:02 $Exp
 */
@Data
@TableName("t_set_activity_dynamic_prompt")
public class SetActivityDynamicPrompt extends AbstractSuperEntity<SetActivityDynamicPrompt> {
    /**
     * 自增编号
     */
    @TableId
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
    private Date tmDisplayStart;
    /**
     * 展示截止时间(yyyy-MM-dd HH:mm:ss)
     */
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

    @Override
    protected Serializable pkVal() {
        return this.dynamicPromptId;
    }
}
