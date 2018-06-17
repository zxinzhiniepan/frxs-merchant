package com.frxs.merchant.service.test;

import com.baomidou.mybatisplus.plugins.Page;
import com.frxs.merchant.common.dal.entity.VendorAccount;
import com.frxs.merchant.core.service.VendorAccountService;
import com.frxs.merchant.core.service.VendorService;
import com.frxs.merchant.service.MerchantApplication;
import com.frxs.merchant.service.api.domain.request.VendorAccountRequest;
import com.frxs.merchant.service.api.domain.request.VendorRequest;
import com.frxs.merchant.service.api.dto.VendorAccountDto;
import com.frxs.merchant.service.api.dto.VendorDto;
import com.frxs.merchant.service.api.dto.VendorTypeDto;
import com.frxs.merchant.service.api.facade.VendorFacade;
import com.frxs.merchant.service.api.result.MerchantResult;
import com.frxs.merchant.service.api.result.VendorResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Cukor.fu
 * @version $Id: VendorTest.java,v 0.1 2018年01月29日 下午 16:42 $Exp
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=MerchantApplication.class)
public class VendorApplicationTests {

//    @Autowired
//    private VendorService vendorService;

//    @Autowired
    //@Resource(name = "vendorFacade")
    //private VendorFacade vendorFacade;

    @Autowired
    private VendorService vendorService;

    @Autowired
    private VendorAccountService vendorAccountService;

    //@Test
    public void saveVendor(){
        VendorDto vendorDto = new VendorDto();
        vendorDto.setVendorId(1L);
//        vendorDto.setVendorName("test000");
//        vendorDto.setVendorCode("test000");
//        vendorDto.setProvinceId(0);
//        vendorDto.setCityId(0);
//        vendorDto.setRegionId(0);
//        vendorDto.setAddress("test000");
//        vendorDto.setContacts("test000");
//        vendorDto.setContactsTel("12412412");

        vendorDto.setContactsTel("999");

        //vendorService.saveVendor(vendorDto);
    }

    //@Test
    public void updateVendor(){
        VendorDto vendorDto = new VendorDto();
        vendorDto.setVendorId(2L);
        vendorDto.setVendorName("test222");
        vendorDto.setVendorCode("test222");
        vendorDto.setProvinceId(2);
        vendorDto.setCityId(2);
        //vendorDto.setRegionId(2);
        vendorDto.setAddress("test222");
        vendorDto.setContacts("test222");
        vendorDto.setContactsTel("00987654321");
        MerchantResult result = vendorService.updateVendor(vendorDto);
    }

    //@Test
    public void getVendorById(){
        MerchantResult<VendorDto> result = vendorService.getVendorById(1L);
    }


    //@Test
    public void getStoreListByContactsTel(){
        String contactsTel = "16666666666";
        MerchantResult<List<VendorDto>> merchantResult =
            vendorService.getVendorListByContactsTel(contactsTel);
        System.out.println(merchantResult.getData());
    }

    //@Test
    public void getVendorByVendorIds(){
        List<Long> vendorIds = new ArrayList<>();
        vendorIds.add(76880000000024L);
        vendorIds.add(76880000000025L);
        MerchantResult<List<VendorDto>> merchantResult =
            vendorService.getVendorListByVendorIds(vendorIds);
        System.out.println(merchantResult.getData());
    }

    //@Test
    public void getVendorByUnionPayMID(){
        List<String> list = new ArrayList<>();
        list.add("34634");
        list.add("346236585");
        MerchantResult<List<VendorDto>> merchantResult =
            vendorService.getVendorListByUnionPayMID(list);
        System.out.println(merchantResult.getData());
    }

    //@Test
    public void getVendorAccount(){

        VendorAccountRequest vendorAccountRequest = new VendorAccountRequest();
        vendorAccountRequest.setVendorId(76880000000128L);
        MerchantResult<VendorAccountDto> vendorAccount =
            vendorAccountService.getVendorAccount(vendorAccountRequest);

        System.out.println(vendorAccount);
    }

    //@Test
    public void getVendorAreaProductCount(){

        Long vendorId = 76880000000086L;
        Integer areaId = 101;
        MerchantResult<Integer> vendorAreaProductCount =
            vendorService.getVendorAreaProductCount(vendorId, areaId);
        System.out.println(vendorAreaProductCount);
    }

}
