/*
 * frxs Inc.  兴盛社区网络服务股份有限公司
 * Copyright (c) 2017-2017 All Rights Reserved.
 */
package com.frxs.merchant.service;

import com.frxs.framework.util.common.log4j.LogUtil;
import com.frxs.merchant.common.dal.enums.IsDeleteEnum;
import com.frxs.merchant.core.service.impl.AreaServiceImpl;
import com.frxs.user.service.api.enums.NormalStatusEnum;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MerchantApplication 启动入口
 *
 * @author colby.liu
 * @version $Id: BaseEnum.java,v 0.1 2017年12月25日 11:34 $Exp
 */
@EnableTransactionManagement
@MapperScan("com.frxs.merchant.core.mapper")
@SpringBootApplication(scanBasePackages = {"com.frxs.framework", "com.frxs.merchant"})
public class MerchantApplication {

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(MerchantApplication.class);
    app.setBannerMode(Banner.Mode.OFF);
    ConfigurableApplicationContext context = app.run(args);
    context.addApplicationListener(new ApplicationPidFileWriter());
//        StoreUserServiceImpl storeUserService = context.getBean(StoreUserServiceImpl.class);
//        storeUserService.saveStoreUser(new Store());
//        StoreContactsServiceImpl storeContactsService = context.getBean(StoreContactsServiceImpl.class);
//        StoreContactsDto storeContactsDto = new StoreContactsDto();
//        storeContactsDto.setStoreId(66880000000108L);
//        storeContactsDto.setContactsName("aiaiai");
//        storeContactsDto.setContactsTel("456456456");
//        storeContactsService.saveStoreContacts(storeContactsDto);
//
//        MerchantResult result = storeContactsService.getContactsByStoreId(66880000000108L);
//        MerchantResult result = storeContactsService.getContactsLikeNameOrTel("da");
//    AreaServiceImpl areaService = context.getBean(AreaServiceImpl.class);
//    areaService.getList(IsDeleteEnum.IS_DELETE_N.getValueDefined());
    LogUtil.info("MerchantApplication 启动成功");
  }
}