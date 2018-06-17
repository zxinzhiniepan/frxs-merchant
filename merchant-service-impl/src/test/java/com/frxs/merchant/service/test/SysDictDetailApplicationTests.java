package com.frxs.merchant.service.test;

import com.frxs.framework.util.common.log4j.LogUtil;
import com.frxs.merchant.core.service.SysDictDetailService;
import com.frxs.merchant.service.MerchantApplication;
import com.frxs.merchant.service.api.dto.SysDictDetailDto;
import com.frxs.merchant.service.api.enums.ParameterSettingEnum;
import com.frxs.merchant.service.api.result.MerchantResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author jiangboxuan
 * @version @version $Id: SysDictDetailApplicationTests.java,v 0.1 2018年03月06日 上午 9:29 $Exp
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes= MerchantApplication.class)
public class SysDictDetailApplicationTests {

    @Autowired
    SysDictDetailService sysDictDetailService;

    //@Test
    public void saveDistributionLine(){
        SysDictDetailDto sysDictDetailDto = new SysDictDetailDto();
        sysDictDetailDto.setDetailCode("PLATFORM_SERVICE_FEE");
        sysDictDetailDto.setAreaId(101);
        MerchantResult<List<SysDictDetailDto>> merchantResult = sysDictDetailService.listSysDictDetail(sysDictDetailDto);
        LogUtil.info("merchantResult",merchantResult);

    }
}
