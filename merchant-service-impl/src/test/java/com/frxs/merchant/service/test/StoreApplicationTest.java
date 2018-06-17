package com.frxs.merchant.service.test;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.frxs.framework.util.common.log4j.LogUtil;
import com.frxs.merchant.common.dal.entity.Store;
import com.frxs.merchant.common.dal.enums.StatusEnum;
import com.frxs.merchant.core.mapper.StoreLineMapper;
import com.frxs.merchant.core.mapper.StoreMapper;
import com.frxs.merchant.core.service.StoreService;
import com.frxs.merchant.core.service.StoreTaskSerivce;
import com.frxs.merchant.service.api.domain.request.StorePageRequest;
import com.frxs.merchant.service.api.domain.request.StoreRequest;
import com.frxs.merchant.service.api.dto.StoreCache;
import com.frxs.merchant.service.api.dto.StoreDto;
import com.frxs.merchant.service.api.dto.StoreLineDto;
import com.frxs.merchant.service.api.result.MerchantResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wushuo
 * @version $Id: StoreApplicationTest.java,v 0.1 2018年01月31日 15:47 $Exp
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StoreApplicationTest {

    @Autowired
    private StoreService storeService;

    @Autowired
    private StoreMapper storeMapper;

    @Autowired
    private StoreLineMapper storeLineMapper;

    @Autowired
    private StoreTaskSerivce storeTaskSerivce;


//    @Test
    public void sava(){
        StoreDto storeDto = new StoreDto();
//        storeDto.setStoreName("芙蓉兴盛");
//        storeDto.setRegionId(111);
//        storeDto.setWarehouseId(1);
//        storeDto.setWarehouseName(001);
//        storeDto.setDetailAddress("塞德里克积分");
        storeDto.setStoreId(0L);
        storeDto.setStoreName("frxs");
        storeDto.setRegionId(222);
        storeDto.setWarehouseId(2);
        storeDto.setWarehouseName("");
        storeDto.setDetailAddress("是否打算");
        StoreLineDto storeLineDto = new StoreLineDto();
        storeLineDto.setLineSort(1);
        storeLineDto.setLineId(14);
        storeService.saveStore(storeDto,storeLineDto);
    }


    //@Test
    public void update(){
        StoreDto storeDto = new StoreDto();
        storeDto.setStoreId(66880000000082L);
        storeDto.setStoreStatus(StatusEnum.NORMAL.getValueDefined());
        storeService.updateOneStore(storeDto);
    }

   // @Test
    public void getOne(){
        Long sotreId = 7L;
        storeService.getStoreById(sotreId);
    }



    //@Test
    public void getLineIds(){
        List<Integer> ids = storeLineMapper.getStoreLineIdsByStoreId(14L);
        System.out.println(ids);
    }

    //@Test
    public void getStoreList(){
        StoreRequest storeRequest = new StoreRequest();
        storeRequest.setStoreCode("99");
        storeRequest.setStoreDeveloper("果冻");
        storeRequest.setStoreStatus("FROZEN");
        storeRequest.setStoreName("兴盛");
        storeRequest.setTmOnLineStart("2017-01-01");
        storeRequest.setTmOnLineEnd("2018-11-11");
        MerchantResult<List<StoreDto>> merchantResult = storeService.getStoreList(storeRequest);
        System.out.println(merchantResult);
    }

   //@Test
    public void getStoreListBycodes(){
        List<String> codes = new ArrayList<>();
        codes.add("898098");
        codes.add("090909");
        codes.add(" 123");
        MerchantResult<List<StoreDto>> m = storeService.getStoreListByCodes(codes);
        System.out.println(m.getData().size());
    }

    //@Test
    public void getStoreListByContactsTel(){
        String contactsTel = "16688899900";
        MerchantResult<List<StoreDto>> merchantResult =
            storeService.getStoreListByContactsTel(contactsTel);
    }

    //@Test
    public void getStoreListByStoreIds() {
        List<Long> list = new ArrayList<>();
        list.add(66880000000007L);
        list.add(66880000000008L);
        MerchantResult<List<StoreDto>> merchantResult = storeService.getStoreListByStoreIds(list);
    }
    public void getStoreCacheById(){
        MerchantResult<StoreCache> storeCache = storeService.getStoreCacheById(568828800000000060L);
        LogUtil.info("storeCache",storeCache);
    }

    //@Test
    public void getStoreTmOnLine(){
        MerchantResult result = storeTaskSerivce.saveStoreTmOnLine();
    }

    //@Test
    public void getPageList(){
        StorePageRequest storePageRequest = new StorePageRequest();
        storePageRequest.setPage(1);
        storePageRequest.setRows(20);
        MerchantResult<Page<StoreDto>> pageList = storeService.getPageList(storePageRequest);
        System.out.println(pageList);
    }

    //@Test
    public void getStoreLinesByStoreIds(){
        List<Long> storeIds = new ArrayList<>();
        storeIds.add(66880000000166L);
        storeIds.add(66880000000167L);
        storeIds.add(66880000000168L);
        storeIds.add(66880000000169L);
        MerchantResult<List<StoreLineDto>> merchantResult =
            storeService.getStoreLineListByStoreIds(storeIds);
        System.out.println(merchantResult);
    }
}
