package com.frxs.merchant.service.api.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author fygu
 * @version $Id: ProductVendorDataImgDto.java,v 0.1 2018年01月31日 18:59 $Exp
 */
@Data
public class ProductVendorDataImgDto implements Serializable {

  /**
   * 供应商商品图片id
   */
  private Long vendorProductImgId;
  /**
   * 供应商商品资料id
   */
  private Long vendorProductDataId;
  /**
   * 800*800原图地址
   */
  private String originalImgUrl;
  /**
   * 60*60图片地址
   */
  private String imgUrl60;
  /**
   * 120*120图片地址
   */
  private String imgUrl120;
  /**
   * 200*200图片地址
   */
  private String imgUrl200;
  /**
   * 400*400图片地址
   */
  private String imgUrl400;

  /**
   * 顺序
   */
  private Integer sortSeq;
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
