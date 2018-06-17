package com.frxs.merchant.common.dal.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.frxs.framework.data.persistent.AbstractSuperEntity;
import java.io.Serializable;
import lombok.Data;

/**
 * table name:  t_product_vendor_data_img
 * author name: sh
 * create time: 2018-01-25 19:50:51
 */
@Data
@TableName("t_product_vendor_data_img")
public class ProductVendorDataImg extends AbstractSuperEntity<ProductVendorDataImg> {

  /**
   * 供应商商品图片id
   */
  @TableId
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

  @Override
  protected Serializable pkVal() {
    return this.vendorProductImgId;
  }
}
