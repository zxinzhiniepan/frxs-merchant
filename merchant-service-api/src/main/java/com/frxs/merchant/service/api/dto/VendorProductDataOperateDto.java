package com.frxs.merchant.service.api.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 * @author fygu
 * @version $Id: VendorProductDataOperateDto.java,v 0.1 2018年02月05日 15:31 $Exp
 */
@Data
public class VendorProductDataOperateDto implements Serializable{

  private List<Long> vendorProductDataIdList;

  private Long operateId;

  private String auditStatus;

  private String operateName;

  private Date operateDate;

  /**
   * 驳回原因
   */
  private String auditRession;

}
