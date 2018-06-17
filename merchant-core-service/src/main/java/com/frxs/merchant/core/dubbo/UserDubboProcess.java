/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.core.dubbo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.frxs.merchant.common.dal.enums.ErrorCodeDetailEnum;
import com.frxs.merchant.core.exception.BaseMerchantException;
import com.frxs.user.service.api.dto.UserDto;
import com.frxs.user.service.api.facade.UserFacade;
import com.frxs.user.service.api.result.UserResult;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户dubbo服务调用
 *
 * @author wushuo
 * @version $Id: UserDubboProcess.java,v 0.1 2018年03月03日 下午 14:04 $Exp
 */
@Component
public class UserDubboProcess {

  //@Reference(check = false, url = "dubbo://192.168.8.26:8211")
  @Reference(check = false, version = "1.0.0",timeout = 30000)
  private UserFacade userFacade;

  public UserDto saveUser(UserDto userDto) {
    UserResult userResult = userFacade.saveUser(userDto);
    if (!userResult.isSuccess()) {
      throw new BaseMerchantException(ErrorCodeDetailEnum.STORE_SAVE_ERROR, "保存门店的用户信息失败");
    }
    return (UserDto) userResult.getData();
  }

  public UserDto updateUser(UserDto userDto) {
    UserResult userResult = userFacade.update(userDto);
    if (!userResult.isSuccess()) {
      throw new BaseMerchantException(ErrorCodeDetailEnum.STORE_UPDATE_ERROR, "修改门店的用户信息失败");
    }
    return (UserDto) userResult.getData();
  }

  public UserResult batchUpdateStatus(List<Long> userIds, String status) {
    UserResult userResult = userFacade.batchUpdateStatus(userIds, status);
    if (!userResult.isSuccess()) {
      throw new BaseMerchantException(ErrorCodeDetailEnum.STORE_UPDATE_ERROR, "批量修改门店的用户信息失败");
    }
    return userResult;
  }

}
