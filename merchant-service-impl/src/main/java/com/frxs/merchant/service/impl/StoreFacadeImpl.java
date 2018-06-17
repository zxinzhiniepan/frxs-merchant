package com.frxs.merchant.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.plugins.Page;
import com.frxs.framework.integration.dubbo.annotation.FrxsAutowired;
import com.frxs.merchant.core.service.StoreService;
import com.frxs.merchant.service.api.domain.request.StoreLineRequest;
import com.frxs.merchant.service.api.domain.request.StorePageRequest;
import com.frxs.merchant.service.api.domain.request.StoreRequest;
import com.frxs.merchant.service.api.dto.StoreCache;
import com.frxs.merchant.service.api.dto.StoreDto;
import com.frxs.merchant.service.api.dto.StoreInfoDto;
import com.frxs.merchant.service.api.dto.StoreLineDto;
import com.frxs.merchant.service.api.dto.StoreProFileInfoDto;
import com.frxs.merchant.service.api.dto.StoreUserDto;
import com.frxs.merchant.service.api.facade.StoreFacade;
import com.frxs.merchant.service.api.result.MerchantResult;
import java.util.List;

/**
 * 门店服务DUBBO实现
 *
 * @author wushuo
 * @version $Id: StoreFacadeImpl.java,v 0.1 2018年01月31日 14:59 $Exp
 */
@Service(timeout = 30000, version = "1.0.0")
public class StoreFacadeImpl implements StoreFacade {

  @FrxsAutowired
  private StoreService storeService;

  /**
   * 分页查询门店信息
   *
   * @param storePageRequest 条件
   */
  @Override
  public MerchantResult<Page<StoreDto>> getPageList(StorePageRequest storePageRequest) {

    return storeService.getPageList(storePageRequest);
  }

  @Override
  public MerchantResult<List<StoreDto>> getList(StorePageRequest storePageRequest) {
    return storeService.getList(storePageRequest);
  }

  /**
   * 添加门店信息
   *
   * @return MerchantResult
   */
  @Override
  public MerchantResult saveStore(StoreDto storeDto, StoreLineDto storeLineDto) {
    return storeService.saveStore(storeDto, storeLineDto);
  }

  /**
   * 修改门店信息
   *
   * @return MerchantResult
   */
  @Override
  public MerchantResult updateStore(StoreDto storeDto, StoreLineDto storeLineDto) {
    return storeService.updateStore(storeDto, storeLineDto);
  }

  @Override
  public MerchantResult<StoreInfoDto> getStoreInfoById(Long userId) {
    return storeService.getStoreInfoById(userId);
  }

  @Override
  public MerchantResult<StoreProFileInfoDto> getProFileInfoById(Long userId) {
    return storeService.getProFileInfoById(userId);
  }

  /**
   * 更新门店
   *
   * @return MerchantResult
   */
  @Override
  public MerchantResult updateOneStore(StoreDto storeDto) {
    return storeService.updateOneStore(storeDto);
  }

  /**
   * 保存门店配送线路
   *
   * @return MerchantResult
   */
  @Override
  public MerchantResult saveStoreLine(StoreLineRequest storeLineRequest) {
    return storeService.saveStoreLine(storeLineRequest);
  }

  /**
   * 批量修改门店状态
   */
  @Override
  public MerchantResult batchUpdateStatus(StoreDto storeDto) {
    return storeService.batchUpdateStatus(storeDto);
  }

  /**
   * 根据门店id查询线路ids
   */
  @Override
  public MerchantResult<List<Integer>> getStoreLineIds(Long storeId) {
    return storeService.getStoreLineIds(storeId);
  }

  /**
   * 主键ID查询
   *
   * @return MerchantResult<StoreDto>
   */
  @Override
  public MerchantResult<StoreDto> getStoreById(Long storeId) {
    return storeService.getStoreById(storeId);
  }


  /**
   * 条件查询
   *
   * @return MerchantResult<List   <   StoreDto>>
   */
  @Override
  public MerchantResult<List<StoreDto>> getStoreList(StoreRequest storeRequest) {
    return storeService.getStoreList(storeRequest);
  }

  /**
   * 门店编号查询
   *
   * @return MerchantResult<List   <   StoreDto>>
   */
  @Override
  public MerchantResult<List<StoreDto>> getStoreListByCodes(List<String> storeCodes) {
    return storeService.getStoreListByCodes(storeCodes);
  }

  @Override
  public MerchantResult<List<StoreDto>> getStoreListByContactsTel(String contactsTel) {
    return storeService.getStoreListByContactsTel(contactsTel);
  }

  @Override
  public MerchantResult<List<StoreDto>> getStoreListByStoreIds(List<Long> storeIds) {
    return storeService.getStoreListByStoreIds(storeIds);
  }

  /**
   * 根据storeId获取门店线路信息
   *
   * @return MerchantResult<StoreLine>
   */
  @Override
  public MerchantResult<StoreLineDto> getStoreLineByStoreId(Long storeId) {
    return storeService.getStoreLineByStoreId(storeId);
  }

  @Override
  public MerchantResult<StoreCache> getStoreCacheById(Long storeId) {
    return storeService.getStoreCacheById(storeId);
  }

  @Override
  public MerchantResult<Long> getUserId(Long storeId) {
    return storeService.getUserId(storeId);
  }

  @Override
  public MerchantResult updateStoreByUserId(StoreDto storeDto, Long userId) {
    return storeService.updateStoreByUserId(storeDto, userId);
  }

  @Override
  public MerchantResult<List<StoreDto>> getStoreListByStoreRequest(StoreRequest storeRequest) {
    return storeService.getStoreListByStoreRequest(storeRequest);
  }

  @Override
  public MerchantResult<StoreLineDto> updateStoreLineSort(Long storeId, Integer lineSort) {
    return storeService.updateStoreLineSort(storeId, lineSort);
  }

  @Override
  public MerchantResult saveStoreList(List<StoreDto> storeDtos) {
    return storeService.saveStoreList(storeDtos);
  }

  @Override
  public MerchantResult<StoreUserDto> getStoreUser(String userName) {
    return storeService.getStoreUser(userName);
  }

  @Override
  public MerchantResult<List<StoreLineDto>> getStoreLineListByStoreIds(List<Long> storeIds) {
    return storeService.getStoreLineListByStoreIds(storeIds);
  }
}
