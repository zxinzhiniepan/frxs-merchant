/*
 * frxs Inc.  兴盛社区网络服务股份有限公司.
 * Copyright (c) 2017-2018. All Rights Reserved.
 */

package com.frxs.merchant.service.test;

import com.alibaba.fastjson.JSON;
import com.frxs.merchant.common.dal.entity.OpLog;
import com.frxs.merchant.core.service.OpLogService;
import com.frxs.merchant.core.service.ProductService;
import com.frxs.merchant.service.MerchantApplication;
import com.frxs.merchant.service.api.dto.OpLogDto;
import com.frxs.merchant.service.api.dto.ProductAttrvalRelationDto;
import com.frxs.merchant.service.api.dto.ProductDescDto;
import com.frxs.merchant.service.api.dto.ProductDto;
import com.frxs.merchant.service.api.dto.ProductImgDto;
import com.frxs.merchant.service.api.enums.ProductImgTypeEnum;
import com.frxs.merchant.service.api.enums.ProductSaleLimitTimeUnitEnum;
import com.frxs.merchant.service.api.result.MerchantResult;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 商品测试类
 *
 * @author sh
 * @version @version $Id: DistributionLineApplicationTests.java,v 0.1 2018年01月30日 下午 17:16 $Exp
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MerchantApplication.class)
public class ProductTest {

  @Autowired
  private ProductService productService;

  @Autowired
  private OpLogService opLogService;

  //@Test
  public void createLog() {
    OpLogDto opLog = new OpLogDto();
    opLog.setAreaId(101);
    opLog.setAction("测试");
    opLog.setMenuId("1");
    opLog.setMenuName("测试");
    opLog.setOperatorId(1);
    opLog.setOperatorName("test");
    opLog.setRemark("test");
    MerchantResult merchantResult = opLogService.createLog(opLog);
    System.err.println("结果：" + JSON.toJSONString(merchantResult));
  }

  //@Test
  public void createSingleProduct() {

    ProductDto product = new ProductDto();
    product.setAreaId(101L);
    product.setProductName("商品名称1");
    product.setProductTitle("商品标题1");
    product.setBrandName("好品牌");
    product.setLimitQty(new BigDecimal("3"));
    product.setVendorId(1L);
    product.setSaleAmt(new BigDecimal("12.36"));
    product.setMarketAmt(new BigDecimal("16.35"));
    product.setPerCommission(new BigDecimal("1.36"));
    product.setPerServiceAmt(new BigDecimal("1.55"));
    product.setYieldly("长沙");
    product.setPackageQty(new BigDecimal("1"));
    product.setSaleLimitTime(new BigDecimal("1.5"));
    product.setSaleLimitTimeUnit(ProductSaleLimitTimeUnitEnum.HOUR.getValueDefined());
    product.setCreateUserId(1L);
    product.setCreateUserName("测试");
    product.setDetailDesc("一个简介");
    product.setShareTitle("分享标题");

    ProductAttrvalRelationDto relationDto = new ProductAttrvalRelationDto();
    relationDto.setAttrVal("一盒12瓶");

    List<ProductImgDto> imgList = new ArrayList<>();
    ProductImgDto ad = new ProductImgDto();
    ad.setImgType(ProductImgTypeEnum.AD.getValueDefined());
    String oUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1517829778623&di=d07f3772d32fc515296efd086ac4f9ba&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01711b59426ca1a8012193a31e5398.gif";
    ad.setOriginalImgUrl(oUrl);
    ad.setImgUrl60(oUrl);
    ad.setImgUrl200(oUrl);
    ad.setImgUrl400(oUrl);

    ProductImgDto pr1 = new ProductImgDto();
    pr1.setImgType(ProductImgTypeEnum.PRIMARY.getValueDefined());
    String prUrl1 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1517829778621&di=e2975c21af9020b49bba1226b4dc79a6&imgtype=0&src=http%3A%2F%2Fpic2.nipic.com%2F20090424%2F1242397_110033072_2.jpg";
    pr1.setOriginalImgUrl(prUrl1);
    pr1.setImgUrl60(prUrl1);
    pr1.setImgUrl200(prUrl1);
    pr1.setImgUrl400(prUrl1);

    ProductImgDto pr2 = new ProductImgDto();
    pr2.setImgType(ProductImgTypeEnum.PRIMARY.getValueDefined());
    String prUrl2 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1517829778621&di=59dbb54064777b2a758a8a7f57c6c297&imgtype=0&src=http%3A%2F%2Fpic78.nipic.com%2Ffile%2F20150915%2F19369522_021019921000_2.jpg";
    pr2.setOriginalImgUrl(prUrl2);
    pr2.setImgUrl60(prUrl2);
    pr2.setImgUrl200(prUrl2);
    pr2.setImgUrl400(prUrl2);

    ProductImgDto pr3 = new ProductImgDto();
    pr3.setImgType(ProductImgTypeEnum.PRIMARY.getValueDefined());
    String prUrl3 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1517829778621&di=68c24e3b0e5c26bc5d5875bdf7b3ec05&imgtype=0&src=http%3A%2F%2Fimg02.tooopen.com%2Fimages%2F20150507%2Ftooopen_sy_122395947985.jpg";
    pr3.setOriginalImgUrl(prUrl3);
    pr3.setImgUrl60(prUrl3);
    pr3.setImgUrl200(prUrl3);
    pr3.setImgUrl400(prUrl3);

    imgList.add(ad);
    imgList.add(pr1);
    imgList.add(pr2);
    imgList.add(pr3);
    for (int i = 17; i < 18; i++) {
      product.setProductName("商品名称" + i);
      product.setProductTitle("商品标题" + i);
      MerchantResult<Long> result = productService.createSingleProduct(product, relationDto, imgList);
      System.err.println("结果：" + JSON.toJSONString(result));
    }
  }

