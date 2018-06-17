package com.frxs.merchant.service.test;

import com.frxs.framework.util.common.log4j.LogUtil;
import com.frxs.merchant.core.service.SetActivityDynamicPromptService;
import com.frxs.merchant.service.api.dto.SetActivityDynamicPromptDto;
import com.frxs.merchant.service.api.result.MerchantResult;
import com.frxs.merchant.service.MerchantApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author jiangboxuan
 * @version @version $Id: SetActivityDynamicPromptApplicationTests.java,v 0.1 2018年01月30日 下午 19:00 $Exp
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes= MerchantApplication.class)
public class SetActivityDynamicPromptApplicationTests {

    @Autowired
    SetActivityDynamicPromptService setActivityDynamicPromptService;

   // @Test
    public void saveSetActivityDynamicPromptDto(){
        SetActivityDynamicPromptDto setActivityDynamicPromptDto = new SetActivityDynamicPromptDto();
     // setActivityDynamicPromptDto.setDynamicPromptId(2);
        setActivityDynamicPromptDto.setDynamicPromptName("test22");
        setActivityDynamicPromptDto.setCreateUserName("sss22");
        setActivityDynamicPromptDto.setStatus("ON");
        setActivityDynamicPromptService.saveSetActivityDynamicPromptDto(setActivityDynamicPromptDto);
    }

    //@Test
    public void getSetActivityDynamicPromptDto(){
        MerchantResult<SetActivityDynamicPromptDto> result = setActivityDynamicPromptService.getSetActivityDynamicPromptDto(2);
        LogUtil.info(result+"");
    }

   // @Test
    public void modifyState(){
        SetActivityDynamicPromptDto setActivityDynamicPromptDto = new SetActivityDynamicPromptDto();
        setActivityDynamicPromptDto.setDynamicPromptId(2);
        setActivityDynamicPromptDto.setStatus("ON");
        setActivityDynamicPromptService.modifyState(setActivityDynamicPromptDto);
    }

   // @Test
    public void listSetActivityDynamicPrompt(){
        SetActivityDynamicPromptDto setActivityDynamicPromptDto = new SetActivityDynamicPromptDto();
        Integer pageNo = 2;
        Integer pageSize = 3;
        setActivityDynamicPromptService.listSetActivityDynamicPrompt(setActivityDynamicPromptDto,pageNo,pageSize);
    }

    //@Test
    public void selectByTime(){
        setActivityDynamicPromptService.selectByTime(101);
    }
}
