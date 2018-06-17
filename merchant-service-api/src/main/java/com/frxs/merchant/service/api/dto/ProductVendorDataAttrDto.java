package com.frxs.merchant.service.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * @author fygu
 * @version $Id: ProductVendorDataAttrDto.java,v 0.1 2018年01月31日 18:58 $Exp
 */
@Data
public class ProductVendorDataAttrDto implements Serializable {

  /**
   * 供应商商品属性id
   */
  private Long vendorProductAttrId;
  /**
   * 供应商商品资料id
   */
  private Long vendorProductDataId;
  /**
   * 商品规格
   */
  private String vendorProductAttr;
  /**
   * 商品价格
   */
  private BigDecimal vendorProductAmt;
  /**
   * 商品数量
   */
  private BigDecimal vendorProductQty;
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
