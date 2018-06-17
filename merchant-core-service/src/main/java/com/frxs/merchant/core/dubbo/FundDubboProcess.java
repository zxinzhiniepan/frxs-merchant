package com.frxs.merchant.core.dubbo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.frxs.fund.service.api.domain.dto.store.StoreBalanceDto;
import com.frxs.fund.service.api.domain.dto.vendor.VendorBalanceDto;
import com.frxs.fund.service.api.domain.result.BalanceProcessResult;
import com.frxs.fund.service.api.facade.balance.FundProcessFacade;
import com.frxs.merchant.common.dal.enums.ErrorCodeDetailEnum;
import com.frxs.merchant.core.exception.BaseMerchantException;
import org.springframework.stereotype.Component;

/**
 * 资金DUBBO服务调用
 *
 * @author wushuo
 * @version $Id: FundDubboProcess.java,v 0.1 2018年03月06日 20:16 $Exp
 */
@Component
public class FundDubboProcess {

  @Reference(check = false, version = "1.0.0",timeout = 30000)
  private FundProcessFacade fundProcessFacade;

  public void addStoreBalance(StoreBalanceDto storeBalanceDto) {
    BalanceProcessResult balanceProcessResult =
        fundProcessFacade.addStoreBalance(storeBalanceDto);
    if (!balanceProcessResult.isSuccess()) {
      throw new BaseMerchantException(ErrorCodeDetailEnum.STORE_SAVE_ERROR, "保存门店的资金账户信息失败");
    }
  }

  public void addVendorBalance(VendorBalanceDto vendorBalanceDto) {
    BalanceProcessResult balanceProcessResult =
        fundProcessFacade.addVendorBalance(vendorBalanceDto);
    if (!balanceProcessResult.isSuccess()) {
      throw new BaseMerchantException(ErrorCodeDetailEnum.VENDOR_SAVE_ERROR, "保存供应商的资金账户信息失败");
    }
  }
}
