package com.frxs.merchant.service.api.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 * @author wushuo
 * @version $Id: VendorAccountDto.java,v 0.1 2018年02月27日 15:20 $Exp
 */
@Data
public class VendorAccountDto implements Serializable{

    private static final long serialVersionUID = -690221750876102636L;

    /**
     * 账户ID
     */
    private Long accountId;
    /**
     * 账户号
     */
    private String accountNo;
    /**
     * 供应商ID
     */
    private Long vendorId;
    /**
     * 账户名称
     */
    private String accountName;
    /**
     * 绑定手机号码
     */
    private String accountMoile;
    /**
     * 开放编号
     */
    private String openId;
    /**
     * 开放类型
     */
    private Integer openType;
    /**
     * 微信号
     */
    private String weChatNo;
    /**
     * 微信昵称
     */
    private String nickName;
    /**
     * 微信头像
     */
    private String headImage;
    /**
     * 密码
     */
    private String pwd;
    /**
     * MD5加密盐值
     */
    private String pwdSalt;
    /**
     * 状态：（正常：normal 冻结：frozen 删除：delete）
     */
    private String status;
    /**
     * 微信是否已登录
     */
    private String wechatLoginStatus;
    /**
     * 最后登录时间
     */
    private Date tmLastLogin;
    /**
     * 最后修改密码时间
     */
    private Date tmLastModifyPwd;
    /**
     * 创建用户 ID
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

    /**
     * 供应商Ids
     */
    private List<Long> vendorIds;
}
