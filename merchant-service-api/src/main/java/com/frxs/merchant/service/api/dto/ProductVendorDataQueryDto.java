package com.frxs.merchant.service.api.dto;

import com.frxs.framework.util.common.StringUtil;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author fygu
 * @version $Id: ProductVendorDataQueryDto.java,v 0.1 2018年02月05日 19:17 $Exp
 */
@Getter
public class ProductVendorDataQueryDto implements Serializable {

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
   * 提交开始时间
   */
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date tmPublishStart;

  /**
   * 提交结束时间
   */
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date tmPublishEnd;

  /**
   * 审核状态
   */
  private String auditStatus;

  /**
   * 区域id
   */
  private Long areaId;

  public void setVendorCode(String vendorCode) {
    this.vendorCode = vendorCode != null ? StringUtil.trim(vendorCode) : vendorCode;
  }

  public void setVendorName(String vendorName) {
    this.vendorName = vendorName != null ? StringUtil.trim(vendorName) : vendorName;
  }

  public void setVendorProductName(String vendorProductName) {
    this.vendorProductName = vendorProductName != null ? StringUtil.trim(vendorProductName) : vendorProductName;
  }

  public void setTmPublishStart(Date tmPublishStart) {
    this.tmPublishStart = tmPublishStart;
  }

  public void setTmPublishEnd(Date tmPublishEnd) {
    this.tmPublishEnd = tmPublishEnd;
  }

  public void setAuditStatus(String auditStatus) {
    this.auditStatus = auditStatus != null ? StringUtil.trim(auditStatus) : auditStatus;
  }

  public void setAreaId(Long areaId) {
    this.areaId = areaId;
  }
}
