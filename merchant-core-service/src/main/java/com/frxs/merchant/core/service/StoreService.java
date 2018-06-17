package com.frxs.merchant.core.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.frxs.merchant.common.dal.entity.StoreLine;
import com.frxs.merchant.service.api.domain.request.StoreLineRequest;
import com.frxs.merchant.service.api.domain.request.StorePageRequest;
import com.frxs.merchant.service.api.domain.request.StoreRequest;
import com.frxs.merchant.service.api.dto.StoreDto;
import com.frxs.merchant.service.api.dto.StoreInfoDto;
import com.frxs.merchant.service.api.dto.StoreLineDto;
import com.frxs.merchant.service.api.dto.StoreProFileInfoDto;
import com.frxs.merchant.service.api.dto.*;
import com.frxs.merchant.service.api.result.MerchantResult;
import java.util.List;

/**
 * 门店信息service接口
 * @author wushuo
 * @version $Id: StoreService.java,v 0.1 2018年01月31日 15:02 $Exp
 */
public interface StoreService {
    /**
     * 分页查询门店信息
     * @param storePageRequest
     * @return MerchantResult<Page<StoreDto>>
     */
    MerchantResult<Page<StoreDto>> getPageList(StorePageRequest storePageRequest);
    /**
     * 查询门店信息
     * @param storePageRequest
     * @return MerchantResult<Page<StoreDto>>
     */
    MerchantResult<List<StoreDto>> getList(StorePageRequest storePageRequest);

    /**
     * 添加门店信息
     * @param storeDto
     * @param storeLineDto
     * @return MerchantResult
     */
    MerchantResult saveStore(StoreDto storeDto,StoreLineDto storeLineDto);

    /**
     * 修改门店信息
     * @param storeDto
     * @param storeLineDto
     * @return MerchantResult
     */
    MerchantResult updateStore(StoreDto storeDto,StoreLineDto storeLineDto);
    /**
     * 通过userId查询门店基本信息
     * @param userId
     * @return MerchantResult<StoreDto>
     */
    MerchantResult<StoreInfoDto> getStoreInfoById(Long userId);

    /**
     * 通过userId查询门店银行卡信息
     * @param userId
     * @return MerchantResult<StoreProFileInfoDto>
     */
    MerchantResult<StoreProFileInfoDto> getProFileInfoById(Long userId);
    /**
     * 更新门店
     * @param storeDto
     * @return MerchantResult
     */
    MerchantResult updateOneStore(StoreDto storeDto);

    /**
     * 保存门店配送线路
     * @param storeLineRequest
     * @return MerchantResult
     */
    MerchantResult saveStoreLine(StoreLineRequest storeLineRequest);

    /**
     * 批量修改门店状态
     * @param storeDto
     * @return
     */
    MerchantResult batchUpdateStatus(StoreDto storeDto);

    /**
     * 根据门店id查询线路ids
     * @param storeId
     * @return
     */
    MerchantResult<List<Integer>> getStoreLineIds(Long storeId);

    /**
     * 主键ID查询
     * @param storeId
     * @return MerchantResult<StoreInfoDto>
     */
    MerchantResult<StoreDto> getStoreById(Long storeId);

    /**
     * 条件查询
     * @param storeRequest
     * @return MerchantResult<List<StoreDto>>
     */
    MerchantResult<List<StoreDto>> getStoreList(StoreRequest storeRequest);

    /**
     * 门店编号查询
     * @param storeCodes
     * @return MerchantResult<List<StoreDto>>
     */
    MerchantResult<List<StoreDto>> getStoreListByCodes(List<String> storeCodes);

    /**
     * 根据号码查询门店信息
     * @param contactsTel
     * @return MerchantResult<List<StoreDto>>
     */
    MerchantResult<List<StoreDto>> getStoreListByContactsTel(String contactsTel);


    /**
     * 根据storeIds获取门店信息
     * @param storeIds
     * @return MerchantResult<List<StoreDto>>
     */
    MerchantResult<List<StoreDto>> getStoreListByStoreIds(List<Long> storeIds);

    /**
     * 根据storeId获取门店线路信息
     * @param storeId
     * @return MerchantResult<StoreLineDto>
     */
    MerchantResult<StoreLineDto> getStoreLineByStoreId(Long storeId);
    /**
     * 通过storeId查询门店基本信息
     * @param storeId
     * @return MerchantResult<StoreCache>
     */
    MerchantResult<StoreCache> getStoreCacheById(Long storeId);

    /**
     * 获取userId
     * @param storeId
     * @return
     */
    MerchantResult<Long> getUserId(Long storeId);

    /** 更新门店
     * @param storeDto
     * @param userId
     * @return MerchantResult
     */
    MerchantResult updateStoreByUserId(StoreDto storeDto,Long userId);
    /**
     * 条件查询
     * @param storeRequest
     * @return
     */
    MerchantResult<List<StoreDto>> getStoreListByStoreRequest(StoreRequest storeRequest);

    /**
     * 修改门店线路
     * @param storeId
     * @param lineSort
     * @return MerchantResult<StoreLineDto>
     */
    MerchantResult<StoreLineDto> updateStoreLineSort(Long storeId,Integer lineSort);

    /**
     * 添加导入门店信息
     * @param storeDtos
     * @return MerchantResult
     */
    MerchantResult saveStoreList(List<StoreDto> storeDtos);

    /**
     * 通过门店id获取门店用户关联信息
     * @param  userName
     * @return
     */
    MerchantResult<StoreUserDto> getStoreUser(String userName);

    /**
     * 多个门店Ids获取线路List
     * @param storeIds
     * @return
     */
    MerchantResult<List<StoreLineDto>> getStoreLineListByStoreIds(List<Long> storeIds);

}
