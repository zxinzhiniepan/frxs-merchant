package com.frxs.merchant.core;

import com.baomidou.mybatisplus.plugins.Page;
import com.frxs.merchant.common.dal.entity.ProductVendorData;
import com.frxs.merchant.core.mapper.ProductVendorDataAttrMapper;
import com.frxs.merchant.core.mapper.ProductVendorDataImgMapper;
import com.frxs.merchant.core.mapper.ProductVendorDataMapper;
import com.frxs.merchant.core.service.ProductService;
import com.frxs.merchant.core.service.ProductVendorDataService;
import com.frxs.merchant.service.MerchantApplication;
import com.frxs.merchant.service.api.dto.ProductDto;
import com.frxs.merchant.service.api.dto.ProductQueryDto;
import com.frxs.merchant.service.api.dto.ProductSortDto;
import com.frxs.merchant.service.api.dto.ProductVendorDataAttrDto;
import com.frxs.merchant.service.api.dto.ProductVendorDataDto;
import com.frxs.merchant.service.api.dto.ProductVendorDataImgDto;
import com.frxs.merchant.service.api.dto.ProductVendorDto;
import com.frxs.merchant.service.api.dto.VendorProductDataOperateDto;
import com.frxs.merchant.service.api.enums.ProductAuditStatusEnum;
import com.frxs.merchant.service.api.result.MerchantResult;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author fygu
 * @version $Id: FyguApplicationTests.java,v 0.1 2018年01月29日 19:09 $Exp
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MerchantApplication.class)
public class FyguApplicationTests {

  @Autowired
  ProductService productService;

  @Autowired
  ProductVendorDataService productVendorDataService;

  @Autowired
  ProductVendorDataMapper productVendorDataMapper;

  @Autowired
  ProductVendorDataAttrMapper productVendorDataAttrMapper;

  @Autowired
  ProductVendorDataImgMapper productVendorDataImgMapper;

  @Test
  public void contextLoads() {
  }


  @Test
  public void testProductVendor() {
    ProductVendorDto productVendorDto = new ProductVendorDto();
    ProductVendorDataDto productVendorDataDto = new ProductVendorDataDto();
    String s1 = ProductAuditStatusEnum.DRAFT.getValueDefined();
    // String s2= ProductAuditStatusEnum.DRAFT.getValue();
    String s3 = ProductAuditStatusEnum.DRAFT.getDesc();
    productVendorDataDto.setVendorProductDataId(15L);
    productVendorDataDto.setAuditStatus("REJECT");
    productVendorDataDto.setVendorProductDesc("保存测试");
    productVendorDto.setProdVendorData(productVendorDataDto);
    productVendorDataService.updateProductVendor(productVendorDto);
  }

  /**
   * 商品显示顺序修改  列表查询、删除、上下架接口测试
   */
  @Test
  public void testProductSort() {
    ProductQueryDto productQueryDto = new ProductQueryDto();
    productQueryDto.setAreaId(101L);
    // productQueryDto.setBrandName("111");
   // productQueryDto.setProductName("444");
    //productQueryDto.setSkuStatus("DELETED");
    productQueryDto.setSku("18020501901904");

    Page<ProductDto> page = new Page<ProductDto>(1,10);
    productService.productQueryList(productQueryDto, page);
    ProductSortDto productSortDto = new ProductSortDto();
    List<Long> productIds = new LinkedList<>();
    productIds.add(11111L);
    productSortDto.setProductIds(productIds);
    productSortDto.setAreaId(4L);
    productSortDto.setOperateId(2L);
    productSortDto.setOperateName("555");

    /*productService.delProducts(productSortDto);

    ProductSortDto productSortDto1 = new ProductSortDto();
    List<Long> productIds1 = new LinkedList<>();
    productIds.add(11111L);
    productSortDto1.setProductIds(productIds1);
    productSortDto1.setAreaId(4L);
    productSortDto1.setOperateId(2L);
    productSortDto1.setOperateName("555");
    productSortDto1.setSkuStatus("UP");
    productService.updateSkuStatus(productSortDto1);*/
  }

