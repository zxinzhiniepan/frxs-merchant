/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.service.test;

import com.alibaba.fastjson.JSON;
import com.frxs.merchant.core.service.ProductService;
import com.frxs.merchant.core.service.ProductVendorDataService;
import com.frxs.merchant.service.MerchantApplication;
import com.frxs.merchant.service.api.dto.ProductAttrvalRelationDto;
import com.frxs.merchant.service.api.dto.ProductDescDto;
import com.frxs.merchant.service.api.dto.ProductDto;
import com.frxs.merchant.service.api.dto.ProductImgDto;
import com.frxs.merchant.service.api.dto.ProductVendorDto;
import com.frxs.merchant.service.api.dto.VendorProductDataOperateDto;
import com.frxs.merchant.service.api.enums.ProductAuditStatusEnum;
import com.frxs.merchant.service.api.enums.ProductImgTypeEnum;
import com.frxs.merchant.service.api.enums.ProductSaleLimitTimeUnitEnum;
import com.frxs.merchant.service.api.result.MerchantResult;
import com.frxs.promotion.service.api.enums.AuditStatusEnum;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 供应商商品测试类
 *
 * @author sh
 * @version @version $Id: DistributionLineApplicationTests.java,v 0.1 2018年01月30日 下午 17:16 $Exp
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MerchantApplication.class)
public class VendorProductTest {

  @Autowired
  private ProductVendorDataService productVendorDataService;

  //@Test
  public void previewProductVendor() {
    MerchantResult<ProductVendorDto> merchantResult = productVendorDataService.previewProductVendor(170L);
    System.err.println("结果：" + JSON.toJSONString(merchantResult));
  }

  //@Test
  public void updateAuditStatus() {
    VendorProductDataOperateDto vendorProductDataOperateDto = new VendorProductDataOperateDto();
    List<Long> list = new ArrayList<>();
    list.add(170L);
    vendorProductDataOperateDto.setVendorProductDataIdList(list);
    vendorProductDataOperateDto.setAuditStatus(ProductAuditStatusEnum.PASS.getValueDefined());
    vendorProductDataOperateDto.setAuditRession("这个是我的理由");
    MerchantResult merchantResult = productVendorDataService.updateAuditStatus(vendorProductDataOperateDto);
    System.err.println("结果：" + JSON.toJSONString(merchantResult));
  }
}
