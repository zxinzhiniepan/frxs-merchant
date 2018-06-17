package com.frxs.merchant.service.test;

import com.baomidou.mybatisplus.plugins.Page;
import com.frxs.framework.util.common.log4j.LogUtil;
import com.frxs.merchant.core.service.ParameterSettingService;
import com.frxs.merchant.service.MerchantApplication;
import com.frxs.merchant.service.api.dto.ParameterSettingDto;
import com.frxs.merchant.service.api.enums.ParameterSettingEnum;
import com.frxs.merchant.service.api.result.MerchantResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author jiangboxuan
 * @version @version $Id: SysDictDetailApplicationTests.java,v 0.1 2018年01月31日 上午 11:03 $Exp
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes= MerchantApplication.class)
public class ParameterSettingApplicationTests {

    @Autowired
    ParameterSettingService parameterSettingService;

   // @Test
    public void getParameterSetting(){
        MerchantResult<ParameterSettingDto> result = parameterSettingService.getParameterSetting(1);
    }

    //@Test
    public void modifyState(){
        ParameterSettingDto parameterSettingDto = new ParameterSettingDto();
        parameterSettingDto.setId(1);
        parameterSettingDto.setParaValue("ON");
        parameterSettingService.modifyState(parameterSettingDto);
    }

    //@Test
    public void listParameterSetting(){
        ParameterSettingDto parameterSettingDto = new ParameterSettingDto();
        Integer pageNo = 2;
        Integer pageSize = 10;
        parameterSettingService.listParameterSetting(parameterSettingDto,pageNo,pageSize);
    }

    //@Test
    public void listParameterSettingArea(){
        ParameterSettingDto parameterSettingDto = new ParameterSettingDto();
        Integer pageNo = 1;
        Integer pageSize = 10;
        parameterSettingDto.setAreaId(101);
        MerchantResult<Page<ParameterSettingDto>> merchantResult = parameterSettingService.listParameterSettingArea(parameterSettingDto, pageNo, pageSize);
    }

    //@Test
    public void getParameterByParaCode(){
        ParameterSettingDto parameterSettingDto = new ParameterSettingDto();
        Integer areaId = 101;
        MerchantResult<ParameterSettingDto> parameterByParaCode = parameterSettingService.getParameterByParaCode(areaId, ParameterSettingEnum.STORESALESRANKINGS.getValueDefined());
        LogUtil.info("res",parameterByParaCode);
    }

    //@Test
    public void getParameterSettingByAreaId(){
        parameterSettingService.getParameterSettingByAreaId(101);
    }
}
