/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.core.exception;

import com.frxs.merchant.common.dal.enums.ErrorCodeDetailEnum;
import java.io.Serializable;

/**
 * 商品业务异常
 *
 * @author sh
 * @version $Id: TransactionDemoException.java,v 0.1 2018年01月10日 下午 16:49 $Exp
 */
public class ProductBizException extends BaseMerchantException implements Serializable {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = -7156880418215410261L;

  /**
   * 创建一个<code>ProductBizException</code>
   *
   * @param code 错误码
   */
  public ProductBizException(ErrorCodeDetailEnum code) {
    super(code);
  }

  /**
   * 创建一个<code>ProductBizException</code>
   *
   * @param code 错误码
   * @param errorMessage 错误描述
   */
  public ProductBizException(ErrorCodeDetailEnum code, String errorMessage) {
    super(code, errorMessage);
  }
}