  /**
   * productVendorDataService 供应商端新增供应商商品
   */
  @Test
  public void testproductVendorDataService1() {
    ProductVendorDto productVendorDto = new ProductVendorDto();
    ProductVendorDataDto productVendorDataDto = new ProductVendorDataDto();
    productVendorDataDto.setVendorId(11112L);
    productVendorDataDto.setVendorCode("1111111111111");
    productVendorDataDto.setVendorName("22222222222222");
    productVendorDataDto.setVendorProductName("1111");
    productVendorDataDto.setVendorProductDesc("fygu测试");
    productVendorDataDto.setTmPublish(new Date());
    productVendorDataDto.setAuditStatus("DRAFT");
    productVendorDataDto.setSpecType("SINGLE");
    productVendorDataDto.setIsAreaDel("FALSE");
    productVendorDataDto.setIsVendorDel("FALSE");
    productVendorDataDto.setCreateUserId(3424324l);
    productVendorDataDto.setCreateUserName("656666");
    productVendorDataDto.setTmSmp(new Date());
    productVendorDataDto.setTmCreate(new Date());
    List<ProductVendorDataAttrDto> productVendorDataAttrDtoList = new LinkedList<>();
    Date date = new Date();
    ProductVendorDataAttrDto productVendorDataAttrDto = new ProductVendorDataAttrDto();
    productVendorDataAttrDto
        .setVendorProductAttr("1111");
    BigDecimal amt = new BigDecimal("1");
    productVendorDataAttrDto
        .setVendorProductAmt(amt);
    productVendorDataAttrDto
        .setVendorProductQty(new BigDecimal("11"));
    productVendorDataAttrDto.setCreateUserId(3424324l);
    productVendorDataAttrDto.setCreateUserName("656666");
    productVendorDataAttrDto.setTmCreate(date);
    productVendorDataAttrDto.setTmSmp(date);
    productVendorDataAttrDtoList.add(productVendorDataAttrDto);
    List<ProductVendorDataImgDto> productVendorDataImgDtoList = new LinkedList<>();
    ProductVendorDataImgDto productVendorDataImgDto = new ProductVendorDataImgDto();
    productVendorDataImgDto.setOriginalImgUrl("http://apiimage.frxs.cn/Product/2018/1/31/a814b668-30d2-4139-bb0a-26018384ab62_120x120.jpg_120x120.jpg");
    productVendorDataImgDto.setImgUrl60("http://apiimage.frxs.cn/Product/2018/1/31/a814b668-30d2-4139-bb0a-26018384ab62_120x120.jpg_120x120.jpg");
    productVendorDataImgDto.setImgUrl120("http://apiimage.frxs.cn/Product/2018/1/31/a814b668-30d2-4139-bb0a-26018384ab62_120x120.jpg_120x120.jpg");
    productVendorDataImgDto.setImgUrl200("http://apiimage.frxs.cn/Product/2018/1/31/a814b668-30d2-4139-bb0a-26018384ab62_120x120.jpg_120x120.jpg");
    productVendorDataImgDto.setImgUrl400("http://apiimage.frxs.cn/Product/2018/1/31/a814b668-30d2-4139-bb0a-26018384ab62_120x120.jpg_120x120.jpg");
    productVendorDataImgDto.setCreateUserId(3424324l);
    productVendorDataImgDto.setCreateUserName("656666");
    productVendorDataImgDto.setTmCreate(date);
    productVendorDataImgDto.setTmSmp(date);
    productVendorDataImgDto.setSortSeq(1);
    productVendorDataImgDtoList.add(productVendorDataImgDto);
    productVendorDto.setProdVendorData(productVendorDataDto);
    productVendorDto.setAttrs(productVendorDataAttrDtoList);
    productVendorDto.setImgs(productVendorDataImgDtoList);
    productVendorDataService.createProductVendor(productVendorDto);
  }

  /**
   * productVendorDataService 供应商端删除草稿状态供应商商品
   */
  @Test
  public void testproductVendorDataService2() {
    VendorProductDataOperateDto vendorProductDataOperateDto = new VendorProductDataOperateDto();
    List<Long> list = new LinkedList<>();
    list.add(6L);
    vendorProductDataOperateDto.setVendorProductDataIdList(list);
    vendorProductDataOperateDto.setOperateId(2L);
    vendorProductDataOperateDto.setOperateName("555");
    productVendorDataService.isVendorDel(vendorProductDataOperateDto);
  }


  /**
   * productVendorDataService 供应商端供应商商品提交审核 包括审核驳回
   */
  @Test
  public void testproductVendorDataService3() {
    VendorProductDataOperateDto vendorProductDataOperateDto = new VendorProductDataOperateDto();
    List<Long> list = new LinkedList<>();
    list.add(7L);
    vendorProductDataOperateDto.setVendorProductDataIdList(list);
    vendorProductDataOperateDto.setOperateId(2L);
    vendorProductDataOperateDto.setOperateName("555");
    vendorProductDataOperateDto.setAuditStatus("PENDING");
    productVendorDataService.updateAuditStatus(vendorProductDataOperateDto);
  }

  /**
   * productVendorDataService 供应商端供应商商品管理列表查询
   */
  @Test
  public void testproductVendorDataService4() {
    Page<ProductVendorDto> page = new Page<ProductVendorDto>(1,10);
    List<Long> list = new LinkedList<>();
    list.add(11112L);
    //未提交列
    MerchantResult<Page<ProductVendorDto>> merchantResult =
        productVendorDataService.productVendorDtoList(11112L, false, page);
    //已提交列
    Page<ProductVendorDto> page1 = new Page<ProductVendorDto>(1,10);
    MerchantResult<Page<ProductVendorDto>> merchantResult1 = productVendorDataService.productVendorDtoList(11112L, true, page1);

    System.out.println();
  }

  /**
   * productVendorDataService 供应商端供应商商品管理 商品预览
   */
  @Test
  public void testproductVendorDataService5() {
    MerchantResult<ProductVendorDto> merchantResult = productVendorDataService.previewProductVendor(7L);
    System.out.println();
  }


  @Test
  public void testInsertProductVendorData() {
    ProductVendorData productVendorData = new ProductVendorData();
    productVendorData.setVendorId(11111L);
    productVendorData.setVendorCode("1111111111111");
    productVendorData.setVendorName("22222222222222");
    productVendorData.setVendorProductName("232424");
    productVendorData.setTmPublish(new Date());
    productVendorData.setAuditStatus("DRAFT");
    productVendorData.setSpecType("SINGLE");
    productVendorData.setIsAreaDel("FALSE");
    productVendorData.setIsVendorDel("FALSE");
    productVendorData.setCreateUserId(3424324l);
    productVendorData.setCreateUserName("656666");
    productVendorData.setTmSmp(new Date());
    productVendorData.setTmCreate(new Date());
    Integer id = productVendorDataMapper.insert(productVendorData);
    System.out.println(id);
  }

  @Test
  public void testselectVendorDataImgByCondition() {
    //productVendorDataImgMapper.selectVendorDataImgByCondition(111L,false);

    //productVendorDataAttrMapper.selectVendorDataAttrByCondition(111L,false);

    String specType = "SINGLE" == "SINGLE" ? "单规格" : "多规格";
    System.out.println(specType);

  }
}
