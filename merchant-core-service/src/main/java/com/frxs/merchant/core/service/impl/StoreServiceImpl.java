package com.frxs.merchant.core.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.frxs.framework.util.common.StringUtil;
import com.frxs.framework.util.common.log4j.LogUtil;
import com.frxs.fund.service.api.domain.dto.store.StoreBalanceDto;
import com.frxs.merchant.common.dal.entity.DistributionLine;
import com.frxs.merchant.common.dal.entity.Store;
import com.frxs.merchant.common.dal.entity.StoreLine;
import com.frxs.merchant.common.dal.entity.StoreQy;
import com.frxs.merchant.common.dal.entity.StoreUserRef;
import com.frxs.merchant.common.dal.enums.ErrorCodeDetailEnum;
import com.frxs.merchant.common.dal.enums.ErrorCodeScenarioEnum;
import com.frxs.merchant.common.dal.enums.IsDeleteEnum;
import com.frxs.merchant.common.dal.enums.StatusEnum;
import com.frxs.merchant.core.cache.CacheTool;
import com.frxs.merchant.core.dubbo.FundDubboProcess;
import com.frxs.merchant.core.generator.IdGenerator;
import com.frxs.merchant.core.helper.MerchantResultHelper;
import com.frxs.merchant.core.mapper.DistributionLineMapper;
import com.frxs.merchant.core.mapper.StoreLineMapper;
import com.frxs.merchant.core.mapper.StoreMapper;
import com.frxs.merchant.core.mapper.StoreUserRefMapper;
import com.frxs.merchant.core.mapstruct.StoreLineMapStruct;
import com.frxs.merchant.core.mapstruct.StoreMapStruct;
import com.frxs.merchant.core.mapstruct.StoreUserRefMapStruct;
import com.frxs.merchant.core.service.StoreService;
import com.frxs.merchant.core.service.StoreUserService;
import com.frxs.merchant.service.api.domain.request.StoreLineRequest;
import com.frxs.merchant.service.api.domain.request.StorePageRequest;
import com.frxs.merchant.service.api.domain.request.StoreRequest;
import com.frxs.merchant.service.api.dto.StoreCache;
import com.frxs.merchant.service.api.dto.StoreDto;
import com.frxs.merchant.service.api.dto.StoreInfoDto;
import com.frxs.merchant.service.api.dto.StoreLineDto;
import com.frxs.merchant.service.api.dto.StoreProFileInfoDto;
import com.frxs.merchant.service.api.dto.StoreUserDto;
import com.frxs.merchant.service.api.result.MerchantResult;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 门店服务service实现
 *
 * @author wushuo
 * @version $Id: StoreService.java,v 0.1 2018年01月29日 18:38 $Exp
 */
