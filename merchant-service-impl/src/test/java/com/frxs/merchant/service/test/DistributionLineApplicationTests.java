package com.frxs.merchant.service.test;

import com.frxs.framework.util.common.log4j.LogUtil;
import com.frxs.merchant.core.service.DistributionLineService;
import com.frxs.merchant.service.MerchantApplication;
import com.frxs.merchant.service.api.dto.DistributionLineDto;
import com.frxs.merchant.service.api.dto.StoreDto;
import com.frxs.merchant.service.api.result.MerchantResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author jiangboxuan
 * @version @version $Id: DistributionLineApplicationTests.java,v 0.1 2018年01月30日 下午 17:16 $Exp
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes= MerchantApplication.class)
public class DistributionLineApplicationTests {

    @Autowired
    DistributionLineService distributionLineService;

    //@Test
    public void saveDistributionLine(){
        DistributionLineDto distributionLineDto = new DistributionLineDto();
        for (int i = 1;i < 11;i++){
            distributionLineDto.setLineName("test"+i+i+i);
            distributionLineDto.setStatus("NO");
            distributionLineService.saveDistributionLine(distributionLineDto);
        }
    }

    //@Test
    public void modifyState(){
        DistributionLineDto distributionLineDto = new DistributionLineDto();
        distributionLineDto.setId(2);
        distributionLineDto.setStatus("ON");
        distributionLineService.modifyState(distributionLineDto);
    }

    //@Test
    public void getDistributionLine(){
        MerchantResult<DistributionLineDto> result = distributionLineService.getDistributionLine(1);
        LogUtil.info(result+"");
    }

    //@Test
    public void listDistributionLine(){
        DistributionLineDto distributionLineDto = new DistributionLineDto();
        Integer pageNo = 1;
        Integer pageSize = 5;
        distributionLineService.listDistributionLine(distributionLineDto,pageNo,pageSize);
    }

    //@Test
    public void listAll(){
        DistributionLineDto distributionLineDto = new DistributionLineDto();
        distributionLineDto.setWarehouseId(0);
        String s = "id=&";
        distributionLineDto.setIds(s);
        distributionLineService.listAll(distributionLineDto);
    }

    //@Test
    public void listDistributionLineAndStore(){
        MerchantResult<Map<DistributionLineDto, List<StoreDto>>> mapMerchantResult =
            distributionLineService.listDistributionLineAndStore(1011);
        System.out.println(mapMerchantResult);
    }
}
