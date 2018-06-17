package com.frxs.merchant.service.api.dto;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * 供应商商品
 *
 * @author fygu
 * @version $Id: ProductVendorDto.java,v 0.1 2018年01月31日 18:57 $Exp
 */
@Data
public class ProductVendorDto implements Serializable{


  private ProductVendorDataDto prodVendorData;

  /**
   * 规格
   */
  private List<ProductVendorDataAttrDto> attrs;

  /**
   * 图片
   */
  private List<ProductVendorDataImgDto> imgs;

}
