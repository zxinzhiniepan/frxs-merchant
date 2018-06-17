package com.frxs.merchant.core.mapstruct;

import com.baomidou.mybatisplus.plugins.Page;
import com.frxs.merchant.common.dal.entity.Store;
import com.frxs.merchant.common.dal.entity.StoreQy;
import com.frxs.merchant.service.api.domain.request.StoreRequest;
import com.frxs.merchant.service.api.dto.StoreCache;
import com.frxs.merchant.service.api.dto.StoreDto;
import com.frxs.merchant.service.api.dto.StoreInfoDto;
import com.frxs.merchant.service.api.dto.StoreProFileInfoDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 门店领域模型转换
 *
 * @author wushuo
 * @version $Id: StoreMapStruct.java,v 0.1 2018年01月29日 16:21 $Exp
 */
@Mapper
public interface StoreMapStruct {

    /**
     * MAPPER
     */
    StoreMapStruct MAPPER = Mappers.getMapper(StoreMapStruct.class);

    /**
     * Store 转 StoreDto
     *
     * @param store 领域模型
     * @return StoreDto
     */
    StoreDto storeToStoreDto(Store store);

    /**
     *  StoreDto 转 Store
     *
     * @param storeDto StoreDto
     * @return store 领域模型
     */
    Store storeDtoToStore(StoreDto storeDto);

    /**
     *  stores 转 storeDtos
     *
     * @param stores 领域模型List
     * @return List<StoreDto>
     */
    List<StoreDto> storesToStoreDtos(List<Store> stores);

    /**
     *  storeDtos 转 stores
     *
     * @param storeDtos StoreDto List
     * @return List<Store>
     */
    List<Store> storeDtosToStores(List<StoreDto> storeDtos);

    /**
     *  storePages 转 storeDtoPages
     *
     * @param stores 领域模型Page
     * @return Page<StoreDto>
     */
    Page<StoreDto> storesPageToStoreDtos(Page<Store> stores);

    /**
     *  storeQys 转 storeDtos
     * @param storeQys
     * @return List<StoreDto>
     */
    List<StoreDto> storeQysToStoreDtos(List<StoreQy> storeQys);

    /**
     * store 转 storeCache
     * @param store
     * @return
     */
    StoreCache storeToStoreCache(Store store);

    /**
     * storeCache 转 storeInfoDto
     * @param storeCache
     * @return
     */
    StoreInfoDto storeCacheToStoreInfoDto(StoreCache storeCache);

    /**
     * store 转 storeProFileInfoDto
     * @param store
     * @return
     */
    StoreProFileInfoDto storeToStoreProFileInfoDto(Store store);

    /**
     * storeRequest 转 store
     * @param storeRequest
     * @return
     */
    Store storeRequestToStore(StoreRequest storeRequest);
}
