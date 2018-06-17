package com.frxs.merchant.service.test;

import com.frxs.merchant.core.service.WarehouseService;
import com.frxs.merchant.service.MerchantApplication;
import com.frxs.merchant.service.api.dto.WarehouseDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author jiangboxuan
 * @version @version $Id: WarehouseApplicationTests.java,v 0.1 2018年02月01日 下午 19:02 $Exp
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes= MerchantApplication.class)
public class WarehouseApplicationTests {

    @Autowired
    WarehouseService warehouseService;

    //@Test
    public void listWarehouse(){
        WarehouseDto warehouseDto = new WarehouseDto();
        warehouseService.listWarehouse(warehouseDto);
    }
}
