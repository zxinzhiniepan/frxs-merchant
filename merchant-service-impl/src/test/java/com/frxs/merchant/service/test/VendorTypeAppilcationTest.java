package com.frxs.merchant.service.test;

import com.frxs.merchant.common.dal.enums.IsDeleteEnum;
import com.frxs.merchant.core.service.VendorTypeService;
import com.frxs.merchant.service.api.dto.VendorTypeDto;
import com.frxs.merchant.service.api.result.MerchantResult;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wushuo
 * @version $Id: VendorTypeAppilcationTest.java,v 0.1 2018年01月31日 15:52 $Exp
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class VendorTypeAppilcationTest {

    @Autowired
    private VendorTypeService vendorTypeService;

//    @Test
    public void save(){
        VendorTypeDto vendorTypeDto = new VendorTypeDto();
//        vendorTypeDto.setVendorTypeName("水果供应商");
       // vendorTypeDto.setVendorTypeId(1);
        vendorTypeDto.setVendorTypeName("奶制品供应商");
        vendorTypeService.save(vendorTypeDto);
    }


//    @Test
    public void update(){
        VendorTypeDto vendorTypeDto = new VendorTypeDto();
        vendorTypeDto.setVendorTypeId(1);
        vendorTypeDto.setVendorTypeName("水果供应商");
        vendorTypeService.update(vendorTypeDto);
    }

//    @Test
    public void getOne(){
        vendorTypeService.getById(1);
    }

//    @Test
    public void getList(){
        vendorTypeService.getList(new VendorTypeDto());
    }

    //@Test
    public void getListByVendorId(){
        MerchantResult<List<Integer>> vendorIds =
            vendorTypeService.getListByVendorId(14L, IsDeleteEnum.IS_DELETE_N.getValueDefined());
        System.out.println(vendorIds);
    }
}
