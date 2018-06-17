package com.frxs.merchant.service.api.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author fygu
 * @version $Id: ProductVendorDataDto.java,v 0.1 2018年01月29日 10:19 $Exp
 */
@Data
public class ProductVendorDataDto implements Serializable {

  /**
   * 供应商商品资料id
   */
  private Long vendorProductDataId;
  /**
   * 供应商id
   */
  private Long vendorId;
  /**
   * 供应商编码
   */
  private String vendorCode;
  /**
   * 供应商名称
   */
  private String vendorName;
  /**
   * 供应商商品名称
   */
  private String vendorProductName;
  /**
   * 供应商商品简介
   */
  private String vendorProductDesc;
  /**
   * 提交时间
   */
  private Date tmPublish;
  /**
   * 审核状态:DRAFT-草稿,PENDING-待审核,PASS-审核通过,REJECT-驳回
   */
  private String auditStatus;
  /**
   * 审核人id
   */
  private Long auditUserId;
  /**
   * 审核人名
   */
  private String auditUserName;
  /**
   * 驳回原因
   */
  private String auditRession;
  /**
   * 审核时间
   */
  private Date tmAudit;
  /**
   * 商品规格类型:SINGLE-单规格,MULTI-多规格
   */
  private String specType;
  /**
   * 区域后台是否删除:TRUE-已删除,FALSE-未删除
   */
  private String isAreaDel;
  /**
   * 供应商是否删除:TRUE-已删除,FALSE-未删除
   */
  private String isVendorDel;
  /**
   * 创建人id
   */
  private Long createUserId;
  /**
   * 创建人用户名
   */
  private String createUserName;
  /**
   * 修改人id
   */
  private Long modifyUserId;
  /**
   * 修改人用户名
   */
  private String modifyUserName;

  private Date tmCreate;

  private Date tmSmp;

}