  //@Test
  public void updateProductDesc() {

    ProductDescDto productDescDto = new ProductDescDto();
    productDescDto.setProductId(86L);
    productDescDto.setDetailDesc(null);
    productDescDto.setModifyUserId(1L);
    productDescDto.setModifyUserName("test");

    List<ProductImgDto> imgList = new ArrayList<>();
    ProductImgDto pr1 = new ProductImgDto();
    String prUrl1 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1517829778621&di=e2975c21af9020b49bba1226b4dc79a6&imgtype=0&src=http%3A%2F%2Fpic2.nipic.com%2F20090424%2F1242397_110033072_2.jpg";
    pr1.setOriginalImgUrl(prUrl1);
    pr1.setImgUrl60(prUrl1);
    pr1.setImgUrl200(prUrl1);
    pr1.setImgUrl400(prUrl1);

    ProductImgDto pr2 = new ProductImgDto();
    String prUrl2 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1517829778621&di=59dbb54064777b2a758a8a7f57c6c297&imgtype=0&src=http%3A%2F%2Fpic78.nipic.com%2Ffile%2F20150915%2F19369522_021019921000_2.jpg";
    pr2.setOriginalImgUrl(prUrl2);
    pr2.setImgUrl60(prUrl2);
    pr2.setImgUrl200(prUrl2);
    pr2.setImgUrl400(prUrl2);

    ProductImgDto pr3 = new ProductImgDto();
    String prUrl3 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1517829778621&di=68c24e3b0e5c26bc5d5875bdf7b3ec05&imgtype=0&src=http%3A%2F%2Fimg02.tooopen.com%2Fimages%2F20150507%2Ftooopen_sy_122395947985.jpg";
    pr3.setOriginalImgUrl(prUrl3);
    pr3.setImgUrl60(prUrl3);
    pr3.setImgUrl200(prUrl3);
    pr3.setImgUrl400(prUrl3);

    imgList.add(pr1);
    imgList.add(pr2);
    imgList.add(pr3);
    MerchantResult result = productService.updateProductDesc(productDescDto, imgList);
    System.err.println("结果：" + JSON.toJSONString(result));
  }

