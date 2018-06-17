package com.frxs.merchant.service.test;

import com.frxs.merchant.core.service.OrgAreaService;
import com.frxs.merchant.service.api.facade.OrgAreaFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wushuo
 * @version $Id: OrgAreaApplictionTest.java,v 0.1 2018年01月31日 15:31 $Exp
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrgAreaApplictionTest {

//    @Autowired
//    private OrgAreaFacade orgAreaFacade;

    @Autowired
    private OrgAreaService orgAreaService;

//    @Test
    public void getByParentId(){
        Integer parentId = 0;
        orgAreaService.getByParentId(parentId);
    }

    //@Test
/*    public void getByOrgAreaName(){
        orgAreaService.getByOrgAreaName("湖南省","长沙市","岳麓区");
    }*/
}
