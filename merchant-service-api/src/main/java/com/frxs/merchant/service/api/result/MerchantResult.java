/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.service.api.result;

import com.frxs.framework.common.domain.BaseResult;
import lombok.Data;

/**
 * 商品dubbo通用返回结果
 *
 * @author sh
 * @version $Id: MerchantResult.java,v 0.1 2018年01月29日 上午 11:49 $Exp
 */
@Data
public class MerchantResult<T> extends BaseResult {

  private static final long serialVersionUID = 7686250624043643826L;

  private T data;
}
