package com.frxs.merchant.service.api.dto;

import com.frxs.framework.util.common.StringUtil;
import java.io.Serializable;
import lombok.Data;
import lombok.Getter;

/**
 * @author fygu
 * @version $Id: ProductQueryDto.java,v 0.1 2018年01月29日 18:00 $Exp
 */
@Getter
public class ProductQueryDto implements Serializable {

  private String productName;

  private String sku;

  private String brandName;

  /**
   * 商品sku状态:UP-上架，DOWN-下架，DELETED-已删除
   */
  private String skuStatus;

  private Long areaId;

  public void setProductName(String productName) {
    this.productName = productName != null ? StringUtil.trim(productName) : productName;
  }

  public void setSku(String sku) {
    this.sku = sku != null ? StringUtil.trim(sku) : sku;
  }

  public void setBrandName(String brandName) {
    this.brandName = brandName != null ? StringUtil.trim(brandName) : brandName;
  }

  public void setSkuStatus(String skuStatus) {
    this.skuStatus = skuStatus != null ? StringUtil.trim(skuStatus) : skuStatus;
  }

  public void setAreaId(Long areaId) {
    this.areaId = areaId;
  }
}
