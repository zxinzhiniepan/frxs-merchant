package com.frxs.merchant.service.api.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * @author wushuo
 * @version $Id: AreaInfoDto.java,v 0.1 2018年03月01日 20:21 $Exp
 */
@Data
public class AreaInfoDto implements Serializable{

    private static final long serialVersionUID = -3932648545637654648L;
    /**
     * 区域ID
     */
    private Integer areaId;
    /**
     * 区域名称
     */
    private String areaName;
}
