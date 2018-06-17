package com.frxs.merchant.core;

import com.frxs.merchant.common.dal.entity.ProductVendorData;
import com.frxs.merchant.core.mapper.ProductVendorDataMapper;
import com.frxs.merchant.service.api.dto.ProductQueryDto;
import com.frxs.merchant.service.api.dto.ProductSortDto;
import com.frxs.merchant.service.api.facade.ProductFacade;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.checkerframework.checker.units.qual.A;
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
@SpringBootTest
public class FyguApplicationTests {
  @Autowired
  ProductFacade productFacade;
  @Autowired
  ProductVendorDataMapper productVendorDataMapper;


//  @Test
  public void contextLoads() {
  }
//  @Test
  public void testProductSort(){

    ProductQueryDto productQueryDto =new ProductQueryDto();
    productQueryDto.setAreaId(4L);
   // productQueryDto.setBrandName("111");
    productQueryDto.setProductName("444");
    productQueryDto.setSkuStatus("DELETED");
    productQueryDto.setSku("66666");
    //productFacade.productQueryList(productQueryDto,1,20);
    ProductSortDto productSortDto = new ProductSortDto();
    List<Long> productIds =new LinkedList<>();
    productIds.add(11111L);
    productSortDto.setProductIds(productIds);
    productSortDto.setAreaId(4L);
    productSortDto.setOperateId(2L);
    productSortDto.setOperateName("555");
    productFacade.delProducts(productSortDto);
    ProductSortDto productSortDto1 = new ProductSortDto();
    List<Long> productIds1 =new LinkedList<>();
    productIds.add(11111L);
    productSortDto1.setProductIds(productIds1);
    productSortDto1.setAreaId(4L);
    productSortDto1.setOperateId(2L);
    productSortDto1.setOperateName("555");
    productSortDto1.setSkuStatus("UP");
    productFacade.updateSkuStatus(productSortDto1);
  }
//  @Test
  public void testInsertProductVendorData(){
    ProductVendorData productVendorData =new ProductVendorData();
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
   Integer id =  productVendorDataMapper.insert(productVendorData);
    System.out.println(id);
  }
}
