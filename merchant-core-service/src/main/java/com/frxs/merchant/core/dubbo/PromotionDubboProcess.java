/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.core.dubbo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.frxs.merchant.common.dal.entity.Product;
import com.frxs.merchant.common.dal.entity.ProductDesc;
import com.frxs.merchant.common.dal.entity.ProductImg;
import com.frxs.merchant.common.dal.enums.ErrorCodeDetailEnum;
import com.frxs.merchant.core.exception.ProductBizException;
import com.frxs.promotion.service.api.dto.PreproductDto;
import com.frxs.promotion.service.api.dto.consumer.PreproductDetailDto;
import com.frxs.promotion.service.api.facade.ActivityPreproductFacade;
import com.frxs.promotion.service.api.result.PromotionBaseResult;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 * 营销dubbo服务调用
 *
 * @author sh
 * @version $Id: PromotionDubboProcess.java,v 0.1 2018年03月03日 下午 14:04 $Exp
 */
@Component
public class PromotionDubboProcess {

  @Reference(check = false, version = "1.0.0",timeout = 30000)
  private ActivityPreproductFacade activityPreproductFacade;

  /**
   * 校验商品在活动中的配置
   *
   * @param productIds 商品id
   * @return 参与活动的商品列表
   */
  public List<PreproductDto> checkProductInActivity(List<Long> productIds) {

    PromotionBaseResult<List<PreproductDto>> promotionBaseResult = activityPreproductFacade.checkProductInActivity(productIds);
    if (!promotionBaseResult.isSuccess()) {
      throw new ProductBizException(ErrorCodeDetailEnum.PRODUCT_CHECK_ACTIVITY_ERROR, "校验商品是否参加活动失败");
    }
    return promotionBaseResult.getData();
  }

  /**
   * 修改商品详情缓存
   *
   * @param product 商品
   * @param desc 描述
   * @param primaryUrls 主图
   * @param detailUrls 详情图
   */
  public void updateProductDetailCache(Product product, ProductDesc desc, List<ProductImg> primaryUrls, List<ProductImg> detailUrls) {

    PreproductDetailDto detail = new PreproductDetailDto();
    detail.setPrId(product.getProductId());
    detail.setAreaId(product.getAreaId());
    detail.setPrBrief(desc.getBriefDesc());
    detail.setPrDetail(desc.getDetailDesc());
    detail.setShTitle(desc.getShareTitle());
    if (primaryUrls != null) {
      detail.setPrimaryUrls(primaryUrls.stream().map(ProductImg::getOriginalImgUrl).collect(Collectors.toList()));
    }
    if (detailUrls != null) {
      detail.setDetailUrls(detailUrls.stream().map(ProductImg::getOriginalImgUrl).collect(Collectors.toList()));
    }
    PromotionBaseResult promotionBaseResult = activityPreproductFacade.updatePreproductDetailCache(detail);
    if (!promotionBaseResult.isSuccess()) {
      throw new ProductBizException(ErrorCodeDetailEnum.PRODUCT_UPDATE_DETAIL_CACHE_ERROR, "更新商品详情缓存失败");
    }
  }
}
