package com.frxs.merchant.service.test;

import com.frxs.merchant.core.mapper.SeqStoreMapper;
import com.frxs.merchant.core.service.AreaService;
import com.frxs.merchant.service.MerchantApplication;
import com.frxs.merchant.service.api.dto.AreaDto;
import com.frxs.merchant.service.api.result.MerchantResult;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wushuo
 * @version $Id: AreaTest.java,v 0.1 2018年02月09日 11:17 $Exp
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes= MerchantApplication.class)
public class AreaTest {

    @Autowired AreaService areaService;

    @Autowired SeqStoreMapper seqStoreMapper;
    @Test
    public void save(){

    }

    //@Test
    public void find(){
        MerchantResult<List<AreaDto>> merchantResult = areaService.getList("Y");
        return;
    }


   // @Test
    public void demo(){
        Long seq = seqStoreMapper.getNextSeqForStore();
        System.out.println(seq);

    }
}
