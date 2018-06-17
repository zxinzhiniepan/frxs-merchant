package com.frxs.merchant.common.dal.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.frxs.framework.data.persistent.AbstractSuperEntity;
import java.io.Serializable;
import lombok.Data;

/**
 * table name:  t_product_sku_code
 * author name: sh
 * create time: 2018-01-25 19:50:51
 */
@Data
@TableName("t_product_sku_code")
public class ProductSkuCode extends AbstractSuperEntity<ProductSkuCode> {

  /**
   * skuId
   */
  @TableId
  private Long skuId;
  /**
   * 固定值:0
   */
  private Integer status;

  @Override
  protected Serializable pkVal() {
    return this.skuId;
  }
}