  // @Test
  public void updateProduct() {

    ProductDto product = new ProductDto();
    product.setProductId(86L);
    product.setProductName("商品名称我就更改一下");
    product.setProductTitle("商品标题1");
    product.setBrandName("好品牌");
    product.setLimitQty(new BigDecimal("3"));
    product.setVendorId(1L);
    product.setSaleAmt(new BigDecimal("12.36"));
    product.setMarketAmt(new BigDecimal("16.35"));
    product.setPerCommission(new BigDecimal("1.36"));
    product.setPerServiceAmt(new BigDecimal("1.55"));
    product.setYieldly("长沙");
    product.setPackageQty(new BigDecimal("1"));
    product.setSaleLimitTime(new BigDecimal("1.5"));
    product.setSaleLimitTimeUnit(ProductSaleLimitTimeUnitEnum.HOUR.getValueDefined());
    product.setModifyUserId(1L);
    product.setModifyUserName("测试哈哈");
    product.setDetailDesc("一个简介哈哈");
    product.setShareTitle("分享标题哈哈");

    ProductAttrvalRelationDto relationDto = new ProductAttrvalRelationDto();
    relationDto.setAttrVal("一盒12瓶");

    List<ProductImgDto> imgList = new ArrayList<>();
    ProductImgDto ad = new ProductImgDto();
    ad.setImgType(ProductImgTypeEnum.AD.getValueDefined());
    String oUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1517829778623&di=d07f3772d32fc515296efd086ac4f9ba&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01711b59426ca1a8012193a31e5398.gif";
    ad.setOriginalImgUrl(oUrl);
    ad.setImgUrl60(oUrl);
    ad.setImgUrl200(oUrl);
    ad.setImgUrl400(oUrl);

    ProductImgDto pr1 = new ProductImgDto();
    pr1.setImgType(ProductImgTypeEnum.PRIMARY.getValueDefined());
    String prUrl1 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1517829778621&di=e2975c21af9020b49bba1226b4dc79a6&imgtype=0&src=http%3A%2F%2Fpic2.nipic.com%2F20090424%2F1242397_110033072_2.jpg";
    pr1.setOriginalImgUrl(prUrl1);
    pr1.setImgUrl60(prUrl1);
    pr1.setImgUrl200(prUrl1);
    pr1.setImgUrl400(prUrl1);

    ProductImgDto pr2 = new ProductImgDto();
    pr2.setImgType(ProductImgTypeEnum.PRIMARY.getValueDefined());
    String prUrl2 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1517829778621&di=59dbb54064777b2a758a8a7f57c6c297&imgtype=0&src=http%3A%2F%2Fpic78.nipic.com%2Ffile%2F20150915%2F19369522_021019921000_2.jpg";
    pr2.setOriginalImgUrl(prUrl2);
    pr2.setImgUrl60(prUrl2);
    pr2.setImgUrl200(prUrl2);
    pr2.setImgUrl400(prUrl2);

    ProductImgDto pr3 = new ProductImgDto();
    pr3.setImgType(ProductImgTypeEnum.PRIMARY.getValueDefined());
    String prUrl3 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1517829778621&di=68c24e3b0e5c26bc5d5875bdf7b3ec05&imgtype=0&src=http%3A%2F%2Fimg02.tooopen.com%2Fimages%2F20150507%2Ftooopen_sy_122395947985.jpg";
    pr3.setOriginalImgUrl(prUrl3);
    pr3.setImgUrl60(prUrl3);
    pr3.setImgUrl200(prUrl3);
    pr3.setImgUrl400(prUrl3);

    imgList.add(ad);
    imgList.add(pr1);
    imgList.add(pr2);
    imgList.add(pr3);
    MerchantResult result = productService.updateSingleProduct(product, relationDto, imgList);
    System.err.println("结果：" + JSON.toJSONString(result));
  }

  // @Test
  public void queryProductDetail() {
    MerchantResult result = productService.queryProductDetail(25L);
    System.err.println("结果：" + JSON.toJSONString(result));
  }
}