@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreMapper storeMapper;

    @Autowired
    private StoreLineMapper storeLineMapper;

    @Autowired
    private StoreUserRefMapper storeUserRefMapper;

    @Autowired
    private DistributionLineMapper distributionLineMapper;

    @Autowired
    private TransactionTemplate newTransactionTemplate;

    @Autowired
    private MerchantResultHelper<MerchantResult> merchantResultHelper;

    @Autowired
    private CacheTool cacheTool;

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private StoreUserService storeUserService;

    @Autowired
    private FundDubboProcess fundDubboProcess;

    /**
     * 分页查询门店信息
     *
     * @param storePageRequest
     * @return MerchantResult<Page < StoreDto>>
     */
    @Override
    public MerchantResult<Page<StoreDto>> getPageList(StorePageRequest storePageRequest) {
        MerchantResult<Page<StoreDto>> result = new MerchantResult<>();
        Integer page = storePageRequest.getPage();
        Integer rows = storePageRequest.getRows();
        Page<Store> storePage = new Page<>(page, rows);
        Page<StoreDto> pageResult = new Page<>();
        boolean flag = (StringUtil.isNotBlank(storePageRequest.getUserName()) && StringUtil.isNotBlank(storePageRequest.getUserName())) || (storePageRequest.getLineId() != null && storePageRequest.getLineId() != 0);
        Integer count = 0;
        if(flag){
            count = storeMapper.getCount(storePageRequest);
        }else {
            count = storeMapper.getCountNotOther(storePageRequest);
        }
        List<StoreDto> resultList = new ArrayList<>();
        if(count > 0){
            List<StoreQy> pageList = new ArrayList<>();
            if(flag){
                pageList = storeMapper.getPageList(storePage, storePageRequest);
            }else {
                pageList = storeMapper.getPageListNotOther(storePage.getOffset(),storePage.getLimit(), storePageRequest);
            }

            resultList = StoreMapStruct.MAPPER.storeQysToStoreDtos(pageList);
        }
        pageResult.setRecords(resultList);
        pageResult.setTotal(count);
        result.setData(pageResult);
        result.setSuccess(true);
        return result;
    }

    /**
     * 查询门店信息
     *
     * @param storePageRequest 条件
     * @return MerchantResult<List<StoreDto>>
     */
    @Override
    public MerchantResult<List<StoreDto>> getList(StorePageRequest storePageRequest) {
        MerchantResult<List<StoreDto>> result = new MerchantResult<>();
        List<StoreQy> list = storeMapper.getList(storePageRequest);
        if (list == null || list.size() == 0) {
            return result;
        }
        List<StoreDto> resultList = StoreMapStruct.MAPPER.storeQysToStoreDtos(list);
        result.setData(resultList);
        result.setSuccess(true);
        return result;
    }

    /**
     * 保存门店信息
     *
     * @param storeDto
     * @param storeLineDto
     * @return MerchantResult
     */
    @Override
    public MerchantResult saveStore(StoreDto storeDto, StoreLineDto storeLineDto) {
        MerchantResult result = newTransactionTemplate.execute(new TransactionCallback<MerchantResult>() {
            MerchantResult<StoreDto> result = new MerchantResult<>();

            @Override
            public MerchantResult doInTransaction(TransactionStatus transactionStatus) {
                try {
                    Store store = StoreMapStruct.MAPPER.storeDtoToStore(storeDto);
                    StoreLine storeLine = StoreLineMapStruct.MAPPER.storeLineDtoToStoreLine(storeLineDto);
                    if (store.getStoreId() != 0) {
                        storeMapper.updateById(store);
                        StoreLine request = new StoreLine();
                        request.setStoreId(storeLine.getStoreId());
                        storeLineMapper.update(request, new EntityWrapper<StoreLine>(storeLine));
                    } else {
                        //增加门店
                        store.setStoreId(idGenerator.genStoreId());
                        store.setTmCreate(new Date());
                        storeMapper.insert(store);
                        //增加线路
                        storeLine.setStoreId(store.getStoreId());
                        storeLine.setTmCreate(new Date());
                        storeLineMapper.insert(storeLine);
                        //增加门店用户
                        storeUserService.saveStoreUser(store, storeDto.getUserName());
                        //新增门店资金账户
                        StoreBalanceDto storeBalanceDto = new StoreBalanceDto();
                        storeBalanceDto.setStoreId(store.getStoreId());
                        storeBalanceDto.setStoreCode(store.getStoreCode());
                        storeBalanceDto.setStoreName(store.getStoreName());
                        storeBalanceDto.setAreaId(store.getAreaId());
                        storeBalanceDto.setAreaName(store.getAreaName());
                        fundDubboProcess.addStoreBalance(storeBalanceDto);
                    }

                    cacheTool.deleteStoreCache(store.getStoreId()+"");
                    result.setSuccess(true);
                    return result;
                } catch (Exception e) {
                    merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.STORE, ErrorCodeDetailEnum.STORE_SAVE_ERROR);
                    LogUtil.error(e,"[store-service:保存门店信息]处理异常  errorCode={}", result.getErrorContext().fetchCurrentErrorCode());
                    // 事务回滚
                        transactionStatus.setRollbackOnly();
                }
                return result;
            }
        });
        return result;
    }

    /**
     * 修改门店信息
     *
     * @param storeDto
     * @param storeLineDto
     * @return MerchantResult
     */
    @Override
    public MerchantResult updateStore(StoreDto storeDto, StoreLineDto storeLineDto) {
        MerchantResult result = newTransactionTemplate.execute(new TransactionCallback<MerchantResult>() {
            MerchantResult<StoreDto> result = new MerchantResult<>();

            @Override
            public MerchantResult doInTransaction(TransactionStatus transactionStatus) {
                try {
                    Store store = StoreMapStruct.MAPPER.storeDtoToStore(storeDto);
                    StoreLine storeLine = StoreLineMapStruct.MAPPER.storeLineDtoToStoreLine(storeLineDto);
                    storeMapper.updateById(store);
                    storeLine.setStoreId(store.getStoreId());
                    storeLineMapper.updateByStoreId(storeLine);

                    cacheTool.deleteStoreCache(store.getStoreId()+"");
                    result.setSuccess(true);
                    return result;
                } catch (Exception e) {
                    merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.STORE, ErrorCodeDetailEnum.STORE_SAVE_ERROR);
                    LogUtil.error(e,"[store-service:修改门店信息]处理异常  errorCode={}", result.getErrorContext().fetchCurrentErrorCode());
                    // 事务回滚
                    transactionStatus.setRollbackOnly();
                }
                return result;
            }
        });
        return result;
    }

    /**
     * 通过userId查询门店基本信息
     *
     * @param userId
     * @return MerchantResult<StoreDto>
     */
    @Override
    public MerchantResult getStoreInfoById(Long userId) {
        MerchantResult result = new MerchantResult();

        Long storeId = cacheTool.findStoreUserByKey(userId + "");
        StoreCache storeCache = new StoreCache();
        storeCache = cacheTool.findStoreCacheByKey(storeId + "");

        if (storeId == null || storeCache == null) {
            Store store = storeMapper.getStoreByUserId(userId);
            if (store == null) {
                result.setSuccess(false);
                return result;
            }

            if(!StatusEnum.NORMAL.getValue().equals(store.getStoreStatus())){
                result.setSuccess(false);
                StatusEnum statusEnum = StatusEnum.getByValue(store.getStoreStatus());
                result.setData("该门店目前是" + statusEnum.getDesc() + "状态，有疑问请您联络客服");
                return result;
            }

            storeCache = StoreMapStruct.MAPPER.storeToStoreCache(store);
            StoreLine storeLine1 = new StoreLine();
            storeLine1.setStoreId(store.getStoreId());
            storeLine1 = storeLineMapper.selectOne(storeLine1);
            DistributionLine distributionLine = distributionLineMapper.selectByPrimaryKey(storeLine1.getLineId());
            if (distributionLine != null) {
                storeCache.setLineId(storeLine1.getLineId());
                storeCache.setLineName(distributionLine.getLineName());
                storeCache.setLineSort(storeLine1.getLineSort());
            } else {
                storeCache.setLineId(null);
                storeCache.setLineName(null);
                storeCache.setLineSort(null);
            }
            cacheTool.saveStoreUser(userId + "", storeCache.getStoreId() + "");
            cacheTool.saveStoreCache(storeCache.getStoreId() + "", storeCache);
        }

        StoreInfoDto dto = StoreMapStruct.MAPPER.storeCacheToStoreInfoDto(storeCache);
        result.setData(dto);
        result.setSuccess(true);
        return result;
    }

    /**
     * 通过userId查询门店银行卡信息
     *
     * @param userId
     * @return
     */
    @Override
    public MerchantResult getProFileInfoById(Long userId) {
        MerchantResult result = new MerchantResult();
        Long storeId = cacheTool.findStoreUserByKey(userId + "");
        StoreCache storeCache = new StoreCache();

        Store store = cacheTool.findStoreByKey(storeId + "");
        if (storeId == null || store == null) {
            store = storeMapper.getStoreByUserId(userId);
            if (store == null) {
                result.setSuccess(false);
                result.setData("门店异常，有疑问请您联络客服");
                return result;
            }

            if(!StatusEnum.NORMAL.getValue().equals(store.getStoreStatus())){
                result.setSuccess(false);
                StatusEnum statusEnum = StatusEnum.getByValue(store.getStoreStatus());
                result.setData("该门店目前是" + statusEnum.getDesc() + "状态，有疑问请您联络客服");
                return result;
            }
            storeCache = StoreMapStruct.MAPPER.storeToStoreCache(store);
            StoreLine storeLine1 = new StoreLine();
            storeLine1.setStoreId(store.getStoreId());
            storeLine1 = storeLineMapper.selectOne(storeLine1);
            DistributionLine distributionLine = distributionLineMapper.selectByPrimaryKey(storeLine1.getLineId());
            if (distributionLine != null) {
                storeCache.setLineId(storeLine1.getLineId());
                storeCache.setLineName(distributionLine.getLineName());
                storeCache.setLineSort(storeLine1.getLineSort());
            } else {
                storeCache.setLineId(null);
                storeCache.setLineName(null);
                storeCache.setLineSort(null);
            }
            cacheTool.saveStoreUser(userId + "", storeCache.getStoreId() + "");
            cacheTool.saveStoreCache(store.getStoreId() + "", storeCache);
        }


        StoreProFileInfoDto dto = StoreMapStruct.MAPPER.storeToStoreProFileInfoDto(store);
        result.setData(dto);
        result.setSuccess(true);
        return result;
    }

    /**
     * 更新门店
     *
     * @param storeDto
     * @return MerchantResult
     */
    @Override
    public MerchantResult updateOneStore(StoreDto storeDto) {
        MerchantResult result = newTransactionTemplate.execute(new TransactionCallback<MerchantResult>() {
            MerchantResult<StoreDto> result = new MerchantResult<>();

            @Override
            public MerchantResult doInTransaction(TransactionStatus transactionStatus) {
                try {
                    Store store = StoreMapStruct.MAPPER.storeDtoToStore(storeDto);
                    storeMapper.updateById(store);
                    //判断是否是冻结、解冻、删除操作
                    String status = storeDto.getStoreStatus();
                    if (status != null) {
                        //更新门店用户状态
                        storeUserService.updateStoreUserStatus(storeDto.getStoreId(), status);
                    }

                    cacheTool.deleteStoreCache(store.getStoreId()+"");
                    result.setSuccess(true);
                    return result;
                } catch (Exception e) {
                    merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.STORE, ErrorCodeDetailEnum.STORE_UPDATE_ERROR);
                    LogUtil.error(e,"[store-service:更新门店信息]处理异常  errorCode={}", result.getErrorContext().fetchCurrentErrorCode());
                    // 事务回滚
                    transactionStatus.setRollbackOnly();
                }
                return result;
            }
        });
        return result;
    }

    ;

    /**
     * 保存门店配送线路
     *
     * @return MerchantResult
     */
    @Override
    public MerchantResult saveStoreLine(StoreLineRequest storeLineRequest) {
        MerchantResult result = newTransactionTemplate.execute(new TransactionCallback<MerchantResult>() {
            MerchantResult<StoreDto> result = new MerchantResult<>();

            @Override
            public MerchantResult doInTransaction(TransactionStatus transactionStatus) {
                try {
                    StoreLine storeLine = new StoreLine();
                    storeLine.setLineId(storeLineRequest.getLineId());
                    storeLine.setStoreId(storeLineRequest.getStoreId());
                    storeLine.setLineSort(storeLineRequest.getLineSort());
                    storeLineMapper.updateByStoreId(storeLine);

                    Store store = new Store();
                    store.setStoreId(storeLineRequest.getStoreId());
                    store.setWarehouseId(storeLineRequest.getWarehouseId());
                    store.setWarehouseName(storeLineRequest.getWarehouseName());
                    storeMapper.updateById(store);

                    cacheTool.deleteStoreCache(store.getStoreId() + "");
                    result.setSuccess(true);
                    return result;
                } catch (Exception e) {
                    merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.STORE, ErrorCodeDetailEnum.STORE_UPDATE_ERROR);
                    LogUtil.error(e,"[store-service:更新门店配送信息]处理异常  errorCode={}", result.getErrorContext().fetchCurrentErrorCode());
                    // 事务回滚
                    transactionStatus.setRollbackOnly();
                }
                return result;
            }
        });
        return result;
    }

    /**
     * 批量修改门店状态
     *
     * @param storeDto
     * @return
     */
    @Override
    public MerchantResult batchUpdateStatus(StoreDto storeDto) {
        MerchantResult result = newTransactionTemplate.execute(new TransactionCallback<MerchantResult>() {
            MerchantResult result = new MerchantResult();

            @Override
            public MerchantResult doInTransaction(TransactionStatus transactionStatus) {
                try {
                    storeMapper.batchUpdateStatus(storeDto);
                    //修改门店用户状态
                    storeUserService.batchUpdateStatus(storeDto.getStoreIds(),storeDto.getStoreStatus());

                    for (Long storeId : storeDto.getStoreIds()) {
                        cacheTool.deleteStoreCache(storeId + "");
                    }
                    result.setSuccess(true);
                    return result;
                } catch (Exception e) {
                    merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.STORE, ErrorCodeDetailEnum.STORE_UPDATE_ERROR);
                    LogUtil.error(e,"[store-service:批量修改门店状态]处理异常  errorCode={}",result.getErrorContext().fetchCurrentErrorCode());
                    // 事务回滚
                    transactionStatus.setRollbackOnly();
                }
                return result;
            }
        });
        return result;
    }

    /**
     * 根据门店id查询线路ids
     *
     * @param storeId
     * @return
     */
    @Override
    public MerchantResult<List<Integer>> getStoreLineIds(Long storeId) {
        MerchantResult<List<Integer>> merchantResult = new MerchantResult<>();
        List<Integer> ids = storeLineMapper.getStoreLineIdsByStoreId(storeId);
        if (ids == null || ids.size() == 0) {
            return merchantResult;
        }
        merchantResult.setData(ids);
        merchantResult.setSuccess(true);
        return merchantResult;
    }

    /**
     * 主键ID查询
     *
     * @param storeId
     * @return MerchantResult<StoreInfoDto>
     */
    @Override
    public MerchantResult<StoreDto> getStoreById(Long storeId) {
        MerchantResult<StoreDto> result = new MerchantResult<StoreDto>();
        Store store = storeMapper.selectByPrimaryKey(storeId);
        if (store == null) {
            return result;
        }
        StoreDto storeDto = StoreMapStruct.MAPPER.storeToStoreDto(store);
        DistributionLine distributionLine =
            distributionLineMapper.getDistributionLineByStoreId(storeDto.getStoreId());
        if(distributionLine != null){
            storeDto.setLineName(distributionLine.getLineName());
        }
        result.setData(storeDto);
        result.setSuccess(true);
        return result;
    }

    /**
     * 条件查询
     *
     * @param storeRequest
     * @return MerchantResult<List < StoreDto>>
     */
    @Override
    public MerchantResult<List<StoreDto>> getStoreList(StoreRequest storeRequest) {
        MerchantResult<List<StoreDto>> merchantResult = new MerchantResult<>();
        List<Store> stores = storeMapper.getStoreList(storeRequest);
        if (stores == null || stores.size() == 0) {
            return merchantResult;
        }

        List<StoreDto> storeDtos = StoreMapStruct.MAPPER.storesToStoreDtos(stores);
        merchantResult.setSuccess(true);
        merchantResult.setData(storeDtos);
        return merchantResult;
    }

    /**
     * 门店编号查询
     *
     * @param storeCodes
     * @return MerchantResult<List < StoreDto>>
     */
    @Override
    public MerchantResult<List<StoreDto>> getStoreListByCodes(List<String> storeCodes) {
        MerchantResult<List<StoreDto>> merchantResult = new MerchantResult<>();
        if (storeCodes == null || storeCodes.size() == 0) {
            return merchantResult;
        }
        List<Store> stores = storeMapper.getStoreListByCodes(storeCodes);
        if (stores == null || stores.size() == 0) {
            return merchantResult;
        }
        List<StoreDto> storeDtos = StoreMapStruct.MAPPER.storesToStoreDtos(stores);
        merchantResult.setSuccess(true);
        merchantResult.setData(storeDtos);
        return merchantResult;
    }

    /**
     * 根据手机号查询供应商信息
     *
     * @param contactsTel
     * @return MerchantResult<List                                                                                                                               <                                                                                                                               StoreDto>>
     */
    @Override
    public MerchantResult<List<StoreDto>> getStoreListByContactsTel(String contactsTel) {
        MerchantResult<List<StoreDto>> merchantResult = new MerchantResult<>();
        List<Store> stores =
                storeMapper.selectList(new EntityWrapper<Store>().eq("contactsTel", contactsTel));

        if (stores == null || stores.size() == 0) {
            return merchantResult;
        }
        List<StoreDto> storeDtos =  StoreMapStruct.MAPPER.storesToStoreDtos(stores);
        merchantResult.setSuccess(true);
        merchantResult.setData(storeDtos);
        return merchantResult;
    }


    /**
     * 根据storeIds获取门店信息
     *
     * @param storeIds
     * @return MerchantResult<List                                                                                                                               <                                                                                                                               StoreDto>>
     */
    @Override
    public MerchantResult<List<StoreDto>> getStoreListByStoreIds(List<Long> storeIds) {
        List<Store> stores = storeMapper.getBatchVendorIds(storeIds);
        MerchantResult<List<StoreDto>> result = new MerchantResult<>();
        if (stores == null || stores.size() == 0) {
            return result;
        }
        List<StoreDto> storeDtos = StoreMapStruct.MAPPER.storesToStoreDtos(stores);
        result.setData(storeDtos);
        result.setSuccess(true);
        return result;
    }

    /**
     * 根据storeId获取门店线路信息
     *
     * @param storeId
     * @return MerchantResult<StoreLine>
     */
    @Override
    public MerchantResult<StoreLineDto> getStoreLineByStoreId(Long storeId) {
        MerchantResult<StoreLineDto> result = new MerchantResult<>();
        List<StoreLine> storeLines =
                storeLineMapper.selectList(new EntityWrapper<StoreLine>().eq("storeId", storeId));
        if (storeLines == null || storeLines.size() == 0) {
            return result;
        }
        StoreLineDto storeLineDto = StoreLineMapStruct.MAPPER.storeLineToStoreLineDto(storeLines.get(0));
        result.setSuccess(true);
        result.setData(storeLineDto);
        return result;
    }

    /**
     * 通过storeid获取门店信息
     *
     * @param storeId
     * @return
     */
    @Override
    public MerchantResult<StoreCache> getStoreCacheById(Long storeId) {
        MerchantResult<StoreCache> result = new MerchantResult<StoreCache>();
        StoreCache storeCache = cacheTool.findStoreCacheByKey(storeId + "");

        if (storeCache == null) {
            Store store = storeMapper.selectByPrimaryKey(storeId);
            if (store == null) {
                result.setSuccess(false);
                return result;
            }
            storeCache = StoreMapStruct.MAPPER.storeToStoreCache(store);
            StoreLine storeLine = new StoreLine();
            storeLine.setStoreId(storeId);
            storeLine = storeLineMapper.selectOne(storeLine);
            DistributionLine distributionLine = distributionLineMapper.selectByPrimaryKey(storeLine.getLineId());
            if (distributionLine != null) {
                storeCache.setLineId(storeLine.getLineId());
                storeCache.setLineName(distributionLine.getLineName());
                storeCache.setLineSort(storeLine.getLineSort());
            } else {
                storeCache.setLineId(null);
                storeCache.setLineName(null);
                storeCache.setLineSort(null);
            }
            cacheTool.saveStoreCache(storeCache.getStoreId() + "", storeCache);
        }
        result.setData(storeCache);
        result.setSuccess(true);
        return result;
    }

    /**
     * 获取userId
     *
     * @param storeId
     * @return
     */
    @Override
    public MerchantResult<Long> getUserId(Long storeId) {
        MerchantResult<Long> merchantResult = new MerchantResult<>();
        StoreUserRef storeUserRef = new StoreUserRef();
        storeUserRef.setStoreId(storeId);
        StoreUserRef storeUserRefResult = storeUserRefMapper.selectOne(storeUserRef);
        if (storeUserRefResult == null) {
            return merchantResult;
        }
        merchantResult.setSuccess(true);
        merchantResult.setData(storeUserRefResult.getUserId());
        return merchantResult;
    }

    @Override
    public MerchantResult updateStoreByUserId(StoreDto storeDto, Long userId) {
        MerchantResult result = newTransactionTemplate.execute(new TransactionCallback<MerchantResult>() {
            MerchantResult result = new MerchantResult();
            @Override
            public MerchantResult doInTransaction(TransactionStatus transactionStatus) {
                Long storeId = cacheTool.findStoreUserByKey(userId + "");
                storeDto.setStoreId(storeId);
                try{
                    Store store = StoreMapStruct.MAPPER.storeDtoToStore(storeDto);
                    storeMapper.updateStoreSignAndLog(store);
                    result.setSuccess(true);
                    cacheTool.deleteStoreCache(storeId+"");
                    return result;
                } catch (Exception e){
                    merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.STORE, ErrorCodeDetailEnum.STORE_UPDATE_ERROR);
                    LogUtil.error(e,"[store-service:修改门店信息]处理异常  errorCode={}", result.getErrorContext().fetchCurrentErrorCode());
                    // 事务回滚
                    transactionStatus.setRollbackOnly();
                }
                return result;
            }
        });
        return result;
    }

    /**
     * 条件查询
     * @param storeRequest
     * @return
     */
    @Override
    public MerchantResult<List<StoreDto>> getStoreListByStoreRequest(StoreRequest storeRequest) {
        MerchantResult<List<StoreDto>> result = new MerchantResult();
        List<Store> stores = storeMapper.getStoreListByStoreRequest(storeRequest);
        if(stores == null ||stores.size() == 0){
            return result;
        }
        List<StoreDto> storeDtos = StoreMapStruct.MAPPER.storesToStoreDtos(stores);
        result.setData(storeDtos);
        result.setSuccess(true);
        return result;

    }

    /**
     * 修改门店线路顺序
     * @param storeId
     * @param lineSort
     * @return
     */
    @Override
    public MerchantResult<StoreLineDto> updateStoreLineSort(Long storeId, Integer lineSort) {
        MerchantResult result =
            newTransactionTemplate.execute(new TransactionCallback<MerchantResult>() {
                MerchantResult<StoreLineDto> result = new MerchantResult<>();
                @Override
                public MerchantResult doInTransaction(TransactionStatus transactionStatus) {
                    try{
                        StoreLine storeLine = new StoreLine();
                        storeLine.setStoreId(storeId);
                        StoreLine selectOne = storeLineMapper.selectOne(storeLine);
                        if(selectOne.getLineId() == 0){
                            return result;
                        }

                        selectOne.setLineSort(lineSort);
                        storeLineMapper.updateById(selectOne);
                        StoreLineDto storeLineDto = StoreLineMapStruct.MAPPER.storeLineToStoreLineDto(selectOne);
                        result.setSuccess(true);
                        result.setData(storeLineDto);

                        cacheTool.deleteStoreCache(storeId+"");
                        return result;
                    }catch (Exception e){
                        merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.STORE, ErrorCodeDetailEnum.STORE_UPDATE_ERROR);
                        LogUtil.error(e,"[store-service:修改门店线路顺序状态]处理异常  errorCode={}", result.getErrorContext().fetchCurrentErrorCode());
                        // 事务回滚
                        transactionStatus.setRollbackOnly();
                    }
                    return result;
                }
            });
        return result;

    }

    @Override
    public MerchantResult saveStoreList(List<StoreDto> storeDtos) {
        MerchantResult result = newTransactionTemplate.execute(new TransactionCallback<MerchantResult>() {
            MerchantResult<StoreDto> result = new MerchantResult<>();

            @Override
            public MerchantResult doInTransaction(TransactionStatus transactionStatus) {
                try {

                    for (StoreDto storeDto : storeDtos){
                        Store store = StoreMapStruct.MAPPER.storeDtoToStore(storeDto);
                        StoreLine storeLine = new StoreLine();
                        if (store.getStoreId() != 0) {
                            storeMapper.updateById(store);
                            cacheTool.deleteStoreCache(store.getStoreId()+"");
                        } else {
                            //增加门店
                            store.setStoreId(idGenerator.genStoreId());
                            store.setTmCreate(new Date());
                            storeMapper.insert(store);
                            //增加线路
                            storeLine.setStoreId(store.getStoreId());
                            storeLine.setTmCreate(new Date());
                            storeLineMapper.insert(storeLine);
                            //增加门店用户
                            storeUserService.saveStoreUser(store, storeDto.getUserName());
                            //新增门店资金账户
                            StoreBalanceDto storeBalanceDto = new StoreBalanceDto();
                            storeBalanceDto.setStoreId(store.getStoreId());
                            storeBalanceDto.setStoreCode(store.getStoreCode());
                            storeBalanceDto.setStoreName(store.getStoreName());
                            storeBalanceDto.setAreaId(store.getAreaId());
                            storeBalanceDto.setAreaName(store.getAreaName());
                            fundDubboProcess.addStoreBalance(storeBalanceDto);
                        }
                    }
                    result.setSuccess(true);
                    return result;
                } catch (Exception e) {
                    merchantResultHelper.fillWithFailure(result, ErrorCodeScenarioEnum.STORE, ErrorCodeDetailEnum.STORE_SAVE_ERROR);
                    LogUtil.error(e,"[store-service:保存门店信息]处理异常  errorCode={}", result.getErrorContext().fetchCurrentErrorCode());
                    // 事务回滚
                    transactionStatus.setRollbackOnly();
                }
                return result;
            }
        });
        return result;
    }

    /**
     * 通过门店账号获取门店用户关联信息
     * @param userName
     * @return
     */
    @Override
    public MerchantResult<StoreUserDto> getStoreUser(String userName) {
        MerchantResult<StoreUserDto> merchantResult = new MerchantResult<>();
        StoreUserRef storeUserRef = new StoreUserRef();
        storeUserRef.setUserName(userName);
        storeUserRef.setIsDeleted(IsDeleteEnum.IS_DELETE_N.getValueDefined());
        StoreUserRef storeUserRefResult = storeUserRefMapper.selectOne(storeUserRef);
        if (storeUserRefResult == null) {
            return merchantResult;
        }
        StoreUserDto storeUserDto = StoreUserRefMapStruct.MAPPER.storeUserRefToStoreUserDto(storeUserRefResult);
        merchantResult.setSuccess(true);
        merchantResult.setData(storeUserDto);
        return merchantResult;
    }

    @Override
    public MerchantResult<List<StoreLineDto>> getStoreLineListByStoreIds(List<Long> storeIds) {
        MerchantResult<List<StoreLineDto>> result = new MerchantResult<>();
        List<StoreLine> storeLines =
            storeLineMapper.selectList(new EntityWrapper<StoreLine>()
                .in("storeId", storeIds)
                .isNotNull("lineId"));
        if(storeLines == null || storeLines.size() == 0){
            return result;
        }
        List<StoreLineDto> storeLineDtos =
            StoreLineMapStruct.MAPPER.storeLinesToStoreLineDtos(storeLines);
        result.setSuccess(true);
        result.setData(storeLineDtos);
        return result;
    }
}
