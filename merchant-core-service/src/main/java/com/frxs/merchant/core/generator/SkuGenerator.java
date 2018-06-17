/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.core.generator;

import com.frxs.framework.data.sequence.MySqlSequence;
import com.frxs.framework.util.common.DateUtil;
import com.frxs.framework.util.common.StringUtil;
import com.frxs.merchant.common.dal.entity.ProductSkuCode;
import com.frxs.merchant.core.mapper.ProductSkuCodeMapper;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * sku生成器
 *
 * @author sh
 * @version $Id: SkuGenerator.java,v 0.1 2018年02月05日 下午 12:43 $Exp
 */
@Component
public class SkuGenerator {

  /**
   * seq_t_product_sku
   */
  private static final String SEQ_T_PRODUCT_SKU = "seq_t_product_sku";

  /**
   * 产品sku业务标识,默认为019
   */
  private static final String PRODUCT_IDENTITY = "019";
  /**
   * 默认用来填充的字符
   */
  private static final String DEFAULT_PADDING_CHAR = "0";

  /**
   * sku默认最大值
   */
  private static final long DEFAULT_PRODUCT_SKU_MAX_NUM = 9999999L;

  @Autowired
  private MySqlSequence mySqlSequence;

  @Autowired
  private ProductSkuCodeMapper productSkuCodeMapper;

  /**
   * 生成16位的SKU:年月日+seq
   *
   * @return sku
   */
  public String generatorSku() throws Exception {

    // 获取当前系统时间
    String nowStr = DateUtil.format(new Date(), "yyMMdd");
    String beforStr = StringUtil.overlay(nowStr, PRODUCT_IDENTITY, 6, 8);
    String sequence = mySqlSequence.getNextValue(SEQ_T_PRODUCT_SKU);
    sequence = StringUtil.alignRight(sequence, 5, DEFAULT_PADDING_CHAR);
    return beforStr + sequence;
  }

  /**
   * 获取商品sku编码，从表中生成的序列号
   */
  public String generatorSkuCode() throws Exception {

    ProductSkuCode skuCode = new ProductSkuCode();
    productSkuCodeMapper.replaceInsertCode(skuCode);
    Long skuId = skuCode.getSkuId();
    String skuIdStr = skuId.toString();
    if (skuId >= DEFAULT_PRODUCT_SKU_MAX_NUM) {
      return skuId.toString();
    } else {
      return StringUtil.alignRight(skuIdStr, 7, DEFAULT_PADDING_CHAR);
    }
  }


}
